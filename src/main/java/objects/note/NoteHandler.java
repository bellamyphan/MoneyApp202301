package objects.note;

import objects.transaction.Transaction;
import dao.transaction.TransactionReaderDAO;
import objects.transaction.TransactionHandler;
import objects.type.Type;
import tools.StringHandler;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class NoteHandler {
    String suggestionInput;
    List<String> suggestedNotes;

    public NoteHandler(Type type, String suggestionInput) {
        this.suggestionInput = suggestionInput;
        List<Transaction> typedTransactions = new TransactionHandler(new TransactionReaderDAO().getTransactions())
                .getFilteredTransactions(type);
        suggestedNotes = new LinkedList<>();
        for (Transaction transaction : typedTransactions) {
            if (transaction.getNote().toLowerCase().contains(suggestionInput.toLowerCase())) {
                if (!suggestedNotes.contains(transaction.getNote())) {
                    suggestedNotes.add(transaction.getNote());
                }
            }
        }
        Collections.reverse(suggestedNotes);
    }

    public String selectNote() {
        // Initialize variables.
        String finalNote;
        Scanner scanner = new Scanner(System.in);
        String inputString;
        // Output suggested notes.
        if (suggestedNotes.size() > 0) {
            System.out.println("Suggested notes:");
            for (int i = 0; i < suggestedNotes.size(); i++) {
                System.out.println(i + ". " + suggestedNotes.get(i));
            }
        }
        // Get input from user.
        System.out.print("Select a suggested notes or enter new notes: ");
        inputString = scanner.nextLine();
        // Convert to an integer if inputString is an integer.
        int option = -1;
        if (inputString.length() > 0) {
            if (new StringHandler(inputString).isAllNumberDigit()) {
                option = Integer.parseInt(inputString);
            }
        }
        // Get suggested notes if we get a valid integer input.
        if (0 <= option && option < suggestedNotes.size()) {
            finalNote = suggestedNotes.get(option);
        } else {
            finalNote = inputString;
        }
        // Confirm and return note.
        System.out.println("Confirm note: " + finalNote);
        return finalNote;
    }
}
