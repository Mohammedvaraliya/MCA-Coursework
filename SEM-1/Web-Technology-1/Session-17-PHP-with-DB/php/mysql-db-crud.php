<?php
    // MySQL Database Connection
    echo "<br> <h1 style='background-color: red; color: white; text-align: center'> PHP MySQL DB Connection </h1>";
    echo "<div style='background-color:bisque; margin-left: auto; margin-right:auto; width: 50%; padding: 20px;'>";

    $connection = mysqli_connect("localhost:3306", "root", "") or die("Failed to connect with MySQL server!!!");

    if ($connection) {
        echo "<br> Connection successful with MySQL server <br>";
    }

    # Show Databases
    $sql = "SHOW DATABASES;";

    if ($result = $connection->query($sql)) {
        $databases = [];
        
        while ($row = $result->fetch_assoc()) {
            $databases[] = $row["Database"];
        }
        
        if (count($databases) > 0) {
            echo "<h2>List of databases from MySQL:</h2>";
            echo "<ul>";
            foreach ($databases as $db) {
                echo "<li>" . $db . "</li>";
            }
            echo "</ul>";
        }
        
        // Create Database
        $dbToUse = "MCADB202426";
        if (!in_array($dbToUse, $databases)) {
            $createDbSql = "CREATE DATABASE $dbToUse;";
            if ($connection->query($createDbSql) === TRUE) {
                echo "<h3>Database '$dbToUse' created successfully.</h3>";
            } else {
                echo "Error creating database: " . $connection->error;
            }
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

        // Include the registration form
        include '../Session-17-mysql-table-insertion-in-php.php';

        // Inserting records in the students table
        // Handle form submission
        if ($_SERVER["REQUEST_METHOD"] == "GET") {
            if (isset($_GET['register'])) {
                $rollno = mysqli_real_escape_string($connection, $_GET['rollno']);
                $email = mysqli_real_escape_string($connection, $_GET['email']);
                $course = mysqli_real_escape_string($connection, $_GET['course']);
                $city = mysqli_real_escape_string($connection, $_GET['city']);
                $reg_time = date('Y-m-d H:i:s');

                $insertSql = "INSERT INTO students (rollno, email, course, city, reg_time) VALUES ('$rollno', '$email', '$course', '$city', '$reg_time')";
                if ($connection->query($insertSql) === TRUE) {
                    echo "<h3>Registration successful! New record created.</h3>";
                } else {
                    echo "Error: " . $insertSql . "<br>" . $connection->error;
                }
            } elseif (isset($_GET['showall'])) {
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
        }

        $result->free();
    } else {
        echo "Error executing query: " . $connection->error;
    }

    $connection->close();
    echo "</div>";
?>