package homesafe.entity;

import homesafe.event.AlertEvent;
import homesafe.service.AuthenticationService;
import homesafe.service.EventService;

/**
 * Data Object to store edits from the user login screen.
 */
public class LoginDataObject implements DataObject {

    /**
     * Valid "active" field names
     */
    public static final String USERNAME_FIELD = "username";
    public static final String PIN_FIELD = "pin";

    private String username;
    private String pin;

    // currently active field name
    private String active;

    public LoginDataObject() {
        this(null, null);
    }

    public LoginDataObject(String username, String pin) {
        this.username = username;
        this.pin = pin;
        this.active = USERNAME_FIELD;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    @Override
    public String getActiveName() {
        return active;
    }

    @Override
    public void setActiveName(String field) {
        this.active = field;
    }

    public String getActiveData() {
        if (active.equals(USERNAME_FIELD)) return username;
        else return pin;
    }


    @Override
    public void appendToActiveData(String str) {
        if (active.equals(USERNAME_FIELD)) username = username.concat(str);
        else pin = pin.concat(str);
    }

    @Override
    public void process() {
        // verify both fields have data
        if (username.isEmpty() || pin.isEmpty()) {
            AlertEvent event = new AlertEvent(AlertEvent.EMPTY_FIELD_EVENT);
            EventService.getInstance().publishEvent(event);
            return;
        }

        // validate login
        boolean authorized = AuthenticationService.authorizeUser(username, pin);
        if (!authorized) {
            AlertEvent event = new AlertEvent(AlertEvent.UNAUTHORIZED_EVENT);
            EventService.getInstance().publishEvent(event);
            return;
        }

        // valid user
        AlertEvent event = new AlertEvent(AlertEvent.AUTHORIZED_EVENT);
        EventService.getInstance().publishEvent(event);
    }
}
