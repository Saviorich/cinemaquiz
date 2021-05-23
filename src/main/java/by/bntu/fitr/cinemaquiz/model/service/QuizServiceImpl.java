package by.bntu.fitr.cinemaquiz.model.service;

import by.bntu.fitr.cinemaquiz.model.dao.DaoProvider;
import by.bntu.fitr.cinemaquiz.model.dao.QuizDao;
import by.bntu.fitr.cinemaquiz.model.dao.exception.DaoException;
import by.bntu.fitr.cinemaquiz.model.entity.Question;
import by.bntu.fitr.cinemaquiz.model.entity.Quiz;
import by.bntu.fitr.cinemaquiz.model.service.exception.ServiceException;

import java.util.List;

public class QuizServiceImpl implements QuizService {

    private static final QuizDao quizDao = DaoProvider.getInstance().getQuizDao();

    @Override
    public List<Quiz> findAll() throws ServiceException {
        try {
            return quizDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException();
        }
    }

    @Override
    public void createQuiz(String title, String imagePath, List<Question> questions) throws ServiceException {
        try{
            quizDao.createQuiz(title, imagePath, questions);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public double calculatePercentage(Quiz quiz) {
        int quizSize = quiz.getQuestionList().size();
        int correctAmount = 0;
        for (Question question : quiz.getQuestionList()) {
            if (question.isCorrect()) {
                correctAmount++;
            }
        }
        return (double) correctAmount / quizSize;
    }
}
