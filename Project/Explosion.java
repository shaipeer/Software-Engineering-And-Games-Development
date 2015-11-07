import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

public class Explosion extends Sprite
{
	private final int EXPLOSION_DELAY = 30;
	
	int explosionCounter;
	
	//===================================================================================
	//								Constructor
	//===================================================================================
	public Explosion(int x, int y, int w, int h, BufferedImage img) 
	{
		super(x, y, w, h, img);
		
		explosionCounter = 0;
	}
	
	
	//===================================================================================
	//								Draw
	//===================================================================================
	@Override
	public void draw(Graphics g)
    {
		
		Image icon = new ImageIcon(GameEngine.loadImage("src/enemy3.png")).getImage();	
		g.drawImage(icon, 20, 20, null);
    }
	
}
