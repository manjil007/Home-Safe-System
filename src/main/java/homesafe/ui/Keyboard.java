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
 * public JPanel createTextPanel5();
 * Creates text fields: admin PIN, reenter PIN
 *
 */

public class Keyboard extends JPanel {
    private JButton keyboardBtn;
    private JButton userNameEntry;
    private JButton pinEntry;

    private JTextField usernameField;
    private JTextField pinField;
    private int textFieldPanelType;
    private JPanel textFieldsPanel; // add "Enter" button to this panel
    private JPanel keyboardPanel; // remove "Enter" button from this panel

    private JPanel changePinTextFieldsPanel;

    public Keyboard(int textFieldPanelType) {
        setLayout(new BorderLayout());

        // Login screen = 1
        //

        // Let there be ONE testFieldPanel field
        // if textFieldPanelType = 1
        textFieldsPanel = createTextPanel1();

        // else if textFieldPanelType = 2

        Component[] components = textFieldsPanel.getComponents();


        for (Component component : components){
            if (component.getName() != null && component.getName().equals("userNameField")){
                usernameField = (JTextField) component;
            }
            else if(component.getName() != null && component.getName().equals("pinField")){
                pinField = (JTextField) component;
            }
        }



        JTextField oldPin = new JTextField(20);
        JTextField newPin = new JTextField(20);
        JTextField confirmPin = new JTextField(20);
        changePinTextFieldsPanel = new JPanel();
        changePinTextFieldsPanel.setLayout(new GridLayout(3, 1));
        changePinTextFieldsPanel.add(new JLabel("Old Pin"));
        changePinTextFieldsPanel.add(oldPin);
        changePinTextFieldsPanel.add(new JLabel("New Pin"));
        changePinTextFieldsPanel.add(newPin);
        changePinTextFieldsPanel.add(new JLabel("Confirm New Pin"));
        changePinTextFieldsPanel.add(confirmPin);


        // Create keyboard panel
        keyboardPanel = new JPanel();
        keyboardPanel.setLayout(new GridLayout(4, 10));

        String[] keyLabels = {
                "a", "b", "c", "d", "e", "f", "g", "h", "i", "j",
                "k", "l", "m", "n", "o", "p", "q", "r", "s", "t",
                "u", "v", "w", "x", "y", "z", "0", "1", "2", "3",
                "4", "5", "6", "7", "8", "9", "Enter", "Backspace"
        };

        for (String label : keyLabels) {
            JButton button = new JButton(label);
            button.addActionListener(e -> {
                String buttonText = button.getText();
                if (buttonText.equals("Enter")) {
                    System.out.println("Username: " + usernameField.getText());
                    System.out.println("PIN: " + pinField.getText());
                } else if (buttonText.equals("Backspace")) {
                    String text = usernameField.getText();
                    if (text.length() > 0) {
                        usernameField.setText(text.substring(0, text.length() - 1));
                    }
                } else {
                    usernameField.setText(usernameField.getText() + buttonText);
                }
            });
            keyboardPanel.add(button);
        }

        // Add components to the main panel
        //add(textFieldsPanel, BorderLayout.CENTER);
        //add(keyboardPanel, BorderLayout.SOUTH);
    }

    public int getTextFieldPanelType() {
        return textFieldPanelType;
    }

    public void setTextFieldPanelType(int textFieldPanelType) {
        this.textFieldPanelType = textFieldPanelType;
    }
    public JPanel getTextFieldsPanel(){return textFieldsPanel;}
    public JPanel getKeyboardPanel(){return keyboardPanel;}
    public JPanel getChangePinTextFieldsPanel(){return changePinTextFieldsPanel;}
    public JButton getKeyboardBtn(){return keyboardBtn;}

    public JPanel createTextPanel1(){
        JPanel textPanel1 = new JPanel();

        // Create text fields
        JTextField usernameField = new JTextField(20);
        JTextField pinField = new JTextField(20);
        textPanel1.setLayout(new GridLayout(2, 1));
        textPanel1.add(new JLabel("Username"));
        textPanel1.add(usernameField);
        textPanel1.add(new JLabel("PIN"));
        textPanel1.add(pinField);

        return textPanel1;
    }
    public JPanel createTextPanel2(){
        JPanel textPanel2 = new JPanel();
        return textPanel2;
    }
    public JPanel createTextPanel3(){
        JPanel textPanel3 = new JPanel();
        return textPanel3;
    }
    public JPanel createTextPanel4(){
        JPanel textPanel4 = new JPanel();
        return textPanel4;
    }
    public JPanel createTextPanel5(){
        JPanel textPanel5 = new JPanel();
        return textPanel5;
    }

}
