package cz.horak.app.exception;

public class FailedToCountStatisticException extends RuntimeException {
    
    private static final long serialVersionUID = 1L;

    public FailedToCountStatisticException(String message) {
        super(message);
    }

}
