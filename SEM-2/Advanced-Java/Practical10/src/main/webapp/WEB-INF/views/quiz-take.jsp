<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>${quiz.title}</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css">
</head>

<body class="bg-light">
<div class="container my-5">
    <div class="card shadow-sm">
        <div class="card-body">
            <h2 class="mb-4 text-center"><i class="bi bi-journal-text me-2"></i>${quiz.title}</h2>

            <form action="<c:url value='/quiz/${quiz.id}/submit'/>" method="post">
                <c:forEach items="${quiz.questions}" var="question" varStatus="status">
                    <div class="mb-4 p-3 border rounded bg-white">
                        <h5 class="mb-3">${status.index + 1}. ${question.text}</h5>
                        <c:forEach items="${question.options}" var="option" varStatus="optStatus">
                            <div class="form-check">
                                <input class="form-check-input" type="radio"
                                       id="q${status.index}_${optStatus.index}"
                                       name="answer${status.index}"
                                       value="${optStatus.index}" required>
                                <label class="form-check-label" for="q${status.index}_${optStatus.index}">
                                        ${option}
                                </label>
                            </div>
                        </c:forEach>
                    </div>
                </c:forEach>

                <div class="d-grid">
                    <button type="submit" class="btn btn-success btn-lg">
                        <i class="bi bi-send me-1"></i> Submit Quiz
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>