/**
	|===========================================================|
	|	  	Exercise #  :	 project							|
	|															|
	|   	File name   :	 GamePanel.java						|
	|		Date		:	 28/08/2015    	      				|
	|		Author 1   	:	 Shai Pe'er 	(032571580)			|
	|		Author 2   	:	 Denys Bedilov 	(327011813)			|
	|		Author 3   	:	 Rita Markovich	(304492291)			|
	|===========================================================|
 */

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Stroke;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import javax.swing.JPanel;


public class GamePanel extends JPanel implements Runnable
{
	private static final long serialVersionUID = 1L;

	
	//===================================================================================
	//						Initial Constant And Static Values
	//===================================================================================	
	
	//========= Game Constants ===========
	public final static  int SCREEN_WIDTH   	= 1000; 
	public final static  int SCREEN_HEIGHT  	= 510; 
	private static final int PERIOD 			= 60;
	private static final int LEVEL_PASS_SCORE 	= 200;
	private static final int WIN_LEVEL			= 5;
	
	private final int KEY_WIDTH    = 200;
	private final int KEY_HEIGHT   = 40;
	
	//========= Game Paint ===========
	private BufferedImage dbImage;
	
	//========= Game Objects ===========
	private Craft craft;
	private ArrayList<EnemyCraft> enemies;
	
	//========= Game Status =============
	private static int enemyCount;
	private Boolean running;
	private Boolean showMenu;
	
	private int difficulty;
	private int level;
	
	private int mouseX, mouseY;
	private Rectangle easyButton, mediumButton, hardButton, resetButton;  
	
	private boolean isGameOver, isWin, resetGame;
	private boolean up, down, left, right;
	private RibbonsManager rManager;
	
	
	//===================================================================================
	//								Constructor
	//===================================================================================
	public GamePanel()
	{
		//========== Game Images ===========
		dbImage = null;

		//========== Key Listener ==========
		addKeyListener(new Listener());
		addMouseListener(new MouseLis());
		up = left = right = false;
		
		difficulty = 1;
		level = 1;
		
		initialParameters();
		initialButtons();
	}

	
	private void initialParameters()
	{
		//========== Game Status ===========
		running    	= true;
		showMenu	= true;
		isWin 		= false;
		isGameOver 	= false;
		resetGame	= false;
		enemyCount	= 0;
		
		//========== Game Craft ===========		
		craft 		= new Craft(SCREEN_WIDTH/2, SCREEN_HEIGHT/2, SCREEN_WIDTH, SCREEN_HEIGHT);
		enemies 	= new ArrayList<EnemyCraft>();
			
		try {
			rManager = new RibbonsManager(SCREEN_WIDTH, SCREEN_HEIGHT);
		} catch (URISyntaxException e) {}
	}
	
	
	private void initialButtons()
	{
		hardButton   = new Rectangle((SCREEN_WIDTH/2)-100, (SCREEN_HEIGHT/2)-175, KEY_WIDTH, KEY_HEIGHT );
		mediumButton = new Rectangle((SCREEN_WIDTH/2)-100, (SCREEN_HEIGHT/2)-100, KEY_WIDTH, KEY_HEIGHT );
		easyButton   = new Rectangle((SCREEN_WIDTH/2)-100, (SCREEN_HEIGHT/2)-25,  KEY_WIDTH, KEY_HEIGHT );
		resetButton  = new Rectangle((SCREEN_WIDTH/2),     (SCREEN_HEIGHT/2)+50,  KEY_WIDTH, KEY_HEIGHT );
	}

	
	//===================================================================================
	//									Run
	//===================================================================================
	public void run()
    {
		long before = System.currentTimeMillis();
		running 	= true;
		
		String fileName = "src/background.wav";////////////////////////////////////////////////////////////////////
        (new SoundThread(fileName, AudioPlayer.LOOP)).start();
		
		while(true)
		{
			//========== Show Menu ==========
			while (showMenu)
			{
				renderMenu();
				paintScreen();
				before = goToSleep(before);
			}
			
			//========== Play Game ==========
			running = true;
	        while(running)
	        {
	        	enemyCount++;
	        	if(enemyCount >= (100/difficulty)-(level*difficulty))
	        		createEnemy();
	        	
	        	if(craft.getScore() >= getNextLevelScore())
	        		levelPass();
	        	
	        	if(level == WIN_LEVEL+1)
	        		youWin();
	        	
	            gameUpdate();	// Update the logical game state
	            gameRender();	// Paint the screen into a buffer
	            paintScreen();	// Active rendering - repaint
	
	            before = goToSleep(before);
	        }
		    
	        //========== Reset Game ==========
	        while (!showMenu)
			{
	        	renderReset();
	        		
	        	paintScreen();
				before = goToSleep(before);
			}
	        resetGame = false;
		}
    }

