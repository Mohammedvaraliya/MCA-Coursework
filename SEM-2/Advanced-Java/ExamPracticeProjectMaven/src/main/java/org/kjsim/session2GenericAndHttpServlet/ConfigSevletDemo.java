package org.kjsim.session2GenericAndHttpServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(
        urlPatterns = {"/session2GenericAndHttpServlet/config"},
        initParams = {
                @WebInitParam(name = "appName", value = "My Java Servlet Application")
        }
)
public class ConfigSevletDemo extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String appName = getServletConfig().getInitParameter("appName");

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<html><body>");
        out.println("<h2>Application Name:</h2>");
        out.println("<p>" + appName + "</p>");
        String contextPath = getServletConfig().getServletContext().getContextPath();
        response.getWriter().println("<a href='"+ contextPath +"/session2GenericAndHttpServlet/genericservlet'>Go back to Generic Servlet</a>");
        out.println("</body></html>");
    }
}
