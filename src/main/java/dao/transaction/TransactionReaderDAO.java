package dao.transaction;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import dao.path.DataPath;
import objects.transaction.Transaction;
import objects.transaction.TransactionObject;
import objects.transaction.TransactionType;
import objects.type.Type;
import objects.amount.AmountObject;
import dao.bank.BankReaderDAO;
import objects.bank.BankObject;
import objects.location.LocationObject;
import tools.DateHandler;
import tools.DoubleQuoteHandler;

import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

public class TransactionReaderDAO {
    List<Transaction> transactions;

    public TransactionReaderDAO() {
        // Create empty transactions list.
        transactions = new ArrayList<>();
        // Read the csv data file.
        try (FileReader fileReader = new FileReader(DataPath.transactionsDataPath);
             CSVReader csvReader = new CSVReader(fileReader)) {

            String[] transactionLine;
            boolean skipHeaderLine = true;

            while ((transactionLine = csvReader.readNext()) != null) {
                if (skipHeaderLine) {
                    skipHeaderLine = false;
                    continue;
                }
                transactions.add(getTransactionFromTransactionLine(transactionLine));
            }
        } catch (IOException | CsvValidationException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public int getAutomatedTransactionId() {
        if (transactions.size() == 0) {
            return 0;
        } else {
            return transactions.get(transactions.size()-1).getId() + 1;
        }
    }

    private TransactionObject getTransactionFromTransactionLine(String[] transactionLine) {
        int id = Integer.parseInt(DoubleQuoteHandler.removeDoubleQuote(transactionLine[0]));
        TransactionType transactionType =
                TransactionType.valueOf(DoubleQuoteHandler.removeDoubleQuote(transactionLine[1]));
        Date date = new DateHandler(DoubleQuoteHandler.removeDoubleQuote(transactionLine[2])).getDate();
        AmountObject amount =
                new AmountObject(new BigDecimal(DoubleQuoteHandler.removeDoubleQuote(transactionLine[3])));
        Type type = Type.valueOf(DoubleQuoteHandler.removeDoubleQuote(transactionLine[4]));
        String note = DoubleQuoteHandler.removeDoubleQuote(transactionLine[5]);
        String name = DoubleQuoteHandler.removeDoubleQuote(transactionLine[6]);
        LocationObject location = new LocationObject(DoubleQuoteHandler.removeDoubleQuote(transactionLine[7]));
        BankObject primaryBank = DoubleQuoteHandler.removeDoubleQuote(transactionLine[8]).length() > 0 ?
                new BankReaderDAO().getBankObject(DoubleQuoteHandler.removeDoubleQuote(transactionLine[8])) :
                null;
        BankObject secondaryBank = DoubleQuoteHandler.removeDoubleQuote(transactionLine[9]).length() > 0 ?
                new BankReaderDAO().getBankObject(DoubleQuoteHandler.removeDoubleQuote(transactionLine[9])) :
                null;
        boolean isPending = Boolean.parseBoolean(DoubleQuoteHandler.removeDoubleQuote(transactionLine[10]));
        return new TransactionObject(id, transactionType, date, amount, note, primaryBank, isPending, type,
                name, location, secondaryBank);
    }
}
