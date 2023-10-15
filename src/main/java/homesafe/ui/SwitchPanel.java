package homesafe.ui;

import homesafe.entity.ApplicationState;
import homesafe.entity.State;
import homesafe.entity.User;
import homesafe.event.DoorEvent;
import homesafe.service.AuthenticationService;
import homesafe.service.EventService;
import homesafe.service.UserService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Objects;

/**
 * public JPanel createTextPanel1();
 * Login screen
 * Creates text fields: Username, PIN
 *
 * public JPanel createTextPanel2();
 * Manage pin screen
 * Creates text fields: Old PW, new PW, confirm new PW
 *
 * public JPanel createTextPanel3();
 * Add user screen
 * Creates text fields: Username, enter 6-digit PIN, confirm new PIN, Admin?
 *
 * public JPanel createTextPanel4();
 * Manage pin admin screen
 * Creates text fields: old PW, new PW, confirm new PIN, Admin?
 *
 *
 * public JPanel createTextPanel5();
 * Delete user screen
 * Creates text fields: admin PIN, reenter PIN
 *
 */

public class SwitchPanel extends JPanel {
    private int textFieldPanelType;
    private JPanel textFieldsPanel;// add "Enter" button to this panel
    private JPanel switchPanel = new JPanel(); // remove "Enter" button from this panel
    private Keyboard keyboard;
    private JFrame frame;

    public SwitchPanel(int textFieldPanelType, JFrame frame) {
        this.frame = frame;
        setLayout(new BorderLayout());
        // case determines which kind of text panel should be created for the current
        // "screen" object
        switch (textFieldPanelType){
            case 1:
                textFieldsPanel = createTextPanel1();
                break;
            case 2:
                textFieldsPanel = createTextPanel2();
                break;
            case 3:
                textFieldsPanel = createTextPanel3();
                break;
            case 4:
                textFieldsPanel = createTextPanel4();
                break;
            case 5:
                textFieldsPanel = createTextPanel5();
                break;
            default:
                // ????
                break;
        }

    }
    public void setTextFieldPanelType(int textFieldPanelType) {this.textFieldPanelType = textFieldPanelType;}
    public JPanel getTextFieldsPanel(){return textFieldsPanel;}
    public JPanel getSwitchPanel(){return switchPanel;}

