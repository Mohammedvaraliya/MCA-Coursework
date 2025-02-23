<%@ page import="java.util.Date" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta
      name="viewport"
      content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0"
    />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <link rel="stylesheet" type="text/css" href="../css/styles.css" />
    <title>Page Directives Demo</title>
  </head>
  <body>
  <%@ include file="../header.jsp" %>
  <div class="container">
      <main>
          <h1 style="color: #333333">Page Directives Demo</h1>
          <p>Current Date and Time: <%= new Date() %></p>
          <br />

          <p>Before increment: <%=counter%></p>

          <% incrementCounter(); %>
          <p>After increment: <%= counter %></p>
          <%! int counter = 0; void incrementCounter() { counter++; } %>

          <a href="../module/actions/home.jsp" class="button">Go to Home Page</a><br /><br />
        </main>
    </div>
  <%@ include file="../footer.jsp" %>
  </body>
</html>
