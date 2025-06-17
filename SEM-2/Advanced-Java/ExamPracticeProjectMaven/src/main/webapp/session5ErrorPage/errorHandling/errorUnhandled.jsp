<html>
<head>
    <title>Unhandled Error Page</title>
    <link rel="stylesheet" href="../style/style.css">
</head>
<body>
<div class="container">
    <h2>This page does not handle errors</h2>
    <p>This page will display the default error page.</p>
    <%
        // This will cause an exception for testing
        String s = null;
        System.out.println(s.length()); // Will throw NullPointerException
    %>
</div>
</body>
</html>