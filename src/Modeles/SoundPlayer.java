package Modeles;

import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class SoundPlayer extends Thread { 
	private Clip clip;
	private String[] cheminsSons = new String[25];//stocker les chemins des sons
	private String chemin;
	private int numSon;
	private FloatControl volumeLevel;
	
	public SoundPlayer(int volumeSounds, int volumeMusic) {
		//this.chemin=System.getProperty("user.home")+ "/Desktop/Jeu_K3/";
		this.chemin="./res/Sounds/";
		// EFFETS SONORES
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
		// MUSIQUES
		/*
		cheminsSons[14] = chemin+"/musiqueAccueil1.wav";
		cheminsSons[14] = chemin+"/musiqueAccueil2.wav";
		cheminsSons[14] = chemin+"/musiqueAccueil3.wav";
		cheminsSons[14] = chemin+"/musiqueEnPartie1.wav";
		cheminsSons[14] = chemin+"/musiqueEnPartie2.wav";
		cheminsSons[14] = chemin+"/musiqueEnPartie3.wav";
		*/
	}
	
	public void run() {
		super.run();
		if(this.numSon==14) {
			int r=Aleatoire.genInt(10000,60000);
			try {
			    Thread.sleep(r);
			} catch (InterruptedException ie) {
			    // ...
			}
			this.clip.start();
		}else {
			this.clip.start();
		}
	}
	
	public void setVolume(int v) {
		float volume;
		if(v>6.0f)
			volume=6.0f;
		else if(v<-80.0f) volume=0.0f;
		else volume=(float)v;
		this.volumeLevel.setValue(volume);
	}
	
	public void setFile(int i) {
		this.numSon=i;
		File f;
		try {
			f=new File(cheminsSons[numSon]);
			AudioInputStream input = AudioSystem.getAudioInputStream(f);
			this.clip = AudioSystem.getClip();
			this.clip.open(input);
			volumeLevel=(FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
			
		}catch(Exception e){
			System.err.println("Impossible d'ouvrir le fichier son "+cheminsSons[numSon]);
			System.err.println("Chemin actuel : "+this.chemin);
		}
	}
	
	public void jouerSon(int i) {
		int r=Aleatoire.genInt(i,i+2);
		setFile(r);
		play();
	}
	

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
	
	public void play() {
		this.clip.start();
	}
	
	public void loop() {
		this.clip.loop(Clip.LOOP_CONTINUOUSLY);
	}
	/*
	public void stop() {
		this.clip.stop();
	}
	*/
} 

