package Modeles;

public class Piece {// piece
	private Couleurs color;

	public Piece(Couleurs color) {
		this.color = color;
	}

	public Couleurs getColor() {
		return this.color;
	}

	public String toString() {
		String res = "Piece de couleur ";
		res += color.toString().substring(14);
		return res;
	}
}
