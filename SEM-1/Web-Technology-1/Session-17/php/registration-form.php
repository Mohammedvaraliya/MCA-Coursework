<?php

    // Registration form in PHP
    echo "<br> <h1 style = 'background-color: red; color: white; text-align: center'> PHP Registration Form </h1>";
    echo "<div style='background-color:bisque; margin-left: auto; margin-right:auto; width: 50%; padding: 20px;'>";

    $rollno = $_GET["rollno"];
    $email = $_GET["email"];
    $course = $_GET["course"];
    $city = $_GET["city"];
    $reg_time = $_GET["reg_time"];

    echo "<br>";
    echo "<h2>User submitted values:</h2>";
    echo "<ul>
            <li>Roll Number: $rollno</li>
            <li>Email: $email</li>
            <li>Course: $course</li>
            <li>City: $city</li>
            <li>Registration Time: $reg_time</li>
        </ul>";

    echo "<br> server-php_self: ", $_SERVER["PHP_SELF"];

    echo "</div>";
    
?>