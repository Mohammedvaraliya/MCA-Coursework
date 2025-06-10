<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Student Registration Form</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f9;
            color: #333;
            padding: 20px;
        }

        h1 {
            color: #007BFF;
            text-align: center;
        }

        form {
            background-color: #fff;
            padding: 40px;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            max-width: 400px;
            margin: 0 auto;
        }

        label {
            display: block;
            margin-bottom: 8px;
            font-size: 14px;
        }

        input[type="text"] {
            width: 100%;
            padding: 8px;
            margin-bottom: 10px;
            border: 1px solid #ddd;
            border-radius: 4px;
        }

        input[type="submit"] {
            background-color: #007BFF;
            color: #fff;
            padding: 10px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            width: 100%;
            font-size: 16px;
        }

        input[type="submit"]:hover {
            background-color: #0056b3;
        }

        button {
            background-color: #28a745;
            color: #fff;
            padding: 10px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }

        button:hover {
            background-color: #218838;
        }

        table {
            width: 100%;
            margin-top: 20px;
            border-collapse: collapse;
        }

        table, th, td {
            border: 1px solid #ddd;
        }

        th, td {
            padding: 8px;
            text-align: left;
        }

        th {
            background-color: #007BFF;
            color: white;
        }
    </style>
</head>
<body>
<h1>Welcome Developers of KJSIM</h1>
<form method="post" action="${pageContext.request.contextPath}/session2GenericAndHttpServlet/jdbc-data">
    <label for="rollno">Roll No: </label>
    <input type="text" id="rollno" name="rollno" /><br>

    <label for="name">Name: </label>
    <input type="text" id="name" name="name" /><br>

    <label for="email">Email: </label>
    <input type="text" id="email" name="email" /><br>

    <label for="department">Department: </label>
    <input type="text" id="department" name="department" /><br><br>

    <input type="submit" name="operation" value="Insert" /> <br><br>
    <input type="submit" name="operation" value="Update" /> <br><br>
    <input type="submit" name="operation" value="Delete" /> <br><br>
    <input type="submit" name="operation" value="Read" /> <br><br>
    <input type="submit" name="operation" value="Show All Students" />
</form>
</body>
</html>