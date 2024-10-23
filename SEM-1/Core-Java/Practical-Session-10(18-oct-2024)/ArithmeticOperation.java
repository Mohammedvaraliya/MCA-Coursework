class IllegalNumberOfArgumentsException extends Exception {
    public IllegalNumberOfArgumentsException(String message) {
        super(message);
    }
}

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

    public static void performOperation(String[] args)
            throws IllegalNumberOfArgumentsException, InvalidOperatorException, NegativeResultException {
        if (args.length != 3) {
            throw new IllegalNumberOfArgumentsException(
                    "Error: Illegal Number of Arguments. Three arguments expected.");
        }

        try {
            int num1 = Integer.parseInt(args[0]);
            char operator = args[1].charAt(0);
            int num2 = Integer.parseInt(args[2]);

            int result = 0;

            switch (operator) {
                case '+':
                    result = num1 + num2;
                    break;
                case '-':
                    result = num1 - num2;
                    break;
                case '*':
                    result = num1 * num2;
                    break;
                case '/':
                    if (num2 == 0) {
                        throw new ArithmeticException("Division by zero is not allowed.");
                    }
                    result = num1 / num2;
                    break;
                default:
                    throw new InvalidOperatorException("Error: Invalid Operator. Only +, -, *, / are allowed.");
            }

            if (result < 0) {
                throw new NegativeResultException("Error: Negative result. Result is " + result);
            }

            System.out.println("Result: " + result);

        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid number format. Please enter valid integers.");
        }
    }

    public static void main(String[] args) {
        try {
            if (args.length != 3) {
                System.out.println("Please provide num1, operator, and num2 as command-line arguments.");
                return;
            }
            performOperation(args);
        } catch (IllegalNumberOfArgumentsException | InvalidOperatorException | NegativeResultException e) {
            System.out.println(e.getMessage());
        }
    }
}