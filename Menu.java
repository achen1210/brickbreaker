package brickbreaker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Menu implements ActionListener{
    /**
     * Width and height of frame.
     */
    int width;
    int height;

    /**
     * Elements of the menu.
     */
    JFrame frame = new JFrame("Main Menu");
    JPanel top = new JPanel();
    JPanel center = new JPanel();
    JPanel bottom = new JPanel();
    JButton exit = new JButton("Quit");
    JButton start = new JButton("<html><center><h1>Start BrickBreaker Game</h1></center></html>");
    JLabel instructions = new JLabel("<html><center><p><h2>Instructions</h2></p>" +
            "<p>Try to score as many points as possible by bouncing" +
            " the ball off the paddle!<br>" +
            "Use the left and right arrow keys to move the paddle left and right." +
            "<br>Each brick broken is worth one point.<br>" +
            "Press (p) to pause the game.<br>" +
            "Use the color buttons above to customize the colors of the bricks!</p></html>", SwingConstants.CENTER);

    /**
     * Menu Bar elements.
     */
    JMenuBar menuBar = new JMenuBar();
    JMenu fileMenu = new JMenu("File");
    JMenuItem mainMenuOption = new JMenuItem("Main Menu");
    JMenu optionsMenu = new JMenu("Options");
    JMenuItem option1 = new JMenuItem("No Options Right Now!");
    JMenu helpMenu = new JMenu("Help");
    JMenuItem instructionHelp = new JMenuItem("Instructions");

    /**
     * Color Chooser elements.
     */
    static Color veryDarkRed = new Color(153,0,0);
    static Color darkRed = new Color(204,0,0);
    JButton paddlePrimary = new JButton("Paddle Color");
    JButton backgroundColorButton = new JButton("Background Color");
    JButton ballColorButton = new JButton("Ball Color");
    JButton color1Button = new JButton("Color 1");
    JButton color2Button = new JButton("Color 2");
    JButton color3Button = new JButton("Color 3");
    JButton color4Button = new JButton("Color 4");
    JButton color5Button = new JButton("Color 5");
    public static Color paddleColor = Color.green;
    public static Color backgroundColor = Color.darkGray;
    public static Color color1 = Color.GREEN;
    public static Color color2 = Color.ORANGE;
    public static Color color3 = Color.RED;
    public static Color color4 = darkRed;
    public static Color color5 = veryDarkRed;
    public static Color ballColor = Color.WHITE;




    /**
     * Constructs new menu.
     * @param w Width of menu, in pixels.
     * @param h Height of menu, in pixels.
     */
    Menu(int w, int h){
        width = w;
        height = h;
        frame.setLayout(new BorderLayout());
        bottom.setLayout(new BorderLayout());
        center.setLayout(new BorderLayout());
        top.setLayout(new FlowLayout());
        String scoreStr = "<html><h5><center>Current Session High Score: " + Integer.toString(Main.maxScore) + "</html>";
        JLabel score = new JLabel(scoreStr);


        //setup color chooser buttons
        color1Button.addActionListener(this);
        color2Button.addActionListener(this);
        color3Button.addActionListener(this);
        color4Button.addActionListener(this);
        color5Button.addActionListener(this);
        backgroundColorButton.addActionListener(this);
        paddlePrimary.addActionListener(this);
        ballColorButton.addActionListener(this);
        color1Button.setFocusable(false);
        color2Button.setFocusable(false);
        color3Button.setFocusable(false);
        color4Button.setFocusable(false);
        color5Button.setFocusable(false);
        backgroundColorButton.setFocusable(false);
        paddlePrimary.setFocusable(false);
        ballColorButton.setFocusable(false);
        top.add(color1Button);
        top.add(color2Button);
        top.add(color3Button);
        top.add(color4Button);
        top.add(color5Button);
        top.add(backgroundColorButton);
        top.add(paddlePrimary);
        top.add(ballColorButton);


        //setup menu
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


        exit.addActionListener(this);
        start.addActionListener(this);

        center.add(score,BorderLayout.SOUTH);
        center.add(start, BorderLayout.NORTH);
        center.add(instructions, BorderLayout.CENTER);
        bottom.add(exit);

        frame.add(top, BorderLayout.NORTH);
        frame.add(center, BorderLayout.CENTER);
        frame.add(bottom, BorderLayout.SOUTH);
        exit.setFocusable(false);
        start.setFocusable(false);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    /**
     * Enables functionality from user input.
     * @param e ActionEvent.
     */
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == exit)
            System.exit(0);

        if(e.getSource() == start){
            frame.dispose();
            Game g = new Game();
        }

        if(e.getSource() == mainMenuOption){
            JOptionPane.showMessageDialog(null, "You're already at the Main Menu!",
                    "Info", JOptionPane.INFORMATION_MESSAGE);
        }

        if(e.getSource() == option1){
            JOptionPane.showMessageDialog(null, "Work in Progress!", "Warning",
                    JOptionPane.WARNING_MESSAGE);
        }

        if (e.getSource() == instructionHelp){
            JOptionPane.showMessageDialog(null, "<html><center>" +
                    "<p>Try to score as many points as possible by bouncing" +
                    " the ball off the paddle!<br>" +
                    "Use the left and right arrow keys to move the paddle left and right." +
                    "<br>Each brick broken is worth one point.<br>" +
                            "Press (p) to pause the game.</p></html>", "Instructions",
                    JOptionPane.INFORMATION_MESSAGE);
        }

        if(e.getSource() == color1Button){
            color1 = JColorChooser.showDialog(null, "Pick color 1 (the lowest row of bricks)", color1);
        }

        if(e.getSource() == color2Button){
            color2 = JColorChooser.showDialog(null, "Pick color 2", color2);
        }

        if(e.getSource() == color3Button){
            color3 = JColorChooser.showDialog(null, "Pick color 3", color3);
        }

        if(e.getSource() == color4Button){
            color4 = JColorChooser.showDialog(null, "Pick color 4", color4);
        }

        if(e.getSource() == color5Button){
            color5 = JColorChooser.showDialog(null, "Pick color 5", color5);
        }

        if(e.getSource() == backgroundColorButton){
            backgroundColor = JColorChooser.showDialog(null, "Pick background color", backgroundColor);
        }

        if(e.getSource() == paddlePrimary){
            paddleColor = JColorChooser.showDialog(null, "Pick paddle color", paddleColor);
        }

        if(e.getSource() == ballColorButton){
            ballColor = JColorChooser.showDialog(null, "Pick ball color", ballColor);
        }

    }

}
