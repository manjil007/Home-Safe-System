package homesafe.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.List;

/**
 * This class was created in order to separate
 * the keyboard from each individual panel. Will have to come
 * up with a more proper name than keyboard2 later.
 *
 * The original keyboard class needs to be renamed something else
 * because at this point it doesn't do keyboard stuff anymore.
 */

public class Keyboard2 extends JPanel {
    private JPanel textFieldsPanel;
    private JPanel keyboardPanel;
    private JTextField focusedField;
    private JButton keyboardBtn;

    private Keyboard mB;

    /**
     * The constructor takes
     * @param textFields
     * the ... means it can take any number of
     * arguments because each panel has a different
     * number of JTextFields
     */
    public Keyboard2(JTextField... textFields) {
        setLayout(new BorderLayout());

        // Create text fields
        textFieldsPanel = new JPanel();
        textFieldsPanel.setLayout(new GridLayout(textFields.length * 2, 1));
        for (JTextField textField : textFields) {
            textFieldsPanel.add(new JLabel(textField.getName()));
            textFieldsPanel.add(textField);
        }

        // Create keyboard panel
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
            //label = String.valueOf(mB.getJLabel());
            button.setFont(new Font("Serif", Font.BOLD, 20));
            button.setPreferredSize(new Dimension(70,50));
            button.addActionListener(e -> {
                String buttonText = button.getText();
                if (buttonText.equals("Enter")) {
                    for (JTextField textField : textFields) {
                        System.out.println(textField.getName() + ": " + textField.getText());
                        // TEST CODE
                        SwingUtilities.invokeLater(new Runnable() {
                            public void run() {
                                // Example usage:
                                PopUpDialog popup = new PopUpDialog("You are entering Manage PIN window");
                                popup.showPopUp();
                            }
                        });
                    }
                } else if (buttonText.equals("Backspace") && focusedField != null && focusedField.getText().length() > 0) {
                    focusedField.setText(focusedField.getText().substring(0, focusedField.getText().length() - 1));
                } else {
                    focusedField.setText(focusedField.getText() + buttonText);
                }
            });
            keyboardPanel.add(button);
        }

        // Add components to the main panel
        add(textFieldsPanel, BorderLayout.CENTER);
        add(keyboardPanel, BorderLayout.SOUTH);
        if (textFields.length > 0) {
            focusedField = textFields[0];
            focusedField.requestFocusInWindow();
        }

        for (JTextField textField : textFields) {
            textField.addFocusListener(new FocusListener() {
                @Override
                public void focusGained(FocusEvent e) {
                    focusedField = (JTextField) e.getSource();
                }

                @Override
                public void focusLost(FocusEvent e) {

                }
            });
        }
    }

    public JPanel getTextFieldsPanel() {
        return textFieldsPanel;
    }

    public JPanel getKeyboardPanel() {
        return keyboardPanel;
    }

    public JButton getKeyboardBtn() {
        return keyboardBtn;
    }
}
