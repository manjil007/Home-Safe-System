package homesafe.event;

/**
 * created by:
 * author: MichaelMillar
 */
public class AlertEvent extends AbstractSafeEvent {

    public static final String PIN_MISMATCH_EVENT = "pins_dont_match";
    public static final String USER_NULL_EVENT = "user_null";
    public static final String INVALID_PIN_EVENT = "invalid_pin";
    public static final String EMPTY_FIELD_EVENT = "empty_field";
    public static final String UNAUTHORIZED_EVENT = "unauthorized";
    public static final String AUTHORIZED_EVENT = "authorized";

    public AlertEvent(String eventType) {
        super(eventType);
    }

}
