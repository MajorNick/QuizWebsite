package Quiz.src.main.java.servlets;

import Quiz.src.main.java.models.DBConn;
import Quiz.src.main.java.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.time;

@WebServlet("/BanUser")
public class BanAccount extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Ban User");

        String UserId = request.getParameter("userId");
        String TargetId = request.getParameter("targetId");

        int targetId = Integer.parseInt(TargetId);
        int userId = Integer.parseInt(UserId);

        DBConn dbConn = new DBConn();
        User u = dbConn.getUsers(userId).get(0);
        u.setEventTime(time());
        dbConn.closeDBConn();

        response.sendRedirect("bannedUser.jsp");
    }
}
