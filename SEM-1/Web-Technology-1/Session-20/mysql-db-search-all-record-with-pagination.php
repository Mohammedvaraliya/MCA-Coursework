<?php
    // Show all records with pagination
    echo "<br> <h1 style='background-color: red; color: white; text-align: center'> Show all records with pagination. </h1>";
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

        mysqli_select_db($connection, $dbToUse);

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

    $recordsPerPage = 2;

    $currentPage = isset($_GET['page']) ? (int)$_GET['page'] : 1;
    
    $startFrom = ($currentPage - 1) * $recordsPerPage;

    $countSql = "SELECT COUNT(*) AS total FROM students";
    $countResult = $connection->query($countSql);
    $totalRows = $countResult->fetch_assoc()['total'];

    $totalPages = ceil($totalRows / $recordsPerPage);

    $showSql = "SELECT * FROM students LIMIT $startFrom, $recordsPerPage";
    $showResult = $connection->query($showSql);

    $message = '';
    if ($showResult->num_rows > 0) {
        $message = "<h3 style='color: green;'>Displaying records " . ($startFrom + 1) . " to " . ($startFrom + $showResult->num_rows) . ".</h3>";
    }
?>

<!-- Display Total Records and Total Pages -->
<div>
    <h4>Total Records: <?php echo $totalRows; ?></h4>
    <h4>Total Pages: <?php echo $totalPages; ?></h4>
    <h4>Current Page: <?php echo $currentPage; ?></h4>
</div>

<!-- Display the message with the current range of records -->
<div>
    <?php echo $message; ?>
</div>

<?php
    // Show records
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
        echo "<h3 style='color: red;'>No records found.</h3>";
    }
?>

<!-- Pagination -->
<div style="text-align: center; margin-top: 20px;">
    <?php if ($currentPage > 1): ?>
        <a href="?page=<?php echo $currentPage - 1; ?>" style="padding: 10px; background-color: #f0f0f0; border: 1px solid #ccc; text-decoration: none;">Previous</a>
    <?php else: ?>
        <span style="padding: 10px; background-color: #f0f0f0; border: 1px solid #ccc;">Previous</span>
    <?php endif; ?>

    <?php if ($currentPage < $totalPages): ?>
        <a href="?page=<?php echo $currentPage + 1; ?>" style="padding: 10px; background-color: #f0f0f0; border: 1px solid #ccc; text-decoration: none;">Next</a>
    <?php else: ?>
        <span style="padding: 10px; background-color: #f0f0f0; border: 1px solid #ccc;">Next</span>
    <?php endif; ?>
</div>

<?php
    echo "</div>";
?>