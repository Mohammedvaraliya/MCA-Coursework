import java.util.Scanner;

class MaxFinder {
    // Static method to find the maximum of three integers
    public static int findMax(int a, int b, int c) {
        return Math.max(a, Math.max(b, c));
    }

    // Overloaded method to find the maximum from an array of integers
    public static int findMax(int[] numbers) {
        int max = numbers[0];
        for (int num : numbers) {
            if (num > max) {
                max = num;
            }
        }
        return max;
    }
}

public class FindMaxFromSet {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("How many sets: ");
        int numSets = scanner.nextInt();

        for (int i = 1; i <= numSets; i++) {
            System.out.print("Enter the number of values in set " + i + ": ");
            int numValues = scanner.nextInt();

            int[] values = new int[numValues];

            System.out.println("Enter the values for set " + i + ": ");
            for (int j = 0; j < numValues; j++) {
                values[j] = scanner.nextInt();
            }

            System.out.print("Values in set " + i + ": { ");
            for (int val : values) {
                System.out.print(val + " ");
            }
            System.out.println("}");

            int max = MaxFinder.findMax(values);
            System.out.println("Maximum value in set " + i + ": " + max);
            System.out.println("------------------------------------------------");
        }

        scanner.close();
    }
}
