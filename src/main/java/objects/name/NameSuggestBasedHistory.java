package objects.name;

import objects.transaction.Transaction;
import objects.transaction.TransactionHandler;
import objects.transaction.TransactionObject;
import objects.type.Type;
import tools.StringHandler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class NameSuggestBasedHistory {
    List<String> suggestedNames;
    List<Transaction> transactions;

    public NameSuggestBasedHistory(List<Transaction> transactions, Type type, String note) {
        this.transactions = new ArrayList<>(transactions);
        Collections.reverse(transactions);
        List<Transaction> typedNotedTransactions = new TransactionHandler(transactions)
                .getFilteredTransactions(type, note);
        suggestedNames = new ArrayList<>();
        for (Transaction transaction : typedNotedTransactions) {
            if (transaction instanceof TransactionObject) {
                if (!suggestedNames.contains(((TransactionObject) transaction).getName())) {
                    suggestedNames.add(((TransactionObject) transaction).getName());
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
            System.out.println("Select or input a name (Company/Brand):");
            for (int i = 0; i < suggestedNames.size(); i++) {
                System.out.println(i + ". " + suggestedNames.get(i));
            }
        }
        // Get input from user.
        System.out.print("Choose a name or input new name(with suggestion): ");
        inputString = scanner.nextLine();
        // Convert to an integer if inputString is an integer.
        int option = -1;
        if (inputString.length() > 0) {
            if (new StringHandler(inputString).isAllNumberDigit()) {
                option = Integer.parseInt(inputString);
            }
        }
        // Get quick name selection from user.
        if (inputString.length() == 0 && suggestedNames.size() > 0) {
            option = 0;
        }
        // Get suggested name if we get a valid integer input.
        if (0 <= option && option < suggestedNames.size()) {
            finalName = suggestedNames.get(option);
        }
        // Get the new name not in the suggested list.
        else {
            System.out.println("Suggestion for names based on '" + inputString + "':");
            finalName = new NameSuggestBasedInput(transactions, inputString).selectName();
        }
        // Return statement.
        System.out.println("Confirm name (Company/Brand): " + finalName);
        return finalName;
    }
}
