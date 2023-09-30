package homesafe.ui;
import javax.swing.*;
import java.awt.*;

public class WestPanelButtons extends JPanel {
    private final JButton backBtn;
    private final JButton exitDisplay;
    public WestPanelButtons() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); // Use BoxLayout with Y_AXIS

        // Button Images for side buttons
        ImageIcon backBtnIcon = new ImageIcon("src/main/resources/images/welcomeScreen/back.png");
        ImageIcon exitDisplayIcon = new ImageIcon("src/main/resources/images/welcomeScreen/exitDisplay.png");

        // Create side buttons with preferred size
        backBtn = new JButton(backBtnIcon);
        backBtn.setPreferredSize(new Dimension(200, 75)); // Increase the size
        backBtn.setContentAreaFilled(false);
        backBtn.setBorderPainted(false);

        exitDisplay = new JButton(exitDisplayIcon);
        exitDisplay.setPreferredSize(new Dimension(200, 75)); // Increase the size
        exitDisplay.setContentAreaFilled(false);
        exitDisplay.setBorderPainted(false);

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
