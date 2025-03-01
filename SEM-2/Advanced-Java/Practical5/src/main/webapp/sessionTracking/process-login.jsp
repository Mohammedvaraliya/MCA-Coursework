<%@ page import="javax.servlet.http.*,javax.servlet.*" %>
<%@ page session="true" %>
<html>
<head>
    <title>Processing Login</title>
    <link rel="stylesheet" href="../style/style.css">
</head>
<body>
<div class="container">
    <h2>Processing Login</h2>
    <%
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if ("admin".equals(username) && "admin".equals(password)) {
            HttpSession session1 = request.getSession(true);
            session1.setAttribute("username", username);
            session1.setMaxInactiveInterval(60*60);

            Cookie sessionCookie = new Cookie("JSESSIONID", session1.getId());
            sessionCookie.setMaxAge(60*60);
            response.addCookie(sessionCookie);

    %>
        <p>Login Successful! Welcome <strong><%= username %></strong></p>
        <a href="welcome.jsp">Go to Welcome Page</a>
    <%
    } else {
    %>
        <div style="background-color: #FF6969; padding: 20px; border-radius: 8px; text-align: center;">
            <p style="color: white;">Invalid Username or Password. Please try again.</p>
            <a href="login.jsp" style="color: white; font-weight: bold;">Go back to login</a>
        </div>
    <%
        }
    %>
</div>
</body>
</html>
