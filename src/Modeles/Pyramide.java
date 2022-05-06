package Modeles;

public class Pyramide {
	
	protected int largeur;
	protected int hauteur;
	protected Piece[][] pyramide;
	public int LARGEUR_MAX = 50;
	public int HAUTEUR_MAX = 50;
	
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
				System.out.println("Pièce retirée");
				return true;
			}else {
				System.err.println("La piece est une piece porteuse et ne peut pas etre retiree.");
				return false;
			}
		}
	}
	
	public String toString() {
		String tableau = new String();
		for(int i = hauteur-1;i >= 0; i--){
			for( int j = 0; j < pyramide[i].length ; j++) {
				if(pyramide[i][j]!=null) {
					switch(pyramide[i][j].getColor()) {
						case BLEU:
							tableau+='B';
							break;
						case NOIR:
							tableau+='N';
							break;
						case ROUGE:
							tableau+='R';
							break;
						case VERT:
							tableau+='V';
							break;
						case JAUNE:
							tableau+='J';
							break;
						case BLANC:
							tableau+='W';
							break;
						case NATUREL:
							tableau+='#';
							break;
						default:
							tableau+='.';
					}
				}else tableau+='.';
			}
			tableau+=System.getProperty("line.separator");
		}
		return tableau;
	}
	
}
