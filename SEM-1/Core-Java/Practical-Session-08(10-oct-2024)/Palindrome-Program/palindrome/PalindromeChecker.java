package palindrome;

public class PalindromeChecker {
    public static boolean isPalindrome(String str) {
        
        String normalizedStr = str.replaceAll("\\s+", "").toLowerCase();
        int len = normalizedStr.length();
        
        for (int i = 0; i < len / 2; i++) {
            if (normalizedStr.charAt(i) != normalizedStr.charAt(len - 1 - i)) {
                return false;
            }
        }
        return true;
    }
}
