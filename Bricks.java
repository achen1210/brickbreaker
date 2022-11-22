//Andrew Chen
//Bricks.java
//Creates bricks for Brick Breaker game
//October 2022

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
     * Number determining how tall to make each brick.
     */
    private int heightFactor = 3;

    /**
     * Sets how often to increment brick's durability, e.g. 2 -> brick's hitpoint(s) increases by 1 every 2 rows.
     */
    private int incrementRange = 2;

    /**
     * Width and height of the graphics context, to help calculate desired brick width and brick height.
     */
    private int width;
    private int height;

    /**
     * Creates a new instance of Bricks with the given number of rows and columns.
     *
     * @param rows The number of rows of bricks.
     * @param columns The number of columns of bricks.
     **/
    public Bricks(int rows, int columns, int w, int h)
    {
        bricks = new int[rows][columns];
        width = w;
        height = h;
        for(int i = 0; i < rows; i++)
        {
            for(int j = 0; j < columns; j++)
            {
                //hitpoints of the brick
                //set to increment by 1 hitpoint every 2 rows
                bricks[bricks.length-1-i][j] = (i+incrementRange)/incrementRange;
            }
        }


        brickWidth = (width-6)/columns;
        brickHeight = height/(heightFactor*rows);
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
        for(int i = 0; i < bricks.length; i++)
        {
            for(int j = 0; j < bricks[0].length; j++)
            {
                //draws outlines between bricks
                g.setStroke(new BasicStroke(3));
                g.setColor(Menu.backgroundColor);
                g.drawRect(3 + j * brickWidth, (bricks.length - 1 - i) * brickHeight,
                        brickWidth, brickHeight);

                //sets color of brick based on remaining hitpoints
                switch(bricks[bricks.length - 1 - i][j])
                {
                    case 0:
                        g.setColor(Menu.backgroundColor);
                        break;
                    case 1:
                        g.setColor(Menu.color1);
                        break;
                    case 2:
                        g.setColor(Menu.color2);
                        break;
                    case 3:
                        g.setColor(Menu.color3);
                        break;
                    case 4:
                        g.setColor(Menu.color4);
                        break;
                    default:
                        g.setColor(Menu.color5);
                        break;
                }

                //draws bricks; j and i are swapped, because j is associated with columns, which is x position,
                // and i is associated with rows, which is y position
                g.fillRect(3 + j * brickWidth, (bricks.length - 1 - i) * brickHeight,
                        brickWidth, brickHeight);

                if(j == bricks[0].length - 1)
                    g.fillRect(width - 6, (bricks.length - 1 - i) * brickHeight, 3, brickHeight);
            }
        }
    }
}
