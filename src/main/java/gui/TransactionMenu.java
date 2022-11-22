package gui;

import objects.transaction.Transaction;
import objects.transaction.TransactionDAO;

import java.util.List;

public class TransactionMenu extends BasicMenu {
    @Override
    public void showMenu() {
        System.out.println("Transaction Menu");
        System.out.println("1. Add a transaction");
        System.out.println("2. Modify a transaction");
        System.out.println("3. Delete a transaction");
        System.out.println("4. Show transactions based on date");
        System.out.println("5. Show all transactions for this week");
        System.out.println("6. Show all transactions for this month");
        System.out.println("7. Show all transactions for the last 3 months");
        System.out.println("8. Show all transactions for the last 6 months");
        System.out.println("9. Show all transactions for the last 1 year");
        System.out.println("10. Show all transactions");
        System.out.println("11. Show all transactions for the selected banks (Primary and Secondary)");
        System.out.println("12. Show all transactions for the selected types");
        System.out.println("13. Show all transactions for the selected notes");
        System.out.println("14. Show all transactions for the selected name");
        System.out.println("15. Show all transactions for the selected locations");
        System.out.println("16. Show all transactions for the tuned amount in USD " +
                "(choose the minimum and maximum amount)");
        System.out.println("17. Special feature: Get a pool of all transactions and tune them down! " +
                "Based on date, types, banks, name, location...");
        System.out.println("18. Special feature: Mark a transaction as a repeated transaction to reuse in the future.");
        System.out.println("0. Exit this menu");
        System.out.print("Select: ");
        option = scanner.nextInt();
        scanner.nextLine();
        System.out.println(guiSupport.shortDashLine());
        switch (option) {
            case 0 -> System.out.println("Exit Transaction Menu...");
            case 10 -> showAllTransactions();
            default -> System.out.println("This feature is not IMPLEMENTED or INVALID input");
        }
        if (option != 0)
            System.out.println(guiSupport.longDashLine());
    }

    private void showAllTransactions() {
        TransactionDAO transactionDAO = new TransactionDAO();
        List<Transaction> transactions = transactionDAO.getTransactions();
        for (Transaction transaction : transactions) {
            System.out.println(transaction);
        }
    }
}
