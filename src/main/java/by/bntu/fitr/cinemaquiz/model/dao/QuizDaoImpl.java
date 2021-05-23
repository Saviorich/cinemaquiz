package by.bntu.fitr.cinemaquiz.model.dao;

import by.bntu.fitr.cinemaquiz.model.connection.ConnectionPool;
import by.bntu.fitr.cinemaquiz.model.connection.exception.ConnectionPoolException;
import by.bntu.fitr.cinemaquiz.model.dao.exception.DaoException;
import by.bntu.fitr.cinemaquiz.model.entity.OptionalQuestion;
import by.bntu.fitr.cinemaquiz.model.entity.Question;
import by.bntu.fitr.cinemaquiz.model.entity.Quiz;
import by.bntu.fitr.cinemaquiz.model.entity.WritableQuestion;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class QuizDaoImpl implements QuizDao {
    private static final Logger logger = LogManager.getLogger();
    private static final ConnectionPool pool = ConnectionPool.getInstance();
    private static final String OPTIONAL_TYPE = "optional";
    private static final String WRITABLE_TYPE = "writable";
    private static final String FIND_ALL_QUIZ_STATEMENT =
            "SELECT * FROM quiz";
    private static final String FIND_BY_ID = "SELECT * FROM quiz WHERE id=?";
    private static final String FIND_QUIZ_QUESTIONS_STATEMENT =
            "SELECT * FROM question WHERE quiz_id=?";
    private static final String FIND_OPTIONS_STATEMENT=
            "SELECT content FROM `option` WHERE question_id=?";
    private static final String ADD_NEW_QUIZ_STATEMENT = "INSERT INTO quiz (title, image_path) VALUES (?, ?)";
    private static final String ADD_NEW_QUESTION_STATEMENT = "INSERT INTO question(title, quiz_id, correct_answer, `type`) VALUES (?, ?, ?, ?)";
    private static final String ADD_NEW_OPTION = "INSERT INTO `option`(question_id, content) VALUES (?, ?)";


    @Override
    public List<Quiz> findAll() throws DaoException {
        List<Quiz> quizList = new ArrayList<>();
        try(Connection connection = pool.takeConnection();
            PreparedStatement statement = connection.prepareStatement(FIND_ALL_QUIZ_STATEMENT)){
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                Quiz quiz = createQuiz(connection, resultSet);
                quizList.add(quiz);
            }
        } catch (SQLException | ConnectionPoolException e){
            logger.error(e);
            throw new DaoException(e);
        }
        return quizList;
    }

    @Override
    public void createQuiz(String title, String imagePath, List<Question> questions) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = pool.takeConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(ADD_NEW_QUIZ_STATEMENT, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, title);
            statement.setString(2, imagePath);
            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            long id = 0;
            if (generatedKeys.next()) {
                id = generatedKeys.getLong(1);
            }
            statement.close();
            statement = connection.prepareStatement(ADD_NEW_QUESTION_STATEMENT, Statement.RETURN_GENERATED_KEYS);
            for (Question question : questions) {
                statement.setString(1, question.getTitle());
                statement.setLong(2, id);
                statement.setString(3,  question.getCorrectAnswer());
                String type;
                if (question instanceof OptionalQuestion){
                    type = OPTIONAL_TYPE;
                } else {
                    type = WRITABLE_TYPE;
                }
                statement.setString(4, type);
                statement.executeUpdate();
                if (type.equals(OPTIONAL_TYPE)){
                    ResultSet generatedKeysQuestion = statement.getGeneratedKeys();
                    long questionId = 0;
                    if (generatedKeysQuestion.next()) {
                        questionId = generatedKeysQuestion.getLong(1);
                    }
                    insertOptions(connection, questionId, ((OptionalQuestion) question).getOptions());
                }
            }
            connection.commit();
        } catch (SQLException | ConnectionPoolException e) {
            logger.error(e);
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    logger.error(ex);
                    throw new DaoException(ex);
                }
            }
            throw new DaoException(e);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException throwable) {
                    logger.error(throwable);
                }
            }
            if (connection != null) {
                try {
                    connection.setAutoCommit(true);
                    connection.close();
                } catch (SQLException e) {
                    logger.error(e);
                }
            }
        }
    }

    @Override
    public Optional<Quiz> getById(int id) throws DaoException {
        Optional<Quiz> quizList = Optional.empty();
        try(Connection connection = pool.takeConnection();
            PreparedStatement statement = connection.prepareStatement(FIND_BY_ID))
        {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()){
                Quiz quiz = createQuiz(connection, resultSet);
                quizList = Optional.of(quiz);
            }
        } catch (SQLException | ConnectionPoolException e){
            logger.error(e);
            throw new DaoException(e);
        }
        return quizList;
    }

    private void insertOptions(Connection connection, long id, List<String> options) throws SQLException{
        try (PreparedStatement preparedStatement = connection.prepareStatement(ADD_NEW_OPTION)){
            for (String option: options){
                preparedStatement.setLong(1, id);
                preparedStatement.setString(2, option);
                preparedStatement.executeUpdate();
            }
        }
    }

    private List<String> findOptions(Connection connection, long questionId) throws SQLException {
        List<String> options = new ArrayList<>();
        try(PreparedStatement statement = connection.prepareStatement(FIND_OPTIONS_STATEMENT)){
            statement.setLong(1, questionId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                options.add(resultSet.getString(1));
            }
        }
        return options;
    }

    private List<Question> findQuestions(Connection connection, long quizId) throws SQLException{
        List<Question> questionList = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(FIND_QUIZ_QUESTIONS_STATEMENT)){
            statement.setLong(1, quizId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                Question question = createQuestion(connection, resultSet);
                questionList.add(question);
            }
        }
        return questionList;
    }

    private Question createQuestion(Connection connection, ResultSet resultSet) throws SQLException {
        Question question;
        long id = resultSet.getLong(1);
        String title = resultSet.getString(3);
        String correctAnswer = resultSet.getString(4);
        String type = resultSet.getString(5);
        if (type.equals("optional")) {
            List<String> options = findOptions(connection, id);
            question = new OptionalQuestion(id, title, correctAnswer, options);
        } else {
            question = new WritableQuestion(id, title, correctAnswer);
        }
        return question;
    }

    private Quiz createQuiz(Connection connection, ResultSet resultSet) throws SQLException{
        long id = resultSet.getLong(1);
        String title = resultSet.getString(2);
        String imagePath = resultSet.getString(3);
        List<Question> questionList = findQuestions(connection, id);
        return new Quiz(id, title, imagePath, questionList);
    }
}