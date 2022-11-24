package gui.transaction;

import dao.transaction.TransactionReaderDAO;
import gui.BasicMenu;
import objects.transaction.Transaction;
import objects.transaction.TransactionHandler;
import tools.DateHandler;

import java.util.List;

public class TransactionShowMenu extends BasicMenu {
    @Override
    public void showMenu() {
        System.out.println("Transaction Show Menu");
        System.out.println("1. Show transactions based on date");
        System.out.println("2. Show all transactions for this week");
        System.out.println("3. Show all transactions for this month");
        System.out.println("4. Show all transactions for the last 3 months");
        System.out.println("5. Show all transactions for the last 6 months");
        System.out.println("6. Show all transactions for the last 1 year");
        System.out.println("7. Show all transactions for the input month");
        System.out.println("8. Show all transactions for the input time (startMonth endMonth)");
        System.out.println("9. Show all transactions");
        System.out.println("10. Show all transactions for the selected banks (Primary and Secondary)");
        System.out.println("11. Show all transactions for the selected types");
        System.out.println("12. Show all transactions for the selected notes");
        System.out.println("13. Show all transactions for the selected name");
        System.out.println("14. Show all transactions for the selected locations");
        System.out.println("15. Show all transactions for the tuned amount in USD " +
                "(choose the minimum and maximum amount)");
        System.out.println("0. Exit this menu");
        System.out.print("Select: ");
        option = scanner.nextInt();
        scanner.nextLine();
        System.out.println(guiSupport.shortDashLine());
        switch (option) {
            case 0 -> System.out.println("Exit Transaction Show Menu...");
            case 7 -> showAllTransactionsForInputMonth();
            case 9 -> showAllTransactions();
            default -> System.out.println("This feature is not IMPLEMENTED or INVALID input");
        }
        if (option != 0) System.out.println(guiSupport.longDashLine());
    }

    private void showAllTransactions() {
        System.out.println(new TransactionHandler(new TransactionReaderDAO().getTransactions()));
    }

    private void showAllTransactionsForInputMonth() {
        System.out.println("Enter year month string (yyyyMM or yyyy-mm): ");
        String yearMonthString = scanner.nextLine();
        DateHandler dateHandler = new DateHandler(yearMonthString.substring(0, 4) + "-"
                + yearMonthString.substring(yearMonthString.length() - 2));
        List<Transaction> transactions = new TransactionHandler(new TransactionReaderDAO().getTransactions())
                .getFilteredTransactions(dateHandler.getFirstDayOfThisMonth(), dateHandler.getLastDayOfThisMonth());
        System.out.println(new TransactionHandler(transactions));
    }
}
