package homesafe.ui;

import javax.swing.*;
import java.awt.*;

public class MainGUI {
    public MainGUI() {
        JFrame frame = new JFrame();
        GUIUtils guiUtils = new GUIUtils(frame);
        SafeLocked safeLocked = new SafeLocked(guiUtils);
        JPanel panel = safeLocked.getPanel();

        Image windowIcon = Toolkit.getDefaultToolkit().getImage("src/main/resources/images/windowIcon.png");
        frame.setIconImage(windowIcon);
        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Safe");
        frame.pack();
        frame.setVisible(true);
        frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);

    }
}
