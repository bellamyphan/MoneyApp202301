package application;

import objects.bank.BankObject;
import objects.bank.BankDAO;

public class TestBank {
    public static void main(String[] args) {
        BankDAO bankDAO = new BankDAO();
        for (BankObject bankObject : bankDAO.getBanks()) {
            System.out.println(bankObject.toSimpleString());
        }
    }
}
