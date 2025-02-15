package org.kjsim;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/welcome")
public class WelcomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        String sessionUsername = null;
        if (session != null) {
            sessionUsername = (String) session.getAttribute("username");
        }

        String cookieUsername = null;
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("username".equals(cookie.getName())) {
                    cookieUsername = cookie.getValue();
                    break;
                }
            }
        }

        String urlUsername = req.getParameter("username");

        if (sessionUsername == null) {
            resp.sendRedirect("login.html");
        } else {
            resp.setContentType("text/html");
            PrintWriter out = resp.getWriter();
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Welcome Page</title>");
            out.println("<link rel='stylesheet' href='css/style.css'>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Welcome to the Welcome Page!</h1>");
            out.println("<p><b>From Session:</b> " + sessionUsername + "</p>");
            out.println("<p><b>From Cookie:</b> " + (cookieUsername != null ? cookieUsername : "No cookie found") + "</p>");
            out.println("<p><b>From URL:</b> " + (urlUsername != null ? urlUsername : "No username from URL") + "</p>");
            out.println("<a href='index.html' class='button'>Go to Landing Page</a><br><br>");
            out.println("<a href='logout' class='button'>Logout</a>");
            out.println("</body>");
            out.println("</html>");
        }
    }
}