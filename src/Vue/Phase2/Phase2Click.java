package Vue.Phase2;

import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;



public class Phase2Click implements MouseListener{
	
	private PanelPhase2 panel;
	
	//CONSTRUTEUR--------------------------------
	public Phase2Click(PanelPhase2 panel) {
		super();
		this.panel = panel;
	}
	
	//SAUVEGARDE--------------------------------
	public boolean clicSave(MouseEvent e){
		int startx = panel.posX_sauvegarder;
		int starty = panel.posY_sauvegarder;
		int hauteurBouton=panel.largeur_sauvegarder;
		int largeurBouton=panel.hauteur_sauvegarder;
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
			panel.partie_actuel.partieEnCours.sauvegarderPartie("test.txt");
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
			if(clicSave(e)) {
				panel.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}else {
				panel.setCursor(new Cursor(0));
			}
		}
	}
}
