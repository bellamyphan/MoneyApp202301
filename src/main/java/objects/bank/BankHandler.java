package objects.bank;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BankHandler {
    public BankObject selectPrimaryBank() {
        Scanner scanner = new Scanner(System.in);
        List<BankObject> banks = new BankDAO().getActiveBanks();
        System.out.println("Select primary bank: ");
        for (int i = 0; i < banks.size(); i++) {
            System.out.println(i + ". " + banks.get(i));
        }
        System.out.print("Your selection: ");
        int option = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Confirm primary bank: " + banks.get(option));
        return banks.get(option);
    }

    public BankObject selectSecondaryBank(BankObject primaryBank) {
        Scanner scanner = new Scanner(System.in);
        List<BankObject> banks = new BankDAO().getActiveBanks();
        List<BankObject> banksExcludePrimary = new ArrayList<>();
        for (BankObject bank : banks) {
            if (bank.getName().compareToIgnoreCase(primaryBank.getName()) != 0) {
                banksExcludePrimary.add(bank);
            }
        }
        System.out.println("Select secondary bank: ");
        int i;
        for (i = 0; i < banksExcludePrimary.size(); i++) {
            System.out.println(i + ". " + banksExcludePrimary.get(i));
        }
        System.out.println(i + ". EMPTY");
        System.out.print("Your selection: ");
        int option = scanner.nextInt();
        scanner.nextLine();
        BankObject selectedBank = null;
        if (option < banksExcludePrimary.size()) {
            selectedBank = banksExcludePrimary.get(option);
        }
        System.out.println("Confirm secondary bank: " + selectedBank);
        return selectedBank;
    }
}
