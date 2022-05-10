package Modeles;

import java.util.ArrayList;

public interface Acteur {

	// phase construction
	public Piece piocherPiece(ArrayList<Piece> sac);

	// phase 2
	public PiecePyramide jouer(PyramideMontagne p); // pyramide au milieu

	public String getNom(); // renvoie le nom du joueur

	public PyramideJoueur getCamp();

	public void addPiecesPiochees(Piece p);

	public int getTaillePiecesPiochees();

	public ArrayList<Piece> getPiecesPiochees();

	public void placerPieces();

}
