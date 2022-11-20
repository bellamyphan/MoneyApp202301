package objects.location;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UsCitiesHandler {
    String dataFilePath = "data/usaStatesCities/usCities.csv";

    public List<String> getCities(String stateCode) {
        // Create an empty list.
        List<String> cities = new ArrayList<>();
        // Read data file.
        try (Scanner scanner = new Scanner(new File(dataFilePath))) {
            // Skip header line.
            scanner.nextLine();
            // Read each line.
            while (scanner.hasNext()) {
                String currentLine = scanner.nextLine();
                String cells[] = currentLine.split(",");
                if (cells[2].equals(stateCode)) {
                    cities.add(cells[1]);
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        // Return the cities list.
        return cities;
    }
}
