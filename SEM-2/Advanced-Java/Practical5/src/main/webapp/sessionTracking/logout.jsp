<%@ page import="javax.servlet.http.*,javax.servlet.*" %>
<%@ page session="true" %>
<html>
<head>
    <title>Logout</title>
    <link rel="stylesheet" href="../style/style.css">
</head>
<body>
<div class="container">
    <h2>Logging Out</h2>
    <%
        HttpSession session1 = request.getSession(false);
        if (session1 != null) {
            session1.invalidate();
            Cookie cookie = new Cookie("JSESSIONID", "");
            cookie.setMaxAge(0);
            response.addCookie(cookie);
            out.println("<p>You have successfully logged out.</p>");
        }
        out.println("<a href='login.jsp'>Go to Login Page</a>");
    %>
</div>
</body>
</html>