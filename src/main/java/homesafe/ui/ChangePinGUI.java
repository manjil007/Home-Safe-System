package homesafe.ui;

import javax.swing.*;
import java.awt.*;

public class ChangePinGUI extends JFrame {
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
    JButton loginButton = new JButton(logInicon);
    // Make the button's content area transparent
//    loginButton.setContentAreaFilled(false);
//
//    // Remove the button's border
//    loginButton.setBorderPainted(false);
    JButton backbutton = new JButton(lbackIcon);
    JButton zoomOutButton = new JButton(zoomIcon);


    //JButton resetButton = new JButton("RESET");
    //JCheckBox showPIN= new JCheckBox("Show PIN");


    ChangePinGUI() {
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
        //showPIN.setBounds(150, 250, 150, 30);
        loginButton.setBounds(50, 300, 100, 30);
        backbutton.setBounds(0, 0, 100, 30);
        zoomOutButton.setBounds(0, 50, 100, 30);
        //resetButton.setBounds(200, 300, 100, 30);


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
        //container.add(resetButton);
        container.add(backbutton);
        container.add(zoomOutButton);
    }




    public static void main(String[] a) {
        ChangePinGUI frame = new ChangePinGUI();
        frame.setTitle("Login Form");
        frame.setVisible(true);
        frame.setBounds(10, 10, 370, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

    }

}

