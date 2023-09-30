package homesafe.ui;
import javax.swing.*;
import java.awt.*;

public class LogScreen {

    private final GUIUtils guiUtils;
    private final JPanel panel = new JPanel();

    public LogScreen(GUIUtils guiUtils) {
        this.guiUtils = guiUtils;
        panel.setLayout(new BorderLayout());
        panel.setBackground(Color.lightGray);
        createPanel();

    }

    private void createPanel() {
        // Needs to be able to display List<LogData> object
        WestPanelButtons westButtons = new WestPanelButtons();
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
