package artemis.simulator.exception;

public class PayloadExceededException extends Exception {
    public PayloadExceededException(String message) {
        super(message);
    }
}