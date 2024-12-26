<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>PHP Student CRUD Operations Form</title>
</head>
<body>
    <div>
        <?php echo $message; ?> <!-- Display the success/error message -->
        <form method="POST" action="">
            <legend>Perform CRUD Operation on this Form</legend>
            <input type="number" name="rollno" placeholder="Roll No" value="<?php echo $studentData['rollno'] ?? $defaultRollNo; ?>"><br><br>
            <input type="email" name="email" placeholder="Email" value="<?php echo $studentData['email'] ?? ''; ?>"><br><br>
            <input type="text" name="course" placeholder="Course" value="<?php echo $studentData['course'] ?? ''; ?>"><br><br>
            <input type="text" name="city" placeholder="City" value="<?php echo $studentData['city'] ?? ''; ?>"><br><br>
            <input type="datetime-local" name="reg_time" value="<?php echo $studentData['reg_time'] ?? date('Y-m-d\TH:i'); ?>"><br><br>
            
            <input type="submit" name="show_records" value="Show All Records">
            <input type="submit" name="search_with_roll_no" value="Search Student with Roll No." formaction="?">
        </form>
    </div>
</body>
</html>