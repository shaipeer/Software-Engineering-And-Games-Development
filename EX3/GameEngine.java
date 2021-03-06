import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.Buffer;

import javax.imageio.ImageIO;

/**
	|===========================================================|
	|	  	Exercise #  :	 3									|
	|															|
	|   	File name   :	 GameEngine.java					|
	|		Date		:	 28/08/2015    	      				|
	|		Author    	:	 Shai Pe'er 	(032571580)			|
	|		Mail   		:	 shaip86@gmail.com					|
	|===========================================================|
*/

public class GameEngine 
{

	public static BufferedImage toBufferedImage(Image img, int width, int height)
	{
		if (img == null)
			return null;

		img = img.getScaledInstance(width, height, Image.SCALE_FAST);
		
		BufferedImage bi = new BufferedImage (img.getWidth(null),height,BufferedImage.TYPE_INT_RGB);
	     Graphics bg = bi.getGraphics();
	     bg.drawImage(img, 0, 0, null);
	     bg.dispose();
	     return bi;
	}
	
	
	public static BufferedImage loadImage(String filePath)
	{
		BufferedImage img = null;
		
		try
		{
			img = ImageIO.read(new File(filePath));
		}
		catch (IOException e)
		{
			
		}
		
		return img;
		
	}
	
	
	public static BufferedImage rotateImage(BufferedImage img, int angle, Graphics2D g)
	{
		AffineTransform at = new AffineTransform();
		at.rotate(Math.toRadians(angle), img.getWidth()/2, img.getHeight()/2);

		g.drawImage(img, at, null);
		
		return img;
		
	}
	
}
