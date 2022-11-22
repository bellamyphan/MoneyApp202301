import objects.bank.BankBalanceHandler;
import dao.bank.BankReaderDAO;
import objects.bank.BankObject;
import objects.transaction.Transaction;
import dao.transaction.TransactionReaderDAO;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class FullSuiteTest {
    @Test
    public void readAllBanks() {
        BankReaderDAO bankReaderDAO = new BankReaderDAO();
        for (BankObject bankObject : bankReaderDAO.getBanks()) {
            System.out.println(bankObject);
        }
        assert bankReaderDAO.getBanks().size() == 2;
    }

    @Test
    public void readALlTransactions() {
        TransactionReaderDAO transactionReaderDAO = new TransactionReaderDAO();
        for (Transaction transaction : transactionReaderDAO.getTransactions()) {
            System.out.println(transaction);
        }
        assert transactionReaderDAO.getTransactions().size() == 6;
    }

    @Test
    public void checkEachBankBalance() {
        BankReaderDAO bankReaderDAO = new BankReaderDAO();
        List<BigDecimal> results = new ArrayList<>();
        results.add(new BigDecimal("700"));
        results.add(new BigDecimal("-210.45"));
        for (BankObject bankObject : bankReaderDAO.getBanks()) {
            System.out.println("Balance of " + bankObject.getName() + ": " + new BankBalanceHandler()
                    .getBalance(bankObject));
        }
        for (int i = 0; i < bankReaderDAO.getBanks().size(); i++) {
            assert new BankBalanceHandler().getBalance(bankReaderDAO.getBanks().get(i)).getAmount()
                    .compareTo(results.get(i)) == 0;
        }
    }
}
