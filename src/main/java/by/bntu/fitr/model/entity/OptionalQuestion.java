package by.bntu.fitr.model.entity;

import com.mysql.cj.util.StringUtils;

import java.util.Collections;
import java.util.List;

public class OptionalQuestion extends Question {

    private List<String> options;

    public OptionalQuestion(long id, String title, String correctAnswer, List<String> options) {
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
}
