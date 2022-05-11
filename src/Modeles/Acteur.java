package Modeles;

import java.util.ArrayList;

public interface Acteur {

	// A PRORAMMER / IA
	public Coup jouer(ArrayList<Coup> arr); // pyramide au milieu
	
	public void placerPieces();// deja implemente
	
	public PiecePyramide choixVol(ArrayList<PiecePyramide> arr);
	
	// COMMUN

	public String getNom();// deja implemente

	public PyramideJoueur getCamp();// deja implemente
	
	public ArrayList<PiecePyramide> getPiecesJouables();// deja implemente

	public ArrayList<Piece> getPiecesVolees();// deja implemente
	
	public void addPiecePiochee(Piece p);// deja implemente

	public String toStringPiecesVolees();// deja implemente

	public ArrayList<Piece> getPiecesPiochees();// deja implemente

	public void addPieceVolee(Piece p);// deja implemente

	public int getTaillePiecesPiochees();// deja implemente

	public Piece piocherPiece(ArrayList<Piece> sac);

	public void afficherCoupsJouables(ArrayList<Coup> arr);// deja implemente

	public void setCamp(PyramideJoueur campJ);// deja implemente	
}
