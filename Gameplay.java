//Andrew Chen
//Gameplay.java
//Code for Brick Breaker gameplay
//October 2022

package brickbreaker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;



/**
 * Class Gameplay holds the gameplay elements of the brick breaker game.
 * It implements functions from KeyListener and ActionListener to respond appropriately to user input.
 */
public class Gameplay extends JPanel implements KeyListener, ActionListener, MouseListener {

    //width and height of JFrame
    private int width = 1000;
    private int height = 500;

    //number of rows and columns for the bricks
    private int brickrows = 6;
    private int brickcolumns = 10;

    //play is true when game is active, and whether game has been started
    private boolean play = false;
    private boolean started = false;

    //tracks score
    private int score = 0;

    //tracks number of bricks left
    private int bricksleft = brickrows * brickcolumns;

    //timer object to generate KeyEvents
    private Timer timer;

    // frame refresh rate, in milliseconds
    private int delay = 6 ;

    //paddle x location
    private int paddle = width/2;

    //paddle width, height, and y location
    private int paddleWidth = width /10;
    private int paddleHeight = height/50;
    private int paddleY = height-4*paddleHeight;

    //radius of ball
    private int ballRad = width/100;

    //x and y location of ball
    private int ballX = 500;
    private int ballY = 250;

    //velocity of ball
    private int ballXdir = 0;
    private int ballYdir = 0;

    //bricks object used in the current game
    private Bricks currBricks;

    /**
     * Creates a new instance of Gameplay, and initializes Timer object to create KeyAction events.
     **/
    public Gameplay(LayoutManager layout)
    {
        JPanel panel = new JPanel(layout);
        currBricks = new Bricks(brickrows, brickcolumns);
        addKeyListener(this);
        setFocusable(true);
        this.requestFocusInWindow();
        setFocusTraversalKeysEnabled(false);
        timer = new Timer(delay, this);
        timer.start();
    }

    /**
     * Creates a new instance of Gameplay, and initializes Timer object to create KeyAction events.
     *
     * @param g Graphics object.
     **/
    public void paint(Graphics g){
        //color background
        g.setColor(Color.darkGray);
        g.fillRect(0,0, width, height);

        //draw bricks
        currBricks.draw((Graphics2D) g);

        //write score
        g.setColor(Color.yellow);
        g.setFont(new Font("SANS_SERIF", Font.BOLD, height/30));
        g.drawString(""+score, height - height/30, width - height/30);


        //color borders
        g.setColor(Color.lightGray);
        g.fillRect(0, 0, 3, height);
        g.fillRect(0, 0, width, 3);
        g.fillRect(width, 0, 3, height);
        g.setColor(Color.red);
        g.fillRect(0, height, width,3);

        //color paddle
        g.setColor(Color.yellow);
        g.fillRect(paddle, paddleY, paddleWidth, paddleHeight);
        g.setColor(Color.green);
        g.fillRect(paddle+10, paddleY, paddleWidth-20, paddleHeight);

        //color ball
        g.setColor(Color.white);
        g.fillOval(ballX, ballY, ballRad, ballRad);

        //if game has not started
        if(!started)
        {
            g.setColor(Color.RED);
            g.setFont(new Font("Arial",Font.BOLD, 20));
            g.drawString("Press (Enter) to Start the Game!", width/3,height/3);
        }

        //if game has been won
        if(bricksleft <= 0)
        {
            play = false;
            ballXdir = 0;
            ballYdir = 0;
            g.setColor(Color.RED);
            g.setFont(new Font("Arial",Font.BOLD, 30));
            g.drawString("You Won, Score:"+score, width/3,height/3);

            g.setColor(Color.RED);
            g.setFont(new Font("Arial",Font.BOLD, 20));
            g.drawString("Press (Enter) to Restart", width/3,height/2);
        }

        //if the ball has hit the bottom and the game has been lost
        if(ballY > paddleY)
        {
            play = false;
            ballXdir = 0;
            ballYdir = 0;
            g.setColor(Color.RED);
            g.setFont(new Font("Arial",Font.BOLD, 30));
            g.drawString("Game Over, Score: "+score, width/3,height/3);

            g.setColor(Color.RED);
            g.setFont(new Font("Arial",Font.BOLD, 20));
            g.drawString("Press (Enter) to Restart", width/3,height/2);
        }

        //g.dispose();
    }

    /**
     * keyTyped not used in this application.
     *
     * @param e KeyEvent.
     **/
    @Override
    public void keyTyped(KeyEvent e) {}

    /**
     * keyReleased not used in this application.
     *
     * @param e KeyEvent.
     **/
    @Override
    public void keyReleased(KeyEvent e) {}