	//=================== Go To Sleep ==============================
	private long goToSleep(long before)
	{
		long sleepTime,diff;
		
		diff = System.currentTimeMillis() - before;
        sleepTime = PERIOD - before;
        if(sleepTime <= 0)
            sleepTime = 20;

        try 
        {
            Thread.sleep(sleepTime);
        }
        catch(InterruptedException e){}
        before = System.currentTimeMillis();
        return System.currentTimeMillis();
	}
	
	//=================== Level Pass ==============================
	private void levelPass()
	{
		craft.resetCraftLocation();
		
		enemies = new ArrayList<EnemyCraft>();
		
		level++;
	}
	
	private int getNextLevelScore()
	{
		return	level * level * LEVEL_PASS_SCORE;
	}
	
	//===================================================================================
	//								Menu Screen
	//===================================================================================
	private void renderMenu()
	{
		Graphics dbg;

		dbImage = new BufferedImage(SCREEN_WIDTH, SCREEN_HEIGHT, BufferedImage.TRANSLUCENT);
        dbg = dbImage.createGraphics();
        rManager.display(dbg);
        
        //========= Draw background ==========
        dbg.setColor(new Color(0, 0,0, 200 ));
        dbg.fillRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
        
        
        //========= Draw Buttons =============
        drawButtons(dbg);
	}
	
	private void drawButtons(Graphics dbg)
	{
		Graphics2D g2   = (Graphics2D)dbg;
		float thickness = 3;
		
		
		//Set buttons stroke
		Stroke oldStroke = g2.getStroke();
        g2.setStroke(new BasicStroke(thickness));
        
        dbg.setColor( Color.WHITE);
        dbg.drawRect((SCREEN_WIDTH/2)-100, (SCREEN_HEIGHT/2)-175, KEY_WIDTH,KEY_HEIGHT );
        dbg.drawRect((SCREEN_WIDTH/2)-100, (SCREEN_HEIGHT/2)-100, KEY_WIDTH,KEY_HEIGHT );
        dbg.drawRect((SCREEN_WIDTH/2)-100, (SCREEN_HEIGHT/2)-25,  KEY_WIDTH,KEY_HEIGHT );
        
        GameEngine.printText(dbg, (SCREEN_WIDTH/2)-100 + 60, (SCREEN_HEIGHT/2)-180+KEY_HEIGHT, 40, "Hard",  Color.WHITE);
        GameEngine.printText(dbg, (SCREEN_WIDTH/2)-100 + 50, (SCREEN_HEIGHT/2)-105+KEY_HEIGHT, 40, "Medum", Color.WHITE);
        GameEngine.printText(dbg, (SCREEN_WIDTH/2)-100 + 60, (SCREEN_HEIGHT/2)-30 +KEY_HEIGHT, 40, "Easy",  Color.WHITE);
        
        //========== Game Instructions ===================
        GameEngine.printText(dbg, (SCREEN_WIDTH/2)-120, (SCREEN_HEIGHT/2)+100,  20, "Instructions",  					Color.WHITE);
        GameEngine.printText(dbg, (SCREEN_WIDTH/2)-120, (SCREEN_HEIGHT/2)+102,  20, "__________",  						Color.WHITE);
        GameEngine.printText(dbg, (SCREEN_WIDTH/2)-120, (SCREEN_HEIGHT/2)+125,  20, "Use the arrow keys to navigate.",  Color.WHITE);
        GameEngine.printText(dbg, (SCREEN_WIDTH/2)-120, (SCREEN_HEIGHT/2)+145,  20, "Use the space bar to fire.",  		Color.WHITE);
        GameEngine.printText(dbg, (SCREEN_WIDTH/2)-120, (SCREEN_HEIGHT/2)+165,  20, "Get to Level 5 to win the game.",	Color.WHITE);
           
        g2.setStroke(oldStroke);
	}
	
	
	//===================================================================================
	//								Reset Screen
	//===================================================================================
	private void renderReset()
	{
		Graphics dbg;
		int keyWidth    = 100;
		int keyHeight   = 20;
		
		dbImage = new BufferedImage(SCREEN_WIDTH, SCREEN_HEIGHT, BufferedImage.TRANSLUCENT);
        dbg = dbImage.createGraphics();
        rManager.display(dbg);
        
        //========= Draw background ==========
        dbg.setColor(new Color(0, 0,0, 200 ));
        dbg.fillRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
 
        //========= Draw Buttons =============
        if (isGameOver)
        	gameOverMessage(dbg);
        else if (isWin)
        	youWinMessage(dbg);
        
        //Draw buttons
        dbg.setColor(Color.WHITE);
        dbg.drawRect((SCREEN_WIDTH/2), (SCREEN_HEIGHT/2)+50, keyWidth, keyHeight );
        GameEngine.printText(dbg, (SCREEN_WIDTH/2)+20, (SCREEN_HEIGHT/2)+68, 20, "RESET", Color.WHITE);
        
        
        if(resetGame)
        {
        	isGameOver  = false;
        	isWin 	 	= false;
        	showMenu	= true;
        	craft		= new Craft(SCREEN_WIDTH/2, SCREEN_HEIGHT/2, SCREEN_WIDTH ,SCREEN_HEIGHT);
        	enemies		= new ArrayList<EnemyCraft>();
        	level		= 1;
        	
        }
	}
	
	
	
	
	
