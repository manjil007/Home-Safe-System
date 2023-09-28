package homesafe.entity;

/**
 * created by:
 * author: MichaelMillar
 */
public class VerifyAdminDataObject implements DataObject {

    public static final String PIN_FIELD = "pin";
    public static final String CONFIRM_PIN_FIELD = "confirmPin";

    private final User user;
    private String pin;
    private String confirmPin;
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

    }
}
