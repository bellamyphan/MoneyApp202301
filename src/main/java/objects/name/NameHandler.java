package objects.name;

import objects.transaction.Transaction;
import objects.transaction.TransactionDAO;
import objects.transaction.TransactionObject;
import objects.type.Type;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class NameHandler {
    List<String> suggestedNames;

    public NameHandler(Type type, String note) {
        List<Transaction> typedNotedTransactions = new TransactionDAO().getTransactions(type, note);
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
        String finalName;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Select a name:");
        int i;
        for (i = 0; i < suggestedNames.size(); i++) {
            System.out.println(i + ". " + suggestedNames.get(i));
        }
        System.out.println(i + ". ENTER YOUR NEW NAME");
        System.out.print("Your selection: ");
        int option = scanner.nextInt();
        scanner.nextLine();
        if (option < suggestedNames.size()) {
            finalName = suggestedNames.get(option);
            System.out.println("Confirm name: " + finalName);
        } else {
            System.out.print("Enter your new name: ");
            finalName = scanner.nextLine();
        }
        return finalName;
    }
}
