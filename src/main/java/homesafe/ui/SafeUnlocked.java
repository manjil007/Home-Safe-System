package homesafe.ui;

import homesafe.event.DoorEvent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SafeUnlocked {

    private GUIUtils guiUtils;
    private JPanel panel = new JPanel();
    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    public SafeUnlocked(GUIUtils guiUtils) {
        this.guiUtils = guiUtils;
        panel.setLayout(null);
        panel.setBackground(Color.darkGray);

        createPanel();
    }

    private void createPanel() {
        //Get screen sizing
        double width = screenSize.getWidth();
        double height = screenSize.getHeight();
        int halfHeight = (int) ((height/2) - 215);

        //Set up button images
        ImageIcon devIcon = new ImageIcon("src/main/resources/images/devTools.png");
        ImageIcon handleIcon = new ImageIcon("src/main/resources/images/safeHandle.png");
        handleIcon = guiUtils.resizeImg(handleIcon, 350, 350);
        ImageIcon keyIcon = new ImageIcon("src/main/resources/images/keyhole.png");
        keyIcon = guiUtils.resizeImg(keyIcon, 30, 60);

        //Create buttons
        JButton devToolsBtn = new JButton(devIcon);
        JButton handleBtn = new JButton(handleIcon);
        JButton displayBtn = new JButton("Unlocked");
        JButton keyBtn = new JButton(keyIcon);

        guiUtils.setFont(displayBtn, 30);

        guiUtils.createBtn(devToolsBtn, 20, 20, 75, 75, Color.lightGray, panel);
        guiUtils.createBtn(handleBtn, 100, halfHeight, 350, 350, Color.darkGray, panel);
        guiUtils.createBtn(displayBtn, 550, halfHeight, (int) width - 650, 350, Color.black, panel);
        guiUtils.createBtn(keyBtn, (int) (width / 2) - 15, (int) (height / 2) + 200, 30, 60, Color.darkGray, panel);

        //Button Actions
        handleBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GoldRiches goldRiches = new GoldRiches(guiUtils);
                guiUtils.switchScreens(goldRiches.getPanel());
                DoorEvent event = new DoorEvent(DoorEvent.DOOR_OPENED, true);
            }
        });

        displayBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                WelcomeScreen welcomeScreen = new WelcomeScreen();
                guiUtils.switchScreens(welcomeScreen.getPanel());
            }
        });

        keyBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GoldRiches goldRiches = new GoldRiches(guiUtils);
                guiUtils.switchScreens(goldRiches.getPanel());
                DoorEvent event = new DoorEvent(DoorEvent.DOOR_OPENED, true);
            }
        });
    }

    public JPanel getPanel() {
        return panel;
    }
}
