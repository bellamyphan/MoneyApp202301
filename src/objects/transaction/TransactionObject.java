package objects.transaction;

import objects.bank.BankObject;
import objects.Type;
import objects.location.LocationObject;

public class TransactionObject extends Transaction {
    Type type;
    String name;
    LocationObject location;
    BankObject secondaryBank;
}
