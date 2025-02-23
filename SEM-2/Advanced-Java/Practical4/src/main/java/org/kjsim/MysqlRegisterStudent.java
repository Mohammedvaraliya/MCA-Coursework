package org.kjsim;

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/register")
public class MysqlRegisterStudent extends HttpServlet {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/adv-java";
    private static final String USER = "root";
    private static final String PASS = "";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        StudentBean student = new StudentBean();
        student.setRollno(request.getParameter("rollno"));
        student.setName(request.getParameter("name"));
        student.setEmail(request.getParameter("email"));
        student.setDepartment(request.getParameter("department"));

        if (student.getRollno().isEmpty() || student.getName().isEmpty() || student.getEmail().isEmpty() || student.getDepartment().isEmpty()) {
            request.setAttribute("message", "All fields are required!");
            request.getRequestDispatcher("/module/actions/register.jsp").forward(request, response);
            return;
        }

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                 PreparedStatement pstmt = conn.prepareStatement("INSERT INTO student (rollno, name, email, department) VALUES (?, ?, ?, ?)")) {

                pstmt.setString(1, student.getRollno());
                pstmt.setString(2, student.getName());
                pstmt.setString(3, student.getEmail());
                pstmt.setString(4, student.getDepartment());

                pstmt.executeUpdate();

                request.setAttribute("student", student);
                request.getRequestDispatcher("/module/actions/registrationSuccess.jsp").forward(request, response);

            }

        } catch (ClassNotFoundException e) {
            request.setAttribute("message", "Error: MySQL JDBC Driver not found!");
            request.getRequestDispatcher("/module/actions/register.jsp").forward(request, response);
        } catch (SQLException e) {
            request.setAttribute("message", "Database error: " + e.getMessage());
            request.getRequestDispatcher("/module/actions/register.jsp").forward(request, response);
        }
    }
}