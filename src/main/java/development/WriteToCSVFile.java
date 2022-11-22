package development;

import com.opencsv.CSVWriter;

import java.io.FileWriter;
import java.io.IOException;

public class WriteToCSVFile {
    public static void main(String[] args) {
        try (FileWriter fileWriter = new FileWriter("data/production/banks2.csv");
             CSVWriter csvWriter = new CSVWriter(fileWriter)) {
            String[] strings = {"FirstColumn", "Second Column", "Third Column"};
            csvWriter.writeNext(strings);
            csvWriter.writeNext(strings);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
