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
    JFrame frame;

    //width and height of JFrame
    private int width;
    private int height;

    //number of rows and columns for the bricks
    private int brickrows;
    private int brickcolumns;

    //play is true when game is active, and whether game has been started, and whether game is paused
    private boolean play = false;
    private boolean started = false;
    private boolean paused = false;


    //tracks score
    private int score = 0;

    //tracks number of bricks left
    private int bricksleft = brickrows * brickcolumns;

    //timer object to generate KeyEvents
    private Timer timer;

    // frame refresh rate, in milliseconds
    private int delay = 1;

    //paddle x location
    private int paddle;

    //paddle speed
    public int paddleSpeed = 40;

    //paddle width, height, and y location
    private int paddleWidth;
    private int paddleHeight;
    private int paddleY;

    //radius of ball
    private int ballSizeFactor;
    private int ballRad;

    //x and y location of ball
    private int ballX;
    private int ballY;

    //velocity of ball
    private int ballXdir = 0;
    private int ballYdir = 0;

    //bricks object used in the current game
    private Bricks currBricks;

    /**
     * Creates a new instance of Gameplay, and initializes Timer object to create KeyAction events.
     **/
    public Gameplay(JFrame f, int w, int h, int r, int c)
    {
        width = w;
        height = h;
        brickrows = r;
        brickcolumns = c;
        frame = f;

        paddleWidth = width /10;
        paddleHeight = height/50;
        paddle = width/2 - paddleWidth/2;
        paddleY = height-4*paddleHeight;
        ballSizeFactor = 100;
        ballRad = width/ballSizeFactor;
        ballX = width/2 - ballRad/2;
        ballY = height/2;


        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        currBricks = new Bricks(brickrows, brickcolumns, w, h);
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
        g.setColor(Menu.backgroundColor);
        g.fillRect(0,0, width, height);

        //draw bricks
        currBricks.draw((Graphics2D) g);

        /*write score
        g.setColor(Color.yellow);
        g.setFont(new Font("SANS_SERIF", Font.BOLD, height/30));
        g.drawString(""+score, height - height/30, width - height/30); */


        //color borders
        g.setColor(Color.lightGray);
        g.fillRect(0, 0, 3, height);
        g.fillRect(0, 0, width, 3);
        g.fillRect(width-3, 0, 3, height);
        g.setColor(Color.red);
        g.fillRect(0, height-3, width,3);

        //color paddle
        g.setColor(Color.yellow);
        g.fillRect(paddle, paddleY, paddleWidth, paddleHeight);
        g.setColor(Menu.paddleColor);
        g.fillRect(paddle+10, paddleY, paddleWidth-20, paddleHeight);

        //color ball
        g.setColor(Menu.ballColor);
        g.fillOval(ballX, ballY, ballRad, ballRad);

        Graphics2D g2d = (Graphics2D) g;

        //if game has not started
        if(!started)
        {
            g.setColor(Color.RED);
            g.setFont(new Font("Arial",Font.BOLD, 20));

            //below process helps center text
            String startMsg = "Press (Enter) to Start the Game!";
            int startSize = (int) g2d.getFontMetrics().getStringBounds(startMsg, g2d).getWidth();
            g.drawString(startMsg, width/2 - startSize/2,3*height/4);
        }

        else if(paused)
        {
            g.setColor(Color.RED);
            g.setFont(new Font("Arial",Font.BOLD, 20));

            //below process helps center text
            String p = "PAUSED";
            int startSize = (int) g2d.getFontMetrics().getStringBounds(p, g2d).getWidth();
            g.drawString(p, width/2 - startSize/2,3*height/4);
        }

        //if game has been won
        else if(bricksleft <= 0)
        {
            play = false;
            started = false;
            ballXdir = 0;
            ballYdir = 0;

            g.setColor(Color.RED);
            g.setFont(new Font("Arial",Font.BOLD, 30));

            //below process helps center text
            String won = "You Won, Score: " + score;
            int wonSize = (int) g2d.getFontMetrics().getStringBounds(won, g2d).getWidth();
            g.drawString(won, width/2 - wonSize/2 ,2*height/3);

            g.setColor(Color.RED);
            g.setFont(new Font("Arial",Font.BOLD, 20));

            String restart = "Press (Enter) to Restart";
            int restartSize = (int) g2d.getFontMetrics().getStringBounds(restart, g2d).getWidth();
            g.drawString(restart, width/2 - restartSize/2, 3 * height/4);
        }

        //if the ball has hit the bottom and the game has been lost
        else if(ballY >= height - 3 - ballRad)
        {
            play = false;
            started = false;
            ballXdir = 0;
            ballYdir = 0;

            g.setColor(Color.RED);
            g.setFont(new Font("Arial",Font.BOLD, 30));

            //below process helpers center text
            String gOver = "Game Over, Score: " + score;
            int gOverSize = (int) g2d.getFontMetrics().getStringBounds(gOver, g2d).getWidth();
            g.drawString(gOver, width/2 - gOverSize/2 ,2*height/3);

            g.setColor(Color.RED);
            g.setFont(new Font("Arial",Font.BOLD, 20));

            String restart = "Press (Enter) to Restart";
            int restartSize = (int) g2d.getFontMetrics().getStringBounds(restart, g2d).getWidth();
            g.drawString(restart, width/2 - restartSize/2, 3 * height/4);

            String mainMenu = "Press (m) to return to Main Menu";
            int mMSize = (int)g.getFontMetrics().getStringBounds(mainMenu, (Graphics2D) g).getWidth();
            int mMVert = (int)g.getFontMetrics().getStringBounds(mainMenu, (Graphics2D) g).getHeight();
            g.drawString(mainMenu, width/2 - mMSize/2,3*height/4+mMVert);
        }

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
            paddle = (paddle >= width - 3 - paddleWidth) ? width - 3 - paddleWidth : paddle +  width/paddleSpeed;
        }

        //if paddle has hit left wall, then set paddle location just touching right wall, else moves paddle right
        if(e.getKeyCode() == KeyEvent.VK_LEFT)
        {
            //play = true;
            paddle = (paddle <= 3) ? 3 : paddle - width/paddleSpeed;
        }

        //if game should be paused
        if(started && e.getKeyCode() == KeyEvent.VK_P)
        {
            paused = paused == true ? false : true;
            play = paused == true ? false : true;
        }

        //if game is not in play, enter starts the game from initial conditions
        if (e.getKeyCode() == KeyEvent.VK_ENTER)
        {
            Main.maxScore = Math.max(Main.maxScore, score);

            if (!started)
                started = true;

            if (!play) {
                play = true;
                Random rand = new Random();
                ballX = ballRad + rand.nextInt(width - 2*ballRad);
                ballY = height * 3/4;
                if (rand.nextInt(2) == 0)
                    ballXdir = -(3 + rand.nextInt(1));
                else
                    ballXdir = (3 + rand.nextInt(1));
                ballYdir = -(3 + rand.nextInt(1));
                paddle = width/2 - paddleWidth/2;
                score = 0;
                bricksleft = brickrows * brickcolumns;
                currBricks = new Bricks(brickrows, brickcolumns, width, height);

                repaint();
            }
        }

        if(!play && e.getKeyCode() == KeyEvent.VK_M)
        {
            Main.maxScore = Math.max(Main.maxScore, score);
            Menu m = new Menu(width, height);
            frame.dispose();
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

        if(paused){
            repaint();
        }

        else if(play)
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

            if(ballY > height - 3 - ballRad)
                play = false;

            //collision with paddle
            if(new Rectangle(ballX,ballY,ballRad,ballRad).intersects
                    (new Rectangle(paddle, paddleY, paddleWidth, paddleHeight)))
            {
                ballYdir = ballYdir > 0 ? -ballYdir : ballYdir;
            }

            brickCollision();

            repaint();
        }
    }

    /**
     * Changes ball direction if it hits a brick. Calculates which brick would be hit and attacks it directly.
     **/
    public void brickCollision()
    {
        int[][] bricks = currBricks.bricks;

        if(ballY <= bricks.length * currBricks.brickHeight) {
            int xIndex = ballX / currBricks.brickWidth;
            int yIndex = ballY / currBricks.brickHeight - 1;

            if(xIndex < bricks[0].length && yIndex < bricks.length && bricks[yIndex][xIndex] > 0){
                bricks[yIndex][xIndex]--;

                if (bricks[yIndex][xIndex] == 0) {
                    score += (bricks.length - 1 - yIndex + 2) / 2;
                    bricksleft--;
                }

                //ideally we can check if ball goes off the side of the brick, have not implemented this feature yet
                ballYdir = ballYdir < 0 ? -ballYdir : ballYdir;
            }
        }
    }
}
