package Quiz.src.main.java.HelperMethods;

import Quiz.src.main.java.models.Answer;
import Quiz.src.main.java.models.DBConn;
import Quiz.src.main.java.models.Question;
import java.util.ArrayList;
import java.util.List;

public class AnswerChecker {
        public static double checkAnswer(int questionId,ArrayList<String> givenAnswers){
            DBConn con = new DBConn();
            Question quest = con.getQuestion(questionId);
            List<Answer> answers = con.getAnswers(questionId,true);
            double score = answers.size();
            if(quest.isMultiAnswerType()){
                for(int i=0;i<answers.size();i++){
                    if(!givenAnswers.contains(answers.get(i).answer)){
                        score--;
                    }
                    System.out.println(givenAnswers.get(i));
                }
            }else{
                System.out.println(answers.get(0).answer.equalsIgnoreCase(givenAnswers.get(0)));
                score = answers.get(0).answer.equalsIgnoreCase(givenAnswers.get(0))?1:0;
            }
            return score;
        }
}
