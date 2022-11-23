package objects.location;

import dao.location.UsCitiesReaderDAO;
import dao.location.UsStatesReaderDAO;
import dao.transaction.TransactionReaderDAO;
import objects.transaction.Transaction;
import objects.transaction.TransactionObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LocationHandler {
    public LocationObject getLocation() {
        LocationObject selectedLocation = guessLocationBasedHistory();
        if (selectedLocation != null)
            return selectedLocation;
        Scanner scanner = new Scanner(System.in);
        UsStatesReaderDAO usStatesReaderDAO = new UsStatesReaderDAO();
        System.out.print("Enter 'cityName, stateCode': ");
        String cityStateString = scanner.nextLine();
        String cityName = getCity(cityStateString);
        String stateCode = getStateCode(cityStateString);
        boolean isValidStateCode = usStatesReaderDAO.isValidStateCode(stateCode);
        boolean isValidCityName = new UsCitiesReaderDAO(stateCode).isValidCity(cityName);
        System.out.println("Valid state code: " + isValidStateCode);
        System.out.println("Valid city name: " + isValidCityName);
        LocationObject location = null;
        if (isValidStateCode && isValidCityName) {
            location = new LocationObject("US", usStatesReaderDAO.getFormalStateCode(stateCode),
                    new UsCitiesReaderDAO(stateCode).getFormalCityName(cityName));
        }
        System.out.println("Confirm location: " + location);
        return location;
    }

    private LocationObject guessLocationBasedHistory() {
        // Get a list of location objects based on most 300 transactions.
        List<Transaction> transactions = new TransactionReaderDAO().getTransactions();
        List<LocationObject> suggestedLocations = new ArrayList<>();
        for (int i = Math.max(0, transactions.size() - 300); i < transactions.size(); i++) {
            Transaction transaction = transactions.get(i);
            if (transaction instanceof TransactionObject) {
                if (!doesLocationListContain(suggestedLocations, ((TransactionObject) transaction).getLocation())) {
                    suggestedLocations.add(((TransactionObject) transaction).getLocation());
                }
            }
        }
        // Pick a location.
        int i;
        for (i = 0; i < suggestedLocations.size(); i++) {
            System.out.println(i + ". " + suggestedLocations.get(i));
        }
        System.out.println(i + ". ENTER NEW CITY, STATE");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Your selection: ");
        int option = scanner.nextInt();
        scanner.nextLine();
        // Confirm the location.
        if (option < suggestedLocations.size()) {
            System.out.println("Confirm location: " + suggestedLocations.get(option));
            return suggestedLocations.get(option);
        }
        return null;
    }

    private String getStateCode(String cityStateString) {
        String[] cells = cityStateString.split(", ");
        return cells[1];
    }

    private String getCity(String cityStateString) {
        String[] cells = cityStateString.split(", ");
        return cells[0];
    }

    private boolean doesLocationListContain(List<LocationObject> list, LocationObject location) {
        for (LocationObject currentLocation : list) {
            if (currentLocation.getCountry().compareToIgnoreCase(location.getCountry()) == 0
                    && currentLocation.getState().compareToIgnoreCase(location.getState()) == 0
                    && currentLocation.getCity().compareToIgnoreCase(location.getCity()) == 0)
                return true;
        }
        return false;
    }
}
