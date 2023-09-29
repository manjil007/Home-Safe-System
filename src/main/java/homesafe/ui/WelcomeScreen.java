package homesafe.ui;

import homesafe.event.DoorEvent;
import homesafe.service.EventService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WelcomeScreen {

        private GUIUtils guiUtils;
        private JPanel panel = new JPanel();
        private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        public WelcomeScreen(GUIUtils guiUtils) {
            this.guiUtils = guiUtils;
            panel.setLayout(null);
            panel.setBackground(Color.white);
            createPanel();
        }

        private void createPanel(){
            //Get screen sizing
            int halfHeight = (int) ((screenSize.getHeight()/2) - 215);
            int halfWidth = (int) ((screenSize.getWidth()/2) - 215);

            //Set up button images
            ImageIcon mngPINIcon = new ImageIcon("src/main/resources/images/welcomeScreen/mngPIN.png");
            ImageIcon viewLogsIcon = new ImageIcon("src/main/resources/images/welcomeScreen/viewLogs.png");
            ImageIcon lockSafeIcon = new ImageIcon("src/main/resources/images/welcomeScreen/lockSafe.png");

            //Create buttons
            JButton mngPINBtn = new JButton(mngPINIcon);
            JButton viewLogsBtn = new JButton(viewLogsIcon);
            JButton lockSafeBtn = new JButton(lockSafeIcon);

            guiUtils.createClearBtn(mngPINBtn, halfWidth + 100, halfHeight, 200, 75, panel);
            guiUtils.createClearBtn(viewLogsBtn, halfWidth + 100, halfHeight + 50, 200, 75, panel);
            guiUtils.createClearBtn(lockSafeBtn, halfWidth + 100, halfHeight + 100, 200, 75, panel);

            // Button Actions
            mngPINBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // if user is ADMIN, create admin screen
                    // else, create non-admin screen (modify PIN only)
                }
            });
            viewLogsBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // if user is ADMIN, create log screen, pass admin = true
                    // else, create log screen, pass admin = false
                }
            });
            lockSafeBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    SafeLocked safeLocked = new SafeLocked(guiUtils);
                    guiUtils.switchScreens(safeLocked.getPanel());
                    DoorEvent event = new DoorEvent(DoorEvent.DOOR_CLOSED, false);
                    EventService.getInstance().publishEvent(event);

                }
            });

        }

        public JPanel getPanel() {
            return panel;
        }
}
