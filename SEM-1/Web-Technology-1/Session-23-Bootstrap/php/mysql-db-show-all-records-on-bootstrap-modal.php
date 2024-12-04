<?php
// Show all records with pagination
echo "<br> <h1 style='background-color: red; color: white; text-align: center'> Show all records using Bootstrap Modal. </h1>";
echo "<div style='background-color:bisque; margin-left: auto; margin-right:auto; width: 50%; padding: 20px;'>";
?>

<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Session 23 Show All Records on Modal</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous" />
</head>

<body>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>
</body>
<?php
$connection = mysqli_connect("localhost", "root", "") or die("Failed to connect with MySQL server!!!");

if ($connection) {
    echo "<br> <p class='h5 text-dark p-3 bg-info'>Connection successful with MySQL server </p><br>";
}

# Show Databases
$sql = "SHOW DATABASES;";
if ($result = $connection->query($sql)) {

    // Create Database
    $dbToUse = "MCADB202426";
    $createDbSql = "CREATE DATABASE IF NOT EXISTS $dbToUse;";
    if ($connection->query($createDbSql) === TRUE) {
        echo "<p class='h5 text-light p-3 bg-success'>Database '$dbToUse' created or already exists.</p>";
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
        echo "<p class='h5 text-light p-3 bg-success'>Table 'students' created successfully in database '$dbToUse'.</p>";
    } else {
        echo "Error creating table in $dbToUse: " . $connection->error;
    }
}
?>

<?php

$showSql = "SELECT * FROM students";
$showResult = $connection->query($showSql);

$message = '';
if ($showResult->num_rows > 0) {
    $message = "<p class='h2 text-success'>Displaying records</p>";
}
?>

<button type="button" class="btn btn-dark mt-4" data-bs-toggle="modal" data-bs-target="#showRecordModal">
    Show all records on Modal
</button>

<div class="modal modal-l fade" id="showRecordModal" aria-labelledby="showRecordModalLabel">
    <div class="modal-dialog modal-dialog-scrollable modal-xl">
        <div class="modal-content">
            <div class="modal-header">
                <h1 class="modal-title fs-5" id="showRecordModalLabel">Showing all the records</h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">

                <?php
                // Show records
                if ($showResult->num_rows > 0) {
                    ?>
                    <p class="h3">List of Students:</p>
                    <table class="table table-bordered table-hover table striped">
                        <thead class="table-dark">
                            <tr>
                                <td>Roll No</td>
                                <td>Email</td>
                                <td>Course</td>
                                <td>City</td>
                                <td>Registration Time</td>
                            </tr>
                        </thead>
                        <tbody>
                            <?php
                            while ($row = $showResult->fetch_assoc()) {
                                ?>

                                <tr class="table-primary">
                                    <td><?php echo $row['rollno']; ?></td>
                                    <td><?php echo $row['email']; ?></td>
                                    <td><?php echo $row['course']; ?></td>
                                    <td><?php echo $row['city']; ?></td>
                                    <td><?php echo $row['reg_time']; ?></td>
                                </tr>
                                <?php
                            }
                } else {
                    echo "<p class='h2 text-danger'>No records found.</p>";
                }
                ?>
                    </tbody>
                </table>

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>

<?php
echo "</div>";
?>