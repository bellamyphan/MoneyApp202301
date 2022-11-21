package objects.transaction;

import objects.Type;
import objects.amount.AmountObject;
import objects.bank.BankObject;
import objects.location.LocationObject;

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

    @Override
    public String toString() {
        return "TransactionObject{" +
                "type=" + type +
                ", name='" + name + '\'' +
                ", location=" + location +
                ", secondaryBank=" + secondaryBank +
                ", id=" + id +
                ", transactionType=" + transactionType +
                ", date=" + date +
                ", amount=" + amount +
                ", note='" + note + '\'' +
                ", primaryBank=" + primaryBank +
                ", isPending=" + isPending +
                '}';
    }
}
