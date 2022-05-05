package Modeles;

public class PiecePyramide {
	private Piece piece;
	private Position pos;

	public PiecePyramide(Piece piece, Position position) {
		this.piece = piece;
		this.pos = position;
	}

	public Piece getPiece() {
		return piece;
	}

	public Position getPos() {
		return pos;
	}

	public String toString() {
		String res = "";
		res += piece.toString() + " et de position " + pos.toString();
		return res;
	}

}
