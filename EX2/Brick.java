import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.nio.channels.SelectableChannel;

import org.w3c.dom.css.RGBColor;

/**
   |============================================================|
   |	  	Exercise #  :	 2									|
   |															|
   |   	   	File name   :	 Brick.java							|
   |		Date		:	 16/08/2015    	      				|
   |		Author 1   	:	 Shai Pe'er 	(032571580)			|
   |		Author 2   	:	 Denys Bedilov	(327011813)			|
   |		Author 3    :	 Rita Markovich	(304492291)			|
   |============================================================|
*/

public class Brick 
{
	//===================================================================================
	//						Initial Constant And Static Values
	//===================================================================================	
	private final Font font = new Font("Serif", Font.PLAIN, 96);	//brick font and size
	private final Color FONT_COLOR = Color.BLACK;					//Brick font color
	private final int COLOR_FACTOR = 23;							//the brick color multiplier
	private final int EMPTY = 0;									//empty value

	public final  int BASE = 2;										//Brick base number
	public static final Boolean EMPTY_BRICK = true;					//Empty brick boolean value for the first builder
	public static final Boolean NEW_BRICK = false;					//New brick boolean value for the first builder
	
	private int _value;				
	
	//===================================================================================
	//								Constructors
	//===================================================================================
	public Brick(boolean emptyBrick)
	{
		if(emptyBrick)	
			_value = EMPTY;
		else
			_value = BASE;
	}
	
	public Brick(int initialValue)
	{
		_value = initialValue;
	}

	
	//===================================================================================
	//								Is Empty
	//===================================================================================
	public Boolean isEmpty()
	{
		if(_value == EMPTY)
			return true;
		
		return false;
	}
	
	//===================================================================================
	//								Is Equals To
	//===================================================================================
	public Boolean isEqualsTo(Brick brickToCompair)
	{
		if(_value == brickToCompair.getValue())		
			return true;
		
		return false;
	}
	
	
	//===================================================================================
	//								Draw Brick
	//===================================================================================
	public void drawBrick(Graphics2D g, int drawLocX, int drawLocY, int brickWidth, int brickHeight)
	{
		if(_value != EMPTY)
		{
			g.setColor(Color.ORANGE);//(new Color(0,(int)(255 - Math.log(_value)*COLOR_FACTOR),255));
			g.fillRect(drawLocX, drawLocY, brickWidth, brickHeight);
			g.setColor(FONT_COLOR);
			g.setFont(font);
			g.drawString(_value + "", drawLocX + brickWidth/2, drawLocY + brickHeight);
		}
		else 
		{
			g.setColor(Color.cyan);//(new Color(0,(int)(255 - Math.log(_value)*COLOR_FACTOR),255));
			g.fillRect(drawLocX, drawLocY, brickWidth, brickHeight);			
		}
		
	}
	
	
	
	//===================================================================================
	//								Getters And Setters
	//===================================================================================

	public int  getValue()			{	return _value;	}
	public void setValue(int val)	{	_value = val;	}
	
}
