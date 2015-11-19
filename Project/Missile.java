/**
	|===========================================================|
	|	  	Exercise #  :	 project							|
	|															|
	|   	File name   :	 Missile.java						|
	|		Date		:	 28/08/2015    	      				|
	|		Author 1   	:	 Shai Pe'er 	(032571580)			|
	|		Author 2   	:	 Denys Bedilov 	(327011813)			|
	|		Author 3   	:	 Rita Markovich	(304492291)			|
	|===========================================================|
 */

import java.awt.Image;


public class Missile extends Sprite 
{
	
	//===================================================================================
	//						Initial Constant And Static Values
	//===================================================================================	
	private static final Image missileImage = GameEngine.loadImage("src/missile.png");
	
	private final static int M_WIDTH 	= 15;		//Pixels per move
	private final static int M_HEIGHT 	= 5;		//Pixels per move
	private final int LIVES				= 1;
	
	
	//===================================================================================
	//								Constructor
	//===================================================================================
	public Missile(int x, int y, int w, int h) 
	{
		super(x, y, w, h, GameEngine.toBufferedImage(missileImage, M_WIDTH, M_HEIGHT));
		
        lives = LIVES;
	}

	
	//===================================================================================
	//								Move
	//===================================================================================
	@Override
	public void move()
	{
		if(lives <= 0 || locX >= pWidth)
			destroy = true;
        
		locX += dx;
	}
	
	
	
	
	
}
