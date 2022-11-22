package development;

import objects.transaction.Transaction;
import objects.transaction.TransactionGenerator;

public class CreatingNewTransaction {
    public static void main(String[] args) {
        Transaction transaction = new TransactionGenerator().createNewTransaction();
        System.out.println(transaction);
    }
}
