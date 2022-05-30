package Vue;

import java.awt.Dimension;

import javax.swing.JFrame;

import Controleur.Jeu;
import Modeles.InitPartie;
import Modeles.OptionsJeu;

import Vue.TexturePack.*;
import Vue.Menu.*;
import Vue.Menu.Chargement.TypeFenetre;
import Vue.Option.*;
import Vue.Tutoriel.*;
import Vue.Phase1.*;
import Vue.Phase2.*;
import Vue.Charger.*;

public class IHM {
	
	private OptionsJeu options;
	private JFrame window;
	private LoadTexture texture;
	private Chargement chargement;
	private InitPartie partie;
	private Jeu jeu;
	
	
	public IHM(OptionsJeu options, Jeu jeu) {
		//RECUPERATION
		texture = jeu.textures;
		//CREATION
		
		chargement = new Chargement();
		partie = new InitPartie();
		window = new JFrame("Jeu K3");
		this.options = options;
		this.jeu = jeu;
		//MODIFICATION DE LA FENETRE
		window.setSize(1024,768);
		window.setMinimumSize(new Dimension(960, 540));
		window.setLocationRelativeTo(null);//centrage de la fenetre
		if(options.modePleinEcran==1) { //modePleinEcran
			window.setUndecorated(true);
			System.out.println("plein ecran");
		}
		//SWITCH DE FENETRE
		TypeFenetre prochaineFenetre = chargement.getProchaineFenetre();
		while(chargement.getProchaineFenetre()!= null) {
			if(chargement.lancement == true) {
				prochaineFenetre = chargement.getProchaineFenetre();
				chargement.lancement = false;
				switch(prochaineFenetre){
				   
			       case MENU: 
			    	   lancementMenu();
			           break;
			           
			       case NEWPARTIE:
			    	   lancementNouvellePartie();
			           break;
			   
			       case CHARGERPARTIE:
			    	   lancementChargerPartie();
			           break;
			           
			       case OPTION:
			    	   lancementOption();
			           break;
			           
			       case TUTO:
			    	   lancementTuto();
			           break;
			           
			       case PHASE1:
			    	   lancementPhase1();
			           break;
			           
			       case PHASE2:
			    	   lancementPhase2();
			           break;
			           
			       default:
			           System.out.println("Choix incorrect");
			           break;
				}
			}
		}
	}
	
	public void lancementMenu() {		
		StartJeu panel = new StartJeu(window, chargement, texture, options);
		window.setContentPane(panel);
		//panel.addMouseListener(new StartJeuClics(panel));
		window.paintAll(window.getGraphics());
		while(!chargement.lancement) {
			Jeu.timer(100);
		}
	}
	
	public void lancementNouvellePartie() {
		partie.paramCharges = false;	
		NouvellePartie newPartie = new NouvellePartie(window, texture, partie, options, chargement);
		window.setContentPane(newPartie);
		window.paintAll(window.getGraphics());
		while(!chargement.lancement) {
			Jeu.timer(200);
		}
	}
	
	public void lancementChargerPartie() {	
		partie.paramCharges = false;
		ChargerPanel panel = new ChargerPanel(window, texture, chargement, partie);
		window.setContentPane(panel);
		panel.addMouseListener(new ChargementClick(panel));
		window.paintAll(window.getGraphics());
		while(!chargement.lancement) {
			Jeu.timer(100);
		}
	}
	
	public void lancementOption() {	
		OptionPanel panel = new OptionPanel(window,texture, chargement, options);
		window.setContentPane(panel);
		window.paintAll(window.getGraphics());
		while(!chargement.lancement) {
			Jeu.timer(100);
		}
	}
	
	public void lancementTuto() {	
		TutorielPanel panel = new TutorielPanel(window,texture, chargement);
		window.setContentPane(panel);
		panel.addMouseListener(new TutorielClick(panel));
		window.paintAll(window.getGraphics());
		while(!chargement.lancement) {
			Jeu.timer(100);
		}
	}
	
	public void lancementPhase1() {	
		Phase1Panel panel = new Phase1Panel(window, this.jeu.partieEnCours, this.texture);
		window.setContentPane(panel);
		window.paintAll(window.getGraphics());
		while(!chargement.lancement) {
			Jeu.timer(100);
		}
	}
	
	public void lancementPhase2() {	
		window.setContentPane(null);
		Phase2 panel = new Phase2();
		window.paintAll(window.getGraphics());
		while(!chargement.lancement) {
			Jeu.timer(100);
		}
	}
	
	public void changementFenetre(TypeFenetre prochaineFenetre) {	
		chargement.setProchaineFenetre(prochaineFenetre);
		chargement.lancement = true;
	}
}
