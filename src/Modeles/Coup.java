package Modeles;

public class Coup {
	private Piece piece;
	private Position posCampJoueur;
	private Position posBaseMontagne;

	// un coup est defini par une piece, une position de depart depuis la montagne
	// du joueur et une position d'arrivee dans la base de la montagne
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
		if (this.posBaseMontagne == null) {
			tableau += this.piece.toString() + "|" + this.posCampJoueur.toString() + "|" + " Vous pouvez passez votre tour.";
		} else {
			if (this.posCampJoueur == null) {
				tableau += this.piece.toString() + "| Piece volee |" + this.posBaseMontagne.toString();
			}else {
				tableau += this.piece.toString() + "|" + this.posCampJoueur.toString() + "|" + this.posBaseMontagne.toString();
			}
		}
		return tableau;
	}

}
