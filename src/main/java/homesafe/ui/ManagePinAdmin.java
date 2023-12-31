package homesafe.ui;
import homesafe.entity.User;
import homesafe.service.UserService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This class allows an administrator to manage their own pin/account
 * and the pins/account of other users. The administrator can  add users,
 * modify their pins or delete them entirely.
 */

public class ManagePinAdmin {
    private final GUIUtils guiUtils;
    private final JPanel panel = new JPanel(); // MAIN Panel

    //constructor
    public ManagePinAdmin(GUIUtils guiUtils){
        this.guiUtils = guiUtils;
        panel.setLayout(new BorderLayout());
        panel.setBackground(Color.LIGHT_GRAY);
        createPanel();
    }

    /**
     * Creates a panel for managing user PINs with a graphical user interface.
     */
    private void createPanel(){
        // Panel for displaying log info
        JPanel pinManagerPanel = new JPanel(new GridLayout(0, 2));

        // Right panel for the second stack of buttons
        JPanel rightButtonStackPanel = new JPanel();
        rightButtonStackPanel.setLayout(new GridLayout(11,0)); //added an extra row in case
        rightButtonStackPanel.setBorder(new EmptyBorder(20, 20, 20, 60));

        JLabel actionListLabel = new JLabel("Actions");
        guiUtils.setFont(actionListLabel, 25);
        rightButtonStackPanel.add(actionListLabel);

        JButton addButton = new JButton();
        guiUtils.createDisplayBtn(addButton, "ADD", 20);
        rightButtonStackPanel.add(addButton);

        JButton modifyButton = new JButton();
        guiUtils.createDisplayBtn(modifyButton, "MODIFY", 20);
        rightButtonStackPanel.add(modifyButton);

        JButton deleteButton = new JButton();
        guiUtils.createDisplayBtn(deleteButton, "DELETE", 20);
        rightButtonStackPanel.add(deleteButton);

        // Left panel for the first stack of buttons
        JPanel leftButtonStackPanel = new JPanel();
        leftButtonStackPanel.setLayout(new GridLayout(11,0));
        leftButtonStackPanel.setBorder(new EmptyBorder(20, 0, 20, 20));

        JLabel userListLabel = new JLabel("Users");
        guiUtils.setFont(userListLabel, 25);
        leftButtonStackPanel.add(userListLabel);

        List<User> users = UserService.getInstance().getAllUsers();
        JButton[] buttons = new JButton[users.size()];

        for (int i = 0; i < users.size(); i++) {
            buttons[i] = new JButton();
            buttons[i].setText(users.get(i).getUsername());
            guiUtils.setFont(buttons[i], 20);
            buttons[i].setBackground(new Color(0, 147, 212));
            buttons[i].setBorder((BorderFactory.createLineBorder(Color.white, 5)));
            leftButtonStackPanel.add(buttons[i]);

            int finalI = i;
            buttons[i].addActionListener(e1 -> {
                for (int j = 0; j < users.size(); j++) {
                    buttons[j].setBorder((BorderFactory.createLineBorder(Color.white, 5)));
                }

                buttons[finalI].setBorder((BorderFactory.createLineBorder(new Color(0, 147, 212), 5)));

                modifyButton.addActionListener(e2 -> {
                    EntryScreen entryScreen = new EntryScreen(guiUtils,4, users.get(finalI));
                    guiUtils.switchScreens(entryScreen.getPanel());
                });
                deleteButton.addActionListener(e3 -> {
                    EntryScreen entryScreen = new EntryScreen(guiUtils,5, users.get(finalI));
                    guiUtils.switchScreens(entryScreen.getPanel());
                });
            });
        }
        pinManagerPanel.add(leftButtonStackPanel);
        pinManagerPanel.add(rightButtonStackPanel);

        // Back & Exit Display Buttons
        WestPanelButtons westButtons = new WestPanelButtons(guiUtils.frame);

        // Adding everything to MAIN panel
        westButtons.setBorder(new EmptyBorder(10, 10, 10, 60));
        panel.add(westButtons, BorderLayout.WEST);
        panel.add(pinManagerPanel, BorderLayout.CENTER);

        // Button Actions
        westButtons.getBackButton().addActionListener(e -> {
            WelcomeScreen welcomeScreen = new WelcomeScreen(guiUtils);
            guiUtils.switchScreens(welcomeScreen.getPanel());
        });
        westButtons.getExitDisplayButton().addActionListener(e -> {
            SafeUnlocked safeUnlocked = new SafeUnlocked(guiUtils);
            guiUtils.switchScreens(safeUnlocked.getPanel());
        });
        addButton.addActionListener(e -> {
            if (UserService.getInstance().getAllUsers().size() >= 10){
                PopUpDialog popup = new PopUpDialog("User Limit Exceeded: Max 10 Users.");
                popup.showPopUp();
            }else {
                EntryScreen entryScreen = new EntryScreen(guiUtils, 3, new User(""));
                guiUtils.switchScreens(entryScreen.getPanel());
            }
        });
    }

    public JPanel getPanel(){return panel;}
}





