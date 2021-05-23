package by.bntu.fitr.cinemaquiz.controller.command.impl;

import by.bntu.fitr.cinemaquiz.controller.command.Command;
import by.bntu.fitr.cinemaquiz.model.entity.Quiz;
import by.bntu.fitr.cinemaquiz.model.service.QuizService;
import by.bntu.fitr.cinemaquiz.model.service.ServiceProvider;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FinishQuizCommand implements Command {

    private static final QuizService quizService = ServiceProvider.getInstance().getQuizService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Quiz quiz = (Quiz) request.getSession().getAttribute("quiz");

        double perc = quizService.calculatePercentage(quiz);
        request.setAttribute("result", perc);
        request.getRequestDispatcher("/WEB-INF/jsp/result.jsp").forward(request, response);
    }
}
