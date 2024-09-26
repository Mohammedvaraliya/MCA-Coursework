import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;

public class ParagraphAnalyzer {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter a paragraph (end input with an empty line):");

        StringBuffer paragraphBuffer = new StringBuffer();
        String line;
        while (!(line = scanner.nextLine()).isEmpty()) {
            paragraphBuffer.append(line).append("\n");
        }

        scanner.close();

        String paragraph = paragraphBuffer.toString();

        int numCharacters = paragraph.length();
        int numVowels = countVowels(paragraph);
        int numWords = countWords(paragraph);
        int numLines = countLines(paragraph);
        int numSentences = countSentences(paragraph);

        System.out.println("Paragraph analysis:");
        System.out.println("Total number of characters: " + numCharacters);
        System.out.println("Total number of vowels: " + numVowels);
        printVowelPositions(paragraph);
        System.out.println("Total number of words: " + numWords);
        System.out.println("Total number of lines: " + numLines);
        System.out.println("Total number of sentences: " + numSentences);
    }

    private static int countVowels(String text) {
        int count = 0;
        String vowels = "aeiouAEIOU";
        for (int i = 0; i < text.length(); i++) {
            if (vowels.indexOf(text.charAt(i)) != -1) {
                count++;
            }
        }
        return count;
    }

    private static void printVowelPositions(String text) {
        String vowels = "aeiouAEIOU";
        Map<Character, ArrayList<Integer>> vowelPositions = new HashMap<>();

        for (char vowel : vowels.toCharArray()) {
            vowelPositions.put(vowel, new ArrayList<>());
        }

        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            if (vowelPositions.containsKey(ch)) {
                vowelPositions.get(ch).add(i + 1);
            }
        }

        for (Map.Entry<Character, ArrayList<Integer>> entry : vowelPositions.entrySet()) {
            if (!entry.getValue().isEmpty()) {
                System.out.println("Vowel '" + entry.getKey() + "' at positions: " + entry.getValue());
            }
        }
    }

    private static int countWords(String text) {
        StringTokenizer words = new StringTokenizer(text);
        return words.countTokens();
    }

    private static int countLines(String text) {
        StringTokenizer lines = new StringTokenizer(text, "\n");
        return lines.countTokens();
    }

    private static int countSentences(String text) {
        StringTokenizer sentences = new StringTokenizer(text, ".!?");
        return sentences.countTokens();
    }
}
