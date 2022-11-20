package objects.transaction;

import objects.AmountObject;
import objects.bank.BankObject;
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
