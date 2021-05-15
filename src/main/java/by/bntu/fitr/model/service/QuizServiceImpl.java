package by.bntu.fitr.model.service;

import by.bntu.fitr.model.dao.DaoProvider;
import by.bntu.fitr.model.dao.QuizDao;
import by.bntu.fitr.model.entity.Quiz;

import java.util.List;

public class QuizServiceImpl implements QuizService {

    private static final QuizDao quizDao = DaoProvider.getInstance().getQuizDao();

    @Override
    public List<Quiz> findAll() {
        return null;
    }

    @Override
    public double calculatePercentage(Quiz quiz) {
        return 0;
    }
}
