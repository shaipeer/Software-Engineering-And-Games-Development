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
	private final int MULTIPLE_FACTOR = 2;
	private final int EMPTY = -1;
	private int _value;

	public static final Boolean EMPTY_BRICK = true;
	public static final Boolean NEW_BRICK = false;
	
	
	//===================================================================================
	//								Constructors
	//===================================================================================
	public Brick(boolean emptyBrick)
	{
		if(emptyBrick)	
			_value = EMPTY;
		else
			_value = MULTIPLE_FACTOR;
	}
	
	public Brick(int initialValue)
	{
		_value = initialValue;
	}

	
	/*
	//===================================================================================
	//								Merge Bricks
	//===================================================================================
	public Boolean mergeBricks(Brick brickToMerge)
	{
		if(_value == brickToMerge.getValue())
		{
			_value 	*= MULTIPLE_FACTOR;
			
			return true;
		}
		
		return false;
	}
	*/
	
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

	public int  getValue()			{	return _value;	}
	public void setValue(int val)	{	_value = val;	}
	
}
