package Modeles;

import java.util.ArrayList;

public class PyramideMontagne extends Pyramide {

	public PyramideMontagne(int largeur, int hauteur) {
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

	public ArrayList<PiecePyramide> piecesPosables() {// renvoie la liste de toutes les pieces que l'on peut placer sur la pyramide de la base
		boolean caseSupGauche, caseSupDroite;
		PiecePyramide newC;

		ArrayList<PiecePyramide> p = new ArrayList<PiecePyramide>();
		// derniere place de la pyramide
		if (pyramide[hauteur - 1][0] == null && pyramide[hauteur - 2][0] != null && pyramide[hauteur - 2][1] != null) {
			Position pp = new Position(hauteur - 1, 0);
			newC = new PiecePyramide(new Piece(pyramide[hauteur - 2][0].getColor()), pp);
			p.add(newC);
			newC = new PiecePyramide(new Piece(pyramide[hauteur - 2][1].getColor()), pp);
			p.add(newC);
		}
		// interieur pyramide
		else {
			for (int i = hauteur - 2; i >= 0; i--) {
				for (int j = 0; j < pyramide[i].length; j++) {
					// en haut a droite
					if (pyramide[i][j] == null) {// si la case courante ne contient pas de piece
						if (j == 0) {
							caseSupGauche = true; // si c'est la premiere case de la ligne en haut a gauche qui n'existe
													// pas
						} else {
							caseSupGauche = (pyramide[i + 1][j - 1] == null); // on regarde si en haut a gauche est
						} // libre
						if (j == pyramide[i].length - 1) {
							caseSupDroite = true; // si c'est la derniere case de la ligne en haut a droite qui n'existe
													// pas
						} else {
							caseSupDroite = (pyramide[i + 1][j] == null); // on regarde si en haut a droite est libre
						}

						// on regarde ne haut a gauche + en haut a droite
						if (caseSupGauche && caseSupDroite) { // on regarde si il ya des pieces porteuses
							if (j != (pyramide[i].length - 1) && i != 0) {
								if (i == 0 || (pyramide[i - 1][j] != null && pyramide[i - 1][j + 1] != null)) {
									Position pp = new Position(i, j);
									newC = new PiecePyramide(new Piece(pyramide[i - 1][j].getColor()), pp);
									if (p.size() == 0) {
										p.add(newC);
										// System.out.println("ajout : " +
										// newC.getPiece().toString()+":"+newC.getPos().toString());
									} else {
										if (!p.get(p.size() - 1).egal(newC)) {
											// System.out.println(newC.getPiece().toString()+":"+newC.getPos().toString()+"
											// n'est pas identique e
											// "+p.get(p.size()-1).getPiece().toString()+":"+p.get(p.size()-1).getPos().toString());
											p.add(newC);
										}
									}
									newC = new PiecePyramide(new Piece(pyramide[i - 1][j + 1].getColor()), pp);
									if (!p.get(p.size() - 1).egal(newC)) {
										// System.out.println(newC.getPiece().toString()+":"+newC.getPos().toString()+"
										// n'est pas identique e
										// "+p.get(p.size()-1).getPiece().toString()+":"+p.get(p.size()-1).getPos().toString());
										p.add(newC);
									}
								}
							}
						}
					}
				}
			}
		}
		return p;
	}

	public void empiler(PiecePyramide pp) {
		Piece piece = pp.getPiece();
		Position p = pp.getPos();
		if (p.etage >= hauteur || p.etage < 0 || p.rang >= pyramide[p.etage].length || p.rang < 0) {
			System.err.println("Erreur : la position (" + p.rang + "," + p.etage + ") est impossible.");
			return;
		} else if (p.etage == 0) {
			// pas de piece porteuse
			pyramide[p.etage][p.rang] = piece;
		} else {
			// avec piece porteuse
			int porteurDroitRang = p.rang;
			int porteurDroitEtage = p.etage - 1;
			Piece porteurDroit = pyramide[porteurDroitEtage][porteurDroitRang];
			int porteurGaucheRang = porteurDroitRang + 1;
			int porteurGaucheEtage = p.etage - 1;
			Piece porteurGauche = pyramide[porteurGaucheEtage][porteurGaucheRang];

			if (porteurDroit != null && porteurGauche != null) {
				if (porteurDroit.getColor() == piece.getColor() || porteurGauche.getColor() == piece.getColor()) {
					pyramide[p.etage][p.rang] = piece;
				} else {
					System.err.println("La piece n'est pas de la bonne couleur qu'une des 2 pieces porteuses.");
					return;
				}
			} else {
				System.err.println("Impossible d'empiler une piece sur du vide. Il faut au moins 2 pieces porteuses.");
				return;
			}
		}
	}

	public void ajouter(Piece p, Position pos) {
		pyramide[pos.etage][pos.rang] = p;
	}
}
