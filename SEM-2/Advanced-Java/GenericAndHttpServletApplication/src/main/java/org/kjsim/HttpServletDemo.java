package org.kjsim;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/HttpServlet-Page")
public class HttpServletDemo extends HttpServlet {

    @Override
    public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");

        resp.getWriter().println("<html>");
        resp.getWriter().println("<head>");
        resp.getWriter().println("<title>HTTP Servlet Demo</title>");
        resp.getWriter().println("</head>");
        resp.getWriter().println("<body>");
        resp.getWriter().println("<h1>This is a simple HTTP Servlet Demo</h1>");
        resp.getWriter().println("<p>This servlet extends the HttpServlet class and overrides the service method.</p>");
        resp.getWriter().println("<p>It demonstrates how to handle HTTP-specific requests (GET, POST, etc.) and send responses.</p>");
        resp.getWriter().println("<p>Request Method: " + req.getMethod() + "</p>");
        resp.getWriter().println("</body>");
        resp.getWriter().println("</html>");
    }
}
