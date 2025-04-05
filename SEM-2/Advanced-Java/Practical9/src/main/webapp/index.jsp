<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="org.kjsim.HibernateEmployee, org.kjsim.Employee, java.util.List" %>
<%@ page import="java.util.Collections" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Employee Management System</title>
    <!-- Add Bootstrap Icons CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css">
</head>
<body>
<%@ include file="header.jsp" %>

<div class="row mb-4">
    <div class="col-md-6 mx-auto">
        <form action="${pageContext.request.contextPath}/operations/viewEmployeeById.jsp" method="get" class="d-flex">
            <input type="number" name="id" class="form-control me-2" placeholder="Search by Employee ID" required>
            <button type="submit" class="btn btn-primary">
                <i class="bi bi-search"></i>
            </button>
        </form>
    </div>
</div>

<div class="card shadow">
    <div class="card-header bg-info text-dark d-flex justify-content-between align-items-center">
        <h4 class="mb-0">Employee Records</h4>
        <a href="${pageContext.request.contextPath}/operations/createEmployee.jsp" class="btn bg-primary-subtle text-primary-emphasis">
            <i class="bi bi-person-plus"></i> Add Employee
        </a>
    </div>
    <div class="card-body">
        <div class="table-responsive">
            <table class="table table-striped table-hover bdr border border-dark">
                <thead class="table-dark border border-secondary">
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Email</th>
                    <th>Hire Date</th>
                    <th>Department</th>
                    <th>Actions</th>
                </tr>
                </thead>
                <tbody>
                <%
                    List<Employee> employees = Collections.emptyList();
                    try {
                        HibernateEmployee hibernateEmployee = new HibernateEmployee();
                        employees = hibernateEmployee.getAllEmployees();
                    } catch (Exception e) {
                        e.printStackTrace();
                %>
                <tr>
                    <td colspan="6" class="text-center text-danger">
                        Error loading employees: <%= e.getMessage() %>
                    </td>
                </tr>
                <%
                    }

                    if(employees != null && !employees.isEmpty()) {
                        for(Employee emp : employees) {
                %>
                <tr>
                    <td><%= emp.getId() %></td>
                    <td><%= emp.getName() %></td>
                    <td><%= emp.getEmail() %></td>
                    <td><%= emp.getHireDate() %></td>
                    <td><%= emp.getDepartment() %></td>
                    <td>
                        <a href="${pageContext.request.contextPath}/operations/updateEmployee.jsp?id=<%= emp.getId() %>"
                           class="btn btn-sm btn-warning">
                            <i class="bi bi-pencil-square"></i> Edit
                        </a>
                        <a href="${pageContext.request.contextPath}/operations/deleteEmployee.jsp?id=<%= emp.getId() %>"
                           class="btn btn-sm btn-danger"
                           onclick="return confirm('Are you sure you want to delete this employee?')">
                            <i class="bi bi-trash"></i> Delete
                        </a>
                    </td>
                </tr>
                <%
                    }
                } else {
                %>
                <tr>
                    <td colspan="6" class="text-center">No employee records found</td>
                </tr>
                <%
                    }
                %>
                </tbody>
            </table>
        </div>
    </div>
</div>

<%@ include file="footer.jsp" %>
</body>
</html>