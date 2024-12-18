var app = angular.module("myapp", []);

// The legal restrict values are:
// E for Element name
// A for Attribute
// C for Class
// M for Comment
// By default the value is EA, meaning that both Element names and attribute names can invoke the directive.

app.directive("element", function () {
  return {
    restrict: "E",
    template: "<h1>Element Attribute</h1>",
  };
});
app.directive("attribute", function () {
  return {
    restrict: "A",
    template: "<h1>Attribute Directive</h1>",
  };
});
app.directive("comment", function () {
  return {
    restrict: "M",
    replace: true,
    template: "<h1>Comment Directive</h1>",
  };
});
app.directive("class", function () {
  return {
    restrict: "C",
    replace: true,
    template: "<h1>Class Directive</h1>",
  };
});
