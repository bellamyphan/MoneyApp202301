package dao.location;

import dao.path.DataPath;
import tools.DoubleQuoteHandler;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UsStatesReaderDAO {
    List<String> stateCodes;

    public UsStatesReaderDAO() {
        stateCodes = new ArrayList<>();
        // Read data file.
        try (Scanner scanner = new Scanner(new File(DataPath.usStatesDataPath))) {
            // Ignore header line.
            scanner.nextLine();
            // Read each data line.
            while (scanner.hasNext()) {
                String currentLine = scanner.nextLine();
                String[] cells = currentLine.split(",");
                stateCodes.add(DoubleQuoteHandler.removeDoubleQuote(cells[2]));
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public String getFormalStateCode(String stateCode) {
        for (String formalStateCode : stateCodes) {
            if (formalStateCode.compareToIgnoreCase(stateCode) == 0) {
                return formalStateCode;
            }
        }
        return null;
    }

    public boolean isValidStateCode(String stateCode) {
        return stateCodes.contains(stateCode.toUpperCase());
    }
}
