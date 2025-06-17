<%@ page import="org.kjsim.session4DirectivesObjectsScriptingActionsBeans.StudentBean" %>
<jsp:useBean id="student" class="org.kjsim.session4DirectivesObjectsScriptingActionsBeans.StudentBean" scope="request" />
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="../../style/style.css">
    <title>Register Page</title>
</head>
<body>
<%@ include file="../../header.jsp" %>
<div class="container">
    <main>
        <h2>Register a New Student</h2>
        <% if (request.getAttribute("message") != null) { %>
        <div class="message error"><%= request.getAttribute("message") %></div>
        <% } %>
        <form action="<%= request.getContextPath() %>/session4DirectivesObjectsScriptingActionsBeans/register" method="post">
            <label for="rollno">Roll Number:</label>
            <input type="text" id="rollno" name="rollno" value="<%= student.getRollno() != null ? student.getRollno() : "" %>" required>
            <br>

            <label for="name">Name:</label>
            <input type="text" id="name" name="name" value="<%= student.getName() != null ? student.getName() : "" %>" required>
            <br>

            <label for="email">Email:</label>
            <input type="email" id="email" name="email" value="<%= student.getEmail() != null ? student.getEmail() : "" %>" required>
            <br>

            <label for="department">Department:</label>
            <input type="text" id="department" name="department" value="<%= student.getDepartment() != null ? student.getDepartment() : "" %>" required>
            <br>

            <button type="submit">Register</button>
        </form>
    </main>
</div>
<%@ include file="../../footer.jsp" %>

</body>
</html>