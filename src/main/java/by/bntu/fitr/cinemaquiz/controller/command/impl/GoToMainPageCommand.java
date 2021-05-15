package by.bntu.fitr.cinemaquiz.controller.command.impl;

import by.bntu.fitr.cinemaquiz.controller.command.Command;
import by.bntu.fitr.model.service.QuizService;
import by.bntu.fitr.model.service.ServiceProvider;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GoToMainPageCommand implements Command {

    private static final QuizService quizService = ServiceProvider.getInstance().getQuizService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Занести в сессию квизы
        request.getRequestDispatcher("/WEB-INF/jsp/main.jsp").forward(request, response);
    }
}
