package lu.karpychev.trailfinder.exception;


import java.util.UUID;

public class TrackNotFoundException extends RuntimeException {
    public TrackNotFoundException(String message) {
        super(message);
    }

    public TrackNotFoundException(String nameObject, UUID id) {
        super(nameObject + " with id=" + id + " was not found.");
    }
}
