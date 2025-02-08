package org.kjsim;

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/jdbc-data")
public class MysqlDemo extends HttpServlet {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/adv-java";
    private static final String USER = "root";
    private static final String PASS = "";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String operation = request.getParameter("operation");
        String rollno = request.getParameter("rollno");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String department = request.getParameter("department");

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            out.println("<html><head><style>");
            out.println("body { font-family: Arial, sans-serif; background-color: #f4f4f9; margin: 0; padding: 0; }");
            out.println("h2 { color: #333; text-align: center; font-size: 2em; margin-top: 20px; }");
            out.println("div.message { text-align: center; font-size: 1.5em; padding: 20px; margin: 20px; border-radius: 5px; }");
            out.println("div.success { color: green; background-color: #d4edda; }");
            out.println("div.error { color: red; background-color: #f8d7da; }");
            out.println("table { width: 80%; margin: 30px auto; border-collapse: separate; border-spacing: 0; overflow: hidden; border-radius: 15px; background-color: white; box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); }");
            out.println("th, td { padding: 12px 20px; text-align: left; border: 1px solid #ddd; }");
            out.println("th { background-color: #007BFF; color: white; font-weight: bold; }");
            out.println("td { background-color: #f9f9f9; }");
            out.println("tr:hover td { background-color: #f1f1f1; }");
            out.println("button { font-size: 1.2em; padding: 12px 30px; background-color: #007BFF; color: white; border: none; border-radius: 5px; cursor: pointer; }");
            out.println("button:hover { background-color: #0056b3; }");
            out.println("a { text-decoration: none; }");
            out.println("</style></head><body>");

            switch (operation) {
                case "Insert":
                    if (rollno == null || rollno.isEmpty() || name == null || name.isEmpty() || email == null || email.isEmpty() || department == null || department.isEmpty()) {
                        out.println("<div class='message error'>All fields are required for Insert operation.</div>");
                    } else {
                        pstmt = conn.prepareStatement("INSERT INTO student (rollno, name, email, department) VALUES (?, ?, ?, ?)");
                        pstmt.setString(1, rollno);
                        pstmt.setString(2, name);
                        pstmt.setString(3, email);
                        pstmt.setString(4, department);
                        pstmt.executeUpdate();
                        out.println("<div class='message success'>Record inserted successfully!</div>");
                    }
                    break;

                case "Update":
                    if (rollno == null || rollno.isEmpty() || name == null || name.isEmpty() || email == null || email.isEmpty() || department == null || department.isEmpty()) {
                        out.println("<div class='message error'>All fields are required for Update operation.</div>");
                    } else {
                        pstmt = conn.prepareStatement("UPDATE student SET name=?, email=?, department=? WHERE rollno=?");
                        pstmt.setString(1, name);
                        pstmt.setString(2, email);
                        pstmt.setString(3, department);
                        pstmt.setString(4, rollno);
                        int updateCount = pstmt.executeUpdate();
                        if (updateCount > 0) {
                            out.println("<div class='message success'>Record updated successfully!</div>");
                        } else {
                            out.println("<div class='message error'>No record found with the given Roll No!</div>");
                        }
                    }
                    break;

                case "Delete":
                    if (rollno == null || rollno.isEmpty()) {
                        out.println("<div class='message error'>Roll No is required for Delete operation.</div>");
                    } else {
                        pstmt = conn.prepareStatement("DELETE FROM student WHERE rollno=?");
                        pstmt.setString(1, rollno);
                        int deleteCount = pstmt.executeUpdate();
                        if (deleteCount > 0) {
                            out.println("<div class='message success'>Record deleted successfully!</div>");
                        } else {
                            out.println("<div class='message error'>No record found with the given Roll No!</div>");
                        }
                    }
                    break;

                case "Read":
                    if (rollno == null || rollno.isEmpty()) {
                        out.println("<div class='message error'>Roll No is required for Read operation.</div>");
                    } else {
                        pstmt = conn.prepareStatement("SELECT * FROM student WHERE rollno=?");
                        pstmt.setString(1, rollno);
                        ResultSet rs = pstmt.executeQuery();
                        if (rs.next()) {
                            out.println("<h2>Student Record Found:</h2>");
                            out.println("<table>");
                            out.println("<tr><th>Roll No</th><th>Name</th><th>Email</th><th>Department</th></tr>");
                            out.println("<tr>");
                            out.println("<td>" + rs.getString("rollno") + "</td>");
                            out.println("<td>" + rs.getString("name") + "</td>");
                            out.println("<td>" + rs.getString("email") + "</td>");
                            out.println("<td>" + rs.getString("department") + "</td>");
                            out.println("</tr>");
                            out.println("</table>");
                        } else {
                            out.println("<div class='message error'>No record found with the given Roll No!</div>");
                        }
                    }
                    break;

                case "Show All Students":
                    pstmt = conn.prepareStatement("SELECT * FROM student");
                    ResultSet rsAll = pstmt.executeQuery();
                    out.println("<h2>All Students:</h2>");
                    out.println("<table>");
                    out.println("<tr><th>Roll No</th><th>Name</th><th>Email</th><th>Department</th></tr>");
                    while (rsAll.next()) {
                        out.println("<tr>");
                        out.println("<td>" + rsAll.getString("rollno") + "</td>");
                        out.println("<td>" + rsAll.getString("name") + "</td>");
                        out.println("<td>" + rsAll.getString("email") + "</td>");
                        out.println("<td>" + rsAll.getString("department") + "</td>");
                        out.println("</tr>");
                    }
                    out.println("</table>");
                    break;

                default:
                    out.println("<div class='message error'>Invalid operation!</div>");
                    break;
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            out.println("<div class='message error'>Error: " + e.getMessage() + "</div>");
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }

        // Add "Go Back to Form" button after every operation
        out.println("<div style='text-align: center; margin-top: 20px;'>");
        out.println("<a href='jdbc-form.html'><button>Go Back to Form</button></a>");
        out.println("</div>");
        out.println("</body></html>");
    }
}