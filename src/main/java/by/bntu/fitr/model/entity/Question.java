package by.bntu.fitr.model.entity;

public abstract class Question {
    private String title;
    private String userAnswer;
    private String correctAnswer;

    public Question() {    }

    public Question(String title, String correctAnswer) {
        this.title = title;
        this.correctAnswer = correctAnswer;
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
        return userAnswer.equalsIgnoreCase(correctAnswer);
    }
}
