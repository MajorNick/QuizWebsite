package Quiz.src.main.java.servlets;

import Quiz.src.main.java.models.DBConn;
import Quiz.src.main.java.models.Notification;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;

@WebServlet("/AddFriend")
public class AddFriend extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Add Friend Servlet");
        response.setContentType("text/html");
        String UserId = request.getParameter("userId");
        String TargetId = request.getParameter("targetId");

        int targetId = Integer.parseInt(TargetId);
        int userId = Integer.parseInt(UserId);

        System.out.println(userId);
        System.out.println(targetId);

        DBConn dbConn = new DBConn();

        // Friend friend = new Friend(-1, userId, targetId);

        // dbConn.insertNotification(friend);

        dbConn.closeDBConn();
    }
}
