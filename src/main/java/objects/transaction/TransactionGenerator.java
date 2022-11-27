package objects.transaction;

import dao.transaction.TransactionReaderDAO;
import gui.GUISupport;
import objects.amount.AmountHandler;
import objects.amount.AmountObject;
import objects.bank.BankHandler;
import objects.bank.BankObject;
import objects.location.LocationHandler;
import objects.location.LocationObject;
import objects.name.NameSuggestBasedHistory;
import objects.note.NoteSuggestionHandler;
import objects.pending.PendingHandler;
import objects.type.Type;
import objects.type.TypeHandler;
import tools.DateHandler;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class TransactionGenerator {

    GUISupport guiSupport;
    List<Transaction> transactions;

    public TransactionGenerator() {
        guiSupport = new GUISupport();
        transactions = new TransactionReaderDAO().getTransactions();
    }

    public TransactionObject createNewTransaction() {
        // Initialized variables.
        Scanner scanner = new Scanner(System.in);
        // Automated transaction id.
        int automatedId = getAutomatedTransactionId();
        System.out.println("Automated id: " + automatedId);
        System.out.println(guiSupport.shortDashLine());
        // Select type.
        Type type = new TypeHandler().selectType();
        System.out.println(guiSupport.shortDashLine());
        // Input date.
        Date automatedDate = getAutomatedDate();
        Date date = getDate(scanner, automatedDate);
        System.out.println(guiSupport.shortDashLine());
        // Get the amount.
        AmountObject amount = new AmountHandler().getAmountInputFromUser(type);
        System.out.println(guiSupport.shortDashLine());
        // Get notes with suggestion.
        String note = getNote(scanner, type);
        System.out.println(guiSupport.shortDashLine());
        // Get name (Company/Brand)
        String name = new NameSuggestBasedHistory(transactions, type, note).selectName();
        System.out.println(guiSupport.shortDashLine());
        // Get location.
        LocationObject location = new LocationHandler().getLocation(transactions, type, name);
        System.out.println(guiSupport.shortDashLine());
        // Get banks
        BankObject primaryBank = new BankHandler().selectPrimaryBank();
        BankObject secondaryBank = new BankHandler().selectSecondaryBank(primaryBank);
        System.out.println(guiSupport.shortDashLine());
        // Is pending?
        boolean isPending = getIsPending();
        System.out.println(guiSupport.shortDashLine());
        // Created a new transaction.
        TransactionObject newTransaction = new TransactionObject(automatedId, TransactionType.NORMAL, date, amount,
                note, primaryBank, isPending, type, name, location, secondaryBank);
        // Confirm transaction is good.
        boolean isSaved = confirmTransactionIsGood(scanner, newTransaction);
        return isSaved ? newTransaction : null;
    }

    private boolean confirmTransactionIsGood(Scanner scanner, TransactionObject newTransaction) {
        System.out.println("Created transaction");
        System.out.println(newTransaction);
        System.out.print("Save this (default NO, type 'yes' or 'y' or BLANK)? ");
        String finalConfirmation = scanner.nextLine();
        boolean isSaved = (finalConfirmation.compareToIgnoreCase("y") == 0 ||
                finalConfirmation.compareToIgnoreCase("yes") == 0);
        System.out.println("Confirm saved: " + isSaved);
        return isSaved;
    }

    private boolean getIsPending() {
        boolean isPending = new PendingHandler().getIsPending();
        System.out.println("Confirm isPending: " + isPending);
        return isPending;
    }

    private String getNote(Scanner scanner, Type type) {
        System.out.print("Note (type something for suggestion): ");
        String suggestedNoteInput = scanner.nextLine();
        return new NoteSuggestionHandler(transactions, type, suggestedNoteInput).selectNote();
    }

    private Date getDate(Scanner scanner, Date automatedDate) {
        Date date = null;
        System.out.print("Date yyyy-mm-dd (automated): ");
        String dateString = scanner.nextLine();
        if (dateString == null || dateString.length() < 6) {
            date = automatedDate;
        } else if (dateString.length() <= 10) {
            date = new DateHandler(dateString).getDate();
        }
        System.out.println("Confirm date: " + new DateHandler(date));
        return date;
    }

    private int getAutomatedTransactionId() {
        if (transactions.size() == 0) {
            return 0;
        } else {
            return transactions.get(transactions.size() - 1).getId() + 1;
        }
    }

    private Date getAutomatedDate() {
        if (transactions.size() == 0) {
            return null;
        } else {
            return transactions.get(transactions.size() - 1).getDate();
        }
    }
}
