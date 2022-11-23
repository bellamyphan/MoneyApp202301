package gui;

public class MainMenu extends BasicMenu {
    @Override
    public void showMenu() {
        System.out.println("Main Menu");
        System.out.println("1. Transaction menu");
        System.out.println("2. Bank menu");
        System.out.println("3. Report menu");
        System.out.println("4. Export menu");
        System.out.println("5. Investment and Saving menu");
        System.out.println("6. Net Worth menu");
        System.out.println("0. Exit this menu");
        System.out.print("Select: ");
        option = scanner.nextInt();
        scanner.nextLine();
        System.out.println(guiSupport.shortDashLine());
        switch (option) {
            case 0 -> System.out.println("Exit Main Menu...");
            case 1 -> new TransactionMenu().run();
            case 2 -> new BankMenu().run();
            case 3 -> new ReportMenu().run();
            case 4 -> new ExportMenu().run();
            case 5 -> new InvestmentAndSavingMenu().run();
            case 6 -> new NetWorthMenu().run();
            default -> System.out.println("INVALID input");
        }
        System.out.println(guiSupport.longDashLine());
    }
}
