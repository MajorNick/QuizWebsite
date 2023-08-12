package Quiz.src.main.java.models;

public class QuizHistory {
    private int Id;
    private double Score;
    private int Quiz_id;
    private int User_id;
    private double Time_taken;
    public QuizHistory(int id, double score, int quiz_id, int user_id, double time_taken){
        Id = id;
        Score = score;
        Quiz_id = quiz_id;
        User_id = user_id;
        Time_taken = time_taken;
    }

    public int getId() {
        return Id;
    }

    public int getUser_id() {
        return User_id;
    }

    public int getQuiz_id() {
        return Quiz_id;
    }

    public double getScore() {
        return Score;
    }

    public double getTime_taken() {
        return Time_taken;
    }
}
