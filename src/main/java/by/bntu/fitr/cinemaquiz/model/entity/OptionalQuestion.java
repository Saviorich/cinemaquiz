package by.bntu.fitr.cinemaquiz.model.entity;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class OptionalQuestion extends Question {

    private List<String> options;

    public OptionalQuestion(long id, String title, String correctAnswer, List<String> options) {
        super(id, title, correctAnswer);
        this.options = options;
    }

    public OptionalQuestion(String title, String correctAnswer, List<String> options) {
        super(title, correctAnswer);
        this.options = options;
    }

    public List<String> getOptions(boolean shuffle) {
        if (shuffle) {
            Collections.shuffle(options);
        }
        return options;
    }

    public List<String> getOptions() {
        return getOptions(false);
    }

    @Override
    public String getType() {
        return getClass().getSimpleName();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("OptionalQuestion{");
        sb.append("options=").append(options);
        sb.append(", userAnswer=").append(getUserAnswer());
        sb.append('}');

        return sb.toString();
    }
}
