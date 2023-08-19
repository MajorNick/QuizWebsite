package Quiz.src.main.java.HelperMethods;

import Quiz.src.main.java.models.Answer;
import Quiz.src.main.java.models.DBConn;
import Quiz.src.main.java.models.Question;

import java.util.List;

public class AnswerChecker {
        public static double checkAnswer(int questionId,String answer){
            DBConn con = new DBConn();
            Question quest = con.getQuestion(questionId);
            List<Answer> answers = con.getAnswers(questionId,true);
            switch(quest.type){
                case QUESTION_RESPONSE :
                    return answer.equalsIgnoreCase(answers.get(0).answer)?1.0:0;

            }
            return 0;
        }
}
