package gui;

import java.util.Scanner;

public abstract class BasicMenu {
    int option;
    Scanner scanner;
    GUISupport guiSupport;

    public BasicMenu() {
        option = -1;
        scanner = new Scanner(System.in);
        guiSupport = new GUISupport();
    }

    public void run() {
        while (option != 0) {
            showMenu();
        }
    }

    public void showMenu() {
        System.out.println("This class " + this.getClass().toString() + " is not IMPLEMENTED. Exit this Menu.");
        option = 0;
    }
}
