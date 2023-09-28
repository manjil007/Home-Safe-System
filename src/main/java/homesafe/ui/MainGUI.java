package homesafe.ui;

import homesafe.HomeSafe;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class MainGUI {

    public MainGUI() {
        JFrame frame = new JFrame();
        safeLocked lockedPanel = null;
        lockedPanel = new safeLocked();
        JPanel panel = lockedPanel.getPanel();

        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Safe");
        frame.pack();
        frame.setVisible(true);
        frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
    }
}
