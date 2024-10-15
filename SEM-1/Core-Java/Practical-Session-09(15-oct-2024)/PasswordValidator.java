import java.util.Scanner;

public class PasswordValidator {

    public static boolean isValidPassword(String password) {
        if (length(password) < 8) {
            return false;
        }

        int digitCount = 0;

        for (int i = 0; i < length(password); i++) {
            char c = password.charAt(i);
            if (!isLetter(c) && !isDigit(c)) {
                return false; // Invalid character found
            }
            if (isDigit(c)) {
                digitCount++;
            }
        }

        if (digitCount < 2) {
            return false;
        }

        return true;
    }

    public static int length(String str) {
        int len = 0;
        while (str.charAt(len) != '\0') {
            len++;
            if (len >= str.length())
                break;
        }
        return len;
    }

    public static boolean isLetter(char c) {
        return (c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z');
    }

    public static boolean isDigit(char c) {
        return c >= '0' && c <= '9';
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a password: ");
        String password = scanner.nextLine();

        if (isValidPassword(password)) {
            System.out.println("Valid Password");
        } else {
            System.out.println("Invalid Password");
        }

        scanner.close();
    }
}