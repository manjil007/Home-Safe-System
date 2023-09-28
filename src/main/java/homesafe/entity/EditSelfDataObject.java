package homesafe.entity;

/**
 * created by:
 * author: MichaelMillar
 */
public class EditSelfDataObject implements DataObject {

    public static final String OLD_PIN_FIELD = "oldPin";
    public static final String NEW_PIN_FIELD = "newPin";
    public static final String CONFIRM_PIN_FIELD = "confirmPin";

    private final User user;
    private String oldPin;
    private String newPin;
    private String confirmPin;
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
        // TODO
    }
}
