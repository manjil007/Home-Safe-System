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

public class Keyboard extends JPanel {
    private JButton keyboardBtn;
    private JButton userNameEntry;
    private JButton pinEntry;

    private JTextField username;
    private JTextField pin;
    private JTextField focusedField;
    private int textFieldPanelType;
    private JPanel textFieldsPanel;// add "Enter" button to this panel
    private JPanel keyboardPanel = new JPanel(); // remove "Enter" button from this panel

    private JLabel jLabel;

    private Keyboard2 keyboard2;


//    private JPanel changePinTextFieldsPanel;

    public Keyboard(int textFieldPanelType) {
        setLayout(new BorderLayout());

        // Login screen = 1
        //

        // Let there be ONE testFieldPanel field
        // if textFieldPanelType = 1
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


        Component[] components = textFieldsPanel.getComponents();


        for (Component component : components){
            if (component.getName() != null && component.getName().equals("userNameField")){
                username = (JTextField) component;
            }
            else if(component.getName() != null && component.getName().equals("pinField")){
                pin = (JTextField) component;
            }
        }


       /* // Create keyboard panel
        keyboardPanel = new JPanel();
        keyboardPanel.setLayout(new GridLayout(4, 10));

        String[] keyLabels = {
                "0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
                "a", "b", "c", "d", "e", "f", "g", "h", "i", "j",
                "k", "l", "m", "n", "o", "p", "q", "r", "s", "t",
                "u", "v", "w", "x", "y", "z", "Enter", "Backspace"
        };

        for (String label : keyLabels) {
            JButton button = new JButton(label);
            button.addActionListener(e -> {
                String buttonText = button.getText();
                if (buttonText.equals("Enter")) {
                    System.out.println("Username: " + username.getText());
                    System.out.println("PIN: " + pin.getText());
                } else if (buttonText.equals("Backspace")) {
                    String text = username.getText();
                    if (text.length() > 0) {
                        username.setText(text.substring(0, text.length() - 1));
                    }
                } else {
                    username.setText(username.getText() + buttonText);
                }
            });
            keyboardPanel.add(button);
        }

        // Add components to the main panel
        //add(textFieldsPanel, BorderLayout.CENTER);
        //add(keyboardPanel, BorderLayout.SOUTH);*/
    }

    public int getTextFieldPanelType() {
        return textFieldPanelType;
    }

    public void setTextFieldPanelType(int textFieldPanelType) {
        this.textFieldPanelType = textFieldPanelType;
    }


    public JButton getKeyboardBtn(){return keyboardBtn;}

    public JPanel createTextPanel1(){
        JPanel textPanel1 = new JPanel();
        // Create text fields
        JTextField usernameField = new JTextField(20);
        usernameField.setName("Username");
        JTextField pinField = new JTextField(20);
        pinField.setName("PIN");
        textPanel1.setLayout(new GridLayout(2, 1));
        textPanel1.add(new JLabel("Username"));
        textPanel1.add(usernameField);
        textPanel1.add(new JLabel("PIN"));
        textPanel1.add(pinField);

        keyboard2 = new Keyboard2(usernameField, pinField);
        textPanel1 = keyboard2.getTextFieldsPanel();
        keyboardPanel.add(keyboard2.getKeyboardPanel());

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

        keyboard2 = new Keyboard2(oldPin, newPin, confirmPin);
        keyboardPanel.add(keyboard2.getKeyboardPanel());
        changePinTextFieldsPanel = keyboard2.getTextFieldsPanel();
        //Tester: Delete later//System.out.println("Panel2");

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

        keyboard2 = new Keyboard2(userNameField, pinField, confirmPin);
        addNewUser = keyboard2.getTextFieldsPanel();
        keyboardPanel.add(keyboard2.getKeyboardPanel());

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

            keyboard2 = new Keyboard2(oldPin, newPin, confirmPin);
            changePinTextFieldsPanel = keyboard2.getTextFieldsPanel();
            keyboardPanel.add(keyboard2.getKeyboardPanel());

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

        keyboard2 = new Keyboard2(adminPin, confirmPin);
        textPanel5 = keyboard2.getTextFieldsPanel();
        keyboardPanel.add(keyboard2.getKeyboardPanel());

        return textPanel5;
    }

    public JPanel getTextFieldsPanel(){return textFieldsPanel;}
    public JPanel getKeyboardPanel(){return keyboardPanel;}

   // public JLabel getJLabel(){return jLabel}

}
