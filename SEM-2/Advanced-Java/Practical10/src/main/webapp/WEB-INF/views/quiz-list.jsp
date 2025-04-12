<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Available Quizzes</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css">
</head>

<body class="bg-light">
<div class="container my-5">
    <div class="card shadow-sm">
        <div class="card-body">
            <h2 class="mb-4 text-center"><i class="bi bi-list-task me-2"></i>Available Quizzes</h2>

            <c:choose>
                <c:when test="${not empty quizzes}">
                    <div class="list-group">
                        <c:forEach items="${quizzes}" var="quiz">
                            <div class="list-group-item list-group-item-action d-flex justify-content-between align-items-center shadow-sm mb-3 rounded">
                                <div>
                                    <h5 class="mb-1">${quiz.title}</h5>
                                    <small class="text-muted">${quiz.questions.size()} Questions</small>
                                </div>
                                <a href="<c:url value='/quiz/${quiz.id}'/>" class="btn btn-success">
                                    <i class="bi bi-play-circle me-1"></i> Take Quiz
                                </a>
                            </div>
                        </c:forEach>
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="alert alert-info text-center">
                        <i class="bi bi-info-circle me-1"></i> No quizzes available at the moment.
                    </div>
                </c:otherwise>
            </c:choose>

        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
