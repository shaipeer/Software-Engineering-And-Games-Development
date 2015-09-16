import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;


/**
|===========================================================|
|	  	Exercise #  :	 3									|
|															|
|   	File name   :	 Sprite.java						|
|		Date		:	 28/08/2015    	      				|
|		Author    	:	 Shai Pe'er 	(032571580)			|
|		Mail   		:	 shaip86@gmail.com					|
|===========================================================|
*/


public class Sprite 
{
	//===================================================================================
	//						Initial Constant And Static Values
	//===================================================================================
    private static final int SIZE = 1; // default sprite's dimensions
    protected BufferedImage bufImage;
    protected int imageWidth, imageHeight; // image dimensions
    
    protected int locX, locY, dx, dy;
    protected int pWidth, pHeight;  // panel's dimensions
    protected int angle;
    protected int lives;
    protected boolean destroy;
    protected boolean atUpperWall, atButtonWall, atLeftWall, atRightWall;
    
    
	//===================================================================================
	//								Builder
	//===================================================================================
    public Sprite(int x, int y, int w, int h,BufferedImage img) 
    {
        locX = x;
        locY = y;
        angle = 0;
        dx = 0;
		dy = 0;
		destroy = false;
        pWidth = w;
        pHeight = h;
        atUpperWall = atButtonWall = atLeftWall = atRightWall = false;
        bufImage = img;
        if(bufImage != null)
        {
            imageWidth  = bufImage.getWidth(null);
            imageHeight = bufImage.getHeight(null);
        }
    
    }
   
	//===================================================================================
	//								Get Bounding Box
	//===================================================================================
    public Rectangle getBoundingBox()
    {
        return new Rectangle(getLocX(), getLocY(), imageWidth, imageHeight);
    }

    
    
    //===================================================================================
	//								Getters
    //===================================================================================
    public int getLocX()					{	return locX;		}
    public int getLocY()					{	return locY;		}
    public int getDx()						{	return dx;			}
    public int getDy()						{	return dy;			}
    public int getPWidth()					{	return pWidth;		}
    public int getPHeight()					{	return pHeight;		}
    public int getImageWidth()				{	return imageWidth;	}
    public int getImageHeight()				{	return imageHeight;	}
    public int getAngle()					{	return angle;		}
    public int getLives()					{	return lives;		}
    public boolean isDestroy()				{	return destroy;		}
    protected int getSpeedX(double speed)	{	return (int)(speed * Math.cos(Math.toRadians(angle)));	}
    protected int getSpeedY(double speed)	{	return (int)(speed * Math.sin(Math.toRadians(angle)));	}
    
    
    
	//===================================================================================
	//								Move
	//===================================================================================
    public void move()
    {
        locX += dx;
        locY += dy;
    }
    
    
	//===================================================================================
	//								Draw
	//===================================================================================
    public void draw(Graphics g)
    {
    	drawAngledImage(g, bufImage, angle, locX, locY);
    }
    
	//===================================================================================
	//								Draw Angled Image
	//===================================================================================    
    public static void drawAngledImage(Graphics g, BufferedImage img, int angle, int x, int y)
	{
		AffineTransform at = new AffineTransform();
		
		Graphics2D g2d = (Graphics2D) g;
		
		int cx = img.getWidth() / 2;
        int cy = img.getHeight() / 2;
 
        at.translate(cx + x, cy + y);
        at.rotate(Math.toRadians(angle));
        at.translate(-cx, -cy);
        g2d.drawImage(img, at, null);
	}
    
   
    protected void correctLocation()
    {
    	if		(locX >= pWidth )	locX = 0;
    	else if (locX <= 0      )	locX = pWidth;
    	else if (locY >= pHeight)	locY = 0;
    	else if (locY <= 0      )	locY = pHeight;
    }
    
}

