<%@ page isErrorPage="true" %>
<html>
<head>
    <title>Error Page</title>
    <link rel="stylesheet" href="../style/style.css">
</head>
<body>
<div class="container">
    <h2>An error occurred</h2>
    <p><strong>Error Message:</strong> <%= exception.getMessage() %></p>
</div>
</body>
</html>