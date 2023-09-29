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
        double width = screenSize.getWidth();
        double height = screenSize.getHeight();
        int halfHeight = (int)((height/2) - 215);

        JLabel userLabel = new JLabel("Username");
        userLabel.setBounds(500, 500, 100, 100);
        panel.add(userLabel);

        JTextField userText = new JTextField(20);
        guiUtils.createTxtFlds(userText, 575, 525, 100,50, panel);

        JLabel userLabel2 = new JLabel("PIN");
        userLabel2.setBounds(500, 550, 100, 100);
        panel.add(userLabel2);

        JTextField userPin = new JTextField(20);
        guiUtils.createTxtFlds(userPin, 575, 575, 100, 50, panel);

        JButton closeBtn = new JButton("Close");
        guiUtils.setFont(closeBtn, 15);
        guiUtils.createColorBtn(closeBtn, 20, 20, 100, 75, Color.lightGray, panel);

        closeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SafeUnlocked safeUnlocked = new SafeUnlocked(guiUtils);
                guiUtils.switchScreens(safeUnlocked.getPanel());
                DoorEvent event = new DoorEvent(DoorEvent.DOOR_CLOSED, false);
                EventService.getInstance().publishEvent(event);
            }
        });
    }

    public JPanel getPanel() {
        return panel;
    }
}
