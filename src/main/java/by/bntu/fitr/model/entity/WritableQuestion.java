package by.bntu.fitr.model.entity;

public class WritableQuestion extends Question {

    public WritableQuestion(long id, String title, String correctAnswer) {
    }

    public WritableQuestion(String title, String correctAnswer) {
        super(title, correctAnswer);
    }
}
