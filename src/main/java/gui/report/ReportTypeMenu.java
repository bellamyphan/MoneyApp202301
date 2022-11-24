package gui.report;

import gui.BasicMenu;
import objects.type.TypeReportHandler;

public class ReportTypeMenu extends BasicMenu {
    @Override
    public void showMenu() {
        System.out.println("Type Report Menu");
        System.out.println("1. Type report for this month");
        System.out.println("2. Type report for last month");
        System.out.println("3. Type report for an input month");
        System.out.println("4. Type report for an input year");
        System.out.println("5. Type report for last 'N' months (excluding this month) - Total");
        System.out.println("6. Type report for last 'N' months (excluding this month) - Average");
        System.out.println("7. Type report for 'N' month with StartMonth and EndMonth - Total");
        System.out.println("8. Type report for 'N' month with StartMonth and EndMonth - Average");
        System.out.println("9. Type report for all transactions - Until today");
        System.out.println("10. Type report for all transactions - Including future");
        // TODO: How to implement quick glance???
        System.out.println("0. Exit this menu");
        System.out.print("Select: ");
        option = scanner.nextInt();
        scanner.nextLine();
        System.out.println(guiSupport.shortDashLine());
        switch (option) {
            case 0 -> System.out.println("Exit Type Report Menu...");
            case 3 -> typeReportForInputMonth();
            case 4 -> typeReportForInputYear();
            default -> System.out.println("This feature is not IMPLEMENTED or INVALID input");
        }
        if (option != 0)
            System.out.println(guiSupport.longDashLine());
    }

    private void typeReportForInputMonth() {
        System.out.print("Enter YyyyMm: ");
        String yearMonthString = scanner.nextLine();
        System.out.println(new TypeReportHandler().getTypeReportFilterByMonth(yearMonthString));
    }

    private void typeReportForInputYear() {
        System.out.print("Enter yyyy: ");
        String yearString = scanner.nextLine();
        System.out.println(new TypeReportHandler().getTypeReportFilterByYear(yearString));
    }
}
