public class DestructorDemo {

    private String x;

    public DestructorDemo() {
        System.out.println("Object created.");
    }

    public static void main(String[] args) {
        DestructorDemo demo = new DestructorDemo();

        demo.x = "Hello World";

        System.out.println(demo.x);

        // Suggesting the Garbage Collector to run (not guaranteed to run immediately)
        System.gc();

        System.out.println("End of program.");
    }
}
