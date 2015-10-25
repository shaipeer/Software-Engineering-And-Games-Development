import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;


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
	
	//========= Craft Movement =============
//	private final int MOVE_DELAY			= 5;	// The craft movement delay, lower is faster
	
	//========= Craft Parameters ===========
	protected final int HIT_BONUS   = 5;
	
	protected int moveInterval = 1;
	protected int delay;
	//protected ArrayList<Missile> missileList;

	//===================================================================================
	//								Constructor
	//===================================================================================
	public EnemyCraft(int x, int y, int w, int h,Image craftImage, int width, int height)
	{
		super(x, y, w, h, GameEngine.toBufferedImage(craftImage, width, height));
		 
		delay = 0;
		
		//========== Generate Missiles ===========
		//missileList = new ArrayList<Missile>();
	}
	
	//===================================================================================
	//								Move
	//===================================================================================
	@Override
	public void move()
	{
		locX=locX-moveInterval;
	}
	
	//===================================================================================
	//								Draw
	//===================================================================================
    @Override
	public void draw(Graphics g)
    {
    	g.drawImage(bufImage, locX + (pWidth/2), locY + (pHeight/2), null);
    }

	
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
