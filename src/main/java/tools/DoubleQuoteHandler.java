package tools;

public class DoubleQuoteHandler {
    public static String removeDoubleQuote(String string) {
        if (hasDoubleQuote(string)) {
            return string.substring(1, string.length()-1);
        } else {
            return string;
        }
    }

    private static boolean hasDoubleQuote(String string) {
        if (string.length() < 2) return false;
        return string.charAt(0) == 34;
    }
}
