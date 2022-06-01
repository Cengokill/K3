package Vue.Charger;

import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;

import Vue.Menu.Chargement.TypeFenetre;

public class ChargementClick implements MouseListener {
	
	private ChargerPanel panel;
	public String typeCurseur;
	
	//CONSTRUTEUR--------------------------------
	public ChargementClick(ChargerPanel panel) {
		super();
		this.panel = panel;
		DragListener dragListener = new DragListener();
		this.panel.addMouseMotionListener(dragListener);
	}
	
	
	//BOUTON CHARGEMENT--------------------------------
	public boolean clickBoutonLoad(MouseEvent e){
		int x = panel.posXBoutonLoad;
		int y = panel.posYBoutonLoad;
		int l = x + panel.largeurBoutonLoad;
		int h = y + panel.hauteurBoutonLoad;
		return (e.getX() >= x && e.getX() <= l && e.getY() >= y && e.getY() <= h);
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
		if(clickBoutonLoad(e)) {
			if(panel.list.getSelectedValue() != null) {
				panel.jouerSonClic();
				panel.initPartie.nomFichierCharge=panel.list.getSelectedValue().toString();
				String a =panel.initPartie.nomFichierCharge;
				System.out.println(a);
				panel.chargement.lancement = true;
				panel.chargement.setProchaineFenetre(TypeFenetre.LOAD);
			}
		}else if(clickBoutonRetourMenu(e)) {
			panel.jouerSonClic();
			System.out.println("retour menu principal");
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
			if(clickBoutonLoad(e)) {
				panel.setCursor(new Cursor(Cursor.HAND_CURSOR));
				panel.presseBoutonLoad = true;
				panel.presseRetourMenu = false;
			}else if(clickBoutonRetourMenu(e)) {
				panel.setCursor(new Cursor(Cursor.HAND_CURSOR));
				panel.presseRetourMenu = true;
				panel.presseBoutonLoad = false;
			}else {
				panel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				panel.presseBoutonLoad = false;
				panel.presseRetourMenu = false;
			}
			if(panel.oldPresseRetourMenu !=panel.presseRetourMenu) {
				panel.oldPresseRetourMenu =panel.presseRetourMenu;
				panel.repaint();
			}
			if(panel.oldPresseBoutonLoad !=panel.presseBoutonLoad) {
				panel.oldPresseBoutonLoad =panel.presseBoutonLoad;
				panel.repaint();
			}
		}
	}

}
