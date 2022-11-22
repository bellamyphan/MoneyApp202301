package objects.transaction;

import objects.amount.AmountObject;
import objects.bank.BankObject;

import java.util.Date;

public abstract class Transaction {
    int id;
    TransactionType transactionType;
    Date date;
    AmountObject amount;
    String note;
    BankObject primaryBank;
    boolean isPending;

    public Transaction(int id, TransactionType transactionType, Date date, AmountObject amount,
                       String note, BankObject primaryBank, boolean isPending) {
        this.id = id;
        this.transactionType = transactionType;
        this.date = date;
        this.amount = amount;
        this.note = note;
        this.primaryBank = primaryBank;
        this.isPending = isPending;
    }

    public int getId() {
        return id;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public Date getDate() {
        return date;
    }

    public AmountObject getAmount() {
        return amount;
    }

    public String getNote() {
        return note;
    }

    public BankObject getPrimaryBank() {
        return primaryBank;
    }

    public boolean isPending() {
        return isPending;
    }

    public boolean hasPrimaryBank() {
        return primaryBank != null;
    }
}
