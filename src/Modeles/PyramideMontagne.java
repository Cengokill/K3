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
	
	public boolean stringToPyramide(ArrayList<String> tab) {
		String pieceSommet=tab.get(0).split(" ")[0];
		String piece44=tab.get(1).split(" ")[0];
		String piece43=tab.get(1).split(" ")[1];
		String piece42=tab.get(2).split(" ")[0];
		String piece41=tab.get(2).split(" ")[1];
		String piece40=tab.get(2).split(" ")[2];
		String piece39=tab.get(3).split(" ")[0];
		String piece38=tab.get(3).split(" ")[1];
		String piece37=tab.get(3).split(" ")[2];
		String piece36=tab.get(3).split(" ")[3];
		String piece35=tab.get(4).split(" ")[0];
		String piece34=tab.get(4).split(" ")[1];
		String piece33=tab.get(4).split(" ")[2];
		String piece32=tab.get(4).split(" ")[3];
		String piece31=tab.get(4).split(" ")[4];
		String piece30=tab.get(5).split(" ")[0];
		String piece29=tab.get(5).split(" ")[1];
		String piece28=tab.get(5).split(" ")[2];
		String piece27=tab.get(5).split(" ")[3];
		String piece26=tab.get(5).split(" ")[4];
		String piece25=tab.get(5).split(" ")[5];
		String piece24=tab.get(6).split(" ")[0];
		String piece23=tab.get(6).split(" ")[1];
		String piece22=tab.get(6).split(" ")[2];
		String piece21=tab.get(6).split(" ")[3];
		String piece20=tab.get(6).split(" ")[4];
		String piece19=tab.get(6).split(" ")[5];
		String piece18=tab.get(6).split(" ")[6];
		String piece17=tab.get(7).split(" ")[0];
		String piece16=tab.get(7).split(" ")[1];
		String piece15=tab.get(7).split(" ")[2];
		String piece14=tab.get(7).split(" ")[3];
		String piece13=tab.get(7).split(" ")[4];
		String piece12=tab.get(7).split(" ")[5];
		String piece11=tab.get(7).split(" ")[6];
		String piece10=tab.get(7).split(" ")[7];
		String piece9=tab.get(8).split(" ")[0];
		String piece8=tab.get(8).split(" ")[1];
		String piece7=tab.get(8).split(" ")[2];
		String piece6=tab.get(8).split(" ")[3];
		String piece5=tab.get(8).split(" ")[4];
		String piece4=tab.get(8).split(" ")[5];
		String piece3=tab.get(8).split(" ")[6];
		String piece2=tab.get(8).split(" ")[7];
		String piece1=tab.get(8).split(" ")[8];
		boolean a=ajoutPieceStringCase(pieceSommet, hauteur-1, 0);
		boolean b=ajoutPieceStringCase(piece44, hauteur-2, 0);
		boolean c=ajoutPieceStringCase(piece43, hauteur-2, 1);
		boolean d=ajoutPieceStringCase(piece42, hauteur-3, 0);
		boolean e=ajoutPieceStringCase(piece41, hauteur-3, 1);
		boolean f=ajoutPieceStringCase(piece40, hauteur-3, 2);
		boolean g=ajoutPieceStringCase(piece39, hauteur-4, 0);
		boolean h=ajoutPieceStringCase(piece38, hauteur-4, 1);
		boolean i=ajoutPieceStringCase(piece37, hauteur-4, 2);
		boolean j=ajoutPieceStringCase(piece36, hauteur-4, 3);
		boolean k=ajoutPieceStringCase(piece35, hauteur-5, 0);
		boolean l=ajoutPieceStringCase(piece34, hauteur-5, 1);
		boolean m=ajoutPieceStringCase(piece33, hauteur-5, 2);
		boolean n=ajoutPieceStringCase(piece32, hauteur-5, 3);
		boolean o=ajoutPieceStringCase(piece31, hauteur-5, 4);
		boolean p=ajoutPieceStringCase(piece30, hauteur-6, 0);
		boolean q=ajoutPieceStringCase(piece29, hauteur-6, 1);
		boolean r=ajoutPieceStringCase(piece28, hauteur-6, 2);
		boolean s=ajoutPieceStringCase(piece27, hauteur-6, 3);
		boolean t=ajoutPieceStringCase(piece26, hauteur-6, 4);
		boolean u=ajoutPieceStringCase(piece25, hauteur-6, 5);
		boolean v=ajoutPieceStringCase(piece24, hauteur-7, 0);
		boolean w=ajoutPieceStringCase(piece23, hauteur-7, 1);
		boolean x=ajoutPieceStringCase(piece22, hauteur-7, 2);
		boolean y=ajoutPieceStringCase(piece21, hauteur-7, 3);
		boolean z=ajoutPieceStringCase(piece20, hauteur-7, 4);
		boolean aa=ajoutPieceStringCase(piece19, hauteur-7, 5);
		boolean ab=ajoutPieceStringCase(piece18, hauteur-7, 6);
		boolean ac=ajoutPieceStringCase(piece17, 1, 0);
		boolean ad=ajoutPieceStringCase(piece16, 1, 1);
		boolean ae=ajoutPieceStringCase(piece15, 1, 2);
		boolean af=ajoutPieceStringCase(piece14, 1, 3);
		boolean ag=ajoutPieceStringCase(piece13, 1, 4);
		boolean ah=ajoutPieceStringCase(piece12, 1, 5);
		boolean ai=ajoutPieceStringCase(piece11, 1, 6);
		boolean aj=ajoutPieceStringCase(piece10, 1, 7);
		boolean ak=ajoutPieceStringCase(piece9, 0, 0);
		boolean al=ajoutPieceStringCase(piece8, 0, 1);
		boolean am=ajoutPieceStringCase(piece7, 0, 2);
		boolean an=ajoutPieceStringCase(piece6, 0, 3);
		boolean ao=ajoutPieceStringCase(piece5, 0, 4);
		boolean ap=ajoutPieceStringCase(piece4, 0, 5);
		boolean aq=ajoutPieceStringCase(piece3, 0, 6);
		boolean ar=ajoutPieceStringCase(piece2, 0, 7);
		boolean as=ajoutPieceStringCase(piece1, 0, 8);
		return a&&b&&c&&d&&e&&f&&g&&h&&i&&j&&k&&l&&m&&n&&o&&p&&q&&r&&s&&t&&u&&v&&w&&x&&y&&z&&aa&&ab&&ac&&ad&&ae&&af&&ag&&ah&&ai&&aj&&ak&&al&&am&&an&&ao&&ap&&aq&&ar&&as;
	}
}
