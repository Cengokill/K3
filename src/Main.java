
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;

import Controleur.*;
import Modeles.InitPartie;
import Vue.Phase0.New_game;
//import Modeles.SoundPlayer;

public class Main {
	public static void main(String args[]) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		System.out.println("K3");
		/*
		String chemin="C:/Users/Killian/Desktop/Développement Eclipse/Espace de travail/K3/src/Ressources/";
		String cheminSons=chemin+"launch1.wav";
		SoundPlayer simpleSoundPlayer = new SoundPlayer(cheminSons);
		simpleSoundPlayer.playSound();
		*/
		JFrame window = new JFrame("Jeu K3");
		window.setSize(1200,1080);
		window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		InitPartie partie = new InitPartie();
		new New_game(window, partie);
		while(!partie.paramCharges) {
			Jeu.timer(100);
		}
        window.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        window.setTitle("Partie en cours");
        window.setMinimumSize(new java.awt.Dimension(1200, 1080));
        window.setResizable(true);
		Jeu j=new Jeu(window, partie);
	}
}
