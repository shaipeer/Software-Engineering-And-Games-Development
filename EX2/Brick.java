import java.awt.Color;

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
	//						Initial And Constant Values
	//===================================================================================
	private static final int MULTIPLE_FACTOR = 2;
	private static final int EMPTY = -1;
	
	private int _value;
	private int _locX, _locY;
	
	
	//===================================================================================
	//								Constructors
	//===================================================================================
	public Brick(int locX, int locY, boolean emptyBrick)
	{
		_locX = locX;
		_locY = locY;
		
		if(emptyBrick)	_value = EMPTY;
		else			_value = MULTIPLE_FACTOR;
	}
	
	public Brick(int initialValue, int locX, int locY)
	{
		_locX = locX;
		_locY = locY;
		_value = initialValue;
	}

	
	//===================================================================================
	//								Merge Bricks
	//===================================================================================
	public Boolean mergeBricks(Brick brickToMerge)
	{
		if(_value == brickToMerge.getValue())
		{
			_locX 	= brickToMerge.getX();
			_locY 	= brickToMerge.getY();
			_value 	*= MULTIPLE_FACTOR;
			
			return true;
		}
		
		return false;
	}
	
	//===================================================================================
	//								Is Empty
	//===================================================================================
	public Boolean isEmpty()
	{
		if(_value == EMPTY)		return true;
		else					return false;
	}
	
	//===================================================================================
	//								Getters And Setters
	//===================================================================================

	public int getX()		{	return _locX;	}
	public int getY()		{	return _locY;	}
	public int getValue()	{	return _value;	}
	
	
}
