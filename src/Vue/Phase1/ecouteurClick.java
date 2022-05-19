package Vue.Phase1;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import Modeles.Acteur;
import Modeles.Position;

public class ecouteurClick implements MouseListener {
	
	private Phase1Panel panel;
	
	public Position clickpyramide(MouseEvent e){
		int startx = 0;
		int starty = panel.POS_BASE_JOUEUR;
		int realx = e.getX() - startx;
		int realy = e.getY() - starty;
		
		Acteur a = panel.initAffichageJoueurs();
		int y = a.getCamp().getHauteur() - realy / (panel.TAILLE_CUBES+1);
		if(y<0) {
			return null;
		}
		
		int x = realx / (panel.TAILLE_CUBES+1);
		x-=((panel.TAILLE_CUBES+1)/2)*y;
		if(x<0) {
			return null;
		}
		System.out.println(x+" , "+y);
		
		return new Position(y,x);
	}
	
	public int clickPioche(MouseEvent e){
		Acteur a = panel.initAffichageJoueurs();
		int startx = 0;
		int starty = panel.POS_PIOCHE;
		int realx = e.getX() - startx;
		int realy = e.getY() - starty;
		
		int x = realx / (panel.TAILLE_CUBES+1);
		if(x<0 || x >= a.getPiecesPiochees().size()) {
			return -1;
		}
		if(realy < 0 || realy > panel.TAILLE_CUBES+1) {
			return -1;
		}		
		return x;
	}

	public ecouteurClick(Phase1Panel panel) {
		super();
		this.panel = panel;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1) { 
			if(panel.getPieceSelectionnee() == null) { // selectionner si aucune piece lock
				int index = clickPioche(e);
				if(index>=0) {
					Acteur a = panel.initAffichageJoueurs();
					panel.setPieceSelectionnee(a.getPiecesPiochees().get(index));
				}
			}else {
				Position p = clickpyramide(e);
				if(p != null) {
					panel.empiler(p);//on empile la piece ! a l'endroit clicker
				}
			}
		}else { // déssélectionner avec click droit
			panel.setPieceSelectionnee(null);
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

}
