package Modeles;

import java.util.ArrayList;

public class PyramideMontagne extends Pyramide {
	
	protected ArrayList<PiecePyramide> historiquePieces;

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
			this.historiquePieces=new ArrayList<PiecePyramide>();
		}
	}
	
	public void AnnulerDernierePiece() {
		int taille=this.historiquePieces.size();
		this.historiquePieces.remove(taille-1);
	}
	
	public boolean estPleine() {
		if(pyramide[hauteur-1][0]!=null) {
			System.out.println("La base de la montagne est PLEINE !");
		}
		return pyramide[hauteur-1][0]!=null;
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
		/*
		System.out.println("Pieces posables sur le camp de la montagne :");
		for(int k=0; k<arr.size(); k++) {
			System.out.println(arr.get(k).toString());
		}
		*/
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

	public void empiler(PiecePyramide pp) {
		Piece piece = pp.getPiece();
		Position p = pp.getPos();
		if (p.etage >= hauteur || p.etage < 0 || p.rang >= pyramide[p.etage].length || p.rang < 0) {
			System.err.println("Erreur : la position (" + p.rang + "," + p.etage + ") est impossible.");
			return;
		} else if (p.etage == 0) {
			// pas de piece porteuse
			this.pyramide[p.etage][p.rang] = piece;
			this.historiquePieces.add(pp);
		} else {
			// avec piece porteuse
			int porteurDroitRang = p.rang;
			int porteurDroitEtage = p.etage - 1;
			Piece porteurDroit = pyramide[porteurDroitEtage][porteurDroitRang];
			int porteurGaucheRang = porteurDroitRang + 1;
			int porteurGaucheEtage = p.etage - 1;
			Piece porteurGauche = pyramide[porteurGaucheEtage][porteurGaucheRang];

			if (porteurDroit != null && porteurGauche != null) {
				if (piece.getColor() == Couleurs.NATUREL || (porteurDroit.getColor() == piece.getColor()
						|| porteurGauche.getColor() == piece.getColor() || porteurDroit.getColor() == Couleurs.NATUREL
						|| porteurGauche.getColor() == Couleurs.NATUREL)) {
					this.pyramide[p.etage][p.rang] = piece;
					this.historiquePieces.add(pp);
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
		this.pyramide[pos.etage][pos.rang] = p;
	}
}
