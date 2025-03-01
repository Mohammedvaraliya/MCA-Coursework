<%@ page isErrorPage="true" errorPage="errorPage.jsp" %>
<html>
<head>
    <title>Handled Error Page</title>
    <link rel="stylesheet" href="../style/style.css">
</head>
<body>
<div class="container">
    <h2>This page handles errors</h2>
    <p>This page will redirect to the custom error page when an error occurs.</p>
    <%
        // Intentionally throw an exception to trigger error handling
        String s = null;
        System.out.println(s.length()); // This will throw a NullPointerException
    %>
</div>
</body>
</html>