package homesafe.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * When a user is finished entering a username and pin,
 * entering the wrong amount of numbers for a pin/ or when they
 * are locked out. This class will send a pop-up with a unique
 * message depending on the situation.
 */

public class PopUpDialog extends JDialog {

    //constructor
    public PopUpDialog(String message) {
        // Set dialog properties
        setTitle("Custom PopUp");
        setSize(300, 150);
        setLocationRelativeTo(null); // Center the dialog on the screen
        setLayout(new FlowLayout());

        // Create and customize components
        JLabel messageLabel = new JLabel(message);
        JButton okButton = new JButton("OK");

        // Add components to the dialog
        add(messageLabel);
        add(okButton);

        // Add action listener for the OK button
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the dialog when the OK button is clicked
            }
        });
    }

    //Method to display the pop-up
    public void showPopUp() {
        setVisible(true);
    }
}

