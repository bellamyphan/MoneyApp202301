package gui;

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


        // Type report for this month.
        // Type report for last month.
        // Type report for the last 3 months (not including this month) on average
        // Type report for x months (start month - end months)
        // Type report for all (excluding future transactions)

        // Name and location report, work the same way just like type.

        // Budget should be dynamic or static for each month? => Budget should be available for the current month only?
        // Use budget to find abnormal transactions in the current month.
        // Budget for this month based on the last 3 months.
        // Budget for this month based on the last 6 months.
        // Select my budget and tuned manually if needed.
        // Monitor my budget.

        // Quick glance?? How to implement this in this program.

        // Total cash I have for each category
        // Checking, savings, brokers

        // Total debt I have based on all credit card balances
        // Car loans, mortgage, student loans...
    }
}
