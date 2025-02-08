package org.kjsim;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;

@WebServlet("/generic")
public class GenericServletDemo extends GenericServlet {

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

        //link for the HttpServletDemo
        servletResponse.getWriter().println("<a href='/GenericAndHttpServletApplication/http'>Go to HTTP Page</a>");

        out.println("<br>");
        out.println("<br>");

        //link for the ConfigServletDemo
        servletResponse.getWriter().println("<a href='/GenericAndHttpServletApplication/config'>Go to Config Page</a>");


        out.println("<br>");
        out.println("<br>");

        //link for the Home page (index.html)
        servletResponse.getWriter().println("<a href='/GenericAndHttpServletApplication'>Go to Home Page</a>");

        out.println("</body></html>");
    }
}