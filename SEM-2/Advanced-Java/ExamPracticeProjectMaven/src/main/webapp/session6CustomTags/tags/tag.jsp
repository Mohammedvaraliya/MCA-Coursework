<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="custom" tagdir="/WEB-INF/tags" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>JSP Custom Simpler Tag</title>
    <link rel="stylesheet" href="../style/style.css">
</head>
<body>
<div class="container">
    <div class="buttons">
        <h2>Simpler Custom Tag Example</h2>
        <p>This tag displays a simple message:</p>
        <custom:simplerHelloTag name="Mohammed Varaliya" />
    </div>
</div>
</body>
</html>