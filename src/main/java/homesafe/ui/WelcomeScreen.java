package homesafe.ui;
import homesafe.controller.SensorSimulation;
import homesafe.entity.User;
import homesafe.event.AbstractSafeEvent;
import homesafe.event.DoorEvent;
import homesafe.event.HumidityEvent;
import homesafe.service.AuthenticationService;
import homesafe.service.EventService;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
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

        // Create buttons with preferred size
        JButton mngPINBtn = new JButton();
        guiUtils.createDisplayBtn(mngPINBtn, "Manage PIN", 25);
        mngPINBtn.setBorder((BorderFactory.createLineBorder(Color.white, 10)));

        JButton viewLogsBtn = new JButton();
        guiUtils.createDisplayBtn(viewLogsBtn, "View Logs", 25);
        viewLogsBtn.setBorder((BorderFactory.createLineBorder(Color.white, 10)));

        JButton lockSafeBtn = new JButton();
        guiUtils.createDisplayBtn(lockSafeBtn, "Lock Safe", 25);
       lockSafeBtn.setBorder((BorderFactory.createLineBorder(Color.white, 10)));

        // Add buttons to the menu grid
        menuGbc.gridx = 0;
        menuGbc.gridy = 0;
        menuGrid.add(mngPINBtn, menuGbc);
        menuGbc.gridy = 1;
        menuGrid.add(viewLogsBtn, menuGbc);
        menuGbc.gridy = 2;
        menuGrid.add(lockSafeBtn, menuGbc);

        // WEST PANEL
        WestPanelButtons westButtons = new WestPanelButtons(guiUtils.frame);

        // EAST PANEL
        JPanel emptySidePanel = new JPanel();
        emptySidePanel.setLayout(new GridLayout(10, 1));
        HumidityEvent humidityEvent = new HumidityEvent("humidity", 20);
        JLabel humid = new JLabel("Humidity: ");
        guiUtils.setFont(humid,20);
        emptySidePanel.add(humid);
        JLabel temp = new JLabel("Temperature: ");
        guiUtils.setFont(temp,20);
        emptySidePanel.add(temp);
        JLabel time = new JLabel("Time: ");
        guiUtils.setFont(time,20);
        emptySidePanel.add(time);


        // Adding sub-panels to MAIN PANEL
        westButtons.setBorder(new EmptyBorder(10, 10, 10, 60));
        panel.add(westButtons, BorderLayout.WEST);
        panel.add(menuGrid, BorderLayout.CENTER);
        panel.add(emptySidePanel, BorderLayout.EAST);


        // Button Actions ----------------------------------------------------------------------------------------------
        mngPINBtn.addActionListener(e -> {
            if (AuthenticationService.getCurrentUser().isAdmin()) {
                ManagePinAdmin managePinAdmin = new ManagePinAdmin(guiUtils);
                guiUtils.switchScreens(managePinAdmin.getPanel());
            }
            else {
                EntryScreen entryScreen = new EntryScreen(guiUtils, 2, new User(""));
                guiUtils.switchScreens(entryScreen.getPanel());
            }
        });
        viewLogsBtn.addActionListener(e -> {
            LogScreen logScreen = new LogScreen(guiUtils);
            guiUtils.switchScreens(logScreen.getPanel());
        });
        lockSafeBtn.addActionListener(e -> {
            AuthenticationService.deAuthorizeUser();
            SafeLocked safeLocked = new SafeLocked(guiUtils);
            guiUtils.switchScreens(safeLocked.getPanel());
            DoorEvent event = new DoorEvent(DoorEvent.DOOR_CLOSED, false);
            EventService.getInstance().publishEvent(event);
        });
        westButtons.getBackButton().addActionListener(e -> {
            EntryScreen entryScreen = new EntryScreen(guiUtils, 1, new User(""));
            guiUtils.switchScreens(entryScreen.getPanel());
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
