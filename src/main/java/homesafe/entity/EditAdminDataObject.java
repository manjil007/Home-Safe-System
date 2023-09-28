package homesafe.entity;

/**
 * created by:
 * author: MichaelMillar
 */
public class EditAdminDataObject implements DataObject {

        public static final String USERNAME_FIELD = "username";
        public static final String NEW_PIN_FIELD = "newPin";
        public static final String CONFIRM_PIN_FIELD = "confirmPin";

        private final User user;
        private String username;
        private String newPin;
        private String confirmPin;
        private boolean admin;

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

    }
}
