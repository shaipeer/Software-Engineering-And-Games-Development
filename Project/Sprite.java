/**
	|===========================================================|
	|	  	Exercise #  :	 project							|
	|															|
	|   	File name   :	 Sprite.java						|
	|		Date		:	 28/08/2015    	      				|
	|		Author 1   	:	 Shai Pe'er 	(032571580)			|
	|		Author 2   	:	 Denys Bedilov 	(327011813)			|
	|		Author 3   	:	 Rita Markovich	(304492291)			|
	|===========================================================|
 */
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;




public class Sprite 
{
	//===================================================================================
	//						Initial Constant And Static Values
	//===================================================================================
    protected BufferedImage bufImage;		
    protected int imageWidth, imageHeight; 	// Image dimensions  
    protected int locX, locY, dx, dy;		// Sprite location
    protected int pWidth, pHeight;  		// Panel's dimensions
    protected int groundHeight;				// Game ground height
    protected int lives;					// Sprite number of lives
    protected boolean destroy;				// Is Sprite destroyed or not
    protected boolean atUpperWall, atButtonWall, atLeftWall, atRightWall;
    
    
	//===================================================================================
	//								Builder
	//===================================================================================
    public Sprite(int x, int y, int w, int h,BufferedImage img) 
    {
        locX = x;
        locY = y;
        dx = 5;
		dy = 4;
		groundHeight = 25;
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
    public int getLives()					{	return lives;		}
    public boolean isDestroy()				{	return destroy;		}
    
    
    
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
    	//g.drawImage(bufImage, locX + (imageWidth/2), locY + (imageHeight/2), null);
    	g.drawImage(bufImage, locX, locY, null);
    }

    
}

