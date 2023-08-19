package Quiz.src.main.java.servlets;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.*;
import Quiz.src.main.java.models.*;
import Quiz.src.main.java.models.enums.*;

import static Quiz.src.main.java.HelperMethods.PassHasher.hashPassword;


@WebServlet(name = "quizResult", value = "/quizResult")
public class testeeer extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
        DBConn con = new DBConn();
        int quizID = Integer.parseInt(httpServletRequest.getParameter("id"));
        int totalScore = 0;

        Quiz quiz = con.getQuiz(quizID);
        HttpSession ses = httpServletRequest.getSession();
        ArrayList<Question> questions = (ArrayList)ses.getAttribute("shuffledQuestions");

        for(int i = 0; i < questions.size(); i++){
            Question question = questions.get(i);
            QuestionType questionType = question.type;
            List<Answer> correctAnswers = con.getAnswers(questions.get(i).quiz_id,true);

            if (questionType == QuestionType.QUESTION_RESPONSE){
                String answer =  httpServletRequest.getParameter("respons_q" + i);
                if(answer.equalsIgnoreCase(correctAnswers.get(0).answer)) totalScore++;

            } else if(questionType == QuestionType.FILL_IN_THE_BLANK){
                String answer =  httpServletRequest.getParameter("fill_in_the_blank_q" + i);
                if(answer.equalsIgnoreCase(correctAnswers.get(0).answer)) totalScore++;

            } else if(questionType == QuestionType.MULTIPLE_CHOICE){
                String answer =  httpServletRequest.getParameter("multiple_choice_q" + i);
                if(answer.equalsIgnoreCase(correctAnswers.get(0).answer)) totalScore++;

            } else if(questionType == QuestionType.PICTURE_RESPONSE) {
                String answer = httpServletRequest.getParameter("picture_response_q" + i);
                if(answer.equalsIgnoreCase(correctAnswers.get(0).answer)) totalScore++;

            } else if (questionType == QuestionType.MULTI_ANSWER){
                ArrayList<String> userAnswers = new ArrayList<String>();

                for(int k = 0; k < userAnswers.size(); k++){
                    for(int j = 0; j < correctAnswers.size(); j++){
                        if(userAnswers.get(k).equalsIgnoreCase(correctAnswers.get(j).answer)){
                            totalScore++;
                        }
                    }
                }

            } else if (questionType == QuestionType.MULTI_AN_CHOICE){
                ArrayList<String> userAnswers = new ArrayList<String>();
                Map<String, Integer> correctFrequencyMap = new HashMap<>();

                for (Answer curAnswer : correctAnswers) {
                    correctFrequencyMap.put(curAnswer.answer, correctFrequencyMap.getOrDefault(curAnswer.answer, 0) + 1);
                }

                for (int k = 0; k < userAnswers.size(); k++) {
                    String userAnswer = userAnswers.get(k);
                    if (correctFrequencyMap.containsKey(userAnswer) && correctFrequencyMap.get(userAnswer) > 0) {
                        correctFrequencyMap.put(userAnswer, correctFrequencyMap.get(userAnswer) - 1);
                        totalScore++;
                    }
                }
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException, ServletException, IOException {
        System.out.println("mze");
    }
}
