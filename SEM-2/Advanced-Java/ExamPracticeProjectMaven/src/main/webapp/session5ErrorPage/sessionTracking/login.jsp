<%@ page import="javax.servlet.http.HttpSession,javax.servlet.*" %>
<%@ page session="true" %>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="../style/style.css">
</head>
<body>
<div class="container">
    <h2>Login</h2>
    <%
        HttpSession session1 = (HttpSession) request.getSession(false);
        if (session1 != null && session1.getAttribute("username") != null) {
            response.sendRedirect("welcome.jsp");
            return;
        } else {
    %>
    <form action="process-login.jsp" method="post">
        <label for="username">Username:</label>
        <input type="text" id="username" name="username" required><br><br>
        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required><br><br>
        <button type="submit">Login</button>
    </form>
    <%
        }
    %>
</div>
</body>
</html>