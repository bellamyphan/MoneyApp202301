package tools;

import exception.InvalidYearMonthDateStringException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateHandler {
    Date date;

    public DateHandler(String dateString) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String formalDateString;
        if (dateString.length() == 4) {
            formalDateString = dateString + "-01-01";
        } else if (dateString.length() == 6) {
            formalDateString = dateString.substring(0, 4) + "-"
                    + dateString.substring(dateString.length() - 2) + "-01";
        } else if (dateString.length() == 7) {
            formalDateString = dateString + "-01";
        } else if (dateString.length() == 10) {
            formalDateString = dateString;
        } else {
            throw new InvalidYearMonthDateStringException("Length of year month day string is not valid");
        }
        try {
            date = simpleDateFormat.parse(formalDateString);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public DateHandler(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public Date getFirstDayOfThisMonth() {
        String newDateString = getSimpleDateString().substring(0, 7);
        return new DateHandler(newDateString).getDate();
    }

    public Date getLastDayOfThisMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        String newDateString = getSimpleDateString().substring(0, 8) + maxDay;
        return new DateHandler(newDateString).getDate();
    }

    @Override
    public String toString() {
        return getSimpleDateString();
    }

    private String getSimpleDateString() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return simpleDateFormat.format(date);
    }
}
