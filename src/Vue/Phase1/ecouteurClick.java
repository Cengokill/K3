package Vue.Phase1;

import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;

import Modeles.Acteur;
import Modeles.Joueur;
import Modeles.Position;

public class ecouteurClick implements MouseListener {
	
	private Phase1Panel panel;
	public int typeCurseur;
	
	
	public Position clickpyramide(MouseEvent e){
		int startx = 0;
		int starty = panel.POS_BASE_JOUEUR;
		int realx = e.getX() - startx;
		int realy = e.getY() - starty;
		
		Acteur a = panel.initAffichageJoueurs();
		int y = a.getCamp().getHauteur() - realy / (panel.TAILLE_CUBES+1);
		if(y<0 || y>=a.getCamp().getHauteur()) {
			return null;
		}
		
		realx-=((panel.TAILLE_CUBES+1)/2)*y;
		int x = realx / (panel.TAILLE_CUBES+1);
		if(x<0 || x>=a.getCamp().getLargeur()-y) {
			return null;
		}
		
		
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
		DragListener dragListener = new DragListener();
		this.panel.addMouseMotionListener(dragListener);
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if(panel.initAffichageJoueurs().getClass() == Joueur.class) {
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
			if(panel.initAffichageJoueurs().getClass() == Joueur.class) {
				panel.OldX = panel.currentX;
				panel.OldY = panel.currentY;
				panel.currentX = e.getX();
				panel.currentY = e.getY();
				if(clickpyramide(e)!=null) {
					typeCurseur = 12;
				}
				else if(clickPioche(e)!=-1) {
					typeCurseur = 12;
				}else {
					typeCurseur = 0;
				}
				if(panel.getCursor().getType() != typeCurseur) {
					panel.setCursor(new Cursor(typeCurseur));
				}
				panel.repaint();
			}
			else if(panel.getCursor().getType() != 3) {
				panel.setCursor(new Cursor(3));
			}
		}
	}

}
