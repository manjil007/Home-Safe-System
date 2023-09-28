package homesafe.event;

/**
 * Door open/close specific event Data Transfer Object
 */
public class DoorEvent extends AbstractSafeEvent {

    public static final String DOOR_OPENED = "door_opened";
    public static final String DOOR_CLOSED = "door_closed";

    private final boolean doorOpen;

    public DoorEvent(String eventType, boolean doorOpen) {
        super(eventType);
        this.doorOpen = doorOpen;
    }

    public boolean isDoorOpen() {
        return doorOpen;
    }

}
