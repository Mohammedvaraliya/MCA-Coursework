// Base Abstract class
abstract class Shape {
    public abstract double surfaceArea();
    public abstract double volume();
}

// Derived class for Sphere
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

// Derived class for Cube
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

// Derived class for Rectangular Prism (Box)
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

// Derived class for Right Circular Cylinder
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
public class ShapeMain {
	public static void main(String[] args) {
		
		Shape sphere = new Sphere(5);
		Shape cube = new Cube(3);
		Shape rectangularPrism = new RectangularPrism(4, 5, 6);
		Shape cylinder = new RightCircularCylinder(3, 7);
		
		System.out.println("Sphere:");
		System.out.println("Surface Area: " + sphere.surfaceArea());
		System.out.println("Volume: " + sphere.volume());
		
		System.out.println("\nCube:");
		System.out.println("Surface Area: " + cube.surfaceArea());
		System.out.println("Volume: " + cube.volume());
		
		System.out.println("\nRectangular Prism:");
		System.out.println("Surface Area: " + rectangularPrism.surfaceArea());
		System.out.println("Volume: " + rectangularPrism.volume());
		
		System.out.println("\nRight Circular Cylinder:");
		System.out.println("Surface Area: " + cylinder.surfaceArea());
		System.out.println("Volume: " + cylinder.volume());
	}
}
