import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SentenceExtractor {

    private static List<String> extractSentencesContainingWord(String text, String word) {
        List<String> sentencesWithWord = new ArrayList<>();

        String[] sentences = text.split("\\.");

        // Create a regex pattern to match the whole word
        String regex = "\\b" + word + "\\b"; // \b represents word boundaries

        for (String sentence : sentences) {
            String cleanedSentence = sentence.trim();
            if (cleanedSentence.matches(".*" + regex + ".*")) {
                sentencesWithWord.add(cleanedSentence);
            }
        }

        return sentencesWithWord;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("The word is: ");
        String searchWord = sc.next().trim().toLowerCase();

        // Clear the buffer
        sc.nextLine();

        System.out.println("The text is:");
        String text = sc.nextLine().trim();

        List<String> resultSentences = extractSentencesContainingWord(text, searchWord);
        System.out.println("The expected result is:");
        for (String sentence : resultSentences) {
            System.out.println(sentence.trim() + ".");
        }

        sc.close();
    }
}
