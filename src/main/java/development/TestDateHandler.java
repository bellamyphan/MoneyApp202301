package development;

import tools.DateHandler;

public class TestDateHandler {
    public static void main(String[] args) {
        String dateString = "2024-11";
        System.out.println(new DateHandler(dateString).getFirstDayOfThisMonth());
        System.out.println(new DateHandler(dateString).getLastDayOfThisMonth());
    }
}
