import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class WordSorter {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter a list of words separated by spaces:");
        String input = scanner.nextLine();
        scanner.close();

        String[] wordsArray = input.split("\\s+");

        List<String> wordsList = Arrays.asList(wordsArray);

        Collections.sort(wordsList);

        System.out.println("Words in alphabetical order:");
        for (String word : wordsList) {
            System.out.print(word + " ");
        }
        System.out.println();

        Collections.reverse(wordsList);

        System.out.println("Words in reverse alphabetical order:");
        for (String word : wordsList) {
            System.out.print(word + " ");
        }
        System.out.println();
    }
}
