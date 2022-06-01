package Vue.Option;

import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;

public class OptionPanelClick implements MouseListener {
	private OptionPanel panel;
	public String typeCurseur;

	// CONSTRUTEUR--------------------------------
	public OptionPanelClick(OptionPanel panel) {
		super();
		this.panel = panel;
		DragListener dragListener = new DragListener();
		this.panel.addMouseMotionListener(dragListener);
	}

	// BOUTON CHARGEMENT--------------------------------
	public boolean clickBoutonRetourMenu(MouseEvent e) {
		int x = panel.posXRetourMenu;
		int y = panel.posYRetourMenu;
		int l = x + panel.largeurRetourMenu;
		int h = y + panel.hauteurRetourMenu;
		return (e.getX() >= x && e.getX() <= l && e.getY() >= y && e.getY() <= h);
	}

	public boolean clickBoutonActive(MouseEvent e) {
		int x = panel.posXActive;
		int y = panel.posYActive;
		int l = x + panel.largeurActive;
		int h = y + panel.hauteurActive;
		return (e.getX() >= x && e.getX() <= l && e.getY() >= y && e.getY() <= h);
	}

	public void mouseClicked(MouseEvent e) {
		if (clickBoutonRetourMenu(e)) {
			panel.jouerSonClic();
			panel.chargement.lancement = true;
			panel.chargement.setProchaineFenetre(panel.chargement.getProchainePrecedent());
			// panel.ecrireOptions();
		} else if (clickBoutonActive(e)) {
			panel.jouerSonClic();
			if (!panel.enfonce_active) {
				panel.enfonce_active = true;
				panel.options.modePleinEcran = 1;
				// panel.ecrireOptions();
				panel.fullScreenOn();
			} else {
				panel.enfonce_active = false;
				panel.options.modePleinEcran = 0;
				// panel.ecrireOptions();
				panel.fullScreenOff();
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

	public class DragListener extends MouseMotionAdapter {
		public void mouseMoved(MouseEvent e) {
			if (clickBoutonRetourMenu(e)) {
				panel.setCursor(new Cursor(Cursor.HAND_CURSOR));
				panel.presseRetourMenu = true;
			} else if (clickBoutonActive(e)) {
				panel.setCursor(new Cursor(Cursor.HAND_CURSOR));
			} else {
				panel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				panel.presseRetourMenu = false;
			}
			if (panel.oldPresseRetourMenu != panel.presseRetourMenu) {
				panel.oldPresseRetourMenu = panel.presseRetourMenu;
				panel.paint(panel.getGraphics());
			}
		}
	}

}
