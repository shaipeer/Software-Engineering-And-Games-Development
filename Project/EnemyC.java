import java.awt.Image;

public class EnemyC extends EnemyCraft
{

	private static final Image craftImage = GameEngine.loadImage("src/enemy3.png");

	//========= Craft Dimensions ===========
	private final static int HEIGHT 	= 35;	//The craft height in pixels
	private final static int WIDTH 		= 50;	//The craft width in pixels
	
	private final static int LIVES = 3;			// The number of craft lives
	private final int MOVE_INTERVAL = 3;
	
	public EnemyC(int x, int y, int w, int h) 
	{
		super(x, y, w, h, GameEngine.toBufferedImage(craftImage, WIDTH, HEIGHT), LIVES);
		lives = LIVES;
	}
	
	public void move()
	{
		locX = locX - MOVE_INTERVAL;
	}
}
