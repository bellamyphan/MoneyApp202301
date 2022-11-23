package objects.name;

import dao.transaction.TransactionReaderDAO;
import objects.transaction.Transaction;
import objects.transaction.TransactionObject;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class NameSuggestBasedInput {
    String suggestionInput;
    List<String> suggestedNames;

    public NameSuggestBasedInput(String suggestionInput) {
        this.suggestionInput = suggestionInput;
        List<Transaction> transactions = new TransactionReaderDAO().getTransactions();
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
        String finalName;
        Scanner scanner = new Scanner(System.in);
        int option = 0;
        if (suggestedNames.size() > 0) {
            System.out.println("Select a name (company/brand):");
            int i;
            for (i = 0; i < suggestedNames.size(); i++) {
                System.out.println(i + ". " + suggestedNames.get(i));
            }
            System.out.println(i + ". ENTER YOUR NEW NAME (COMPANY/BRAND)");
            System.out.print("Your selection: ");
            option = scanner.nextInt();
            scanner.nextLine();
        }
        if (option < suggestedNames.size()) {
            finalName = suggestedNames.get(option);
            System.out.println("Confirm name (company/brand): " + finalName);
        } else {
            System.out.print("Enter your new name (company/brand): ");
            finalName = scanner.nextLine();
        }
        return finalName;
    }
}
