import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

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

public class GameBoard extends JPanel
{
	//===================================================================================
	//						Initial And Constant Values
	//===================================================================================
	private final int BOARD_SIZE 			  = 4; 			 //Board size nXn
	private final int INITIAL_BRICKS 		  = 2;			 //The initial amount of bricks
	private final Color BG_COLOR 			  = Color.white; //Board background color
	private final double DISTANCE_FROM_BORDER = 0.1;		 //10% from each side
	
	
	private Brick[][] _gameBoard;
	private Logic logic;
		
	
	//===================================================================================
	//								Constructor
	//===================================================================================
	public GameBoard()
	{
		_gameBoard = new Brick[BOARD_SIZE][BOARD_SIZE];
		initialGameBoard();
		
		logic  = new Logic();
	}
	
	
	
	@Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        
        g2d.setColor(BG_COLOR);        
        
        
		//ball.move(g2d);
	
		//Creates rectangle 
		g2d.fillRect((int)(getWidth()*DISTANCE_FROM_BORDER),
        			 (int)(getHeight()*DISTANCE_FROM_BORDER), 
        			 (int)(getWidth()-(getWidth()*DISTANCE_FROM_BORDER*2)), 
					 (int)(getHeight()-(getHeight()*DISTANCE_FROM_BORDER*2)));
    }
	
	
	
	private void initialGameBoard()
	{
		for (int x = 0 ; x < BOARD_SIZE ; x++)
			for (int y = 0 ; y < BOARD_SIZE ; y++)
			{
				_gameBoard[x][y] = new Brick(true);
			}
		
		_gameBoard[(int)(BOARD_SIZE/2)][(int)(BOARD_SIZE/2)-1] = new Brick(false);
		_gameBoard[(int)(BOARD_SIZE/2)-1][(int)(BOARD_SIZE/2)] = new Brick(false);
	}
	
	
}
