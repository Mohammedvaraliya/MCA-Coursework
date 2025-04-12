<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Success</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css">
</head>
<body class="bg-light">

<div class="container d-flex justify-content-center align-items-center" style="height: 100vh;">
    <div class="card shadow p-4 text-center" style="width: 100%; max-width: 500px;">
        <div class="card-body">
            <h2 class="card-title text-success mb-3">
                <i class="bi bi-check-circle-fill me-2"></i>Success!
            </h2>
            <p class="fs-5 mb-4">${successMessage}</p>
            <a href="${pageContext.request.contextPath}/quiz/list" class="btn btn-primary">
                <i class="bi bi-play-circle me-1"></i>Start a Quiz
            </a>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
