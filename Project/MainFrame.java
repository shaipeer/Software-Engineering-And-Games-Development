/**
	|===========================================================|
	|	  	Exercise #  :	 project							|
	|															|
	|   	File name   :	 MainFrame.java						|
	|		Date		:	 28/08/2015    	      				|
	|		Author 1   	:	 Shai Pe'er 	(032571580)			|
	|		Author 2   	:	 Denys Bedilov 	(327011813)			|
	|		Author 3   	:	 Rita Markovich	(304492291)			|
	|===========================================================|
 */

import javax.swing.JFrame;


public class MainFrame 
{
	
	
	public static void main(String[] args) 
	{
		//======= Frame Settings ==============
		JFrame frame = new JFrame("Space Craft");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(GamePanel.SCREEN_WIDTH, GamePanel.SCREEN_HEIGHT);
		frame.setResizable(false);
		
		//======= GameDrawer settings =========
		GamePanel gamePanel = new GamePanel();
		gamePanel.setFocusable(true);
		gamePanel.requestFocusInWindow();
		
		//=======  Add Panel  =================
		frame.add(gamePanel);
		
		//======= Frame Viability ==============
		frame.setVisible(true);
		
		//======== Start The Game ==============
		gamePanel.startGame();
	}
}
