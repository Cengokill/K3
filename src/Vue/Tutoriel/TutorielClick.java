package Vue.Tutoriel;

import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;

public class TutorielClick implements MouseListener {
	
	private TutorielPanel panel;
	public String typeCurseur;
	
	//CONSTRUTEUR--------------------------------
	public TutorielClick(TutorielPanel panel) {
		super();
		this.panel = panel;
		DragListener dragListener = new DragListener();
		this.panel.addMouseMotionListener(dragListener);
	}
	
	
	//BOUTON CHARGEMENT--------------------------------
	public boolean clickBoutonSuivant(MouseEvent e){
		if(panel.fenetreActuel < panel.fenetreMax) {
			int x = panel.posXSuivant;
			int y = panel.posYSuivant;
			int l = x + panel.largeurSuivant;
			int h = y + panel.hauteurSuivant;
			return (e.getX() >= x && e.getX() <= l && e.getY() >= y && e.getY() <= h);
		}
		return false;
	}
	
	//BOUTON CHARGEMENT--------------------------------
	public boolean clickBoutonPrecedent(MouseEvent e){
		if(panel.fenetreActuel > panel.fenetreMin) {
			int x = panel.posXPrecedent;
			int y = panel.posYPrecedent;
			int l = x + panel.largeurPrecedent;
			int h = y + panel.hauteurPrecedent;
			return (e.getX() >= x && e.getX() <= l && e.getY() >= y && e.getY() <= h);
		}
		return false;
	}
	
	//BOUTON CHARGEMENT--------------------------------
	public boolean clickBoutonRetourMenu(MouseEvent e){
		int x = panel.posXRetourMenu;
		int y = panel.posYRetourMenu;
		int l = x + panel.largeurRetourMenu;
		int h = y + panel.hauteurRetourMenu;
		return (e.getX() >= x && e.getX() <= l && e.getY() >= y && e.getY() <= h);
	}
	
	//GESTION SOURI----------------------------------------------------------------
	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if(clickBoutonSuivant(e)) {
			panel.jouerSonClic();
			panel.tutoSuivant();
			panel.repaint();
		}else if(clickBoutonPrecedent(e)) {
			panel.jouerSonClic();
			panel.tutoPrecedent();
			panel.repaint();
		}else if(clickBoutonRetourMenu(e)) {
			panel.jouerSonClic();
			panel.chargement.lancement = true;
			panel.chargement.setProchaineFenetre(panel.chargement.getProchainePrecedent());
		}
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
	
	public class DragListener extends MouseMotionAdapter{
		public void mouseMoved(MouseEvent e) {
			if(clickBoutonSuivant(e)) {
				panel.setCursor(new Cursor(Cursor.HAND_CURSOR));
				panel.presseSuivant = true;
			}else if(clickBoutonPrecedent(e)) {
				panel.setCursor(new Cursor(Cursor.HAND_CURSOR));
				panel.pressePrecedent = true;
			}else if(clickBoutonRetourMenu(e)) {
				panel.setCursor(new Cursor(Cursor.HAND_CURSOR));
				panel.presseRetourMenu = true;
			}else {
				panel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				panel.presseSuivant = false;
				panel.pressePrecedent = false;
				panel.presseRetourMenu = false;
			}
			panel.repaint();
		}
	}

}