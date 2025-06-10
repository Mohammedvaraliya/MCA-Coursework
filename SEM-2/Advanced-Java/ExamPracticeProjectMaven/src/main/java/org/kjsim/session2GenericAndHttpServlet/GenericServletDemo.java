package org.kjsim.session2GenericAndHttpServlet;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/session2GenericAndHttpServlet/genericservlet")
public class GenericServletDemo extends GenericServlet {
    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        PrintWriter out = servletResponse.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Generic Servlet Demo</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>This is a simple Generic Servlet Demo</h1>");
        out.println("<p>This servlet extends the GenericServlet class and overrides the service method.</p>");
        out.println("<p>It demonstrates how to handle client requests and send responses using the Servlet API.</p>");
        out.println("</body>");
        out.println("</html>");
    }
}