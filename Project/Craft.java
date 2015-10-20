import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;


/**
	|===========================================================|
	|	  	Exercise #  :	 3									|
	|															|
	|   	File name   :	 Craft.java							|
	|		Date		:	 28/08/2015    	      				|
	|		Author    	:	 Shai Pe'er 	(032571580)			|
	|===========================================================|
*/


public class Craft extends Sprite
{
	//===================================================================================
	//						Initial Constant And Static Values
	//===================================================================================	
	private static final Image craftImage = GameEngine.loadImage("src/craft.png");
	
	//========= Craft Dimensions ===========
	private final static int C_WIDTH 		= 25;	//The craft width in pixels
	private final static int C_HEIGHT 		= 25;	//The craft height in pixels
	
	//========= Craft Movement =============
	private final int TOP_SPEED      		= 10;	// The craft top speed
	private final int MOVE_DELAY			= 5;	// The craft movement delay, lower is faster
	private final double FRICTION 			= 0.1;	// The craft friction, need to be lower then 1
	private final int ROTATION_SPEED 		= 2;	// The craft rotation speed
	private final int ACCELERATION_SPEED	= 2;	// The craft acceleration speed
	
	//========= Game Play ==================
	private final int LIVES					= 3;	// The number of craft lives
	private final int HIT_BONUS				= 10;	// The amount of score added by each missile hit
	private final int MAX_MISSILES			= 0; 	// The maximum number of missiles on the screen, 0 is infinite

	//========= Craft Parameters ===========
	private int score;
	private int delay;
	private double velocity;
	private ArrayList<Missile> missileList;

	
	//===================================================================================
	//								Constructor
	//===================================================================================
	public Craft(int x, int y, int w, int h)
	{
		super(x, y, w, h, GameEngine.toBufferedImage(craftImage, C_WIDTH, C_HEIGHT));
		 
		velocity = 0;
		delay = 0;
		score = 0;
		lives = LIVES;
		
		//========== Generate Missiles ===========
		missileList = new ArrayList<Missile>();
	}
	
	//===================================================================================
	//								Move
	//===================================================================================
	@Override
	public void move()
	{
		if(lives >= LIVES)
			destroy = true;
		
		if(delay++ >= MOVE_DELAY)
		{
			velocity -= FRICTION * velocity;
			delay = 0;
		
			//locX += dx;
			//locY += dy;
		}
		moveMissiles();
	}
	
	
	private void moveMissiles()
	{
		try
		{
			Missile missile;
			Iterator<Missile> iter = missileList.iterator();
			while(iter.hasNext())
			{
				missile = iter.next();
				missile.move();
				if(missile.isDestroy())
					iter.remove();
			}
		}catch(Exception e){}
	}
	
	
	//===================================================================================
	//								Draw
	//===================================================================================
    @Override
	public void draw(Graphics g)
    {
    	g.drawImage(bufImage, locX + (imageWidth/2), locY + (imageHeight/2), null);
    	
    	drawMissiles(g);
    }
	
    private void drawMissiles(Graphics g)
    {
    	for(int i = 0 ; i < missileList.size() ; i++)
    		missileList.get(i).draw(g);
    }
    
    
    
	//===================================================================================
	//								Collisions
    //===================================================================================
    
/*    public boolean hit(Missile Mi)
    {
    	if(getBoundingBox().intersects(asteroid.getBoundingBox()))
    	{
    		lives--;
    		locX = pWidth/2;
    		locY = pHeight/2;
    		return true;
    	}
    	return false;	
    }
    
    public boolean missileHit(Asteroid asteroid)
    {
    	for(int i = 0 ; i < missileList.size() ; i++)
    		if(missileList.get(i).getBoundingBox().intersects(asteroid.getBoundingBox()))
			{
    			missileList.remove(i);
    			score += HIT_BONUS;
				return true;
			}
    	
    	return false;
    }
    
    
*/    
	//===================================================================================
	//								Movement
	//===================================================================================
	
	public void accelerat()
	{
		if (velocity <= TOP_SPEED)
			velocity += ACCELERATION_SPEED;
	}
	public void moveUp()
	{
		locY -= dy;
	}
	
	public void moveDown()
	{
		locY += dy;
	}
	public void moveLeft()
	{
		locX -= dx;
	}
	public void moveRight()
	{
		locX += dx;
	}
	
	
	/*	public void fire()
	{
		if( (missileList.size() < MAX_MISSILES) || (MAX_MISSILES == 0) )
			missileList.add( new Missile(locX, locY, pWidth, pHeight) );
	}
	 */
	
	
	//===================================================================================
	//								Getters
	//===================================================================================
	public int getScore()					{	return score;		}
	public BufferedImage getCraftImage()	{	return bufImage;	}
	
	
	//===================================================================================
	//								To String
	//===================================================================================
	public String toString()
	{
		return "";//"Craft Angle: " + angle + "   Craft Velocity: " + Math.round(velocity) + "   Missiles: " + missileList.size();
	}
}
