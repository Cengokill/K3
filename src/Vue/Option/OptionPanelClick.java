package Vue.Option;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;

import Vue.Menu.Chargement.TypeFenetre;

public class OptionPanelClick implements MouseListener {
	private OptionPanel panel;
	public String typeCurseur;
	
	//CONSTRUTEUR--------------------------------
	public OptionPanelClick(OptionPanel panel) {
		super();
		this.panel = panel;
		DragListener dragListener = new DragListener();
		this.panel.addMouseMotionListener(dragListener);
	}
	
	//BOUTON CHARGEMENT--------------------------------
	public boolean clickBoutonRetourMenu(MouseEvent e){
		int x = panel.posXRetourMenu;
		int y = panel.posYRetourMenu;
		int l = x + panel.largeurRetourMenu;
		int h = y + panel.hauteurRetourMenu;
		return (e.getX() >= x && e.getX() <= l && e.getY() >= y && e.getY() <= h);
	}
	
	public void mouseClicked(MouseEvent e) {
		if(clickBoutonRetourMenu(e)) {
			System.out.println("retour menu principal");
			panel.chargement.lancement = true;
			panel.chargement.setProchaineFenetre(panel.chargement.getProchainePrecedent());
			panel.ecrireOptions();
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
	
	public class DragListener extends MouseMotionAdapter{
		public void mouseMoved(MouseEvent e) {
			if(clickBoutonRetourMenu(e)) {
				panel.presseRetourMenu = true;
			}else {
				panel.presseRetourMenu = false;
			}
			if(panel.oldPresseRetourMenu != panel.presseRetourMenu) {
				panel.oldPresseRetourMenu = panel.presseRetourMenu;
				panel.repaint();
			}
			
		}
	}
	
}
