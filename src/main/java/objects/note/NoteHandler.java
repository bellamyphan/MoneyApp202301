package objects.note;

import objects.transaction.Transaction;
import dao.transaction.TransactionReaderDAO;
import objects.type.Type;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class NoteHandler {
    String suggestionInput;
    List<String> suggestedNotes;

    public NoteHandler(Type type, String suggestionInput) {
        this.suggestionInput = suggestionInput;
        List<Transaction> typedTransactions = new TransactionReaderDAO().getTransactions(type);
        suggestedNotes = new LinkedList<>();
        for (Transaction transaction : typedTransactions) {
            if (transaction.getNote().toLowerCase().contains(suggestionInput.toLowerCase())) {
                if (!suggestedNotes.contains(transaction.getNote())) {
                    suggestedNotes.add(transaction.getNote());
                }
            }
        }
    }

    public String selectNote() {
        String finalNote;
        Scanner scanner = new Scanner(System.in);
        int option = 0;
        if (suggestedNotes.size() > 0) {
            System.out.println("Select a note:");
            int i;
            for (i = 0; i < suggestedNotes.size(); i++) {
                System.out.println(i + ". " + suggestedNotes.get(i));
            }
            System.out.println(i + ". ENTER YOUR NEW NOTES");
            System.out.print("Your selection: ");
            option = scanner.nextInt();
            scanner.nextLine();
        }
        if (option < suggestedNotes.size()) {
            finalNote = suggestedNotes.get(option);
            System.out.println("Confirm note: " + finalNote);
        } else {
            System.out.print("Enter your new notes: ");
            finalNote = scanner.nextLine();
        }
        return finalNote;
    }
}
