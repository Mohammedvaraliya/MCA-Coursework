var app = angular.module('app', []);

app.controller("sortTable", function ($scope) {
    $scope.studentObjects = [
        { rollno: 101, name: 'Mohammed Varaliya', city: 'Mumbai' },
        { rollno: 102, name: 'Jayesh', city: 'Pune' },
        { rollno: 103, name: 'Ben', city: 'Delhi' },
        { rollno: 104, name: 'Swarali', city: 'Mumbai' },
        { rollno: 105, name: 'vishal', city: 'Mumbai' },
        { rollno: 106, name: 'Ben Stokes', city: 'Christchurch' },
        { rollno: 107, name: 'Virat Kohli', city: 'Delhi' },
        { rollno: 108, name: 'JSP Bumrah', city: 'Gujarat' },
        { rollno: 109, name: 'DSP Siraj', city: 'Heydrabad' },
        { rollno: 110, name: 'Bhuvi', city: 'Mumbai' },
    ];

    $scope.orderByColumn = function(columnName) {
        $scope.studentObjectOrder = columnName;
    }
});