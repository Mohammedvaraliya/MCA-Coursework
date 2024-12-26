<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>PHP Registration Form</title>

    <style>
        div {
            background-color: bisque;
            margin-left: auto;
            margin-right: auto;
            width: 50%;
            padding: 20px;
        }
    </style>
</head>

<body>

    <div>
        <form metho="get" action="<?php echo 'php/registration-form.php'; ?>">
            <legend>Registration form</legend>
            <input type="number" name="rollno" placeholder="1"><br><br>
            <input type="email" name="email" placeholder="abc@somaiya.edu"><br><br>
            <input type="text" name="course" placeholder="MCA"><br><br>
            <input type="text" name="city" placeholder="Mumbai"><br><br>
            <input type="datetime-local" name="reg_time"><br><br>

            <input type="submit" name="register" value="register">
        </form>
    </div>

    
</body>

</html>