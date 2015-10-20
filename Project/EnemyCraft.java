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


public class EnemyCraft extends Sprite
{
	//===================================================================================
	//						Initial Constant And Static Values
	//===================================================================================	
	//private static final Image craftImage = GameEngine.loadImage("src/craft.png");
	
	//========= Craft Dimensions ===========
	//private final int C_WIDTH ;						//The craft width in pixels
	//private final int C_HEIGHT;						//The craft height in pixels
	
	//========= Craft Movement =============
	private final int MOVE_DELAY			= 5;	// The craft movement delay, lower is faster
	//private final int TOP_SPEED;      				// The craft top speed
	
	//========= Craft Parameters ===========
	protected final int HIT_BONUS   = 5;
	
	protected final int moveInterval = 5;
	protected int delay;
	protected ArrayList<Missile> missileList;

	private static final Image craftImage = GameEngine.loadImage("src/craft.png");

	//===================================================================================
	//								Constructor
	//===================================================================================
	public EnemyCraft(int x, int y, int w, int h,Image craftImage, int width, int height)
	{
		super(x, y, w, h, GameEngine.toBufferedImage(craftImage, width, height));
		 
		delay = 0;
		
		//========== Generate Missiles ===========
		missileList = new ArrayList<Missile>();
	}
	
	//===================================================================================
	//								Move
	//===================================================================================
	@Override
	public void move()
	{
		
		
		dx=dx-moveInterval;
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
		}
		catch(Exception e)
		{}
	}
	
	
	//===================================================================================
	//								Draw
	//===================================================================================
    @Override
	public void draw(Graphics g)
    {
    	//drawAngledImage(g, bufImage, angle, locX, locY);
    	
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
    

    
	//===================================================================================
	//								Movement
	//===================================================================================
	
//	public void accelerat()
//	{
//		if (velocity <= TOP_SPEED)
//			velocity += ACCELERATION_SPEED;
//	}
//	
//	public void rotateLeft()
//	{
//		if(angle <= 0)
//			angle = 360-ROTATION_SPEED + angle;
//		else
//			angle -= ROTATION_SPEED;
//	}
//	
//	public void rotateRight()
//	{
//		if(angle >= 360-ROTATION_SPEED)
//			angle = 0+ROTATION_SPEED + (angle - 360);
//		else
//			angle += ROTATION_SPEED;
//	}
	
//	public void fire()
//	{
//		if( (missileList.size() < MAX_MISSILES) || (MAX_MISSILES == 0) )
//			missileList.add( new Missile(locX, locY, pWidth, pHeight, angle) );
//	}
	
	
	//===================================================================================
	//								Getters
	//===================================================================================
//	public int getScore()					{	return score;		}
	public BufferedImage getCraftImage()	{	return bufImage;	}
	
	
	//===================================================================================
	//								To String
	//===================================================================================
	public String toString()
	{
		return ""; //"Craft Angle: " + angle + "   Craft Velocity: " + Math.round(velocity) + "   Missiles: " + missileList.size();
	}
}
