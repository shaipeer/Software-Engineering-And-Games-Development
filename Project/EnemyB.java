import java.awt.Image;
import java.util.ArrayList;

public class EnemyB extends EnemyCraft
{

	private static final Image craftImage = GameEngine.loadImage("src/enemy2.png");

	//========= Craft Dimensions ===========
	private final static int WIDTH 		= 25;	//The craft width in pixels
	private final static int HEIGHT 	= 25;	//The craft height in pixels

	private final int LIVES=2;						// The number of craft lives
	
	
	public EnemyB(int x, int y, int w, int h) 
	{
		super(x, y, w, h,craftImage,WIDTH,HEIGHT);
		lives=LIVES;
		moveInterval=2;
		
		//========== Generate Missiles ===========
		missileList = new ArrayList<Missile>();
	}
	public void move()
	{
		
		locX=locX-moveInterval;
		//moveMissiles();
	}

}
