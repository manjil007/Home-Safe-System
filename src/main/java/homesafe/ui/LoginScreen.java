package homesafe.ui;

import javax.swing.*;
import java.awt.*;

public class LoginScreen {

    private GUIUtils guiUtils;
    private JPanel panel = new JPanel();

    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    public LoginScreen(GUIUtils guiUtils) {
        this.guiUtils = guiUtils;
        panel.setLayout(null);
        panel.setBackground(Color.lightGray);
        createPanel();
    }

    private void createPanel() {
        double width = screenSize.getWidth();
        double height = screenSize.getHeight();
        int halfHeight = (int)((height/2) - 215);

        JLabel userLabel = new JLabel("Username");
        userLabel.setBounds(50, 100, 100, 100);
        panel.add(userLabel);

        JTextField userText = new JTextField(20);
        userText.setBounds(100, 100, 200, 100);
    }

    public JPanel getPanel() {
        return panel;
    }
}
