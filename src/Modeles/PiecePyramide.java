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
		if (pos == null) {
			res += piece.toString() + " | PAS DE POSITION";
		} else {
			res += piece.toString() + " | " + pos.toString();
		}
		return res;
	}

	public boolean egal(PiecePyramide p) {
		Couleurs c2 = p.getPiece().getColor();
		Position p2 = p.getPos();
		Couleurs c1 = this.getPiece().getColor();
		Position p1 = this.getPos();
		if(p1==null) {
			if(p2!=null) {
				return false;
			}else {
				return c1 == c2;
			}
		}else {
			if(p2!=null) {
				return c1 == c2 && p1.egal(p2);
			}else {
				return false;
			}
		}
	}

}
