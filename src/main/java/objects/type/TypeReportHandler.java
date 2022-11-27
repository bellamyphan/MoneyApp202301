package objects.type;

import dao.transaction.TransactionReaderDAO;
import exception.InvalidYearMonthStringException;
import exception.InvalidYearStringException;
import objects.amount.AmountObject;
import objects.transaction.Transaction;
import objects.transaction.TransactionHandler;
import objects.transaction.TransactionObject;
import tools.DateHandler;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;

public class TypeReportHandler {
    public String getTypeReportFilterByTime(Date start, Date end) {
        // Initialize variables.
        StringBuilder result = new StringBuilder();
        AmountObject overallBalance = new AmountObject(new BigDecimal("0"));
        // Get filtered transactions by time.
        List<Transaction> allTransactions = new TransactionReaderDAO().getTransactions();
        List<Transaction> filteredTransactions;
        if (start != null) {
            filteredTransactions = new TransactionHandler(allTransactions)
                    .getFilteredTransactions(start, end);
        } else {
            filteredTransactions = new TransactionHandler(allTransactions)
                    .getFilteredTransactionsUntilDate(end);
        }
        // Summarize per type and keep track overall balance.
        for (Type type : Type.values()) {
            AmountObject balance = new AmountObject(new BigDecimal("0"));
            ListIterator<Transaction> listIterator = filteredTransactions.listIterator();
            while(listIterator.hasNext()) {
                Transaction transaction = listIterator.next();
                if (transaction instanceof TransactionObject) {
                    if (((TransactionObject) transaction).getType() == type) {
                        balance = balance.add(transaction.getAmount());
                        listIterator.remove();
                    }
                }
            }
            if (balance.getAmount().compareTo(new BigDecimal("0")) == 0) {
                continue;
            }
            overallBalance = overallBalance.add(balance);
            result.append(type.toString()).append(": ").append(balance).append("\n");
        }
        result.append("OVERALL BALANCE: ").append(overallBalance);
        // Return statement.
        return result.toString();
    }

    public String getTypeReportFilterUntilToday() {
        return getTypeReportFilterByTime(null, new Date());
    }

    public String getTypeReportFilterByMonth(String yearMonthString) {
        // Check if the input yearMonthString is valid.
        int stringLength = yearMonthString.length();
        if (stringLength < 6) {
            throw new InvalidYearMonthStringException("Length of the yearMonth string is less than 6");
        }
        // Get the year and month string to construct correct form to use year month string.
        String year = yearMonthString.substring(0, 4);
        String month = yearMonthString.substring(stringLength - 2);
        String formattedYearMonthString = year + "-" + month;
        // Use DateHandler to get the first and last day of this month.
        DateHandler dateHandler = new DateHandler(formattedYearMonthString);
        Date firstDayOfMonth = dateHandler.getFirstDayOfThisMonth();
        Date lastDayOfMonth = dateHandler.getLastDayOfThisMonth();
        // Return the result.
        return getTypeReportFilterByTime(firstDayOfMonth, lastDayOfMonth);
    }

    public String getTypeReportFilterByYear(String yearString) {
        // Check if the input yearString is valid.
        int stringLength = yearString.length();
        if (stringLength < 4) {
            throw new InvalidYearStringException("Length of year string is less than 4");
        }
        // Get the first date.
        Date firstDate = new DateHandler(yearString + "-01").getFirstDayOfThisMonth();
        // Get the last date.
        Date lastDate = new DateHandler(yearString + "-12").getLastDayOfThisMonth();
        // Return the result.
        return getTypeReportFilterByTime(firstDate, lastDate);
    }
}
