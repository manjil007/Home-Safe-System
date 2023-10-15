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
        backBtn.setPreferredSize(new Dimension(200, 75)); // Increase the size
        backBtn.setBackground(new Color(0, 147, 212));
        backBtn.setText("<--");
        guiUtils.setFont(backBtn, 35);
        backBtn.setBorder((BorderFactory.createLineBorder(Color.white, 5)));

        exitDisplay = new JButton();
        exitDisplay.setPreferredSize(new Dimension(200, 75)); // Increase the size
        exitDisplay.setBackground(new Color(0, 147, 212));
        exitDisplay.setText("Exit");
        guiUtils.setFont(exitDisplay, 35);
        exitDisplay.setBorder((BorderFactory.createLineBorder(Color.white, 5)));

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
