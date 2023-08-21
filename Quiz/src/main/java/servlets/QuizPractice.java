package Quiz.src.main.java.servlets;

import Quiz.src.main.java.models.Answer;
import Quiz.src.main.java.models.DBConn;
import Quiz.src.main.java.models.Question;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;


@WebServlet("/quizPractice")
public class QuizPractice extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession ses = req.getSession();
        Integer trials = (Integer)ses.getAttribute("trialsLeft");
        if (trials == 0){
            //quiz ended
        }else{
            LinkedHashMap <Question, ArrayList<Answer>> mp = (LinkedHashMap) ses.getAttribute("questionMap");
            ses.setAttribute("trialsLeft",trials-1);
        }
    }

}
