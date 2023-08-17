package Quiz.src.main.java.servlets;


import Quiz.src.main.java.models.DBConn;
import Quiz.src.main.java.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;
import static Quiz.src.main.java.HelperMethods.PassHasher.hashPassword;


@WebServlet(name = "Task1Servlet", value = "/Task1Servlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {

    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException, ServletException, IOException {
        DBConn acc = (DBConn) getServletContext().getAttribute("manacc");

        String name = httpServletRequest.getParameter("username");
        ArrayList<User> user = acc.getUsersByUsername(name);


        if (!user.isEmpty()) {
            String password = httpServletRequest.getParameter("password");
            password = hashPassword(password);
            if (user.get(0).getPasswordHash().equals(password)) {
                HttpSession session = httpServletRequest.getSession();

                session.setAttribute("loggedIn", true);
                session.setAttribute("user", user.get(0));
                session.setAttribute("username", user.get(0).getUsername());

                Cookie sessionCookie = new Cookie("username", name);
                sessionCookie.setMaxAge(Integer.MAX_VALUE);
                httpServletResponse.addCookie(sessionCookie);

//                httpServletRequest.getRequestDispatcher("userProfile/index.jsp").forward(httpServletRequest, httpServletResponse);
                httpServletResponse.sendRedirect("home.jsp");
            } else {

                httpServletRequest.getRequestDispatcher("failedlogg.jsp").forward(httpServletRequest, httpServletResponse);
            }
        } else {
            httpServletRequest.getRequestDispatcher("failedlogg.jsp").forward(httpServletRequest, httpServletResponse);
        }
    }
}
