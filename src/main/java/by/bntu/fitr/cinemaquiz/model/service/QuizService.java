package by.bntu.fitr.cinemaquiz.model.service;

import by.bntu.fitr.cinemaquiz.model.entity.Question;
import by.bntu.fitr.cinemaquiz.model.entity.Quiz;
import by.bntu.fitr.cinemaquiz.model.service.exception.ServiceException;

import java.util.List;

public interface QuizService {
    List<Quiz> findAll() throws ServiceException;
    void createQuiz(String title, String imagePath, List<Question> questions) throws ServiceException;
    double calculatePercentage(Quiz quiz);

    Quiz getById(int quizId)throws ServiceException;
}
