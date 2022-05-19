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
		String pos_Montagne;
		String pos_Camp_Joueur;
		if (this.posBaseMontagne == null) {
			pos_Montagne="PASSEZ VOTRE TOUR";
		}else {
			pos_Montagne=this.posBaseMontagne.toString();
		}
		if(this.posCampJoueur == null) {
			pos_Camp_Joueur="PIECE VOLEE";
		}else {
			pos_Camp_Joueur=this.posCampJoueur.toString();
		}
		tableau += this.piece.toString() + "|" + pos_Camp_Joueur + "|" + pos_Montagne;
		return tableau;
	}
	
	public boolean egal(Coup coup2) {
		return coup2.getPiece().equals(this.piece) && coup2.getPosJ().egal(this.posCampJoueur) && coup2.getPosBase().egal(this.posBaseMontagne);
	}

}
