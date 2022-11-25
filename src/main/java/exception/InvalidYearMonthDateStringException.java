package exception;

public class InvalidYearMonthDateStringException extends IllegalArgumentException {
    public InvalidYearMonthDateStringException(String s) {
        super(s);
    }
}
