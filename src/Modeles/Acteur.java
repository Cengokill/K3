package Modeles;

import java.util.ArrayList;

public interface Acteur {

	// phase GAMEPLAY
	public Coup jouer(ArrayList<Coup> arr); // pyramide au milieu

	public void placerPieces();

	public String getNom();

	public PyramideJoueur getCamp();

	public ArrayList<Piece> getPiecesVolees();

	public void addPiecePiochee(Piece p);

	public String toStringPiecesVolees();

	public ArrayList<Piece> getPiecesPiochees();

	public void addPieceVolee(Piece p);

	public int getTaillePiecesPiochees();

	public Piece piocherPiece(ArrayList<Piece> sac);

	public void afficherCoupsJouables(ArrayList<Coup> arr);

	public void setCamp(PyramideJoueur campJ);
}
