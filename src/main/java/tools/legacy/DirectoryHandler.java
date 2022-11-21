package tools.legacy;

import tools.DateHandler;

import java.io.File;
import java.util.*;

public class DirectoryHandler {
    public List<String> listFileNamesFromDirectory(String directoryPath) {
        // List all file names inside this directory.
        File file = new File(directoryPath);
        String stringArray[] = file.list();
        Arrays.sort(stringArray);
        List<String> list = new LinkedList<>();
        Collections.addAll(list, stringArray);
        // Get a full file path list.
        List<String> fullFilePath = new LinkedList<>();
        for (String shortPath : list) {
            fullFilePath.add(directoryPath + "/" + shortPath);
        }
        return fullFilePath;
    }

    public Date getDateFromLegacyFileName(String fileName) {
        int index = getFirstIndexOf6Digit(fileName);
        String yyyy = fileName.substring(index, index + 4);
        String mm = fileName.substring(index + 4, index + 6);
        return new DateHandler(yyyy + "-" + mm + "-" + "01").getDate();
    }

    private boolean isDigitCharacter(char a) {
        return 48 <= a && a <= 57;
    }

    private int getFirstIndexOf6Digit(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (isDigitCharacter(str.charAt(i)) && isDigitCharacter(str.charAt(i + 5))) {
                return i;
            }
        }
        return -1;
    }
}
