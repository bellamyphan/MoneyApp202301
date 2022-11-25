package objects.type;

import tools.StringHandler;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class TypeHandler {

    List<Type> typeList;

    public TypeHandler() {
        typeList = Arrays.asList(Type.values());
    }

    public Type selectType() {
        // Initialize variables.
        Type selectedType = null;
        Scanner scanner = new Scanner(System.in);
        // Get input from the user.
        System.out.println("Select or enter type:");
        for (int i = 0; i < typeList.size(); i++) {
            if (i > 0 && i % 8 == 0) {
                System.out.println();
            }
            System.out.print(i + "-" + typeList.get(i) + "\t");
        }
        System.out.print("\nYour input: ");
        String input = scanner.nextLine();
        // Get selected type if input is an integer.
        if (new StringHandler(input).isAllNumberDigit()) {
            int option = Integer.parseInt(input);
            selectedType = typeList.get(option);

        }
        // Get selected type if input is a string.
        if (new StringHandler(input).isAllLowerCaseCharacter()) {
            selectedType = getTypeFromString(input);
            if (selectedType == null) {
                selectedType = getMostConfidentTypeFromString(input);
            }
        }
        // Confirm and return.
        System.out.println("Confirm type: " + selectedType);
        return selectedType;
    }

    private Type getTypeFromString(String inputString) {
        for (Type type : typeList) {
            if (type.toString().compareToIgnoreCase(inputString) == 0) {
                return type;
            }
        }
        return null;
    }

    private double getConfidentScoreFromString(Type type, String string) {
        List<String> typeWords = new StringHandler(type.toString()).getWords();
        List<String> stringWords = new StringHandler(string).getWords();
        int countMatch = 0;
        for (String typeWord : typeWords) {
            for (String stringWord : stringWords) {
                if (typeWord.compareToIgnoreCase(stringWord) == 0) {
                    countMatch++;
                    break;
                }
            }
        }
        return (double) countMatch / typeWords.size();
    }

    private Type getMostConfidentTypeFromString(String inputString) {
        Type mostConfidentType = null;
        double mostConfidentScore = 0.0;
        for (Type type : typeList) {
            double curConfident = getConfidentScoreFromString(type, inputString);
            if (curConfident > mostConfidentScore) {
                mostConfidentScore = curConfident;
                mostConfidentType = type;
            }
        }
        return mostConfidentType;
    }
}
