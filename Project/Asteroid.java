import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Random;

/**
	|===========================================================|
	|	  	Exercise #  :	 3									|
	|															|
	|   	File name   :	 Astroid.java						|
	|		Date		:	 28/08/2015    	      				|
	|		Author    	:	 Shai Pe'er 	(032571580)			|
	|		Mail   		:	 shaip86@gmail.com					|
	|===========================================================|
*/


public class Asteroid extends Sprite 
{
	//===================================================================================
	//						Initial Constant And Static Values
	//===================================================================================	
	private static final Image asteroidImage = GameEngine.loadImage("src/asteroid.png");
	
	private final static int A_SIZE		= 60;	//Pixals per move
	
	private final int A_SPEED 			= 3;	//Pixals per move
	private final int A_SPIN_SPEED 		= 10;	//Lower is faster
	private final int MOVE_DELAY		= 5;	//
	private final int LIVES				= 4;
	private int spinDelayCounter;
	private int delay;
	//===================================================================================
	//								Constructor
	//===================================================================================
	public Asteroid(int x, int y, int w, int h)
	{
		super(x, y, w, h,GameEngine.toBufferedImage(asteroidImage, A_SIZE, A_SIZE));

		lives = LIVES;
		setInitialParameters();
		
	}
	
	public Asteroid(Asteroid fatherAsteroid)
	{
		super(fatherAsteroid.getLocX(),   fatherAsteroid.getLocY(), 
			  fatherAsteroid.getPWidth(), fatherAsteroid.getPHeight(),
			  GameEngine.toBufferedImage(asteroidImage, fatherAsteroid.getImageHeight()/2, fatherAsteroid.getImageWidth()/2));
		
		lives = fatherAsteroid.getLives() - 1;
		
		setInitialParameters();
	}
	
	
	private void setInitialParameters()
	{
		this.angle = getRandomAngle();
        dx = getSpeedX(A_SPEED);
        dy = getSpeedY(A_SPEED);
        
        spinDelayCounter = 0;
        delay = 0;

	}
	
	//===================================================================================
	//								Move
	//===================================================================================
	@Override
	public void move() 
	{
		if(lives <= 0)
			destroy = true;
		
		if(spinDelayCounter++ == A_SPIN_SPEED)
		{
			rotateAstroid();
			spinDelayCounter = 0;
		}
		
		if(delay++ >= MOVE_DELAY)
		{
			locX += dx;
			locY += dy;
			delay = 0;
			
			correctLocation();
		}
		
	}
	
	//===================================================================================
	//								Rotate Asteroid
	//===================================================================================
	public void rotateAstroid()
	{
		if(angle >= 360)
			angle = 0;
		angle++;
	}
	
	//===================================================================================
	//								Get Random Asteroid Angle
	//===================================================================================
	private int getRandomAngle()
	{
		Random rand = new Random();
		return rand.nextInt(359);
	}
	
}
