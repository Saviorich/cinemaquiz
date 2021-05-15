package by.bntu.fitr.model.service;

import by.bntu.fitr.model.dao.DaoProvider;
import by.bntu.fitr.model.dao.QuizDao;
import by.bntu.fitr.model.dao.exception.DaoException;
import by.bntu.fitr.model.entity.Question;
import by.bntu.fitr.model.entity.Quiz;
import by.bntu.fitr.model.service.exception.ServiceException;

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
