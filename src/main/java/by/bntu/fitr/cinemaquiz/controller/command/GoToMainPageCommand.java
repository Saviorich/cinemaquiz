package by.bntu.fitr.cinemaquiz.controller.command;

import by.bntu.fitr.model.entity.Quiz;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GoToMainPageCommand implements Command {

    private static final QuizService quizService = ServiceProvider.getInstance().getQuizService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Занести в сессию квизы
        request.getRequestDispatcher("/WEB-INF/jsp/main.jsp").forward(request, response);
    }
}
