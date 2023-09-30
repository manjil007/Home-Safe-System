package homesafe.ui;

import javax.swing.*;
import java.awt.*;

public class Keyboard extends JPanel {
    private final JButton keyboardBtn;

    public Keyboard() {
        setLayout(new BorderLayout()); // Use BorderLayout to allow scaling
        keyboardBtn = new JButton("Keyboard");
        keyboardBtn.setPreferredSize(new Dimension(400, 400));
        add(keyboardBtn, BorderLayout.CENTER);
    }
    public JButton getKeyboardBtn(){return keyboardBtn;}
}
