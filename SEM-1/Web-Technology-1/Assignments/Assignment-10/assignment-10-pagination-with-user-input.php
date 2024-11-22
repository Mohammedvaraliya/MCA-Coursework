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
    $recordsPerPage = $_REQUEST['recordsPerPage'] ?? 2;
    $currentPage = $_REQUEST['page'] ?? 1;
    $startFrom = ($currentPage - 1) * $recordsPerPage; // formula
    $totalRows = 0;
    $totalPages = 0;

    if ($selectedDb) {
        mysqli_select_db($connection, $selectedDb);

        // Fetch available tables in the selected database
        $tableList = [];
        $tableQuery = "SHOW TABLES";
        $tableResult = $connection->query($tableQuery);

        while ($row = $tableResult->fetch_row()) {
            $tableList[] = $row[0];
        }

        // Fetch records from the selected table with pagination
        if ($selectedTable) {
            $countSql = "SELECT COUNT(*) AS total FROM $selectedTable";
            $countResult = $connection->query($countSql);
            $totalRows = $countResult->fetch_assoc()['total'];
            $totalPages = ceil($totalRows / $recordsPerPage);

            $showSql = "SELECT * FROM $selectedTable LIMIT $startFrom, $recordsPerPage";
            $showResult = $connection->query($showSql);
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

<?php if (isset($totalRows) && $totalRows > 0): ?>
    <div>
        <h4>Total Records: <?php echo $totalRows; ?></h4>
        <h4>Total Pages: <?php echo $totalPages; ?></h4>
        <h4>Current Page: <?php echo $currentPage; ?></h4>
    </div>

    <!-- Form for selecting records per page -->
    <form method="get" action="" style="margin-top: 20px;">
        <input type="hidden" name="database" value="<?php echo $selectedDb; ?>">
        <input type="hidden" name="table" value="<?php echo $selectedTable; ?>">
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
<?php if (isset($showResult) && $showResult->num_rows > 0): ?>
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

<!-- Pagination -->
<?php if ($totalPages > 1): ?>
    <div style="text-align: center; margin-top: 20px;">
        <?php if ($currentPage > 1): ?>
            <a href="?database=<?php echo $selectedDb; ?>&table=<?php echo $selectedTable; ?>&page=<?php echo $currentPage - 1; ?>&recordsPerPage=<?php echo $recordsPerPage; ?>" style="padding: 10px; background-color: #f0f0f0; border: 1px solid #ccc; text-decoration: none;">Previous</a>
        <?php else: ?>
            <span style="padding: 10px; background-color: #f0f0f0; border: 1px solid #ccc;">Previous</span>
        <?php endif; ?>

        <?php if ($currentPage < $totalPages): ?>
            <a href="?database=<?php echo $selectedDb; ?>&table=<?php echo $selectedTable; ?>&page=<?php echo $currentPage + 1; ?>&recordsPerPage=<?php echo $recordsPerPage; ?>" style="padding: 10px; background-color: #f0f0f0; border: 1px solid #ccc; text-decoration: none;">Next</a>
        <?php else: ?>
            <span style="padding: 10px; background-color: #f0f0f0; border: 1px solid #ccc;">Next</span>
        <?php endif; ?>
    </div>
<?php endif; ?>

<?php
    echo "</div>";
?>