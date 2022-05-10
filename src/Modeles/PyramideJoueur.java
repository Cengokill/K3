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
		if(p.y >= hauteur || p.y < 0 || p.x >= pyramide[p.y].length || p.x < 0 ) {
			System.err.println("Erreur position impossible : " + p.x + "," + p.y);
		}
		else if(p.y == 0) {
			// pas de piece porteuse
			pyramide[p.y][p.x] = piece;
		}
		else {
			// avec piece porteuse
			int porteurDroitx = p.x;
			int porteurDroity = p.y-1;
			int porteurGauchex = porteurDroitx +1;
			int porteurGauchey = p.y-1;
			if(pyramide[porteurDroity][porteurDroitx] != null && pyramide[porteurGauchey][porteurGauchex] !=null) {
				pyramide[p.y][p.x] = piece;
			}else {
				System.err.println("La piece n'a pas de piece porteuse.");
			}
		}
	}
	
	public ArrayList<PiecePyramide> piecesJouables() {//renvoie les pieces que le joueur peut prendre a partir de sa pyramide
		ArrayList<PiecePyramide> arr = new ArrayList<PiecePyramide>();
		ArrayList<Position> piecesVerif = new ArrayList<Position>();
		piecesVerif.add(new Position(hauteur-1, 0));
		while(!piecesVerif.isEmpty()) {
			int x=piecesVerif.get(0).x;
			int y=piecesVerif.get(0).y;
			if(pyramide[y][x]==null) {
				piecesVerif.add(new Position(x, y-1));
				piecesVerif.add(new Position(x+1, y-1));
			}else {//case contenant une pi�ce
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


	
}
