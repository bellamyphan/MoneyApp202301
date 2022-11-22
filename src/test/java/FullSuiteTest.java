import objects.bank.BankBalanceHandler;
import objects.bank.BankDAO;
import objects.bank.BankObject;
import objects.transaction.Transaction;
import objects.transaction.TransactionDAO;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class FullSuiteTest {
    @Test
    public void readAllBanks() {
        BankDAO bankDAO = new BankDAO();
        for (BankObject bankObject : bankDAO.getBanks()) {
            System.out.println(bankObject);
        }
        assert bankDAO.getBanks().size() == 2;
    }

    @Test
    public void readALlTransactions() {
        TransactionDAO transactionDAO = new TransactionDAO();
        for (Transaction transaction : transactionDAO.getTransactions()) {
            System.out.println(transaction);
        }
        assert transactionDAO.getTransactions().size() == 6;
    }

    @Test
    public void checkEachBankBalance() {
        BankDAO bankDAO = new BankDAO();
        List<BigDecimal> results = new ArrayList<>();
        results.add(new BigDecimal("700"));
        results.add(new BigDecimal("-210.45"));
        for (BankObject bankObject : bankDAO.getBanks()) {
            System.out.println("Balance of " + bankObject.getName() + ": " + new BankBalanceHandler()
                    .getBalance(bankObject));
        }
        for (int i = 0; i < bankDAO.getBanks().size(); i++) {
            assert new BankBalanceHandler().getBalance(bankDAO.getBanks().get(i)).getAmount()
                    .compareTo(results.get(i)) == 0;
        }
    }
}
