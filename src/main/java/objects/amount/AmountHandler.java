package objects.amount;

import objects.type.Type;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AmountHandler {
    List<Type> shouldPositiveTypes;

    public AmountHandler() {
        shouldPositiveTypes = new ArrayList<>();
        shouldPositiveTypes.add(Type.INCOME_PASSIVE);
        shouldPositiveTypes.add(Type.INCOME_EARN);
        shouldPositiveTypes.add(Type.INCOME_PORTFOLIO);
    }

    public AmountObject getAmountInputFromUser(Type type) {
        // Generate scanner to get input from user.
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter amount (assumed USD): ");
        String amountString = scanner.nextLine();
        // Default value is 0.0
        if (amountString.length() == 0) {
            amountString = "0";
        }
        BigDecimal tempBigDecimal = new BigDecimal(amountString);
        // If type should be negative, then amount should be negative.
        if (!shouldPositiveTypes.contains(type) && tempBigDecimal.compareTo(new BigDecimal("0")) > 0) {
            System.out.print(type.toString() + " should be negative. Want to switch to negative? ");
            String wantToSwitchString = scanner.nextLine();
            if (wantToSwitchString.compareToIgnoreCase("y") == 0 ||
                    wantToSwitchString.compareToIgnoreCase("yes") == 0) {
                amountString = "-" + amountString;
            }
        }
        // If type should be positive, then amount should be positive.
        if (shouldPositiveTypes.contains(type) && tempBigDecimal.compareTo(new BigDecimal("0")) < 0) {
            System.out.print(type.toString() + " should be positive. Want to switch to positive? ");
            String wantToSwitchString = scanner.nextLine();
            if (wantToSwitchString.compareToIgnoreCase("y") == 0 ||
                    wantToSwitchString.compareToIgnoreCase("yes") == 0) {
                amountString = amountString.substring(1);
            }
        }
        // Confirm and return AmountObject.
        AmountObject amount = new AmountObject(new BigDecimal(amountString));
        System.out.println("Confirm amount: " + amount);
        return amount;
    }
}
