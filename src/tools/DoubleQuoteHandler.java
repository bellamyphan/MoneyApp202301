package tools;

public class DoubleQuoteHandler {
    public static boolean hasDoubleQuote(String string) {
        if (string.charAt(0) == 34) {
            return true;
        } else {
            return false;
        }
    }

    public static String addDoubleQuote(String string) {
        if (!hasDoubleQuote(string)) {
            return "\"" + string + "\"";
        } else {
            return string;
        }
    }

    public static String removeDoubleQuote(String string) {
        if (hasDoubleQuote(string)) {
            return string.substring(1, string.length()-1);
        } else {
            return string;
        }
    }
}
