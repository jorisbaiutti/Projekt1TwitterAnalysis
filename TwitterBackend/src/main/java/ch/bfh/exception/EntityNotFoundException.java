
package ch.bfh.exception;

public class EntityNotFoundException extends RuntimeException {
    private static final long servialVersionUID = -5597371741018039691L;
    public EntityNotFoundException(String message) {
        super(message);
    }
}
