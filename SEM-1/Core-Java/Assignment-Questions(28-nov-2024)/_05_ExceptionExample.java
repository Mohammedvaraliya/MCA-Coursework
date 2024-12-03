public class _05_ExceptionExample {
    public static void main(String[] args) {
        try {
            int result = 10 / 0; // Division by zero (throws ArithmeticException)
            System.out.println(result);
        } catch (ArithmeticException e) {
            System.out.println("Error: Cannot divide by zero!"); // Handle the exception
        } finally {
            System.out.println("This block runs regardless of an exception.");
        }
    }
}