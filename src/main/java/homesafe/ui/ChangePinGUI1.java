//package homesafe.ui;
//
//import homesafe.event.DoorEvent;
//import homesafe.service.EventService;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//public class ChangePinGUI1 extends Keyboard{
//    private GUIUtils guiUtils;
//    private JPanel panel = new JPanel();
//
//    public ChangePinGUI1(GUIUtils guiUtils) {
//        this.guiUtils = guiUtils;
//        panel.setLayout(new BorderLayout());
//        panel.setBackground(Color.lightGray);
//        createPanel();
//    }
//
//    /**
//     * The createPanel() method just creates the panel
//     * the user sees once they reach the login screen
//     */
//
//    private void createPanel() {
//
//        // Keyboard + Entry Buttons
//        // Back + Display Exit Buttons
//        WestPanelButtons westButtons = new WestPanelButtons();
//
//        /**
//         * Commenting this chunck of code out for the time being
//         * because we're adding westPanelButton object. We'll see
//         * how that works, may come back to this later.
//         */
//
//        JPanel emptySidePanel = new JPanel();
//        emptySidePanel.setLayout(new BoxLayout(emptySidePanel, BoxLayout.Y_AXIS)); // Use BoxLayout with Y_AXIS
//        emptySidePanel.setPreferredSize(new Dimension (200,150));
//
//        panel.add(westButtons, BorderLayout.WEST);
//        panel.add(emptySidePanel, BorderLayout.EAST);
//        panel.add(getTextFieldsPanel(), BorderLayout.CENTER);
//        panel.add(getKeyboardPanel(), BorderLayout.SOUTH);
//
//        // Button Actions
//        westButtons.getBackButton().addActionListener(e -> {
//            SafeUnlocked safeUnlocked = new SafeUnlocked(guiUtils);
//            guiUtils.switchScreens(safeUnlocked.getPanel());
//        });
//
//        westButtons.getExitDisplayButton().addActionListener(e -> {
//            SafeUnlocked safeUnlocked = new SafeUnlocked(guiUtils);
//            guiUtils.switchScreens(safeUnlocked.getPanel());
//
//
//        });
//
//    }
//
//
//
//    public JPanel getPanel() {
//        return panel;
//    }
//
//    public static void main(String[] args){
//        JFrame frame = new JFrame();
//        GUIUtils guiUtils = new GUIUtils(frame);
//        ChangePinGUI1 pin = new ChangePinGUI1(guiUtils);
//        JPanel panel = pin.getPanel();
//        frame.add(panel, BorderLayout.CENTER);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setTitle("Safe");
//        frame.pack();
//        frame.setVisible(true);
//        frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
//    }
//}
//
