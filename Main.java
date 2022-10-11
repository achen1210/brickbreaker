package brickbreaker;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

/**
 * Class Main is the main client program for brick breaker game management.
 * It creates the JFrame canvas and places the Gameplay class's elements onto it.
 */
public class Main {

    /**
     * Main method; creates JFrame and initializes Gameplay object.
     * @param args A user input option.
     */
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setLayout(new BorderLayout());
        Gameplay game = new Gameplay(new BorderLayout());
        frame.add(game, BorderLayout.CENTER);
        game.setPreferredSize(new Dimension(1003, 503));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Andrew's Brick Breaker Game");
        frame.pack();
        frame.setVisible(true);
        game.requestFocusInWindow();
    }
}
