<?php

    echo "<h2> Using For Loop <br> </h2>";
    $arr = [1, 2, 3, 4, 5, 6];
    for ($i = 0; $i < count($arr); $i++) {
        echo "Element value is {$arr[$i]} <br>";
    }

    echo "<h2> <br> Using While Loop <br> </h2>";
    $i = 0;
    while ($i < count($arr)) {
        echo "Element value is {$arr[$i]} <br>";
        $i++;
    }

    echo "<h2> <br> Using ForEach Loop <br> </h2>";
    foreach ($arr as $value) {
        echo "Element value is {$value} <br>";
    }

    echo "<h2> <br> Using Do While Loop <br> </h2>";
    $i = 0;
    do {
        echo "Element value is {$arr[$i]} <br>";
        $i++;
    } while ($i < count($arr));

    echo "<h2> <br> Using Swich Statements <br> </h2>";
    $subj = "";
    switch ($subj) {
        case 'Web Technology':
            echo "Now you are learing Web Technology";
            break;

        case 'DSA':
            echo "Now you are learing Data Structure";
            break;

        case 'Core Java':
            echo "Now you are learing Core Java";
            break;

        case 'NEP':
            echo "Now you are attending NEP Session";
            break;

        case 'Database Application':
            echo "Now you are learing Database Application";
            break;
        
        default:
            echo "You've to attend the lectures, regularly";
            break;
    }
?>