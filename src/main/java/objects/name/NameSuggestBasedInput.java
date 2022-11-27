package objects.name;

import objects.transaction.Transaction;
import objects.transaction.TransactionObject;
import tools.StringHandler;

import java.util.*;

public class NameSuggestBasedInput {
    String suggestionInput;
    List<String> suggestedNames;
    List<Transaction> transactions;

    public NameSuggestBasedInput(List<Transaction> transactions, String suggestionInput) {
        this.transactions = new ArrayList<>(transactions);
        Collections.reverse(transactions);
        this.suggestionInput = suggestionInput;
        suggestedNames = new LinkedList<>();
        for (Transaction transaction : transactions) {
            if (transaction instanceof TransactionObject) {
                if (((TransactionObject) transaction).getName().toLowerCase().contains(suggestionInput.toLowerCase())) {
                    if (!suggestedNames.contains(((TransactionObject) transaction).getName())) {
                        suggestedNames.add(((TransactionObject) transaction).getName());
                    }
                }
            }
        }
    }

    public String selectName() {
        // Initialize variables.
        String finalName;
        Scanner scanner = new Scanner(System.in);
        String inputString;
        // Output suggested names.
        if (suggestedNames.size() > 0) {
            for (int i = 0; i < suggestedNames.size(); i++) {
                System.out.println(i + ". " + suggestedNames.get(i));
            }
        }
        // Get input from user.
        if (suggestedNames.size() > 0)
            System.out.print("Choose suggested name or enter new Name: ");
        else
            System.out.print("Enter new name: ");
        inputString = scanner.nextLine();
        // Convert to an integer if inputString is an integer.
        int option = -1;
        if (inputString.length() > 0) {
            if (new StringHandler(inputString).isAllNumberDigit()) {
                option = Integer.parseInt(inputString);
            }
        }
        // Get quick selection.
        if (inputString.length() == 0 && suggestedNames.size() > 0)
            option = 0;
        // Get suggested name if we get a valid integer input.
        if (0 <= option && option < suggestedNames.size()) {
            finalName = suggestedNames.get(option);
        }
        // Get the new name not in the suggested list.
        else {
            finalName = inputString;
        }
        // Return statement.
        return finalName;
    }
}
