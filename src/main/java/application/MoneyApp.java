package application;

import GUI.GUISupport;
import GUI.MainMenu;

public class MoneyApp {
    public final static String applicationName = "MoneyAppV1_202211";
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
