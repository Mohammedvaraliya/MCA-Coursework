<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
    java.util.List<String> courses = new java.util.ArrayList<>();
    courses.add("Java");
    courses.add("Python");
    courses.add("AWS");
    courses.add("AI");
    request.setAttribute("courses", courses);
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>JSP Custom Core Tag</title>
    <link rel="stylesheet" href="../style/style.css">
</head>
<body>
<div class="container">
    <h1>Core Tags</h1>

    <div class="buttons">
        <ul>
            <c:forEach var="name" items="${courses}">
                <li><c:out value="${name}"/></li>
            </c:forEach>
        </ul>

        <c:if test="${courses.size() > 2}">
            <p>There are more than 2 courses available.</p>
        </c:if>
    </div>
</div>
</body>
</html>