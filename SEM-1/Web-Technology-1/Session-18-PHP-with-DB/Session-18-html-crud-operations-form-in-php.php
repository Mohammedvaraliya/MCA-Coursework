<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>PHP Student CRUD Operations Form</title>
</head>
<body>
    <div>
        <form method="POST" action="">
            <legend>Perform CRUD Operation on this Form</legend>
            <input type="number" name="rollno" placeholder="Roll No"><br><br>
            <input type="email" name="email" placeholder="Email"><br><br>
            <input type="text" name="course" placeholder="Course"><br><br>
            <input type="text" name="city" placeholder="City"><br><br>
            <input type="datetime-local" name="reg_time" value="<?php echo date('Y-m-d\TH:i'); ?>"><br><br>
            
            <input type="submit" name="register" value="Register Student">
            <input type="submit" name="update_record" value="Update Record">
            <input type="submit" name="delete_record" value="Delete Record">
            <input type="submit" name="show_records" value="Show All Records">
        </form>
    </div>
</body>
</html>