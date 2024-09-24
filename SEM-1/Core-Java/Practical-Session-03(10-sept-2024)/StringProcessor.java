import java.util.Scanner;

public class StringProcessor {
    
    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Enter a string:");
        String input = scanner.nextLine();
        scanner.close();
        
        String result = removeConsecutiveDuplicates(input);
        String countResult = countOccurrences(input);
        
        System.out.println("Processed string: " + result);
        System.out.println("Count result: " + countResult);
    }

    private static String removeConsecutiveDuplicates(String str) {
        if (str == null || str.isEmpty()) {
            return "";
        }
        
        StringBuilder sb = new StringBuilder();
        char lastChar = str.charAt(0);
        sb.append(lastChar);
        
        for (int i = 1; i < str.length(); i++) {
            char currentChar = str.charAt(i);
            if (currentChar != lastChar) {
                sb.append(currentChar);
                lastChar = currentChar;
            }
        }
        
        return sb.toString();
    }
    
    private static String countOccurrences(String str) {
        if (str == null || str.isEmpty()) {
            return "";
        }
        
        StringBuilder sb = new StringBuilder();
        int count = 1;
        
        for (int i = 1; i < str.length(); i++) {
            if (str.charAt(i) == str.charAt(i - 1)) {
                count++;
            } else {
                sb.append(str.charAt(i - 1)).append(count);
                count = 1;
            }
        }
        
        sb.append(str.charAt(str.length() - 1)).append(count);
        
        return sb.toString();
    }
}
