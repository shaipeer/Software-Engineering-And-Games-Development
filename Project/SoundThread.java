/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author user
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
