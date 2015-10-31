import java.awt.Color;
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
	private final static int C_WIDTH 		= 50;	//The craft width in pixels
	private final static int C_HEIGHT 		= 25;	//The craft height in pixels
	
	//========= Game Play ==================
	private final int LIVES					= 6;	// The number of craft lives
	private final int HIT_BONUS				= 10;	// The amount of score added by each missile hit

	//========= Craft Parameters ===========
	private int score;
	private ArrayList<Missile> missileList;

	
	//===================================================================================
	//								Constructor
	//===================================================================================
	public Craft(int x, int y, int w, int h)
	{
		super(x, y, w, h, GameEngine.toBufferedImage(craftImage, C_WIDTH, C_HEIGHT));
		 
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
    	//g.drawImage(bufImage, locX + (imageWidth/2), locY + (imageHeight/2), null);
    	g.drawImage(bufImage, locX, locY, null);
    	
    	drawMissiles(g);
    	
    	GameEngine.printText(g,50, 50, 25, "Score: " + score, null);
    	
    	drawLifeBar(g);
    }
	
    private void drawMissiles(Graphics g)
    {
    	for(int i = 0 ; i < missileList.size() ; i++)
    		missileList.get(i).draw(g);
    }
    
    private void drawLifeBar(Graphics g)
    {
    	int barSize = 150;
    	int barLocX = 50; 
    	int barLocY = 60;
    	
    	g.drawRect(barLocX, 	barLocY, 	 barSize, 20);
    	g.drawRect(barLocX + 1, barLocY + 1, barSize, 20);
    	g.setColor(Color.green);    	
    	g.fillRect(barLocX + 1, barLocY + 1, (barSize/LIVES) * lives , 20);
    }
    
    
    
	//===================================================================================
	//								Collisions
    //===================================================================================
    
    public boolean hit(EnemyCraft enemy)
    {
    	if(getBoundingBox().intersects(enemy.getBoundingBox()))
    	{
    		lives--;
    		locX = pWidth/2;
    		locY = pHeight/2;
    		return true;
    	}
    	return false;	
    }
    
    public boolean missileHit(EnemyCraft enemy)
    {
    	for(int i = 0 ; i < missileList.size() ; i++)
    		if(missileList.get(i).getBoundingBox().intersects(enemy.getBoundingBox()))
			{
    			missileList.remove(i);
    			score += HIT_BONUS;
				return true;
			}
    	
    	return false;
    }
      
	//===================================================================================
	//								Movement
	//===================================================================================

	public void fire()
	{
		missileList.add( new Missile(locX+20, locY+15, pWidth, pHeight) );
	}
	
	public void moveUp()	{	if (locY > 0)								locY -= dy;	}
	public void moveDown()	{	if (locY < pHeight - groundHeight - 65)		locY += dy;	}
	public void moveLeft()	{	if (locX > 0)								locX -= dx;	}
	public void moveRight()	{	if (locX < pWidth - 65)						locX += dx;	}
	
	
	
	//===================================================================================
	//								Getters / Setters
	//===================================================================================
	public int getScore()					{	return score;		}
	public BufferedImage getCraftImage()	{	return bufImage;	}
	public void resetCraftLocation()		{	}
	
	//===================================================================================
	//								To String
	//===================================================================================
	public String toString()
	{
		return "Score: " + score;
	}
}
