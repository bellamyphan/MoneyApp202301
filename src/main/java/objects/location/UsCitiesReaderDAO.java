package objects.location;

import dao.DataPath;
import tools.DoubleQuoteHandler;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UsCitiesReaderDAO {
    public List<String> getCities(String stateCode) {
        // Create an empty list.
        List<String> cities = new ArrayList<>();
        // Read data file.
        try (Scanner scanner = new Scanner(new File(DataPath.usCitiesDataPath))) {
            // Skip header line.
            scanner.nextLine();
            // Read each line.
            while (scanner.hasNext()) {
                String currentLine = scanner.nextLine();
                String[] cells = currentLine.split(",");
                if (DoubleQuoteHandler.removeDoubleQuote(cells[2]).equals(stateCode)) {
                    cities.add(DoubleQuoteHandler.removeDoubleQuote(cells[1]));
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        // Return the cities list.
        return cities;
    }

    public boolean isValidCity(String stateCode, String city) {
        if (new UsStatesReaderDAO().isValidStateCode(stateCode))
            return getCities(stateCode).contains(city);
        return false;
    }
}
