package main.java.objects.transaction;

import main.java.objects.amount.AmountObject;
import main.java.objects.bank.BankObject;

import java.util.Date;
import java.util.List;

public class TransactionFutureSplitObject extends Transaction {
    List<Integer> futureTransactions;

    // todo: need to design how future split transactions work.
    public TransactionFutureSplitObject(int id, TransactionType transactionType, Date date, AmountObject amount,
                                        String note, BankObject primaryBank, boolean isPending,
                                        List<Integer> futureTransactions) {
        super(id, transactionType, date, amount, note, primaryBank, isPending);
        this.futureTransactions = futureTransactions;
    }
}
