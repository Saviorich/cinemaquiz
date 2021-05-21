package by.bntu.fitr.cinemaquiz.model.dao;

import by.bntu.fitr.cinemaquiz.model.dao.exception.DaoException;
import by.bntu.fitr.cinemaquiz.model.entity.Quiz;

import java.util.List;

public interface QuizDao {
    List<Quiz> findAll() throws DaoException;
}
