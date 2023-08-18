package Quiz.src.main.java.servlets;


import Quiz.src.main.java.models.DBConn;
import Quiz.src.main.java.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;
import static Quiz.src.main.java.HelperMethods.PassHasher.hashPassword;


@WebServlet(name = "tester", value = "/tester")
public class testeeer extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
        System.out.println("mze");
    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException, ServletException, IOException {
        System.out.println("mze");
    }

}
