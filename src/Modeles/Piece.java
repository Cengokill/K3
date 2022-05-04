package Modeles;
import java.awt.Color;

public class Piece {//piece
	private Color color;
	private Position pos;
	
	public Piece(Color color, Position pos) {
		this.color = color;
		this.pos=pos;
	}
	
	public Color getColor() {
		return this.color;
	}
	
	public void setPos(Position p) {
		this.pos=p;
	}
	
	public Position getPos() {
		return this.pos;
	}	
	
}
