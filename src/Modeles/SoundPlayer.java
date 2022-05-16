package Modeles;

import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class SoundPlayer { 
	
	private Clip clip;
	private String[] cheminsSons = new String[25];//stocker les chemins des sons
	private String chemin;
	
	public SoundPlayer() {
		//this.chemin=System.getProperty("user.home")+ "/Desktop/Jeu_K3/";
		this.chemin="./res/Sounds/";
		cheminsSons[0] = chemin+"victoire.wav";//ok
		cheminsSons[1] = chemin+"PoserPieceCamp/poserPieceCamp1.wav";
		cheminsSons[2] = chemin+"PoserPieceCamp/poserPieceCamp2.wav";
		cheminsSons[3] = chemin+"PoserPieceCamp/poserPieceCamp3.wav";
		cheminsSons[4] = chemin+"PoserPieceMontagne/poserPieceMontagne1.wav";
		cheminsSons[5] = chemin+"PoserPieceMontagne/poserPieceMontagne2.wav";
		cheminsSons[6] = chemin+"PoserPieceMontagne/poserPieceMontagne3.wav";
		cheminsSons[7] = chemin+"PoserPieceNaturelle/poserPieceNaturelle1.wav";
		cheminsSons[8] = chemin+"PoserPieceNaturelle/poserPieceNaturelle2.wav";
		cheminsSons[9] = chemin+"PoserPieceNaturelle/poserPieceNaturelle3.wav";
		cheminsSons[10] = chemin+"PasserTour/passerTour1.wav";
		cheminsSons[11] = chemin+"PasserTour/passerTour2.wav";
		cheminsSons[12] = chemin+"PasserTour/passerTour3.wav";
		cheminsSons[11] = chemin+"JouerCoupBlanc/jouerCoupBlanc1.wav";
		cheminsSons[12] = chemin+"JouerCoupBlanc/jouerCoupBlanc2.wav";
		cheminsSons[13] = chemin+"JouerCoupBlanc/jouerCoupBlanc2.wav";
		cheminsSons[14] = chemin+"/cave1.wav";
		/*
		cheminsSons[4] = chemin+"lancementPartie.wav";
		cheminsSons[5] = chemin+"melange.wav";
		cheminsSons[6] = chemin+"launch1.wav";
		cheminsSons[7] = chemin+"votre_tour.wav";
		cheminsSons[10] = chemin+"joueurFiniCamp.wav";
		cheminsSons[11] = chemin+"joueurPerdu.wav";
		cheminsSons[12] = chemin+"joueurGagne.wav";
		*/
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
		int r=Aleatoire.genInt(i,i+2);
		setFile(r);
		play();
	}
	
	/*
	public void jouerSonTimeRandom() {
		setFile(14);
		int r=Aleatoire.genInt(1000,6000);
		try {
		    Thread.sleep(r);
		} catch (InterruptedException ie) {
		    // ...
		}
		play();
	}
	*/
	
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

