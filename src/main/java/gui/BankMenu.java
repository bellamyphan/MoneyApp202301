package gui;

import objects.bank.BankBalanceHandler;
import dao.bank.BankReaderDAO;
import objects.bank.BankObject;

public class BankMenu extends BasicMenu {
    @Override
    public void showMenu() {
        System.out.println("Bank Menu");
        System.out.println("1. Show all banks");
        System.out.println("2. Show all bank balances");
        System.out.println("3. Add a bank");
        System.out.println("4. Add bank url");
        System.out.println("5. Modify a bank");
        System.out.println("6. Bank monthly/quarterly statement management");
        System.out.println("0. Exit this menu");
        System.out.print("Select: ");
        option = scanner.nextInt();
        scanner.nextLine();
        System.out.println(guiSupport.shortDashLine());
        switch (option) {
            case 0 -> System.out.println("Exit Bank Menu...");
            case 1 -> showAllBanks();
            case 2 -> showAllBankBalances();
            default -> System.out.println("This feature is not IMPLEMENTED or INVALID input");
        }
        if (option != 0)
            System.out.println(guiSupport.longDashLine());
    }

    private void showAllBanks() {
        BankReaderDAO bankReaderDAO = new BankReaderDAO();
        for (BankObject bank : bankReaderDAO.getBanks()) {
            System.out.println(bank);
        }
    }

    private void showAllBankBalances() {
        BankReaderDAO bankReaderDAO = new BankReaderDAO();
        for (BankObject bankObject : bankReaderDAO.getBanks()) {
            System.out.println("Balance of " + bankObject.getName() + ": " + new BankBalanceHandler()
                    .getBalance(bankObject));
        }
    }
}
