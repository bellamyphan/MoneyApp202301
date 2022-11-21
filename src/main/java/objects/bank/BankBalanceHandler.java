package main.java.objects.bank;

import main.java.objects.amount.AmountObject;
import main.java.objects.transaction.Transaction;
import main.java.objects.transaction.TransactionDAO;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class BankBalanceHandler {
    public AmountObject getBalance(BankObject bank) {
        List<Transaction> transactions = new TransactionDAO().getTransactions();
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
