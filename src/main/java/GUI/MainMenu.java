package GUI;

import objects.bank.BankBalanceHandler;
import objects.bank.BankDAO;
import objects.bank.BankObject;
import objects.transaction.Transaction;
import objects.transaction.TransactionDAO;

import java.util.List;
import java.util.Scanner;

public class MainMenu {
    int option;
    Scanner scanner;

    public MainMenu() {
        option = -1;
        scanner = new Scanner(System.in);
    }

    public void run() {
        while (option != 0) {
            showMainMenu();
        }
        scanner.close();
    }

    public void showMainMenu() {
        lineSeparator();
        System.out.println("Main Menu");
        System.out.println("1. Show all transactions");
        System.out.println("2. Show all banks");
        System.out.println("3. Show all bank balances");
        System.out.println("3. Add a bank");
        System.out.println("4. Modify a bank");
        System.out.println("5. Add a transaction");
        System.out.println("6. Modify a transaction");
        System.out.println("7. Delete a transaction");
        System.out.println("0. Exit");
        System.out.print("Select: ");
        option = scanner.nextInt();
        scanner.nextLine();
        switch (option) {
            case 0 -> {
                lineSeparator();
                System.out.println("Closing this application...");
            }
            case 1 -> showAllTransactions();
            case 2 -> showAllBanks();
            case 3 -> showAllBankBalances();
            default -> System.out.println("This feature is not IMPLEMENTED or INVALID input");
        }
    }

    private void showAllTransactions() {
        TransactionDAO transactionDAO = new TransactionDAO();
        List<Transaction> transactions = transactionDAO.getTransactions();
        for (Transaction transaction : transactions) {
            System.out.println(transaction);
        }
    }

    private void showAllBanks() {
        BankDAO bankDAO = new BankDAO();
        for (BankObject bank : bankDAO.getBanks()) {
            System.out.println(bank);
        }
    }

    private void showAllBankBalances() {
        BankDAO bankDAO = new BankDAO();
        for (BankObject bankObject : bankDAO.getBanks()) {
            System.out.println("Balance of " + bankObject.getName() + ": " + new BankBalanceHandler()
                    .getBalance(bankObject));
        }
    }

    private void lineSeparator() {
        System.out.println("---------------------------------------------------");
    }
}
