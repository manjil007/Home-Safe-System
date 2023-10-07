package homesafe.ui;

import javax.swing.*;
import java.awt.*;

public class EntryScreen {
    private WestPanelButtons westPanelButtons = new WestPanelButtons();
    private JPanel eastEmptyPanel = new JPanel();
    private SwitchPanel keyboard;
    private GUIUtils guiUtils;
    private JPanel panel = new JPanel();


    public EntryScreen(GUIUtils guiUtils, int textFieldPanelType) {
        keyboard = new SwitchPanel(textFieldPanelType);
        this.guiUtils = guiUtils;
        panel.setLayout(new BorderLayout());
        panel.setBackground(Color.GREEN);
        keyboard.setTextFieldPanelType(textFieldPanelType);
        createPanel();
    }

    private void createPanel() {

        // EAST Panel Setup
        eastEmptyPanel.setLayout(new BoxLayout(eastEmptyPanel, BoxLayout.Y_AXIS));
        eastEmptyPanel.setPreferredSize(new Dimension(200,150));
        // Create Enter Button object

        panel.add(keyboard.getSwitchPanel(), BorderLayout.SOUTH);
        panel.add(keyboard.getTextFieldsPanel(), BorderLayout.CENTER);
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

    public SwitchPanel getKeyboard(){return keyboard;}

    public JPanel getPanel(){return panel;}
}
