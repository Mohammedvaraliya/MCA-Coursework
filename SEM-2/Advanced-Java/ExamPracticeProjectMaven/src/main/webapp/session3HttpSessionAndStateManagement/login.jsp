<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Login</title>
  <link rel="stylesheet" href="style/style.css">
</head>
<body>
<div class="form-container">
  <h2>Login</h2>
  <form action="${pageContext.request.contextPath}/session3HttpSessionAndStateManagement/login" method="POST">
    <label for="username">Username:</label>
    <input type="text" id="username" name="username" required><br><br>

    <label for="password">Password:</label>
    <input type="password" id="password" name="password" required><br><br>

    <input type="submit" value="Login" class="button">
  </form>
</div>
</body>
</html>