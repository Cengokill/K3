package Modeles;

public interface Pyramide {
	
	int LARGEUR_MAX = 1000;
	int HAUTEUR_MAX = 1000;
	
	public void empiler(Piece piece, Position p);
	
	public int getlargeur();
	
	public int getHauteur();
	
	public Piece getPiece(Position p);
	
}
