package homesafe.event;

/**
 * Abstract base class for safe events. Each "event publisher" will have their
 * own event class.
 */
public abstract class AbstractSafeEvent {

    private final String eventType;

    protected AbstractSafeEvent(String eventType) {
        this.eventType = eventType;
    }

    public String getEventType() {
        return eventType;
    }

}
