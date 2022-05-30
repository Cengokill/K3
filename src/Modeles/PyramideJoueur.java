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

	public boolean contiens(ArrayList<PiecePyramide> arr, PiecePyramide pp) {
		PiecePyramide pCour;
		for (int i = 0; i < arr.size(); i++) {
			pCour = arr.get(i);
			if (pp.egal(pCour)) {
				return true;
			}
		}
		return false;
	}

	public ArrayList<PiecePyramide> piecesJouables() {// renvoie les pieces que le joueur peut prendre a partir de sa
														// pyramide
		int etage;
		int rang;
		PiecePyramide newPieceJouable;
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
					newPieceJouable = new PiecePyramide(pyramide[etage][rang], piecesVerif.get(0));
					if (!contiens(arr, newPieceJouable)) {
						arr.add(newPieceJouable);
					}
				}
			}
			piecesVerif.remove(0);
		}
		return arr;
	}

	public ArrayList<Position> posDisponibles() {// renvoie les positions des pieces que le joueur peut placer sur sa
													// pyramide
		ArrayList<Position> arr = new ArrayList<Position>();
		int i = 0;
		int max_etage = 6;
		while (i < max_etage) {
			for (int j = 0; j < this.pyramide[i].length; j++) {// pour chaque rang
				if (pyramide[i][j] == null && (i == 0 || aPiecesPorteuses(i, j))) {
					arr.add(new Position(i, j));
				}
			}
			i++;
		}
		return arr;
	}

	public PyramideJoueur clonepyraJ() {
		PyramideJoueur clone = new PyramideJoueur(this.largeur, this.hauteur);
		for (int i = 0; i < this.hauteur; i++) {
			for (int j = 0; j < (6 - i); j++) {
				if (this.pyramide[i][j] != null) {
					clone.pyramide[i][j] = new Piece(this.pyramide[i][j].getColor());
				} else {
					clone.pyramide[i][j] = null;
				}
			}
		}
		for (PiecePyramide p : this.historiquePieces) {
			clone.historiquePieces.add(new PiecePyramide(new Piece(p.getPiece().getColor()),
					new Position(p.getPos().etage, p.getPos().rang)));
		}
		return clone;
	}

}
