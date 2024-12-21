import java.util.Scanner;

class Fibonacci extends Thread {
    private int n;

    public Fibonacci(int n) {
        this.n = n;
    }

    public void run() {
        System.out.println("Fibonacci Series up to " + n + " terms:");
        if (n > 0) {
            int a = 0, b = 1;
            System.out.println(a);
            if (n > 1) {
                System.out.println(b);
            }
            for (int i = 2; i < n; i++) {
                int next = a + b;
                System.out.println(next);
                a = b;
                b = next;
            }
        } else {
            System.out.println("Please enter a positive number for Fibonacci calculation.");
        }
        System.out.println();
    }
}

class Factorial extends Thread {
    private int n;

    public Factorial(int n) {
        this.n = n;
    }

    public void run() {
        if (n >= 0) {
            long result = 1;
            for (int i = 1; i <= n; i++) {
                result *= i;
            }
            System.out.println("Factorial of " + n + " is: " + result);
        } else {
            System.out.println("Factorial is not defined for negative numbers.");
        }
    }
}

public class FibonacciAndFactorial {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter a number to calculate Fibonacci series and Factorial: ");
        int n = sc.nextInt();
        sc.close();

        if (n < 0) {
            System.out.println("Please enter a positive number.");
            return;
        }

        Thread fibonacciThread = new Fibonacci(n);
        Thread factorialThread = new Factorial(n);

        fibonacciThread.start();
        factorialThread.start();

        try {
            // Ensure both threads finish before printing completion message
            fibonacciThread.join();
            factorialThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Both Fibonacci and Factorial calculations are complete.");
    }
}