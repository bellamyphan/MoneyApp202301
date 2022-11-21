package objects.bank;

import objects.transaction.Transaction;
import objects.transaction.TransactionDAO;

import java.math.BigDecimal;
import java.util.List;

public class BankBalanceHandler {
    public BigDecimal getBalance(BankObject bank) {
        List<Transaction> transactions = new TransactionDAO().getTransactions();
        BigDecimal balance = new BigDecimal("0");
        for (Transaction transaction : transactions) {
            if (transaction.hasPrimaryBank() && transaction.getPrimaryBank().getName().compareTo(bank.getName()) == 0) {
                balance = balance.add(transaction.getAmount().getAmount());
            }
        }
        return balance;
    }
}
