var app = angular.module("myapp", []);

app.filter("caplist", function () {
  return function (x) {
    return x.toUpperCase();
  };
});

app.directive("kjsim", function () {
  return {
    // The legal restrict values are:
    // E for Element name
    // A for Attribute
    // C for Class
    // M for Comment
    // By default the value is EA, meaning that both Element names and attribute names can invoke the directive.
    restrict : "C",
    template: "<h1 style='background-color: orange;'> KJ Somaiya Institute of Management </h1>"
  };
});

app.directive("mycomment", function () {
  return  {
      restrict: "E", 
      replace: true,
      template: "<i>This is my custom directive</i>"
  };
});

app.controller("controller", function ($scope) {
  $scope.subjects = ["webtech", "android", "pyhton"];
});