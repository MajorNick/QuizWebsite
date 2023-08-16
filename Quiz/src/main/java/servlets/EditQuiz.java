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

@WebServlet("/EditQuiz")
public class EditQuiz extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Edit Quiz Servlet");
        response.setContentType("text/html");

        String referringURL = request.getHeader("referer");

        String userId_req = request.getParameter("userId");
        String quizId_req = request.getParameter("quizId");

        if(userId_req == null){
            response.sendRedirect(referringURL);
            System.out.println("userId null");
            return;
        }
        if(quizId_req == null){
            response.sendRedirect(referringURL);
            System.out.println("quizId null");
            return;
        }

        int userId = Integer.parseInt(userId_req);
        int quizId = Integer.parseInt(quizId_req);

        response.getWriter().write("amogus");
    }
}
