package org.kjsim;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;

@WebServlet("/GenericServlet-Page")
public class GenericServletDemo extends GenericServlet {

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        servletResponse.getWriter().println("<html>");
        servletResponse.getWriter().println("<head>");
        servletResponse.getWriter().println("<title>Generic Servlet Demo</title>");
        servletResponse.getWriter().println("</head>");
        servletResponse.getWriter().println("<body>");
        servletResponse.getWriter().println("<h1>This is a simple Generic Servlet Demo</h1>");
        servletResponse.getWriter().println("<p>This servlet extends the GenericServlet class and overrides the service method.</p>");
        servletResponse.getWriter().println("<p>It demonstrates how to handle client requests and send responses using the Servlet API.</p>");
        servletResponse.getWriter().println("</body>");
        servletResponse.getWriter().println("</html>");

    }
}