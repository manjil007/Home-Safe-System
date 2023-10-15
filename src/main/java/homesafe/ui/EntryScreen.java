package homesafe.ui;

import javax.swing.*;
import java.awt.*;

public class EntryScreen {
    private WestPanelButtons westPanelButtons = new WestPanelButtons();
    private JPanel eastEmptyPanel = new JPanel();
    private SwitchPanel switchPanel;
    private GUIUtils guiUtils;
    private JPanel panel = new JPanel();


    public EntryScreen(GUIUtils guiUtils, int textFieldPanelType) {
        switchPanel = new SwitchPanel(textFieldPanelType, guiUtils.frame);
        this.guiUtils = guiUtils;
        panel.setLayout(new BorderLayout());
        switchPanel.setTextFieldPanelType(textFieldPanelType);
        createPanel();
    }

    private void createPanel() {

        // EAST Panel Setup
        eastEmptyPanel.setLayout(new BoxLayout(eastEmptyPanel, BoxLayout.Y_AXIS));
        eastEmptyPanel.setPreferredSize(new Dimension(200,150));
        // Create Enter Button object

        panel.add(switchPanel.getSwitchPanel(), BorderLayout.SOUTH);
        panel.add(switchPanel.getTextFieldsPanel(), BorderLayout.CENTER);
        panel.add(westPanelButtons, BorderLayout.WEST);
        panel.add(eastEmptyPanel, BorderLayout.EAST);

        // Button Actions
        westPanelButtons.getBackButton().addActionListener(e -> {
            SafeUnlocked safeUnlocked = new SafeUnlocked(guiUtils);
            guiUtils.switchScreens(safeUnlocked.getPanel());
        });

        westPanelButtons.getExitDisplayButton().addActionListener(e -> {
            SafeUnlocked safeUnlocked = new SafeUnlocked(guiUtils);
            guiUtils.switchScreens(safeUnlocked.getPanel());
        });
    }

    public SwitchPanel getSwitchPanel(){return switchPanel;}

    public JPanel getPanel(){return panel;}
}
