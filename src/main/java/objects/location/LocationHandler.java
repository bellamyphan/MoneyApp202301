package objects.location;

import dao.location.UsCitiesReaderDAO;
import dao.location.UsStatesReaderDAO;
import objects.transaction.Transaction;
import objects.transaction.TransactionObject;
import objects.type.Type;
import tools.StringHandler;

import java.util.*;

public class LocationHandler {

    List<Transaction> transactions;
    List<LocationObject> suggestedLocations;
    Scanner scanner;
    String userInput;

    public LocationHandler(List<Transaction> transactions, Type type, String name) {
        this.transactions = new ArrayList<>(transactions);
        Collections.reverse(transactions);
        scanner = new Scanner(System.in);
        suggestedLocations = new ArrayList<>();
        // Get a list of location objects based on most 300 transactions.
        for (int i = Math.max(0, transactions.size() - 300); i < transactions.size(); i++) {
            Transaction transaction = transactions.get(i);
            if (transaction instanceof TransactionObject) {
                if (((TransactionObject) transaction).getType() == type &&
                        ((TransactionObject) transaction).getName().compareToIgnoreCase(name) == 0) {
                    if (!suggestedLocations.contains(((TransactionObject) transaction).getLocation())) {
                        suggestedLocations.add(((TransactionObject) transaction).getLocation());
                    }
                }
            }
        }
    }

    public LocationObject getLocation() {
        LocationObject finalLocation;
        // Try to get location from history.
        finalLocation = getLocationBasedHistory();
        // Get location based on user input.
        if (finalLocation == null)
            finalLocation = getLocationBasedUserInput();
        // Confirm and return statement.
        System.out.println("Confirm location: " + finalLocation);
        return finalLocation;
    }

    private LocationObject getLocationBasedUserInput() {
        UsStatesReaderDAO usStatesReaderDAO = new UsStatesReaderDAO();
        // Get location input from user
        if (userInput == null || userInput.length() == 0) {
            System.out.print("Enter city, stateCode: ");
            userInput = scanner.nextLine();
        }
        String cityName = getCity(userInput);
        String stateCode = getStateCode(userInput);
        boolean isValidStateCode = usStatesReaderDAO.isValidStateCode(stateCode);
        boolean isValidCityName = new UsCitiesReaderDAO(stateCode).isValidCity(cityName);
        System.out.println("Valid state code: " + isValidStateCode);
        System.out.println("Valid city name: " + isValidCityName);
        LocationObject location = null;
        if (isValidStateCode && isValidCityName) {
            location = new LocationObject("US", usStatesReaderDAO.getFormalStateCode(stateCode),
                    new UsCitiesReaderDAO(stateCode).getFormalCityName(cityName));
        }
        return location;
    }

    private LocationObject getLocationBasedHistory() {
        LocationObject finalLocation = null;
        // Skip if there is no suggestion locations.
        if (suggestedLocations.size() == 0) {
            return null;
        }
        // Output suggested locations.
        for (int i = 0; i < suggestedLocations.size(); i++) {
            System.out.println(i + ". " + suggestedLocations.get(i));
        }
        // Get input from user.
        System.out.print("Select a location or enter new one 'city, stateCode': ");
        userInput = scanner.nextLine();
        // Convert to an integer if userInput is an integer.
        int option = -1;
        if (userInput.length() > 0) {
            if (new StringHandler(userInput).isAllNumberDigit()) {
                option = Integer.parseInt(userInput);
            }
        }
        // Get quick name selection from user.
        if (userInput.length() == 0 && suggestedLocations.size() > 0) {
            option = 0;
        }
        // Get suggested location if we get a valid integer input.
        if (0 <= option && option < suggestedLocations.size()) {
            finalLocation = suggestedLocations.get(option);
        }
        // Return statement.
        return finalLocation;
    }

    private String getStateCode(String cityStateString) {
        String[] cells = cityStateString.split(", ");
        return cells[1];
    }

    private String getCity(String cityStateString) {
        String[] cells = cityStateString.split(", ");
        return cells[0];
    }
}
