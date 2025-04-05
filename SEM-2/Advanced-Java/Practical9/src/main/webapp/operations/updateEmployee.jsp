<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="org.kjsim.HibernateEmployee, org.kjsim.Employee" %>
<%@ page import="java.net.URLEncoder" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Update Employee</title>
</head>
<body>
<%@ include file="../header.jsp" %>

<%
    String idParam = request.getParameter("id");
    if(idParam == null || idParam.isEmpty()) {
        response.sendRedirect("../error.jsp?message=" + URLEncoder.encode("Employee ID is required", "UTF-8"));
        return;
    }

    long employeeId;
    try {
        employeeId = Long.parseLong(idParam);
    } catch (NumberFormatException e) {
        response.sendRedirect("../error.jsp?message=" + URLEncoder.encode("Invalid Employee ID format", "UTF-8"));
        return;
    }

    HibernateEmployee hibernateEmployee = new HibernateEmployee();
    Employee employee = hibernateEmployee.getEmployeeById(employeeId);

    if(employee == null) {
        response.sendRedirect("../error.jsp?message=" +
                URLEncoder.encode("Employee not found with ID: " + employeeId, "UTF-8"));
        return;
    }
%>

<div class="card shadow mx-auto" style="max-width: 600px;">
    <div class="card-header bg-warning-subtle text-warning-emphasis">
        <h4 class="mb-0">Update Employee</h4>
    </div>
    <div class="card-body">
        <form action="updateEmployee.jsp" method="post">
            <input type="hidden" name="id" value="<%= employee.getId() %>">
            <div class="mb-3">
                <label for="name" class="form-label">Name</label>
                <input type="text" class="form-control" id="name" name="name"
                       value="<%= employee.getName() %>" required>
            </div>
            <div class="mb-3">
                <label for="email" class="form-label">Email</label>
                <input type="email" class="form-control" id="email" name="email"
                       value="<%= employee.getEmail() %>" required>
            </div>
            <div class="mb-3">
                <label for="hireDate" class="form-label">Hire Date</label>
                <input type="date" class="form-control" id="hireDate" name="hireDate"
                       value="<%= employee.getHireDate() %>" required>
            </div>
            <div class="mb-3">
                <label for="department" class="form-label">Department</label>
                <input type="text" class="form-control" id="department" name="department"
                       value="<%= employee.getDepartment() %>" required>
            </div>
            <div class="d-grid gap-2 d-md-flex justify-content-md-end">
                <a href="../index.jsp" class="btn btn-secondary me-md-2">Cancel</a>
                <button type="submit" class="btn btn-warning">Update</button>
            </div>
        </form>

        <%
            if("POST".equalsIgnoreCase(request.getMethod())) {
                try {
                    long empId = Long.parseLong(request.getParameter("id"));
                    String name = request.getParameter("name");
                    String email = request.getParameter("email");
                    String hireDate = request.getParameter("hireDate");
                    String department = request.getParameter("department");

                    hibernateEmployee.updateEmployee(empId, name, email, hireDate, department);

                    response.sendRedirect("../index.jsp");
                } catch(Exception e) {
                    String errorMessage = "Error updating employee: " + e.getMessage();
                    response.sendRedirect("../error.jsp?message=" + URLEncoder.encode(errorMessage, "UTF-8"));
                }
            }
        %>
    </div>
</div>

<%@ include file="../footer.jsp" %>
</body>
</html>