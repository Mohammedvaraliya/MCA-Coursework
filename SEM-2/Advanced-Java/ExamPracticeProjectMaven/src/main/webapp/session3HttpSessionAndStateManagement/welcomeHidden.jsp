<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="javax.servlet.http.Cookie" %>
<%
    // Get username from hidden field (POST data)
    String hiddenUsername = request.getParameter("username");

    // Use existing session object (provided by JSP)
    String sessionUsername = null;
    if (session != null) {
        sessionUsername = (String) session.getAttribute("username");
    }

    // Retrieve cookie username
    String cookieUsername = null;
    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
        for (Cookie cookie : cookies) {
            if ("username".equals(cookie.getName())) {
                cookieUsername = cookie.getValue();
                break;
            }
        }
    }

    // If no session, redirect
    if (sessionUsername == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Welcome Page (Hidden Field)</title>
    <link rel="stylesheet" href="style/style.css">
</head>
<body>
<h1>Welcome to the Welcome Page!</h1>
<p><b>From Session:</b> <%= sessionUsername %></p>
<p><b>From Cookie:</b> <%= (cookieUsername != null ? cookieUsername : "No cookie found") %></p>
<p><b>From Hidden Field:</b> <%= (hiddenUsername != null ? hiddenUsername : "No username from Hidden Field") %></p>

<form action="welcomeHidden.jsp" method="POST">
    <input type="hidden" name="username" value="<%= hiddenUsername %>">
    <input type="submit" value="Refresh with Hidden Field" class="button">
</form>

<br>
<a href="index.jsp" class="button">Go to Landing Page</a><br><br>
<a href="${pageContext.request.contextPath}/session3HttpSessionAndStateManagement/logout" class="button">Logout</a>
</body>
</html>
