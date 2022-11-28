package development;

import dao.transaction.TransactionReaderDAO;
import objects.location.LocationReportHandler;
import objects.transaction.Transaction;
import objects.type.TypeReportHandler;

import java.util.List;

public class TestLocationReportHandler {
    public static void main(String[] args) {

        List<Transaction> transactions = new TransactionReaderDAO().getTransactions();

        System.out.println(new LocationReportHandler(transactions).getLocationReportFilterUntilToday());
        System.out.println();

        System.out.println(new TypeReportHandler(new TransactionReaderDAO().getTransactions())
                .getTypeReportFilterUntilToday());
        System.out.println();

        System.out.println(new LocationReportHandler(transactions).getLocationPerNameReportFilterUntilToday());
    }
}
