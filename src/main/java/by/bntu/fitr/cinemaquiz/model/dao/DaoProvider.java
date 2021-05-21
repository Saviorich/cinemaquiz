package by.bntu.fitr.cinemaquiz.model.dao;

public final class DaoProvider {

    private static DaoProvider daoProvider = new DaoProvider();

    private QuizDao quizDao = new QuizDaoImpl();

    public static DaoProvider getInstance() {
        return daoProvider;
    }

    public QuizDao getQuizDao() {
        return quizDao;
    }
}
