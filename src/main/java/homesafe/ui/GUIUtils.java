package homesafe.ui;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class GUIUtils {
    JFrame frame;

    public GUIUtils(JFrame frame) {
        this.frame= frame;
    }

    public void createBtn (JButton btn, int x, int y, int width, int height, Color bg, JPanel panel) {
        btn.setBounds(x, y, width, height);
        btn.setBackground(bg);
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        panel.add(btn);
    }

    public void setFont (JComponent comp, float size) {
        try {
            Font customFont = Font.createFont(Font.TRUETYPE_FONT,
                    new File("src/main/resources/fonts/spectrumFont.ttf")).deriveFont(size);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(customFont);
            comp.setFont(customFont);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
    }

    public ImageIcon resizeImg (ImageIcon img, int width, int height) {
        Image image = img.getImage();
        image = image.getScaledInstance(width, height, Image.SCALE_DEFAULT);
        return new ImageIcon(image);
    }

    public void switchScreens (JPanel newPanel){
        JPanel cards = new JPanel(new CardLayout());
        cards.add(newPanel);
        frame.getContentPane().removeAll();
        Container pane = frame.getContentPane();
        pane.add(cards, BorderLayout.CENTER);
        CardLayout cl = (CardLayout) (cards.getLayout());
        cl.next(cards);
    }
}
