package dao.transaction;

import dao.path.DataPath;
import objects.transaction.Transaction;
import objects.transaction.TransactionObject;
import objects.transaction.TransactionType;
import objects.type.Type;
import objects.amount.AmountObject;
import dao.bank.BankReaderDAO;
import objects.bank.BankObject;
import objects.location.LocationObject;
import dao.location.UsCitiesReaderDAO;
import tools.DateHandler;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.*;

public class TransactionReaderDAO {
    List<Transaction> transactions;

    public TransactionReaderDAO() {
        // Create empty transactions list.
        transactions = new ArrayList<>();
        // Read the csv data file.
        try (Scanner scanner = new Scanner(new File(DataPath.transactionsDataPath))) {
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
                    location = new LocationObject("US", getState(cells[7]), getCity(cells[7]));
                }
                BankObject primaryBank = new BankReaderDAO().getBankObject(cells[8]);
                BankObject secondaryBank = new BankReaderDAO().getBankObject(cells[9]);
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

    public List<Transaction> getTransactions(Type type) {
        List<Transaction> typedTransactions = new ArrayList<>();
        for (Transaction transaction : transactions) {
            if (transaction instanceof TransactionObject) {
                if (((TransactionObject) transaction).getType() == type) {
                    typedTransactions.add(transaction);
                }
            }
        }
        return typedTransactions;
    }

    public List<Transaction> getTransactions(Type type, String note) {
        List<Transaction> typedTransactions = getTransactions(type);
        List<Transaction> typedNotedTransactions = new ArrayList<>();
        for (Transaction transaction : typedTransactions) {
            if (transaction.getNote().compareToIgnoreCase(note) == 0) {
                typedNotedTransactions.add(transaction);
            }
        }
        return typedNotedTransactions;
    }

    public int getAutomatedTransactionId() {
        if (transactions.size() == 0) {
            return 0;
        } else {
            return transactions.get(transactions.size()-1).getId() + 1;
        }
    }

    private String getCity(String transactionLocation) {
        String[] cells = transactionLocation.split("-");
        return cells[0];
    }

    private String getState(String transactionLocation) {
        String[] cells = transactionLocation.split("-");
        return cells[1];
    }

    private boolean isValidUSALocation(String transactionLocation) {
        return new UsCitiesReaderDAO(getState(transactionLocation)).isValidCity(getCity(transactionLocation));
    }
}
