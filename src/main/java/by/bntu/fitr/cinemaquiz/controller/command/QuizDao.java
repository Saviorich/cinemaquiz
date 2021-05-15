package by.bntu.fitr.cinemaquiz.controller.command;

import by.bntu.fitr.model.entity.Quiz;

import java.util.List;

public interface QuizDao {
    List<Quiz> findAll();
}
