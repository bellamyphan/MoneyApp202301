package application;

import objects.transaction.Transaction;
import objects.transaction.TransactionDAO;

public class TestTransaction {
    public static void main(String[] args) {
        TransactionDAO transactionDAO = new TransactionDAO();
        for (Transaction transaction : transactionDAO.getTransactions()) {
            System.out.println(transaction);
        }
    }
}
