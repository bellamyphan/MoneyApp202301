package objects.location;

import dao.location.UsCitiesReaderDAO;
import dao.location.UsStatesReaderDAO;

import java.util.Scanner;

public class LocationHandler {
    public LocationObject getLocation() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Location city-stateCode: ");
        String cityStateString = scanner.nextLine();
        boolean isValidStateCode = new UsStatesReaderDAO().isValidStateCode(getStateCode(cityStateString));
        boolean isValidCityName = new UsCitiesReaderDAO().isValidCity(
                getStateCode(cityStateString), getCity(cityStateString));
        System.out.println("Valid state code: " + isValidStateCode);
        System.out.println("Valid city name: " + isValidCityName);
        LocationObject location = null;
        if (isValidStateCode && isValidCityName) {
            location = new LocationObject("US", getStateCode(cityStateString), getCity(cityStateString));
        }
        System.out.println("Confirm location: " + location);
        return location;
    }

    private String getStateCode(String cityStateString) {
        String[] cells = cityStateString.split("-");
        return cells[1];
    }

    private String getCity(String cityStateString) {
        String[] cells = cityStateString.split("-");
        return cells[0];
    }
}
