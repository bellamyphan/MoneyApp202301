package objects.amount;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

public class AmountObject {
    Locale locale;
    BigDecimal amount;

    public AmountObject(BigDecimal amount) {
        locale = Locale.US;
        this.amount = amount;
    }

    public AmountObject(Locale locale, BigDecimal amount) {
        this.locale = locale;
        this.amount = amount;
    }

    public Locale getLocale() {
        return locale;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return NumberFormat.getCurrencyInstance(locale).format(amount);
    }

    public AmountObject add(AmountObject otherObject) {
        if (locale.equals(otherObject.getLocale())) {
            return new AmountObject(locale, amount.add(otherObject.getAmount()));
        } else {
            // todo: how to convert between currencies?
            return null;
        }
    }
}
