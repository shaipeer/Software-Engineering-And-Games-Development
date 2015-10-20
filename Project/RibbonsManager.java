import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;


public class RibbonsManager {
    private Ribbon[] ribbons;
    private int pWidth, pHeight;

    public RibbonsManager(int w, int h)
    {
        ribbons = new Ribbon[3];
		
	pWidth = w;
	pHeight = h;
		
        File pathSky = new File(new File(".").getAbsolutePath()+ "//Sky.png");
        File pathSkyLine = new File(new File(".").getAbsolutePath()+ "//Skyline.gif");
        File pathLamps = new File(new File(".").getAbsolutePath()+ "//Lamps.png");
        
        BufferedImage skyImg = null, skylineImg = null, lampsImg = null;
        try {
            skyImg = ImageIO.read(pathSky);
            skylineImg = ImageIO.read(pathSkyLine);
            lampsImg = ImageIO.read(pathLamps);
        } catch (IOException ex) {
            Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        ribbons[0] = new Ribbon(pWidth, pHeight, skyImg, 2);
        ribbons[1] = new Ribbon(pWidth, pHeight, skylineImg, 3);
        ribbons[2] = new Ribbon(pWidth, pHeight, lampsImg, 5);
        
        for(Ribbon r : ribbons)
        	r.moveLeft();
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

    public void moveRight()
    {
            for(Ribbon r : ribbons)
                r.moveRight();
    }
    public void moveLeft()
    {
            for(Ribbon r : ribbons)
                r.moveLeft();
    }
}
