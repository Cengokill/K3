
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

import Controleur.*;
import Modeles.InitPartie;
import Vue.Menu.Chargement;
import Vue.Menu.StartJeu;
import Vue.Menu.StartJeuClics;
import Vue.Option.*;
import Vue.Phase0.New_game;
import Vue.Phase1.GifAnime;
//import Modeles.SoundPlayer;
import Vue.Phase1.Phase1Panel;
import Vue.Phase1.ecouteurClick;
import Vue.TexturePack.LoadTexture;
import Vue.Charger.*;
import Vue.Tutoriel.*;

public class Main {
	
	public static void main(String args[]) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		
		JFrame window = new JFrame("Jeu K3");
		LoadTexture texture = new LoadTexture("./src/Ressources/");
		window.setSize(1024,768);
		
		
		
		int prochaineFenetre = lancementMenu(window, texture);
		InitPartie partie = new InitPartie();
		if(prochaineFenetre==0) {
			lancementNouvellePartie(window, texture, partie);
		}
		else if(prochaineFenetre==1){
			lancementChargerPartie(window, texture, partie);
		}else if(prochaineFenetre==2) {
			lancementTuto(window, texture);
		}
		window.setTitle("Partie en cours");
        window.setMinimumSize(new java.awt.Dimension(1200, 1080));
        window.setResizable(true);
		new Jeu(window, partie);
	}
	
	public static int lancementMenu(JFrame window, LoadTexture texture) {
		Chargement chargement = new Chargement();
		StartJeu panel = new StartJeu(window, chargement, texture);
		window.setContentPane(panel);
		panel.addMouseListener(new StartJeuClics(panel));
		Jeu.timer(100);
		panel.repaint();

		while(!panel.chargement.lancement) {
			Jeu.timer(100);
		}
		return chargement.prochaineFenetre;
	}
	
	public static void lancementNouvellePartie(JFrame window, LoadTexture texture, InitPartie partie) {
		
		new New_game(window, partie);
		while(!partie.paramCharges) {
			Jeu.timer(100);
		}
	}
	
	public static void lancementChargerPartie(JFrame window, LoadTexture texture, InitPartie partie) {
		
		ChargerPanel panel = new ChargerPanel(window, texture);
		window.setContentPane(panel);
		panel.addMouseListener(new ChargementClick(panel));
		Jeu.timer(100);
		panel.repaint();
		while(!partie.paramCharges) {
			Jeu.timer(100);
		}
	}
	
	public static void lancementOption(JFrame window, LoadTexture texture) {
		
		OptionPanel panel = new OptionPanel(window,texture);
		window.setContentPane(panel);
		//panel.addMouseListener(new ChargementClick(panel));
		Jeu.timer(100);
		panel.repaint();
		while(true) {
			Jeu.timer(100);
		}
	}
	
	public static void lancementTuto(JFrame window, LoadTexture texture) {
		
		TutorielPanel panel = new TutorielPanel(window,texture);
		window.setContentPane(panel);
		panel.addMouseListener(new TutorielClick(panel));
		Jeu.timer(100);
		panel.repaint();
		while(true) {
			Jeu.timer(100);
		}
	}
}
