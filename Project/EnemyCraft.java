import java.awt.Color;
import java.awt.Graphics;
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
	
	//========= Craft Parameters ===========
	protected final int HIT_BONUS   = 5;
	
	protected int initialLives = 0;
	protected int moveInterval = 1;
	protected int delay;

	//===================================================================================
	//								Constructor
	//===================================================================================
	public EnemyCraft(int x, int y, int w, int h, BufferedImage craftImage, int initialLives)
	{
		super(x, y, w, h, craftImage);
		this.initialLives = initialLives;
		delay = 0;
	}
	
	//===================================================================================
	//								Move
	//===================================================================================
	@Override
	public void move()
	{
		locX = locX - moveInterval;
	}
	
	//===================================================================================
	//								Draw
	//===================================================================================
    @Override
	public void draw(Graphics g)
    {
    	//g.drawImage(bufImage, locX + (imageWidth/2), locY + (imageHeight/2), null);
    	g.drawImage(bufImage, locX, locY, null);
    	drawLifeBar(g);
    }

	
    public void hit()
    {
    	lives--;
    	if(lives <= 0)
    		destroy = true;
    }
    
    private void drawLifeBar(Graphics g)
    {
    	int barSize = 30;
    	int barLocX = locX+10; 
    	int barLocY = locY + imageHeight;
    	
    	g.drawRect(barLocX, barLocY, barSize, 5);
    	g.setColor(Color.green);    	
    	g.fillRect(barLocX + 1, barLocY + 1, ((barSize/initialLives) * lives) - 1, 4);
    }
    
	//===================================================================================
	//								Getters
	//===================================================================================
	public BufferedImage getCraftImage()	{	return bufImage;	}
	
	
	//===================================================================================
	//								To String
	//===================================================================================
	public String toString()
	{
		return "";
	}
}
