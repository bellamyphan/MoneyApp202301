package objects.location;

import dao.transaction.TransactionReaderDAO;
import objects.amount.AmountObject;
import objects.transaction.Transaction;
import objects.transaction.TransactionHandler;
import objects.transaction.TransactionObject;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;

public class LocationReportHandler {
    public String getLocationReportFilterByTime(Date start, Date end) {
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
        // Get a list of locations from filtered transactions.
        List<LocationObject> locations = new ArrayList<>();
        for (Transaction transaction : filteredTransactions) {
            if (transaction instanceof TransactionObject) {
                LocationObject location = ((TransactionObject) transaction).getLocation();
                if (!locations.contains(location)) {
                    locations.add(location);
                }
            }
        }
        // Summarize per location and keep track overall balance.
        for (LocationObject location : locations) {
            AmountObject balance = new AmountObject(new BigDecimal("0"));
            ListIterator<Transaction> listIterator = filteredTransactions.listIterator();
            while (listIterator.hasNext()) {
                Transaction transaction = listIterator.next();
                if (transaction instanceof TransactionObject) {
                    if (((TransactionObject) transaction).getLocation().equals(location)) {
                        balance = balance.add(transaction.getAmount());
                        listIterator.remove();
                    }
                }
            }
            if (balance.getAmount().compareTo(new BigDecimal("0")) == 0) {
                continue;
            }
            overallBalance = overallBalance.add(balance);
            result.append(location.toString()).append(": ").append(balance).append("\n");
        }
        result.append("OVERALL BALANCE: ").append(overallBalance);
        // Return statement.
        return result.toString();
    }

    public String getLocationPerNameReportFilterByTime(Date start, Date end) {
        // Initialize variables.
        StringBuilder finalStringReport = new StringBuilder();
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
        // Get a list of locations from filtered transactions.
        List<LocationObject> locations = new ArrayList<>();
        for (Transaction transaction : filteredTransactions) {
            if (transaction instanceof TransactionObject) {
                LocationObject location = ((TransactionObject) transaction).getLocation();
                if (!locations.contains(location))
                    locations.add(location);
            }
        }
        // Get a list of names from filtered transactions.
        List<String> names = new ArrayList<>();
        for (Transaction transaction : filteredTransactions) {
            if (transaction instanceof TransactionObject) {
                String name = ((TransactionObject) transaction).getName();
                if (!names.contains(name))
                    names.add(name);
            }
        }
        // Summarize per location per name and keep track overall balance and location balance.
        for (LocationObject location : locations) {
            AmountObject locationBalance = new AmountObject(new BigDecimal("0"));
            StringBuilder locationStringReport = new StringBuilder();
            for (String name : names) {
                AmountObject nameBalance = new AmountObject(new BigDecimal("0"));
                ListIterator<Transaction> listIterator = filteredTransactions.listIterator();
                while (listIterator.hasNext()) {
                    Transaction transaction = listIterator.next();
                    if (transaction instanceof TransactionObject) {
                        if (((TransactionObject) transaction).getLocation().equals(location)) {
                            if (((TransactionObject) transaction).getName().equals(name)) {
                                nameBalance = nameBalance.add(transaction.getAmount());
                                listIterator.remove();
                            }
                        }
                    }
                }
                if (nameBalance.getAmount().compareTo(new BigDecimal("0")) == 0) {
                    continue;
                }
                locationBalance = locationBalance.add(nameBalance);
                locationStringReport.append("\t").append(name).append(": ").append(nameBalance).append("\n");
            }
            finalStringReport.append(location).append(": ").append(locationBalance).append("\n");
            finalStringReport.append(locationStringReport);
            overallBalance = overallBalance.add(locationBalance);
        }
        finalStringReport.append("OVERALL BALANCE: ").append(overallBalance);
        // Return statement.
        return finalStringReport.toString();
    }

    public String getLocationReportFilterUntilToday() {
        return getLocationReportFilterByTime(null, new Date());
    }

    public String getLocationPerNameReportFilterUntilToday() {
        return getLocationPerNameReportFilterByTime(null, new Date());
    }


//    public String getTypeReportFilterByMonth(String yearMonthString) {
//        // Check if the input yearMonthString is valid.
//        int stringLength = yearMonthString.length();
//        if (stringLength < 6) {
//            throw new InvalidYearMonthStringException("Length of the yearMonth string is less than 6");
//        }
//        // Get the year and month string to construct correct form to use year month string.
//        String year = yearMonthString.substring(0, 4);
//        String month = yearMonthString.substring(stringLength - 2);
//        String formattedYearMonthString = year + "-" + month;
//        // Use DateHandler to get the first and last day of this month.
//        DateHandler dateHandler = new DateHandler(formattedYearMonthString);
//        Date firstDayOfMonth = dateHandler.getFirstDayOfThisMonth();
//        Date lastDayOfMonth = dateHandler.getLastDayOfThisMonth();
//        // Return the result.
//        return getTypeReportFilterByTime(firstDayOfMonth, lastDayOfMonth);
//    }
//
//    public String getTypeReportFilterByYear(String yearString) {
//        // Check if the input yearString is valid.
//        int stringLength = yearString.length();
//        if (stringLength < 4) {
//            throw new InvalidYearStringException("Length of year string is less than 4");
//        }
//        // Get the first date.
//        Date firstDate = new DateHandler(yearString + "-01").getFirstDayOfThisMonth();
//        // Get the last date.
//        Date lastDate = new DateHandler(yearString + "-12").getLastDayOfThisMonth();
//        // Return the result.
//        return getTypeReportFilterByTime(firstDate, lastDate);
//    }
}
