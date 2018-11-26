package nam.concertsintown.concertsintown.events;

public class EventNotFoundException extends RuntimeException {
    public EventNotFoundException(String uuid) {
        super("Could not find events " + uuid);
    }
}
