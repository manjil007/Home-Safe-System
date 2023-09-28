package homesafe.entity;

/**
 * created by:
 * author: MichaelMillar
 */
public class LoginDataObject implements DataObject {

    public static final String USERNAME_FIELD = "username";
    public static final String PIN_FIELD = "pin";

    private String username;
    private String pin;
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

    }
}
