<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>PHP Student Registration Form</title>
</head>
<body>
    <div>
        <form method="get" action="">
            <legend>Registration Form</legend>
            <input type="number" name="rollno" placeholder="Roll No" required><br><br>
            <input type="email" name="email" placeholder="Email" required><br><br>
            <input type="text" name="course" placeholder="Course" required><br><br>
            <input type="text" name="city" placeholder="City" required><br><br>
            <input type="datetime-local" name="reg_time" value="<?php echo date('Y-m-d\TH:i'); ?>"><br><br>
            <input type="submit" name="register" value="Register">
            <input type="submit" name="showall" value="Show All">
        </form>
    </div>
</body>
</html>