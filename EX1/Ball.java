import java.awt.Color;
import java.awt.Graphics2D;
import javax.swing.JPanel;

/**
   |============================================================|
   |	  	Exercise #  :	 1									|
   |															|
   |   	   	File name   :	 Ball.java							|
   |		Date		:	 16/08/2015    	      				|
   |		Author 1   	:	 Shai Pe'er 	(032571580)			|
   |		Author 2   	:	 Denys Bedilov	(327011813)			|
   |		Author 3    :	 Rita Markovich	(304492291)			|
   |============================================================|
*/

public class Ball extends JPanel
{
	//===================================================================================
	//						Initial And Constant Values
	//===================================================================================
	private final int ONE_METRE = 6;
	private final int BALL_RADYUS = 15;
	private final double TIME_FACTOR = 0.01;
	
	//===================================================================================
	//						Variables 
	//===================================================================================
	private int _x,_y, _groundLoc;
	private double _g, _t, _v0, _y0, _cor;
	private boolean freeFall = true;
	
	
	//============================================================================================
	//								Constructor
	//============================================================================================
	public Ball(int startX, int groundLoc, int initialHeight, double gravitation, double cor)
	{
		_t = 0.0;
		_cor = cor;
		_x = startX;
		_g = gravitation;
		_groundLoc = groundLoc;
		_y0 = _groundLoc - initialHeight * ONE_METRE;
	}
	
	//============================================================================================
	//								Move One Step
	//============================================================================================
	public void move(Graphics2D g2d)
	{
		_t += TIME_FACTOR;
		
		if(freeFall)
		{
			if((_y + BALL_RADYUS) > _groundLoc)	//reset values for the first ground hit
			{
				freeFall = false;
				_v0 = _v0 + (_g * _t);
				_v0 *= _cor;
				_t = 0;	
			}
			_y =(int) (_y0 + freeFallCalc(_t, _g));		//free fall
		}
		else
		{
			if(_y + BALL_RADYUS >= _groundLoc)	//reset throw up values
			{
				_v0 *= _cor;
				_t = 0.01;
			}
			
			_y =(int) ((_groundLoc - BALL_RADYUS) - throwUpCalc(_v0, _t, _g));	//ball starts from ground with v0
		}
		
		g2d.setColor(Color.WHITE);
        g2d.fillOval(_x-BALL_RADYUS, (_y - BALL_RADYUS), BALL_RADYUS*2, BALL_RADYUS*2);
        
	}
	
	//============================================================================================
	//						Throw The Ball Up Calculation
	//============================================================================================
	private double throwUpCalc(double v0, double t, double g)
	{
		return ((v0 * t) - (0.5 * g * (t * t)) );
	}
	
	//============================================================================================
	//						Free Ball Fall Calculation
	//============================================================================================
	private double freeFallCalc(double t, double g)
	{
		return (0.5 * g * (t * t));
	}
	
	//============================================================================================
	//								Getters
	//============================================================================================
	public int getX()			{	return _x;	}
	public int getY()			{	return _y;	}		
	public String getBallInfo() {	return "";	}
	
}
