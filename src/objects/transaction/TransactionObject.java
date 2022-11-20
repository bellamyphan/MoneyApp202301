package objects.transaction;

import objects.*;
import objects.bank.BankObject;

public class TransactionObject extends Transaction {
    TypeObject type;
    NameObject name;
    LocationObject location;
    BankObject secondaryBank;
}
