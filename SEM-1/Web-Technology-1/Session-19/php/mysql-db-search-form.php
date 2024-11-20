<?php
    // Search Form
    echo "<br> <h1 style='background-color: red; color: white; text-align: center'> PHP MySQL Search Form </h1>";
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

    $defaultRollNo = 1;
    $rollno = isset($_REQUEST['rollno']) ? (int)$_REQUEST['rollno'] : $defaultRollNo;
    $studentData = [];
    $message = '';

    if ($rollno) {
        $showSql = "SELECT * FROM students WHERE rollno = '$rollno'";
        $showResult = $connection->query($showSql);
        
        if ($showResult->num_rows > 0) {
            $studentData = $showResult->fetch_assoc();
            $message = "<h3 style='color: green;'>Record found and form updated.</h3>";
        } else {
            $message = "<h3 style='color: red;'>Record not found for Roll No. $rollno!</h3>";
        }
    }

?>

<!-- Form start for taking input -->
<div>
    <?php echo $message; ?> <!-- Display the success/error message -->
    <form method="POST" action="">
        <legend>Perform CRUD Operation on this Form</legend>
        <input type="number" name="rollno" placeholder="Roll No" value="<?php echo $studentData['rollno'] ?? $rollno; ?>"><br><br>
        <input type="email" name="email" placeholder="Email" value="<?php echo $studentData['email'] ?? ''; ?>"><br><br>
        <input type="text" name="course" placeholder="Course" value="<?php echo $studentData['course'] ?? ''; ?>"><br><br>
        <input type="text" name="city" placeholder="City" value="<?php echo $studentData['city'] ?? ''; ?>"><br><br>
        <input type="datetime-local" name="reg_time" value="<?php echo $studentData['reg_time'] ?? date('Y-m-d\TH:i'); ?>"><br><br>
        
        <input type="submit" name="show_records" value="Show All Records">
        <input type="submit" name="search_with_roll_no" value="Search Student with Roll No." formaction="?">
    </form>
</div>
<!-- Form end for taking input -->

<?php
    // Handle searching student by roll number (same page redirection)
    if (isset($_POST['search_with_roll_no'])) {
        if (empty($_POST['rollno'])) {
            echo "<h3 style='color: red;'>Please provide Roll No for searching record!</h3>";
        } else {
            $rollno = mysqli_real_escape_string($connection, $_POST['rollno']);
            header("Location: " . $_SERVER['PHP_SELF'] . "?rollno=$rollno");
            exit();
        }
    }

    // Show records if the 'Show All Records' button is pressed
    if (isset($_POST['show_records'])) {
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