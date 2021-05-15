package by.bntu.fitr.model.service;

import by.bntu.fitr.model.entity.Quiz;
import by.bntu.fitr.model.service.exception.ServiceException;

import java.util.List;

public interface QuizService {
    List<Quiz> findAll() throws ServiceException;
    double calculatePercentage(Quiz quiz);
}
