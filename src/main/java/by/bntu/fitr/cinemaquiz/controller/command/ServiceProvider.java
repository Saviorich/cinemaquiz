package by.bntu.fitr.cinemaquiz.controller.command;

public class ServiceProvider {

    private static ServiceProvider serviceProvider = new ServiceProvider();

    private QuizService quizService = new QuizServiceImpl();

    public static ServiceProvider getInstance() {
        return serviceProvider;
    }

    public QuizService getQuizService() {
        return quizService;
    }
}
