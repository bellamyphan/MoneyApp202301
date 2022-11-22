package dao.transaction;

import com.opencsv.CSVWriter;
import dao.path.DataPath;
import objects.transaction.TransactionObject;
import tools.DateHandler;

import java.io.FileWriter;
import java.io.IOException;

public class TransactionWriterDAO {
    String[] headerLine;

    public TransactionWriterDAO() {
        headerLine = new String[]{"Id", "Transaction Type", "Date", "Amount", "Type", "Note", "Name", "Location",
                "Primary Bank", "Secondary Bank", "Is Pending"};
    }

    public void addATransactionToDatabase(TransactionObject transaction) {
        try (FileWriter fileWriter = new FileWriter(DataPath.transactionsDataPath, true);
             CSVWriter csvWriter = new CSVWriter(fileWriter)) {
            String[] transactionLine = getTransactionLine(transaction);
            csvWriter.writeNext(transactionLine);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String[] getTransactionLine(TransactionObject transaction) {
        String[] transactionLine;
        transactionLine = new String[]{String.valueOf(transaction.getId()),
                transaction.getTransactionType().toString(), new DateHandler(transaction.getDate()).toString(),
                transaction.getAmount().getAmount().toString(), transaction.getType().toString(), transaction.getNote(),
                transaction.getName(), transaction.getLocation().toString(),
                transaction.getPrimaryBank() == null ? "" : transaction.getPrimaryBank().toString(),
                transaction.getSecondaryBank() == null ? "" : transaction.getSecondaryBank().toString(),
                String.valueOf(transaction.isPending())};
        return transactionLine;
    }
}
