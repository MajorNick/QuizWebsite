package Quiz.src.main.java.servlets;

import Quiz.src.main.java.models.DBConn;
import Quiz.src.main.java.models.Notification;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;

@WebServlet("/Challenge")
public class Challenge extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Send Challenge Servlet");
        response.setContentType("text/html");

        String note_text = request.getParameter("note_text");
        String UserId = request.getParameter("userId");
        String TargetId = request.getParameter("targetId");

        int targetId = Integer.parseInt(TargetId);
        int userId = Integer.parseInt(UserId);

        System.out.println(note_text);
        System.out.println(userId);
        System.out.println(targetId);

        DBConn dbConn = new DBConn();

        String challengeText = String.format("User %s has Challenged you to Quiz: %s", userId, note_text);
        Notification notification = new Notification(-1, targetId, userId, "challenge", challengeText);

        dbConn.insertNotification(notification);

        dbConn.closeDBConn();
    }
}
