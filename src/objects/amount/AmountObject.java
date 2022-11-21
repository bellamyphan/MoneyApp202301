package objects.amount;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Locale;

public class AmountObject {
    Currency currency;
    BigDecimal amount;

    public AmountObject(BigDecimal amount) {
        currency = Currency.getInstance(Locale.US);
        this.amount = amount;
    }

    public AmountObject(Currency currency, BigDecimal amount) {
        this.currency = currency;
        this.amount = amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public BigDecimal getAmount() {
        return amount;
    }
}
