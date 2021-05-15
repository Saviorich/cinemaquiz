package by.bntu.fitr.cinemaquiz.controller.command.impl;

import by.bntu.fitr.cinemaquiz.controller.command.Command;
import by.bntu.fitr.model.entity.Quiz;
import by.bntu.fitr.model.service.QuizService;
import by.bntu.fitr.model.service.ServiceProvider;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

public class GoToMainPageCommand implements Command {

    private static final QuizService quizService = ServiceProvider.getInstance().getQuizService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Quiz q1 = new Quiz(1, "privvet", "img/тёма.jpg", null);
        Quiz q2 = new Quiz(2, "[poka]", "img/sanya.PNG", null);
        Quiz q3 = new Quiz(3, "zdarova", "img/sanya2.PNG", null);

        request.getSession().setAttribute("quizList", Arrays.asList(q1, q2, q3));

        request.getRequestDispatcher("/WEB-INF/jsp/main.jsp").forward(request, response);
    }
}
