<?php

    // All type of Functions in PHP
    echo "<br> <h1 style = 'background-color: red; color: white; text-align: center'> PHP Functions </h1>";
    echo "<div style='background-color:bisque; margin-left: auto; margin-right:auto; width: 500px; padding: 20px;'>";

        // 1. Zero-parameter Function
        function noParams() {
            return "No parameters";
        }

        echo noParams() . "<br>";

        // 2. Single-parameter Function
        function singleParam($param) {
            return "Parameter: " . $param;
        }

        echo singleParam("test") . "<br>";

        // 3. Multiple-parameter Function
        function doubleParams($param1, $param2) {
            return "Params: $param1, $param2";
        }

        echo doubleParams("first", "second") . "<br>";

        // 4. Function Returning Value
        function returnValue() {
            return 42;
        }

        echo "Return value: " . returnValue() . "<br>";

        // 5. Function Returning Void
        function voidFunction() {
            echo "This function does not return a value.<br>";
        }

        voidFunction();

        // 6. Function with Specific Return Type
        function returnInt(): int {
            return 10;
        }

        echo "Return int: " . returnInt() . "<br>";

        
        function returnString(): string {
            return "Hello";
        }

        echo "Return string: " . returnString() . "<br>";

        // 7. Type-Hinted Function
        function typeHintedFunction(array $arr) {
            return count($arr);
        }

        echo "Array count: " . typeHintedFunction([1, 2, 3]) . "<br>";

        // 8. Variadic Function
        function variadicFunction(...$args) {
            return count($args);
        }

        echo "Variadic count: " . variadicFunction(1, 2, 3, 4) . "<br>";

        // 9. Global Function
        function globalFunction() {
            return "I'm global!";
        }

        echo globalFunction() . "<br>";

        // 10. Static Method
        class MyClass {
            public static function staticMethod() {
                return "I'm a static method!";
            }
        }

        echo MyClass::staticMethod() . "<br>";

        // 11. Anonymous Function (Closure)
        $anonymousFunction = function($name) {
            return "Hello, " . $name;
        };

        echo $anonymousFunction("Mohammed") . "<br>";

        // 12. Callback Function
        function callFunction($callback) {
            return $callback();
        }

        echo callFunction(function() {
            return "This is a callback!";
        }) . "<br>";

        // 13. Recursive Function
        function factorial($n) {
            return $n <= 1 ? 1 : $n * factorial($n - 1);
        }

        echo "Factorial of 5: " . factorial(5) . "<br>";

        // 14. Generator Function
        function simpleGenerator() {
            yield 1;
            yield 2;
            yield 3;
        }

        echo "Generator values: ";
        foreach (simpleGenerator() as $value) {
            echo $value . " ";
        }

        // 15. Magic Method for Method Overloading
        class OverloadExample {
            public function __call($name, $arguments) {
                return "Called $name with " . implode(', ', $arguments);
            }
        }

        $overload = new OverloadExample();
        echo "<br>" . $overload->nonExistentMethod("arg1", "arg2") . "<br>";

    echo "</div>";
?>