package application;

import objects.location.UsCitiesHandler;

public class TestTransaction {
    public static void main(String[] args) {
//        TransactionDAO transactionDAO = new TransactionDAO("data/transactions.csv");

        // Check if a city is valid.
        System.out.println(new UsCitiesHandler().isValidCity("TX", "Dallas"));
    }
}
