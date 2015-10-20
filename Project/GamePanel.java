import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import javax.swing.JPanel;

import org.w3c.dom.css.RGBColor;

/**
	|===========================================================|
	|	  	Exercise #  :	 3									|
	|															|
	|   	File name   :	 GamePanel.java						|
	|		Date		:	 28/08/2015    	      				|
	|		Author    	:	 Shai Pe'er 	 (032571580)		|
	|===========================================================|
*/


public class GamePanel extends JPanel implements Runnable
{
	private static final long serialVersionUID = 1L;

	
	//===================================================================================
	//						Initial Constant And Static Values
	//===================================================================================	
	
	//========= Game Constants ===========
	public final static  int SCREEN_WIDTH   = 1000; 
	public final static  int SCREEN_HEIGHT  = 510; 
	private static final int PERIOD 		= 60;
	private final int INITIAL_ASTEROIDS     = 4;
	private final int ASTEROIDS_AT_SPLIT    = 2;
	
	//========= Game Paint ===========
	private Image bgImage;
	private BufferedImage dbImage;
	
	//========= Game Objects ===========
	private Craft craft;
	//private ArrayList<Asteroid> asteroidList;
	
	//========= Game Status =============
	private Boolean running;
	private boolean isGameOver, isWin;
	
	private boolean up, down, left, right;
	
	
	
	private Ribbon skyRibbon, skyLineRibbon;
	private RibbonsManager rManager;
	//===================================================================================
	//								Constructor
	//===================================================================================
	public GamePanel()
	{
		//========== Game Images ===========
		bgImage = GameEngine.loadImage("src/bg.jpg");
		dbImage = null;

		//========== Key Listener ==========
		addKeyListener(new Listener());
		up = left = right = false;
		
		
		try {
			rManager = new RibbonsManager(SCREEN_WIDTH, SCREEN_HEIGHT);
		} catch (URISyntaxException e) {}
		
		initialParameters();
		
	}

	
	private void initialParameters()
	{
		//========== Generate Asteroids ===========
/*		asteroidList = new ArrayList<Asteroid>();
		for(int i = 0 ; i < INITIAL_ASTEROIDS ; i++)
			asteroidList.add(new Asteroid(getRandomLocationX(), getRandomLocationY(), SCREEN_WIDTH, SCREEN_HEIGHT));
	*/	
		//========== Game Status ===========
		running    	= true;
		isWin 		= false;
		isGameOver 	= false;
		
		//========== Game Craft ===========		
		craft = new Craft(SCREEN_WIDTH/2, SCREEN_HEIGHT/2, SCREEN_WIDTH, SCREEN_HEIGHT);
	}
	
	//===================================================================================
	//								Paint Component
	//===================================================================================
	public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        gameRender();
        g.drawImage(dbImage, 0, 0, this);
    }
	
	
	
	//===================================================================================
	//									Run
	//===================================================================================
	public void run()
    {
		long before = System.currentTimeMillis();
		running 	= true;
        
        while(running)
        {
            gameUpdate();	// Update the logical game state
            gameRender();	// Paint the screen into a buffer
            paintScreen();	// Active rendering - repaint

            before = goToSleep(before);
        }
    }

	//=================== Go To Sleep ==============================
	private long goToSleep(long lastSleepTime)
	{
		long sleepTime;
		
		
        sleepTime = PERIOD - lastSleepTime;
        if(sleepTime <= 0)
            sleepTime = 5;

        try 
        {
            Thread.sleep(sleepTime);
        }
        catch(InterruptedException e){}

        return System.currentTimeMillis();
	}
	
	
	
	
	//===================================================================================
	//								Game Loop
	//===================================================================================
	
	
	
	//-----------------------------------------------------------------------------------
	//								Game Update
	//-----------------------------------------------------------------------------------
	public void gameUpdate()
    {
		//checkCollisions();
		
		if(up)		craft.moveUp();
		if(down)	craft.moveDown();
		if(left)	craft.moveLeft();
		if(right) 	craft.moveRight();
		
		craft.move();
		
		rManager.update();
    }
	
	private void checkCollisions()
	{
		//checkCraftCollision();
		
		//checkMissilesCollision();
	}
	
	
