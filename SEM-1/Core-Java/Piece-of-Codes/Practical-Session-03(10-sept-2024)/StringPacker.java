import java.util.Scanner;

public class StringPacker {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Enter a string (up to 20 characters):");
        String input = scanner.nextLine();
        scanner.close();
        
        final int MAX_LENGTH = 20;
        
        if (input.length() > MAX_LENGTH) {
            input = input.substring(0, MAX_LENGTH);
        }
        
        StringBuilder sb = new StringBuilder(input);
        
        while (sb.length() < MAX_LENGTH) {
            sb.append('*');
        }
        
        String paddedString = sb.toString();
        System.out.println("Padded string: " + paddedString);
    }
}
