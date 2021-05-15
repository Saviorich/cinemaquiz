package by.bntu.fitr.cinemaquiz.controller.command;

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
