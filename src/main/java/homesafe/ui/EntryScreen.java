package homesafe.ui;

import homesafe.entity.User;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class EntryScreen {
    private GUIUtils guiUtils;
    private WestPanelButtons westPanelButtons;
    private JPanel eastEmptyPanel = new JPanel();
    private SwitchPanel switchPanel;
    private JPanel panel = new JPanel();

    public EntryScreen(GUIUtils guiUtils, int textFieldPanelType, User user) {
        this.guiUtils = guiUtils;
        westPanelButtons = new WestPanelButtons(guiUtils.frame);
        switchPanel = new SwitchPanel(textFieldPanelType, guiUtils.frame,
                westPanelButtons.getBackButton(), westPanelButtons.getExitDisplayButton(), user, eastEmptyPanel);
        panel.setLayout(new BorderLayout());
        switchPanel.setTextFieldPanelType(textFieldPanelType);
        createPanel();
    }

    private void createPanel() {

        // EAST Panel Setup
        eastEmptyPanel.setLayout(new BoxLayout(eastEmptyPanel, BoxLayout.Y_AXIS));
        eastEmptyPanel.setPreferredSize(new Dimension(200,150));

        // WEST Panel Setup
        westPanelButtons.setBorder(new EmptyBorder(10, 10, 10, 60));
        eastEmptyPanel.setBorder(new EmptyBorder(100, 0, 10, 50));
        panel.add(switchPanel.getSwitchPanel(), BorderLayout.SOUTH);
        panel.add(switchPanel.getTextFieldsPanel(), BorderLayout.CENTER);
        panel.add(westPanelButtons, BorderLayout.WEST);
        panel.add(eastEmptyPanel, BorderLayout.EAST);

        // Button Actions
//        westPanelButtons.getBackButton().addActionListener(e -> {
//            SafeUnlocked safeUnlocked = new SafeUnlocked(guiUtils);
//            guiUtils.switchScreens(safeUnlocked.getPanel());
//        });

        westPanelButtons.getExitDisplayButton().addActionListener(e -> {
            SafeUnlocked safeUnlocked = new SafeUnlocked(guiUtils);
            guiUtils.switchScreens(safeUnlocked.getPanel());
        });
    }

    public SwitchPanel getSwitchPanel(){return switchPanel;}

    public JPanel getPanel(){return panel;}
}
