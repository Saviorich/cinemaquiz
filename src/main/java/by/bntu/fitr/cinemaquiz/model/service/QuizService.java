package by.bntu.fitr.cinemaquiz.model.service;

import by.bntu.fitr.cinemaquiz.model.entity.Quiz;
import by.bntu.fitr.cinemaquiz.model.service.exception.ServiceException;

import java.util.List;

public interface QuizService {
    List<Quiz> findAll() throws ServiceException;
    double calculatePercentage(Quiz quiz);
}
