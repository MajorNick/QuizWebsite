package Quiz.src.main.java.servlets;

import Quiz.src.main.java.models.DBConn;
import Quiz.src.main.java.models.Notification;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.annotation.WebServlet;

@WebServlet("/Challenge")
public class Challenge extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Send Challenge Servlet");
        response.setContentType("text/html");

        String QuizId = request.getParameter("quizId");
        String UserId = request.getParameter("userId");
        String TargetId = request.getParameter("targetId");

        int targetId = Integer.parseInt(TargetId);
        int userId = Integer.parseInt(UserId);

        if(QuizId == "" || QuizId == null){
            String redirectUrl = String.format("./userProfile.jsp?id=%d&challengetext=%s", targetId, "Incorrect Quiz Id");
            response.sendRedirect(redirectUrl);
            return;
        }

        int quizId = Integer.parseInt(QuizId);

        // check if challenger completed quiz
        // get challenger score

        String quizLink = String.format("<a href=\"./index.jsp?id=%d\">Quiz</a>", quizId);
        String challengeText = String.format("User %d has Challenged you to %s", userId, quizLink);
        System.out.println(challengeText);

        DBConn dbConn = new DBConn();

        Notification notification = new Notification(-1, targetId, userId, "challenge", challengeText);

        dbConn.insertNotification(notification);

        dbConn.closeDBConn();

        String redirectUrl = String.format("./userProfile.jsp?id=%d&challengetext=%s", targetId, "Challenge Sent");
        response.sendRedirect(redirectUrl);
    }
}
