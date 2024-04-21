package desafio.bridge.back.primenumber.exceptions;

/**
 * Exception class used to indicate errors related to prime number calculations.
 * This exception is thrown when an error occurs during prime number calculations.
 */
public class PrimeNumberException extends RuntimeException {

    /**
     * Constructs a new PrimeNumberException with the specified detail message.
     * @param message error message which is saved for later retrieval by the getMessage() method
     */
    public PrimeNumberException(String message) {
        super(message);
    }
}
