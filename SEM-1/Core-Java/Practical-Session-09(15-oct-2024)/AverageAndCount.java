import java.util.Scanner;

public class AverageAndCount {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of elements: ");
        int n = scanner.nextInt();

        double[] numbers = new double[n];

        System.out.println("Enter " + n + " numbers:");
        for (int i = 0; i < n; i++) {
            numbers[i] = scanner.nextDouble();
        }

        double sum = 0;
        for (int i = 0; i < n; i++) {
            sum += numbers[i];
        }
        double average = sum / n;

        int countGreaterThanAverage = 0;
        for (int i = 0; i < n; i++) {
            if (numbers[i] > average) {
                countGreaterThanAverage++;
            }
        }

        System.out.println("Average: " + average);
        System.out.println("Count of numbers greater than the average: " + countGreaterThanAverage);

        System.out.print("Numbers greater than the average: ");
        for (int i = 0; i < n; i++) {
            if (numbers[i] > average) {
                System.out.print(numbers[i] + " ");
            }
        }

        scanner.close();

    }
}