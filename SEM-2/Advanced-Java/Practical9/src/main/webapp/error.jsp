<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Error</title>
    <link href="${pageContext.request.contextPath}/styles/style.css" rel="stylesheet">
</head>
<body>
<%@ include file="header.jsp" %>

<div class="card shadow mx-auto" style="max-width: 600px;">
    <div class="card-header bg-danger text-white">
        <h4 class="mb-0">Error Occurred</h4>
    </div>
    <div class="card-body text-center">
        <div class="alert alert-danger" role="alert">
            <%= request.getParameter("message") %>
        </div>
        <a href="index.jsp" class="btn btn-primary">Go Back to Home</a>
    </div>
</div>

<%@ include file="footer.jsp" %>
</body>
</html>