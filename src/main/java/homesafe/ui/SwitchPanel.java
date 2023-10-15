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
    private JPanel textFieldsPanel;
    private JPanel switchPanel = new JPanel();
    private Keyboard keyboard;
    private JFrame frame;
    private GUIUtils guiUtils;

    public SwitchPanel(int textFieldPanelType, JFrame frame) {
        this.frame = frame;
        guiUtils = new GUIUtils(frame);
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
        JTextField usernameField = new JTextField();
        guiUtils.createTxtFlds(usernameField, 20, 20, textPanel1, "Username");
        JTextField pinField = new JTextField(20);
        guiUtils.createTxtFlds(pinField, 20, 20, textPanel1, "PIN");
        textPanel1.setLayout(new GridLayout(2, 1));

        keyboard = new Keyboard(frame, usernameField, pinField);

        JButton enterBtn = keyboard.getEnterButton();
        enterBtn.addActionListener(e -> {
            String username = usernameField.getText();
            String pin = pinField.getText();
            if (AuthenticationService.authorizeUser(username, pin)) {
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
        JPanel changePinTextFieldsPanel = new JPanel();

        JTextField oldPin = new JTextField(10);
        guiUtils.createTxtFlds(oldPin, 20, 20, changePinTextFieldsPanel, "Old Pin");
        JTextField newPin = new JTextField(10);
        guiUtils.createTxtFlds(newPin, 20, 20, changePinTextFieldsPanel, "New Pin");
        JTextField confirmPin = new JTextField(10);
        guiUtils.createTxtFlds(confirmPin, 20, 20, changePinTextFieldsPanel, "Confirm New Pin");

        changePinTextFieldsPanel.setLayout(new GridLayout(3, 1));

        keyboard = new Keyboard(frame, oldPin, newPin, confirmPin);

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
        JPanel addNewUser = new JPanel();

        JTextField userNameField = new JTextField(20);
        guiUtils.createTxtFlds(userNameField, 20, 20, addNewUser, "Username");
        JTextField pinField = new JTextField(20);
        guiUtils.createTxtFlds(pinField, 20, 20, addNewUser, "PIN");
        JTextField confirmPin = new JTextField(20);
        guiUtils.createTxtFlds(confirmPin, 20, 20, addNewUser, "Confirm PIN");
        JCheckBox adminField = new JCheckBox("Admin?");

        addNewUser.setLayout(new GridLayout(3, 1));

        keyboard = new Keyboard(frame, userNameField, pinField, confirmPin);

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
        JPanel changePinTextFieldsPanel = new JPanel();

        JTextField oldPin = new JTextField(20);
        guiUtils.createTxtFlds(oldPin, 20, 20, changePinTextFieldsPanel, "Old PIN");
        JTextField newPin = new JTextField(20);
        guiUtils.createTxtFlds(newPin, 20, 20, changePinTextFieldsPanel, "New PIN");
        JTextField confirmPin = new JTextField(20);
        guiUtils.createTxtFlds(confirmPin, 20, 20, changePinTextFieldsPanel, "Confirm New PIN");
        JCheckBox adminField = new JCheckBox("Admin?");

        changePinTextFieldsPanel.setLayout(new GridLayout(3, 1));

        keyboard = new Keyboard(frame, oldPin, newPin, confirmPin);

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
        guiUtils.createTxtFlds(adminPin, 20, 20, textPanel5, "PIN");
        JTextField confirmPin = new JTextField(20);
        guiUtils.createTxtFlds(confirmPin, 20, 20, textPanel5, "Confirm PIN");

        textPanel5.setLayout(new GridLayout(2, 1));

        keyboard = new Keyboard(frame, adminPin, confirmPin);

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