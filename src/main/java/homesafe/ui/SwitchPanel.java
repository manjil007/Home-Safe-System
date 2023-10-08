package homesafe.ui;

import javax.swing.*;
import java.awt.*;

/**
 * public JPanel createTextPanel1();
 * Creates text fields: Username, PIN
 *
 * public JPanel createTextPanel2();
 * Creates text fields: Old PW, new PW, confirm new PW
 *
 * public JPanel createTextPanel3();
 * Creates text fields: Username, enter 6-digit PIN, confirm new PIN, Admin?
 *
 * public JPanel createTextPanel4();
 * Creates text fields: old PW, new PW, confirm new PIN, Admin?
 *
 *
 * public JPanel createTextPanel5();
 * Creates text fields: admin PIN, reenter PIN
 *
 */

public class SwitchPanel extends JPanel {
    private int textFieldPanelType;
    private JPanel textFieldsPanel;// add "Enter" button to this panel
    private JPanel switchPanel = new JPanel(); // remove "Enter" button from this panel
    private Keyboard keyboard;

    public SwitchPanel(int textFieldPanelType) {
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
        JPanel textPanel1 = new JPanel();
        // Create text fields
        JTextField usernameField = new JTextField(20);
        usernameField.setName("Username");
        JTextField pinField = new JTextField(20);
        pinField.setName("PIN");
        textPanel1.setLayout(new GridLayout(2, 1));
        // lines 71-73 might not be needed; deletion of JLabels might be applicable
        // to other createTextPanel methods as well
        //textPanel1.add(new JLabel("Username"));
        //textPanel1.add(usernameField);
        //textPanel1.add(new JLabel("PIN"));
        //textPanel1.add(pinField);

        keyboard = new Keyboard(usernameField, pinField);

        JButton enterBtn = keyboard.getEnterButton();
        enterBtn.addActionListener(e -> {
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    // Example usage:
                    PopUpDialog popup = new PopUpDialog("You pushed the enter button on screen 1");
                    popup.showPopUp();
                }
            });
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
        changePinTextFieldsPanel.add(new JLabel("Old Pin"));
        changePinTextFieldsPanel.add(oldPin);
        changePinTextFieldsPanel.add(new JLabel("New Pin"));
        changePinTextFieldsPanel.add(newPin);
        changePinTextFieldsPanel.add(new JLabel("Confirm New Pin"));
        changePinTextFieldsPanel.add(confirmPin);

        keyboard = new Keyboard(oldPin, newPin, confirmPin);

        JButton enterBtn = keyboard.getEnterButton();
        enterBtn.addActionListener(e -> {
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    // Example usage:
                    PopUpDialog popup = new PopUpDialog("You pushed the enter button on screen 2");
                    popup.showPopUp();
                }
            });
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

        JPanel addNewUser = new JPanel();
        addNewUser.setLayout(new GridLayout(3, 1));
        addNewUser.add(new JLabel("User Name"));
        addNewUser.add(userNameField);
        addNewUser.add(new JLabel("6 Digit PIN"));
        addNewUser.add(pinField);
        addNewUser.add(new JLabel("Confirm New Pin"));
        addNewUser.add(confirmPin);

        keyboard = new Keyboard(userNameField, pinField, confirmPin);

        JButton enterBtn = keyboard.getEnterButton();
        enterBtn.addActionListener(e -> {
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    // Example usage:
                    PopUpDialog popup = new PopUpDialog("You pushed the enter button on screen 3");
                    popup.showPopUp();
                }
            });
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

        JPanel changePinTextFieldsPanel = new JPanel();
        changePinTextFieldsPanel.setLayout(new GridLayout(3, 1));
        changePinTextFieldsPanel.add(new JLabel("Old Pin"));
        changePinTextFieldsPanel.add(oldPin);
        changePinTextFieldsPanel.add(new JLabel("New Pin"));
        changePinTextFieldsPanel.add(newPin);
        changePinTextFieldsPanel.add(new JLabel("Confirm New Pin"));
        changePinTextFieldsPanel.add(confirmPin);
        changePinTextFieldsPanel.getName();

        keyboard = new Keyboard(oldPin, newPin, confirmPin);

        JButton enterBtn = keyboard.getEnterButton();
        enterBtn.addActionListener(e -> {
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    // Example usage:
                    PopUpDialog popup = new PopUpDialog("You pushed the enter button on screen 4");
                    popup.showPopUp();
                }
            });
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
        textPanel5.add(new JLabel("Admin PIN"));
        textPanel5.add(adminPin);
        textPanel5.add(new JLabel("Confirm PIN"));
        textPanel5.add(confirmPin);

        keyboard = new Keyboard(adminPin, confirmPin);

        JButton enterBtn = keyboard.getEnterButton();
        enterBtn.addActionListener(e -> {
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    // Example usage:
                    PopUpDialog popup = new PopUpDialog("You pushed the enter button on screen 5");
                    popup.showPopUp();
                }
            });
        });

        textPanel5 = keyboard.getTextFieldsPanel();
        switchPanel.add(keyboard.getKeyboardPanel());

        return textPanel5;
    }
}
