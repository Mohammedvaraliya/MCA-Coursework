<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Home Page</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css">
</head>

<body class="bg-light">
<div class="container d-flex justify-content-center align-items-center vh-100">
    <div class="card text-center p-4 shadow" style="width: 100%; max-width: 500px;">
        <h1 class="card-title mb-4">
            <i class="bi bi-house-door-fill me-2"></i>Welcome to the Home Page
        </h1>

        <div class="d-grid gap-3">
            <a href="${pageContext.request.contextPath}/spring" class="btn btn-outline-primary">
                <i class="bi bi-box-arrow-up-right me-1"></i> Check Sample Spring View
            </a>
            <a href="${pageContext.request.contextPath}/login" class="btn btn-primary">
                <i class="bi bi-play-circle-fill me-1"></i> Start a Quiz
            </a>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>