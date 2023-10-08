package homesafe.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;
import java.io.IOException;

/**
 * This class was created in order to separate
 * the keyboard from each individual panel. Will have to come
 * up with a more proper name than keyboard2 later.
 *
 * The original keyboard class needs to be renamed something else
 * because at this point it doesn't do keyboard stuff anymore.
 */

public class Keyboard extends JPanel {
    private JPanel textFieldsPanel;
    private JPanel keyboardPanel;
    private JTextField focusedField;
    private JButton[] keyButtons;

    /**
     * The constructor takes
     * @param textFields
     * the ... means it can take any number of
     * arguments because each panel has a different
     * number of JTextFields
     */
    public Keyboard(JTextField... textFields) {
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

        keyButtons = new JButton[keyLabels.length];

        for (int i = 0; i < keyButtons.length; i++) {
            // Label
            keyButtons[i] = new JButton(keyLabels[i]);
            keyButtons[i].setText(keyLabels[i]);

            //Font
            try {
                Font customFont = Font.createFont(Font.TRUETYPE_FONT,
                        new File("src/main/resources/fonts/spectrumFont.ttf")).deriveFont(15F);
                GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
                ge.registerFont(customFont);
                keyButtons[i].setFont(customFont);
            } catch (IOException | FontFormatException e) {
                e.printStackTrace();
            }

            int finalI = i;
            keyButtons[i].addActionListener(e -> {
                String buttonText = keyButtons[finalI].getText();
                if (!buttonText.equals("Enter")) {
                    if (buttonText.equals("Backspace") && focusedField != null && focusedField.getText().length() > 0) {
                        focusedField.setText(focusedField.getText().substring(0, focusedField.getText().length() - 1));
                    } else {
                        keyButtons[finalI].setPreferredSize(new Dimension(70, 50));
                        focusedField.setText(focusedField.getText() + buttonText);
                    }
                }
            });

            keyboardPanel.add(keyButtons[i]);

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

    public JButton getEnterButton(){
        return keyButtons[36];
    }

}
