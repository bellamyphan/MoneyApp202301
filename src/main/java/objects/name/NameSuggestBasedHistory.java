package objects.name;

import objects.transaction.Transaction;
import dao.transaction.TransactionReaderDAO;
import objects.transaction.TransactionHandler;
import objects.transaction.TransactionObject;
import objects.type.Type;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class NameSuggestBasedHistory {
    List<String> suggestedNames;

    public NameSuggestBasedHistory(Type type, String note) {
        List<Transaction> typedNotedTransactions = new TransactionHandler(new TransactionReaderDAO().getTransactions())
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
        int option = 0;
        // Output suggested names.
        if (suggestedNames.size() > 0) {
            System.out.println("Select a name (Company/Brand):");
            int i;
            for (i = 0; i < suggestedNames.size(); i++) {
                System.out.println(i + ". " + suggestedNames.get(i));
            }
            System.out.println(i + ". ENTER YOUR NEW NAME (Company/Brand)");
            System.out.print("Your selection: ");
            option = scanner.nextInt();
            scanner.nextLine();
        }
        // Get the suggested name.
        if (option < suggestedNames.size()) {
            finalName = suggestedNames.get(option);
            System.out.println("Confirm name (Company/Brand): " + finalName);
        }
        // Get the new name not in the suggested list.
        else {
            System.out.print("Enter any name (Company/Brand) for suggestion: ");
            String suggestedInput = scanner.nextLine();
            finalName = new NameSuggestBasedInput(suggestedInput).selectName();
        }
        // Return statement.
        return finalName;
    }
}
