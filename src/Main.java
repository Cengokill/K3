
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import Controleur.*;
import Modeles.SoundPlayer;

public class Main {
	public static void main(String args[]) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		System.out.println("K3");
		/*
		String chemin="C:/Users/Killian/Desktop/Développement Eclipse/Espace de travail/K3/src/Ressources/";
		String cheminSons=chemin+"launch1.wav";
		SoundPlayer simpleSoundPlayer = new SoundPlayer(cheminSons);
		simpleSoundPlayer.playSound();
		*/
		Jeu j=new Jeu("Gaston","Mademoiselle Jeanne",1);
	}
}
