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
    }


    public JPanel getPanel() {
        return panel;
    }


}
