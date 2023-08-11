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

        ArrayList<Notification> notifications = dbConn.getNotifications(targetId, userId, "friend request");

        if(notifications.size() > 0){
            dbConn.closeDBConn();
            String redirectUrl = String.format("./userProfile.jsp?id=%d&addfriendtext=%s", targetId, "Request Already Sent");
            response.sendRedirect(redirectUrl);

            return;
        }

//        ArrayList<User> users = dbConn.getUser("userId", "");
//        User user = users.get(0);
//        String userName = user.getUsername();
        String userName = "Jonathan smith";
        String confirmLink = "    <form action=\"./ConfirmFriendRequest\" method=\"post\">\n" +
                "      <button class=\"action-button\">Confirm</button>\n" +
                "      <input type=\"hidden\" name=\"userId\" value=\"" + userId + "\">\n" +
                "      <input type=\"hidden\" name=\"targetId\" value=\"" + targetId + "\">\n" +
                "    </form>";
//        String confirmLink = "<a href=\"./home.jsp\">Confirm</a>";

        Notification notification2 = new Notification(-1, targetId, userId, "friend request", String.format("%s has sent you a friend request! click here to confirm: %s", userName, confirmLink));
        dbConn.insertNotification(notification2);

        dbConn.closeDBConn();

        String redirectUrl = String.format("./userProfile.jsp?id=%d&addfriendtext=%s", targetId, "Request Sent");
        response.sendRedirect(redirectUrl);
    }
}
