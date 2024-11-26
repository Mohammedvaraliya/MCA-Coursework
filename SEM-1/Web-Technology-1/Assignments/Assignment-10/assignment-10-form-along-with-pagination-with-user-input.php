<?php
    echo "<br> <h1 style='background-color: red; color: white; text-align: center'> Show records with dynamic database and table selection. </h1>";
    echo "<div style='background-color:bisque; margin-left: auto; margin-right:auto; width: 50%; padding: 20px;'>";

    $connection = mysqli_connect("localhost", "root", "") or die("Failed to connect with MySQL server!!!");

    if ($connection) {
        echo "<br> Connection successful with MySQL server <br> <br>";
    }

    // Fetch available databases
    $dbList = [];
    $dbQuery = "SHOW DATABASES";
    $dbResult = $connection->query($dbQuery);

    while ($row = $dbResult->fetch_assoc()) {
        $dbList[] = $row['Database'];
    }

    $selectedDb = $_REQUEST['database'] ?? null;
    $selectedTable = $_REQUEST['table'] ?? null;

    if ($selectedDb) {
        mysqli_select_db($connection, $selectedDb);

        // Fetch available tables in the selected database
        $tableList = [];
        $tableQuery = "SHOW TABLES";
        $tableResult = $connection->query($tableQuery);

        while ($row = $tableResult->fetch_row()) {
            $tableList[] = $row[0];
        }

        $message = '';
        $studentData = [];
    }

    // Handle searching student by roll number
    if (isset($_POST['search_with_roll_no'])) {
        if (empty($_POST['rollno'])) {
            echo "<h3 style='color: red;'>Please provide Roll No for searching record!</h3>";
        } else {
            $rollno = mysqli_real_escape_string($connection, $_POST['rollno']);
            // SQL query to search for a student with the provided roll number
            $searchSql = "SELECT * FROM students WHERE rollno = '$rollno'";
            $searchResult = $connection->query($searchSql);
            
            if ($searchResult->num_rows > 0) {
                $studentData = $searchResult->fetch_assoc();
                $message = "<h3 style='color: green;'>Record found and form updated.</h3>";
            } else {
                $message = "<h3 style='color: red;'>Record not found for Roll No. $rollno!</h3>";
            }
        }
    }
?>

<!-- Form for selecting database -->
<form method="get" action="">
    <label for="database"><b>Select Database:</b></label>
    <select name="database" id="database" onchange="this.form.submit()">
        <option value="">-- Select Database --</option>
        <?php foreach ($dbList as $db): ?>
            <option value="<?php echo $db; ?>" <?php if ($selectedDb === $db) echo 'selected'; ?>>
                <?php echo $db; ?>
            </option>
        <?php endforeach; ?>
    </select>
</form>

<?php if (isset($tableList)): ?>
    <!-- Form for selecting table -->
    <form method="get" action="">
        <input type="hidden" name="database" value="<?php echo $selectedDb; ?>">
        <label for="table"><b>Select Table:</b></label>
        <select name="table" id="table" onchange="this.form.submit()">
            <option value="">-- Select Table --</option>
            <?php foreach ($tableList as $table): ?>
                <option value="<?php echo $table; ?>" <?php if ($selectedTable === $table) echo 'selected'; ?>>
                    <?php echo $table; ?>
                </option>
            <?php endforeach; ?>
        </select>
    </form>
<?php endif; ?>

<?php if (isset($selectedTable)): ?>
    <div>
        <?php echo $message; ?> <!-- Display the success/error message -->
        <form method="POST" action="">
            <legend>Perform CRUD Operation on this Form</legend>
            <input type="number" name="rollno" placeholder="Roll No" value="<?php echo $studentData['rollno'] ?? ''; ?>"><br><br>
            <input type="email" name="email" placeholder="Email" value="<?php echo $studentData['email'] ?? ''; ?>"><br><br>
            <input type="text" name="course" placeholder="Course" value="<?php echo $studentData['course'] ?? ''; ?>"><br><br>
            <input type="text" name="city" placeholder="City" value="<?php echo $studentData['city'] ?? ''; ?>"><br><br>
            <input type="datetime-local" name="reg_time" value="<?php echo $studentData['reg_time'] ?? date('Y-m-d\TH:i'); ?>"><br><br>
        
            <input type="submit" name="register" value="Register Student">
            <input type="submit" name="update_record" value="Update Record">
            <input type="submit" name="delete_record" value="Delete Record">
            <input type="submit" name="search_with_roll_no" value="Search Student with Roll No.">
            <input type="submit" name="show_records" value="Show All Records">
            <input type="submit" name="show_records_with_pagination" value="Show All Records with Pagination">
        </form>
    </div>
