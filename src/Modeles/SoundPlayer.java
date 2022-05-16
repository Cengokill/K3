package Modeles;

import java.io.File; 
import java.util.Scanner; 
import java.io.IOException; 
import javax.sound.sampled.*;

public class SoundPlayer { 
	
	public void playSound(String chemin) { 
		try {
			File musicPath=new File(chemin);
			if(musicPath.exists()) {
				AudioInputStream audioStream = AudioSystem.getAudioInputStream(musicPath);
				Clip clip = AudioSystem.getClip(); 
				clip.open(audioStream);
				clip.start();
				clip.loop(Clip.LOOP_CONTINUOUSLY);
			}else {
				System.err.println("Erreur : "+chemin+" n'existe pas.");
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	} 
} 

