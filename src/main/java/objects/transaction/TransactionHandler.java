package objects.transaction;

import objects.type.Type;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TransactionHandler {
    List<Transaction> transactions;

    public TransactionHandler(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public List<Transaction> getFilteredTransactions(Type type) {
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

    public List<Transaction> getFilteredTransactions(Type type, String note) {
        List<Transaction> typedTransactions = getFilteredTransactions(type);
        List<Transaction> typedNotedTransactions = new ArrayList<>();
        for (Transaction transaction : typedTransactions) {
            if (transaction.getNote().compareToIgnoreCase(note) == 0) {
                typedNotedTransactions.add(transaction);
            }
        }
        return typedNotedTransactions;
    }

    public List<Transaction> getFilteredTransactions(Date startDate, Date endDate) {
        List<Transaction> filteredTransactions = new ArrayList<>();
        for (Transaction transaction : transactions) {
            if (startDate.compareTo(transaction.getDate()) <= 0 && transaction.getDate().compareTo(endDate) <= 0) {
                filteredTransactions.add(transaction);
            }
        }
        return filteredTransactions;
    }

    public List<Transaction> getFilteredTransactionsUntilDate(Date endDate) {
        List<Transaction> filteredTransactions = new ArrayList<>();
        for (Transaction transaction : transactions) {
            if (endDate.compareTo(transaction.getDate()) >= 0) {
                filteredTransactions.add(transaction);
            }
        }
        return filteredTransactions;
    }

    @Override
    public String toString() {
        StringBuilder finalString = new StringBuilder();
        for (Transaction transaction : transactions) {
            finalString.append(transaction.toString()).append("\n");
        }
        return finalString.substring(0, finalString.length() - 1);
    }
}
