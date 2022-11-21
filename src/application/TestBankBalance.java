package application;

import objects.bank.BankBalanceHandler;
import objects.bank.BankDAO;

public class TestBankBalance {
    public static void main(String[] args) {
        System.out.println(new BankBalanceHandler()
                .getBalance(new BankDAO().getBankObject("Chase Checking")));
        System.out.println(new BankBalanceHandler()
                .getBalance(new BankDAO().getBankObject("Chase Freedom Unlimited")));
    }
}
