package application;

import objects.location.UsStatesHandler;

public class TestTransaction {
    public static void main(String[] args) {
//        TransactionDAO transactionDAO = new TransactionDAO("data/transactions.csv");

        // Check if a state is valid.
        UsStatesHandler usStatesHandler = new UsStatesHandler();
        System.out.println(usStatesHandler.isValidStateCode("TX"));
    }
}
