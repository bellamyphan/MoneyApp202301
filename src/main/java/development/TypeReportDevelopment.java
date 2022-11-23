package development;

import dao.transaction.TransactionReaderDAO;
import objects.transaction.Transaction;
import objects.transaction.TransactionHandler;
import tools.DateHandler;

import java.util.Date;
import java.util.List;

public class TypeReportDevelopment {
    public static void main(String[] args) {
        List<Transaction> allTransactions = new TransactionReaderDAO().getTransactions();

        Date startDate = new DateHandler("2018-10-10").getDate();
        Date endDate = new DateHandler("2018-10-15").getDate();
        List<Transaction> filteredTransactions = new TransactionHandler(allTransactions)
                .getFilteredTransactions(startDate, endDate);


        System.out.println(new TransactionHandler(filteredTransactions));

    }
}
