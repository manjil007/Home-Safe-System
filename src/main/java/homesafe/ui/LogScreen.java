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

    /**
     * Creates a panel for displaying logs for both admin and non-admin users with a graphical user interface.
     */
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

    public JPanel getPanel() {
        return panel;
    }
}
