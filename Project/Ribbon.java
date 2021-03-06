/**
	|===========================================================|
	|	  	Exercise #  :	 project							|
	|															|
	|   	File name   :	 Ribbon.java						|
	|		Date		:	 28/08/2015    	      				|
	|		Author 1   	:	 Shai Pe'er 	(032571580)			|
	|		Author 2   	:	 Denys Bedilov 	(327011813)			|
	|		Author 3   	:	 Rita Markovich	(304492291)			|
	|===========================================================|
*/

import java.awt.Graphics;
import java.awt.image.BufferedImage;


public class Ribbon {
    private BufferedImage img;
    private int imgWidth, pWidth, pHeight, xImgHead;
    private int moveInterval;
    
    public Ribbon(int w, int h, BufferedImage img, int moveInterval)
    {
        this.img = img;
        pWidth = w;
        pHeight = h;
        this.moveInterval = moveInterval;
        imgWidth = img.getWidth();
        xImgHead = 0;
    }
    
    public void update()
    {
    	xImgHead = (xImgHead - moveInterval) % imgWidth;
    }
    
    public void display(Graphics g)
    {
        if(xImgHead == 0)
            draw(g, 0, pWidth, 0, pWidth);
        else if(xImgHead > 0 && xImgHead < pWidth)
        {
            draw(g, 0, xImgHead, imgWidth-xImgHead, imgWidth);
            draw(g, xImgHead, pWidth, 0, pWidth-xImgHead);
        }
        else if(xImgHead >= pWidth)
            draw(g, 0, pWidth, imgWidth-xImgHead, imgWidth - xImgHead + pWidth);
        else if(xImgHead < 0 && xImgHead >= pWidth - imgWidth)
            draw(g, 0, pWidth, -xImgHead, pWidth - xImgHead);
        else if(xImgHead < pWidth - imgWidth)
        {
            draw(g, 0, imgWidth + xImgHead, -xImgHead, imgWidth);
            draw(g, imgWidth + xImgHead, pWidth, 0, pWidth - imgWidth - xImgHead);
        }
        
    }
    
    private void draw(Graphics g, int sX1, int sX2, int imgX1, int imgX2)
    {
        g.drawImage(img, sX1, 0, sX2, pHeight, imgX1, 0, imgX2, pHeight, null);
    }
}
