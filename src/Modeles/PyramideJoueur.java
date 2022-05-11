package Modeles;

import java.util.ArrayList;

public class PyramideJoueur extends Pyramide{
	/*y � | x-> 
	3+
	2+     +
	1+++++++
	0++++++++
	 */
	public PyramideJoueur(int largeur, int hauteur) {
		if(largeur <= 0 || hauteur <= 0 || hauteur >= LARGEUR_MAX || largeur >= HAUTEUR_MAX) {
			System.err.println("erreur taille impossible : " + largeur + "," + hauteur);
		}
		else {
			this.largeur = largeur;
			this.hauteur = hauteur;
			int l=largeur;
			
			Piece[][] etage = new Piece[hauteur][];
			for(int i = 0; i<hauteur; i++) {
				Piece[] ligne = new Piece[l];
				etage[i] = ligne;
				l = l-1;
			}
			this.pyramide = etage;
		}
	}
		
	public void empiler(PiecePyramide pp) {
		Piece piece=pp.getPiece();
		Position p=pp.getPos();
		if(p.etage >= hauteur || p.etage < 0 || p.rang >= pyramide[p.etage].length || p.rang < 0 ) {
			System.err.println("Erreur position impossible : " + p.rang + "," + p.etage);
		}
		else if(p.etage == 0) {
			// pas de piece porteuse
			pyramide[p.etage][p.rang] = piece;
		}
		else {
			// avec piece porteuse
			int porteurDroitRang = p.rang;
			int porteurDroitEtage = p.etage-1;
			int porteurGaucheRang = porteurDroitRang +1;
			int porteurGaucheEtage = p.etage-1;
			if(pyramide[porteurDroitEtage][porteurDroitRang] != null && pyramide[porteurGaucheEtage][porteurGaucheRang] !=null) {
				pyramide[p.etage][p.rang] = piece;
			}else {
				System.err.println("La piece n'a pas de piece porteuse.");
			}
		}
	}
	
	public ArrayList<PiecePyramide> piecesJouables() {//renvoie les pieces que le joueur peut prendre a partir de sa pyramide
		ArrayList<PiecePyramide> arr = new ArrayList<PiecePyramide>();
		ArrayList<Position> piecesVerif = new ArrayList<Position>();
		boolean caseSupGauche, caseSupDroite;
		piecesVerif.add(new Position(hauteur-1, 0));
		while(!piecesVerif.isEmpty()) {
			int x=piecesVerif.get(0).etage;
			int y=piecesVerif.get(0).rang;
			if(pyramide[y][x]==null) {
				piecesVerif.add(new Position(x-1,y));
				piecesVerif.add(new Position(x-1, y+1));
			}else {//case contenant une piece
				if (x == 0 || y==0) {
                    caseSupGauche = true; // si c'est la premiere case de la ligne en haut a gauche qui n'existe pas
                } else {
                    caseSupGauche = (pyramide[x + 1][y - 1] == null); // on regarde si en haut a gauche est libre
                }
                if (y == pyramide[x].length - 1) {
                    caseSupDroite = true; // si c'est la derniere case de la ligne en haut a droite qui n'existe pas
                } else {
                    caseSupDroite = (pyramide[x + 1][y] == null); // on regarde si en haut a droite est libre
                }
                if(caseSupGauche && caseSupDroite) {//
                	arr.add(new PiecePyramide(pyramide[x][y], piecesVerif.get(0)));
                }
			}
			piecesVerif.remove(0);
		}
		return arr;
	}

}
