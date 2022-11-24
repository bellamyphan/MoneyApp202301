package gui.report;

import gui.BasicMenu;

public class ReportMenu extends BasicMenu {

    @Override
    public void showMenu() {
        System.out.println("Report Menu");
        System.out.println("1. Type Report menu");
        System.out.println("2. Name (Company/Brand) Report menu");
        System.out.println("3. Location Report menu");
        System.out.println("4. Budget Report menu");
        System.out.println("0. Exit this menu");
        System.out.print("Select: ");
        option = scanner.nextInt();
        scanner.nextLine();
        System.out.println(guiSupport.shortDashLine());
        switch (option) {
            case 0 -> System.out.println("Exit Report Menu...");
            case 1 -> new ReportTypeMenu().run();
            case 2 -> new ReportNameMenu().run();
            case 3 -> new ReportLocationMenu().run();
            case 4 -> new ReportBudgetMenu().run();
            default -> System.out.println("This feature is not IMPLEMENTED or INVALID input");
        }
        if (option != 0)
            System.out.println(guiSupport.longDashLine());


    }
}
