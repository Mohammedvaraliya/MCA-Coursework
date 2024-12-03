class MyClass {
    static int counter = 0;

    static void displayCounter() {
        System.out.println("Counter: " + counter);
    }
}

public class _09_StaticKeywordExample {
    public static void main(String[] args) {

        MyClass.counter = 10;
        MyClass.displayCounter();
    }
}