package Quiz.src.main.java.servlets;
import Quiz.src.main.java.models.Answer;
import Quiz.src.main.java.models.DBConn;
import Quiz.src.main.java.models.Question;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpSession;

@WebServlet("/preparePractice")
public class PreparePracticeQuiz extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int quizId = Integer.parseInt(req.getParameter("id"));

        LinkedHashMap<Question, ArrayList<Answer>> sessionQuestions = new LinkedHashMap<>();
        DBConn con = new DBConn();
        ArrayList<Question> questions = con.getQuestions(quizId);
        for(Question quest : questions){
            sessionQuestions.put(quest,con.getAnswers(quest.quiz_id,true));
        }
        HttpSession session = req.getSession();
        session.setAttribute("questionMap",sessionQuestions);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/quizPractice");
        dispatcher.forward(req, resp);
    }

}
