package main.java.tools;

public class DoubleQuoteHandler {
    public static String removeDoubleQuote(String string) {
        if (hasDoubleQuote(string)) {
            return string.substring(1, string.length()-1);
        } else {
            return string;
        }
    }

    private static boolean hasDoubleQuote(String string) {
        if (string.charAt(0) == 34) {
            return true;
        } else {
            return false;
        }
    }
}
