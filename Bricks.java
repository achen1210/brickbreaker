//Andrew Chen
//September 2022
//Brick generator for brickbreaker

package brickbreaker;

import javax.swing.*;  
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.*;


public class Bricks {
    public int bricks[][];
    public int brickWidth;
    public int brickHeight;

    private int width;
    private int height;

    public Bricks(int rows, int columns, int w, int h)
    {
        width = w;
        height = h;
        bricks = new int[rows][columns];
        for(int i = 0; i < rows; i++)
        {
            for(int j = 0; j < columns; j++)
            {
                //hitpoints of the brick
                //set to increment by 1 hitpoint every 2 rows
                bricks[i][j] = (i+2)/2;
            }
        }

        brickWidth = width/columns;
        brickHeight = height/(4*rows);
    }

    public void draw(Graphics2D g)
    {
        Color darkRed = new Color(204,0,0);
        Color veryDarkRed = new Color(153,0,0);
        for(int i = 0; i < bricks.length; i++)
        {
            for(int j = 0; j < bricks[0].length; j++)
            {
                switch(bricks[i][j])
                {
                    case 0: {}//brick gone        
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

                g.fillRect(j * brickWidth, i * brickHeight, 
                           brickWidth, brickHeight);
                
                g.setStroke(new BasicStroke(3));
				g.setColor(Color.darkGray);
				g.drawRect(j * brickWidth, i * brickHeight,
                           brickWidth, brickHeight);

            }
        }
    }

    public void setBrickValue(int row, int col, int val)
    {
        bricks[row][col] = val;
    }

}
