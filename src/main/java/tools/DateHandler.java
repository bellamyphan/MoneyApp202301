package tools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateHandler {
    String yyyyMMddString;

    public DateHandler(String yyyyMMddString) {
        this.yyyyMMddString = yyyyMMddString;
    }

    public DateHandler(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        yyyyMMddString = simpleDateFormat.format(date);
    }

    public Date getDate() {
        // If we have an empty date string.
        if (yyyyMMddString.length() == 0) {
            return null;
        }
        // If we have a valid string date format.
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return simpleDateFormat.parse(yyyyMMddString);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {
        return yyyyMMddString;
    }
}
