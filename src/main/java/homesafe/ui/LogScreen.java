package homesafe.ui;
import homesafe.entity.LogData;
import homesafe.service.AuthenticationService;
import homesafe.service.LogService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.util.ArrayList;
import java.util.Vector;


/**
 * The LogScreen shows the username and the time
 * that the user logged in. It also tracks when new
 * users are created and deleted.
 */

public class LogScreen {

    private final GUIUtils guiUtils;
    private final JPanel panel = new JPanel(); // MAIN Panel

    public LogScreen(GUIUtils guiUtils) {
        this.guiUtils = guiUtils;
        panel.setLayout(new BorderLayout());
        createPanel();

    }

    private void createPanel(){
        String[] columnNames = {"Message", "Time"};
        JTable table;
        //int size = LogService.fetchAllLogsByUsername(AuthenticationService.getCurrentUser().getUsername()).size();
        String[][] data;// = new String[size][2];
        int i = 0;

        if (AuthenticationService.getCurrentUser().isAdmin()) {
            int size = LogService.fetchAllLogs().size();
            data = new String[size][2];
            for (LogData logData : LogService.fetchAllLogs()) {
                int j = 0;
                data[i][j++] = logData.getMessage();
                data[i][j] = String.valueOf(logData.getCreatedAt());
                i++;
            }
        }
        //non-admin
        else {
            int size = LogService.fetchAllLogsByUsername(AuthenticationService.getCurrentUser().getUsername()).size();
            data = new String[size][2];
            for (LogData logData : LogService.fetchAllLogsByUsername(
                    AuthenticationService.getCurrentUser().getUsername())) {
                int j = 0;
                data[i][j++] = logData.getMessage();
                data[i][j] = String.valueOf(logData.getCreatedAt());
                i++;
            }
        }

        table = new JTable(data, columnNames);
        table.setRowHeight(100);
        table.setEnabled(false);
        guiUtils.setFont(table.getTableHeader(), 25);
        guiUtils.setFont(table, 17);
        table.setBounds(30, 40, 200, 300);

        JScrollPane sp = new JScrollPane(table);
        sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        table.getColumnModel().getColumn(0).setPreferredWidth(500);
        panel.add(sp, BorderLayout.CENTER);
        // Back & Exit Display Buttons
        WestPanelButtons westButtons = new WestPanelButtons(guiUtils.frame);
        westButtons.setBorder(new EmptyBorder(10, 10, 10, 60));
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

//    private void createPanel(){
//        JPanel leftPanel = new JPanel();
//        JPanel centerPanel = new JPanel(new BorderLayout());
//        JPanel rightPanel = new JPanel();
//
//
//
//        // Populate panel with LogData
//        //admin
//        if (AuthenticationService.getCurrentUser().isAdmin()) {
//            for (LogData logData : LogService.fetchAllLogs()) {
//                JLabel msg = new JLabel(logData.getMessage());
//                JLabel time = new JLabel(String.valueOf(logData.getCreatedAt()));
//
//                guiUtils.setFont(msg, 20);
//                guiUtils.setFont(time, 20);
//
//
//                centerPanel.add(msg);
//                rightPanel.add(time);
//            }
//        }
//        //non-admin
//        else {
//            for (LogData logData : LogService.fetchAllLogsByUsername(
//                    AuthenticationService.getCurrentUser().getUsername())) {
//                JLabel msg = new JLabel(logData.getMessage());
//                JLabel time = new JLabel(String.valueOf(logData.getCreatedAt()));
//
//                guiUtils.setFont(msg, 20);
//                guiUtils.setFont(time, 20);
//
//
//                centerPanel.add(msg);
//                rightPanel.add(time);
//            }
//        }
//
//        panel.add(centerPanel, BorderLayout.CENTER);
//        panel.add(rightPanel, BorderLayout.EAST);
//
//        // Back & Exit Display Buttons
//        WestPanelButtons westButtons = new WestPanelButtons(guiUtils.frame);
//        panel.add(westButtons, BorderLayout.WEST);
//
//        // Button Actions
//        westButtons.getBackButton().addActionListener(e -> {
//            WelcomeScreen welcomeScreen = new WelcomeScreen(guiUtils);
//            guiUtils.switchScreens(welcomeScreen.getPanel());
//        });
//        westButtons.getExitDisplayButton().addActionListener(e -> {
//            SafeUnlocked safeUnlocked = new SafeUnlocked(guiUtils);
//            guiUtils.switchScreens(safeUnlocked.getPanel());
//
//        });
//
//    }

    private void createPanel1() {

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
        logInfoPanel.setPreferredSize(new Dimension(800,500));
        String heading = "     Message                         Time";
        JLabel head = new JLabel(heading);
        guiUtils.setFont(head,25);
        logInfoPanel.add(head);


        // Populate panel with LogData
        //admin
        if (AuthenticationService.getCurrentUser().isAdmin()) {
            for (LogData logData : LogService.fetchAllLogs()) {
                String msg = logData.getMessage() + ": " + logData.getCreatedAt();
                JLabel logMsg = new JLabel(msg);

                guiUtils.setFont(logMsg, 20);
                logInfoPanel.add(logMsg);
            }
        }
        //non-admin
        else {
            for (LogData logData : LogService.fetchAllLogsByUsername(
                    AuthenticationService.getCurrentUser().getUsername())) {
                String msg = logData.getMessage() + ": " + logData.getCreatedAt();
                JLabel logMsg = new JLabel(msg);

                guiUtils.setFont(logMsg, 20);
                logInfoPanel.add(logMsg);
            }
        }

        panel.add(logInfoPanel, BorderLayout.CENTER);

        // Back & Exit Display Buttons
        WestPanelButtons westButtons = new WestPanelButtons(guiUtils.frame);
        westButtons.setBorder(new EmptyBorder(10, 10, 10, 60));
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
