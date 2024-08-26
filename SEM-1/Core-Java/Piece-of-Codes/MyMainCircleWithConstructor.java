class Circle {
    
    public double x, y;
    public double r;
	
	// Constructor
	public Circle(double centreX, double centreY, double radius) {
		x = centreX;
		y = centreY;
		r = radius;
	}
    
    // Methods to return circumference and area
    public double circumference() {
        return 2 * 3.14* r; 
    }
    
    public double area() {
        return 3.14 * r * r; 
    }
}

public class MyMainCircleWithConstructor {

    public static void main(String[] args) {
        Circle crcle = new Circle(10, 20, 5);
        
        double area = crcle.area();
        double circumf = crcle.circumference();
        
        System.out.println("Radius = " + crcle.r + " Area = " + area);
        System.out.println("Radius = " + crcle.r + " Circumference = " + circumf);
    }
}


