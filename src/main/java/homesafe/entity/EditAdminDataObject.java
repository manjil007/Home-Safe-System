package homesafe.entity;

import homesafe.event.AlertEvent;
import homesafe.service.EventService;
import homesafe.service.UserService;

/**
 * Data Object to store edits from the user edit admin screen.
 */
public class EditAdminDataObject implements DataObject {

    /**
     * Valid "active" field data strings
     */
    public static final String USERNAME_FIELD = "username";
    public static final String NEW_PIN_FIELD = "newPin";
    public static final String CONFIRM_PIN_FIELD = "confirmPin";

    // User should only be null if adding a new user
    private User user;
    // Username will only be updated for a new user
    private String username;
    private String newPin;
    private String confirmPin;
    private boolean admin;

    // currently active field name to be updated
    private String active;

    public EditAdminDataObject(User user) {
        this.user = user;
        if (user != null) {
            this.username = user.getUsername();
            this.admin = user.isAdmin();
        }
    }

    public User getUser() {
        return user;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    @Override
    public String getActiveName() {
        return active;
    }

    @Override
    public String getActiveData() {
        String data = null;
        switch (active) {
            case USERNAME_FIELD -> data = username;
            case NEW_PIN_FIELD -> data = newPin;
            case CONFIRM_PIN_FIELD -> data = confirmPin;
        }
        return data;
    }

    @Override
    public void setActiveName(String field) {
        switch (field) {
            case USERNAME_FIELD -> active = USERNAME_FIELD;
            case NEW_PIN_FIELD -> active = NEW_PIN_FIELD;
            case CONFIRM_PIN_FIELD -> active = CONFIRM_PIN_FIELD;
        }
    }

    @Override
    public void appendToActiveData(String str) {
        switch (active) {
            case USERNAME_FIELD -> username = username.concat(str);
            case NEW_PIN_FIELD -> newPin = newPin.concat(str);
            case CONFIRM_PIN_FIELD -> confirmPin = confirmPin.concat(str);
        }
    }

    @Override
    public void process() {
        boolean newUser = user == null;

        // confirm if pins match
        if (!newPin.equals(confirmPin)) {
            AlertEvent event = new AlertEvent(AlertEvent.PIN_MISMATCH_EVENT);
            EventService.getInstance().publishEvent(event);
            return;
        }

        if (newUser) {
            // update username ONLY if new user
            user = new User(username);
            user.setHashedPIN(newPin);
            user.setAdmin(admin);

            // Save user
            UserService.getInstance().addUser(user);
        } else {
            user.setHashedPIN(newPin);
            user.setAdmin(admin);

            // Update user
            UserService.getInstance().updateUser(user);
        }

    }
}
