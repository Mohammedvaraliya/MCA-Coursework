<?php

    namespace kJSIM_MCA;

    echo "<h1 style='background-color:red; text-align:center; color:white'> Magic Constant </h1>";
    // Inbuilt constants use to fetch some information about program and code
    // __CONSTANT__ format used to access them with two underscores before and after constant name
    
    echo "<BR><h2> You are at line no: ", __LINE__;
    echo "<BR><h2> You are in directory: ", __DIR__;
    echo "<BR><h2> You are in php file: ", __FILE__;
    echo "<BR><h2> You are in namespace: ", __NAMESPACE__;


    function wish(){
        echo "<BR> you are inside function: ", __FUNCTION__;
    }
    wish();

    class A {
        function show(){
            echo "<BR> You are inside class: ", __CLASS__;
            echo "<BR> You are inside class method: ", __METHOD__;
        }
    }

    $a = new A;
    $a->show();
    
    trait mytrait {
        function trait_method(){
            echo "<BR> Inside trait: ", __TRAIT__;
        }
    }

    class B {
        use mytrait;
    }
    
    $b = new B();
    $b->trait_method();

    // 1. define()
    // 2. const keyword
    // 3. constant() to access constant
    // constant - variable with constant values and no need of using $ sign for accessing them
    // By default case sensitive
    // By default have global scope

    echo "<h1 style='background-color:red; text-align:center; color:white'> php constants and magic constants </h1>";

    define("kjsim","Kj Somaiya Institute of Management");

    const svu = "Somaiya Visdyavihar University";
    const KJSIM = "<h1 style='color:red; text-align:center;'>Somaiya Visdyavihar University </h1>
    <h2 style='color:green; text-align:center;'> kJ Somaiya institute of management </h2>";

    echo "<BR> kjsim: ", kjsim;
    echo "<BR> svu: ", svu;
    echo "<BR>";
    echo constant("kjsim");
?>