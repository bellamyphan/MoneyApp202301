package objects.transaction;

import objects.bank.BankObject;

import java.util.Date;

public abstract class Transaction {
    int id;
    TransactionType transactionType;
    Date date;
    double amount;
    String note;
    BankObject primaryBank;
    boolean isPending;

    public Transaction(int id, TransactionType transactionType, Date date, double amount,
                       String note, BankObject primaryBank, boolean isPending) {
        this.id = id;
        this.transactionType = transactionType;
        this.date = date;
        this.amount = amount;
        this.note = note;
        this.primaryBank = primaryBank;
        this.isPending = isPending;
    }
}
