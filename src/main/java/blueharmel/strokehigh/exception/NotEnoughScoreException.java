package blueharmel.strokehigh.exception;

public class NotEnoughScoreException extends RuntimeException {

    public NotEnoughScoreException() {
        super();
    }

    public NotEnoughScoreException(String message) {
        super(message);
    }

    public NotEnoughScoreException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotEnoughScoreException(Throwable cause) {
        super(cause);
    }

    protected NotEnoughScoreException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
