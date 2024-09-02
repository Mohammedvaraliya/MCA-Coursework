import java.util.Scanner;

// Base Abstract class
abstract class Shape {
    public abstract double surfaceArea();
    public abstract double volume();
}

// Derived class
class Sphere extends Shape {
	private double radius;
	
	public Sphere(double radius) {
		this.radius = radius;
	}
	
	@Override
	public double surfaceArea() {
		return 4 * Math.PI * Math.pow(radius, 2);
	}
	
	@Override
	public double volume() {
		return (4.0 / 3) * Math.PI * Math.pow(radius, 3);
	}
}

// Derived class
class Cube extends Shape {
	private double side;
	
	public Cube(double side) {
		this.side = side;
	}
	
	@Override
	public double surfaceArea() {
		return 6 * Math.pow(side, 2);
	}
	
	@Override
	public double volume() {
		return Math.pow(side, 3);
	}
}

// Derived class
class RectangularPrism extends Shape {
	private double length;
	private double width;
	private double height;
	
	public RectangularPrism(double length, double width, double height) {
		this.length = length;
		this.width = width;
		this.height = height;
	}
	
	@Override
	public double surfaceArea() {
		return 2 * (length * width + width * height + height * length);
	}
	
	@Override
	public double volume() {
		return length * width * height;
	}
}

// Derived class
class RightCircularCylinder extends Shape {
	private double radius;
	private double height;
	
	public RightCircularCylinder(double radius, double height) {
		this.radius = radius;
		this.height = height;
	}
	
	@Override
	public double surfaceArea() {
		return 2 * Math.PI * radius * (radius + height);
	}
	
	@Override
	public double volume() {
		return Math.PI * Math.pow(radius, 2) * height;
	}
}

// Main class
public class ShapeMainWithScanner {
	public static void main(String[] args) {
		
		// Sphere With User input
		Scanner scan = new Scanner(System.in);
		System.out.println("\nEnter the radius value for Sphere:");
		double radius1 = scan.nextDouble();
		Shape sphere = new Sphere(radius1);
		System.out.println("Sphere:");
		System.out.println("Surface Area: " + sphere.surfaceArea());
		System.out.println("Volume: " + sphere.volume());
		
		// Cube With User Input
		System.out.println("\nEnter the side value for Cube:");
		double side = scan.nextDouble();
		Shape cube = new Cube(side);
		System.out.println("\nCube:");
		System.out.println("Surface Area: " + cube.surfaceArea());
		System.out.println("Volume: " + cube.volume());
		
		// Rectangular Prism With User Input
		System.out.println("\nEnter the length value for Rectangular Prism:");
		double length = scan.nextDouble();
		System.out.println("\nEnter the width value for Rectangular Prism:");
		double width = scan.nextDouble();
		System.out.println("\nEnter the hieght value for Rectangular Prism:");
		double height1 = scan.nextDouble();
		Shape rectangularPrism = new RectangularPrism(length, width, height1);
		System.out.println("\nRectangular Prism:");
		System.out.println("Surface Area: " + rectangularPrism.surfaceArea());
		System.out.println("Volume: " + rectangularPrism.volume());
		
		// Right Circular Cylinder With User Input
		System.out.println("\nEnter the radius value for Right Circular Cylinder:");
		double radius2 = scan.nextDouble();
		System.out.println("\nEnter the height value for Right Circular Cylinder:");
		double hieght2 = scan.nextDouble();
		Shape cylinder = new RightCircularCylinder(radius2, hieght2);
		System.out.println("\nRight Circular Cylinder with User Input:");
		System.out.println("Surface Area: " + cylinder.surfaceArea());
		System.out.println("Volume: " + cylinder.volume());
	}
}
