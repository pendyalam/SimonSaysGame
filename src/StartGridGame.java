import java.awt.*;
import java.io.*;
import javax.swing.*;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import sun.audio.ContinuousAudioDataStream;

/**
* The Simon Says game implements an application that
* displays a pattern that the user needs to repeat.
*
* @author  Meghana Pendyala
* @since   2017-05-09
*/


public class StartGridGame {
	public static void main(String[] args)
	   {
	      JFrame frame = new JFrame();

	      final GridGame scene = new GridGame();
	     
	      frame.add (scene, BorderLayout.CENTER); // starts window and game
	      
	      frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
	      frame.setSize (800, 800);
	      frame.setVisible (true);
	      
	      music();
	   }
	
	/* Found a tutorial on how to add background music */
	public static void music() 
    {       
        AudioPlayer MGP = AudioPlayer.player;
        AudioStream BGM;

        ContinuousAudioDataStream loop = null;

        try
        {
            InputStream test = new FileInputStream("/Users/meghanpendyala/Desktop/CSCI 2300/FinalProject/src/Sunset_Lover.wav");
            BGM = new AudioStream(test);
            AudioPlayer.player.start(BGM);
        }
        catch(FileNotFoundException e){
            System.out.print(e.toString());
        }
        catch(IOException error)
        {
            System.out.print(error.toString());
        }
        MGP.start(loop);
    }
}
