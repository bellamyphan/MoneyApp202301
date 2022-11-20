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
}
