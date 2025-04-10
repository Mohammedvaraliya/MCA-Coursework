<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="org.kjsim.HibernateEmployee, org.kjsim.Employee" %>
<%@ page import="java.net.URLEncoder" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>View Employee</title>
    <link href="${pageContext.request.contextPath}/styles/style.css" rel="stylesheet">
</head>
<body>
<%@ include file="../header.jsp" %>

<%
    String idParam = request.getParameter("id");
    if(idParam == null || idParam.isEmpty()) {
        response.sendRedirect("../error.jsp?message=" + URLEncoder.encode("BlogPost ID is required", "UTF-8"));
        return;
    }

    long employeeId;
    try {
        employeeId = Long.parseLong(idParam);
    } catch (NumberFormatException e) {
        response.sendRedirect("../error.jsp?message=" + URLEncoder.encode("Invalid BlogPost ID format", "UTF-8"));
        return;
    }

    HibernateEmployee hibernateEmployee = new HibernateEmployee();
    Employee blogPost = null;
    try {
        blogPost = hibernateEmployee.getEmployeeById(employeeId);
    } catch (Exception e) {
        response.sendRedirect("../error.jsp?message=" + URLEncoder.encode("Error fetching blogPost: " + e.getMessage(), "UTF-8"));
        return;
    }

    if(blogPost == null) {
        response.sendRedirect("../error.jsp?message=" + URLEncoder.encode("BlogPost not found with ID: " + employeeId, "UTF-8"));
        return;
    }
%>

<div class="card shadow mx-auto" style="max-width: 600px;">
    <div class="card-header bg-info-subtle text-info-emphasis">
        <h4 class="mb-0">Employee Details</h4>
    </div>
    <div class="card-body">
        <div class="row mb-3">
            <div class="col-sm-4 fw-bold">Employee ID:</div>
            <div class="col-sm-8"><%= blogPost.getId() %></div>
        </div>
        <div class="row mb-3">
            <div class="col-sm-4 fw-bold">Name:</div>
            <div class="col-sm-8"><%= blogPost.getName() %></div>
        </div>
        <div class="row mb-3">
            <div class="col-sm-4 fw-bold">Email:</div>
            <div class="col-sm-8"><%= blogPost.getEmail() %></div>
        </div>
        <div class="row mb-3">
            <div class="col-sm-4 fw-bold">Hire Date:</div>
            <div class="col-sm-8"><%= blogPost.getHireDate() %></div>
        </div>
        <div class="row mb-3">
            <div class="col-sm-4 fw-bold">Department:</div>
            <div class="col-sm-8"><%= blogPost.getDepartment() %></div>
        </div>
        <div class="d-grid gap-2 d-md-flex justify-content-md-end">
            <a href="../index.jsp" class="btn btn-primary">Back to List</a>
        </div>
    </div>
</div>

<%@ include file="../footer.jsp" %>
</body>
</html>