package objects.bank;

import objects.amount.AmountObject;
import objects.transaction.Transaction;
import dao.transaction.TransactionReaderDAO;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class BankBalanceHandler {
    public AmountObject getBalance(BankObject bank) {
        List<Transaction> transactions = new TransactionReaderDAO().getTransactions();
        AmountObject balance = new AmountObject(new BigDecimal("0"));
        for (Transaction transaction : transactions) {
            // Ignore pending transactions.
            if (transaction.isPending()) {
                continue;
            }
            // Ignore future transactions.
            if (transaction.getDate().getTime() > new Date().getTime()) {
                continue;
            }
            // Bank in transaction must match with this bank.
            if (transaction.hasPrimaryBank() && transaction.getPrimaryBank().getName().compareTo(bank.getName()) == 0) {
                balance = balance.add(transaction.getAmount());
            }
        }
        return balance;
    }
}
