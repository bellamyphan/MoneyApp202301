package application;

import objects.transaction.TransactionDAO;

public class TestTransaction {
    public static void main(String[] args) {
        TransactionDAO transactionDAO = new TransactionDAO("data/transactions.csv");
    }
}
