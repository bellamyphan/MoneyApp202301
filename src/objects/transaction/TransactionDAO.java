package objects.transaction;

import tools.DateHandler;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class TransactionDAO {
    List<Transaction> transactions;

    public TransactionDAO(String dataFilePath) {
        // Create empty transactions list.
        transactions = new ArrayList<>();
        // Read the csv data file.
        try (Scanner scanner = new Scanner(new File(dataFilePath))) {
            // Skip header line.
            scanner.nextLine();
            // Read each line.
            while (scanner.hasNext()) {
                String currentLine = scanner.nextLine();
                String[] cells = currentLine.split(",");
                int id = Integer.parseInt(cells[0]);
                TransactionType transactionType = TransactionType.valueOf(cells[1].toUpperCase());
                Date date = new DateHandler(cells[2]).getDate();
                double amount = Double.parseDouble(cells[3]);
                System.out.println(currentLine);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
