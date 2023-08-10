package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class models {
    private static final String account = "'quiz'";
    private static final String password = "qazqaz123";
    private static final String server = "localhost";
    private static final String database = "oop_proj";
    public class Quiz{
        public    int id;
        public    int creator_id;
        public  String quiz_name;
        public  boolean is_single_page;
        public boolean can_be_practiced;

        public Quiz(int id, int creator_id, String quiz_name, boolean is_single_page, boolean can_be_practiced) {
            this.id = id;
            this.creator_id = creator_id;
            this.quiz_name = quiz_name;
            this.is_single_page = is_single_page;
            this.can_be_practiced = can_be_practiced;
        }

    }
    public ArrayList<Quiz> getQuizzes(){
        String quizzesQuery = "SELECT * FROM quizzes;";
        ArrayList<Quiz> selection = new ArrayList<>();
        try{
            Connection conn = DriverManager.getConnection("jdbc:mysql://" + server, account, password);
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("USE " + database);
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(quizzesQuery);

            while (rs.next()) {
                Quiz quiz = new Quiz(rs.getInt("id"),
                        rs.getInt("creator_id"),
                        rs.getString("quiz_name"),
                        rs.getBoolean("is_single_page"),
                        rs.getBoolean("can_be_practiced")
                );
                selection.add(quiz);
            }
            rs.close();
            stmt.close();
            conn.close();

        } catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
            return  null;
        }
        return selection;
    }
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
    public ArrayList<Question> getQuestions( int quiz_id){
        String questionQuery = String.format("SELECT * FROM questions where quiz_id = %d",quiz_id);
        ArrayList<Question> selection = new ArrayList<>();
        try{
            Connection conn = DriverManager.getConnection("jdbc:mysql://" + server, account, password);
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("USE " + database);
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(questionQuery);

            while (rs.next()) {
                Question question  = new Question(rs.getInt("id"),
                        rs.getInt("quiz_id"),
                        rs.getString("question")
                );
                selection.add(question);
            }
            rs.close();
            stmt.close();
            conn.close();

        } catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
            return  null;
        }
        return selection;
    }

    public class Answer{
        public int id;
        public int question_id;
        public String answer;
        public boolean isCorrect;

        public Answer(int id, int question_id, String answer, boolean isCorrect) {
            this.id = id;
            this.question_id = question_id;
            this.answer = answer;
            this.isCorrect = isCorrect;
        }
    }
    public ArrayList<Answer> getAnswers( int question_id){
        String questionQuery = String.format("SELECT * FROM answers where question_id = %d",question_id);
        ArrayList<Answer> selection = new ArrayList<>();
        try{
            Connection conn = DriverManager.getConnection("jdbc:mysql://" + server, account, password);
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("USE " + database);
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(questionQuery);

            while (rs.next()) {
                Answer answer  = new Answer(rs.getInt("id"),
                        rs.getInt("question_id"),
                        rs.getString("answer"),
                        rs.getBoolean("is_correct")
                );
                selection.add(answer);
            }
            rs.close();
            stmt.close();
            conn.close();

        } catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
            return  null;
        }
        return selection;
    }

}
