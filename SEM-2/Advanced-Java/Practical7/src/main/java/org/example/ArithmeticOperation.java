package org.example;

import java.util.Scanner;

class InvalidOperatorException extends Exception {
    public InvalidOperatorException(String message) {
        super(message);
    }
}

class NegativeResultException extends Exception {
    public NegativeResultException(String message) {
        super(message);
    }
}

public class ArithmeticOperation {

    public static void performOperation(int num1, char operator, int num2)
            throws InvalidOperatorException, NegativeResultException {
        int result = 0;

        switch (operator) {
            case '+':
                result = num1 + num2;
                break;
            case '-':
                result = num1 - num2;
                break;
            case 'x':
                result = num1 * num2;
                break;
            case '/':
                if (num2 == 0) {
                    throw new ArithmeticException("Division by zero is not allowed.");
                }
                result = num1 / num2;
                break;
            default:
                throw new InvalidOperatorException("Error: Invalid Operator. Only +, -, x, / are allowed.");
        }

        if (result < 0) {
            throw new NegativeResultException("Error: Negative result. Result is " + result);
        }

        System.out.println("Result: " + result);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Enter the first number: ");
            int num1 = Integer.parseInt(scanner.nextLine());

            System.out.print("Enter the operator (+, -, x, /): ");
            char operator = scanner.nextLine().charAt(0);

            System.out.print("Enter the second number: ");
            int num2 = Integer.parseInt(scanner.nextLine());

            performOperation(num1, operator, num2);
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid number format. Please enter valid integers.");
        } catch (InvalidOperatorException | NegativeResultException | ArithmeticException e) {
            System.out.println(e.getMessage());
        } finally {
            scanner.close();
        }
    }
}