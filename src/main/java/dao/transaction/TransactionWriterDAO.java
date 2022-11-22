package dao.transaction;

public class TransactionWriterDAO {
    String[] headerLine;

    public TransactionWriterDAO() {
        headerLine = new String[]{"Id", "Transaction Type", "Date", "Amount", "Type", "Note", "Name", "Location",
                "Primary Bank", "Secondary Bank", "Is Pending"};
    }
}
