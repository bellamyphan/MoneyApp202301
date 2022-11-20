package transactionObjects;

import objects.*;

import java.util.Date;

public class TransactionObject {
    private int id;
    private Date date;
    private AmountObject amount;
    private TypeObject type;
    private NoteObject note;
    private NameObject name;
    private LocationObject location;
    private BankObject primaryBank;
    private BankObject secondaryBank;
    private boolean isPending;
}
