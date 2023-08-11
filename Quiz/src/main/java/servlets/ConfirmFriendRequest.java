package Quiz.src.main.java.servlets;

import Quiz.src.main.java.models.DBConn;
import Quiz.src.main.java.models.Notification;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;

@WebServlet("/ConfirmFriendRequest")
public class ConfirmFriendRequest extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Confirm Friend Request Servlet");
        response.setContentType("text/html");

        String UserId = request.getParameter("userId");
        String TargetId = request.getParameter("targetId");

        int targetId = Integer.parseInt(TargetId);
        int userId = Integer.parseInt(UserId);

        DBConn dbConn = new DBConn();

//        Friend friend1 = new Friend(targetId, userId);
//        Friend friend2 = new Friend(userId, targetId);
//        dbConn.insertFriend(friend1);
//        dbConn.insertFriend(friend2);

        dbConn.closeDBConn();

    }
}
