package objects.transaction;

import dao.transaction.TransactionReaderDAO;
import gui.GUISupport;
import objects.amount.AmountObject;
import objects.bank.BankHandler;
import objects.bank.BankObject;
import objects.location.LocationHandler;
import objects.location.LocationObject;
import objects.name.NameSuggestBasedHistory;
import objects.note.NoteHandler;
import objects.pending.PendingHandler;
import objects.type.Type;
import objects.type.TypeHandler;
import tools.DateHandler;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Scanner;

public class TransactionGenerator {

    GUISupport guiSupport;

    public TransactionGenerator() {
        guiSupport = new GUISupport();
    }

    public TransactionObject createNewTransaction() {
        Scanner scanner = new Scanner(System.in);
        // Automated transaction id.
        int automatedId = new TransactionReaderDAO().getAutomatedTransactionId();
        System.out.println("Id: " + automatedId);
        System.out.println(guiSupport.shortDashLine());
        // Select type.
        Type type = new TypeHandler().selectType();
        System.out.println(guiSupport.shortDashLine());
        // Input date.
        System.out.print("Date yyyy-mm-dd (Leave it BLANK for today's date): ");
        String dateString = scanner.nextLine();
        Date date = null;
        if (dateString == null || dateString.length() < 6) {
            date = new Date();
        } else if (dateString.length() <= 10) {
            date = new DateHandler(dateString).getDate();
        }
        System.out.println("Confirm date: " + new DateHandler(date));
        System.out.println(guiSupport.shortDashLine());
        // Get the amount.
        System.out.print("Enter amount (assumed USD): ");
        String amountString = scanner.nextLine();
        AmountObject amount = new AmountObject(new BigDecimal(amountString.length() > 0 ? amountString : "0"));
        System.out.println("Confirm amount: " + amount);
        System.out.println(guiSupport.shortDashLine());
        // Get notes with suggestion.
        System.out.print("Note (type something for suggestion): ");
        String suggestedNoteInput = scanner.nextLine();
        String note = new NoteHandler(type, suggestedNoteInput).selectNote();
        System.out.println(guiSupport.shortDashLine());
        // Get name (Company/Brand)
        String name = new NameSuggestBasedHistory(type, note).selectName();
        System.out.println(guiSupport.shortDashLine());
        // Get location.
        // todo: can be based on 100 most current transactions to suggest location.
        LocationObject location = new LocationHandler().getLocation(type, name);
        System.out.println(guiSupport.shortDashLine());
        // Get banks
        BankObject primaryBank = new BankHandler().selectPrimaryBank();
        BankObject secondaryBank = new BankHandler().selectSecondaryBank(primaryBank);
        System.out.println(guiSupport.shortDashLine());
        // Is pending?
        boolean isPending = new PendingHandler().getIsPending();
        System.out.println("Confirm isPending: " + isPending);
        System.out.println(guiSupport.shortDashLine());
        // Created a new transaction.
        TransactionObject newTransaction = new TransactionObject(automatedId, TransactionType.NORMAL, date, amount,
                note, primaryBank, isPending, type, name, location, secondaryBank);
        // Confirm transaction is good.
        System.out.println("Created transaction");
        System.out.println(newTransaction);
        System.out.print("Save this (default NO, type 'yes' or 'y' or BLANK)? ");
        String finalConfirmation = scanner.nextLine();
        boolean isSaved = (finalConfirmation.compareToIgnoreCase("y") == 0 ||
                finalConfirmation.compareToIgnoreCase("yes") == 0);
        System.out.println("Confirm saved: " + isSaved);
        return isSaved ? newTransaction : null;
    }
}
