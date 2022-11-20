package objects.bank;

import java.util.Date;

public class BankObject {
    String name;
    Date openDate;
    Date closeDate;
    BankType type;

    public BankObject(String name, Date openDate, Date closeDate, BankType type) {
        this.name = name;
        this.openDate = openDate;
        this.closeDate = closeDate;
        this.type = type;
    }

    @Override
    public String toString() {
        return "BankObject{" +
                "name='" + name + '\'' +
                ", openDate=" + openDate +
                ", closeDate=" + closeDate +
                ", type=" + type +
                '}';
    }
}