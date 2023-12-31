package homesafe.controller;

import homesafe.entity.ApplicationState;
import homesafe.entity.DataObject;
import homesafe.entity.EditAdminDataObject;
import homesafe.entity.EditSelfDataObject;
import homesafe.entity.LoginDataObject;
import homesafe.entity.State;
import homesafe.event.ButtonEvent;
import homesafe.event.SafeEventHandler;
import homesafe.service.AuthenticationService;
import homesafe.service.EventService;

/**
 * SwitchPanel/keypad controller class that handles the inputs from the display and
 * keyboard.
 */
public class KeypadController extends AbstractController implements SafeEventHandler<ButtonEvent> {

    private static KeypadController instance;

    private DataObject currentDataObject;
    private State currentState;

    public static KeypadController getInstance() {
        if (instance == null) {
            instance = new KeypadController();
        }
        return instance;
    }

    private KeypadController() {
        EventService.getInstance().subscribe(this, ButtonEvent.class);
    }

    /**
     * Helper method that updates the current data object being updated from
     * the display based on the current application state.
     */
    private void monitorState() {
        State state = ApplicationState.getInstance().getState();
        if (state != currentState) {
            currentState = state;
            switch (state) {
                case SLEEP, VIEW_LOGS, OPEN, LOCKOUT -> currentDataObject = null;
                case LOGIN -> currentDataObject = new LoginDataObject();
                case ADMIN_EDIT_USER -> currentDataObject =
                        new EditAdminDataObject(AuthenticationService.getCurrentUser());
                case SELF_EDIT_USER -> currentDataObject =
                        new EditSelfDataObject(AuthenticationService.getCurrentUser());
            }
        }
    }

    @Override
    public void run() {
        while (run) {
            monitorState();
        }
    }

    @Override
    public void handleEvent(ButtonEvent event) {
        monitorState();
        switch (event.getEventType()) {
            case ButtonEvent.FIELD_FOCUSED -> {
                if (currentDataObject != null) {
                    currentDataObject.setActiveName(event.getData());
                }
            }
            case ButtonEvent.KEY_PRESSED -> {
                if (currentDataObject != null) {
                    currentDataObject.appendToActiveData(event.getData());
                }
            }
            case ButtonEvent.PROCESS_FORM -> {
                if (currentDataObject != null) {
                    currentDataObject.process();
                }
            }
        }
    }
}
