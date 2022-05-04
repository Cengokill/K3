package Modeles;

import java.util.LinkedList;

public class PyramideMontagne implements Pyramide {
	
	private Piece[][] pyramide;
	private int largeur;
	private int hauteur;

	public PyramideMontagne(int largeur, int hauteur) {
		this.largeur= largeur;
		this.hauteur= hauteur;
	}
	
	public LinkedList<Piece> listePositionsAccescible() {
		boolean premierecase;
		boolean dernierecase;
		
		LinkedList<Piece> p = new LinkedList<Piece>();
		//dernier place de la pyramide
		if(pyramide[hauteur-1][0] == null && pyramide[hauteur-2][0] != null && pyramide[hauteur-2][1] != null) {
			Position pp = new Position(hauteur-1,0);
			Piece newP;
			newP = new Piece(pyramide[hauteur-2][0].getColor(),pp);
			p.add(newP);
			newP = new Piece(pyramide[hauteur-2][1].getColor(),pp);
			p.add(newP);
			p.add(newP);
		}
		//intérrieur pyramide
		else {
			int i = hauteur-2 ;
			while( i >= 0){
				int j = 0;
				while( j < pyramide[i].length) {
					// en haut a droite
					if(j-1 < 0) {
						premierecase = true; //si c'est la derniere case de la ligne en haut a gauche = libre
					}else {
						premierecase = (pyramide[i+1][j-1] == null); //on regarde en haut a gauche = libre
					}
					
					if(j == pyramide[i-1].length) {
						dernierecase = true; //si c'est la derniere case de la ligne en haut a droite = libre
					}else {
						dernierecase = (pyramide[i+1][j] == null); //on regarde en haut a droite = libre
					}
					
					//on regarde ne haut a gauche + en haut a droite
					if(premierecase && dernierecase && pyramide[i-1][j] != null && pyramide[i-1][j+1] != null){ // on regarde si il ya des pieces porteuses
						Position pp = new Position(i,j);
						Piece newP;
						newP = new Piece(pyramide[i-1][j].getColor(),pp);
						p.add(newP);
						newP = new Piece(pyramide[i-1][j+1].getColor(),pp);
						p.add(newP);
					}
				}
			}
		}
		return p;
	}

	public void empiler(Piece piece, Position p) {
		if(p.y >= hauteur || p.y < 0 || p.x >= pyramide[p.y].length || p.x < 0 ) {
			System.err.println("erreur position impossible : " + p.x + "," + p.y);
		}
		else if(p.y == 0) {
			// pas de piece porteuse
			pyramide[p.y][p.x] = piece;
		}
		else {
			// avec piece porteuse
			int porteurDroitx = p.x;
			int porteurDroity = p.y-1;
			Piece porteurDroit = pyramide[porteurDroity][porteurDroitx];
			int porteurGauchex = porteurDroitx +1;
			int porteurGauchey = p.y-1;
			Piece porteurGauche = pyramide[porteurGauchey][porteurGauchex];
			
			if( porteurDroit != null && porteurGauche !=null) {
				if(porteurDroit.getColor() == piece.getColor() || porteurGauche.getColor() == piece.getColor()) {
					pyramide[p.y][p.x] = piece;
				}else {
					System.err.println("la piece n'est pas de la bonne couleur");
				}
			}else {
				System.err.println("la piece n'as pas de piece porteuse");
			}
		}
	}

	public int getlargeur() {
		return this.largeur;
	}

	public int getHauteur() {
		return this.hauteur;
	}

	public Piece getPiece(Position p) {
		if(p.y >= hauteur || p.y < 0 || p.x >= pyramide[p.y].length || p.x < 0 ) {
			System.err.println("erreur position impossible : " + p.x + "," + p.y);
			return null;
		}
		return pyramide[p.y][p.x];
		
	}
}
