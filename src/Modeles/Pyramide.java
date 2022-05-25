package Modeles;

import java.util.ArrayList;

public class Pyramide {

	protected int largeur;
	protected int hauteur;
	protected Piece[][] pyramide;
	public int LARGEUR_MAX = 50;
	public int HAUTEUR_MAX = 50;
	protected ArrayList<PiecePyramide> historiquePieces;

	public Pyramide(int largeur, int hauteur) {
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
			this.historiquePieces = new ArrayList<PiecePyramide>();
		}
	}

	public boolean estPleine() {
		return pyramide[hauteur - 1][0] != null;
	}

	public int getLargeur() {
		return this.largeur;
	}

	public int getHauteur() {
		return this.hauteur;
	}

	public Piece getPiece(Position p) {
		if (p.etage >= hauteur || p.etage < 0 || p.rang >= pyramide[p.etage].length || p.rang < 0) {
			System.err.println("erreur position impossible : " + p.rang + "," + p.etage);
			return null;
		}
		return this.pyramide[p.etage][p.rang];
	}

	public void annulerDernierePiece() {// ok
		int taille = this.historiquePieces.size();
		this.historiquePieces.remove(taille - 1);
	}

	public boolean aPiecesPorteuses(int etage, int rang) {// renvoie true si la piece a la position p a deux pieces
															// porteuses
		return pyramide[etage - 1][rang] != null && pyramide[etage - 1][rang + 1] != null;
	}

	public boolean estPiecePorteuse(Position p) {// renvoie true si la piece a la position p est porteuse
		boolean caseSupGauche, caseSupDroite;
		if (p.rang == 0) {
			caseSupGauche = true; // si c'est la premiere case de la ligne en haut a gauche qui n'existe pas
		} else {
			caseSupGauche = (pyramide[p.etage + 1][p.rang - 1] == null); // on regarde si en haut a gauche est libre
		}

		if (p.rang == pyramide[p.etage].length - 1) {
			caseSupDroite = true; // si c'est la derniere case de la ligne en haut a droite qui n'existe pas
		} else {
			caseSupDroite = (pyramide[p.etage + 1][p.rang] == null); // on regarde si en haut a droite est libre
		}
		return (caseSupGauche && caseSupDroite);
	}

	public PiecePyramide retirer(Position p) {
		if (p.etage >= hauteur || p.etage < 0 || p.rang >= pyramide[p.etage].length || p.rang < 0
				|| pyramide[p.etage][p.rang] == null) {
			System.err.println("erreur impossible de retirer la piece.");
			return null;
		} else {
			// avec piece porteuse
			boolean caseSupGauche, caseSupDroite;
			if (p.rang == 0) {
				caseSupGauche = true; // si c'est la premiere case de la ligne en haut a gauche qui n'existe
										// pas
			} else {
				caseSupGauche = (pyramide[p.etage + 1][p.rang - 1] == null); // on regarde si en haut a gauche est
			} // libre
			if (p.rang == pyramide[p.etage].length - 1) {
				caseSupDroite = true; // si c'est la derniere case de la ligne en haut a droite qui n'existe
										// pas
			} else {
				caseSupDroite = (pyramide[p.etage + 1][p.rang] == null); // on regarde si en haut a droite est libre
			}
			// on regarde ne haut a gauche + en haut a droite
			if (caseSupGauche && caseSupDroite) { // on regarde si il ya des pieces porteuses
				Piece pie = this.pyramide[p.etage][p.rang];
				this.pyramide[p.etage][p.rang] = null;
				this.annulerDernierePiece();
				return new PiecePyramide(pie, p);

			} else {
				System.err.println("La piece est une piece porteuse et ne peut pas etre retiree.");
				return null;
			}
		}
	}
	
	public Piece retirePhase1(Position p) {
		if (p.etage >= hauteur || p.etage < 0 || p.rang >= pyramide[p.etage].length || p.rang < 0
				|| pyramide[p.etage][p.rang] == null) {
			System.err.println("erreur impossible de retirer la piece.");
			return null;
		}else {
			Piece pie = this.pyramide[p.etage][p.rang];
			this.pyramide[p.etage][p.rang]=null;
			return pie;
		}
	}
	
	public boolean empilerPhase1(PiecePyramide pp) {
		Piece piece = pp.getPiece();
		Position p = pp.getPos();
		if (p.etage >= hauteur || p.etage < 0 || p.rang >= pyramide[p.etage].length || p.rang < 0) {
			System.err.println("Erreur : la position (" + p.rang + "," + p.etage + ") est impossible.");
			return false;
		} else if (this.pyramide[p.etage][p.rang] != null) {
			System.err.println("la place est deja prise");
			return false;
		} else {
			// pas de piece porteuse
			this.pyramide[p.etage][p.rang] = piece;
			this.historiquePieces.add(pp);
			return true;
		}
	}
	
	public boolean ajoutPieceString(char s, int etage, int rang) {
		switch(s) {
		case 'B':
			this.pyramide[etage][rang]=new Piece(Couleurs.BLEU);
			break;
		case 'N':
			this.pyramide[etage][rang]=new Piece(Couleurs.NOIR);
			break;
		case 'R':
			this.pyramide[etage][rang]=new Piece(Couleurs.ROUGE);
			break;
		case 'V':
			this.pyramide[etage][rang]=new Piece(Couleurs.VERT);
			break;
		case 'J':
			this.pyramide[etage][rang]=new Piece(Couleurs.JAUNE);
			break;
		case 'W':
			this.pyramide[etage][rang]=new Piece(Couleurs.BLANC);
			break;
		case '#':
			this.pyramide[etage][rang]=new Piece(Couleurs.NATUREL);
			break;
		default:
			return false;
		}
		return true;
	}
	
	public boolean ajoutPieceStringCase(char c, int etage, int rang) {
		if(c=='.') {
			this.pyramide[etage][rang]=null;
			return true;
		}else {
			ajoutPieceString(c, etage, rang);
			return true;
		}
	}
	
	public boolean stringToPyramide(ArrayList<String> tab) {
		boolean a;
        String piece;
        for(int i=this.hauteur-1; i>=0; i--) {
            for(int j=0; j<this.largeur-i; j++) {
                piece=tab.get((this.hauteur-1) - i);
                a = ajoutPieceStringCase(piece.charAt(j), i, j);
                if(!a) {
                    return a;
                }
            }
        }
        return true;
	}

	public String toString() {
		String tableau = new String();
		String ligne = "";
		String debut = "";
		for (int i = hauteur - 1; i >= 0; i--) {
			switch (i) {
				case 8:
					debut = "        ";
					break;
				case 7:
					debut = "       ";
					break;
				case 6:
					debut = "      ";
					break;
				case 5:
					debut = "     ";
					break;
				case 4:
					debut = "    ";
					break;
				case 3:
					debut = "   ";
					break;
				case 2:
					debut = "  ";
					break;
				case 1:
					debut = " ";
					break;
				case 0:
					debut = "";
			}
			for (int j = 0; j < pyramide[i].length; j++) {
				if (pyramide[i][j] != null) {
					switch (pyramide[i][j].getColor()) {
						case BLEU:
							ligne += 'B';
							break;
						case NOIR:
							ligne += 'N';
							break;
						case ROUGE:
							ligne += 'R';
							break;
						case VERT:
							ligne += 'V';
							break;
						case JAUNE:
							ligne += 'J';
							break;
						case BLANC:
							ligne += 'W';
							break;
						case NATUREL:
							ligne += '#';
							break;
					}
				} else
					ligne += '.';
				ligne += ' ';
			}
			ligne += System.getProperty("line.separator");
			ligne = debut + ligne;
			tableau += ligne;
			ligne = "";
		}
		return tableau;
	}
	
	public String toStringSauvegarde() {
		String tableau = new String();
		String ligne="";
		for (int i = hauteur - 1; i >= 0; i--) {
			for (int j = 0; j < pyramide[i].length; j++) {
				if (pyramide[i][j] != null) {
					switch (pyramide[i][j].getColor()) {
						case BLEU:
							ligne += 'B';
							break;
						case NOIR:
							ligne += 'N';
							break;
						case ROUGE:
							ligne += 'R';
							break;
						case VERT:
							ligne += 'V';
							break;
						case JAUNE:
							ligne += 'J';
							break;
						case BLANC:
							ligne += 'W';
							break;
						case NATUREL:
							ligne += '#';
							break;
					}
				} else
					ligne += '.';
			}
			ligne += System.getProperty("line.separator");
			tableau += ligne;
			ligne = "";
		}
		return tableau;
	}

	public boolean empiler(PiecePyramide pp) {
		Piece piece = pp.getPiece();
		Position p = pp.getPos();
		if (p.etage >= hauteur || p.etage < 0 || p.rang >= pyramide[p.etage].length || p.rang < 0) {
			System.err.println("Erreur : la position (" + p.rang + "," + p.etage + ") est impossible.");
			return false;
		} else if (this.pyramide[p.etage][p.rang] != null) {
			System.err.println("la place est deja prise");
			return false;
		} else if (p.etage == 0) {
			// pas de piece porteuse
			this.pyramide[p.etage][p.rang] = piece;
			this.historiquePieces.add(pp);
			return true;
		} else {
			// avec piece porteuse
			int porteurDroitRang = p.rang;
			int porteurDroitEtage = p.etage - 1;
			Piece porteurDroit = pyramide[porteurDroitEtage][porteurDroitRang];
			int porteurGaucheRang = porteurDroitRang + 1;
			int porteurGaucheEtage = p.etage - 1;
			Piece porteurGauche = pyramide[porteurGaucheEtage][porteurGaucheRang];

			if (porteurDroit != null && porteurGauche != null) {
				if (conditionEmpiler(piece, porteurGauche, porteurDroit)) {
					this.pyramide[p.etage][p.rang] = piece;
					this.historiquePieces.add(pp);
					return true;
				} else {
					System.err.println("La piece n'est pas de la bonne couleur qu'une des 2 pieces porteuses.");
					return false;
				}
			} else {
				System.err.println("Impossible d'empiler une piece sur du vide. Il faut au moins 2 pieces porteuses.");
				return false;
			}
		}
	}

	public boolean conditionEmpiler(Piece piece, Piece porteurDroit, Piece porteurGauche) {
		return true;
	}
}
