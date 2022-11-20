package application;

import objects.location.UsCitiesHandler;

import java.util.List;

public class TestTransaction {
    public static void main(String[] args) {
//        TransactionDAO transactionDAO = new TransactionDAO("data/transactions.csv");

        UsCitiesHandler usCitiesHandler = new UsCitiesHandler();
        List<String> cities = usCitiesHandler.getCities("TX");
        for (String city : cities) {
            System.out.println(city);
        }
    }
}
