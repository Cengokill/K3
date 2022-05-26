package Vue;

import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;

import Vue.Menu.Chargement.TypeFenetre;
import Vue.Menu.StartJeuClics.DetectionSurvol;


public class NouvellePartieClics implements MouseListener {
	
	public NouvellePartie nouvellePartie;
	
	public NouvellePartieClics(NouvellePartie panel) {
		super();
		this.nouvellePartie = panel;
		DetectionSurvol survol = new DetectionSurvol();
		this.nouvellePartie.addMouseMotionListener(survol);
		
	}
	public boolean clicPVP(MouseEvent e){
		int startx = nouvellePartie.posX_PVP;
		int starty = nouvellePartie.posY_PVP;
		int hauteurBouton=nouvellePartie.hauteur_bouton1;
		int largeurBouton=nouvellePartie.largeur_bouton1;
		if(e.getX() >= startx && e.getX() <= startx+largeurBouton && e.getY() >= starty && e.getY() <= starty+hauteurBouton) {
			return true;
		}else return false;
	}
	
	public boolean clicPVM(MouseEvent e){
		int startx = nouvellePartie.posX_PVM;
		int starty = nouvellePartie.posY_PVM;
		int hauteurBouton=nouvellePartie.hauteur_bouton1;
		int largeurBouton=nouvellePartie.largeur_bouton1;
		if(e.getX() >= startx && e.getX() <= startx+largeurBouton && e.getY() >= starty && e.getY() <= starty+hauteurBouton) {
			return true;
		}else return false;
	}
	
	public boolean clicMVM(MouseEvent e){
		int startx = nouvellePartie.posX_MVM;
		int starty = nouvellePartie.posY_MVM;
		int hauteurBouton=nouvellePartie.hauteur_bouton1;
		int largeurBouton=nouvellePartie.largeur_bouton1;
		if(e.getX() >= startx && e.getX() <= startx+largeurBouton && e.getY() >= starty && e.getY() <= starty+hauteurBouton) {
			return true;
		}else return false;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(clicPVP(e)) {
			if(!nouvellePartie.enfonce_pb_PVP) {
				nouvellePartie.enfonce_pb_PVP=true;
				nouvellePartie.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
		}
		else if(clicPVM(e)) {
			if(!nouvellePartie.enfonce_pb_PVM) {
				nouvellePartie.enfonce_pb_PVM=true;
				nouvellePartie.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
		}
		else if(clicMVM(e)) {
			if(!nouvellePartie.enfonce_pb_MVM) {
				nouvellePartie.enfonce_pb_MVM=true;
				nouvellePartie.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
		}
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public class DetectionSurvol extends MouseMotionAdapter{
		public void mouseMoved(MouseEvent e) {
			if(clicPVP(e)) {
				nouvellePartie.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}else {
				nouvellePartie.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			nouvellePartie.repaint();
		}
	}
}
