package by.bntu.fitr.cinemaquiz.controller.command.impl;

import by.bntu.fitr.cinemaquiz.controller.command.Command;
import by.bntu.fitr.cinemaquiz.model.entity.OptionalQuestion;
import by.bntu.fitr.cinemaquiz.model.entity.Question;
import by.bntu.fitr.cinemaquiz.model.entity.WritableQuestion;
import by.bntu.fitr.cinemaquiz.model.service.QuizService;
import by.bntu.fitr.cinemaquiz.model.service.ServiceProvider;
import by.bntu.fitr.cinemaquiz.model.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CreateQuizCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private static final String COMMAND_TO_REDIRECT = "Controller?command=gotomainpage";
    private static final QuizService quizService = ServiceProvider.getInstance().getQuizService();
    private static final int OPTION_AMOUNT = 4;


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String title = request.getParameter("quiz_name");
            String imagePath = (String) request.getAttribute("image_path");
            int questionAmount = Integer.parseInt(request.getParameter("question_amount"));
            List<Question> questions = new ArrayList<>();
            for (int i = 1; i <= questionAmount; i++) {
                String questionTitle = request.getParameter("question_title");
                String correctAnswer;
                Question question;
                String type = request.getParameter("question_type" + i);
                if (type != null && type.equals("on")) {
                    List<String> options = new ArrayList<>();
                    String correctOption = request.getParameter("r" + i);
                    correctAnswer = request.getParameter("Option" + correctOption + "ForQuestion" + i);
                    for (int j = 1; j <= OPTION_AMOUNT; j++){
                        options.add(request.getParameter("Option" + j + "ForQuestion" + i));
                    }

                    question = new OptionalQuestion(questionTitle, correctAnswer, options);
                } else {
                    correctAnswer = request.getParameter("correct_answer" + i);
                    question = new WritableQuestion(questionTitle, correctAnswer);
                }
                questions.add(question);
            }
            quizService.createQuiz(title, imagePath, questions);
        } catch (ServiceException e) {
            logger.error(e);
        }

        response.sendRedirect(COMMAND_TO_REDIRECT);
    }
}
