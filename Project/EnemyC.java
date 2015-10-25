import java.awt.Image;

public class EnemyC extends EnemyCraft
{

	private static final Image craftImage = GameEngine.loadImage("src/enemy3.png");

	//========= Craft Dimensions ===========
	private final static int WIDTH 		= 30;	//The craft width in pixels
	private final static int HEIGHT 	= 35;	//The craft height in pixels
	private final int LIVES=3;						// The number of craft lives
	
	
	public EnemyC(int x, int y, int w, int h) 
	{
		super(x, y, w, h, craftImage, WIDTH, HEIGHT);
		lives=LIVES;
		moveInterval=3;
	}
	
	public void move()
	{
		
		locX=locX-moveInterval;
		//moveMissiles();
	}
}
