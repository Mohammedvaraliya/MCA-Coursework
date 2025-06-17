<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://example.com/hello" prefix="st"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>JSP Custom Tag</title>
    <link rel="stylesheet" href="style/style.css">
</head>
<body>
<div class="container">
    <h1>JSP Custom Tag Practical</h1>

    <div class="buttons">
        <h2>Simple Tag Example</h2>
        <p>This tag displays a simple message:</p>
        <st:hello name="Mohammed Varaliya" />
    </div>
    <br>
    <br>

    <div class="buttons">
        <a href="tags/tag.jsp">Custom Simple Tag</a>
    </div>
    <br>
    <br>

    <div class="buttons">
        <a href="tags/coreTags.jsp">Simple Core Tag</a>
    </div>
    <br>
    <br>

    <div class="buttons">
        <a href="tags/sqlTags.jsp">Core Tag (SQL)</a>
    </div>
</div>
</body>
</html>