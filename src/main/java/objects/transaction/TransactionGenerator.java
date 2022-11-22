package objects.transaction;

import objects.amount.AmountObject;
import objects.bank.BankHandler;
import objects.bank.BankObject;
import objects.location.LocationHandler;
import objects.location.LocationObject;
import objects.name.NameHandler;
import objects.note.NoteHandler;
import objects.type.Type;
import objects.type.TypeHandler;
import tools.DateHandler;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Scanner;

public class TransactionGenerator {

    public TransactionObject createNewTransaction() {
        Scanner scanner = new Scanner(System.in);
        // Automated transaction id.
        int automatedId = new TransactionDAO().getAutomatedTransactionId();
        System.out.println("Id: " + automatedId);
        // Select type.
        Type type = new TypeHandler().selectType();
//        Type type = Type.INCOME_EARN;
        // Input date.
        System.out.print("Date yyyy-mm-dd (leave blank for auto input): ");
        String dateString = scanner.nextLine();
//        String dateString = "";
        Date date;
        if (dateString == null || dateString.length() == 0) {
            date = new Date();
        } else {
            date = new DateHandler(dateString).getDate();
        }
        System.out.println("Confirm date: " + new DateHandler(date));
        // Get the amount.
        System.out.print("Enter amount (assumed USD): ");
        String amountString = scanner.nextLine();
//        String amountString = "350";
        AmountObject amount = new AmountObject(new BigDecimal(amountString));
        System.out.println("Confirm amount: " + amount);
        // Get notes with suggestion.
        System.out.print("Note (auto suggest based on TYPE): ");
        String suggestedNoteInput = scanner.nextLine();
//        String suggestedNoteInput = "wee";
        String note = new NoteHandler(type, suggestedNoteInput).selectNote();
        // Get name.
        String name = new NameHandler(type, note).selectName();
        // Get location.
        // todo: can be based on 100 most current transactions to suggest location.
        LocationObject location = new LocationHandler().getLocation();
        // Get banks
        BankObject primaryBank = new BankHandler().selectPrimaryBank();
        BankObject secondaryBank = new BankHandler().selectSecondaryBank(primaryBank);
        // Is pending?
        System.out.print("Pending (true/false)?: ");
        String pendingString = scanner.nextLine();
        boolean isPending = Boolean.parseBoolean(pendingString);

        return new TransactionObject(automatedId, TransactionType.NORMAL, date, amount, note, primaryBank, isPending,
                type, name, location, secondaryBank);
    }

}
