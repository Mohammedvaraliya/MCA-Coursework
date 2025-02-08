package org.kjsim;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

@WebServlet("/http")
public class HttpServletDemo extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        PrintWriter out = res.getWriter();
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
        res.getWriter().println("<a href='/GenericAndHttpServletApplication/generic'>Go back to Generic Servlet</a>");
        out.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doGet(req, res);
    }
}