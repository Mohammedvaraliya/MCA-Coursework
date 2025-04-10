<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="org.kjsim.HibernateEmployee, org.kjsim.Employee" %>
<%@ page import="java.net.URLEncoder" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add New Employee</title>
    <link href="${pageContext.request.contextPath}/styles/style.css" rel="stylesheet">
</head>
<body>
<%@ include file="../header.jsp" %>

<div class="card shadow mx-auto" style="max-width: 600px;">
    <div class="card-header bg-success-subtle text-success-emphasis">
        <h4 class="mb-0">Add New Employee</h4>
    </div>
    <div class="card-body">
        <form action="createEmployee.jsp" method="post">
            <div class="mb-3">
                <label for="id" class="form-label">Employee ID</label>
                <input type="number" class="form-control" id="id" name="id" required>
            </div>
            <div class="mb-3">
                <label for="name" class="form-label">Name</label>
                <input type="text" class="form-control" id="name" name="name" required>
            </div>
            <div class="mb-3">
                <label for="email" class="form-label">Email</label>
                <input type="email" class="form-control" id="email" name="email" required>
            </div>
            <div class="mb-3">
                <label for="hireDate" class="form-label">Hire Date</label>
                <input type="date" class="form-control" id="hireDate" name="hireDate" required>
            </div>
            <div class="mb-3">
                <label for="department" class="form-label">Department</label>
                <input type="text" class="form-control" id="department" name="department" required>
            </div>
            <div class="d-grid gap-2">
                <button type="submit" class="btn btn-success">Add Employee</button>
            </div>
        </form>

        <%
            if("POST".equalsIgnoreCase(request.getMethod())) {
                try {
                    Employee blogPost = new Employee();
                    blogPost.setId(Long.parseLong(request.getParameter("id")));
                    blogPost.setName(request.getParameter("name"));
                    blogPost.setEmail(request.getParameter("email"));
                    blogPost.setHireDate(request.getParameter("hireDate"));
                    blogPost.setDepartment(request.getParameter("department"));

                    HibernateEmployee hibernateEmployee = new HibernateEmployee();
                    hibernateEmployee.createEmployee(blogPost);

                    response.sendRedirect("../index.jsp");
                } catch(Exception e) {
                    String errorMessage = "Error creating blogPost: " + e.getMessage();
                    String encodedErrorMessage = URLEncoder.encode(errorMessage, "UTF-8");
                    response.sendRedirect("../error.jsp?message=" + encodedErrorMessage);
                }
            }
        %>
    </div>
</div>

<%@ include file="../footer.jsp" %>
</body>
</html>