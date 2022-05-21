
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;

import Controleur.*;
import Modeles.InitPartie;
import Vue.Menu.Chargement;
import Vue.Menu.StartJeu;
import Vue.Menu.StartJeuClics;
import Vue.Phase0.New_game;
import Vue.Phase1.GifAnime;
//import Modeles.SoundPlayer;
import Vue.Phase1.Phase1Panel;
import Vue.Phase1.ecouteurClick;

public class Main {
	public static void main(String args[]) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		
		JFrame window = new JFrame("Jeu K3");
		window.setResizable(true);
		Chargement chargement = new Chargement();
		
		StartJeu panel = new StartJeu(window, chargement);
		panel.addMouseListener(new StartJeuClics(panel));
		window.setContentPane(panel);
		while(!panel.chargement.lancement) {
			Jeu.timer(100);
		}
		InitPartie partie = new InitPartie();
		new New_game(window, partie);
		while(!partie.paramCharges) {
			Jeu.timer(100);
		}
        window.setTitle("Partie en cours");
        window.setMinimumSize(new java.awt.Dimension(1200, 1080));
        window.setResizable(true);
		Jeu j=new Jeu(window, partie);
	}
}
