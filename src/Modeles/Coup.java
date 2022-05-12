package Modeles;

public class Coup {
	private Piece piece;
	private Position posCampJoueur;
	private Position posBaseMontagne;

	// un coup est d�fini par une pi�ce, une position de d�part depuis la pyramide
	// du joueur et une position d'arriv�e dans la base de la montagne
	public Coup(Piece p, Position pos1, Position pos2) {
		this.piece = p;
		this.posCampJoueur = pos1;
		this.posBaseMontagne = pos2;
	}

	public Piece getPiece() {
		return this.piece;
	}

	public Position getPosJ() {
		return this.posCampJoueur;
	}

	public Position getPosBase() {
		return this.posBaseMontagne;
	}

	public String toString() {
		String tableau = new String();
		if (posBaseMontagne == null) {
			tableau += piece.toString() + "|" + posCampJoueur.toString() + "|" + " Vous pouvez passez votre tour.";
		} else {
			tableau += piece.toString() + "|" + posCampJoueur.toString() + "|" + posBaseMontagne.toString();
		}
		return tableau;
	}

}
