package org.kjsim.session2GenericAndHttpServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

@WebServlet("/session2GenericAndHttpServlet/httpservlet")
public class HttpServletDemo extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        PrintWriter out = res.getWriter();

        out.println("<html>");
        out.println("<head>");
        out.println("<title>HTTP Servlet Demo</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>This is a simple HTTP Servlet Demo</h1>");
        out.println("<p>This servlet extends the HttpServlet class and overrides the service method.</p>");
        out.println("<p>It demonstrates how to handle HTTP-specific requests (GET, POST, etc.) and send responses.</p>");
        out.println("<p>Request Method: " + req.getMethod() + "</p>");
        out.println("</body>");
        out.println("</html>");

        Enumeration<String> headers = req.getHeaderNames();
        while (headers.hasMoreElements()) {
            String headerName = headers.nextElement();
            String headerValue = req.getHeader(headerName);
            out.println("<b>"+headerName+"</b>");
            out.println(headerValue+"<br>");
        }

        res.setContentType("text/html");

        out.println("<html><body>");
        out.println("<h1>Welcome to the Second HTTP Servlet!</h1>");
        String contextPath = req.getContextPath();
        res.getWriter().println("<a href='" + contextPath + "/session2GenericAndHttpServlet/genericservlet'>Go back to Generic Servlet</a>");
        out.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doGet(req, res);
    }
}