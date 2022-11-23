package objects.type;

import dao.transaction.TransactionReaderDAO;
import objects.amount.AmountObject;
import objects.transaction.Transaction;
import objects.transaction.TransactionHandler;
import objects.transaction.TransactionObject;

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
}
