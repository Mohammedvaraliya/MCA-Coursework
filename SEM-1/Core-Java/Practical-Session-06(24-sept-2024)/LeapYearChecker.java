import java.util.Scanner;

public class LeapYearChecker {

    public static Boolean isLeapYear(int year) {
        // Leap year condition for years after 1582 (Gregorian correction)
        if (year >= 1582) {
            if (year % 400 == 0) {
                return true;
            } else if (year % 100 == 0) {
                return false;
            } else if (year % 4 == 0) {
                return true;
            } else {
                return false;
            }
        } else {
            return false; // Years before 1582 do not follow this rule
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int year;

        System.out.println("Enter a set of years to check for leap years (enter a negative number to stop):");

        while (true) {
            year = scanner.nextInt();
            if (year < 0) {
                break; // Exit if the user enters a negative number
            }

            if (isLeapYear(year)) {
                System.out.println(year + " is a leap year.");
            } else {
                System.out.println(year + " is not a leap year.");
            }
        }

        System.out.println("Program terminated.");
        scanner.close();
    }
}