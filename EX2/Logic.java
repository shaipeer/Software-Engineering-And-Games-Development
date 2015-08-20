import java.awt.Point;

/**
	|============================================================|
	|	  	Exercise #  :	 2									|
	|															|
	|   	File name   :	 MainFrame.java						|
	|		Date		:	 16/08/2015    	      				|
	|		Author 1   	:	 Shai Pe'er 	(032571580)			|
	|		Author 2   	:	 Denys Bedilov	(327011813)			|
	|		Author 3    :	 Rita Markovich	(304492291)			|
	|============================================================|
*/

public class Logic 
{
	private final int MERGR_MULTIPLIER = 2;
	
	

	//===================================================================================
	//								Constructor
	//===================================================================================
	public Logic()
	{
		
	}
	
	
	
	//===================================================================================
	//								Merge Bricks
	//===================================================================================
	public Brick[][] mergeBricks(Brick brickBoard[][], Point brickA, Point brickB)
	{
		brickBoard[brickA.x][brickA.y] = new Brick(brickBoard[brickA.x][brickA.y].getValue() * MERGR_MULTIPLIER );
		brickBoard[brickB.x][brickB.y] = new Brick(Brick.EMPTY_BRICK);
		
		return brickBoard;
	}
	
	
	
}
