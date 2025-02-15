package org.kjsim;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if ("admin".equals(username) && "1234".equals(password)) {
            // Valid credentials, create session
            HttpSession session = request.getSession();
            session.setAttribute("username", username);

            // Set cookie with session ID
            Cookie sessionCookie = new Cookie("username", username);
            sessionCookie.setMaxAge(5000);
            response.addCookie(sessionCookie);

            response.sendRedirect("welcome?username=" + username);
        } else {
            response.setContentType("text/html");
            response.getWriter().write("<html><head><link rel='stylesheet' href='css/style.css'></head><body>");
            response.getWriter().write("<div class='center'><h2>Invalid credentials. Please try again.</h2>");
            response.getWriter().write("<a href='login.html' class='button'>Go Back to Login</a></div>");
            response.getWriter().write("</body></html>");
        }
    }
}