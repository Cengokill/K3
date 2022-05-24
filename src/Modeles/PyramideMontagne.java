package Modeles;

import java.util.ArrayList;

public class PyramideMontagne extends Pyramide {

	public PyramideMontagne(int largeur, int hauteur) {
		super(largeur, hauteur);
	}

	public ArrayList<PiecePyramide> piecesPosables() {// renvoie la liste de toutes les pieces que l'on peut placer sur la pyramide de la base
		ArrayList<PiecePyramide> arr = new ArrayList<PiecePyramide>();	
		for (int i = 1; i <= hauteur - 1; i++) {
			for (int j = 0; j < pyramide[i].length; j++) {
				if (pyramide[i][j] == null) {// si la case courante ne contient pas de piece
					Position pp = new Position(i, j);
					if(aPiecesPorteuses(i,j)){
						PiecePyramide porteurGauche=new PiecePyramide(new Piece(pyramide[i - 1][j].getColor()), new Position(i-1, j));
						PiecePyramide porteurDroit=new PiecePyramide(new Piece(pyramide[i - 1][j+1].getColor()), new Position(i-1, j+1));
						PiecePyramide pieceNaturelle=new PiecePyramide(new Piece(Couleurs.NATUREL), pp);
						Couleurs portGaucheColor=porteurGauche.getPiece().getColor();
						Couleurs portDroitColor=porteurDroit.getPiece().getColor();
						if(portGaucheColor!=Couleurs.NATUREL && portDroitColor!=Couleurs.NATUREL) {//si pas de NATUREL
							PiecePyramide pAjoutG=new PiecePyramide(new Piece(portGaucheColor), pp);
							arr.add(pieceNaturelle);
							arr.add(pAjoutG);//ajout de la piece de la couleur du porteur gauche avec la position (i,j)
							if(portDroitColor!=portGaucheColor) {//si les 2 couleurs sont differentes
								PiecePyramide pAjoutD=new PiecePyramide(new Piece(portDroitColor), pp);
								arr.add(pAjoutD);//ajout de la piece de la couleur du porteur droit avec la position (i,j)
							}
						}else {
							for(Couleurs c : Couleurs.values()) {//si NATUREL
								PiecePyramide pAjout=new PiecePyramide(new Piece(c), pp);
								arr.add(pAjout);//ajout des pieces de toutes les couleurs a la position (i,j)
							}
						}
					}
				}
			}
		}
		return arr;
	}

	public boolean estPorteursMemeCouleur(Position p) {
		int porteurDroitRang = p.rang;
		int porteurDroitEtage = p.etage - 1;
		Piece porteurDroit = pyramide[porteurDroitEtage][porteurDroitRang];
		int porteurGaucheRang = porteurDroitRang + 1;
		int porteurGaucheEtage = p.etage - 1;
		Piece porteurGauche = pyramide[porteurGaucheEtage][porteurGaucheRang];

		return porteurGauche.getColor() == porteurDroit.getColor();
	}

	public boolean conditionEmpiler(Piece piece, Piece porteurDroit, Piece porteurGauche) { //vérification que les pieces porteuse possède la meme couleur que la piece posée
		return (piece.getColor() == Couleurs.NATUREL || (porteurDroit.getColor() == piece.getColor()
				|| porteurGauche.getColor() == piece.getColor() || porteurDroit.getColor() == Couleurs.NATUREL
				|| porteurGauche.getColor() == Couleurs.NATUREL));
	}
	
}
