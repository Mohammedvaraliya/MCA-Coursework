<?php
    // MySQL Database Connection
    echo "<br> <h1 style='background-color: red; color: white; text-align: center'> PHP MySQL CRUD Operations </h1>";
    echo "<div style='background-color:bisque; margin-left: auto; margin-right:auto; width: 50%; padding: 20px;'>";

    $connection = mysqli_connect("localhost", "root", "") or die("Failed to connect with MySQL server!!!");

    if ($connection) {
        echo "<br> Connection successful with MySQL server <br>";
    }

    # Show Databases
    $sql = "SHOW DATABASES;";
    if ($result = $connection->query($sql)) {
        
        // Create Database
        $dbToUse = "MCADB202426";
        $createDbSql = "CREATE DATABASE IF NOT EXISTS $dbToUse;";
        if ($connection->query($createDbSql) === TRUE) {
            echo "<h3>Database '$dbToUse' created or already exists.</h3>";
        } else {
            echo "Error creating database: " . $connection->error;
        }

        // Select the database for further operations
        mysqli_select_db($connection, $dbToUse);

        // Create Table in MCADB202426 database
        $tableCreation = "CREATE TABLE IF NOT EXISTS students (
            rollno INT(5) AUTO_INCREMENT PRIMARY KEY,
            email VARCHAR(50) NOT NULL,
            course VARCHAR(50),
            city VARCHAR(50),
            reg_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP
        )";
        if ($connection->query($tableCreation) === TRUE) {
            echo "<h3>Table 'students' created successfully in database '$dbToUse'.</h3>";
        } else {
            echo "Error creating table in $dbToUse: " . $connection->error;
        }
    }

    // Include the html crud operation file
    include '../Session-18-html-crud-operations-form-in-php.php';

    // Insert Data (Register)
    if ($_SERVER["REQUEST_METHOD"] == "POST" && isset($_POST['register'])) {

        if (empty($_POST['rollno']) || empty($_POST['email']) || empty($_POST['course']) || empty($_POST['city']) || empty($_POST['reg_time'])) {
            echo "<h3 style='color: red;'>Error: All fields are required. Please fill in all fields.</h3>";
        }
        else {
            $rollno = mysqli_real_escape_string($connection, $_POST['rollno']);
            $email = mysqli_real_escape_string($connection, $_POST['email']);
            $course = mysqli_real_escape_string($connection, $_POST['course']);
            $city = mysqli_real_escape_string($connection, $_POST['city']);
            $reg_time = $_POST['reg_time'];

            $insertSql = "INSERT INTO students (rollno, email, course, city, reg_time) 
                        VALUES ('$rollno', '$email', '$course', '$city', '$reg_time')";

            if ($connection->query($insertSql) === TRUE) {
                echo "<h3>Registration successful! New record created.</h3>";
            } else {
                echo "Error: " . $insertSql . "<br>" . $connection->error;
            }
        }
        
    }


    // Update Data
    if ($_SERVER["REQUEST_METHOD"] == "POST" && isset($_POST['update_record'])) {
        if (empty($_POST['rollno'])) {
            echo "<h3 style='color: red;'>Please provide Roll No for updating record!</h3>";
        } else {
            $rollno = mysqli_real_escape_string($connection, $_POST['rollno']);
            $email = mysqli_real_escape_string($connection, $_POST['email']);
            $course = mysqli_real_escape_string($connection, $_POST['course']);
            $city = mysqli_real_escape_string($connection, $_POST['city']);
            $reg_time = $_POST['reg_time'];

            // Initialize the update query with the base where clause
            $updateSql = "UPDATE students SET ";

            // Append the values to update only if provided (non-empty)
            if (!empty($email)) {
                $updateSql .= "email='$email', ";
            }
            if (!empty($course)) {
                $updateSql .= "course='$course', ";
            }
            if (!empty($city)) {
                $updateSql .= "city='$city', ";
            }
            if (!empty($reg_time)) {
                $updateSql .= "reg_time='$reg_time', ";
            }

            $updateSql = rtrim($updateSql, ", ");

            $updateSql .= " WHERE rollno='$rollno'";

            // Execute the update query
            if ($connection->query($updateSql) === TRUE) {
                echo "<h3>Record updated successfully.</h3>";
            } else {
                echo "Error: " . $updateSql . "<br>" . $connection->error;
            }
        }
    }

    // Delete Record
    if ($_SERVER["REQUEST_METHOD"] == "POST" && isset($_POST['delete_record'])) {
        if (empty($_POST['rollno'])) {
            echo "<h3 style='color: red;'>Please provide Roll No for deleting record!</h3>";
        } else {
            $rollno = mysqli_real_escape_string($connection, $_POST['rollno']);

            // Check if the record exists
            $checkRecordSql = "SELECT * FROM students WHERE rollno='$rollno'";
            $result = $connection->query($checkRecordSql);

            if ($result->num_rows > 0) {
                $deleteSql = "DELETE FROM students WHERE rollno='$rollno'";

                if ($connection->query($deleteSql) === TRUE) {
                    echo "<h3>Record deleted successfully.</h3>";
                } else {
                    echo "Error: " . $deleteSql . "<br>" . $connection->error;
                }
            } else {
                echo "<h3 style='color: red;'>Record with Roll No $rollno not found!</h3>";
            }
        }
    }


    // Fetch Records
    if ($_SERVER["REQUEST_METHOD"] == "POST" && isset($_POST['show_records'])) {
        $showSql = "SELECT * FROM students";
        $showResult = $connection->query($showSql);
        if ($showResult->num_rows > 0) {
            echo "<h2>List of Students:</h2>";
            echo "<table style='width: 100%; border-collapse: collapse;'>";
            echo "<thead style='background-color: #f2f2f2;'>";
            echo "<tr>";
            echo "<th style='border: 1px solid #dddddd; padding: 8px;'>Roll No</th>";
            echo "<th style='border: 1px solid #dddddd; padding: 8px;'>Email</th>";
            echo "<th style='border: 1px solid #dddddd; padding: 8px;'>Course</th>";
            echo "<th style='border: 1px solid #dddddd; padding: 8px;'>City</th>";
            echo "<th style='border: 1px solid #dddddd; padding: 8px;'>Registration Time</th>";
            echo "</tr>";
            echo "</thead>";
            echo "<tbody>";
            while ($row = $showResult->fetch_assoc()) {
                echo "<tr>";
                echo "<td style='border: 1px solid #dddddd; padding: 8px;'>" . $row['rollno'] . "</td>";
                echo "<td style='border: 1px solid #dddddd; padding: 8px;'>" . $row['email'] . "</td>";
                echo "<td style='border: 1px solid #dddddd; padding: 8px;'>" . $row['course'] . "</td>";
                echo "<td style='border: 1px solid #dddddd; padding: 8px;'>" . $row['city'] . "</td>";
                echo "<td style='border: 1px solid #dddddd; padding: 8px;'>" . $row['reg_time'] . "</td>";
                echo "</tr>";
            }
            echo "</tbody>";
            echo "</table>";
        } else {
            echo "<h3>No records found.</h3>";
        }
    }

    echo "</div>";
?>
