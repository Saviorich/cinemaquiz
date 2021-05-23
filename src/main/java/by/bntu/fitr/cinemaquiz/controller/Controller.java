package by.bntu.fitr.cinemaquiz.controller;

import by.bntu.fitr.cinemaquiz.controller.command.Command;
import by.bntu.fitr.cinemaquiz.controller.command.CommandProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "Controller", value = "/Controller")
public class Controller extends HttpServlet {

    private static final Logger logger = LogManager.getLogger();

    private final CommandProvider provider = CommandProvider.getInstance();

    private static final String COMMAND = "command";

    public Controller() {super();}

    public void init() {
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    /**
     * Process the request
     * @param request contains the command that will be executed
     */
    private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name;
        Command command;

        name = request.getParameter(COMMAND);
        command = provider.takeCommand(name);

        logger.debug("name={}, class={}", name, command);

        command.execute(request, response);
    }

    public void destroy() {
    }
}