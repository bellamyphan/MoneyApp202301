package gui.report;

import gui.BasicMenu;
import objects.location.LocationReportHandler;

public class ReportLocationMenu extends BasicMenu {
    @Override
    public void showMenu() {
        System.out.println("Location Report Menu");
        System.out.println("1. Location report for this month");
        System.out.println("2. Location report for last month");
        System.out.println("3. Location report for an input month");
        System.out.println("4. Location report for an input year");
        System.out.println("5. Location report for last 'N' months (excluding this month) - Total");
        System.out.println("6. Location report for last 'N' months (excluding this month) - Average");
        System.out.println("7. Location report for 'N' month with StartMonth and EndMonth - Total");
        System.out.println("8. Location report for 'N' month with StartMonth and EndMonth - Average");
        System.out.println("9. Location report for all transactions - Until today");
        System.out.println("10. Location report per name for all transactions - Until today");
        System.out.println("11. Location report for all transactions - Including future");
        System.out.println("0. Exit this menu");
        System.out.print("Select: ");
        option = scanner.nextInt();
        scanner.nextLine();
        System.out.println(guiSupport.shortDashLine());
        switch (option) {
            case 0 -> System.out.println("Exit Location Report Menu...");
            case 9 -> System.out.println(new LocationReportHandler().getLocationReportFilterUntilToday());
            case 10 -> System.out.println(new LocationReportHandler().getLocationPerNameReportFilterUntilToday());
            default -> System.out.println("This feature is not IMPLEMENTED or INVALID input");
        }
        if (option != 0)
            System.out.println(guiSupport.longDashLine());
    }
}
