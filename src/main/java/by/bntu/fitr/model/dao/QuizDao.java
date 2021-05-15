package by.bntu.fitr.model.dao;

import by.bntu.fitr.model.entity.Quiz;

import java.util.List;

public interface QuizDao {
    List<Quiz> findAll();
}
