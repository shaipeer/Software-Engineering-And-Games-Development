import java.awt.Image;
import java.util.ArrayList;

public class EnemyC extends EnemyCraft
{

	private static final Image craftImage = GameEngine.loadImage("src/craft.png");

	//========= Craft Dimensions ===========
	private final static int WIDTH 		= 25;	//The craft width in pixels
	private final static int HEIGHT 	= 25;	//The craft height in pixels
	private final int LIVES=3;						// The number of craft lives
	
	
	public EnemyC(int x, int y, int w, int h) 
	{
		super(x, y, w, h,craftImage,WIDTH,HEIGHT);
		lives=LIVES;
		
		//========== Generate Missiles ===========
		missileList = new ArrayList<Missile>();
	}
	
	
}
