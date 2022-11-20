package objects.transaction;

import objects.bank.BankObject;

import java.util.Date;
import java.util.List;

public class TransactionFutureSplitObject extends Transaction {
    List<Integer> futureTransactions;

    public TransactionFutureSplitObject(int id, TransactionType transactionType, Date date, double amount, String note,
                                        BankObject primaryBank, boolean isPending, List<Integer> futureTransactions) {
        super(id, transactionType, date, amount, note, primaryBank, isPending);
        this.futureTransactions = futureTransactions;
    }
}
