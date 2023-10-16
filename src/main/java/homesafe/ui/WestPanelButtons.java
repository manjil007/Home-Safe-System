package homesafe.ui;
import javax.swing.*;
import java.awt.*;

public class WestPanelButtons extends JPanel {
    private final JButton backBtn;
    private final JButton exitDisplay;

    public WestPanelButtons(JFrame frame) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); // Use BoxLayout with Y_AXIS
        GUIUtils guiUtils = new GUIUtils(frame);

        // Button Images for side buttons
      //  ImageIcon backBtnIcon = new ImageIcon("src/main/resources/images/welcomeScreen/back.png");
      //  ImageIcon exitDisplayIcon = new ImageIcon("src/main/resources/images/welcomeScreen/exitDisplay.png");

        // Create side buttons with preferred size
        backBtn = new JButton();
        guiUtils.createDisplayBtn(backBtn, "<--", 35);

        exitDisplay = new JButton();
        guiUtils.createDisplayBtn(exitDisplay, "Exit", 35);

        // Add side buttons to the side button panel
        add(backBtn);
        add(exitDisplay);
    }
    public JButton getBackButton() {
        return backBtn;
    }

    public JButton getExitDisplayButton() {
        return exitDisplay;
    }
}
