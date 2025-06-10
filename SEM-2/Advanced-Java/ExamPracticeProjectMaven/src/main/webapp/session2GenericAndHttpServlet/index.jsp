<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Session 2: Generic and HttpServlet</title>
    <link rel="stylesheet" href="../styles/style.css">
</head>
<body>
<header>
    <h1>Session 2: GenericServlet vs HttpServlet</h1>
</header>

<main class="session-list">
    <section class="session">
        <h2>GenericServlet Demo</h2>
        <a href="${pageContext.request.contextPath}/session2GenericAndHttpServlet/genericservlet" class="session-link">
            View GenericServlet Page
        </a>
    </section>

    <section class="session">
        <h2>HttpServlet Demo</h2>
        <a href="${pageContext.request.contextPath}/session2GenericAndHttpServlet/httpservlet" class="session-link">
            View HttpServlet Page
        </a>
    </section>

    <section class="session">
        <h2>Simple Form</h2>
        <a href="simple-form.jsp" class="session-link">Open Simple Form</a>
    </section>

    <section class="session">
        <h2>JDBC Form</h2>
        <a href="jdbc-form.jsp" class="session-link">Open JDBC Form</a>
    </section>
</main>
</body>
</html>