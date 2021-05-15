package by.bntu.fitr.model.service;

import by.bntu.fitr.model.entity.Quiz;

import java.util.List;

public interface QuizService {
    List<Quiz> findAll();
    double calculatePercentage(Quiz quiz);
}
