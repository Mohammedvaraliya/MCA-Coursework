<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Quiz Results</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css">
</head>

<body class="bg-light">
<div class="container my-5">
    <div class="card shadow text-center">
        <div class="card-body">
            <h2 class="card-title mb-4">
                <i class="bi bi-bar-chart-line-fill me-2"></i>Quiz Results: ${quiz.title}
            </h2>

            <div class="alert alert-secondary fs-5 py-3">
                Your score:
                <span class="fw-bold text-success">${score} out of ${total}</span>
            </div>

            <c:if test="${score == total}">
                <div class="alert alert-success" role="alert">
                    <i class="bi bi-star-fill me-1"></i> Perfect score! Congratulations!
                </div>
            </c:if>

            <c:if test="${score >= total/2 && score < total}">
                <div class="alert alert-primary" role="alert">
                    <i class="bi bi-emoji-smile me-1"></i> Good job! You passed the quiz.
                </div>
            </c:if>

            <c:if test="${score < total/2}">
                <div class="alert alert-warning" role="alert">
                    <i class="bi bi-emoji-frown me-1"></i> You might want to review the material and try again.
                </div>
            </c:if>

            <a href="<c:url value='/quiz/list'/>" class="btn btn-outline-success mt-4">
                <i class="bi bi-arrow-left-circle me-1"></i> Back to Quiz List
            </a>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>