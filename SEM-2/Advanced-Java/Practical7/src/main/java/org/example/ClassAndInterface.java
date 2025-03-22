package org.example;

interface AreaCalculable {
    double calculateArea();
}

class Circle implements AreaCalculable {
    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    public double calculateArea() {
        return Math.PI * Math.pow(radius, 2); // Area of circle = π * r²
    }
}

class Sphere implements AreaCalculable {
    private double radius;

    public Sphere(double radius) {
        this.radius = radius;
    }

    @Override
    public double calculateArea() {
        return 4 * Math.PI * Math.pow(radius, 2);
    }
}

public class ClassAndInterface {
    public static void main(String[] args) {
        AreaCalculable circle = new Circle(5.0);
        AreaCalculable sphere = new Sphere(5.0);

        System.out.println("Area of the circle: " + circle.calculateArea());
        System.out.println("Surface area of the sphere: " + sphere.calculateArea());
    }
}