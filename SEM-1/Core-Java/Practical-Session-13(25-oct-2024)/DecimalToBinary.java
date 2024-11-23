import java.util.Scanner;

public class DecimalToBinary {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a decimal number: ");
        int decimal = scanner.nextInt();
        scanner.close();

        String binary = convertToBinary(decimal);

        System.out.println("Binary representation of " + decimal + " is: " + binary);

    }

    public static String convertToBinary(int decimal) {
        if (decimal == 0) {
            return "0";
        }

        StringBuilder binary = new StringBuilder();

        while (decimal > 0) {
            int remainder = decimal % 2;
            binary.append(remainder);
            decimal = decimal / 2;
        }

        return binary.reverse().toString();
    }
}