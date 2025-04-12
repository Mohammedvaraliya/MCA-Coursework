<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css">
</head>

<body class="bg-light">

<div class="container d-flex justify-content-center align-items-center" style="height: 100vh;">
    <div class="card shadow p-4" style="width: 100%; max-width: 400px;">
        <div class="text-center mb-4">
            <i class="bi bi-person-circle text-primary fs-1"></i>
            <h3 class="mt-2">Login to Your Account</h3>
            <p class="text-muted small">You need to login first</p>
        </div>

        <form:form method="POST" action="${pageContext.request.contextPath}/login" modelAttribute="loginForm">
            <div class="mb-3">
                <label for="username" class="form-label">Username</label>
                <form:input id="username" class="form-control" path="username"
                            placeholder="Enter your username" required="true"/>
            </div>

            <div class="mb-3">
                <label for="password" class="form-label">Password</label>
                <form:password id="password" class="form-control" path="password"
                               placeholder="Enter your password" required="true"/>
            </div>

            <div class="d-grid">
                <button type="submit" class="btn btn-primary">
                    <i class="bi bi-box-arrow-in-right me-1"></i> Login
                </button>
            </div>

            <c:if test="${not empty errorMessage}">
                <div class="alert alert-danger mt-3 text-center" role="alert">
                    <i class="bi bi-exclamation-triangle-fill me-1"></i> ${errorMessage}
                </div>
            </c:if>
        </form:form>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>