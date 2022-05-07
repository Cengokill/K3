package modeles;

public class Piece {// piece
	private Couleurs color;

	public Piece(Couleurs color) {
		this.color = color;
	}

	public Couleurs getColor() {
		return this.color;
	}

	public String toString() {
		return "["+color+"]";
	}
}
