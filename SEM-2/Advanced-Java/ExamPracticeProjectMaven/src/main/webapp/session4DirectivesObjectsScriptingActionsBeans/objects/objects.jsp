<%@ page import="java.util.Date" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Objects Demo</title>
    <link rel="stylesheet" type="text/css" href="../style/style.css" />
</head>
<body>
<%@ include file="../header.jsp" %>
<div class="container">
    <main>
        <h2>Implicit Object Example</h2>
        <p>
            Request Parameter (name): <%= request.getParameter("name") != null ?
                request.getParameter("name") : "No parameter provided" %>
        </p>
        <p>
            Session Attribute (User): <%= session.getAttribute("user") != null ?
                session.getAttribute("user") : "No user set" %>
        </p>
        <p>
            Application Attribute (Counter): <%= application.getAttribute("counter")
                != null ? application.getAttribute("counter") : "Counter not initialized" %>
        </p>
        <a href="../module/actions/home.jsp" class="button">Go to Home Page</a><br /><br />
    </main>
</div>
<%@ include file="../footer.jsp" %>
</body>
</html>