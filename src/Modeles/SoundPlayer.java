package Modeles;

import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class SoundPlayer {
	private Clip clip;
	private String[] cheminsSons = new String[50];// stocker les chemins des sons
	private String chemin;
	private int numSon = 0, volume = 0;
	private FloatControl volumeLevel;

	public SoundPlayer(int volume, String c) {
		String path = "";
		this.numSon = -1;
		this.volume = volume;
		this.chemin = c + "Sounds/";

		// EFFETS SONORES
		cheminsSons[0] = chemin + "cave1.wav";
		cheminsSons[1] = chemin + "PoserPieceCamp/poserPieceCamp1.wav";
		cheminsSons[2] = chemin + "PoserPieceCamp/poserPieceCamp2.wav";
		cheminsSons[3] = chemin + "PoserPieceCamp/poserPieceCamp3.wav";
		cheminsSons[4] = chemin + "PoserPieceMontagne/poserPieceMontagne1.wav";// ok
		cheminsSons[5] = chemin + "PoserPieceMontagne/poserPieceMontagne2.wav";
		cheminsSons[6] = chemin + "PoserPieceMontagne/poserPieceMontagne3.wav";
		cheminsSons[7] = chemin + "PoserPieceNaturelle/poserPieceNaturelle1.wav";// ok
		cheminsSons[8] = chemin + "PoserPieceNaturelle/poserPieceNaturelle2.wav";
		cheminsSons[9] = chemin + "PoserPieceNaturelle/poserPieceNaturelle3.wav";
		cheminsSons[10] = chemin + "PasserTour/PasserTour1.wav";// ok
		cheminsSons[11] = chemin + "PasserTour/PasserTour2.wav";
		cheminsSons[12] = chemin + "PasserTour/PasserTour3.wav";
		cheminsSons[13] = chemin + "JouerCoupBlanc/jouerCoupBlanc1.wav";
		cheminsSons[14] = chemin + "JouerCoupBlanc/jouerCoupBlanc2.wav";
		cheminsSons[15] = chemin + "JouerCoupBlanc/jouerCoupBlanc2.wav";
		cheminsSons[16] = chemin + "cave1.wav";// ok
		cheminsSons[17] = chemin + "Cliquer/cliquer1.wav";// ok
		cheminsSons[18] = chemin + "Cliquer/cliquer2.wav";
		cheminsSons[19] = chemin + "Cliquer/cliquer3.wav";
		cheminsSons[20] = chemin + "ChargerFenetre/chargerFenetre1.wav";// ok
		cheminsSons[21] = chemin + "ChargerFenetre/chargerFenetre2.wav";
		cheminsSons[22] = chemin + "ChargerFenetre/chargerFenetre3.wav";
		cheminsSons[23] = chemin + "DemandeVol/demandeVol1.wav";// ok
		cheminsSons[24] = chemin + "DemandeVol/demandeVol2.wav";
		cheminsSons[25] = chemin + "DemandeVol/demandeVol3.wav";
		cheminsSons[26] = chemin + "VolerPiece/sifflement1.wav";// ok
		cheminsSons[27] = chemin + "VolerPiece/sifflement2.wav";
		cheminsSons[28] = chemin + "VolerPiece/sifflement3.wav";
		cheminsSons[29] = chemin + "LancementPartie/lancement1.wav";// ok
		cheminsSons[30] = chemin + "LancementPartie/lancement2.wav";
		cheminsSons[31] = chemin + "LancementPartie/lancement3.wav";
		cheminsSons[32] = chemin + "Victoire/victoire1.wav";// ok
		cheminsSons[33] = chemin + "Victoire/victoire2.wav";
		cheminsSons[34] = chemin + "Victoire/victoire3.wav";
		cheminsSons[35] = chemin + "DebutTour/debutTour1.wav";// ok
		cheminsSons[36] = chemin + "DebutTour/debutTour2.wav";
		cheminsSons[37] = chemin + "DebutTour/debutTour3.wav";
		cheminsSons[38] = chemin + "SurvolBouton/survolBouton1.wav";// ok
		cheminsSons[39] = chemin + "SurvolBouton/survolBouton2.wav";
		cheminsSons[40] = chemin + "SurvolBouton/survolBouton3.wav";
		cheminsSons[43] = chemin + "MusiqueFond/musiqueFond1.wav";
		cheminsSons[44] = chemin + "MusiqueFond/musiqueFond2.wav";
		cheminsSons[45] = chemin + "MusiqueFond/musiqueFond3.wav";
		/*
		 * cheminsSons[10] = chemin+"joueurFiniCamp.wav";
		 * cheminsSons[11] = chemin+"joueurPerdu.wav";
		 * cheminsSons[12] = chemin+"joueurGagne.wav";
		 */
		// MUSIQUES
		/*
		 * cheminsSons[14] = chemin+"/musiqueAccueil1.wav";
		 * cheminsSons[14] = chemin+"/musiqueAccueil2.wav";
		 * cheminsSons[14] = chemin+"/musiqueAccueil3.wav";
		 * cheminsSons[14] = chemin+"/musiqueEnPartie1.wav";
		 * cheminsSons[14] = chemin+"/musiqueEnPartie2.wav";
		 * cheminsSons[14] = chemin+"/musiqueEnPartie3.wav";
		 */
	}

	public void run() {
		// super.run();
		if (this.numSon == 0) {
			int r = Aleatoire.genInt(10000, 60000);
			try {
				Thread.sleep(r);
			} catch (InterruptedException ie) {
				// ...
			}
			jouerSon();
		} else {
			jouerSon();
		}
	}

	public int getNumSon() {
		return this.numSon;
	}

	public void setNumSon(int i) {
		this.numSon = i;
	}

	public void setVolume(int v) {
		float volumeConverti = Volume10ToRange(v);
		this.volumeLevel.setValue(volumeConverti);
	}

	public void setVolume2(int v) {
		this.volume = v;
	}

	public void jouerSon() {
		int r = Aleatoire.genInt(this.numSon, this.numSon + 2);
		setFile(r);
		setVolume(this.volume);
		play();
	}

	public void loopSon() {
		int r = Aleatoire.genInt(this.numSon, this.numSon + 2);
		setFile(r);
		setVolume(this.volume);
		loop();
	}

	public void setFile(int i) {
		File f;
		try {
			f = new File(cheminsSons[i]); // mettre en commentaire si jar
			AudioInputStream input = AudioSystem.getAudioInputStream(f); // getClass().getResource(cheminsSons[i])
																			// si
			// .jar
			this.clip = AudioSystem.getClip();
			this.clip.open(input);
			this.volumeLevel = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);

		} catch (Exception e) {
			System.err.println("Impossible d'ouvrir le fichier son " + cheminsSons[i]);
			System.err.println("Chemin actuel : " + this.chemin);
			System.err.println("Erreur : " + e.getMessage());
		}
	}

	public void jouerSonTimeRandom() {
		setFile(0);
		int r = Aleatoire.genInt(1000, 6000);
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

	public void stopSound() {
		this.clip.stop();
	}

	public float Volume10ToRange(int v) {// de -50 � 6
		float volume;
		switch (v) {
			case 0:
				volume = (float) -80;
				break;
			case 1:
				// volume = (float)-71.4;
				volume = (float) -37;
				break;
			case 2:
				volume = (float) -32.7;
				break;
			case 3:
				volume = (float) -28.4;
				break;
			case 4:
				volume = (float) -24.1;
				break;
			case 5:
				volume = (float) -19.8;
				break;
			case 6:
				volume = (float) -15.5;
				break;
			case 7:
				volume = (float) -11.2;
				break;
			case 8:
				volume = (float) -6.9;
				break;
			case 9:
				volume = (float) 1.7;
				break;
			case 10:
				volume = (float) 6;
				break;
			default:
				volume = 5;
		}
		return volume;
	}
}
