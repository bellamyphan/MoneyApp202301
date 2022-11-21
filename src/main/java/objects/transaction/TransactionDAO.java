package objects.transaction;

import objects.Type;
import objects.amount.AmountObject;
import objects.bank.BankDAO;
import objects.bank.BankObject;
import objects.location.LocationObject;
import objects.location.UsCitiesHandler;
import tools.DateHandler;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class TransactionDAO {
    String dataFilePath = "data/phase1/transactions.csv";
    List<Transaction> transactions;

    public TransactionDAO() {
        // Create empty transactions list.
        transactions = new ArrayList<>();
        // Read the csv data file.
        try (Scanner scanner = new Scanner(new File(dataFilePath))) {
            // Skip header line.
            scanner.nextLine();
            // Read each line.
            while (scanner.hasNext()) {
                String currentLine = scanner.nextLine();
                String[] cells = currentLine.split(",");
                int id = Integer.parseInt(cells[0]);
                TransactionType transactionType = TransactionType.valueOf(cells[1].toUpperCase());
                Date date = new DateHandler(cells[2]).getDate();
                AmountObject amount = new AmountObject(new BigDecimal(cells[3]));
                Type type = Type.valueOf(cells[4].toUpperCase());
                String note = cells[5];
                String name = cells[6];
                LocationObject location = null;
                if (isValidUSALocation(cells[7])) {
                    location = new LocationObject("USA", getCity(cells[7]), getState(cells[7]));
                }
                BankObject primaryBank = new BankDAO().getBankObject(cells[8]);
                BankObject secondaryBank = new BankDAO().getBankObject(cells[9]);
                boolean isPending = Boolean.parseBoolean(cells[10]);
                if (transactionType == TransactionType.NORMAL) {
                    transactions.add(new TransactionObject(id, transactionType, date, amount, note, primaryBank,
                            isPending, type, name, location, secondaryBank));
                } else if (transactionType == TransactionType.FUTURE_SPLIT) {
                    System.out.println("Need to implement Future Split transactions.");
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    private String getCity(String transactionLocation) {
        String cells[] = transactionLocation.split("-");
        return cells[0];
    }

    private String getState(String transactionLocation) {
        String cells[] = transactionLocation.split("-");
        return cells[1];
    }

    private boolean isValidUSALocation(String transactionLocation) {
        if (new UsCitiesHandler().isValidCity(getState(transactionLocation), getCity(transactionLocation)))
            return true;
        else
            return false;
    }
}
