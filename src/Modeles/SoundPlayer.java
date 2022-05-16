package Modeles;

import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JOptionPane;

public class SoundPlayer { 
	
	private String chemin;
	
	public SoundPlayer(String chemin) {
		this.chemin=chemin;
	}
	
	public void playSound() { 
		try {
			File musicPath=new File(this.chemin);
			if(musicPath.exists()) {
				AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
				Clip clip = AudioSystem.getClip(); 
				clip.open(audioInput);
				clip.start();
				//clip.loop(Clip.LOOP_CONTINUOUSLY);
				//clip.close();
				JOptionPane.showMessageDialog(null, "Press ok to stop.");
			}else {
				System.err.println("Erreur : "+this.chemin+" n'existe pas.");
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	} 
} 

