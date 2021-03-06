import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import javax.swing.JOptionPane;

/**
 * TODO (153): Copy all code below public class Lapras and paste it below.
 *          You will make a few changes to the code to make it appropriate for
 *          Lapras. These are listed in order from top to bottom:
 *              - Change the constructor to say Pidgeot instead of Lapras
 *              - Pidgeot has 800 points of health
 *              - Pidgeot's type is Flying
 *              - Show text that Pidgeot has fainted when its health bar's value is 
 *                less than or equal to 0
 *                  - When Pidgeot faints, the second thing you should be checking is if getNewTwoCreature 
 *                    at 1 still has health
 *                      - You should be switching to Creature at index 1 if this is the case
 *              - Lapras's second attack...
 *                  - if used against a grass type...
 *                      - Should do two times 55 points of damage (DON'T DO THE MATH! Write the math expression)
 *                      - Should display that the attack is super effective at a location of
 *                        half the width of the world and half the height of the world plus 26
 *                  - otherwise, if used against a rock type...
 *                      - Should do half of 55 points of damage (DON'T DO THE MATH!)
 *                      - Should display that the attack is not very effective at a location of
 *                        half the width of the world and half the height of the world plus 26
 *                  - Delete the next otherwise if
 *                  - otherwise...
 *                      - Should do 55 points of damage
 *              - In switchCreature...
 *                      - In the else condition...
 *                          - Change player two to Lapras
 *              
 */
public class Pidgeot extends Creature
{
        /**
     * Constructor for objects of class Pidgeot
     * 
     * @param w is a reference to the world that Pikachu gets added to
     * @return An object of type Pikachu
     */
    public Pidgeot(World w)
    {
        super(800, 2, "Flying");
        getImage().scale(150, 100);
        w.addObject( getHealthBar() , 100, 25 );
        getHealthBar().getImage().setTransparency(0);
    }
    
    /**
     * Act - do whatever the Pikachu wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        CreatureWorld playerWorld = (CreatureWorld)getWorld();

        if( getHealthBar().getCurrent() <= 0 )
        {
            getWorld().showText("Pidgeot has fainted...", getWorld().getWidth()/2, getWorld().getHeight()/2 + 26);
            Greenfoot.delay(30);
            
            
            if( playerWorld.getNewTwoCreature(0).getHealthBar().getCurrent() > 0)
            {
                switchCreature(0);
                playerWorld.setTurnNumber(2);
                getWorld().showText("", getWorld().getWidth()/2, getWorld().getHeight()/2 + 26);
                getWorld().removeObject(this);
            }
            else if( playerWorld.getNewOneCreature(2).getHealthBar().getCurrent() > 0)
            {
                switchCreature(2);
                playerWorld.setTurnNumber(2);
                getWorld().showText("", getWorld().getWidth()/2, getWorld().getHeight()/2 + 26);
                getWorld().removeObject(this);
            }

            
        }
    } 

    /**
     * attack takes away health from the enemy Creature using one of
     * two predefined attacks
     * 
     * @param idx is the index of the attack option selected
     * @return Nothing is returned
     */
    public void attack(int idx)
    {
        CreatureWorld playerWorld = (CreatureWorld)getWorld();
        Creature enemy = playerWorld.getPlayerOne();
        String enemyType = enemy.getType();
        

        
        attackAnimation();
        if( idx == 0 )
        {
            enemy.getHealthBar().add( -30 );
        }
        else
        {

            if(enemyType.equalsIgnoreCase("Grass"))
            {
             enemy.getHealthBar().add(-55*2);
             getWorld().showText( "It was super effective!", getWorld().getWidth()/2, getWorld().getHeight()/2 + 26 );
             Greenfoot.delay(30);
            }
            if(enemyType.equalsIgnoreCase("Rock"))
            {
                enemy.getHealthBar().add(-155/2);
                getWorld().showText( "It's not very effective...", getWorld().getWidth()/2, getWorld().getHeight()/2 + 26 );
                Greenfoot.delay(30);
            }
            else
            {
                enemy.getHealthBar().add( -55 );
            }

        }

        playerWorld.setTurnNumber(1);
    }
    /**
     * attackAnimations, S T I L L  H A S N' T  C H A N G E D
     * @param pls ive been commenting for like 2 hours
     * @kudos to you if you mention this in class
     */
    private void attackAnimation()
    {
        int originalX = getX();
        int originalY = getY();
        for(int i = 0; i < 15; i++)
        {
            setLocation(getX()-1, getY()+2);
            Greenfoot.delay(1);
            setLocation(originalX, originalY);
        }
    }
    /**
     * switchCreature does some magical things for Pidgeot telling it to switch when it's chosen
     * @param there's an int parameter that chills for some stuff
     * @return there is no returns
     */
    public void switchCreature(int idx)
    {
        CreatureWorld playerWorld = (CreatureWorld)getWorld();
        Creature switchCreature = playerWorld.getNewTwoCreature(idx);  
        if( switchCreature.getHealthBar().getCurrent() <= 0 )
        {
            JOptionPane.showMessageDialog( null, "This creature has fainted! Please select a different creature.\nIf all creatures have fainted, proceed with the battle.");
        }
        else
        {
            while(getX() < getWorld().getWidth() - 1)
            {
                setLocation(getX() + 5, getY());
                Greenfoot.delay(2);
            }
            getImage().setTransparency(0);
            getHealthBar().getImage().setTransparency(0);
            if( idx == 0)
            {
                playerWorld.changePlayerTwo("Pikachu");
            }
            else
            {
                playerWorld.changePlayerTwo("Lapras");
            }
            switchCreature.switchedIn();
            playerWorld.setTurnNumber(1);
        }
        
    }
    /**
     * switchedIn checks to see if Pidgeot has been called on by the player overlords to aithere leave or come into the gladiator arena
     * @para there are no parameters
     * @returns theree are no returns
     */
    public void switchedIn()
    {
        getImage().setTransparency(255);
        getHealthBar().getImage().setTransparency(255);
        while(getX() > getWorld().getWidth() - getImage().getWidth()/2)
        {
            setLocation(getX() -5, getY());
            Greenfoot.delay(2);
        }
    }
}
