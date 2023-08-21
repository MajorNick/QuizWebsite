package Quiz.src.main.java.HelperMethods;

import Quiz.src.main.java.models.Answer;
import Quiz.src.main.java.models.DBConn;
import Quiz.src.main.java.models.Question;

import java.util.ArrayList;
import java.util.List;

public class AnswerChecker {
        public static double checkAnswer(int questionId, ArrayList<String> answers){
            DBConn con = new DBConn();
            Question quest = con.getQuestion(questionId);
            List<Answer> answerscon = con.getAnswers(questionId,true);
            if (quest.isMultiAnswerType()){

            }else{

            }
            return 0;
        }
}
