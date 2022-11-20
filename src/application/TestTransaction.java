package application;

import objects.location.UsStatesHandler;
import objects.transaction.TransactionDAO;

public class TestTransaction {
    public static void main(String[] args) {
        TransactionDAO transactionDAO = new TransactionDAO("data/transactions.csv");

        UsStatesHandler usStatesHandler = new UsStatesHandler();
        for (int i = 0; i < usStatesHandler.getStateNames().size(); i++) {
            System.out.println(usStatesHandler.getStateCodes().get(i) + ": " + usStatesHandler.getStateNames().get(i));
        }
        System.out.println(usStatesHandler.getStateCodes().size());
    }
}
