import java.awt.Color;
import java.util.Random;
import java.awt.*;
import java.util.HashSet;
import java.util.Iterator;

/**
 * Class BallDemo - a short demonstration showing animation with the 
 * Canvas class. 
 *
 * @author Michael Kölling and David J. Barnes
 * @version 2011.07.31
 */

public class BallDemo   
{
    private Canvas myCanvas;

    /**
     * Create a BallDemo object. Creates a fresh canvas and makes it visible.
     */
    public BallDemo()
    {
        myCanvas = new Canvas("Ball Demo", 600, 500);
    }

    /**
     * Simulate two bouncing balls
     */
    public void bounce()
    {
        int ground = 400;   // position of the ground line

        myCanvas.setVisible(true);

        // draw the ground
        myCanvas.drawLine(50, ground, 550, ground);

        // crate and show the balls
        BouncingBall ball = new BouncingBall(50, 50, 16, Color.BLUE, ground, myCanvas);
        ball.draw();
        BouncingBall ball2 = new BouncingBall(70, 80, 20, Color.RED, ground, myCanvas);
        ball2.draw();

        // make them bounce
        boolean finished =  false;
        while(!finished) {
            myCanvas.wait(50);           // small delay
            ball.move();
            ball2.move();
            // stop once ball has travelled a certain distance on x axis
            if(ball.getXPosition() >= 550 || ball2.getXPosition() >= 550) {
                finished = true;
            }
        }
    }
    
    public void boxbounce(int numberOfBalls)
    { 
        Random random = new Random();
        HashSet balls = new HashSet();
        myCanvas.setVisible(true);
        
        Rectangle box = new Rectangle (50,50,300,300);
        myCanvas.draw(box);
        
        for (int i = 0; i < numberOfBalls; i++)
        {
            Dimension size = myCanvas.getSize();
            int x = (int) box.getX() + random.nextInt((int) box.getWidth());
            int y = (int) box.getY() + random.nextInt((int) box.getWidth());
            int xSpeed = random.nextInt(30);
            int ySpeed = random.nextInt(30);
            Color color = new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));
            
            BoxBall boxball = new BoxBall(x, y, 16, color, 400, myCanvas);
            balls.add(boxball);
            boxball.draw();
        }
        
        boolean finished =  false;
        while(!finished) {
            myCanvas.wait(50);           // small delay
            Iterator it = balls.iterator();
            while(it.hasNext()) {
                BoxBall ball = (BoxBall) it.next();
                ball.move();
                // stop once ball has travelled a certain distance on x axis
                if(ball.getXPosition() >= 550 + 32*numberOfBalls) {
                    finished = true;
                }
            }
        }
        Iterator it = balls.iterator();
        while(it.hasNext()) {
            BoxBall ball = (BoxBall) it.next();
            ball.erase();
        }

    }
}
