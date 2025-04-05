<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="org.kjsim.HibernateEmployee" %>
<%@ page import="java.net.URLEncoder" %>
<%
    String idParam = request.getParameter("id");
    if(idParam == null || idParam.isEmpty()) {
        response.sendRedirect("../error.jsp?message=" + URLEncoder.encode("Employee ID is required", "UTF-8"));
        return;
    }

    try {
        long employeeId = Long.parseLong(idParam);
        HibernateEmployee hibernateEmployee = new HibernateEmployee();
        hibernateEmployee.deleteEmployee(employeeId);

        response.sendRedirect("../index.jsp");
    } catch(NumberFormatException e) {
        response.sendRedirect("../error.jsp?message=" + URLEncoder.encode("Invalid Employee ID format", "UTF-8"));
    } catch(Exception e) {
        String errorMessage = "Error deleting employee: " + e.getMessage();
        response.sendRedirect("../error.jsp?message=" + URLEncoder.encode(errorMessage, "UTF-8"));
    }
%>