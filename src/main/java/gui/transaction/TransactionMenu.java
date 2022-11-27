package gui.transaction;

import gui.BasicMenu;

public class TransactionMenu extends BasicMenu {
    @Override
    public void showMenu() {
        System.out.println("Transaction Menu");
        System.out.println("1. Transaction Basic menu");
        System.out.println("2. Transaction Search menu");
        System.out.println("3. Transaction Show menu");
        System.out.println("0. Exit this menu");
        System.out.print("Select: ");
        option = scanner.nextInt();
        scanner.nextLine();
        System.out.println(guiSupport.shortDashLine());
        switch (option) {
            case 0 -> System.out.println("Exit Transaction Menu...");
            case 1 -> new TransactionBasicMenu().run();
            case 2 -> new TransactionSearchMenu().run();
            case 3 -> new TransactionShowMenu().run();
            default -> System.out.println("This feature is not IMPLEMENTED or INVALID input");
        }
        if (option != 0)
            System.out.println(guiSupport.longDashLine());
    }
}
