package dao.bank;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import dao.path.DataPath;
import objects.amount.AmountObject;
import objects.bank.BankObject;
import objects.bank.BankType;
import objects.location.LocationObject;
import objects.transaction.TransactionObject;
import objects.transaction.TransactionType;
import objects.type.Type;
import tools.DateHandler;
import tools.DoubleQuoteHandler;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class BankReaderDAO {
    List<BankObject> banks;

    public BankReaderDAO() {
        // Create empty banks list.
        banks = new ArrayList<>();
        // Read the csv data file.
        try (FileReader fileReader = new FileReader(DataPath.banksDataPath);
             CSVReader csvReader = new CSVReader(fileReader)) {
            String[] bankLine;
            boolean skipHeaderLine = true;
            while ((bankLine = csvReader.readNext()) != null) {
                if (skipHeaderLine) {
                    skipHeaderLine = false;
                    continue;
                }
                banks.add(getBankFromBankLine(bankLine));
            }
        } catch (IOException | CsvValidationException e) {
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

    private BankObject getBankFromBankLine(String[] bankLine) {
        String name = DoubleQuoteHandler.removeDoubleQuote(bankLine[0]);
        Date openDate = new DateHandler(DoubleQuoteHandler.removeDoubleQuote(bankLine[1])).getDate();
        // In case close date is null (active bank).
        String closeDateString = DoubleQuoteHandler.removeDoubleQuote(bankLine[2]);
        Date closeDate = null;
        if (closeDateString.length() != 0) {
            closeDate = new DateHandler(closeDateString).getDate();
        }
        BankType type = BankType.valueOf(DoubleQuoteHandler.removeDoubleQuote(bankLine[3]));
        return new BankObject(name, openDate, closeDate, type);
    }
}
