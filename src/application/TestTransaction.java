package application;

import objects.location.UsCitiesHandler;
import objects.location.UsStatesHandler;
import objects.transaction.TransactionDAO;
import tools.DoubleQuoteHandler;

import java.util.List;

public class TestTransaction {
    public static void main(String[] args) {
        TransactionDAO transactionDAO = new TransactionDAO("data/transactions.csv");

//        UsStatesHandler usStatesHandler = new UsStatesHandler();
//        for (int i = 0; i < usStatesHandler.getStateNames().size(); i++) {
//            System.out.println(usStatesHandler.getStateCodes().get(i) + ": " + usStatesHandler.getStateNames().get(i));
//        }
//        System.out.println(usStatesHandler.getStateCodes().size());

        System.out.println(DoubleQuoteHandler.hasDoubleQuote("\"BellamyPhan"));
        System.out.println(DoubleQuoteHandler.addDoubleQuote("Bell"));
        System.out.println(DoubleQuoteHandler.removeDoubleQuote("\"Hai\""));

//        UsCitiesHandler usCitiesHandler = new UsCitiesHandler();
//        List<String> cities = usCitiesHandler.getCities("\"TX\"");
//        for (String city : cities) {
//            System.out.println(city);
//        }
    }
}
