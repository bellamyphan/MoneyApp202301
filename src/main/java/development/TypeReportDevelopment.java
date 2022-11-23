package development;

import dao.transaction.TransactionReaderDAO;
import objects.transaction.TransactionHandler;

public class TypeReportDevelopment {
    public static void main(String[] args) {


        System.out.println(new TransactionHandler(new TransactionReaderDAO().getTransactions()));





    }
}
