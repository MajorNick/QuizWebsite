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
import javax.servlet.annotation.WebServlet;

@WebServlet("/Note")
public class Note extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Send Note Servlet");
        response.setContentType("text/html");

        String note_text = request.getParameter("note_text");
        int targetId = Integer.parseInt(request.getParameter("userId"));
        int userId = Integer.parseInt(request.getParameter("targetId"));

        System.out.println(note_text);
        System.out.println(userId);
        System.out.println(targetId);

        DBConn dbConn = new DBConn();

        Notification notification = new Notification(-1, targetId, userId, "note", note_text);

        dbConn.insertNotification(notification);

        dbConn.closeDBConn();
    }
}
