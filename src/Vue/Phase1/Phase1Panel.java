package Vue.Phase1;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JPanel;

import Modeles.*;

public class Phase1Panel extends JPanel{
	
	private Partie partieEnCours;
	private Piece pieceSelectionnee;
	
	public int OldX = 0;
	public int OldY = 0;
	public int currentX = 0;
	public int currentY = 0;
	public int TAILLE_CUBES = 40;
	public int POS_PIECE_CHOISIE;
	public int POS_BASE_JOUEUR;
	public int POS_PIOCHE;
	public int POS_BASE_MONTAGNE;
	
	public Phase1Panel(Partie partieEnCours){
		this.partieEnCours=partieEnCours;
		DragListener dragListener = new DragListener();
		this.addMouseMotionListener(dragListener);
		this.POS_PIECE_CHOISIE = 0;
		this.POS_BASE_JOUEUR = 100;
		this.POS_PIOCHE = POS_BASE_JOUEUR+(TAILLE_CUBES+1)*(partieEnCours.joueur1().getCamp().getHauteur()+2);
		this.POS_BASE_MONTAGNE = POS_PIOCHE+TAILLE_CUBES+1;
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
		a.setPiecesPosees(pp);
		pieceSelectionnee = null;
		this.repaint();
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
		Acteur a = initAffichageJoueurs();
		for(int i = 0; i < a.getPiecesPiochees().size(); i++) {
			Piece p = a.getPiecesPiochees().get(i);
			g.setColor(getpetitcolor(p));
			g.fillRect(i*(TAILLE_CUBES+1), POS_PIOCHE, TAILLE_CUBES, TAILLE_CUBES);
		}
	}
	
	public void affichePyramide(Graphics g) {
		Acteur a = initAffichageJoueurs();
		Position positionPiecePyramide;
		int decalage=0;
		for(int etage = 0; etage < a.getCamp().getHauteur(); etage++) {
			for(int rang = 0; rang < a.getCamp().getLargeur() - etage; rang++) {
				positionPiecePyramide = new Position(etage,rang);
				Piece pieceJoueur = a.getCamp().getPiece(positionPiecePyramide);
				g.setColor(getpetitcolor(pieceJoueur));
				g.fillRect(decalage+rang*(TAILLE_CUBES+1), POS_BASE_JOUEUR+a.getCamp().getHauteur()*(TAILLE_CUBES+1) - etage*(TAILLE_CUBES+1), TAILLE_CUBES, TAILLE_CUBES);
			}
			decalage += (TAILLE_CUBES+1)/2;
		}
	}
	
	public void afficheBaseMontagne(Graphics g) {
		PyramideMontagne m = this.partieEnCours.getBaseMontagne();
		Position positionPiecePyramide;
		afficherNomJoueur(g);
		int decalage=0;
		for(int etage = 0; etage < m.getHauteur(); etage++) {
			for(int rang = 0; rang < m.getLargeur() - etage; rang++) {
				positionPiecePyramide = new Position(etage,rang);
				Piece piece = m.getPiece(positionPiecePyramide);
				g.setColor(getpetitcolor(piece));
				g.fillRect(decalage+rang*(TAILLE_CUBES+1), POS_BASE_MONTAGNE+m.getHauteur()*(TAILLE_CUBES+1) - etage*(TAILLE_CUBES+1), TAILLE_CUBES, TAILLE_CUBES);
			}
			decalage += (TAILLE_CUBES+1)/2;
		}
	}
	
	public void afficherNomJoueur(Graphics g) {
		String nom;
		if(this.partieEnCours.getJoueurCourant()==0) {
			nom = this.partieEnCours.joueur1().getNom();
		}else {
			nom = this.partieEnCours.joueur2().getNom();
		}
		g.setColor(Color.BLACK);
		g.setFont(new Font("Courier New", Font.BOLD, 25));
		g.drawString(nom, 300, POS_BASE_JOUEUR-POS_PIECE_CHOISIE+TAILLE_CUBES);
	}
	
	public void dragNdrop(Graphics g) {
		if(pieceSelectionnee != null) {
			/*
			g.setColor(this.getBackground()); // petite trainner a supprimer
			g.fillRect(OldX, OldY, 10, 10);
			*/
			g.setColor(getpetitcolor(pieceSelectionnee));
			g.fillRect(currentX, currentY, (int)(TAILLE_CUBES/1.5), (int)(TAILLE_CUBES/1.5));
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
