package transactionObjects;

import objects.AmountObject;
import objects.BankObject;
import objects.NoteObject;

import java.util.Date;

public abstract class Transaction {
    int id;
    Date date;
    AmountObject amount;
    NoteObject note;
    BankObject primaryBank;
    boolean isPending;
}
