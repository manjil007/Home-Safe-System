package homesafe.ui;

import homesafe.event.DoorEvent;
import homesafe.service.EventService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        int halfHeight = (int) ((screenSize.getHeight()/2) - 215);
        int halfWidth = (int) ((screenSize.getWidth()/2) - 215);

        JLabel userLabel = new JLabel("Username");
        userLabel.setBounds(halfWidth, halfHeight, 100, 100);
        panel.add(userLabel);

        JTextField userText = new JTextField(20);
        guiUtils.createTxtFlds(userText, halfWidth + 75, halfHeight + 25, 100,50, panel);

        JLabel userLabel2 = new JLabel("PIN");
        userLabel2.setBounds(halfWidth, halfHeight + 50, 100, 100);
        panel.add(userLabel2);

        JTextField userPin = new JTextField(20);
        guiUtils.createTxtFlds(userPin, halfWidth + 75, halfHeight + 75, 100, 50, panel);

        JButton closeBtn = new JButton("Close");
        guiUtils.setFont(closeBtn, 15);
        guiUtils.createColorBtn(closeBtn, 20, 20, 100, 75, Color.lightGray, panel);

        JButton enterBtn = new JButton("Enter");
        guiUtils.setFont(enterBtn, 15);
        guiUtils.createColorBtn(enterBtn, halfWidth + 200, halfHeight + 70, 100, 75, Color.lightGray, panel);

        closeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SafeUnlocked safeUnlocked = new SafeUnlocked(guiUtils);
                guiUtils.switchScreens(safeUnlocked.getPanel());
                DoorEvent event = new DoorEvent(DoorEvent.DOOR_CLOSED, false);
                EventService.getInstance().publishEvent(event);
            }
        });

        enterBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Bypassing authentication for now
                WelcomeScreen welcomeScreen = new WelcomeScreen(guiUtils);
                guiUtils.switchScreens(welcomeScreen.getPanel());

            }
        });
    }

    public JPanel getPanel() {
        return panel;
    }
}
