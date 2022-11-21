package main.java.objects.location;

import main.java.tools.DoubleQuoteHandler;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UsStatesHandler {
    String dataFilePath = "data/usaStatesCities/usStates.csv";
    List<String> stateCodes;
    List<String> stateNames;

    public UsStatesHandler() {
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
                String cells[] = currentLine.split(",");
                stateNames.add(DoubleQuoteHandler.removeDoubleQuote(cells[0]));
                stateCodes.add(DoubleQuoteHandler.removeDoubleQuote(cells[2]));
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public List<String> getStateCodes() {
        return stateCodes;
    }

    public List<String> getStateNames() {
        return stateNames;
    }

    public boolean isValidStateCode(String stateCode) {
        if (stateCodes.contains(stateCode)) {
            return true;
        } else {
            return false;
        }
    }
}
