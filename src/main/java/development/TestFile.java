package development;

import tools.DateHandler;
import tools.legacy.DirectoryHandler;
import tools.legacy.XLSXHandler;

import java.util.List;

public class TestFile {
    static String directoryPath = "data/phase2/legacyData";

    public static void main(String[] args) {
        // List all files inside the directory.
        DirectoryHandler directoryHandler = new DirectoryHandler();
        List<String> filePaths = directoryHandler.listFileNamesFromDirectory(directoryPath);
        // Get the date from file name.
        for (String fileName : filePaths) {
            System.out.println(fileName);
            DateHandler dateHandler = new DateHandler(directoryHandler.getDateFromLegacyFileName(fileName));
            System.out.println(dateHandler);
        }
        System.out.println();
        // Read each file.
        for (String filePath : filePaths) {
            System.out.println("File path: " + filePath);
            XLSXHandler xlsxHandler = new XLSXHandler(filePath);
            System.out.println(xlsxHandler.readFileV2() + "\n");
        }
    }
}
