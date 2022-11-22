package objects.bank;

import dao.bank.BankReaderDAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BankHandler {
    public BankObject selectPrimaryBank() {
        Scanner scanner = new Scanner(System.in);
        BankObject selectedBank = null;
        List<BankObject> banks = new BankReaderDAO().getActiveBanks();
        if (banks.size() > 0) {
            System.out.println("Select primary bank: ");
            for (int i = 0; i < banks.size(); i++) {
                System.out.println(i + ". " + banks.get(i));
            }
            System.out.print("Your selection: ");
            int option = scanner.nextInt();
            scanner.nextLine();
            selectedBank = banks.get(option);
        } else {
            System.out.println("Warning: No banks listed in the database");
        }
        System.out.println("Confirm primary bank: " + selectedBank);
        return selectedBank;
    }

    public BankObject selectSecondaryBank(BankObject primaryBank) {
        Scanner scanner = new Scanner(System.in);
        BankObject selectedBank = null;
        List<BankObject> banks = new BankReaderDAO().getActiveBanks();
        // Creating banksExcludePrimary
        List<BankObject> banksExcludePrimary = new ArrayList<>();
        for (BankObject bank : banks) {
            if (bank.getName().compareToIgnoreCase(primaryBank.getName()) != 0) {
                banksExcludePrimary.add(bank);
            }
        }

        if (banksExcludePrimary.size() > 0) {
            System.out.println("Select secondary bank: ");
            int i;
            for (i = 0; i < banksExcludePrimary.size(); i++) {
                System.out.println(i + ". " + banksExcludePrimary.get(i));
            }
            System.out.println(i + ". EMPTY");
            System.out.print("Your selection: ");
            int option = scanner.nextInt();
            scanner.nextLine();
            if (option < banksExcludePrimary.size()) {
                selectedBank = banksExcludePrimary.get(option);
            }
        } else {
            System.out.println("Warning: No available banks listed.");
        }
        System.out.println("Confirm secondary bank: " + selectedBank);
        return selectedBank;
    }
}
