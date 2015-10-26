import java.awt.Image;

public class EnemyA extends EnemyCraft
{

	private static final Image craftImage = GameEngine.loadImage("src/enemy1.png");

	//========= Craft Dimensions ===========
	private final static int HEIGHT 	= 20;	//The craft height in pixels
	private final static int WIDTH 		= 50;	//The craft width in pixels
	
	//========= Craft Dimensions ===========
	private final static int LIVES = 1;						// The number of craft lives

	
	public EnemyA(int x, int y, int w, int h) 
	{
		super(x, y, w, h, GameEngine.toBufferedImage(craftImage, WIDTH, HEIGHT), LIVES );
		lives = LIVES;
	}

}
