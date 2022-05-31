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
	private StartJeu startJeuPanel;
	private NouvellePartie newPartiePanel;
	private ChargerPanel chargerPanel;
	private OptionPanel optionsPanel;
	private TutorielPanel tutoPanel;
	private Phase1Panel phase1Panel;
	private Phase2 phase2Panel;
	
	public IHM(JFrame w, OptionsJeu options, Jeu jeu, LoadTexture t, Chargement c, InitPartie p) {
		//RECUPERATION
		this.window=w;
		this.options = options;
		this.texture=t;
		this.jeu = jeu;
		this.chargement=c;
		this.partie=p;
		
		//MODIFICATION DE LA FENETRE
		window.setSize(1024,768);
		window.setMinimumSize(new Dimension(960, 540));
		window.setLocationRelativeTo(null);//centrage de la fenetre
		window.setVisible(true);
		//CREATION FENETRE
		this.startJeuPanel = new StartJeu(window, chargement, texture, options);
		this.newPartiePanel = new NouvellePartie(window, texture, partie, chargement, options);
		this.chargerPanel = new ChargerPanel(window, texture, chargement, options, partie);
		this.optionsPanel = new OptionPanel(window,texture, chargement, options);
		this.tutoPanel = new TutorielPanel(window,texture, chargement, options);
		//this.phase1Panel = new Phase1Panel(window, this.jeu.partieEnCours, this.texture);
		//this.phase2Panel = new Phase2();
		//SWITCH DE FENETRE
		TypeFenetre prochaineFenetre = chargement.getProchaineFenetre();
		chargement.lancement = true;
		while(chargement.getProchaineFenetre() != TypeFenetre.FIN) {
			System.out.println(chargement.getProchaineFenetre().toString());
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
			    	   jeu.initNewPhase1(partie);
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
		window.setContentPane(startJeuPanel);
		//panel.addMouseListener(new StartJeuClics(panel));
		window.paintAll(window.getGraphics());
		while(!chargement.lancement) {
			Jeu.timer(100);
			startJeuPanel.repaint();
		}
	}
	
	public void lancementNouvellePartie() {
		partie.paramCharges = false;	
		window.setContentPane(newPartiePanel);
		window.paintAll(window.getGraphics());
		while(!chargement.lancement) {
			Jeu.timer(200);
			newPartiePanel.repaint();
		}
	}
	
	public void lancementChargerPartie() {	
		partie.paramCharges = false;
		window.setContentPane(chargerPanel);
		chargerPanel.addMouseListener(new ChargementClick(chargerPanel));
		window.paintAll(window.getGraphics());
		while(!chargement.lancement) {
			Jeu.timer(100);
			chargerPanel.repaint();
		}
	}
	
	public void lancementOption() {	
		window.setContentPane(optionsPanel);
		window.paintAll(window.getGraphics());
		while(!chargement.lancement) {
			Jeu.timer(100);
			optionsPanel.repaint();
		}
	}
	
	public void lancementTuto() {	
		window.setContentPane(tutoPanel);
		tutoPanel.addMouseListener(new TutorielClick(tutoPanel));
		window.paintAll(window.getGraphics());
		while(!chargement.lancement) {
			Jeu.timer(100);
			tutoPanel.repaint();
		}
	}
	
	public void lancementPhase1() {	
		this.phase1Panel = new Phase1Panel(window, this.jeu.partieEnCours, this.texture);
		window.setContentPane(phase1Panel);
		window.paintAll(window.getGraphics());
		while(!chargement.lancement) {
			Jeu.timer(100);
			//phase1Panel.repaint();
		}
	}
	
	public void lancementPhase2() {	
		this.phase2Panel = new Phase2();
		window.setContentPane(phase2Panel);
		window.paintAll(window.getGraphics());
		while(!chargement.lancement) {
			Jeu.timer(100);
			phase2Panel.repaint();
		}
	}
	
	public void changementFenetre(TypeFenetre prochaineFenetre) {	
		chargement.setProchaineFenetre(prochaineFenetre);
		chargement.lancement = true;
	}
}