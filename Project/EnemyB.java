/**
	|===========================================================|
	|	  	Exercise #  :	 project							|
	|															|
	|   	File name   :	 EnemyB.java						|
	|		Date		:	 28/08/2015    	      				|
	|		Author 1   	:	 Shai Pe'er 	(032571580)			|
	|		Author 2   	:	 Denys Bedilov 	(327011813)			|
	|		Author 3   	:	 Rita Markovich	(304492291)			|
	|===========================================================|
*/

import java.awt.Image;

public class EnemyB extends EnemyCraft
{

	private static final Image craftImage = GameEngine.loadImage("src/enemy2.png");

	//========= Craft Dimensions ===========
	private final static int HEIGHT 	= 20;	//The craft height in pixels
	private final static int WIDTH 		= 50;	//The craft width in pixels

	private final static int LIVES = 2;			// The number of craft lives
	private final int MOVE_INTERVAL = 2;
	
	public EnemyB(int x, int y, int w, int h) 
	{
		super(x, y, w, h, GameEngine.toBufferedImage(craftImage, WIDTH, HEIGHT), LIVES);
		lives = LIVES;
	}
	
	public void move()
	{
		locX = locX - MOVE_INTERVAL;
	}
}
