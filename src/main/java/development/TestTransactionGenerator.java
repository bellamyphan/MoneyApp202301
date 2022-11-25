package development;

import objects.amount.AmountHandler;
import objects.type.Type;

public class TestTransactionGenerator {
    public static void main(String[] args) {
        //new TransactionGenerator().createNewTransaction();
        new AmountHandler().getAmountInputFromUser(Type.INCOME_EARN);
    }
}
