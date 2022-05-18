package Vue.Phase1;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.Color;
import javax.swing.JPanel;

import Modeles.*;

public class Phase1Panel extends JPanel{
	
	private Partie partieEnCours;
	private Piece pieceSelectionnee;
	
	public int OldX = 0;
	public int OldY = 0;
	public int currentX = 0;
	public int currentY = 0;
	
	public Phase1Panel(Partie partieEnCours){
		this.partieEnCours=partieEnCours;
		DragListener dragListener = new DragListener();
		this.addMouseMotionListener(dragListener);
	}
	
	public Acteur initAffichageJoueurs() {
		Acteur a;
		if(this.partieEnCours.getJoueurCourant()==0) {
			a=this.partieEnCours.joueur1();
		}else {
			a=this.partieEnCours.joueur2();
		}
		return a;
	}
	
	public Piece getPieceSelectionnee() {
		return pieceSelectionnee;
	}
	public void setPieceSelectionnee(Piece p) {
		pieceSelectionnee = p;
		this.repaint();
	}
	
	public void empiler(Position positionPiecePyramide) {
		Acteur a = initAffichageJoueurs();
		PiecePyramide pp=new PiecePyramide(pieceSelectionnee,positionPiecePyramide);
		if(a.getCamp().empiler(pp)) {
			a.setPiecesPosees(pp);
			pieceSelectionnee = null;
			a.getPiecesPiochees().remove(a.getCamp().getPiece(positionPiecePyramide));
			this.repaint();
		}
	}
	
	public Color getpetitcolor(Piece p) {
		if(p == null) {
			return Color.GRAY;
		}
		else {
			Couleurs colorP = p.getColor();
			if(colorP == Couleurs.BLEU) {
				return Color.BLUE;
			}
			else if(colorP == Couleurs.VERT) {
				return Color.GREEN;
			}else if(colorP == Couleurs.JAUNE) {
				return Color.YELLOW;
			}else if(colorP == Couleurs.ROUGE) {
				return Color.RED;
			}else if(colorP == Couleurs.NOIR) {
				return Color.BLACK;
			}else if(colorP == Couleurs.BLANC) {
				return Color.WHITE;
			}else if(colorP == Couleurs.NATUREL) {
				return Color.ORANGE;
			}else {
				return Color.MAGENTA;
			}
		}
		
	}
	
	public void affichePioche(Graphics g) {
		/*
		g.setColor(Color.white);
		g.fillRect(piecesPiochees.size()*20, 200+pyramideDuJoueur.hauteur*20, 19, 19);
		*/
		Acteur a = initAffichageJoueurs();
		for(int i = 0; i < a.getPiecesPiochees().size(); i++) {
			Piece p = a.getPiecesPiochees().get(i);
			g.setColor(getpetitcolor(p));
			g.fillRect(i*20, 200+a.getCamp().getHauteur()*20, 19, 19);
		}
	}
	
	public void affichePyramide(Graphics g) {
		Acteur a = initAffichageJoueurs();
		Position positionPiecePyramide;
		for(int etage = 0; etage < a.getCamp().getHauteur(); etage++) {
			for(int rang = 0; rang < a.getCamp().getLargeur() - etage; rang++) {
				positionPiecePyramide = new Position(etage,rang);
				Piece pieceJoueur = a.getCamp().getPiece(positionPiecePyramide);
				g.setColor(getpetitcolor(pieceJoueur));
				g.fillRect(rang*20, 100+a.getCamp().getHauteur()*20 - etage*20, 19, 19);
			}
		}
	}
	
	public void afficheBaseMontagne(Graphics g) {
		PyramideMontagne m = this.partieEnCours.getBaseMontagne();
		Position positionPiecePyramide;
		for(int etage = 0; etage < m.getHauteur(); etage++) {
			for(int rang = 0; rang < m.getLargeur() - etage; rang++) {
				positionPiecePyramide = new Position(etage,rang);
				Piece piece = m.getPiece(positionPiecePyramide);
				g.setColor(getpetitcolor(piece));
				g.fillRect(rang*20, 500+m.getHauteur()*20 - etage*20, 19, 19);
			}
		}
	}
	
	public void dragNdrop(Graphics g) {
		if(pieceSelectionnee != null) {
			/*
			g.setColor(this.getBackground()); // petite trainner a supprimer
			g.fillRect(OldX, OldY, 10, 10);
			*/
			
			g.setColor(getpetitcolor(pieceSelectionnee));
			g.fillRect(currentX, currentY, 10, 10);
		}
	}
	
	public void paint(Graphics g) {
		g.setColor(this.getBackground());
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		g.setColor(getpetitcolor(pieceSelectionnee));
		g.fillRect(0, 0, 100, 100);
		afficheBaseMontagne(g);
		affichePyramide(g);
		affichePioche(g);
		dragNdrop(g);
	}
	
	private class DragListener extends MouseMotionAdapter{	
		public void mouseMoved(MouseEvent e) {
			OldX = currentX;
			OldY = currentY;
			currentX = e.getX();
			currentY = e.getY();
			repaint();
		}
	}
	
}
