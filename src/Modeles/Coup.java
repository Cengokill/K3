package modeles;

public class Coup{
	private Piece piece;
	private Position posCampJoueur;
	private Position posBaseMontagne;
	//un coup est défini par une pièce, une position de départ depuis la pyramide du joueur et une position d'arrivée dans la base de la montagne
	public Coup(Piece p, Position pos1, Position pos2) {
		this.piece=p;
		this.posCampJoueur=pos1;
		this.posBaseMontagne=pos2;
	}
	
	public Piece getPiece() {
		return this.piece;
	}
	
	public Position getposJ() {
		return this.posCampJoueur;
	}
	
	public Position getPosBase() {
		return this.posBaseMontagne;
	}
	
	public String toString() {
		String tableau=new String();
		tableau+=piece.toString()+"|"+posCampJoueur.toString()+"|"+posBaseMontagne.toString();
		return tableau;
	}
		
}
