package homesafe.ui;

import javax.swing.*;
import java.awt.*;

public class Keyboard extends JPanel {
    private JButton keyboardBtn;
    private JButton userNameEntry;
    private JButton pinEntry;

    //private JTextField usernameField;
    //private JTextField pinField;
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
        // textFieldPanel = createTextFieldPanelOne()
        // else if textFieldPanelType = 2


        // Create text fields
        JTextField usernameField = new JTextField(20);
        JTextField pinField = new JTextField(20);
        textFieldsPanel = new JPanel();
        textFieldsPanel.setLayout(new GridLayout(2, 1));
        textFieldsPanel.add(new JLabel("Username"));
        textFieldsPanel.add(usernameField);
        textFieldsPanel.add(new JLabel("PIN"));
        textFieldsPanel.add(pinField);

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
}
