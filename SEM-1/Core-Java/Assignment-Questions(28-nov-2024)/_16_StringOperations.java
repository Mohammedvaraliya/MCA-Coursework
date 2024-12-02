public class _16_StringOperations {
    public static void main(String[] args) {
        String str = " Hello, World! ";

        // 1. length()
        System.out.println("Length: " + str.length());

        // 2. charAt()
        System.out.println("Character at index 7: " + str.charAt(8));

        // 3. substring()
        System.out.println("Substring from index 7: " + str.substring(8));

        // 4. toLowerCase()
        System.out.println("Lowercase: " + str.toLowerCase());

        // 5. toUpperCase()
        System.out.println("Uppercase: " + str.toUpperCase());

        // 6. contains()
        System.out.println("Contains 'World': " + str.contains("World"));

        // 7. replace()
        System.out.println("Replace 'World' with 'Java': " + str.replace("World", "Java"));

        // 8. trim()
        System.out.println("Trimmed string: '" + str.trim() + "'");
    }
}