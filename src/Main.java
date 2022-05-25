
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
		window.setLocationRelativeTo(null);//centrage de la fenetre
		
		Chargement chargement = new Chargement();
		InitPartie partie = new InitPartie();
		
		int prochaineFenetre = chargement.getProchaineFenetre();
		chargement.lancement = true;
		
		while(chargement.getProchaineFenetre()!=4) {
			if(chargement.lancement == true) {
				prochaineFenetre = chargement.getProchaineFenetre();
				chargement.lancement = false;
				if(prochaineFenetre==0) {
					lancementMenu(window, texture, chargement);
				}
				else if(prochaineFenetre==1) {
					lancementNouvellePartie(window, texture, partie, chargement);
				}
				else if(prochaineFenetre==2){
					lancementChargerPartie(window, texture, partie, chargement);
				}else if(prochaineFenetre==3) {
					lancementTuto(window, texture, chargement);
				}
			}
		}
		
		
		window.setTitle("Partie en cours");
        window.setMinimumSize(new java.awt.Dimension(1200, 1080));
        window.setResizable(true);
		new Jeu(window, partie);
	}
	
	public static void lancementMenu(JFrame window, LoadTexture texture, Chargement chargement) {
		
		StartJeu panel = new StartJeu(window, chargement, texture);
		window.setContentPane(panel);
		panel.addMouseListener(new StartJeuClics(panel));
		window.paintAll(window.getGraphics());

		while(!panel.chargement.lancement) {
			Jeu.timer(100);
		}
	}
	
	public static void lancementNouvellePartie(JFrame window, LoadTexture texture, InitPartie partie, Chargement chargement) {
		
		partie.paramCharges = false;
		
		new New_game(window, partie);
		while(!partie.paramCharges) {
			Jeu.timer(100);
		}
		chargement.setProchaineFenetre(4);
	}
	
	public static void lancementChargerPartie(JFrame window, LoadTexture texture, InitPartie partie, Chargement chargement) {
		
		partie.paramCharges = false;
		
		ChargerPanel panel = new ChargerPanel(window, texture, chargement, partie);
		window.setContentPane(panel);
		panel.addMouseListener(new ChargementClick(panel));
		window.paintAll(window.getGraphics());
		while(!chargement.lancement) {
			Jeu.timer(100);
		}
	}
	
	public static void lancementOption(JFrame window, LoadTexture texture, Chargement chargement) {
		
		OptionPanel panel = new OptionPanel(window,texture, chargement);
		window.setContentPane(panel);
		//panel.addMouseListener(new ChargementClick(panel));
		window.paintAll(window.getGraphics());
		while(!chargement.lancement) {
			Jeu.timer(100);
		}
		
	}
	
	public static void lancementTuto(JFrame window, LoadTexture texture, Chargement chargement) {
		
		TutorielPanel panel = new TutorielPanel(window,texture, chargement);
		window.setContentPane(panel);
		panel.addMouseListener(new TutorielClick(panel));
		window.paintAll(window.getGraphics());
		while(!chargement.lancement) {
			Jeu.timer(100);
		}
	}
}
