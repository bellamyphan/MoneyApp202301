package dao.location;

import dao.path.DataPath;
import tools.DoubleQuoteHandler;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UsCitiesReaderDAO {

    List<String> cityNames;

    public UsCitiesReaderDAO(String informalStateCode) {
        String formalStateCode = new UsStatesReaderDAO().getFormalStateCode(informalStateCode);
        cityNames = new ArrayList<>();
        // Read data file.
        try (Scanner scanner = new Scanner(new File(DataPath.usCitiesDataPath))) {
            // Skip header line.
            scanner.nextLine();
            // Read each line.
            while (scanner.hasNext()) {
                String currentLine = scanner.nextLine();
                String[] cells = currentLine.split(",");
                if (DoubleQuoteHandler.removeDoubleQuote(cells[2]).equals(formalStateCode)) {
                    cityNames.add(DoubleQuoteHandler.removeDoubleQuote(cells[1]));
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public String getFormalCityName(String cityNameInput) {
        for (String cityName : cityNames) {
            if (cityName.compareToIgnoreCase(cityNameInput) == 0) {
                return cityName;
            }
        }
        return null;
    }

    public boolean isValidCity(String cityNameInput) {
        for (String cityName : cityNames) {
            if (cityName.compareToIgnoreCase(cityNameInput) == 0) {
                return true;
            }
        }
        return false;
    }
}
