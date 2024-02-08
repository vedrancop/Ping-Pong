import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class IntroBackground here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class IntroBackground extends Actor
{
    GifImage introGif = new GifImage("newbg.gif");
    /**
     * Act - do whatever the IntroBackground wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        setImage( introGif.getCurrentImage() );
    }
}
