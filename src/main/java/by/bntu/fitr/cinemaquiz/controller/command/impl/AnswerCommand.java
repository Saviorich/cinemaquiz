package by.bntu.fitr.cinemaquiz.controller.command.impl;

import by.bntu.fitr.cinemaquiz.controller.command.Command;
import by.bntu.fitr.cinemaquiz.model.entity.Question;
import by.bntu.fitr.cinemaquiz.model.entity.Quiz;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AnswerCommand implements Command {

    private static final Logger logger = LogManager.getLogger();
    private static final String TO_REDIRECT  = "Controller?command=gotoquizpage&question=%d&data_id=%d";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        int questionNumber = Integer.parseInt(request.getParameter("question_number"));
        logger.debug("questionNumber={}", questionNumber);
        Quiz quiz = (Quiz) session.getAttribute("quiz");

        quiz.getQuestionList()
                .get(questionNumber)
                .setUserAnswer(request.getParameter("user_answer"));
        int pageNumber = ((questionNumber + 1) %  quiz.getQuestionList().size()) + 1;
        logger.debug("pageNumber={}", pageNumber);
        response.sendRedirect(String.format(TO_REDIRECT, pageNumber, quiz.getId()));
    }
}
