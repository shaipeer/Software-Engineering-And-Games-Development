/*
 *	SimpleAudioPlayer.java
 *
 *	This file is part of jsresources.org
 */

/*
 * Copyright (c) 1999 - 2001 by Matthias Pfisterer
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * - Redistributions of source code must retain the above copyright notice,
 *   this list of conditions and the following disclaimer.
 * - Redistributions in binary form must reproduce the above copyright
 *   notice, this list of conditions and the following disclaimer in the
 *   documentation and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS
 * FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE
 * COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT,
 * INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION)
 * HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT,
 * STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED
 * OF THE POSSIBILITY OF SUCH DAMAGE.
 */

/*
|<---            this code is formatted to fit into 80 columns             --->|
*/

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

public class AudioPlayer 
{
	private static final int EXTERNAL_BUFFER_SIZE = 128000;
        public static final int ONCE = 1, LOOP = 2;
	
        public static void playMultiple(String fName, int nLoopCount)
        {
            Clip clip = null;
            File clipFile = new File(fName);
            AudioInputStream audioInputStream = null;
            try
            {
                audioInputStream = AudioSystem.getAudioInputStream(clipFile);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            if (audioInputStream != null)
            {
                AudioFormat	format = audioInputStream.getFormat();
                DataLine.Info	info = new DataLine.Info(Clip.class, format);
                try
                {
                        clip = (Clip) AudioSystem.getLine(info);
                        clip.open(audioInputStream);
                }
                catch (LineUnavailableException e)
                {
                        e.printStackTrace();
                }
                catch (IOException e)
                {
                        e.printStackTrace();
                }
                clip.loop(nLoopCount);
            }
            else
            {
                System.out.println("Error in reading " + clipFile.getName());
            }	
        }
      
	public static void play(String fileName)
	{
            File soundFile = new File(fileName);
            AudioInputStream audioInputStream = null;
            try
            {
                    audioInputStream = AudioSystem.getAudioInputStream(soundFile);
            }
            catch (Exception e)
            {
                    e.printStackTrace();
            }

            AudioFormat	audioFormat = audioInputStream.getFormat();

            SourceDataLine line = null;
            DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);
            try
            {
                line = (SourceDataLine) AudioSystem.getLine(info);
                line.open(audioFormat);
            }
            catch (LineUnavailableException e)
            {
                    e.printStackTrace();
            }
            catch (Exception e)
            {
                    e.printStackTrace();
            }

            line.start();

            int	nBytesRead = 0;
            byte[] abData = new byte[EXTERNAL_BUFFER_SIZE];
            while (nBytesRead != -1)
            {
                try
                {
                    nBytesRead = audioInputStream.read(abData, 0, abData.length);
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
                if (nBytesRead >= 0)
                {
                    int	nBytesWritten = line.write(abData, 0, nBytesRead);
                }
            }
            line.close();
	}
}
