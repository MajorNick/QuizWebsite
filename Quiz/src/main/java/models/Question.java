package Quiz.src.main.java.models;

import Quiz.src.main.java.models.enums.QuestionType;

public class Question{
    public int id;
    public int quiz_id;
    public String question;
    public QuestionType type;
    public Question(int id, int quiz_id, String question,int type) {
        this.id = id;
        this.quiz_id = quiz_id;
        this.question = question;
        this.type = QuestionType.fromInt(type);
    }

}