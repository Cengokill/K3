package Vue.Phase2;

import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;

import Vue.Menu.Chargement.TypeFenetre;

public class Phase2Click implements MouseListener{
	
	private PanelPhase2 panel;
	
	//CONSTRUTEUR--------------------------------
	public Phase2Click(PanelPhase2 panel) {
		super();
		this.panel = panel;
		this.panel.addMouseMotionListener(new DragListener());
	}
	
	//SAUVEGARDE--------------------------------
	public boolean clicSave(MouseEvent e){
		int startx = panel.posX_sauvegarder;
		int starty = panel.posY_sauvegarder;
		int largeurBouton=panel.largeur_sauvegarder;
		int hauteurBouton=panel.hauteur_sauvegarder;
		if(e.getX() >= startx && e.getX() <= startx+largeurBouton && e.getY() >= starty && e.getY() <= starty+hauteurBouton) {
			return true;
		}else return false;
	}
	
	public boolean clicValider(MouseEvent e){
		int startx = panel.posX_valider;
		int starty = panel.posY_valider;
		int largeurBouton=panel.largeur_valider;
		int hauteurBouton=panel.hauteur_valider;
		if(e.getX() >= startx && e.getX() <= startx+largeurBouton && e.getY() >= starty && e.getY() <= starty+hauteurBouton) {
			return true;
		}else return false;
	}
	
	public boolean clicFermer(MouseEvent e){
		int startx = panel.posX_fermer;
		int starty = panel.posY_fermer;
		int largeurBouton=panel.largeur_fermer;
		int hauteurBouton=panel.largeur_fermer;
		if(e.getX() >= startx && e.getX() <= startx+largeurBouton && e.getY() >= starty && e.getY() <= starty+hauteurBouton) {
			return true;
		}else return false;
	}
	
	public boolean clicRetour(MouseEvent e){
		int startx = panel.posX_back;
		int starty = panel.posY_back;
		int hauteurBouton=panel.hauteur_back;
		int largeurBouton=panel.largeur_back;
		if(e.getX() >= startx && e.getX() <= startx+largeurBouton && e.getY() >= starty && e.getY() <= starty+hauteurBouton) {
			return true;
		}else return false;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if(clicSave(e)) {
			panel.popup_save=true;
		}
		if(clicRetour(e)) {
			/*
			panel.chargement.nouvellePartie=false;
			panel.chargement.lancement = true;
			panel.chargement.setProchaineFenetre(TypeFenetre.MENU);
			*/
		}
		if(clicFermer(e)) {
			panel.popup_save=false;
		}
		if(clicValider(e)){
			if(!panel.nomSave.getText().isEmpty()) {
				String chemin=panel.jeu.cheminSauvegardes;
				panel.jeu.partieEnCours.sauvegarderPartie(chemin+panel.nomSave.getText()+".saveK3");
				panel.popup_save=false;
			}
		}
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
	
	public class DragListener extends MouseMotionAdapter{
		public void mouseMoved(MouseEvent e) {
			if(panel.popup_save) {
				if(clicValider(e) || clicFermer(e)) {
					panel.setCursor(new Cursor(Cursor.HAND_CURSOR));
				}else {
					panel.setCursor(new Cursor(0));
				}
			}
			else if(clicSave(e)) {
				panel.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			else {
				panel.setCursor(new Cursor(0));
			}
		}
	}
}
