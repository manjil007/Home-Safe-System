package homesafe.ui;

import homesafe.entity.LogData;
import homesafe.service.LogService;

import javax.swing.*;
import javax.swing.text.Document;
import java.awt.*;

public class LogScreen {

    private final GUIUtils guiUtils;
    private final JPanel panel = new JPanel();
    private final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    public LogScreen(GUIUtils guiUtils) {
        this.guiUtils = guiUtils;
        panel.setLayout(new BorderLayout());
        panel.setBackground(Color.lightGray);
        createPanel();

    }

    private void createPanel() {
        // Needs to be able to display List<LogData> object
        // Side Button BOX LAYOUT --------------------------------------------------------------------------------------
        JPanel sideBtnPanel = new JPanel();
        sideBtnPanel.setLayout(new BoxLayout(sideBtnPanel, BoxLayout.Y_AXIS)); // Use BoxLayout with Y_AXIS

        // Button Images for side buttons
        ImageIcon backBtnIcon = new ImageIcon("src/main/resources/images/welcomeScreen/back.png");
        ImageIcon exitDisplayIcon = new ImageIcon("src/main/resources/images/welcomeScreen/exitDisplay.png");
        // Create side buttons with preferred size
        JButton backBtn = new JButton(backBtnIcon);
        backBtn.setPreferredSize(new Dimension(200, 75)); // Increase the size
        backBtn.setContentAreaFilled(false);
        backBtn.setBorderPainted(false);
        JButton exitDisplay = new JButton(exitDisplayIcon);
        exitDisplay.setPreferredSize(new Dimension(200, 75)); // Increase the size
        exitDisplay.setContentAreaFilled(false);
        exitDisplay.setBorderPainted(false);
        // Add side buttons to the side button panel
        sideBtnPanel.add(backBtn);
        sideBtnPanel.add(exitDisplay);
        // -------------------------------------------------------------------------------------------------------------

        // Add side button panel & menu grid to MAIN PANEL -------------------------------------------------------------
        panel.add(sideBtnPanel, BorderLayout.WEST);


    }


    public JPanel getPanel() {
        return panel;
    }


}
