package exception;

public class InvalidYearStringException extends IllegalArgumentException {
    public InvalidYearStringException(String s) {
        super(s);
    }
}
