package development;

import tools.DateHandler;
import tools.legacy.DirectoryHandler;

import java.util.List;

public class TestFile {
    static String directoryPath = "data/phase2/legacyData";

    public static void main(String[] args) {
        // List all files inside the directory.
        DirectoryHandler directoryHandler = new DirectoryHandler();
        List<String> list = directoryHandler.listFileNamesFromDirectory(directoryPath);
        // Get the date from file name.
        for (String fileName : list) {
            System.out.println(fileName);
            DateHandler dateHandler = new DateHandler(directoryHandler.getDateFromLegacyFileName(fileName));
            System.out.println(dateHandler);
            System.out.println();
        }
    }
}
