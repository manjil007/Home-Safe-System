package homesafe.entity;

import homesafe.event.AlertEvent;
import homesafe.service.AuthenticationService;
import homesafe.service.EventService;

/**
 * Data Object to store edits from the admin authorize screen.
 */
public class VerifyAdminDataObject implements DataObject {

    /**
     * Valid "active" field names
     */
    public static final String PIN_FIELD = "pin";
    public static final String CONFIRM_PIN_FIELD = "confirmPin";

    // User should NEVER be null
    private final User user;
    private String pin;
    private String confirmPin;
    // active field name
    private String active;

    public VerifyAdminDataObject(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getConfirmPin() {
        return confirmPin;
    }

    public void setConfirmPin(String confirmPin) {
        this.confirmPin = confirmPin;
    }

    @Override
    public String getActiveName() {
        return active;
    }

    @Override
    public String getActiveData() {
        if (active.equals(PIN_FIELD)) return pin;
        else return confirmPin;
    }

    @Override
    public void setActiveName(String field) {
        if (field.equals(PIN_FIELD)) active = PIN_FIELD;
        else active = CONFIRM_PIN_FIELD;
    }

    @Override
    public void appendToActiveData(String str) {
        if (active.equals(PIN_FIELD)) pin = pin.concat(str);
        else confirmPin = confirmPin.concat(str);
    }

    @Override
    public void process() {
        // Verify both fields match
        if (!pin.equals(confirmPin)) {
            AlertEvent event = new AlertEvent(AlertEvent.PIN_MISMATCH_EVENT);
            EventService.getInstance().publishEvent(event);
            return;
        }

        // Verify pin matches existing pin
        boolean valid = AuthenticationService.verifyHash(user,
                AuthenticationService.hashPIN(user.getUsername(), pin));

        // pin doesn't match
        if (!valid) {
            AlertEvent event = new AlertEvent(AlertEvent.INVALID_PIN_EVENT);
            EventService.getInstance().publishEvent(event);
            return;
        }
        
        // verified
        AlertEvent event = new AlertEvent(AlertEvent.AUTHORIZED_EVENT);
        EventService.getInstance().publishEvent(event);
    }
}
