package gui.transaction;

import dao.transaction.TransactionWriterDAO;
import gui.BasicMenu;
import objects.transaction.TransactionGenerator;
import objects.transaction.TransactionObject;

public class TransactionBasicMenu extends BasicMenu {
    @Override
    public void showMenu() {
        System.out.println("Transaction Basic Menu");
        System.out.println("1. Add a transaction");
        System.out.println("2. Modify a transaction");
        System.out.println("3. Delete a transaction");
        System.out.println("4. Special feature: Mark a transaction as a repeated transaction to reuse in the future.");
        System.out.println("0. Exit this menu");
        System.out.print("Select: ");
        option = scanner.nextInt();
        scanner.nextLine();
        System.out.println(guiSupport.shortDashLine());
        switch (option) {
            case 0 -> System.out.println("Exit Transaction Basic Menu...");
            case 1 -> addATransaction();
            default -> System.out.println("This feature is not IMPLEMENTED or INVALID input");
        }
        if (option != 0)
            System.out.println(guiSupport.longDashLine());
    }

    private void addATransaction() {
        TransactionObject transactionObject = new TransactionGenerator().createNewTransaction();
        if (transactionObject != null) {
            new TransactionWriterDAO().addATransactionToDatabase(transactionObject);
        }
    }
}
