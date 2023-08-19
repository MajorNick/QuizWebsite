package Quiz.src.main.java.models;

import Quiz.src.main.java.models.enums.QuestionType;

import java.util.Objects;

public class Question{
    public int id;
    public int quiz_id;
    public String question;
    public QuestionType type;
    public int num;
    public Question(int id, int quiz_id, String question, int type, int num) {
        this.id = id;
        this.quiz_id = quiz_id;
        this.question = question;
        this.type = QuestionType.fromInt(type);
        this.num = num;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question = (Question) o;
        return id == question.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}