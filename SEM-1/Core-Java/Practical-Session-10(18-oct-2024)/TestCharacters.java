class VowelException extends Exception {
    public VowelException(String message) {
        super(message);
    }
}

class BlankException extends Exception {
    public BlankException(String message) {
        super(message);
    }
}

class ExitException extends Exception {
    public ExitException(String message) {
        super(message);
    }
}

public class TestCharacters {

    public static void checkCharacter(char ch) throws VowelException, BlankException, ExitException {

        if (ch == ' ') {
            throw new BlankException("Error: Input is a blank space.");
        }

        if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u' ||
                ch == 'A' || ch == 'E' || ch == 'I' || ch == 'O' || ch == 'U') {
            throw new VowelException("Error: The character is a vowel.");
        }

        if (ch == 'x' || ch == 'X') {
            throw new ExitException("Exiting the program as 'x' or 'X' was entered.");
        }

        System.out.println("Valid Character: " + ch);
    }

    public static void main(String[] args) {
        try {
            if (args.length != 1) {
                System.out.println("Please provide exactly one character as an argument.");
                return;
            }

            char input = args[0].charAt(0);

            checkCharacter(input);

        } catch (VowelException e) {
            System.out.println(e.getMessage());
        } catch (BlankException e) {
            System.out.println(e.getMessage());
        } catch (ExitException e) {
            System.out.println(e.getMessage());
            System.exit(0); // Terminate the program
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }
    }
}