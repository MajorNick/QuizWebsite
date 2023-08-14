package Quiz.src.main.java.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "MainPageServlet", value = "/MainPageServlet")
public class StartMainServlet extends HttpServlet {
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {

    }

    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        HttpSession session = httpServletRequest.getSession(false);
        System.out.println("movedi");

        if (session == null || session.getAttribute("user") == null) {
            httpServletRequest.getRequestDispatcher("/loginPage.jsp").forward(httpServletRequest, httpServletResponse);
            return;
        }

        httpServletRequest.getRequestDispatcher("/userProfile.jsp").forward(httpServletRequest, httpServletResponse);
    }
}
