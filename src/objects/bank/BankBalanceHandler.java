package objects.bank;

import objects.amount.AmountObject;
import objects.transaction.Transaction;
import objects.transaction.TransactionDAO;

import java.math.BigDecimal;
import java.util.List;

public class BankBalanceHandler {
    public AmountObject getBalance(BankObject bank) {
        List<Transaction> transactions = new TransactionDAO().getTransactions();
        AmountObject balance = new AmountObject(new BigDecimal("0"));
        for (Transaction transaction : transactions) {
            if (transaction.hasPrimaryBank() && transaction.getPrimaryBank().getName().compareTo(bank.getName()) == 0) {
                balance = balance.add(transaction.getAmount());
            }
        }
        return balance;
    }
}
