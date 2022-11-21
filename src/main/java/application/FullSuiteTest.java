package main.java.application;

import main.java.objects.bank.BankBalanceHandler;
import main.java.objects.bank.BankDAO;
import main.java.objects.bank.BankObject;
import main.java.objects.transaction.Transaction;
import main.java.objects.transaction.TransactionDAO;

public class FullSuiteTest {

    public static void main(String[] args) {
        // Read all banks.
        BankDAO bankDAO = new BankDAO();
        for (BankObject bankObject : bankDAO.getBanks()) {
            System.out.println(bankObject.toSimpleString());
        }
        // Read all transactions.
        TransactionDAO transactionDAO = new TransactionDAO();
        for (Transaction transaction : transactionDAO.getTransactions()) {
            System.out.println(transaction);
        }
        // Check each bank balance.
        for (BankObject bankObject : bankDAO.getBanks()) {
            System.out.println("Balance of " + bankObject.getName() + ": " + new BankBalanceHandler()
                    .getBalance(bankObject));
        }
    }
}
