import java.awt.*;
import java.awt.geom.*;

/**
 * Write a description of class BoxBall here.
 * 
 * @author Nathan Pierre
 * @version 2011.07.31
 * 10/26/
 */
public class BoxBall
{
    private static final int GRAVITY = 3;  // effect of gravity

    private int ballDegradation = 2;
    private Ellipse2D.Double circle;
    private Color color;
    private int diameter;
    private int xPosition;
    private int yPosition;
    private final Rectangle walls;      //boundries of the Rectangle box
    private Canvas canvas;
    private int ySpeed;                // initial downward speed
    private int xSpeed;


     /**
     * Constructor for objects of class BouncingBall
     *
     * @param xPos  the horizontal coordinate of the ball
     * @param yPos  the vertical coordinate of the ball
     * @param ballDiameter  the diameter (in pixels) of the ball
     * @param ballColor  the color of the ball
     * @param groundPos  the position of the ground (where the wall will bounce)
     * @param drawingCanvas  the canvas to draw this ball on
     */
    public BoxBall(int xPos, int yPos, int xBallSpeed, int yBallSpeed, int ballDiameter, Color ballColor,
                        Rectangle wallBounds, Canvas drawingCanvas)
    {
        xPosition = xPos + 3;
        yPosition = yPos + 4;
        xSpeed = xBallSpeed;
        ySpeed = yBallSpeed;
        color = ballColor;
        diameter = ballDiameter;
        walls = wallBounds;
        canvas = drawingCanvas;
    }
    
     /**
     * Draw this ball at its current position onto the canvas.
     **/
    public void draw()
    {
        canvas.setForegroundColor(color);
        canvas.fillCircle(xPosition, yPosition, diameter);
    }
    
    /**
     * Erase this ball at its current position.
     **/
    public void erase()
    {
        canvas.eraseCircle(xPosition, yPosition, diameter);
    }
    
    /**
     * Move this ball according to its position and speed and redraw.
     **/
    public void move()
    {
        // remove from canvas at the current position
        erase();
            
        // compute new position
        yPosition += ySpeed;
        xPosition += xSpeed;

        // check if the ball has hit the boundries of the wall
        if(yPosition >= (walls.getMaxX() - diameter) && ySpeed > 0) {
            yPosition = (int)(walls.getMaxY() - diameter);
            ySpeed = -ySpeed + ballDegradation;
        }else if(yPosition <= (walls.getMinY()) && ySpeed < 0) {
            yPosition = (int)(walls.getMinY());
            ySpeed = -ySpeed + ballDegradation;
        }else if(xPosition >= (walls.getMaxX()) && xSpeed > 0) {
            xPosition = (int)(walls.getMaxX() - diameter);
            xSpeed = -xSpeed + ballDegradation;
        }else if(xPosition <= (walls.getMinX()) && xSpeed <0) {
            xPosition = (int)(walls.getMinX());
            xSpeed = -xSpeed + ballDegradation;
        }
                                                                                                                                                                                                                                                                                                                        
        
        // draw again at new position
        draw();
    } 
    
     /**
     * return the horizontal position of this ball
     */
    public int getXPosition()
    {
        return xPosition;
    }

    /**
     * return the vertical position of this ball
     */
    public int getYPosition()
    {
        return yPosition;
    }
}
