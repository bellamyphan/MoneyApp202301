package application;

import objects.bank.BankObject;
import objects.bank.BanksObject;

public class TestBank {
    public static void main(String[] args) {
        BanksObject banksObject = new BanksObject("data/banks.csv");
        for (BankObject bankObject : banksObject.getBanks()) {
            System.out.println(bankObject.getName());
        }
    }
}
