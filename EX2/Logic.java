import java.awt.Point;
import java.awt.event.KeyEvent;
import java.lang.invoke.ConstantCallSite;
import java.util.Random;

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
//	private final int MERGR_MULTIPLIER = 2;
	
	public static final int OTHER_KEY = 0;	//Other Direction
	public static final int DIR_UP 	= 1;	//Direction up
	public static final int DIR_DOWN 	= 2;	//Direction doun
	public static final int DIR_LEFT 	= 3;	//Direction left
	public static final int DIR_RIGHT = 4;	//Direction right
	
	

	//===================================================================================
	//								Constructor
	//===================================================================================
	public Logic()
	{
		
	}
	
	
	//===================================================================================
	//								Move
	//===================================================================================
	public Brick[][] move(Brick gameBoard[][], int direction)
	{
		Brick[] tempBrickRow;
		
		
		for(int i = 0 ; i < GameBoard.BOARD_SIZE ; i++)
		{
			switch(direction)
			{
				case DIR_UP: 
					gameBoard[i] = mergeRow(gameBoard[i]);
					break;
					
				case DIR_DOWN: 
					gameBoard[i] = mergeRow(reverseBrickRow(gameBoard[i]));
					gameBoard[i] = reverseBrickRow(gameBoard[i]);
					break;
					
				case DIR_LEFT: 
					tempBrickRow = getCol(gameBoard, i);
					gameBoard = setCol(gameBoard, mergeRow(tempBrickRow), i) ;

					break;
					
				case DIR_RIGHT: 
					tempBrickRow = reverseBrickRow(getCol(gameBoard, i));
					tempBrickRow = mergeRow(tempBrickRow);
					gameBoard = setCol(gameBoard, reverseBrickRow(tempBrickRow), i) ;
					break;
			}
		}
		
		
		return gameBoard;
	}
	
	
	//============= Reverse Brick Row =========================
	private Brick[] reverseBrickRow(Brick[] brickRow)
	{
		Brick temp;
		for(int i = 0 ; i <= (int)((brickRow.length-1)/2) ; i++)
		{
			temp = brickRow[i];
			brickRow[i] = brickRow[brickRow.length-1 - i];
			brickRow[brickRow.length-1 - i] = temp;
		}
		
		return brickRow;
	}

	
	//============= Get Column =========================
	private Brick[] getCol (Brick[][] gameBoard, int colNumber)
	{
		Brick[] brickRow = new Brick[GameBoard.BOARD_SIZE];
		
		for(int i = 0 ; i < GameBoard.BOARD_SIZE ; i++)
			brickRow[i] = gameBoard[i][colNumber];
		
		return brickRow;
	}
	
	//============= Set Column =========================
	private Brick[][] setCol (Brick[][] gameBoard, Brick[] colToSet,int colNumber)
	{
		
		for(int i = 0 ; i < GameBoard.BOARD_SIZE ; i++)
			gameBoard[i][colNumber] = colToSet[i];
		
		return gameBoard;
	}
	
	
	//===================================================================================
	//								Merge Row
	//===================================================================================
	private Brick[] mergeRow(Brick brickRow[])
	{
		for(int i = 1 ; i < GameBoard.BOARD_SIZE; i++)
			if(!brickRow[i].isEmpty())
				for(int j = i-1 ; j >= 0 ; j--)
					if ( (!brickRow[j].isEmpty() && brickRow[j].isEqualsTo(brickRow[i])) || (j == 0))
					{
						brickRow = mergeBricks(brickRow, j, i);
						break;
					}
		
		return brickRow;
	}
	
	
	//===================================================================================
	//								Merge Bricks
	//===================================================================================
	private Brick[] mergeBricks(Brick brickRow[], int brickAloc, int brickBloc)
	{
		brickRow[brickAloc] = new Brick(brickRow[brickAloc].getValue() + brickRow[brickBloc].getValue() );
		brickRow[brickBloc] = new Brick(Brick.EMPTY_BRICK);
		
		return brickRow;
	}
	
	
	//===================================================================================
	//								Generate New Random Break
	//===================================================================================
	public Brick[][] generateNewRandomBreak(Brick[][] gameBoard)
	{
		Point newBrickLoc = findEmptySpace(gameBoard);
		
		if(newBrickLoc == null)
			return null;
		
		gameBoard[newBrickLoc.x][newBrickLoc.y] = new Brick(Brick.NEW_BRICK);
		return gameBoard;	
	}
	
	//======= Find Empty Space In The Game Board ===========
	private Point findEmptySpace(Brick[][] gameBoard)
	{
		Random rand = new Random();
		int x,y;
		
		if(boardIsFull(gameBoard))
			return null;
		
		do
		{
			x = rand.nextInt(GameBoard.BOARD_SIZE-1) + 1;
			y = rand.nextInt(GameBoard.BOARD_SIZE-1) + 1;
		}while(!gameBoard[x][y].isEmpty());
		
		return new Point(x, y);
	}

	//============== Check If Board Is Full ===============
	private Boolean boardIsFull(Brick[][] gameBoard)
	{
		for(int x = 0 ; x < GameBoard.BOARD_SIZE ; x++)
			for(int y = 0 ; y < GameBoard.BOARD_SIZE ; y++)
				if(gameBoard[x][y].isEmpty())
					return false;
		return true;
	}
	
	
	

	
}
