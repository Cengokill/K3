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
		super(largeur, hauteur);
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
			if (pyramide[etage][rang] == null) {
				if (etage != 0) {
					piecesVerif.add(new Position(etage - 1, rang));
					piecesVerif.add(new Position(etage - 1, rang + 1));
				}
			} else {// case contenant une piece
				if (super.estPiecePorteuse(piecesVerif.get(0))) {
					arr.add(new PiecePyramide(pyramide[etage][rang], piecesVerif.get(0)));
				}
			}
			piecesVerif.remove(0);
		}
		return arr;
	}
	
	public ArrayList<Position> posDisponibles() {// renvoie les positions des pieces que le joueur peut placer sur sa pyramide
		ArrayList<Position> arr = new ArrayList<Position>();
		int i=0;
		int max_etage=6;
		while(i<max_etage) {
			for(int j=0; j<this.pyramide[i].length; j++) {//pour chaque rang
				if(pyramide[i][j]==null && (i==0 || aPiecesPorteuses(i,j))) {
					arr.add(new Position(i,j));
				}
			}
			i++;
		}
		return arr;
	}

}
