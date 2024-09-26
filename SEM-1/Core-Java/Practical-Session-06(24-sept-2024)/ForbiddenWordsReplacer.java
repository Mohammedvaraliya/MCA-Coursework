import java.util.Scanner;

public class ForbiddenWordsReplacer {

    public static String replaceForbiddenWords(String sentence, String[] forbiddenWords) {

        for (String word : forbiddenWords) {

            String replacement = "*".repeat(word.length());

            // (?i): This is a regular expression flag that tells Java to perform a
            // case-insensitive match
            sentence = sentence.replaceAll("(?i)" + word, replacement);
        }
        return sentence;
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter a sentence:");
        String sentence = scanner.nextLine();

        System.out.println("How many forbidden words do you want to enter?");
        int numForbiddenWords = scanner.nextInt();
        scanner.nextLine(); // Consume the leftover newline character

        String[] forbiddenWords = new String[numForbiddenWords];

        for (int i = 0; i < numForbiddenWords; i++) {
            System.out.println("Enter forbidden word " + (i + 1) + ":");
            forbiddenWords[i] = scanner.nextLine();
        }

        String censoredSentence = replaceForbiddenWords(sentence, forbiddenWords);

        System.out.println("Censored Sentence:");
        System.out.println(censoredSentence);

        scanner.close();
    }
}
