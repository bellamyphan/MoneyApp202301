package tools;

public class CharacterHandler {
    char ch;

    public CharacterHandler(char ch) {
        this.ch = ch;
    }

    public boolean isLowerCase() {
        return 97 <= ch && ch <= 122;
    }

    public boolean isUpperCase() {
        return 65 <= ch && ch <= 90;
    }

    public boolean isAlphabet() {
        return isLowerCase() || isUpperCase();
    }

    public boolean isNumber() {
        return 48 <= ch && ch <= 57;
    }
}
