package brickbreaker;

import java.awt.*;

/**
 * Class Bricks holds the Bricks elements of the brick breaker game.
 * It stores the bricks values in an array, and also has a function to draw the bricks.
 */
public class Bricks {

    /**
     * Stores the bricks as an int array, with the int representing the number of hitpoints the brick has left.
     * The first row in the matrix is the topmost row in the brick breaker game, and the
     * last row of the matrix is the bottommost row in the brick breaker game (first to be broken).
     */
    public int bricks[][];

    /**
     * The width and height of each brick.
     */
    public int brickWidth;
    public int brickHeight;


    /**
     * Width and height of the graphics context, to help calculate desired brick width and brick height.
     */
    private int width = 1000;
    private int height = 500;

    /**
     * Creates a new instance of Bricks with the given number of rows and columns.
     *
     * @param rows The number of rows of bricks.
     * @param columns The number of columns of bricks.
     **/
    public Bricks(int rows, int columns)
    {
        bricks = new int[rows][columns];
        for(int i = 0; i < rows; i++)
        {
            for(int j = 0; j < columns; j++)
            {
                //hitpoints of the brick
                //set to increment by 1 hitpoint every 2 rows
                bricks[bricks.length-1-i][j] = (i+2)/2;
            }
        }

        brickWidth = width/columns;
        brickHeight = height/(4*rows);
    }

    /**
     * Draws the bricks the appropriate color on the provided graphics context;
     * Bricks with one hitpoint left are green, those with two left are yellow,
     * and those with 3 or more hitpoints left are red.
     *
     * @param g Graphics object.
     **/
    public void draw(Graphics2D g)
    {
        Color darkRed = new Color(204,0,0);
        Color veryDarkRed = new Color(153,0,0);
        for(int i = 0; i < bricks.length; i++)
        {
            for(int j = 0; j < bricks[0].length; j++)
            {

                //sets color of brick based on remaining hitpoints
                switch(bricks[bricks.length - 1 - i][j])
                {
                    case 0:
                        g.setColor(Color.darkGray);
                        break;
                    case 1:
                        g.setColor(Color.green);
                        break;
                    case 2:
                        g.setColor(Color.orange);
                        break;
                    case 3:
                        g.setColor(Color.red);
                        break;
                    case 4:
                        g.setColor(darkRed);
                        break;
                    default:
                        g.setColor(veryDarkRed);
                        break;
                }

                //draws bricks
                g.fillRect(j * brickWidth, (bricks.length - 1 - i) * brickHeight,
                        brickWidth, brickHeight);

                //draws outlines between bricks
                g.setStroke(new BasicStroke(3));
                g.setColor(Color.darkGray);
                g.drawRect(j * brickWidth, (bricks.length - 1 - i) * brickHeight,
                        brickWidth, brickHeight);

            }
        }
    }
}
