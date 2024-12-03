import java.util.StringTokenizer;

public class _08_StringTokenizerExample {
    public static void main(String[] args) {
        String sentence = "Java is powerful, flexible, and easy to learn!";

        // Create StringTokenizer with space as delimiter
        StringTokenizer tokenizer = new StringTokenizer(sentence, " ");

        // Loop through tokens
        while (tokenizer.hasMoreTokens()) {
            System.out.println(tokenizer.nextToken());
        }
    }
}
