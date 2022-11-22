package objects.pending;

import java.util.Scanner;

public class PendingHandler {
    public boolean getIsPending() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Pending (default NO, type yes or y or BLANK)?: ");
        String pendingString = scanner.nextLine();
        return pendingString.compareToIgnoreCase("y") == 0 ||
                pendingString.compareToIgnoreCase("yes") == 0;
    }
}
