package objects.transaction;

import objects.type.Type;
import objects.amount.AmountObject;
import objects.bank.BankObject;
import objects.location.LocationObject;
import tools.DateHandler;

import java.util.Date;

public class TransactionObject extends Transaction {
    Type type;
    String name;
    LocationObject location;
    BankObject secondaryBank;

    public TransactionObject(int id, TransactionType transactionType, Date date, AmountObject amount, String note,
                             BankObject primaryBank, boolean isPending, Type type, String name,
                             LocationObject location, BankObject secondaryBank) {
        super(id, transactionType, date, amount, note, primaryBank, isPending);
        this.type = type;
        this.name = name;
        this.location = location;
        this.secondaryBank = secondaryBank;
    }

    public Type getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "id=" + id +
                ", date=" + new DateHandler(date) +
                ", amount=" + amount +
                ", type=" + type +
                ", note='" + note + '\'' +
                ", name='" + name + '\'' +
                ", location=" + location +
                ", primaryBank=" + primaryBank +
                ", secondaryBank=" + secondaryBank +
                ", transactionType=" + transactionType +
                ", isPending=" + isPending;
    }
}
