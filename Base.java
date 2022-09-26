// Andrew Chen
// Base.java
// September 2022

import javax.swing.*;  
import java.awt.*;

public class Base {  
    public static void main(String[] args) {  

        //get screen size and create jframe proportional to the screen size
        Dimension size
        = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) size.getWidth();
        int height = (int) size.getHeight();
        JFrame f = new JFrame();
        //JButton b=new JButton("hello andrew");//creating instance of JButton  
        //b.setBounds(130,100,100, 40);//x axis, y axis, width, height  
        //f.add(b);//adding button in JFrame  
        f.setSize(width*2/3, height*2/3); 
        //f.setLayout(null);//using no layout managers  
        f.setVisible(true);
        f.setLocation(width/6, height/6);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setTitle("Brick Breaker");

    }  
}  
