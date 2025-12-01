package exception;

public class ChangeDetectionException extends RuntimeException {
    public ChangeDetectionException(String message) {
        super(message);
    }

    public ChangeDetectionException(String message, Throwable cause) {
        super(message, cause);
    }
}
