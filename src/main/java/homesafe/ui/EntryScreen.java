package homesafe.ui;

import homesafe.entity.User;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * This class serves the purpsose of calling the switchPanel class,
 * which has each individual screen base on what the user wishses to do.
 * Establishes the west and east panels (where most buttons will be)
 *
 * Should probably consolidate the class with switchPanel
 */

public class EntryScreen {
    private GUIUtils guiUtils;
    private WestPanelButtons westPanelButtons;
    private JPanel eastEmptyPanel = new JPanel();
    private SwitchPanel switchPanel;
    private JPanel panel = new JPanel();

    /**
     * Constrcutor for EntryScreen
     * @param guiUtils
     * @param textFieldPanelType
     * @param user
     */
    public EntryScreen(GUIUtils guiUtils, int textFieldPanelType, User user) {
        this.guiUtils = guiUtils;
        westPanelButtons = new WestPanelButtons(guiUtils.frame);
        switchPanel = new SwitchPanel(textFieldPanelType, guiUtils.frame,
                westPanelButtons.getBackButton(), westPanelButtons.getExitDisplayButton(), user, eastEmptyPanel);
        panel.setLayout(new BorderLayout());
        switchPanel.setTextFieldPanelType(textFieldPanelType);
        createPanel();
    }

    /**
     * Method to create panel
     */
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

        westPanelButtons.getExitDisplayButton().addActionListener(e -> {
            SafeUnlocked safeUnlocked = new SafeUnlocked(guiUtils);
            guiUtils.switchScreens(safeUnlocked.getPanel());
        });
    }

    /**
     * Method to get the panel created above
     */
    public JPanel getPanel(){return panel;}
}
