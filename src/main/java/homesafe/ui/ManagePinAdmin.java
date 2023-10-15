package homesafe.ui;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ManagePinAdmin {

    private final GUIUtils guiUtils;
    private final JPanel panel = new JPanel(); // MAIN Panel
    public ManagePinAdmin(GUIUtils guiUtils){
        this.guiUtils = guiUtils;
        panel.setLayout(new BorderLayout());
        panel.setBackground(Color.LIGHT_GRAY);
        createPanel();
    }

    private void createPanel(){
        // Panel for displaying log info
        JPanel pinManagerPanel = new JPanel(new GridLayout(0, 2)); // Use GridLayout to split into two panels

        // Left panel for the first stack of buttons
        JPanel leftButtonStackPanel = new JPanel();
        leftButtonStackPanel.setLayout(new GridLayout(10,0));

        List<JButton> leftButtons = new ArrayList<>();
        Dimension buttonSize = new Dimension(10000, 200);
        for (int i = 1; i <= 10; i++) {
            JButton button = new JButton("User " + i);
            leftButtons.add(button);
            button.setPreferredSize(buttonSize);
            leftButtonStackPanel.add(button);
        }
        pinManagerPanel.add(leftButtonStackPanel);

        // Right panel for the second stack of buttons
        JPanel rightButtonStackPanel = new JPanel();
        rightButtonStackPanel.setLayout(new GridLayout(10,0));
        List<JButton> rightButtons = new ArrayList<>();

        JButton addButton = new JButton("ADD");
        rightButtons.add(addButton);
        addButton.setPreferredSize(buttonSize);
        rightButtonStackPanel.add(addButton);
        JButton modifyButton = new JButton("MODIFY");
        rightButtons.add(modifyButton);
        modifyButton.setPreferredSize(buttonSize);
        rightButtonStackPanel.add(modifyButton);
        JButton deleteButton = new JButton("DELETE");
        rightButtons.add(deleteButton);
        deleteButton.setPreferredSize(buttonSize);
        rightButtonStackPanel.add(deleteButton);

        pinManagerPanel.add(rightButtonStackPanel);
        panel.add(pinManagerPanel, BorderLayout.CENTER);

        // Back & Exit Display Buttons
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

    public JPanel getPanel(){return panel;}
}