	//===================================================================================
	//								Game Loop
	//===================================================================================
	

	//-----------------------------------------------------------------------------------
	//								Game Update
	//-----------------------------------------------------------------------------------
	public void gameUpdate()
    {
		if(up)		craft.moveUp();
		if(down)	craft.moveDown();
		if(left)	craft.moveLeft();
		if(right) 	craft.moveRight();
		
		//========= Update Background ==========
		rManager.update();
		
		//========= Update Craft ==========
		craft.move();
		
		//========= Update Enemies ==========
		try
		{
			for(EnemyCraft enemy : enemies)
			{
				if (enemy.isDestroy())
					enemies.remove(enemy);
				else if(enemy.getLocX() < -enemy.getImageWidth())
					enemies.remove(enemy);
				else
					enemy.move();
			}
			
		}
		catch (Exception e)
		{}
		
		checkCollisions();
    }

	
	private void checkCollisions()
	{
		String fileName = "src/explosion.wav";
		
		try
		{
			EnemyCraft enemy;
			Iterator<EnemyCraft> iter = enemies.iterator();
			
			while(iter.hasNext())
			{
				enemy = iter.next();
				if(craft.hit(enemy))
				{
					iter.remove();
					
					(new SoundThread(fileName, AudioPlayer.ONCE)).start();
					
					if(craft.getLives() <= 0)
						gameOver();
					return;
				}
				
				else if(craft.missileHit(enemy))
				{
					enemy.hit();
					(new SoundThread(fileName, AudioPlayer.ONCE)).start();//////////////////////////////////////////////////////////////////////////
				}
			}
		}
		catch (Exception e)
		{}
	}
	

	public void createEnemy()
	{
		int enemy	 		= (new Random().nextInt(3)  + 1);
		int place	 		= (new Random().nextInt(SCREEN_HEIGHT-150) + 150);
		EnemyCraft newEnemy = null;
		
		switch(enemy)
		{
			case 1:	newEnemy = new EnemyA(SCREEN_WIDTH, SCREEN_HEIGHT-place, SCREEN_WIDTH, SCREEN_HEIGHT);		break;
			case 2: newEnemy = new EnemyB(SCREEN_WIDTH, SCREEN_HEIGHT-place, SCREEN_WIDTH, SCREEN_HEIGHT);		break;
			case 3: newEnemy = new EnemyC(SCREEN_WIDTH, SCREEN_HEIGHT - 100, SCREEN_WIDTH, SCREEN_HEIGHT);		break;
			default:break;
		}
		enemies.add(newEnemy);
		enemyCount = 0;
		
	}

