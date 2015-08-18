import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
	|===========================================================|
	|	  	Exercise #  :	 1									|
	|															|
	|   	File name   :	 World.java							|
	|		Date		:	 16/08/2015    	      				|
	|		Author 1   	:	 Shai Pe'er 	(032571580)			|
	|		Author 2   	:	 Denys Bedilov	(327011813)			|
	|		Author 3    :	 Rita Markovich	(304492291)			|
	|===========================================================|
*/

public class World extends JPanel  implements ActionListener
{
	public static final int GROUND_LOCATION = 40;
	
	private Ball ball;
	
	private double 	_cor;
	private double 	_gravitation;
	private int 	_initialBallHeight;
	
	private JLabel ballInfoLBL = new JLabel();
	
	Image _imageBG;
	Boolean firstPrint;
	private Timer _time; //Timer object
	
	
	//============================================================================================
	//								Builder
	//============================================================================================
	public World(double gravitation, double cor, int initialHeight, String choosenWorldString)
	{
		_initialBallHeight = initialHeight;
		_gravitation = gravitation;
		_cor = cor;
		
		setBackground(Color.BLACK);
		setBackgroundIMG(choosenWorldString);
		
		firstPrint = true;
		
		ballInfoLBL.setForeground(Color.WHITE);
		this.add(ballInfoLBL);
		
		_time = new Timer (0, this);
		_time.start();
	}
	
	//============================================================================================
	//								Paint Component
	//============================================================================================
	
	@Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        
        g2d.drawImage(_imageBG, 0, (getHeight()-getWidth())/2, getWidth(), getWidth(), this);
        
        g2d.setColor(Color.white);
        
        if (firstPrint)
        {
        	firstPrint = false;
        	ball = new Ball( getWidth()/2, getHeight() - GROUND_LOCATION, _initialBallHeight, _gravitation, _cor);
        }
        
        
		ball.move(g2d);
		ballInfoLBL.setText(ball.getBallInfo());
        g2d.fillRoundRect(getWidth()/6, getHeight() - GROUND_LOCATION, (getWidth()/6)*4, 20, 10, 10);
    }

   
	//============================================================================================
	//								Action Performed
	//============================================================================================
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		repaint();
	}
	
	//============================================================================================
	//								Set BG Image
	//============================================================================================
	public void setBackgroundIMG(String world)
	{
		try 
		{
			switch(world)
			{
				case "Earth":	_imageBG = ImageIO.read(new File("src/earth.jpg"));	return;
				case "Moon":	_imageBG = ImageIO.read(new File("src/moon.jpg"));	return;
				case "Mars":	_imageBG = ImageIO.read(new File("src/mars.jpg"));	return;
				case "Other":	_imageBG = ImageIO.read(new File("src/other.jpg"));	return;
			}
				
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	
	public void stopTimer()	{	_time.stop();	}
	
}
