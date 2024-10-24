<?php

    // All type of Array Functions in PHP
    echo "<br> <h1 style = 'background-color: red; color: white; text-align: center'> PHP Array Functions </h1>";
    echo "<div style='background-color:bisque; margin-left: auto; margin-right:auto; width: 500px; padding: 20px;'>";

    $arr = [1, 2, 3, 4];

    // Using array_push() to add elements to the end
    array_push($arr, 5);
    echo "After push: " . implode(", ", $arr) . "<br>";
    
    // Using array_pop() to remove the last element
    array_pop($arr);
    echo "After pop: " . implode(", ", $arr) . "<br>";
    
    // Using array_shift() to remove the first element
    array_shift($arr);
    echo "After shift: " . implode(", ", $arr) . "<br>";
    
    // Using array_unshift() to add an element at the beginning
    array_unshift($arr, 0);
    echo "After unshift: " . implode(", ", $arr) . "<br>";
    
    // Using array_slice() to get a portion of the array
    $slicedArray = array_slice($arr, 1, 2);
    echo "Sliced array (from index 1, length 2): " . implode(", ", $slicedArray) . "<br>";
    
    // Using array_map() to apply a function to each element
    $mappedArray = array_map(function($x) { return $x * 2; }, $arr);
    echo "Mapped array (each element multiplied by 2): " . implode(", ", $mappedArray) . "<br>";
    
    // Using array_filter() to filter elements based on a condition
    $filteredArray = array_filter($arr, function($x) { return $x > 2; });
    echo "Filtered array (elements greater than 2): " . implode(", ", $filteredArray) . "<br>";
    
    // Using array_reduce() to reduce the array to a single value
    $reducedValue = array_reduce($arr, function($carry, $item) { return $carry + $item; }, 0);
    echo "Reduced value (sum of elements): " . $reducedValue . "<br>";
    
    // Using array_keys() to get all keys of an associative array
    $assocArr = ["a" => 1, "b" => 2, "c" => 3];
    echo "Keys of associative array: ";
    print_r(array_keys($assocArr));
    echo "<br>";
    
    // Using array_values() to get all values of an associative array
    echo "Values of associative array: ";
    print_r(array_values($assocArr));
    echo "<br>";
    
    // Using array_merge() to merge two arrays
    $mergedArray = array_merge($arr, [6, 7, 8]);
    echo "Merged array: " . implode(", ", $mergedArray) . "<br>";
    
    // Using array_sort() to sort the array
    sort($arr);
    echo "Sorted array: " . implode(", ", $arr) . "<br>";
    
    // Using in_array() to check if a value exists in an array
    $exists = in_array(3, $arr) ? "Yes" : "No";
    echo "Does 3 exist in the array? $exists<br>";
    
    // Using array_flip() to swap keys and values of an associative array
    $flippedArray = array_flip($assocArr);
    echo "Flipped array (keys and values swapped): ";
    print_r($flippedArray);
    echo "<br>";
    
    // Using array_unique() to remove duplicate values from an array
    $duplicateArray = [1, 2, 2, 3, 4, 4];
    $uniqueArray = array_unique($duplicateArray);
    echo "Unique array (duplicates removed): " . implode(", ", $uniqueArray) . "<br>";

    echo "</div>";
?>