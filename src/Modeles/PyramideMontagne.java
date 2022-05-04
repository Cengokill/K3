package Modeles;

public class PyramideMontagne implements Pyramide {
	
	private Piece[][] pyramide;
	private int largeur;
	private int hauteur;

	public void empiler(Piece piece, Position p) {
		if(p.y >= hauteur || p.y < 0 || p.x >= pyramide[p.y].length || p.x < 0 ) {
			System.err.println("erreur position impossible : " + p.x + "," + p.y);
		}
		else if(p.y == 0) {
			// pas de piece porteuse
			pyramide[p.y][p.x] = piece;
		}
		else {
			// avec piece porteuse
			int porteurDroitx = p.x;
			int porteurDroity = p.y-1;
			Piece porteurDroit = pyramide[porteurDroity][porteurDroitx];
			int porteurGauchex = porteurDroitx +1;
			int porteurGauchey = p.y-1;
			Piece porteurGauche = pyramide[porteurGauchey][porteurGauchex];
			
			if( porteurDroit != null && porteurGauche !=null) {
				if(porteurDroit.getColor() == piece.getColor() || porteurGauche.getColor() == piece.getColor()) {
					pyramide[p.y][p.x] = piece;
				}else {
					System.err.println("la piece n'est pas de la bonne couleur");
				}
			}else {
				System.err.println("la piece n'as pas de piece porteuse");
			}
		}
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
}