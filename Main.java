package brickbreaker;

import java.awt.*;



/**
 * Class Main is the main client program for brick breaker game management.
 * It creates the JFrame canvas and places the Gameplay class's elements onto it.
 */
public class Main {
    /**
     * Width and height of game.
     */
    public static int width;
    public static int height;

    /**
     * Max score of current session.
     */
    public static int maxScore = 0;

    /**
     * Main method; creates JFrame and initializes Gameplay object.
     * @param args A user input option.
     */
    public static void main(String[] args) {
        Dimension size
                = Toolkit.getDefaultToolkit().getScreenSize();

        width = (int)size.getWidth()/2;
        height = (int)size.getHeight()/2;

        Menu m = new Menu(width/2, height/2);

    }
}
