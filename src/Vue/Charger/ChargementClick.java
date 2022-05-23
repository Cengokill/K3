package Vue.Charger;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;

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
	
	//GESTION SOURI----------------------------------------------------------------
	@Override
	public void mouseClicked(MouseEvent e) {
		if(clickBoutonLoad(e)) {
			
			if(panel.list.getSelectedValue() != null) {
				System.out.println("CHARGEMENT DE LA PARTI SELLECTIONNER : "+panel.list.getSelectedValue());
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
	
	public class DragListener extends MouseMotionAdapter{
		public void mouseMoved(MouseEvent e) {
			if(clickBoutonLoad(e)) {
				//System.out.println("animation du bouton");
			}
		}
	}

}
