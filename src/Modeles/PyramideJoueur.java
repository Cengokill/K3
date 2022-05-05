package Modeles;

import java.util.LinkedList;

public class PyramideJoueur implements Pyramide{
	
	
	/*y î | x-> 
	3+
	2+     +
	1+++++++
	0++++++++
	 */
	private Piece[][] pyramide;
	private int largeur;
	private int hauteur;
	public int nbPieces;// variable test
	
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
	
	public boolean retirer(PiecePyramide pp) {
		Position p=pp.getPos();
		if(p.y >= hauteur || p.y < 0 || p.x >= pyramide[p.y].length || p.x < 0  || pyramide[p.y][p.x]==null) {
			System.err.println("erreur impossible de retirer la piece.");
			return false;
		}
		else {
			// avec piece porteuse
			boolean caseSupGauche, caseSupDroite;
			if (p.x == 0) {
				caseSupGauche = true; // si c'est la premiere case de la ligne en haut a gauche qui n'existe
										// pas
			} else {
				caseSupGauche = (pyramide[p.y + 1][p.x - 1] == null); // on regarde si en haut a gauche est
			}														// libre
			if (p.x == pyramide[p.y].length - 1) {
				caseSupDroite = true; // si c'est la derniere case de la ligne en haut a droite qui n'existe
										// pas
			} else {
				caseSupDroite = (pyramide[p.y + 1][p.x] == null); // on regarde si en haut a droite est libre
			}
			// on regarde ne haut a gauche + en haut a droite
			if (caseSupGauche && caseSupDroite) { // on regarde si il ya des pieces porteuses
				pyramide[p.y][p.x] = null;
				this.nbPieces--;
				System.out.println("Pièce retirée");
				return true;
			}else {
				System.err.println("La piece n'a pas de piece porteuse.");
				return false;
			}
		}
	}
	
	public void empiler(PiecePyramide pp) {
		Piece piece=pp.getPiece();
		Position p=pp.getPos();
		if(p.y >= hauteur || p.y < 0 || p.x >= pyramide[p.y].length || p.x < 0 ) {
			System.err.println("Erreur position impossible : " + p.x + "," + p.y);
		}
		else if(p.y == 0) {
			// pas de piece porteuse
			pyramide[p.y][p.x] = piece;
			this.nbPieces++;
		}
		else {
			// avec piece porteuse
			int porteurDroitx = p.x;
			int porteurDroity = p.y-1;
			int porteurGauchex = porteurDroitx +1;
			int porteurGauchey = p.y-1;
			if(pyramide[porteurDroity][porteurDroitx] != null && pyramide[porteurGauchey][porteurGauchex] !=null) {
				pyramide[p.y][p.x] = piece;
				this.nbPieces++;
			}else {
				System.err.println("La piece n'a pas de piece porteuse.");
			}
		}
	}
	
	public LinkedList<PiecePyramide> piecesJouables() {//renvoie les pieces que le joueur peut prendre à partir de sa pyramide
		LinkedList<PiecePyramide> arr = new LinkedList<PiecePyramide>();
		LinkedList<Position> piecesVerif = new LinkedList<Position>();
		piecesVerif.add(new Position(hauteur-1, 0));
		while(!piecesVerif.isEmpty()) {
			int x=piecesVerif.get(0).x;
			int y=piecesVerif.get(0).y;
			if(pyramide[y][x]==null) {
				piecesVerif.add(new Position(x, y-1));
				piecesVerif.add(new Position(x+1, y-1));
			}else {//case contenant une pièce
				arr.add(new PiecePyramide(pyramide[y][x], piecesVerif.get(0)));
				if(x == pyramide[y].length-1) {
					arr.add(new PiecePyramide(pyramide[y][x], piecesVerif.get(0)));
				}else if(pyramide[y+1][x]==null){
					arr.add(new PiecePyramide(pyramide[y][x], piecesVerif.get(0)));
				}
			}
			piecesVerif.remove(0);
		}
		return arr;

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
	
	public void afficher() {
		for(int i = hauteur-1;i >= 0; i--){
			for( int j = 0; j < pyramide[i].length ; j++) {
				if(pyramide[i][j]!=null) {
					switch(pyramide[i][j].getColor()) {
						case BLEU:
							System.out.print('B');
							break;
						case NOIR:
							System.out.print('N');
							break;
						case ROUGE:
							System.out.print('R');
							break;
						case VERT:
							System.out.print('V');
							break;
						case JAUNE:
							System.out.print('J');
							break;
						case BLANC:
							System.out.print('W');
							break;
						case NATUREL:
							System.out.print('#');
							break;
						default:
							System.out.print('.');
					}
				}else System.out.print('.');
			}
			System.out.println();
		}
	}
	
}
