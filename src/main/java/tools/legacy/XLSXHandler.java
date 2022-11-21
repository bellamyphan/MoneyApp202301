package tools.legacy;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class XLSXHandler {
    String filePath;

    public XLSXHandler(String filePath) {
        this.filePath = filePath;
    }

    // todo: delete this later. Use V2 instead.
    public String readFileV1() {
        String result = "";
        // Use FileInputStream.
        try (FileInputStream fis = new FileInputStream(filePath)) {
            // Find the workBook instance.
            XSSFWorkbook myWorkBook = new XSSFWorkbook(fis);
            // Get the first sheet.
            XSSFSheet mySheet = myWorkBook.getSheetAt(0);
            // Get iterator to all the rows in the sheet.
            Iterator<Row> rowIterator = mySheet.iterator();
            // Traversing over each row.
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                // For each row, travel through each column or cell.
                Iterator<Cell> cellIterator = row.cellIterator();
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    if (cell.getCellType() == CellType.BLANK) {
                        result += "BLANK,";
                    }
                    result += cell + ",";
                }
                result += "\b\n";
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    public String readFileV2 () {
        String result = "";
        try (FileInputStream fis = new FileInputStream(filePath);
                XSSFWorkbook myWorkBook = new XSSFWorkbook(fis)) {
            XSSFSheet mySheet = myWorkBook.getSheetAt(0);
            boolean skipHeader = true;
            for (Row row : mySheet) {
                if (skipHeader) {
                    skipHeader = false;
                    continue;
                }
                List<Cell> cells = new ArrayList<>();
                int lastColumn = Math.min(6, row.getLastCellNum());
                for (int i = 0; i < lastColumn; i++) {
                    Cell cell = row.getCell(i, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
                    cells.add(cell);
                }
                for (Cell cell : cells) {
                    if (cell == null) {
                        result += "NULL,";
                        continue;
                    }
                    result += cell + ",";
                }
                result += "\b\n";
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}
