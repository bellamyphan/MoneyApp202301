package main.java.development;

import main.java.objects.bank.BankBalanceHandler;
import main.java.objects.bank.BankDAO;

public class TestBankBalance {
    public static void main(String[] args) {
        System.out.println(new BankBalanceHandler()
                .getBalance(new BankDAO().getBankObject("Chase Checking")));
        System.out.println(new BankBalanceHandler()
                .getBalance(new BankDAO().getBankObject("Chase Freedom Unlimited")));
    }
}
