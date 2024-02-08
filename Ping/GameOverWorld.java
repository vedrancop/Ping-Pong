import greenfoot.*;

/**
 * Write a description of class IntroWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GameOverWorld extends World
{
    private static final int WORLD_WIDTH = 500;     // X axis
    private static final int WORLD_HEIGHT = 700;    // Y axis

    /**
     * Constructor for objects of class IntroWorld.
     */
    public GameOverWorld()
    {
        super(WORLD_WIDTH, WORLD_HEIGHT, 1); 
        GreenfootImage background = getBackground();
        background.setColor(Color.BLACK);
        background.drawString("GAME OVER hit enter to start again", WORLD_WIDTH / 2 - 100, WORLD_HEIGHT / 2);
    }
    
    public void act()
    {
        String key = Greenfoot.getKey();
        if (key != null && key.equals("enter"))
        {
            Greenfoot.setWorld(new PingWorld(true));
        }
    }
    
}
