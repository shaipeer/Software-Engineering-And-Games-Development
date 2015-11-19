/**
	|===========================================================|
	|	  	Exercise #  :	 project							|
	|															|
	|   	File name   :	 RibbonsManager.java				|
	|		Date		:	 28/08/2015    	      				|
	|		Author 1   	:	 Shai Pe'er 	(032571580)			|
	|		Author 2   	:	 Denys Bedilov 	(327011813)			|
	|		Author 3   	:	 Rita Markovich	(304492291)			|
	|===========================================================|
*/

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;


public class RibbonsManager 
{
    private Ribbon[] ribbons;
    private int pWidth, pHeight;

    public RibbonsManager(int w, int h) throws URISyntaxException
    {
        ribbons = new Ribbon[3];
		
	pWidth = w;
	pHeight = h;
		
//        File pathSky = new File(new File(".").getAbsolutePath()+ "//Sky.png");
//        File pathSkyLine = new File(new File(".").getAbsolutePath()+ "//Skyline.gif");
//        File pathLamps = new File(new File(".").getAbsolutePath()+ "//Lamps.png");

        
        BufferedImage skyImg = null, skylineImg = null, lampsImg = null;
        try 
        {
            skyImg 		= ImageIO.read(new File(getClass().getResource("/sky.png").toURI()));
            skylineImg 	= ImageIO.read(new File(getClass().getResource("/skyline.png").toURI()));
            lampsImg 	= ImageIO.read(new File(getClass().getResource("/ground.png").toURI()));
        } catch (IOException ex) {
            Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        ribbons[0] = new Ribbon(pWidth, pHeight, skyImg, 1);
        ribbons[1] = new Ribbon(pWidth, pHeight, skylineImg, 2);
        ribbons[2] = new Ribbon(pWidth, pHeight, lampsImg, 3);
        
    }
	
    public void display(Graphics g)
    {
        for(Ribbon r : ribbons)
            r.display(g);
    }
	
    public void update()
    {
            for(Ribbon r : ribbons)
                r.update();
    }
  
}
