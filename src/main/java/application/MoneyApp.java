package application;

import gui.GUISupport;
import gui.MainMenu;

public class MoneyApp {
    public final static String applicationName = "MoneyAppV1_202211";
    public final static String transactionsDataPath = "data/production/transactions.csv";
    public final static String banksDataPath = "data/production/banks.csv";
    public final static String usCitiesDataPath = "data/usStatesCities/usCities.csv";
    public final static String usStatesDataPath = "data/usStatesCities/usStates.csv";
    public static void main(String[] args) {
        // Introduction.
        System.out.println("Application is under development - " + applicationName);
        System.out.println(new GUISupport().longDashLine());
        // Start the Main Menu.
        new MainMenu().run();
        // Exit the application.
        System.out.println(applicationName + " is closing......");
        System.out.println(new GUISupport().longDashLine());
    }
}
