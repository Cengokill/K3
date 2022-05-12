package Modeles;

import java.util.ArrayList;

public class PyramideJoueur extends Pyramide {
	/*
	 * y � | x->
	 * 3+
	 * 2+ +
	 * 1+++++++
	 * 0++++++++
	 */
	public PyramideJoueur(int largeur, int hauteur) {
		if (largeur <= 0 || hauteur <= 0 || hauteur >= LARGEUR_MAX || largeur >= HAUTEUR_MAX) {
			System.err.println("erreur taille impossible : " + largeur + "," + hauteur);
		} else {
			this.largeur = largeur;
			this.hauteur = hauteur;
			int l = largeur;

			Piece[][] etage = new Piece[hauteur][];
			for (int i = 0; i < hauteur; i++) {
				Piece[] ligne = new Piece[l];
				etage[i] = ligne;
				l = l - 1;
			}
			this.pyramide = etage;
		}
	}

	public void empiler(PiecePyramide pp) {
		Piece piece = pp.getPiece();
		Position p = pp.getPos();
		if (p.etage >= hauteur || p.etage < 0 || p.rang >= pyramide[p.etage].length || p.rang < 0) {
			System.err.println("Erreur position impossible : " + p.rang + "," + p.etage);
		} else if (p.etage == 0) {
			// pas de piece porteuse
			pyramide[p.etage][p.rang] = piece;
		} else {
			// avec piece porteuse
			int porteurDroitRang = p.rang;
			int porteurDroitEtage = p.etage - 1;
			int porteurGaucheRang = porteurDroitRang + 1;
			int porteurGaucheEtage = p.etage - 1;
			if (pyramide[porteurDroitEtage][porteurDroitRang] != null
					&& pyramide[porteurGaucheEtage][porteurGaucheRang] != null) {
				pyramide[p.etage][p.rang] = piece;
			} else {
				System.err.println("La piece n'a pas de piece porteuse.");
			}
		}
	}

	public ArrayList<PiecePyramide> piecesJouables() {// renvoie les pieces que le joueur peut prendre a partir de sa
														// pyramide
		int etage;
		int rang;
		ArrayList<PiecePyramide> arr = new ArrayList<PiecePyramide>();
		ArrayList<Position> piecesVerif = new ArrayList<Position>();
		piecesVerif.add(new Position(hauteur - 1, 0));
		while (!piecesVerif.isEmpty()) {
			etage = piecesVerif.get(0).etage;
			rang = piecesVerif.get(0).rang;
			if (pyramide[etage][rang] == null) { // modification car une piece de la base a pu etre joue et ne doit pas
													// etre renvoye(erreur car on renvoie une piece null)
				if (etage != 0) {
					piecesVerif.add(new Position(etage - 1, rang));
					piecesVerif.add(new Position(etage - 1, rang + 1));
				}
			} else {// case contenant une piece
				if (super.estPiecesPorteuses(piecesVerif.get(0))) {
					arr.add(new PiecePyramide(pyramide[etage][rang], piecesVerif.get(0)));
				}
			}
			piecesVerif.remove(0);
		}
		return arr;
	}
	
	public ArrayList<Position> posDisponibles() {// renvoie les positions des pieces que le joueur peut placer sur sa pyramide
		ArrayList<Position> arr = new ArrayList<Position>();
		Position posCourante;
		int i=0;
		int max_etage=6;
		while(i<max_etage) {
			for(int j=0; j<this.pyramide[i].length; j++) {
				posCourante=new Position(i,j);
				if(pyramide[i][j]==null && (i==0 || aPiecesPorteuses(posCourante))) {
					arr.add(new Position(i,j));
				}
			}
			i++;
		}
		return arr;
	}

}
