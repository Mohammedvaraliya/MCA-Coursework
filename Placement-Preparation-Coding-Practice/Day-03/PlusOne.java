import java.util.Scanner;

class PlusOne {

    public int[] plusOne(int[] digits) {
        int n = digits.length;
        for (int i = n - 1; i >= 0; i--) {
            if (digits[i] < 9) {
                digits[i]++;
                return digits;
            }
            digits[i] = 0;
        }
        int[] result = new int[n + 1];
        result[0] = 1;
        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter digits of the number separated by spaces (e.g. 1 2 3):");
        String[] input = scanner.nextLine().split(" ");

        int[] digits = new int[input.length];
        for (int i = 0; i < input.length; i++) {
            digits[i] = Integer.parseInt(input[i]);
        }
        
        PlusOne solution = new PlusOne();
        int[] result = solution.plusOne(digits);
        System.out.print("Result after adding one: ");
        for (int digit : result) {
            System.out.print(digit + " ");
        }
        System.out.println();

        scanner.close();
    }
}