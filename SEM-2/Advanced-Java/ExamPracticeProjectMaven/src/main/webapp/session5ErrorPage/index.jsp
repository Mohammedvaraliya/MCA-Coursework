<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>JSP Error Handling Example</title>
    <link rel="stylesheet" href="style/style.css">
</head>
<body>
<div class="container">
    <h1>JSP Error Handling, Session Tracking and Online Quiz Application</h1>
    <p>Click on a button to perform the following practicals:</p>

    <div class="buttons">
        <div class="buttons">
            <a href="${pageContext.request.contextPath}/session5ErrorPage/errorHandling/errorHandled.jsp">
                <button>Handled Error Example</button>
            </a>

            <a href="${pageContext.request.contextPath}/session5ErrorPage/errorHandling/errorUnhandled.jsp">
                <button>Unhandled Error Example</button>
            </a>

            <a href="${pageContext.request.contextPath}/session5ErrorPage/sessionTracking/login.jsp">
                <button>Session Tracking</button>
            </a>
        </div>

    </div>
</div>
</body>
</html>