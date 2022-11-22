package brickbreaker;

import  javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.KeyEvent;

public class Game implements ActionListener{
    /**
     * Holds width and height of Game. Currently unused.
     */
    public static int width;
    public static int height;

    /**
     * JFrame context for the game.
     */
    JFrame frame = new JFrame();

    /**
     * Menubar items, same as in Menu class.
     */
    JMenuBar menuBar = new JMenuBar();
    JMenu fileMenu = new JMenu("File");
    JMenuItem mainMenuOption = new JMenuItem("Main Menu");
    JMenu optionsMenu = new JMenu("Options");
    JMenuItem option1 = new JMenuItem("No Options Right Now!");
    JMenu helpMenu = new JMenu("Help");
    JMenuItem instructionHelp = new JMenuItem("Instructions");

    /**
     * Creates a new instance of Game.
     */
    public Game(){
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        width = (int)size.getWidth()/2;
        height = (int)size.getHeight()/2;

        fileMenu.add(mainMenuOption);
        optionsMenu.add(option1);
        helpMenu.add(instructionHelp);
        menuBar.add(fileMenu);
        menuBar.add(optionsMenu);
        menuBar.add(helpMenu);
        frame.setJMenuBar(menuBar);

        mainMenuOption.addActionListener(this);
        option1.addActionListener(this);
        instructionHelp.addActionListener(this);


        frame.setLayout(new BorderLayout());
        Gameplay game = new Gameplay(frame, width, height, 6, 10);
        JPanel gamePanel = new JPanel();
        gamePanel.setLayout(new BorderLayout());
        gamePanel.add(game, BorderLayout.CENTER);
        frame.add(gamePanel, BorderLayout.CENTER);
        game.setPreferredSize(new Dimension(width, height));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Andrew's Brick Breaker Game");
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        game.requestFocusInWindow();
    }

    /**
     * Dictates what happens when an action is performed on the menubar.
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == mainMenuOption){
            int choice = JOptionPane.showConfirmDialog(null, "You're about to return to the "
                            +"main menu. This will erase the high score of a game currently in progress. Are you sure "
                            + "you want to continue?",
                    "Warning", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if(choice == 0) {
                Menu m = new Menu(width, height);
                frame.dispose();
            }
        }

        if(e.getSource() == option1){
            JOptionPane.showMessageDialog(null, "No options are available yet, sorry!", "Warning",
                    JOptionPane.WARNING_MESSAGE);
        }

        if (e.getSource() == instructionHelp){
            JOptionPane.showMessageDialog(null, "<html><center>" +
                            "<p>Try to score as many points as possible by bouncing" +
                            " the ball off the paddle!<br>" +
                            "Use the left and right arrow keys to move the paddle left and right " +
                            "<br>Each brick broken is worth one point.<br>Press (p) to pause the game." +
                            "</p></html>", "Instructions",
                    JOptionPane.INFORMATION_MESSAGE);
        }

    }
}
