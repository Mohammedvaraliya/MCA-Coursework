class NotAnAlphabetException extends Exception {
    public NotAnAlphabetException(String message) {
        super(message);
    }
}

class NotARainbowColorException extends Exception {
    public NotARainbowColorException(String message) {
        super(message);
    }
}

class ColorDisplay {

    public void DisplayColor(char ch) throws NotAnAlphabetException, NotARainbowColorException {

        if (!Character.isAlphabetic(ch)) {
            throw new NotAnAlphabetException("Error: The character " + ch + " is not an alphabet.");
        }

        ch = Character.toUpperCase(ch);

        // Check if the character corresponds to a rainbow color
        switch (ch) {
            case 'R':
                System.out.println("Red");
                break;
            case 'O':
                System.out.println("Orange");
                break;
            case 'Y':
                System.out.println("Yellow");
                break;
            case 'G':
                System.out.println("Green");
                break;
            case 'B':
                System.out.println("Blue");
                break;
            case 'I':
                System.out.println("Indigo");
                break;
            case 'V':
                System.out.println("Violet");
                break;
            default:
                throw new NotARainbowColorException(
                        "Error: The alphabet " + ch + " is not associated with any rainbow color.");
        }
    }

    public static void main(String[] args) {
        ColorDisplay colorDisplay = new ColorDisplay();

        try {
            colorDisplay.DisplayColor('B'); // Should print "Blue"
        } catch (NotAnAlphabetException | NotARainbowColorException e) {
            System.out.println(e.getMessage());
        }

        try {
            colorDisplay.DisplayColor('A'); // Should throw NotARainbowColorException
        } catch (NotAnAlphabetException | NotARainbowColorException e) {
            System.out.println(e.getMessage());
        }

        try {
            colorDisplay.DisplayColor('1'); // Should throw NotAnAlphabetException
        } catch (NotAnAlphabetException | NotARainbowColorException e) {
            System.out.println(e.getMessage());
        }
    }
}