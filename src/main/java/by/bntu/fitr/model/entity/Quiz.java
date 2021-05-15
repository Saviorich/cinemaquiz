package by.bntu.fitr.model.entity;

import java.util.List;

public class Quiz {
    private long id;
    private String title;
    private String imagePath;
    private List<Question> questionList;

    public Quiz() {
    }

    public Quiz(long id, String title, String imagePath, List<Question> questionList) {
        this.id = id;
        this.title = title;
        this.imagePath = imagePath;
        this.questionList = questionList;
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

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public List<Question> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(List<Question> questionList) {
        this.questionList = questionList;
    }
}
