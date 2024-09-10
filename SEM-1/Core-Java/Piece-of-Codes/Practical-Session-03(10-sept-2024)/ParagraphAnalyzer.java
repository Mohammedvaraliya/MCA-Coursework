import java.util.Scanner;

public class ParagraphAnalyzer {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Enter a paragraph (end input with an empty line):");

        StringBuilder paragraphBuilder = new StringBuilder();
        String line;
        while (!(line = scanner.nextLine()).isEmpty()) {
            paragraphBuilder.append(line).append("\n");
        }
        
        scanner.close();

        String paragraph = paragraphBuilder.toString();

        int numCharacters = paragraph.length();
        int numVowels = countVowels(paragraph);
        int numWords = countWords(paragraph);
        int numLines = countLines(paragraph);
        
        System.out.println("Paragraph analysis:");
        System.out.println("Total number of characters: " + numCharacters);
        System.out.println("Total number of vowels: " + numVowels);
        System.out.println("Total number of words: " + numWords);
        System.out.println("Total number of lines: " + numLines);
    }

    private static int countVowels(String text) {
        int count = 0;
        text = text.toLowerCase();
        for (char c : text.toCharArray()) {
            if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
                count++;
            }
        }
        return count;
    }

    private static int countWords(String text) {
        String[] words = text.trim().split("\\s+");
        return words.length;
    }

    private static int countLines(String text) {
        String[] lines = text.split("\n");
        return lines.length;
    }
}
