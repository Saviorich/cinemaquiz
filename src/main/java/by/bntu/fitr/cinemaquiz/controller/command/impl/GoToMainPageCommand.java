package by.bntu.fitr.cinemaquiz.controller.command.impl;

import by.bntu.fitr.cinemaquiz.model.entity.Quiz;
import by.bntu.fitr.cinemaquiz.model.service.ServiceProvider;
import by.bntu.fitr.cinemaquiz.model.service.exception.ServiceException;
import by.bntu.fitr.cinemaquiz.controller.command.Command;
import by.bntu.fitr.cinemaquiz.model.service.QuizService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class GoToMainPageCommand implements Command {

    private static final Logger logger = LogManager.getLogger(GoToMainPageCommand.class);
    private static final QuizService quizService = ServiceProvider.getInstance().getQuizService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            List<Quiz> quizList = quizService.findAll();
            request.getSession().setAttribute("quizList", quizList);
            request.getRequestDispatcher("/WEB-INF/jsp/main.jsp").forward(request, response);
        } catch (ServiceException e) {
            logger.fatal(e);
        }
    }
}
