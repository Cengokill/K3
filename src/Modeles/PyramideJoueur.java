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
		for(int i=0; i<arr.size(); i++) {
			pCour=arr.get(i);
			if(pp.egal(pCour)) {
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
					if(!contiens(arr,newPieceJouable)) {
						arr.add(newPieceJouable);
					}
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
	
	public boolean stringToPyramide(ArrayList<String> tab) {
		String pieceSommet=tab.get(0).split(" ")[0];
		String piece20=tab.get(1).split(" ")[0];
		String piece19=tab.get(1).split(" ")[1];
		String piece18=tab.get(2).split(" ")[0];
		String piece17=tab.get(2).split(" ")[1];
		String piece16=tab.get(2).split(" ")[2];
		String piece15=tab.get(3).split(" ")[0];
		String piece14=tab.get(3).split(" ")[1];
		String piece13=tab.get(3).split(" ")[2];
		String piece12=tab.get(3).split(" ")[3];
		String piece11=tab.get(4).split(" ")[0];
		String piece10=tab.get(4).split(" ")[1];
		String piece9=tab.get(4).split(" ")[2];
		String piece8=tab.get(4).split(" ")[3];
		String piece7=tab.get(4).split(" ")[4];
		String piece6=tab.get(5).split(" ")[0];
		String piece5=tab.get(5).split(" ")[1];
		String piece4=tab.get(5).split(" ")[2];
		String piece3=tab.get(5).split(" ")[3];
		String piece2=tab.get(5).split(" ")[4];
		String piece1=tab.get(5).split(" ")[5];
		boolean a=ajoutPieceStringCase(pieceSommet, hauteur-1, 0);
		boolean b=ajoutPieceStringCase(piece20, hauteur-2, 0);
		boolean c=ajoutPieceStringCase(piece19, hauteur-2, 1);
		boolean d=ajoutPieceStringCase(piece18, hauteur-3, 0);
		boolean e=ajoutPieceStringCase(piece17, hauteur-3, 1);
		boolean f=ajoutPieceStringCase(piece16, hauteur-3, 2);
		boolean g=ajoutPieceStringCase(piece15, hauteur-4, 0);
		boolean h=ajoutPieceStringCase(piece14, hauteur-4, 1);
		boolean i=ajoutPieceStringCase(piece13, hauteur-4, 2);
		boolean j=ajoutPieceStringCase(piece12, hauteur-4, 3);
		boolean k=ajoutPieceStringCase(piece11, hauteur-5, 0);
		boolean l=ajoutPieceStringCase(piece10, hauteur-5, 1);
		boolean m=ajoutPieceStringCase(piece9, hauteur-5, 2);
		boolean n=ajoutPieceStringCase(piece8, hauteur-5, 3);
		boolean o=ajoutPieceStringCase(piece7, hauteur-5, 4);
		boolean p=ajoutPieceStringCase(piece6, 0, 0);
		boolean q=ajoutPieceStringCase(piece5, 0, 1);
		boolean r=ajoutPieceStringCase(piece4, 0, 2);
		boolean s=ajoutPieceStringCase(piece3, 0, 3);
		boolean t=ajoutPieceStringCase(piece2, 0, 4);
		boolean u=ajoutPieceStringCase(piece1, 0, 5);
		return a&&b&&c&&d&&e&&f&&g&&h&&i&&j&&k&&l&&m&&n&&o&&p&&q&&r&&s&&t&&u;
	}

}
