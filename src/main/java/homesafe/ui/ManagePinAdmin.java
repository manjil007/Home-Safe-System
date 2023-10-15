package homesafe.ui;
import homesafe.service.UserService;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ManagePinAdmin {
    private final GUIUtils guiUtils;
    private final JPanel panel = new JPanel(); // MAIN Panel
    public ManagePinAdmin(GUIUtils guiUtils){
        this.guiUtils = guiUtils;
        panel.setLayout(new BorderLayout());
        panel.setBackground(Color.LIGHT_GRAY);
        createPanel();
    }

    private void createPanel(){
        // Panel for displaying log info
        JPanel pinManagerPanel = new JPanel(new GridLayout(0, 2));

        // Right panel for the second stack of buttons
        JPanel rightButtonStackPanel = new JPanel();
        rightButtonStackPanel.setLayout(new GridLayout(10,0));
        List<JButton> rightButtons = new ArrayList<>();
        JButton addButton = new JButton("ADD");
        rightButtons.add(addButton);
        rightButtonStackPanel.add(addButton);
        JButton modifyButton = new JButton("MODIFY");
        rightButtons.add(modifyButton);
        rightButtonStackPanel.add(modifyButton);
        JButton deleteButton = new JButton("DELETE");
        rightButtons.add(deleteButton);
        rightButtonStackPanel.add(deleteButton);


        // Left panel for the first stack of buttons
        JPanel leftButtonStackPanel = new JPanel();
        leftButtonStackPanel.setLayout(new GridLayout(10,0));
        List<JButton> leftButtons = new ArrayList<>();


        for (int i = 1; i <= 10; i++) {
            JButton button = new JButton("User " + i);
            leftButtons.add(button);
            button.addActionListener(e1 -> {
                modifyButton.addActionListener(e2 -> {
                    EntryScreen entryScreen = new EntryScreen(guiUtils,4);
                    guiUtils.switchScreens(entryScreen.getPanel());
                });
                deleteButton.addActionListener(e3 -> {
                    EntryScreen entryScreen = new EntryScreen(guiUtils,5);
                    guiUtils.switchScreens(entryScreen.getPanel());
                });
            });
            leftButtonStackPanel.add(button);
        }
        pinManagerPanel.add(leftButtonStackPanel);
        pinManagerPanel.add(rightButtonStackPanel);

        // Back & Exit Display Buttons
        WestPanelButtons westButtons = new WestPanelButtons(guiUtils.frame);

        // Adding everything to MAIN panel
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
                PopUpDialog popup = new PopUpDialog("User Limit Exceeded: Max 10 Users");
                popup.showPopUp();
            }else {
                EntryScreen entryScreen = new EntryScreen(guiUtils, 3);
                guiUtils.switchScreens(entryScreen.getPanel());
            }
        });

        // Actions if user tries to click modify and delete without
        // first selection a user
//        modifyButton.addActionListener(e -> {
//            PopUpDialog msg = new PopUpDialog("You must choose a user whose PIN you can MODIFY first!");
//            msg.showPopUp();
//        });
//        deleteButton.addActionListener(e -> {
//            PopUpDialog msg = new PopUpDialog("You must choose a user to DELETE first!");
//            msg.showPopUp();
//        });

    }

    public JPanel getPanel(){return panel;}
}





