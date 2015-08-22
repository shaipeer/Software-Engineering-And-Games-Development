import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

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
	private final int INITIAL_BRICKS 		  = 2;			//The initial amount of bricks
	private final Color BG_COLOR 			  = Color.gray; //Board background color
	
	//========  Board Visual Variables ==================
	private final double GRID_GAP			  = 0.02;		//2% from each side
	private final double DISTANCE_FROM_BORDER = 0.05;		//10% from each side
	public static final int BOARD_SIZE		  = 4; 			 //Board size nXn
	
	private Brick[][] _gameBoard;
	private Logic logic = new Logic();;
	
	//===================================================================================
	//								Constructor
	//===================================================================================
	public GameBoard()
	{
		_gameBoard = new Brick[BOARD_SIZE][BOARD_SIZE];
		initialGameBoard();
		keyManager();
		logic  = new Logic();
	}
	
	
	//===================================================================================
	//								Paint Component
	//===================================================================================
	@Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        
              
        
        drawGameBoard(g2d);
		
        _gameBoard = logic.generateNewRandomBreak(_gameBoard);
        if(_gameBoard == null)
        {
        	//Game Over
        }
    }
	
	
	
	
	//===================================================================================
	//								Initial Game Board
	//===================================================================================
	private void initialGameBoard()
	{
		for (int x = 0 ; x < BOARD_SIZE ; x++)
			for (int y = 0 ; y < BOARD_SIZE ; y++)
				_gameBoard[x][y] = new Brick(Brick.EMPTY_BRICK);
		
		for (int i = 0 ; i < INITIAL_BRICKS ; i++)
			_gameBoard = logic.generateNewRandomBreak(_gameBoard);
		
	}
	
	
	//===================================================================================
	//								Draw Game Board
	//===================================================================================
	private void drawGameBoard(Graphics2D g2d)
	{
		g2d.setColor(BG_COLOR);
		g2d.fillRect((int)(getWidth()*DISTANCE_FROM_BORDER),
		   			 (int)(getWidth()*DISTANCE_FROM_BORDER), 
		   			 (int)(getWidth()-(getWidth()*DISTANCE_FROM_BORDER*2)), 
					 (int)(getWidth()-(getWidth()*DISTANCE_FROM_BORDER*2)));
		
		drawBrickGrid(g2d);
	}
	
	//===================================================================================
	//								Draw Brick Grid
	//===================================================================================
	private void drawBrickGrid(Graphics2D g2d)
	{
		int drawLocX = (int)(getWidth()  * DISTANCE_FROM_BORDER);
		int drawLocY = (int)(getHeight() * DISTANCE_FROM_BORDER);
		int gridGap  = (int)(getWidth()  * GRID_GAP);
		int brickSize= (int)((getWidth() * ( 1 - (GRID_GAP * (BOARD_SIZE + 1)) - (DISTANCE_FROM_BORDER * 2) ) ) / BOARD_SIZE);
		
		for(int x = 0 ; x < BOARD_SIZE ; x++)
		{
			drawLocX += gridGap;
			
			for(int y = 0 ; y < BOARD_SIZE ; y++)
			{
				drawLocY += gridGap;
				_gameBoard[x][y].drawBrick(g2d, drawLocX, drawLocY, brickSize, brickSize);
				drawLocY += brickSize;
			}
			drawLocX += brickSize;
			drawLocY = (int)(getHeight() * DISTANCE_FROM_BORDER);
		}
	}

	
	//===================================================================================
	//								Key Manager
	//===================================================================================
	private void keyManager()
	{
		addKeyListener( new KeyAdapter()
		{
		
			@Override
			public void keyReleased(KeyEvent e) 
			{
				int keyCode = e.getKeyCode();
			    switch( keyCode ) 
			    { 
			        case KeyEvent.VK_UP:	 _gameBoard = logic.move(_gameBoard ,Logic.DIR_UP);		break;
			        case KeyEvent.VK_DOWN:	 _gameBoard = logic.move(_gameBoard ,Logic.DIR_DOWN);	break;
			        case KeyEvent.VK_LEFT:	 _gameBoard = logic.move(_gameBoard ,Logic.DIR_LEFT);	break;
			        case KeyEvent.VK_RIGHT : _gameBoard = logic.move(_gameBoard ,Logic.DIR_RIGHT);	break;
			        default: 				 //return OTHER_KEY;
			     }
			    
				repaint();
			}
			
		});
	}
	
}
