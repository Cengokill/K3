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
	
	public LinkedList<PiecePyramide> piecesPosables() {//renvoie la liste de toutes les pièces que l'on peut placer sur la pyramide de la base
		boolean caseSupGauche;
		boolean caseSupDroite;
		PiecePyramide newC;
		
		LinkedList<PiecePyramide> p = new LinkedList<PiecePyramide>();
		//derniere place de la pyramide
		if(pyramide[hauteur-1][0] == null && pyramide[hauteur-2][0] != null && pyramide[hauteur-2][1] != null) {
			Position pp = new Position(hauteur-1,0);
			newC = new PiecePyramide(new Piece(pyramide[hauteur-2][0].getColor()),pp);
			p.add(newC);
			newC = new PiecePyramide(new Piece(pyramide[hauteur-2][1].getColor()),pp);
			p.add(newC);
		}
		//interieur pyramide
		else {
			for(int i = hauteur-2; i>= 0; i--){
				for(int j = 0; j < pyramide[i].length; j++) {
					// en haut a droite
					if(pyramide[i][j]==null) {//si la case courante ne contient pas de piece
						if(j==0) {
							caseSupGauche = true; //si c'est la premiere case de la ligne en haut a gauche qui n'existe pas
						}else {
							caseSupGauche = (pyramide[i+1][j-1] == null); //on regarde si en haut a gauche est libre
						}
						
						if(j == pyramide[i].length-1) {
							caseSupDroite = true; //si c'est la derniere case de la ligne en haut a droite qui n'existe pas
						}else {
							caseSupDroite = (pyramide[i+1][j] == null); //on regarde si en haut a droite est libre
						}
						
						//on regarde ne haut a gauche + en haut a droite
						if(caseSupGauche && caseSupDroite){ // on regarde si il ya des pieces porteuses
							if(i==0 || (pyramide[i-1][j] != null && pyramide[i-1][j+1] != null)) {
								Position pp = new Position(i,j);
								newC = new PiecePyramide(new Piece(pyramide[i-1][j].getColor()),pp);
								p.add(newC);
								newC = new PiecePyramide(new Piece(pyramide[i-1][j+1].getColor()),pp);
								p.add(newC);
							}
						}
					}
				}
			}
		}
		return p;
	}

	public void empiler(PiecePyramide pp) {
		Piece piece=pp.getPiece();
		Position p=pp.getPos();
		if(p.y >= hauteur || p.y < 0 || p.x >= pyramide[p.y].length || p.x < 0 ) {
			System.err.println("Erreur : la position (" + p.x + "," + p.y+") est impossible.");
			return;
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
					System.err.println("La piece n'est pas de la bonne couleur qu'une des 2 pieces porteuses.");
					return;
				}
			}else {
				System.err.println("Impossible d'empiler une pièce sur du vide. Il faut au moins 2 pieces porteuses.");
				return;
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