	//-----------------------------------------------------------------------------------
	//								Game Render
	//-----------------------------------------------------------------------------------
	public void gameRender()
    {
        Graphics dbg;
        
        dbImage = new BufferedImage(SCREEN_WIDTH, SCREEN_HEIGHT, BufferedImage.TRANSLUCENT);
        dbg = dbImage.createGraphics();
        
        
        //========= Draw Background ==========
        rManager.display(dbg);
        
        //========= Draw Screen Objects ==========
        craft.draw(dbg); 
		for(int i = 0 ; i < enemies.size() ; i++)
			   enemies.get(i).draw(dbg);
		
		//========= Draw Level  ==========
        GameEngine.printText(dbg, SCREEN_WIDTH - 150, 50, 25, "Level: " + level, null);
		
		//====== Check Game Status =======
        if(isGameOver)
        {
            gameOverMessage(dbg);
            running = false;
        }
        else if(isWin)
        {
            youWinMessage(dbg);
            running = false;
        }
        
    }
	
	//-----------------------------------------------------------------------------------
	//								Paint Screen
	//-----------------------------------------------------------------------------------
	public void paintScreen()
    {
        Graphics g;
        
        try 
        {
            g = getGraphics();
            if(g != null && dbImage != null)
            {
                g.drawImage(dbImage, 0, 0, null);
                
            }
           
        }
        catch(Exception e)
        {
            System.out.println("Graphics error");
            e.printStackTrace();
        }
        
    }

	
	//===================================================================================
	//								Start Game
	//===================================================================================
    public void addNotify()
    { 
    	// only start the animation once the JPanel has been added to the JFrame
        super.addNotify();	// creates the peer
        startGame();    	// start the thread
    }
    
    
	//===================================================================================
	//								Game Status
    //===================================================================================
    
    public void startGame()		{	(new Thread(this)).start();		}
    public void gameOver()		{	isGameOver = true;				}
    public void youWin()		{	isWin = true;					}

    private void gameOverMessage(Graphics g)
    {
        g.setFont(new Font("Arial", Font.PLAIN, 30));
        g.setColor(Color.WHITE);
        g.drawString("Game Over", SCREEN_WIDTH/2 - 30, SCREEN_HEIGHT/2);
    }
	
    private void youWinMessage(Graphics g)
    {
        g.setFont(new Font("Arial", Font.PLAIN, 30));
        g.setColor(Color.WHITE);
        g.drawString("You Win", (SCREEN_WIDTH/2) - 30, SCREEN_HEIGHT/2);
    }
    

	//===================================================================================
	//								Mouse Listener
	//===================================================================================
	private class MouseLis extends MouseAdapter
    {
		@Override
		public void mouseClicked(MouseEvent e) 
		{
			super.mouseClicked(e);
			mouseX = e.getX();
			mouseY = e.getY();
			Rectangle mouseRect = new Rectangle(mouseX, mouseY, 1, 1);
			
			if      (hardButton.intersects(mouseRect))		{	difficulty = 3;		showMenu = false;	}
			else if (mediumButton.intersects(mouseRect))	{	difficulty = 2;		showMenu = false;	}
			else if (easyButton.intersects(mouseRect))		{	difficulty = 1;		showMenu = false;	}
			else if (resetButton.intersects(mouseRect))		{	resetGame = true;}	
		}
    }
	
	
	//===================================================================================
	//								Key Listener
	//===================================================================================
	private class Listener extends KeyAdapter
    {
		//========= Game Constants ===========
		public void keyPressed(KeyEvent e)
		{
			int keyCode = e.getKeyCode();
			
		    switch( keyCode ) 
		    { 
		        case KeyEvent.VK_UP:	 	up    = true;	break;
		        case KeyEvent.VK_DOWN:	 	down  = true;	break;
		        case KeyEvent.VK_LEFT:	 	left  = true;	break;
		        case KeyEvent.VK_RIGHT : 	right = true;	break;
		        default: 				 					return;
		        
		    }
        }
        
      //========= Game Constants ===========
		public void keyReleased(KeyEvent e)
		{
			int keyCode = e.getKeyCode();
			
		    switch( keyCode ) 
		    { 
			    case KeyEvent.VK_UP:	 	up    = false;		break;
			    case KeyEvent.VK_DOWN:	 	down  = false;		break;
		        case KeyEvent.VK_LEFT:	 	left  = false;		break;
		        case KeyEvent.VK_RIGHT : 	right = false;		break;
		        case KeyEvent.VK_SPACE: 	craft.fire();		break;
		        default: 				 						return;
		    }		 
		}
    }
	
	
}




