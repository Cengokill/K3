package Modeles;

import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class SoundPlayer { 
	
	private Clip clip;
	private String[] cheminsSons = new String[20];//stocker les chemins des sons
	private String chemin;
	
	public SoundPlayer() {
		//this.chemin=System.getProperty("user.home")+ "/Desktop/Jeu_K3/";
		this.chemin="C:/Users/Killian/Desktop/Développement Eclipse/Espace de travail/K3/res/Sounds/";
		cheminsSons[0] = chemin+"victoire.wav";//ok
		cheminsSons[1] = chemin+"poserPieceCamp.wav";//ok
		cheminsSons[2] = chemin+"poserPieceMontagne.wav";
		cheminsSons[3] = chemin+"poserPieceNaturelle.wav";
		cheminsSons[4] = chemin+"lancementPartie.wav";
		cheminsSons[5] = chemin+"melange.wav";
		cheminsSons[6] = chemin+"launch1.wav";
		cheminsSons[7] = chemin+"votre_tour.wav";
		cheminsSons[8] = chemin+"jouerCoupBlanc.wav";
		cheminsSons[9] = chemin+"passerTour.wav";
		cheminsSons[10] = chemin+"joueurFiniCamp.wav";
		cheminsSons[11] = chemin+"joueurPerdu.wav";
		cheminsSons[12] = chemin+"joueurGagne.wav";
		System.out.println(cheminsSons[0]);
	}
	
	public void setFile(int i) {
		File f;
		try {
			f=new File(cheminsSons[i]);
			AudioInputStream input = AudioSystem.getAudioInputStream(f);
			this.clip = AudioSystem.getClip();
			this.clip.open(input);
			System.out.println(cheminsSons[i]);
		}catch(Exception e){
			System.err.println("Impossible d'ouvrir le fichier son "+cheminsSons[i]);
			System.err.println("Chemin actuel : "+this.chemin);
		}
	}
	
	public void jouerSon(int i) {
		setFile(i);
		play();
	}
	
	public void play() {
		this.clip.start();
	}
	
	public void loop() {
		this.clip.loop(Clip.LOOP_CONTINUOUSLY);
	}
	
	public void stop() {
		this.clip.stop();
	}
} 

