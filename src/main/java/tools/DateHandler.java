package tools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateHandler {
    Date date;

    public DateHandler(String dateString) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        if (dateString.length() < 10) {
            dateString += "-01";
        }
        try {
            date = simpleDateFormat.parse(dateString);
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

    public Date getMinDateOfMonth() {
        String newDateString = getSimpleDateString().substring(0, 7);
        return new DateHandler(newDateString).getDate();
    }

    public Date getMaxDateOfMonth() {
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
