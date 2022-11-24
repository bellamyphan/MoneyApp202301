package exception;

public class InvalidYearMonthStringException extends IllegalArgumentException {
    public InvalidYearMonthStringException(String s) {
        super(s);
    }
}