/*	private void checkCraftCollision()
	{
		for(int i = 0 ; i < asteroidList.size() ; i++)
			if(craft.hit(asteroidList.get(i)))
			{
				splitAsteroid(i);
				
				if(craft.getLives() <= 0)
					gameOver();
				return;
			}
		
	}
	*/
	
	private void checkMissilesCollision()
	{
		/*
		try
		{
			Asteroid asteroid;
			Iterator<Asteroid> iter = asteroidList.iterator();
			
			while(iter.hasNext())
			{
				asteroid = iter.next();
				if(craft.missileHit(asteroid))
				{
					for(int i = 0 ; i < ASTEROIDS_AT_SPLIT ; i++)
						asteroidList.add(new Asteroid(asteroid));
					iter.remove();
				}
			}
		}
		catch (Exception e)
		{}
		*/
/*		
		for(int i = 0 ; i < asteroidList.size() ; i++)
			if(craft.missileHit(asteroidList.get(i)))//////////////////////////////////////////NULL POINTER EXEPTION
			{
				splitAsteroid(i);
				i--;
			}
	*/	
		
		/*
		try
		{
		ArrayList<Asteroid> toRemove = new ArrayList<Asteroid>();
		ArrayList<Asteroid> toAdd    = new ArrayList<Asteroid>();
		for(Asteroid asteroid : asteroidList)
			if(craft.missileHit(asteroid))
			{
				for(int i = 0 ; i < ASTEROIDS_AT_SPLIT ; i++)
					toAdd.add(new Asteroid(asteroid));
				toRemove.add(asteroid);
				
			}
		
		asteroidList.removeAll(toRemove);
		asteroidList.addAll(toAdd);
		}
		catch(Exception e){}
		*/
		
		
/*		if(asteroidList.size() == 0)
			youWin();
			*/
	}
	


	//-----------------------------------------------------------------------------------
	//								Game Render
	//-----------------------------------------------------------------------------------
	public void gameRender()
    {
        Graphics dbg;
        
        dbImage = new BufferedImage(SCREEN_WIDTH, SCREEN_HEIGHT, BufferedImage.TRANSLUCENT);
        dbg = dbImage.createGraphics();
        // dbg.setColor();
        //dbg.fillRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
        //========= Draw background ==========
        rManager.display(dbg);
        
        //======= Draw game elements =========
        craft.draw(dbg);
              
        
  /*      
        ////////////////////////////////////////////////////////////////
        dbg.setFont(new Font("Arial", Font.PLAIN, 30));
        dbg.setColor(Color.WHITE);
        
        dbg.drawString("Score: " + craft.getScore(), SCREEN_WIDTH-SCREEN_WIDTH/4, 60);
        for(int i = 1 ; i <= craft.getLives() ; i++)
        	Sprite.drawAngledImage(dbg, craft.getCraftImage(), 270, i * 30, 30);
        
        
       // dbg.drawString(craft.toString(), 30, 90);
        
        //////////////////////////////////////////////////////////
  */      
        //====== Check if game is over =======
        if(isGameOver)
        {
            gameOverMessage(dbg);
            running = false;
        }
        if(isWin)
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
	//								Listener
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
//		        case KeyEvent.VK_SPACE: 	craft.fire();		break;
		        default: 				 						return;
		    }		 
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
        g.drawString("You Win", SCREEN_WIDTH/2 - 30, SCREEN_HEIGHT/2);
    }
    
    
    private int getRandomLocationX()	{	return new Random().nextInt(SCREEN_WIDTH );	}
    private int getRandomLocationY()	{	return new Random().nextInt(SCREEN_HEIGHT);	}
}
