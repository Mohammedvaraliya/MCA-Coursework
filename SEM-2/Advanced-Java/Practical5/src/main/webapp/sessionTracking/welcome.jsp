<%@ page import="javax.servlet.http.*,javax.servlet.*" %>
<%@ page session="true" %>
<html>
<head>
    <title>Welcome</title>
    <link rel="stylesheet" href="../style/style.css">
</head>
<body>
<div class="container">
    <h2>Welcome to the Protected Page</h2>
    <%
        HttpSession session1 = request.getSession(false);
        if (session1 != null && session1.getAttribute("username") != null) {
            String username = (String) session1.getAttribute("username");
            out.println("<p>Session ID: " + session1.getId() + "!</p>");
            out.println("<p>Session Creation Time: " + session1.getCreationTime() + "!</p>");
            out.println("<p>Session Last Accessed: " + session1.getLastAccessedTime() + "!</p>");
            out.println("<p>Session Max Inactive Interval: " + session1.getMaxInactiveInterval() + "!</p><br><br>");
            out.println("<strong><p>Hello, " + username + "!</p></strong>");
            out.println("<a href=\"logout.jsp\"><button style=\"background-color: red;color: white;width: 10%;border-radius: 10px;background-color: #ff0000;color: white;border: none;padding: 10px 20px;font-size: 16px;margin-top: 10px;cursor: pointer;width: 10%;border-radius: 10px;box-shadow: 0 8px 16px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);\">Logout</button></a>");
        } else {
            out.println("<p>You are not logged in. <a href='login.jsp'>Login</a> first.</p>");
        }
    %>
</div>
</body>
</html>