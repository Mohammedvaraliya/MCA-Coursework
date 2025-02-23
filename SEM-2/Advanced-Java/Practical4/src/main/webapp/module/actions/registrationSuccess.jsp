<%@ page import="org.kjsim.StudentBean" %>
<jsp:useBean id="student" class="org.kjsim.StudentBean" scope="request" />
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="../../css/styles.css">
    <title>Registration Successful</title>
</head>
<body>
<%@ include file="../../header.jsp" %>
<div class="container">
<main>
    <div class="message success">Student Registered Successfully</div>
    <p>Details of the registered student:</p>
    <table>
        <tr>
            <th>Roll No</th>
            <th>Name</th>
            <th>Email</th>
            <th>Department</th>
        </tr>
        <tr><td><jsp:getProperty name="student" property="rollno"/></td>
            <td><jsp:getProperty name="student" property="name"/></td>
            <td><jsp:getProperty name="student" property="email"/></td>
            <td><jsp:getProperty name="student" property="department"/></td>
        </tr>
    </table>

    <div class="button-container">
        <a href="/app/module/actions/register.jsp"><button>Go Back to Register</button></a>
        <a href="/app/module/actions/results.jsp"><button>View Results</button></a>
    </div>
</main>
</div>
<%@ include file="../../footer.jsp" %>

</body>
</html>
