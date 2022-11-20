package objects.bank;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class BanksObject {
    List<BankObject> banks;

    public BanksObject(String dataFilePath) {
        // Create empty banks list.
        banks = new ArrayList<>();
        // Read the csv data file.
        try (Scanner scanner = new Scanner(new File(dataFilePath))) {
            // Skip header line.
            scanner.nextLine();
            // Read each line.
            while (scanner.hasNext()) {
                String currentLine = scanner.nextLine();
                String[] cells = currentLine.split(",");
                String name = cells[0];
                Date openDate = getDate(cells[1]);
                Date closeDate = getDate(cells[2]);
                BankType type = getBankType(cells[3]);
                banks.add(new BankObject(name, openDate, closeDate, type));
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public List<BankObject> getBanks() {
        return banks;
    }

    private BankType getBankType (String type) {
        if (type.compareToIgnoreCase("checking") == 0) {
            return BankType.CHECKING;
        } else if (type.compareToIgnoreCase("savings") == 0) {
            return BankType.SAVINGS;
        } else if (type.compareToIgnoreCase("creditcard") == 0) {
            return BankType.CREDIT_CARD;
        } else if (type.compareToIgnoreCase("broker") == 0) {
            return BankType.BROKER;
        } else {
            return null;
        }
    }

    private Date getDate (String yyyyMMdd) {
        // If we have an empty date string.
        if (yyyyMMdd.length() == 0) {
            return null;
        }
        // If we have a valid string date format.
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return simpleDateFormat.parse(yyyyMMdd);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
