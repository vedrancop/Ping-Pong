import greenfoot.*;


/**
 * A Ball is a thing that bounces of walls and paddles (or at least i should).
 * 
 * @author The teachers 
 * @version 1
 */
public class Ball extends Actor
{
    private static final int BALL_SIZE = 20;
    private static final int BOUNCE_DEVIANCE_MAX = 5;
    private static final int STARTING_ANGLE_WIDTH = 90;
    private static final int DELAY_TIME = 100;

    private int speed;
    private boolean hasBouncedHorizontally;
    private boolean hasBouncedVertically;
    private boolean hasBouncedFromPaddle;
    private boolean hasBouncedFromRoboPaddle;
    private boolean hasBouncedFromCeiling;
    private int delay;
    private Paddle paddle;
    private int amountOfTimesHit = 0;
    PingWorld thisGame;
    /**
     * Contructs the ball and sets it in motion!
     */
    public Ball()
    {
        init();
    }

    /**
     * Act - do whatever the Ball wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if (delay > 0)
        {
            delay--;
        }
        else
        {
            move(speed);
            checkBounce(); //checks if the ball is hit from the ceiling or from playerpaddle
            checkBounceOffWalls();
            checkBounceOffCeiling();
            checkBounceOffPaddle();
            checkBounceOffRoboPaddle();
            speedUpTheBall(); //after 10 hits, speeds up the ball, gamelevel also increases
            checkRestart();
        }
    }    
    
    private boolean isTouchingPaddle(){
        return (getX() <= BALL_SIZE/2 || getX() >= getWorld().getWidth() - BALL_SIZE/2);
    }
        

    /**
     * Returns true if the ball is touching one of the side walls.
     */
    private boolean isTouchingSides()
    {
        return (getX() <= BALL_SIZE/2 || getX() >= getWorld().getWidth() - BALL_SIZE/2);
    }

    /**
     * Returns true if the ball is touching the ceiling.
     */
    private boolean isTouchingCeiling()
    {
        return (getY() <= BALL_SIZE/2);
    }

    /**
     * Returns true if the ball is touching the floor.
     */
    private boolean isTouchingFloor()
    { 
        return (getY() >= getWorld().getHeight() - BALL_SIZE/2);
    }
    
    /**
     * Check to see if the ball should bounce off one of the walls.
     * If touching one of the walls, the ball is bouncing off.
     */
    private void checkBounceOffWalls()
    {
        if (isTouchingSides())
        {
            if (! hasBouncedHorizontally)
            {
                revertHorizontally();
                Greenfoot.playSound("swoosh.mp3");
            }
        }
        else
        {
            hasBouncedHorizontally = false;
        }
    }

    /**
     * Check to see if the ball should bounce off the ceiling.
     * If touching the ceiling the ball is bouncing off.
     */
    private void checkBounceOffCeiling()
    {
        if (isTouchingCeiling())
        {
            if (! hasBouncedVertically)
            {
                revertVertically();
                thisGame.scoreBoard = thisGame.scoreBoard + 100;
                Greenfoot.playSound("swoosh.mp3");
                hasBouncedFromCeiling = true;   //set to true, so UFO cant bounce it back again on the ceiling after we scored on him
            }
        }
        else
        {
            hasBouncedVertically = false;
        }
    }

    private void checkBounce(){ 
        if (getY() >= 350){
            hasBouncedFromCeiling = false; //after it reaches the middle of the screen, the ball can bounce on the UFO again
        }
    }
    
    public void checkBounceOffPaddle()
    {
        
        if(getOneIntersectingObject(Paddle.class) != null && !hasBouncedFromPaddle)
          {
                revertVertically();
                amountOfTimesHit++;
                Greenfoot.playSound("impact.mp3");
                hasBouncedFromPaddle = true;
        }
        else if (getOneIntersectingObject(Paddle.class) == null) 
        {
            hasBouncedFromPaddle = false;
        }
    }
  
    public void checkBounceOffRoboPaddle()
    {
        
        if(getOneIntersectingObject(RoboPaddle.class) != null &&  !hasBouncedFromRoboPaddle && !hasBouncedFromCeiling)
          {
                revertVertically();
                Greenfoot.playSound("impact.mp3");
                hasBouncedFromRoboPaddle = true;
        }
        else if (getOneIntersectingObject(RoboPaddle.class) == null) 
        {
            hasBouncedFromRoboPaddle = false;
        }
    }
    
    
    private void speedUpTheBall(){
        if (amountOfTimesHit >= 10){
            speed = speed + 1;
            thisGame.gameLevel++;
            amountOfTimesHit = 0;
        }
    }
    
    /**
     * Check to see if the ball should be restarted.
     * If touching the floor the ball is restarted in initial position and speed.
     */
    private void checkRestart()
    {
        if (isTouchingFloor())
        {
            init();
            setLocation(getWorld().getWidth() / 2, getWorld().getHeight() / 2);
            Greenfoot.playSound("fail.mp3");
            thisGame.lifeLevel--;
            if (thisGame.lifeLevel == 0){
                Greenfoot.setWorld(new GameOverWorld());
                thisGame.lifeLevel = 3;
                thisGame.gameLevel = 1;
                thisGame.scoreBoard = 0;
            }
        }
    }

    /**
     * Bounces the ball back from a vertical surface.
     */
    private void revertHorizontally()
    {
        int randomness = Greenfoot.getRandomNumber(BOUNCE_DEVIANCE_MAX)- BOUNCE_DEVIANCE_MAX / 2;
        setRotation((180 - getRotation()+ randomness + 360) % 360);
        hasBouncedHorizontally = true;
    }

    /**
     * Bounces the ball back from a horizontal surface.
     */
    private void revertVertically()
    {
        int randomness = Greenfoot.getRandomNumber(BOUNCE_DEVIANCE_MAX)- BOUNCE_DEVIANCE_MAX / 2;
        setRotation((360 - getRotation()+ randomness + 360) % 360);
        hasBouncedVertically = true;
    }

    /**
     * Initialize the ball settings.
     */
    private void init()
    {
        speed = 2;
        delay = DELAY_TIME;
        hasBouncedHorizontally = false;
        hasBouncedVertically = false;
        setRotation(Greenfoot.getRandomNumber(STARTING_ANGLE_WIDTH)+STARTING_ANGLE_WIDTH/2);
    }

}