<?php endif; ?>

<?php

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

            $insertSql = "INSERT INTO $selectedTable (rollno, email, course, city, reg_time) 
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
            $updateSql = "UPDATE $selectedTable SET ";

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
            $checkRecordSql = "SELECT * FROM $selectedTable WHERE rollno='$rollno'";
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

    if ($_SERVER["REQUEST_METHOD"] == "POST" && isset($_POST['search_with_roll_no'])) {
        if (empty($_POST['rollno'])) {
            $message = "<h3 style='color: red;'>Please provide Roll No for searching record!</h3>";
        } else {
            $rollno = mysqli_real_escape_string($connection, $_POST['rollno']);
            $searchSql = "SELECT * FROM $selectedTable WHERE rollno = '$rollno'"; // Use dynamic table name
            $searchResult = $connection->query($searchSql);
    
            if ($searchResult && $searchResult->num_rows > 0) {
                $studentData = $searchResult->fetch_assoc();
                $message = "<h3 style='color: green;'>Record found and form updated.</h3>";
            } else {
                $message = "<h3 style='color: red;'>Record not found for Roll No. $rollno!</h3>";
            }
        }
    }

    

    // Show records if the 'Show All Records' button is pressed
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

    if ($_SERVER["REQUEST_METHOD"] == "POST" && isset($_POST['show_records_with_pagination'])) {

        // Fetch records from the selected table with pagination
        if ($selectedTable) {
            $recordsPerPage = $_REQUEST['recordsPerPage'] ?? 2;
            $currentPage = $_REQUEST['page'] ?? 1;
            
            $startFrom = ($currentPage - 1) * $recordsPerPage; // formula

            $countSql = "SELECT COUNT(*) AS total FROM $selectedTable";
            $countResult = $connection->query($countSql);
            $totalRows = $countResult->fetch_assoc()['total'];
            $totalPages = ceil($totalRows / $recordsPerPage);


            $showSql = "SELECT * FROM $selectedTable LIMIT $startFrom, $recordsPerPage";
            $showResult = $connection->query($showSql);
        }
?>
        <?php if (isset($totalRows) && $totalRows > 0): ?>
            <div>
                <h4>Total Records: <?php echo $totalRows; ?></h4>
                <h4>Total Pages: <?php echo $totalPages; ?></h4>
                <h4>Current Page: <?php echo $currentPage; ?></h4>
            </div>

            <!-- Form for selecting records per page -->
            <form method="POST" action="" style="margin-top: 20px;">
                <input type="hidden" name="database" value="<?php echo $selectedDb; ?>">
                <input type="hidden" name="table" value="<?php echo $selectedTable; ?>">
                <input type="hidden" name="page" value="<?php echo $currentPage; ?>">

                <input type="hidden" name="show_records_with_pagination" value="true">
                
                <label for="recordsPerPage"><b>Records per Page:</b></label>
                <select name="recordsPerPage" id="recordsPerPage" onchange="this.form.submit()">
                    <option value="2" <?php if ($recordsPerPage == 2) echo 'selected'; ?>>2</option>
                    <option value="5" <?php if ($recordsPerPage == 5) echo 'selected'; ?>>5</option>
                    <option value="10" <?php if ($recordsPerPage == 10) echo 'selected'; ?>>10</option>
                </select>
            </form>

            <!-- Message showing records range -->
            <div>
                <?php
                    $message = "Displaying records " . ($startFrom + 1) . " to " . min($startFrom + $recordsPerPage, $totalRows) . ".";
                    echo "<h3 style='color: green;'>$message</h3>";
                ?>
            </div>
        <?php endif; ?>

        <!-- Display records -->
        <?php 

        if (isset($showResult) && $showResult->num_rows > 0): ?>
            <h2>Records from <?php echo $selectedTable; ?>:</h2>
            <table style="width: 100%; border-collapse: collapse;">
                <thead style="background-color: #f2f2f2;">
                <tr>
                    <?php foreach (array_keys($showResult->fetch_assoc()) as $colName): ?>
                        <th style="border: 1px solid #dddddd; padding: 8px;"><?php echo $colName; ?></th>
                    <?php endforeach; ?>
                </tr>
                </thead>
                <tbody>
                <?php
                $showResult->data_seek(0); // Reset result pointer
                while ($row = $showResult->fetch_assoc()): ?>
                    <tr>
                        <?php foreach ($row as $value): ?>
                            <td style="border: 1px solid #dddddd; padding: 8px;"><?php echo $value; ?></td>
                        <?php endforeach; ?>
                    </tr>
                <?php endwhile; ?>
                </tbody>
            </table>
        <?php elseif ($selectedTable): ?>
            <h3 style="color: red;">No records found in <?php echo $selectedTable; ?>.</h3>
        <?php endif; ?>

        <div style="text-align: center; margin-top: 20px;">
            <form method="POST" action="" style="display: inline;">
                <input type="hidden" name="database" value="<?php echo $selectedDb; ?>">
                <input type="hidden" name="table" value="<?php echo $selectedTable; ?>">
                <input type="hidden" name="recordsPerPage" value="<?php echo $recordsPerPage; ?>">
                <input type="hidden" name="page" value="<?php echo $currentPage - 1; ?>">
                <input type="hidden" name="show_records_with_pagination" value="true">
                <?php if ($currentPage > 1): ?>
                    <button type="submit" 
                            style="padding: 12px 20px; 
                                background-color: #007bff; 
                                margin-right: 20px;
                                color: white; 
                                border: 2px solid #0056b3; 
                                border-radius: 5px; 
                                font-size: 16px; 
                                cursor: pointer; 
                                transition: background-color 0.3s, transform 0.2s;"
                            onmouseover="this.style.backgroundColor='#0056b3'; this.style.transform='scale(1.05)';"
                            onmouseout="this.style.backgroundColor='#007bff'; this.style.transform='scale(1)';">
                        Previous
                    </button>
                <?php else: ?>
                    <span style="padding: 12px 20px; 
                                background-color: #f0f0f0; 
                                margin-right: 20px;
                                color: #ccc; 
                                border: 2px solid #ddd; 
                                border-radius: 5px; 
                                font-size: 16px; 
                                cursor: not-allowed;">
                        Previous
                    </span>
                <?php endif; ?>
            </form>

            <form method="POST" action="" style="display: inline;">
                <input type="hidden" name="database" value="<?php echo $selectedDb; ?>">
                <input type="hidden" name="table" value="<?php echo $selectedTable; ?>">
                <input type="hidden" name="recordsPerPage" value="<?php echo $recordsPerPage; ?>">
                <input type="hidden" name="page" value="<?php echo $currentPage + 1; ?>">
                <input type="hidden" name="show_records_with_pagination" value="true">
                <?php if ($currentPage < $totalPages): ?>
                    <button type="submit" 
                            style="padding: 12px 20px; 
                                background-color: #007bff; 
                                color: white; 
                                border: 2px solid #0056b3; 
                                border-radius: 5px; 
                                font-size: 16px; 
                                cursor: pointer; 
                                transition: background-color 0.3s, transform 0.2s;"
                            onmouseover="this.style.backgroundColor='#0056b3'; this.style.transform='scale(1.05)';"
                            onmouseout="this.style.backgroundColor='#007bff'; this.style.transform='scale(1)';">
                        Next
                    </button>
                <?php else: ?>
                    <span style="padding: 12px 20px; 
                                background-color: #f0f0f0; 
                                color: #ccc; 
                                border: 2px solid #ddd; 
                                border-radius: 5px; 
                                font-size: 16px; 
                                cursor: not-allowed;">
                        Next
                    </span>
                <?php endif; ?>
            </form>
        </div>
<?php 
    }
?>

<?php
    echo "</div>";
?>