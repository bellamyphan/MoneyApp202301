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

    public String getName() {
        return name;
    }

    public Date getOpenDate() {
        return openDate;
    }

    public Date getCloseDate() {
        return closeDate;
    }

    public BankType getType() {
        return type;
    }
}
