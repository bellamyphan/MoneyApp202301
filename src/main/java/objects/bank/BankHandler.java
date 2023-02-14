package objects.bank;

import dao.bank.BankReaderDAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BankHandler {
    public BankObject selectPrimaryBank() {
        // Initialize variables.
        Scanner scanner = new Scanner(System.in);
        BankObject selectedBank = null;
        // Output bank list.
        List<BankObject> banks = new BankReaderDAO().getActiveBanks();
        if (banks.size() > 0) {
            System.out.println("Select primary bank: ");
            for (int i = 0; i < banks.size(); i++) {
                System.out.println(i + ". " + banks.get(i));
            }
            // Get user's input.
            System.out.print("Your selection: ");
            String input = scanner.nextLine();
            int option;
            if (input.length() > 0) {
                option = Integer.parseInt(input);
            } else {
                option = 0;
            }
            selectedBank = banks.get(option);
        } else {
            System.out.println("Warning: No banks listed in the database");
        }
        // Confirmation and return.
        System.out.println("Confirm primary bank: " + selectedBank);
        return selectedBank;
    }

    public BankObject selectSecondaryBank(BankObject primaryBank) {
        // Initialize variables.
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
        // Output bank list.
        if (banksExcludePrimary.size() > 0) {
            System.out.println("Select secondary bank: ");
            int indexPlusOne = 0;
            System.out.println(indexPlusOne + ". EMPTY");
            for (indexPlusOne = 1; indexPlusOne <= banksExcludePrimary.size(); indexPlusOne++) {
                System.out.println(indexPlusOne + ". " + banksExcludePrimary.get(indexPlusOne - 1));
            }
            // Get user's input.
            System.out.print("Your selection: ");
            String input = scanner.nextLine();
            int option;
            if (input.length() > 0) {
                option = Integer.parseInt(input) - 1;
            } else {
                option = banksExcludePrimary.size();
            }
            if (option < banksExcludePrimary.size()) {
                selectedBank = banksExcludePrimary.get(option);
            }
        } else {
            System.out.println("Warning: No available banks listed.");
        }
        // Confirmation and return.
        System.out.println("Confirm secondary bank: " + selectedBank);
        return selectedBank;
    }
}
