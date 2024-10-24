<?php

    // All type of String Functions in PHP
    echo "<br> <h1 style = 'background-color: red; color: white; text-align: center'> PHP String Functions </h1>";
    echo "<div style='background-color:bisque; margin-left: auto; margin-right:auto; width: 500px; padding: 20px;'>";

    $str = " PHP String Functions! ";

    echo "Original string: '$str'<br>";
    echo "Length: " . strlen($str) . "<br>";
    echo "Trimmed: '" . trim($str) . "'<br>";
    echo "Uppercase: " . strtoupper($str) . "<br>";
    echo "Lowercase: " . strtolower($str) . "<br>";
    echo "Substring (1, 5): '" . substr($str, 1, 5) . "'<br>";
    echo "Replaced: '" . str_replace("String", "Functions", $str) . "'<br>";
    echo "Position of 'World': " . strpos($str, "World") . "<br>";
    echo "Split: ";
    print_r(str_split(trim($str)));
    
    echo "<h3 style='background-color: lightgreen;'>Additional String Functions</h3>";
    
    $anotherStr = "Welcome to PHP programming.";
    
    echo "Repeated (3 times): " . str_repeat($anotherStr, 3) . "<br>";
    
    echo "Length of another string: " . strlen($anotherStr) . "<br>";
    
    echo "Position of 'PHP': " . strpos($anotherStr, "PHP") . "<br>";
    
    echo "Substring from 0 to 7: '" . substr($anotherStr, 0, 7) . "'<br>";
    
    printf("Formatted string: %s<br>", $anotherStr);
    
    echo "Replaced 'PHP' with 'Python': " . str_replace("PHP", "Python", $anotherStr) . "<br>";
    
    echo "String comparison (equal): " . strcmp($str, $anotherStr) . "<br>";
    
    echo "To lowercase: " . strtolower($anotherStr) . "<br>";
    echo "To uppercase: " . strtoupper($anotherStr) . "<br>";
    
    echo "Trimmed string: '" . trim("  Hello World!  ") . "'<br>";
    
    $arrayFromString = explode(" ", $anotherStr);
    echo "Exploded string: ";
    print_r($arrayFromString);
    echo "<br>";
    
    echo "Imploded string: " . implode("-", $arrayFromString) . "<br>";
    
    $contains = strpos($anotherStr, "PHP") !== false ? "Yes" : "No";
    echo "Does the string contain 'PHP'? " . $contains . "<br>";
    
    $caseInsensitivePos = stripos($anotherStr, "php");
    echo "Position of 'php' (case insensitive): " . $caseInsensitivePos . "<br>";
    
    echo "Reversed string: " . strrev($anotherStr) . "<br>";
    
    echo "Replacing first occurrence of 'o' with 'O': " . preg_replace('/o/', 'O', $anotherStr, 1) . "<br>";

    echo "</div>";
?>