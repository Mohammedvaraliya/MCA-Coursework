import java.util.Scanner;

public class WordCounterAndReplacer {

    private static int countOccurrences(String text, String word) {
        String[] words = text.split("\\s+");
        int count = 0;
        for (String w : words) {
            if (w.equalsIgnoreCase(word)) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the text:");
        String originalText = scanner.nextLine();

        System.out.print("Enter the word to count: ");
        String wordToCount = scanner.next().trim();

        System.out.print("Enter the word to replace it with: ");
        String replacementWord = scanner.next().trim();

        int count = countOccurrences(originalText, wordToCount);
        System.out.println("The word '" + wordToCount + "' occurs " + count + " time(s).");

        String modifiedText = originalText.replaceAll("\\b" + wordToCount + "\\b", replacementWord);
        System.out.println("Modified text: ");
        System.out.println(modifiedText);

        scanner.close();
    }
}
