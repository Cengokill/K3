package Vue.Menu;

import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;

import Modeles.Acteur;
import Modeles.Joueur;
import Vue.Phase1.Phase1Panel;
import Vue.Phase1.ecouteurClick.DragListener;

public class StartJeuClics implements MouseListener{
	
	private StartJeu startJeu;
	public String typeCurseur;
	public Cursor cursor1, cursor2;
	public String CHEMIN="./src/Ressources/";
	
	public StartJeuClics(StartJeu panel) {
		super();
		this.startJeu = panel;
		DetectionSurvol survol = new DetectionSurvol();
		this.startJeu.addMouseMotionListener(survol);
	}
	
	public boolean clicNouvellePartie(MouseEvent e){
		int startx = startJeu.posX_bouton;
		int starty = startJeu.posY_nouvellePartie;
		int hauteurBouton=startJeu.hauteur_bouton;
		int largeurBouton=startJeu.largeur_bouton;
		if(e.getX() >= startx && e.getX() <= startx+largeurBouton && e.getY() >= starty && e.getY() <= starty+hauteurBouton) {
			return true;
		}else return false;
	}
	
	public boolean clicCharger(MouseEvent e){
		int startx = startJeu.posX_bouton;
		int starty = startJeu.posY_charger;
		int hauteurBouton=startJeu.hauteur_bouton;
		int largeurBouton=startJeu.largeur_bouton;
		if(e.getX() >= startx && e.getX() <= startx+largeurBouton && e.getY() >= starty && e.getY() <= starty+hauteurBouton) {
			return true;
		}else return false;
	}
	
	public boolean clicOptions(MouseEvent e){
		int startx = startJeu.posX_bouton;
		int starty = startJeu.posY_options;
		int hauteurBouton=startJeu.hauteur_bouton;
		int largeurBouton=startJeu.largeur_bouton;
		if(e.getX() >= startx && e.getX() <= startx+largeurBouton && e.getY() >= starty && e.getY() <= starty+hauteurBouton) {
			return true;
		}else return false;
	}
	
	public boolean clicTuto(MouseEvent e){
		int startx = startJeu.posX_bouton;
		int starty = startJeu.posY_tuto;
		int hauteurBouton=startJeu.hauteur_bouton;
		int largeurBouton=startJeu.largeur_bouton;
		if(e.getX() >= startx && e.getX() <= startx+largeurBouton && e.getY() >= starty && e.getY() <= starty+hauteurBouton) {
			return true;
		}else return false;
	}
	
	public boolean clicQuitter(MouseEvent e){
		int startx = startJeu.posX_bouton;
		int starty = startJeu.posY_quitter;
		int hauteurBouton=startJeu.hauteur_bouton;
		int largeurBouton=startJeu.largeur_bouton;
		if(e.getX() >= startx && e.getX() <= startx+largeurBouton && e.getY() >= starty && e.getY() <= starty+hauteurBouton) {
			return true;
		}else return false;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(clicNouvellePartie(e)) {
			System.out.println("lancer la nouvelle partie");
			this.startJeu.chargement.lancement=true;
			this.startJeu.chargement.setProchaineFenetre(1);
		}
		else if(clicQuitter(e)) {
			System.exit(0);
		}
		else if(clicCharger(e)) {
		}
		else if(clicOptions(e)) {
			this.startJeu.chargement.lancement=true;
			this.startJeu.chargement.setProchaineFenetre(2);
			//new OptionsPanel(startJeu.window, startJeu.getGraphics());
		}else if(clicTuto(e)) {
			this.startJeu.chargement.lancement=true;
			this.startJeu.chargement.setProchaineFenetre(3);
			//new OptionsPanel(startJeu.window, startJeu.getGraphics());
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}
	
	public class DetectionSurvol extends MouseMotionAdapter{
		public void mouseMoved(MouseEvent e) {
			if(clicNouvellePartie(e)) {
				if(!startJeu.enfonce_nouvellePartie) {
					startJeu.enfonce_nouvellePartie=true;
					startJeu.setCursor(new Cursor(Cursor.HAND_CURSOR));
					startJeu.jouerSonSurvol();
				}
			}else {
				startJeu.enfonce_nouvellePartie=false;
				if(clicOptions(e)) {
					if(!startJeu.enfonce_options) {
						startJeu.enfonce_options=true;
						startJeu.setCursor(new Cursor(Cursor.HAND_CURSOR));
						startJeu.jouerSonSurvol();
					}
				}else {
					startJeu.enfonce_options=false;
					if(clicQuitter(e)) {
						if(!startJeu.enfonce_quitter) {
							startJeu.enfonce_quitter=true;
							startJeu.setCursor(new Cursor(Cursor.HAND_CURSOR));
							startJeu.jouerSonSurvol();
						}
					}else {
						startJeu.enfonce_quitter=false;
						if(clicTuto(e)) {
							if(!startJeu.enfonce_tuto) {
								startJeu.enfonce_tuto=true;
								startJeu.setCursor(new Cursor(Cursor.HAND_CURSOR));
								startJeu.jouerSonSurvol();
							}
						}else {
							startJeu.enfonce_tuto=false;
							if(clicCharger(e)) {
								if(!startJeu.enfonce_charger) {
									startJeu.enfonce_charger=true;
									startJeu.setCursor(new Cursor(Cursor.HAND_CURSOR));
									startJeu.jouerSonSurvol();
								}}
								else {
									startJeu.enfonce_charger=false;
									startJeu.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
								}
						}
					}
				}
			}
			startJeu.repaint();
		}
	}

}
