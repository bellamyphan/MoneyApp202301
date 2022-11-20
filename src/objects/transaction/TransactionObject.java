package objects.transaction;

import objects.*;
import objects.bank.BankObject;
import objects.Type;

public class TransactionObject extends Transaction {
    Type type;
    String name;
    LocationObject location;
    BankObject secondaryBank;
}
