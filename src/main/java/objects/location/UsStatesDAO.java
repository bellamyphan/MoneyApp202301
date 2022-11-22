package objects.location;

import tools.DoubleQuoteHandler;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UsStatesDAO {
    final String dataFilePath = "data/usaStatesCities/usStates.csv";
    List<String> stateCodes;
    List<String> stateNames;

    public UsStatesDAO() {
        // Create empty lists.
        stateCodes = new ArrayList<>();
        stateNames = new ArrayList<>();
        // Read data file.
        try (Scanner scanner = new Scanner(new File(dataFilePath))) {
            // Ignore header line.
            scanner.nextLine();
            // Read each data line.
            while (scanner.hasNext()) {
                String currentLine = scanner.nextLine();
                String[] cells = currentLine.split(",");
                stateNames.add(DoubleQuoteHandler.removeDoubleQuote(cells[0]));
                stateCodes.add(DoubleQuoteHandler.removeDoubleQuote(cells[2]));
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isValidStateCode(String stateCode) {
        return stateCodes.contains(stateCode);
    }
}
