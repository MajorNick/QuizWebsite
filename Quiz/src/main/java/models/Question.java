package Quiz.src.main.java.models;

public class Question{
    public int id;
    public int quiz_id;
    public String question;

    public Question(int id, int quiz_id, String question) {
        this.id = id;
        this.quiz_id = quiz_id;
        this.question = question;
    }

}