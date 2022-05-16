package Modeles;

import java.io.File; 
import java.util.Scanner; 
import java.io.IOException; 
import javax.sound.sampled.*;

public class SoundPlayer {
	private Long nowFrame; 
	private Clip clip; 
	private String thestatus; 
	
	public SoundPlayer(String chemin) throws LineUnavailableException, UnsupportedAudioFileException, IOException {
		Scanner scanner = new Scanner(System.in);
		System.out.println(chemin);
		File fichier = new File(chemin);
		AudioInputStream audioStream = AudioSystem.getAudioInputStream(fichier); 
		Clip clip = AudioSystem.getClip(); 
		clip.open(audioStream);
		clip.loop(Clip.LOOP_CONTINUOUSLY); 
		
		String response = scanner.next();
	}

	public static void main(String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		// the input stream object 
		new SoundPlayer(args[0]);
		//scanner.close(); 
	} 
	
	@SuppressWarnings("unused")
	private void choisir(int a) throws IOException, LineUnavailableException, UnsupportedAudioFileException { 
		switch (a) { 
			case 1: 
				pause(); 
				break; 
			case 2: 
				stop(); 
				break; 
		}
	} 
	
	public void play() { 
		this.clip.start();
		this.thestatus = "play"; 
	} 
	
	public void pause() { 
		if (this.thestatus.equals("paused")) 
		{ 
			System.out.println("audio is already paused"); 
			return; 
		} 
		this.nowFrame = 
		this.clip.getMicrosecondPosition(); 
		this.clip.stop(); 
		this.thestatus = "paused"; 
	} 
	
	public void stop() throws UnsupportedAudioFileException, 
	IOException, LineUnavailableException { 
		this.nowFrame = 0L; 
		this.clip.stop(); 
		this.clip.close(); 
	}

} 

