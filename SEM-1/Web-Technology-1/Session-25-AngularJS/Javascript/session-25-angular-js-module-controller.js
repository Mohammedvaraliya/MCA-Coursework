var app = angular.module('app', []);

app.controller("simpleControllerFunc", function ($scope) {
    $scope.message = "Hello Everyone!!";
    $scope.studentObjects = [
        { rollno: 101, name: 'Mohammed Varaliya' },
        { rollno: 102, name: 'Jayesh' },
        { rollno: 103, name: 'Ben' }
    ];
});

app.controller("simpleInputFunc", function ($scope) {
    $scope.username = "";
    $scope.email = "";
    $scope.password = "";
    $scope.result = "";
    $scope.message = "";

    $scope.check = function () {
        if ($scope.username === "Mohammed Varaliya" && $scope.email === "abc@somaiya.edu" && $scope.password === "123") {
            $scope.result = "Login Successful with Username: " + $scope.username + ", Email: " + $scope.email;
            $scope.message = "";
        } else if (!$scope.username || !$scope.email || !$scope.password) {
            $scope.result = "";
            $scope.message = "Please enter all credentials first.";
        } else {
            $scope.result = "";
            $scope.message = "Login failed with Username: " + $scope.username + ", Email: " + $scope.email;
        }
    };
});
