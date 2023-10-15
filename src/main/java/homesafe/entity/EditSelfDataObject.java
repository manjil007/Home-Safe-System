package homesafe.entity;

import homesafe.event.AlertEvent;
import homesafe.service.AuthenticationService;
import homesafe.service.EventService;
import homesafe.service.UserService;

/**
 * Data Object to store edits from the user edit self screen.
 */
public class EditSelfDataObject implements DataObject {

    /**
     * Valid "active" field names
     */
    public static final String OLD_PIN_FIELD = "oldPin";
    public static final String NEW_PIN_FIELD = "newPin";
    public static final String CONFIRM_PIN_FIELD = "confirmPin";

    // User should NEVER be null
    private final User user;
    private String oldPin = "";
    private String newPin = "";
    private String confirmPin = "";

    // Currently active field being edited
    private String active;

    public EditSelfDataObject(User user) {
        this.user = user;
        this.active = OLD_PIN_FIELD;
    }

    public User getUser() {
        return user;
    }

    public String getOldPin() {
        return oldPin;
    }

    public void setOldPin(String oldPin) {
        this.oldPin = oldPin;
    }

    public String getNewPin() {
        return newPin;
    }

    public void setNewPin(String newPin) {
        this.newPin = newPin;
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
        String data = null;
        switch (active) {
            case OLD_PIN_FIELD -> data = oldPin;
            case NEW_PIN_FIELD -> data = newPin;
            case CONFIRM_PIN_FIELD -> data = confirmPin;
        }
        return data;
    }

    @Override
    public void setActiveName(String field) {
        switch (field) {
            case OLD_PIN_FIELD -> active = OLD_PIN_FIELD;
            case NEW_PIN_FIELD -> active = NEW_PIN_FIELD;
            case CONFIRM_PIN_FIELD -> active = CONFIRM_PIN_FIELD;
        }
    }

    @Override
    public void appendToActiveData(String str) {
        switch (active) {
            case OLD_PIN_FIELD -> oldPin = oldPin.concat(str);
            case NEW_PIN_FIELD -> newPin = newPin.concat(str);
            case CONFIRM_PIN_FIELD -> confirmPin = confirmPin.concat(str);
        }
    }

    @Override
    public void process() {
        // User cannot be null if editing self
        if (user == null) {
            AlertEvent event = new AlertEvent(AlertEvent.USER_NULL_EVENT);
            EventService.getInstance().publishEvent(event);
            return;
        }

        // confirm new and confirm pin match
        if (!newPin.equals(confirmPin)) {
            AlertEvent event = new AlertEvent(AlertEvent.PIN_MISMATCH_EVENT);
            EventService.getInstance().publishEvent(event);
            return;
        }

        // validate users existing hash with new hash
        boolean verified = AuthenticationService
                .verifyHash(user, AuthenticationService.hashPIN(user.getUsername(), oldPin));

        // verify hashed "old pin" matches existing pin
        if (!verified) {
            AlertEvent event = new AlertEvent(AlertEvent.INVALID_PIN_EVENT);
            EventService.getInstance().publishEvent(event);
            return;
        }

        // update user
        user.setHashedPIN(newPin);
        UserService.getInstance().updateUser(user);
    }
}
