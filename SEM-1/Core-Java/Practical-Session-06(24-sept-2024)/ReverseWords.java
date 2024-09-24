import java.util.*;

public class ReverseWords {

    public static String reverse(String sentence) {

        String[] words = sentence.split(" ");
        // System.out.println("Array of words: " + Arrays.toString(words));

        StringBuilder reversedSentence = new StringBuilder();

        for (int i = words.length - 1; i >= 0; i--) {
            reversedSentence.append(words[i]).append(" ");
        }

        return reversedSentence.toString().trim();
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter a sentence:");
        String sentence = scanner.nextLine();

        String reversedSentence = reverse(sentence);

        System.out.println("Your sentence in backwards:");
        System.out.println(reversedSentence);

        scanner.close();
    }
}