package by.bntu.fitr.cinemaquiz.controller.command.impl;

import by.bntu.fitr.cinemaquiz.model.entity.Quiz;
import by.bntu.fitr.cinemaquiz.model.service.QuizService;
import by.bntu.fitr.cinemaquiz.model.service.ServiceProvider;
import by.bntu.fitr.cinemaquiz.model.service.exception.ServiceException;
import jdk.nashorn.internal.runtime.Context;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class GoToQuizPageCommand implements by.bntu.fitr.cinemaquiz.controller.command.Command {

    QuizService quizService = ServiceProvider.getInstance().getQuizService();
    private static final Logger logger = LogManager.getLogger();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int quizId = Integer.parseInt(request.getParameter("data_id"));
        HttpSession session = request.getSession();
        int question = Integer.parseInt(request.getParameter("question"));

        try {
            if (quizId != ((Quiz) session.getAttribute("quiz")).getId()) {
                session.setAttribute("quiz", quizService.getById(quizId));
            }
            request.setAttribute("question", question);
        } catch (ServiceException e) {
            logger.error(e);
        }
        request.getRequestDispatcher("/WEB-INF/jsp/question.jsp").forward(request, response);
    }
}
