package lu.karpychev.trailfinder.exception;


import java.util.UUID;

public class ObjectNotFoundException extends RuntimeException {
    public ObjectNotFoundException(String message) {
        super(message);
    }

    public ObjectNotFoundException(String nameObject, UUID id) {
        super(nameObject + " with id=" + id + " was not found.");
    }
}
