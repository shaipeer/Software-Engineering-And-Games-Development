/**
	|===========================================================|
	|	  	Exercise #  :	 project							|
	|															|
	|   	File name   :	 SoundThread.java					|
	|		Date		:	 28/08/2015    	      				|
	|		Author 1   	:	 Shai Pe'er 	(032571580)			|
	|		Author 2   	:	 Denys Bedilov 	(327011813)			|
	|		Author 3   	:	 Rita Markovich	(304492291)			|
	|===========================================================|
*/

public class SoundThread extends Thread {
    private String fName;
    private int playMode;
    
    public SoundThread(String fileName, int mode)
    {
        fName = fileName;
        playMode = mode;
    }
    public void run()
    {
        if(playMode == AudioPlayer.ONCE)
             AudioPlayer.play(fName);
        else if(playMode == AudioPlayer.LOOP)
            AudioPlayer.playMultiple(fName, 5);
    }
    
}
