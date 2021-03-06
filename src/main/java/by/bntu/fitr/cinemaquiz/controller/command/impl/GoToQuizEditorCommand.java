package by.bntu.fitr.cinemaquiz.controller.command.impl;

import by.bntu.fitr.cinemaquiz.controller.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GoToQuizEditorCommand implements Command {
    private static final String EDITOR_PAGE = "/WEB-INF/jsp/quiz_editor.jsp";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(EDITOR_PAGE).
                forward(request, response);
    }
}
