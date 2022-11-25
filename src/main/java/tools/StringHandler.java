package tools;

import java.util.Arrays;
import java.util.List;

public class StringHandler {
    String string;

    public StringHandler(String string) {
        this.string = string;
    }

    public List<String> getWords() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < string.length(); i++) {
            if (new CharacterHandler(string.charAt(i)).isAlphabet()) {
                stringBuilder.append(string.charAt(i));
            } else {
                stringBuilder.append(" ");
            }
        }
        String newString = stringBuilder.toString();
        return Arrays.asList(newString.split(" "));
    }

    public boolean isAllNumberDigit() {
        for (int i = 0; i < string.length(); i++) {
            if (!new CharacterHandler(string.charAt(i)).isNumber()) {
                return false;
            }
        }
        return true;
    }

    public boolean isAllLowerCaseCharacter() {
        for (int i = 0; i < string.length(); i++) {
            if (!new CharacterHandler(string.charAt(i)).isLowerCase()) {
                return false;
            }
        }
        return true;
    }




}
