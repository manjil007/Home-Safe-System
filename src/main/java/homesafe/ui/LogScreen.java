package homesafe.ui;
import homesafe.entity.LogData;
import homesafe.service.AuthenticationService;
import homesafe.service.LogService;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class LogScreen {

    private final GUIUtils guiUtils;
    private final JPanel panel = new JPanel(); // MAIN Panel

    public LogScreen(GUIUtils guiUtils) {
        this.guiUtils = guiUtils;
        panel.setLayout(new BorderLayout());
        createPanel();

    }

    private void createPanel() {

        // if ADMIN
        // ...
        // use fetchAllLogs
        // else
        // ...
        // use fetch

        // Needs to be able to display List<LogData> object
        //LogService.fetchAllLogs();

        // Panel for displaying log info
        JPanel logInfoPanel = new JPanel();
        logInfoPanel.setLayout(new BoxLayout(logInfoPanel, BoxLayout.Y_AXIS));
        logInfoPanel.setBackground(new Color(0, 147, 212));
        logInfoPanel.setPreferredSize(new Dimension(800,500));

        // Populate panel with LogData
        if (AuthenticationService.getCurrentUser().isAdmin()) {
            for (LogData logData : LogService.fetchAllLogs()) {
                JLabel msg = new JLabel(logData.getMessage());
                JLabel user = new JLabel(logData.getUsername());
                JLabel time = new JLabel(String.valueOf(logData.getCreatedAt()));

                guiUtils.setFont(msg, 20);
                guiUtils.setFont(user, 20);
                guiUtils.setFont(time, 20);

                logInfoPanel.add(msg);
                logInfoPanel.add(user);
                logInfoPanel.add(time);
            }
        }
        else {
            for (LogData logData : LogService.fetchAllLogsByUsername(
                    AuthenticationService.getCurrentUser().getUsername())) {
                JLabel msg = new JLabel(logData.getMessage());
                JLabel user = new JLabel(logData.getUsername());
                JLabel time = new JLabel(String.valueOf(logData.getCreatedAt()));

                guiUtils.setFont(msg, 20);
                guiUtils.setFont(user, 20);
                guiUtils.setFont(time, 20);

                logInfoPanel.add(msg);
                logInfoPanel.add(user);
                logInfoPanel.add(time);
            }
        }

        panel.add(logInfoPanel, BorderLayout.CENTER);

        // Back & Exit Display Buttons
        WestPanelButtons westButtons = new WestPanelButtons(guiUtils.frame);
        panel.add(westButtons, BorderLayout.WEST);

        // Button Actions
        westButtons.getBackButton().addActionListener(e -> {
            WelcomeScreen welcomeScreen = new WelcomeScreen(guiUtils);
            guiUtils.switchScreens(welcomeScreen.getPanel());
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
