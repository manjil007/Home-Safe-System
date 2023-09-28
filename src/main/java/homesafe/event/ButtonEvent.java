package homesafe.event;

/**
 * created by:
 * author: MichaelMillar
 */
public class ButtonEvent extends AbstractSafeEvent {

    public static final String FIELD_FOCUSED = "field_focused";
    public static final String PROCESS_FORM = "process_form";
    public static final String KEY_PRESSED = "key_pressed";

    private final String data;

    public ButtonEvent(String eventType, String data) {
        super(eventType);
        this.data = data;
    }

    public String getData() {
        return data;
    }

}
