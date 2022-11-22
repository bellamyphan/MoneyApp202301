package objects.bank;

import tools.DateHandler;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class BankDAO {
    String dataFilePath = "data/production/banks.csv";
    List<BankObject> banks;

    public BankDAO() {
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
                Date openDate = new DateHandler(cells[1]).getDate();
                Date closeDate = new DateHandler(cells[2]).getDate();
                BankType type = BankType.valueOf(cells[3].toUpperCase());
                banks.add(new BankObject(name, openDate, closeDate, type));
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public List<BankObject> getBanks() {
        return banks;
    }

    public List<BankObject> getActiveBanks() {
        List<BankObject> activeBanks = new ArrayList<>();
        for (BankObject bank : banks) {
            if (bank.isActive()) {
                activeBanks.add(bank);
            }
        }
        return activeBanks;
    }

    public BankObject getBankObject(String bankName) {
        for (BankObject bankObject : banks) {
            if (bankObject.getName().compareTo(bankName) == 0) {
                return bankObject;
            }
        }
        return null;
    }
}
