package homesafe.event;

/**
 * created by:
 * author: MichaelMillar
 */
public class AlertEvent extends AbstractSafeEvent {

    public static final String PIN_MISMATCH_EVENT = "pins_dont_match";

    public AlertEvent(String eventType) {
        super(eventType);
    }

}
