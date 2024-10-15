<?php
    print("<h1>My first PHP Program</h1>");

    echo("<p>It is running on server using echo</p>");

    Echo "<p>It is running on server using Echo too</p>";

    // Variables in PHP
    // It is Global Variable
    $college = "KJ Somaiya";

    // iliteral String
    echo "<p>My College is $college</p>";

    // literal String
    echo '<p>My College is $college</p>';

    // Get the type of variable
    echo "Var college is of type: ".gettype( $college );

    echo "<BR><BR> Var college is of type: ".var_dump( $college );

    class A{}
    $dp = new A();
    echo"<BR><BR> Type of dp variable: ".gettype( $dp );
    
    // Global Variable x
    $x = 500;

    function show(){
        $y = 100;
        global $x;
        echo "<BR> Global x = ".$x;
        echo "<BR> Global x = ".$GLOBALS["x"];
        echo "<BR> Local x = ".$y;
    }

    show();

    function statScope(){
        global $x;
        echo "<BR> Global x = ".$x;
        echo "<BR> Global x = ".$GLOBALS["x"];

        static $stat = 5;
        echo "<BR> Static var stat == ".$stat;
        $stat++;
    }
    statScope();
    statScope();
    statScope();
    statScope();
    statScope();
?>