class Circle {
    
    public double x, y;
    public double r;
    
    // Methods to return circumference and area
    public double circumference() {
        return 2 * 3.14* r; 
    }
    
    public double area() {
        return 3.14 * r * r; 
    }
}

public class MyMainCircle {

    public static void main(String[] args) {
        Circle crcle = new Circle();
        crcle.x = 10;
        crcle.y = 20;
        crcle.r = 5;
        
        double area = crcle.area();
        double circumf = crcle.circumference();
        
        System.out.println("Radius = " + crcle.r + " Area = " + area);
        System.out.println("Radius = " + crcle.r + " Circumference = " + circumf);
    }
}


