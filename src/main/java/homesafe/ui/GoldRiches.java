package homesafe.ui;

import homesafe.event.DoorEvent;
import homesafe.service.EventService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GoldRiches {
    private JPanel panel = new JPanel();
    private GUIUtils guiUtils;

    public GoldRiches(GUIUtils guiUtils) {
        this.guiUtils = guiUtils;
        panel.setLayout(null);
        panel.setBackground(Color.darkGray);

        createPanel();
    }

    private void createPanel() {
        //Get screen dimensions
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth();
        double height = screenSize.getHeight();

        //Create button
        JButton closeBtn = new JButton("Close");
        guiUtils.setFont(closeBtn, 15);
        guiUtils.createColorBtn(closeBtn, 20, 20, 100, 75, Color.lightGray, panel);

        //Create Gold
        ImageIcon goldIcon = new ImageIcon("src/main/resources/images/goldgoldgold.jpeg");
        goldIcon = guiUtils.resizeImg(goldIcon, (int) width, (int) height);
        JLabel gold = new JLabel(goldIcon);
        gold.setBounds(1, 1, (int) width, (int) height);
        panel.add(gold);

        //Button Actions
        closeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SafeUnlocked safeUnlocked = new SafeUnlocked(guiUtils);
                guiUtils.switchScreens(safeUnlocked.getPanel());
                DoorEvent event = new DoorEvent(DoorEvent.DOOR_CLOSED, false);
                EventService.getInstance().publishEvent(event);
            }
        });
    }

    public JPanel getPanel() {
        return panel;
    }
}

