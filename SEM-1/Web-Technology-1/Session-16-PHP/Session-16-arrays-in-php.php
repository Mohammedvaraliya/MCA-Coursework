<?php

    // arrays in php is the method
    echo "<br> <h1 style = 'background-color: red; color: white; text-align: center'> PHP Arrays </h1>";
    echo "<div style='background-color:bisque; margin-left: auto; margin-right:auto; width: 500px; padding: 20px;'>";

        $subjects = array("Web Technology","DSA","Core Java","NEP","Database Application");

        echo "<hr><br> print_r output. <br>";
        print_r( $subjects );

        echo "<hr><br> var_dump output. <br>";
        var_dump( $subjects );

        // Accessing arrays with for loop
        echo "<hr><br> <b>Accessing array with simple for loop:</b> <br>";
        for ($i = 0; $i < count($subjects); $i++) {
            echo "Subject {$i} is {$subjects[$i]} <br>";
        }

        // Accessing arrays with for each loop
        echo "<hr><br> <b>Accessing array with simple for each loop:</b> <br>";
        foreach ($subjects as $key => $subj) {
            echo "Subject {$key} is {$subj} <br>";
        }

        // Accessing associative arrays with for each loop from key value, dictionary
        // It accepts any type key and value, such as int, string, etc
        echo "<hr><br> <b>Accessing associative array with for each loop:</b> <br>";
        $sub_faculty = array("Web Technology" => "Sudarshan Sir","DSA" => "Krantee ma'am", "Core Java" => "Jaya ma'am","NEP" => "IDK", "Database Application" => "Sangeeta ma'am");
        foreach ($sub_faculty as $subj => $prof) {
            echo "Subject {$subj}'s professor is {$prof} <br>";
        }

        // Accessing multidimensional arrays with for each loop from key value, dictionary
        // It accepts any type of values
        echo "<hr><br> <b>Accessing Multi-dimensional array with for each loop:</b> <br>";
        echo "<br>";

        $students_detail = array(
            array("101", "m.varaliya@somaiya.edu", "SDE", "Pursuing MCA"),
            array("102", "jayesh.mal@somaiya.edu", "QA", "Pursuing MCA"),
            array("103", "vraj.shah@somaiya.edu", "Modeling", "Pursuing Fashion Designing"),
            array("104", "vishal.maabehen@somaiya.edu", "Data Scientist", "Pursuing MSc DS"),
        );

        echo "<table border='1' cellpadding='10' cellspacing='0'>";
        echo "<tr>
                <th>ID</th>
                <th>Email</th>
                <th>Role</th>
                <th>Course</th>
            </tr>";

        foreach ($students_detail as $rec) {
            echo "<tr>";
            foreach ($rec as $val) {
                echo "<td>{$val}</td>";
            }
            echo "</tr>";
        }

        echo "</table>";

        // Accessing arrays with for each loop and fullfilling ordered list
        echo "<hr><br> <b>Accessing arrays with for each loop and fullfilling ordered list</b> <br>";
        $ordered_list = array("Web Technology","DSA","Core Java","NEP","Database Application");
        echo "<ol>";
        foreach ($ordered_list as $item) {
            echo "<li>{$item}</li>";
        }
        echo "</ol>";

        // Accessing Associative arrays with for each loop and fullfilling Definitive list
        echo "<hr><br> <b>Accessing Associative arrays with for each loop and fullfilling Descriptive list</b> <br>";
        $descriptive_list = array(
            "name" => "Mohammed Varaliya",
            "email" => "m.varaliya@somaiya.edu",
            "occupation" => "Software Development Engineer"
        );
        
        echo "<dl>";
        foreach ($descriptive_list as $key => $value) {
            echo "<dt><b>{$key}:</b></dt>";
            echo "<dd>{$value}</dd>";
        }
        echo "</dl>";

    echo "</div>";
?>