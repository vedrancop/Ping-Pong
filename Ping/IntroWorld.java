import greenfoot.*;

/**
 * Write a description of class IntroWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class IntroWorld extends World
{
    private static final int WORLD_WIDTH = 500;
    private static final int WORLD_HEIGHT = 700;
    private IntroBackground introBackground;
    GreenfootSound gfs = new GreenfootSound("musicbg.mp3"); //main music
        /**
     * Constructor for objects of class IntroWorld.
     */
    public IntroWorld()
    {
        super(WORLD_WIDTH, WORLD_HEIGHT, 1); 
            music();
            intro();
    }
    
    public void music(){
        gfs.setVolume(35);
        gfs.play();
    }
    
    private void intro(){ // initiates GIF for the intropage
        introBackground = new IntroBackground();
        addObject(introBackground, WORLD_WIDTH/2, WORLD_HEIGHT/2);
    }
    
    public void act()
    {
        String key = Greenfoot.getKey();
        if (key != null && key.equals("enter"))
        {
            if(key.equals("enter")){
            gfs.setVolume(0);
            }
            Greenfoot.setWorld(new PingWorld(true));
        }
    }
    
}
