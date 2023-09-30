package homesafe.ui;

import javax.swing.*;
import java.awt.*;

public class AdminChangePinGUI extends JFrame {
    JFrame frame = new JFrame();
    Container container = getContentPane();
    JLabel oldPin = new JLabel("OLD PIN");
    JLabel newPin = new JLabel("NEW PIN");
    JLabel confirmNewPin = new JLabel("CONFIRM PIN");

    JTextField oldField = new JTextField(6);
    JPasswordField newField = new JPasswordField(6);
    JPasswordField confirmField = new JPasswordField(6);
    ImageIcon logInicon = new ImageIcon("src/main/resources/images/ButtonIcon/login.png");
    Icon lbackIcon = new ImageIcon("src/main/resources/images/ButtonIcon/back.png");
    Icon zoomIcon = new ImageIcon("src/main/resources/images/ButtonIcon/zoomout.png");
    JButton test = new JButton("LOG IN");
    int width = test.getWidth();
    int height = test.getHeight();
    Image resizedIcon = logInicon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
    JButton loginButton = new JButton(new ImageIcon(resizedIcon));
    JButton backbutton = new JButton(lbackIcon);
    JButton zoomOutButton = new JButton(zoomIcon);
    JCheckBox adminCheckBox = new JCheckBox("Admin");

    //JCheckBox showPIN= new JCheckBox("Show PIN");




    AdminChangePinGUI() {
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
    }

    public void setLayoutManager() {
        container.setLayout(null);
    }

    public void setLocationAndSize() {
        oldPin.setBounds(50, 150, 100, 30);
        newPin.setBounds(50, 185, 100, 30);
        confirmNewPin.setBounds(50, 220, 100, 30);
        oldField.setBounds(150, 150, 150, 30);
        newField.setBounds(150, 185, 150, 30);
        confirmField.setBounds(150, 220, 150, 30);
        adminCheckBox.setBounds(150,250,150,30);
        //showPIN.setBounds(150, 250, 150, 30);
        loginButton.setBounds(150, 300, 100, 30);
        backbutton.setBounds(0, 0, 100, 30);
        zoomOutButton.setBounds(0, 50, 100, 30);

    }

    public void addComponentsToContainer() {
        container.add(oldPin);
        container.add(newPin);
        container.add(confirmNewPin);
        container.add(oldField);
        container.add(newField);
        container.add(confirmField);
        //container.add(showPassword);
        container.add(loginButton);
        container.add(backbutton);
        container.add(zoomOutButton);
        container.add(adminCheckBox);
    }




    public static void main(String[] a) {
        AdminChangePinGUI frame = new AdminChangePinGUI();
        frame.setTitle("Login Form");
        frame.setVisible(true);
        frame.setBounds(10, 10, 370, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

    }

}

