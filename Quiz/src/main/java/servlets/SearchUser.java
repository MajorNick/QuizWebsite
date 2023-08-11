package Quiz.src.main.java.servlets;

import Quiz.src.main.java.models.DBConn;
import Quiz.src.main.java.models.models.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.annotation.WebServlet;

@WebServlet("/SearchUser")
public class SearchUser extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Look Up User Servlet");
        response.setContentType("text/html");

        String search_text = request.getParameter("search_text");

        System.out.println(search_text);

        DBConn dbConn = new DBConn();

//        ArrayList<User> users = dbConn.getUser("-1", search_text);

        dbConn.closeDBConn();

//        User user = users.get(0);
//        int userId = user.getId();
        int userId = 2;
        String redirectUrl = String.format("./userProfile.jsp?id=%d", userId);

        response.sendRedirect(redirectUrl);
    }
}
