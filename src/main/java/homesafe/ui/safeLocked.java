package homesafe.ui;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class safeLocked {

    private JPanel panel = new JPanel(new BorderLayout());

    public safeLocked(){
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        panel.setLayout(new GridLayout(3, 3));
        panel.setBackground(Color.darkGray);

        createPanel();
    }

    private void createPanel() {
        JButton devTools = new JButton();
        JButton handle = new JButton();
        JButton display = new JButton();

//        panel.add(devTools);
//        panel.add(handle);
//        panel.add(display);
    }

    public JPanel getPanel() {
        return panel;
    }

}
