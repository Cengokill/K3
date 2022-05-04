package Modeles;

public interface Pyramide {
	
	int LARGEUR_MAX = 100;
	int HAUTEUR_MAX = 100;
	
	public void empiler(Piece piece, Position p);
	
	public int getlargeur();
	
	public int getHauteur();
	
	public Piece getPiece(Position p);
	
}
