package homesafe.ui;
import homesafe.event.DoorEvent;
import homesafe.service.EventService;
import javax.swing.*;
import java.awt.*;


public class WelcomeScreen {

    private final GUIUtils guiUtils;
    private final JPanel panel = new JPanel();


    public WelcomeScreen(GUIUtils guiUtils) {
        this.guiUtils = guiUtils;
        panel.setLayout(new BorderLayout());
        panel.setBackground(Color.white);
        createPanel();
    }

    private void createPanel() {

        // CENTRAL PANEL
        JPanel menuGrid = new JPanel(new GridBagLayout());
        GridBagConstraints menuGbc = new GridBagConstraints();
        menuGbc.fill = GridBagConstraints.BOTH;
        // Button images for menu
        ImageIcon mngPINIcon = new ImageIcon("src/main/resources/images/welcomeScreen/managePIN.png");
        ImageIcon viewLogsIcon = new ImageIcon("src/main/resources/images/welcomeScreen/viewLogs.png");
        ImageIcon lockSafeIcon = new ImageIcon("src/main/resources/images/welcomeScreen/lockSafe.png");
        // Create buttons with preferred size
        JButton mngPINBtn = new JButton(mngPINIcon);
        mngPINBtn.setPreferredSize(new Dimension(150, 80));
        mngPINBtn.setContentAreaFilled(false);
        mngPINBtn.setBorderPainted(false);
        JButton viewLogsBtn = new JButton(viewLogsIcon);
        viewLogsBtn.setPreferredSize(new Dimension(130, 80));
        viewLogsBtn.setContentAreaFilled(false);
        viewLogsBtn.setBorderPainted(false);
        JButton lockSafeBtn = new JButton(lockSafeIcon);
        lockSafeBtn.setPreferredSize(new Dimension(130, 80));
        lockSafeBtn.setContentAreaFilled(false);
        lockSafeBtn.setBorderPainted(false);
        // Add buttons to the menu grid
        menuGbc.gridx = 0;
        menuGbc.gridy = 0;
        menuGrid.add(mngPINBtn, menuGbc);
        menuGbc.gridy = 1;
        menuGrid.add(viewLogsBtn, menuGbc);
        menuGbc.gridy = 2;
        menuGrid.add(lockSafeBtn, menuGbc);

        // WEST PANEL
        WestPanelButtons westButtons = new WestPanelButtons();
        panel.add(westButtons, BorderLayout.WEST);

        // EAST PANEL
        JPanel emptySidePanel = new JPanel();
        emptySidePanel.setLayout(new BoxLayout(emptySidePanel, BoxLayout.Y_AXIS)); // Use BoxLayout with Y_AXIS
        emptySidePanel.setPreferredSize(new Dimension (200,150));


        // Adding sub-panels to MAIN PANEL
        panel.add(westButtons, BorderLayout.WEST);
        panel.add(menuGrid, BorderLayout.CENTER);
        panel.add(emptySidePanel, BorderLayout.EAST);


        // Button Actions ----------------------------------------------------------------------------------------------
        mngPINBtn.addActionListener(e -> {
            // if user is ADMIN, create admin screen
            // else, create non-admin screen (modify PIN only)
        });
        viewLogsBtn.addActionListener(e -> {
            // if user is ADMIN, create log screen, pass admin = true
            // else, create log screen, pass admin = false
            LogScreen logScreen = new LogScreen(guiUtils);
            guiUtils.switchScreens(logScreen.getPanel());
        });
        lockSafeBtn.addActionListener(e -> {
            SafeLocked safeLocked = new SafeLocked(guiUtils);
            guiUtils.switchScreens(safeLocked.getPanel());
            DoorEvent event = new DoorEvent(DoorEvent.DOOR_CLOSED, false);
            EventService.getInstance().publishEvent(event);
        });
        westButtons.getBackButton().addActionListener(e -> {
            LoginScreen loginScreen = new LoginScreen(guiUtils);
            guiUtils.switchScreens(loginScreen.getPanel());
        });
        westButtons.getExitDisplayButton().addActionListener(e -> {
            SafeUnlocked safeUnlocked = new SafeUnlocked(guiUtils);
            guiUtils.switchScreens(safeUnlocked.getPanel());

        });

    }

    public JPanel getPanel() {
        return panel;
    }
}
