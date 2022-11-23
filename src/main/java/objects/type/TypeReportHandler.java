package objects.type;

import dao.transaction.TransactionReaderDAO;
import objects.amount.AmountObject;
import objects.transaction.Transaction;
import objects.transaction.TransactionHandler;
import objects.transaction.TransactionObject;
import tools.DateHandler;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class TypeReportHandler {
    public String getTypeReportFilterByTime(Date start, Date end) {
        StringBuilder result = new StringBuilder();
        AmountObject overallBalance = new AmountObject(new BigDecimal("0"));
        List<Transaction> allTransactions = new TransactionReaderDAO().getTransactions();
        List<Transaction> filteredTransactions = new TransactionHandler(allTransactions)
                .getFilteredTransactions(start, end);
        for (Type type : Type.values()) {
            AmountObject balance = new AmountObject(new BigDecimal("0"));
            for (Transaction transaction : filteredTransactions) {
                if (transaction instanceof TransactionObject) {
                    if (((TransactionObject) transaction).getType() == type) {
                        balance = balance.add(transaction.getAmount());
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
        return result.toString();
    }

    public String getTypeReportFilterByMonth(String yearMonthString) {
        // Check if the input yearMonthString is valid.
        int stringLength = yearMonthString.length();
        if (stringLength < 6) {
            // todo: throw invalid input HERE.
            return null;
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
}
