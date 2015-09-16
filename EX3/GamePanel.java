import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import javax.swing.JPanel;

/**
	|===========================================================|
	|	  	Exercise #  :	 3									|
	|															|
	|   	File name   :	 GamePanel.java						|
	|		Date		:	 28/08/2015    	      				|
	|		Author    	:	 Shai Pe'er 	(032571580)			|
	|		Mail   		:	 shaip86@gmail.com					|
	|===========================================================|
*/


public class GamePanel extends JPanel implements Runnable
{
	private static final long serialVersionUID = 1L;

	//===================================================================================
	//						Initial Constant And Static Values
	//===================================================================================	
	
	//========= Game Constants ===========
	public final static  int SCREEN_WIDTH   = 700; 
	public final static  int SCREEN_HEIGHT  = 700; 
	private static final int PERIOD 		= 60;
	private final int INITIAL_ASTEROIDS     = 4;
	private final int ASTEROIDS_AT_SPLIT    = 2;
	
	//========= Game Paint ===========
	private Image bgImage;
	private BufferedImage dbImage;
	
	//========= Game Objects ===========
	private Craft craft;
	private ArrayList<Asteroid> asteroidList;
	
	//========= Game Status =============
	private Boolean running;
	private boolean isGameOver, isWin;
	
	private boolean up, down, left, right, space;
	
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
		up = down = left = right = space = false;
		
		
		initialParameters();
		
	}

	
	private void initialParameters()
	{
		//========== Generate Asteroids ===========
		asteroidList = new ArrayList<Asteroid>();
		for(int i = 0 ; i < INITIAL_ASTEROIDS ; i++)
			asteroidList.add(new Asteroid(getRandomLocationX(), getRandomLocationY(), SCREEN_WIDTH, SCREEN_HEIGHT));
		
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
		long diff, sleepTime;
		
		diff = System.currentTimeMillis() - lastSleepTime;
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
		checkCollisions();
		
		if(up)		craft.accelerat();		
		if(left)	craft.rotateLeft();
		if(right) 	craft.rotateRight();
		
		craft.move();
		moveAstroids();
    }
	
	private void checkCollisions()
	{
		checkCraftCollision();
		
		checkMissilesCollision();
	}
	
	
	private void checkCraftCollision()
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
		
		for(int i = 0 ; i < asteroidList.size() ; i++)
			if(craft.missileHit(asteroidList.get(i)))//////////////////////////////////////////NULL POINTER EXEPTION
			{
				splitAsteroid(i);
				i--;
			}
		
		
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
		
		
		if(asteroidList.size() == 0)
			youWin();
	}
	
	private void splitAsteroid(int index)
	{
		
		for(int j = 0 ; j < ASTEROIDS_AT_SPLIT ; j++)
			asteroidList.add(new Asteroid(asteroidList.get(index)));
		asteroidList.remove(index);
		
	}
	
	private void moveAstroids()
	{
		Asteroid asteroid;
		Iterator<Asteroid> iter = asteroidList.iterator();
		while(iter.hasNext())
		{
			asteroid = iter.next();
			asteroid.move();
			if (asteroid.isDestroy())
				iter.remove();
			
		}
	}
	//-----------------------------------------------------------------------------------
	//								Game Render
	//-----------------------------------------------------------------------------------
	public void gameRender()
    {
        Graphics dbg;
        
        dbImage = new BufferedImage(SCREEN_WIDTH, SCREEN_HEIGHT, BufferedImage.OPAQUE);
        dbg = dbImage.createGraphics();
        
        //========= Draw background ==========
        dbg.drawImage(bgImage, 0, 0, this);		
        
        //======= Draw game elements =========
        craft.draw(dbg);
              
        try
        {
	        for (Asteroid asteroid : asteroidList)
	     	   asteroid.draw(dbg);
        }
        catch(Exception e)
        {
        	
        }
        
        
        ////////////////////////////////////////////////////////////////
        dbg.setFont(new Font("Arial", Font.PLAIN, 30));
        dbg.setColor(Color.WHITE);
        
        dbg.drawString("Score: " + craft.getScore(), SCREEN_WIDTH-SCREEN_WIDTH/4, 60);
        for(int i = 1 ; i <= craft.getLives() ; i++)
        	Sprite.drawAngledImage(dbg, craft.getCraftImage(), 270, i * 30, 30);
        
        
       // dbg.drawString(craft.toString(), 30, 90);
        
        //////////////////////////////////////////////////////////
        
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
		        case KeyEvent.VK_LEFT:	 	left  = false;		break;
		        case KeyEvent.VK_RIGHT : 	right = false;		break;
		        case KeyEvent.VK_SPACE: 	craft.fire();		break;
		        case KeyEvent.VK_R: 		resetGame();		break;
		        default: 				 						return;
		    }
			 
		}
    }
	
	
	
	
	// only start the animation once the JPanel has been added to the JFrame
    public void addNotify()
    { 
        super.addNotify();   // creates the peer
        startGame();    // start the thread
    }
    
    public void startGame()
    {
        (new Thread(this)).start();
    }
     
    public void gameOver()
    {
        isGameOver = true;
    }
    
    public void youWin()
    {
        isWin = true;
    }
    
    private void resetGame()
    {
    	//initialParameters();
    	
    	try 
        {
            Thread.sleep(200);
        }
        catch(InterruptedException e){}
    }
    
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
    
    private void resetGameMessage(Graphics g)
    {
        g.setFont(new Font("Arial", Font.PLAIN, 30));
        g.setColor(Color.WHITE);
        g.drawString("For Reset Press 'R'", SCREEN_WIDTH/2 - 30, SCREEN_HEIGHT/2);
    }
    
    private int getRandomLocationX()	{	return new Random().nextInt(SCREEN_WIDTH );	}
    private int getRandomLocationY()	{	return new Random().nextInt(SCREEN_HEIGHT);	}
}
