<%@ page import="java.util.Date" %>
<%
    String name = "Mohammed Varaliya";
    String course = "MCA";
//  out.println("Hello, " + name + "! You are learning " + course + ".");
%>

<%!
    int counter = 0;
    public void incrementCounter() {
        counter++;
    }
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Scripting Demo</title>
    <link rel="stylesheet" type="text/css" href="../style/style.css" />
</head>
<body>
<%@ include file="../header.jsp" %>
<div class="container">
    <main>
        <h2>Scripting Elements Demo</h2>
        <p>Today's date is <%= new Date() %>.</p>
        <p>Before increment: <%= counter %></p>

        <%
            incrementCounter();
            boolean counterUpdated = (counter > 0);
            boolean appUpdated = false;
            boolean sessionUpdated = false;

            application.setAttribute("counter", counter);
            appUpdated = (application.getAttribute("counter") != null);

            session.setAttribute("user", "MCA");
            sessionUpdated = (session.getAttribute("user") != null);
        %>

        <p>After increment: <%= counter %></p>

        <p>Counter Update: <%= counterUpdated ? "Successfully updated!" : "Failed to update." %></p>
        <p>Application Scope Update: <%= appUpdated ? "Successfully set!" : "Failed to set." %></p>
        <p>Session Scope Update: <%= sessionUpdated ? "Successfully set!" : "Failed to set." %></p>

        <a href="../module/actions/home.jsp" class="button">Go to Home Page</a><br /><br />
    </main>
</div>
<%@ include file="../footer.jsp" %>
</body>
</html>
