package Quiz.src.main.java.servlets;

import Quiz.src.main.java.models.DBConn;
import Quiz.src.main.java.models.Notification;
import Quiz.src.main.java.models.models.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.annotation.WebServlet;

@WebServlet("/Note")
public class Note extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Send Note Servlet");
        response.setContentType("text/html");

        String note_text = request.getParameter("note_text");
        String userId_req = request.getParameter("userId");
        String targetId_req = request.getParameter("targetId");

        if(userId_req == null){
            String redirectUrl = String.format("./userProfile.jsp?notephtext=Could not get your ID");
            response.sendRedirect(redirectUrl);
            return;
        }
        if(targetId_req == null){
            String redirectUrl = String.format("./userProfile.jsp?notephtext=Could not get target ID");
            response.sendRedirect(redirectUrl);
            return;
        }
        int userId = Integer.parseInt(userId_req);
        int targetId = Integer.parseInt(targetId_req);

        if(note_text == "" || note_text == null){
            String redirectUrl = String.format("./userProfile.jsp?id=%d&notephtext=%s", targetId, "Note Can not be empty");
            response.sendRedirect(redirectUrl);
            return;
        }

        System.out.println(note_text);
        System.out.println(userId);
        System.out.println(targetId);

        DBConn dbConn = new DBConn();

        Notification notification = new Notification(-1, targetId, userId, "note", note_text);
        dbConn.insertNotification(notification);

        dbConn.closeDBConn();

        String redirectUrl = String.format("./userProfile.jsp?id=%d&notephtext=Note sent", targetId);
        response.sendRedirect(redirectUrl);
    }
}
