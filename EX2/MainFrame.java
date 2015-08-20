
import javax.swing.JFrame;

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

public class MainFrame 
{
	public static int SCREEN_SIZE_W  = 600;
	public static int SCREEN_SIZE_H  = 760;
	
	public static void main(String[] args) 
	{
		JFrame frame = new JFrame("2048");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(SCREEN_SIZE_W, SCREEN_SIZE_H);
      
        //=======  Add Panels  =================
        frame.add(new GameBoard());
        frame.setVisible(true);

	}
}
