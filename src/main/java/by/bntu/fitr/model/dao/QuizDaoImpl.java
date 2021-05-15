package by.bntu.fitr.model.dao;

import by.bntu.fitr.model.connection.ConnectionPool;
import by.bntu.fitr.model.connection.exception.ConnectionPoolException;
import by.bntu.fitr.model.entity.OptionalQuestion;
import by.bntu.fitr.model.entity.Question;
import by.bntu.fitr.model.entity.Quiz;
import by.bntu.fitr.model.entity.WritableQuestion;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QuizDaoImpl implements QuizDao {
    private static final Logger logger = LogManager.getLogger();
    private static final ConnectionPool pool = ConnectionPool.getInstance();
    private static final String FIND_ALL_QUIZ_STATEMENT =
            "SELECT * FROM quiz";
    private static final String FIND_QUIZ_QUESTIONS_STATEMENT =
            "SELECT * FROM question WHERE quiz_id=?";
    private static final String FIND_OPTIONS_STATEMENT=
            "SELECT content FROM `option` WHERE question_id=?";


    @Override
    public List<Quiz> findAll() {
        // TODO: 5/16/2021 Realizovat
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
        }
        return quizList;
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
        if (type.equals("")) {
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