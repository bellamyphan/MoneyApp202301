package objects.location;

import dao.location.UsCitiesReaderDAO;
import dao.location.UsStatesReaderDAO;

import java.util.Scanner;

public class LocationHandler {
    public LocationObject getLocation() {
        Scanner scanner = new Scanner(System.in);
        UsStatesReaderDAO usStatesReaderDAO = new UsStatesReaderDAO();
        System.out.print("Location (Enter 'cityName, stateCode'): ");
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

    private String getStateCode(String cityStateString) {
        String[] cells = cityStateString.split(", ");
        return cells[1];
    }

    private String getCity(String cityStateString) {
        String[] cells = cityStateString.split(", ");
        return cells[0];
    }
}
