package homesafe.entity;

/**
 * Door open/close specific event Data Transfer Object
 */
public class DoorEvent extends AbstractSafeEvent {

    private final boolean doorOpen;

    public DoorEvent(String eventType, boolean doorOpen) {
        super(eventType);
        this.doorOpen = doorOpen;
    }

    public boolean isDoorOpen() {
        return doorOpen;
    }

}
