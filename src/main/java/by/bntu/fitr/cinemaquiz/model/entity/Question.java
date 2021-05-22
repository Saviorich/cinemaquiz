package by.bntu.fitr.cinemaquiz.model.entity;

public abstract class Question {
    private long id;
    private String title;
    private String userAnswer;
    private String correctAnswer;

    public Question() {    }

    public Question(String title, String correctAnswer) {
        this.title = title;
        this.correctAnswer = correctAnswer;
    }

    public Question(long id, String title, String correctAnswer) {
        this.id = id;
        this.title = title;
        this.correctAnswer = correctAnswer;
    }

    public Question(long id, String title, String userAnswer, String correctAnswer) {
        this.id = id;
        this.title = title;
        this.userAnswer = userAnswer;
        this.correctAnswer = correctAnswer;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUserAnswer() {
        return userAnswer;
    }

    public void setUserAnswer(String userAnswer) {
        this.userAnswer = userAnswer;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public boolean isCorrect() {
        return correctAnswer.equalsIgnoreCase(userAnswer);
    }

    public abstract String toString();
}