    public JPanel createTextPanel1(){
        // Create text fields
        JPanel textPanel1 = new JPanel();
        JTextField usernameField = new JTextField(20);
        usernameField.setName("Username");
        JTextField pinField = new JTextField(20);
        pinField.setName("PIN");
        textPanel1.setLayout(new GridLayout(2, 1));

        keyboard = new Keyboard(usernameField, pinField);

        JButton enterBtn = keyboard.getEnterButton();
        enterBtn.addActionListener(e -> {
            String username = usernameField.getText();
            String pin = pinField.getText();
            if (AuthenticationService.authorizeUser(username, pin)) {
                GUIUtils guiUtils = new GUIUtils(frame);
                WelcomeScreen welcomeScreen = new WelcomeScreen(guiUtils);
                guiUtils.switchScreens(welcomeScreen.getPanel());
            }
            else {
                if(ApplicationState.getInstance().getState() == State.LOCKOUT){
                    PopUpDialog popup = new PopUpDialog("You are locked out.");
                    popup.showPopUp();
                }
                else {
                    PopUpDialog popup = new PopUpDialog("Username or PIN is incorrect.");
                    popup.showPopUp();
                }
            }
        });

        textPanel1 = keyboard.getTextFieldsPanel();
        switchPanel.add(keyboard.getKeyboardPanel());

        return textPanel1;
    }
    public JPanel createTextPanel2(){
        JTextField oldPin = new JTextField(10);
        oldPin.setName("Old Pin");
        JTextField newPin = new JTextField(10);
        newPin.setName("New Pin");
        JTextField confirmPin = new JTextField(10);
        confirmPin.setName("Confirm New Pin");

        JPanel changePinTextFieldsPanel = new JPanel();
        changePinTextFieldsPanel.setLayout(new GridLayout(3, 1));
        changePinTextFieldsPanel.add(oldPin);
        changePinTextFieldsPanel.add(newPin);
        changePinTextFieldsPanel.add(confirmPin);

        keyboard = new Keyboard(oldPin, newPin, confirmPin);

        JButton enterBtn = keyboard.getEnterButton();
        enterBtn.addActionListener(e -> {
            User currentUser = AuthenticationService.getCurrentUser();
            String oldPass = oldPin.getText();
            String newPass = newPin.getText();
            String confirm = confirmPin.getText();
            if (AuthenticationService.authorizeUser(currentUser.getUsername(), oldPass)) {
                if (!Objects.equals(newPass, confirm)) {
                    PopUpDialog popup = new PopUpDialog("New PINs do not match.");
                    popup.showPopUp();
                }
                else {
                    String hashedPin = AuthenticationService.hashPIN(currentUser.getUsername(), confirm);
                    currentUser.setHashedPIN(hashedPin);
                    UserService.getInstance().updateUser(currentUser);
                    PopUpDialog popup = new PopUpDialog("PIN change successful");
                    popup.showPopUp();
                }
            }
            else {
                lockOut();
            }
        });

        switchPanel.add(keyboard.getKeyboardPanel());
        changePinTextFieldsPanel = keyboard.getTextFieldsPanel();

        return changePinTextFieldsPanel;
    }
    public JPanel createTextPanel3(){
        JTextField userNameField = new JTextField(20);
        userNameField.setName("User Name");
        JTextField pinField = new JTextField(20);
        pinField.setName("6 Digit Pin");
        JTextField confirmPin = new JTextField(20);
        confirmPin.setName("Confirm New Pin");
        JCheckBox adminField = new JCheckBox("Admin?");

        JPanel addNewUser = new JPanel();
        addNewUser.setLayout(new GridLayout(3, 1));
        addNewUser.add(userNameField);
        addNewUser.add(pinField);
        addNewUser.add(confirmPin);
        addNewUser.add(adminField);

        keyboard = new Keyboard(userNameField, pinField, confirmPin);

        JButton enterBtn = keyboard.getEnterButton();
        enterBtn.addActionListener(e -> {
            String username = userNameField.getText();
            String pin = pinField.getText();
            String confirm = confirmPin.getText();
            if (Objects.equals(pin, confirm)) {
                User newUser = new User(username);
                String hashedPin = AuthenticationService.hashPIN(newUser.getUsername(), confirm);
                newUser.setHashedPIN(hashedPin);
                newUser.setAdmin(adminField.isSelected());
                UserService.getInstance().addUser(newUser);
                PopUpDialog popup = new PopUpDialog("PIN change successful");
                popup.showPopUp();
            }
            else {
                PopUpDialog popup = new PopUpDialog("New PINs do not match.");
                popup.showPopUp();
            }
        });

        addNewUser = keyboard.getTextFieldsPanel();
        switchPanel.add(keyboard.getKeyboardPanel());

        return addNewUser;
    }
    public JPanel createTextPanel4(){
        JTextField oldPin = new JTextField(20);
        oldPin.setName("Old Pin");
        JTextField newPin = new JTextField(20);
        newPin.setName("New Pin");
        JTextField confirmPin = new JTextField(20);
        confirmPin.setName("Confirm New Pin");
        JCheckBox adminField = new JCheckBox("Admin?");

        JPanel changePinTextFieldsPanel = new JPanel();
        changePinTextFieldsPanel.setLayout(new GridLayout(3, 1));
        changePinTextFieldsPanel.add(oldPin);
        changePinTextFieldsPanel.add(newPin);
        changePinTextFieldsPanel.add(confirmPin);
        changePinTextFieldsPanel.getName();
        changePinTextFieldsPanel.add(adminField);

        keyboard = new Keyboard(oldPin, newPin, confirmPin);

        JButton enterBtn = keyboard.getEnterButton();
        enterBtn.addActionListener(e -> {
            User currentUser = AuthenticationService.getCurrentUser();
            String oldPass = oldPin.getText();
            String newPass = newPin.getText();
            String confirm = confirmPin.getText();
            if (AuthenticationService.authorizeUser(currentUser.getUsername(), oldPass)) {
                if (!Objects.equals(newPass, confirm)) {
                    PopUpDialog popup = new PopUpDialog("New PINs do not match.");
                    popup.showPopUp();
                }
                else {
//                    String hashedPin = AuthenticationService.hashPIN(currentUser.getUsername(), confirm);
//                    .setHashedPIN(hashedPin);
//                    .setAdmin(adminField.isSelected());
//                    UserService.getInstance().updateUser();
//                    PopUpDialog popup = new PopUpDialog("Changes successful");
//                    popup.showPopUp();
                }
            }
            else {
                lockOut();
            }
        });


        changePinTextFieldsPanel = keyboard.getTextFieldsPanel();
            switchPanel.add(keyboard.getKeyboardPanel());

            return changePinTextFieldsPanel;
    }
    public JPanel createTextPanel5(){
        JPanel textPanel5 = new JPanel();

        // Create text fields
        JTextField adminPin = new JTextField(20);
        adminPin.setName("Admin PIN");
        JTextField confirmPin = new JTextField(20);
        confirmPin.setName("Confirm Pin");

        textPanel5.setLayout(new GridLayout(2, 1));
        textPanel5.add(adminPin);
        textPanel5.add(confirmPin);

        keyboard = new Keyboard(adminPin, confirmPin);

        JButton enterBtn = keyboard.getEnterButton();
        enterBtn.addActionListener(e -> {
            User currentUser = AuthenticationService.getCurrentUser();
            String pin = adminPin.getText();
            String confirm = confirmPin.getText();
            if (Objects.equals(pin, confirm)) {
                if (AuthenticationService.authorizeUser(currentUser.getUsername(), pin)) {
                    //UserService.getInstance().deleteUser();
                }
                else {
                    lockOut();
                }
            }
            else {
                PopUpDialog popup = new PopUpDialog("New PINs do not match.");
                popup.showPopUp();
            }
        });

        textPanel5 = keyboard.getTextFieldsPanel();
        switchPanel.add(keyboard.getKeyboardPanel());

        return textPanel5;
    }

    private void lockOut () {
        if(ApplicationState.getInstance().getState() == State.LOCKOUT){
            GUIUtils guiUtils = new GUIUtils(frame);
            AuthenticationService.deAuthorizeUser();
            SafeLocked safeLocked = new SafeLocked(guiUtils);
            guiUtils.switchScreens(safeLocked.getPanel());
            DoorEvent event = new DoorEvent(DoorEvent.DOOR_CLOSED, false);
            EventService.getInstance().publishEvent(event);
            PopUpDialog popup = new PopUpDialog("You are locked out.");
            popup.showPopUp();
        }
        else {
            PopUpDialog popup = new PopUpDialog("PIN is incorrect.");
            popup.showPopUp();
        }
    }
}