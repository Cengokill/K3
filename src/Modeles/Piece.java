package Modeles;

import java.awt.Color;

public class Piece {// piece
	private Color color;

	public Piece(Color color) {
		this.color = color;
	}

	public Color getColor() {
		return this.color;
	}

	public String toString() {
		String res = "Piece de couleur ";
		res += color.toString().substring(14);
		return res;
	}

}
