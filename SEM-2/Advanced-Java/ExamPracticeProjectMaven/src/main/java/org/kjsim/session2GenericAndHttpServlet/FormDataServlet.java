package org.kjsim.session2GenericAndHttpServlet;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/session2GenericAndHttpServlet/form-data")
public class FormDataServlet extends GenericServlet {

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        servletResponse.setContentType("text/html");
        PrintWriter out = servletResponse.getWriter();

        String name = servletRequest.getParameter("name");
        String rollno = servletRequest.getParameter("rollno");

        out.println("<html><body>");
        out.println("<h2>Received Data:</h2>");
        out.println("<p>Name: " + name + "</p>");
        out.println("<p>Roll No: " + rollno + "</p>");

        out.println("<br>");

        servletResponse.getWriter().println("<h2>Servlet Details:</h2>");
        servletResponse.getWriter().println("<p><strong>Protocol:</strong> " + servletRequest.getProtocol() + "</p>");
        servletResponse.getWriter().println("<p><strong>Scheme:</strong> " + servletRequest.getScheme() + "</p>");
        servletResponse.getWriter().println("<p><strong>Encoding:</strong> " + servletRequest.getCharacterEncoding() + "</p>");
        servletResponse.getWriter().println("<p><strong>Content Type:</strong> " + servletResponse.getContentType() + "</p>");
        servletResponse.getWriter().println("<p><strong>Server Name:</strong> " + servletRequest.getServerName() + "</p>");
        servletResponse.getWriter().println("<p><strong>Server Port:</strong> " + servletRequest.getServerPort() + "</p>");

        String contextPath = servletRequest.getServletContext().getContextPath();

        //link for the HttpServletDemo
        servletResponse.getWriter().println("<a href='" + contextPath + "/session2GenericAndHttpServlet/httpservlet' class='session-link'>View HttpServlet Page</a>");
        out.println("<br>");
        out.println("<br>");

        //link for the ConfigServletDemo
        servletResponse.getWriter().println("<a href='" + contextPath + "/session2GenericAndHttpServlet/config'>Go to Config Page</a>");


        out.println("<br>");
        out.println("<br>");

        //link for the Home page (index.html)
        servletResponse.getWriter().println("<a href='" + contextPath + "/session2GenericAndHttpServlet'>Go to Home Page</a>");

        out.println("</body></html>");
    }
}
