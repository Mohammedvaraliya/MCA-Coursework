<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Form Submission</title>
</head>
<body>
<h1>Welcome Developers</h1>
<form method="post" action="${pageContext.request.contextPath}/session2GenericAndHttpServlet/form-data">
    <label for="name">Name: </label>
    <input type="text" id="name" name="name" required /><br><br>

    <label for="rollno">Roll No: </label>
    <input type="text" id="rollno" name="rollno" required /><br><br>

    <input type="submit" value="Submit" />
</form>
</body>
</html>