package tools;

public class StringHandler {
    String string;

    public StringHandler(String string) {
        this.string = string;
    }

    public boolean isAllNumberDigit() {
        for (int i = 0; i < string.length(); i++) {
            if (!isNumberDigit(string.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public boolean isAllLowerCaseCharacter() {
        for (int i = 0; i < string.length(); i++) {
            if (!isLowerCaseCharacter(string.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    private boolean isNumberDigit(char ch) {
        return 48 <= ch && ch <= 57;
    }

    private boolean isLowerCaseCharacter(char ch) {
        return 97 <= ch && ch <= 122;
    }
}
