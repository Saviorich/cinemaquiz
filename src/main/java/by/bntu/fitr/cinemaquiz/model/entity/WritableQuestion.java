package by.bntu.fitr.cinemaquiz.model.entity;

public class WritableQuestion extends Question {

    public WritableQuestion(long id, String title, String correctAnswer) {
        super(id, title, correctAnswer);
    }

    public WritableQuestion(String title, String correctAnswer) {
        super(title, correctAnswer);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("WritableQuestion{");
        sb.append("title=").append(getTitle());
        sb.append(", correctAnswer=").append(getCorrectAnswer());
        sb.append('}');
        return sb.toString();
    }
}