    /**
     * Moves paddle left when left arrow key is pressed, moves paddle right when right arrow key is pressed,
     * and starts/restarts game when enter key is pressed.
     *
     * @param e KeyEvent.
     **/
    @Override
    public void keyPressed(KeyEvent e) {

        timer.start();
        //if paddle has hit right wall, then set paddle location just touching right wall, else moves paddle right
        if(e.getKeyCode() == KeyEvent.VK_RIGHT)
        {
            //play = true;
            paddle = (paddle >= width - paddleWidth) ? width - paddleWidth : paddle +  width/40;
        }

        //if paddle has hit right wall, then set paddle location just touching right wall, else moves paddle right
        if(e.getKeyCode() == KeyEvent.VK_LEFT)
        {
            //play = true;
            paddle = (paddle <= 3) ? 3 : paddle - width/40;
        }

        //if game is not in play, enter starts the game from initial conditions
        if (e.getKeyCode() == KeyEvent.VK_ENTER)
        {
            if (!started)
                started = true;

            if (!play) {
                play = true;
                Random rand = new Random();
                ballX = ballRad + rand.nextInt(width - 2*ballRad);
                ballY = 250;
                if (rand.nextInt(2) == 0)
                    ballXdir = -(3 + rand.nextInt(1));
                else
                    ballXdir = (3 + rand.nextInt(1));
                ballYdir = -(3 + rand.nextInt(1));
                paddle = width/2;
                score = 0;
                bricksleft = brickrows * brickcolumns;
                currBricks = new Bricks(brickrows, brickcolumns);

                repaint();
            }
        }
    }

    /**
     * Regains focus when mouse is clicked on the frame.
     *
     * @param e MouseEvent.
     **/
    @Override
    public void mouseClicked(MouseEvent e) {
        requestFocusInWindow();
    }

    /**
     * Not used in this application.
     *
     * @param e MouseEvent.
     **/
    public void mouseEntered(MouseEvent e){}

    /**
     * Not used in this application.
     *
     * @param e MouseEvent.
     **/
    public void mousePressed(MouseEvent e){}

    /**
     * Not used in this application.
     *
     * @param e MouseEvent.
     **/
    public void mouseExited(MouseEvent e){}

    /**
     * Not used in this application.
     *
     * @param e MouseEvent.
     **/
    public void mouseReleased(MouseEvent e){}


    /**
     * Dictates game behavior - changes ball direction when it hits a wall, a brick, or the paddle.
     *
     * @param e ActionEvent.
     **/
    @Override
    public void actionPerformed(ActionEvent e)
    {
        timer.start();
        if(play)
        {
            ballX += ballXdir;
            ballY += ballYdir;

            //reflect ball when hits a wall
            if(ballX <= ballRad)
                ballXdir = -ballXdir;

            if(ballY <= ballRad)
                ballYdir = -ballYdir;

            if(ballX >= width - ballRad)
                ballXdir = -ballXdir;

            if(ballY > paddleY)
                play = false;

            //collision with paddle
            if(new Rectangle(ballX,ballY,ballRad,ballRad).intersects
                    (new Rectangle(paddle, paddleY, paddleWidth, paddleHeight)))
            {
                ballYdir = -ballYdir;
            }

            brickCollision(ballX,ballY);

            repaint();
        }
    }

    /**
     * Changes ball direction if it hits a brick.
     *
     * @param ballXpos x coordinate of the ball.
     * @param ballYpos y coordinate of the ball.
     **/
    public void brickCollision(int ballXpos, int ballYpos)
    {
        int[][] bricks = currBricks.bricks;
        checker:
        for(int i = 0; i < bricks.length; i++)
        {
            for(int j = 0; j < bricks[0].length; j++)
            {
                int brickPosX = j * currBricks.brickWidth;
                int brickPosY = i * currBricks.brickHeight; //(bricks.length - 1 - i) * currBricks.brickHeight;
                Rectangle thisbrick = new Rectangle(brickPosX, brickPosY,
                        currBricks.brickWidth, currBricks.brickHeight);

                //approximates ball as a Rectangle to use intersects method
                Rectangle ballAsRect = new Rectangle(ballXpos, ballYpos,
                        ballRad, ballRad);

                if (thisbrick.intersects(ballAsRect) && bricks[i][j] > 0)
                {
                    bricks[i][j]--;
                    //if brick was eliminated
                    if (bricks[i][j] == 0) {
                        score += (bricks.length-1-i + 2)/2;
                        bricksleft--;
                    }

                    ballYdir = ballYdir < 0 ? -ballYdir : ballYdir;
                    /* attempt at making ball go off sides of brick;
                        work still in progress

                    int vertGap;
                    int horGap;

                    if (ballYdir > 0)
                        vertGap = ballYpos + ballRad - brickPosY;
                    else
                        vertGap = brickPosY + currBricks.brickHeight - (ballYpos - ballRad);

                    if (ballXdir > 0)
                        horGap = ballXpos > brickPosX ? 0 : (ballXpos + ballRad) - brickPosX;
                    else
                        horGap = ballXpos < brickPosX + currBricks.brickWidth ? 0 :
                                currBricks.brickWidth - (ballX - ballRad);

                    if (vertGap >= horGap) ballYdir = -ballYdir;
                    else ballXdir = -ballXdir;
                    */

                    break checker;
                }
            }
        }
    }
}
