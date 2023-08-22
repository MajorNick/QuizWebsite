package Quiz.src.main.java.HelperMethods;

import Quiz.src.main.java.models.Answer;
import Quiz.src.main.java.models.DBConn;
import Quiz.src.main.java.models.Question;
import java.util.ArrayList;
import java.util.List;

public class AnswerChecker {
        public static ArrayList<String> checkAnswer(int questionId,ArrayList<String> givenAnswers){
            DBConn con = new DBConn();
            Question quest = con.getQuestion(questionId);
            List<Answer> answers = con.getAnswers(questionId,true);
            ArrayList<String> correctAnswers= new ArrayList<>();
            double score = answers.size();
            if(quest.isMultiAnswerType()){
                for(int i=0;i<answers.size();i++){
                    if(givenAnswers.contains(answers.get(i).answer)){
                        correctAnswers.add(givenAnswers.get(i));
                    }
                }
            }else{

                if(givenAnswers.contains(answers.get(0).answer)) {
                    correctAnswers.add(givenAnswers.get(0));
                }
            }
            return correctAnswers;
        }
    public static boolean checkAnswerBool(int questionId,ArrayList<String> givenAnswers){
        DBConn con = new DBConn();
        Question quest = con.getQuestion(questionId);
        List<Answer> answers = con.getAnswers(questionId,true);
        ArrayList<String> correctAnswers= new ArrayList<>();
        double score = answers.size();
        if(quest.isMultiAnswerType()){
            for (Answer answer : answers) {
                if (!givenAnswers.contains(answer.answer)) {
                    return false;
                }
            }
        }else{

            return givenAnswers.contains(answers.get(0).answer);
        }
        return true;
    }
}
