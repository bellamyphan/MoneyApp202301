package tools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateHandler {
    String dateString;

    public DateHandler(String dateString) {
        this.dateString = dateString;
    }

    public DateHandler(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateString = simpleDateFormat.format(date);
    }

    public Date getDate() {
        // If we have an empty date string.
        if (dateString.length() == 0) {
            return null;
        }
        // If we have a valid string date format.
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return simpleDateFormat.parse(dateString);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {
        return dateString;
    }
}
