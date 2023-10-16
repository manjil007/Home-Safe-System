package homesafe.ui;

import homesafe.event.DoorEvent;
import homesafe.service.AuthenticationService;
import homesafe.service.EventService;
import homesafe.service.UserService;
import homesafe.entity.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * This class establishes how the  safe will look
 * and act when it is locked.
 */

public class SafeLocked {
    private GUIUtils guiUtils;
    private JPanel panel = new JPanel();
    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    public SafeLocked(GUIUtils guiUtils) {
        this.guiUtils = guiUtils;
        panel.setLayout(null);
        panel.setPreferredSize(screenSize);

        createPanel();
    }

    private void createPanel() {
        //Get screen sizing
        double width = screenSize.getWidth();
        double height = screenSize.getHeight();
        int halfHeight = (int) ((height / 2) - 215);

        //Set up button images
        ImageIcon devIcon = new ImageIcon("src/main/resources/images/devTools.png");
        ImageIcon handleIcon = new ImageIcon("src/main/resources/images/safeHandle.png");
        handleIcon = guiUtils.resizeImg(handleIcon, 350, 350);
        ImageIcon keyIcon = new ImageIcon("src/main/resources/images/keyhole.png");
        keyIcon = guiUtils.resizeImg(keyIcon, 30, 60);

        //Create buttons
        //  JButton devToolsBtn = new JButton(devIcon);
        JButton handleBtn = new JButton(handleIcon);
        JButton displayBtn = new JButton("Locked");
        JButton keyBtn = new JButton(keyIcon);

        guiUtils.setFont(displayBtn, 30);

        //  guiUtils.createClearBtn(devToolsBtn, 20, 20, 75, 75, panel);
        guiUtils.createClearBtn(handleBtn, 100, halfHeight, 350, 350, panel);
        guiUtils.createColorBtn(displayBtn, 550, halfHeight, (int) width - 650, 350, Color.black, panel);
        guiUtils.createClearBtn(keyBtn, (int) (width / 2) - 15, (int) (height / 2) + 200, 30, 60, panel);

        //Create background
        ImageIcon safeBack = new ImageIcon("src/main/resources/images/safeWall.jpg");
        safeBack = guiUtils.resizeImg(safeBack, (int) width, (int) height);
        JLabel safeBkgrnd = new JLabel(safeBack);
        safeBkgrnd.setBounds(1, 1, (int) width, (int) height);
        panel.add(safeBkgrnd);

        //Button Actions
        //Added the (guiUtils)
        displayBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<User> users = UserService.getInstance().getAllUsers();
                EntryScreen entryScreen;

                if (users.isEmpty()) {
                    User user = new User("first_user");
                    user.setAdmin(true);
                    AuthenticationService.setCurrentUser(user);
                    entryScreen = new EntryScreen(guiUtils, 3, new User(""));
                    guiUtils.switchScreens(entryScreen.getPanel());
                    //first user must be an admin
                } else {
                    entryScreen = new EntryScreen(guiUtils, 1, new User(""));
                    guiUtils.switchScreens(entryScreen.getPanel());
                }
            }
        });


        keyBtn.addActionListener(e -> {
            GoldRiches goldRiches = new GoldRiches(guiUtils);
            guiUtils.switchScreens(goldRiches.getPanel());
            DoorEvent event = new DoorEvent(DoorEvent.DOOR_OPENED, true);
            EventService.getInstance().publishEvent(event);
        });
    }


    public JPanel getPanel() {
        return panel;
    }
}


