package homesafe.ui;

import javax.swing.*;
import java.awt.*;

public class WelcomeScreen {

    private GUIUtils guiUtils;
    private JPanel panel = new JPanel();
    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    public WelcomeScreen(GUIUtils guiUtils) {
        this.guiUtils = guiUtils;
        panel.setLayout(null);
        panel.setBackground(Color.lightGray);
        createPanel();
    }

    private void createPanel(){
        //Get screen sizing
        double width = screenSize.getWidth();
        double height = screenSize.getHeight();
        int halfHeight = (int) ((height/2) - 215);

        //Set up button images
        ImageIcon mngPINIcon = new ImageIcon("src/main/resources/images/welcomeScreen/mngPIN.png");
        ImageIcon viewLogsIcon = new ImageIcon("src/main/resources/images/welcomeScreen/viewLogs.png");
        ImageIcon lockSafeIcon = new ImageIcon("src/main/resources/images/welcomeScreen/lockSafe.png");

        //Create buttons
        JButton mngPINBtn = new JButton(mngPINIcon);
        JButton viewLogsBtn = new JButton(viewLogsIcon);
        JButton lockSafeBtn = new JButton(lockSafeIcon);

        guiUtils.createClearBtn(mngPINBtn, 650, 300, 200, 75, panel);
        guiUtils.createClearBtn(viewLogsBtn, 650, 350, 200, 75, panel);
        guiUtils.createClearBtn(lockSafeBtn, 650, 400, 200, 75, panel);

        //Create Background

    }

    public JPanel getPanel() {
        return panel;
    }
}
