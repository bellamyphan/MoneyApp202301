package objects.transaction;

import objects.type.Type;

import java.util.ArrayList;
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


}
