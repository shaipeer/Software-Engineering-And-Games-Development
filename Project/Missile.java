import java.awt.Image;

/**
	|===========================================================|
	|	  	Exercise #  :	 3									|
	|															|
	|   	File name   :	 Missile.java						|
	|		Date		:	 28/08/2015    	      				|
	|		Author    	:	 Shai Pe'er 	(032571580)			|
	|===========================================================|
*/


public class Missile extends Sprite 
{
	
	//===================================================================================
	//						Initial Constant And Static Values
	//===================================================================================	
	private static final Image missileImage = GameEngine.loadImage("src/missile.png");
	
	private final static int M_SPEED 	= 3;		//Pixels per move
	private final int M_MAX_DISTANCE 	= 800;		//How long the missile will go in pixels   
	
	private final static int M_WIDTH 	= 15;		//Pixels per move
	private final static int M_HEIGHT 	= 5;		//Pixels per move
	private final int LIVES				= 1;
	
	private int distanceCounter;
	
	
	//===================================================================================
	//								Constructor
	//===================================================================================
	public Missile(int x, int y, int w, int h, int angle) 
	{
		super(x, y, w, h, GameEngine.toBufferedImage(missileImage, M_WIDTH, M_HEIGHT));
		
		this.angle = angle;
        dx = getSpeedX(M_SPEED);
        dy = getSpeedY(M_SPEED);
        lives = LIVES;
		distanceCounter = 0;
	}

	
	//===================================================================================
	//								Move
	//===================================================================================
	@Override
	public void move()
	{
		if(lives <= 0 || distanceCounter >= M_MAX_DISTANCE)
			destroy = true;
		
		dx = getSpeedX(M_SPEED);
        dy = getSpeedY(M_SPEED);
        
		locX += dx;
		locY += dy;
		
		
		distanceCounter += M_SPEED;
		
		correctLocation();
	}
	
	
	
	
	
}
