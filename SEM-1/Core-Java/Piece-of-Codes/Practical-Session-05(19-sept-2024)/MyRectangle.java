import java.util.*;

class Rectangle {
    static float height;
    static float width;

    static void calArea() {
        System.out.println("Area = " + height * width);
    }
}

public class MyRectangle {
    public static void main(String[] args) {
        // Without creating object
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter height: ");
        Rectangle.height = sc.nextFloat();

        System.out.println("Enter width: ");
        Rectangle.width = sc.nextFloat();

        Rectangle.calArea();
        System.out.println("\n");

        // With Object
        Rectangle r2 = new Rectangle();
        System.out.println("Enter height: ");
        r2.height = sc.nextFloat();

        System.out.println("Enter width: ");
        r2.width = sc.nextFloat();

        r2.calArea();

        sc.close();
    }
}