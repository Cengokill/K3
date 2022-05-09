package Modeles;

import java.util.ArrayList;

public class Joueur implements Acteur {
	private String nom;
	private PyramideJoueur campJ;
	private ArrayList<Piece> piecesVolees;// pieces que le joueur a volees a l'autre joueur

	public Joueur(String nom) {
		this.nom = nom;
		this.piecesVolees = new ArrayList<Piece>();
		campJ = new PyramideJoueur(6, 6);
	}

	public String getNom() {// renvoie le nom du joueur
		return this.nom;
	}

	public PyramideJoueur getCamp() {
		return this.campJ;
	}

	public void placerPiece(Piece p) {
	}

	public ArrayList<Piece> getPiecesVolees() {
		return this.piecesVolees;
	}

	public void addPieceVolee(Piece p) {
		this.piecesVolees.add(p);
	}

	public String toStringPiecesVolees() {
		String tableau = new String();
		for (int i = 0; i < piecesVolees.size(); i++) {
			tableau += piecesVolees.get(i).toString();
		}
		return tableau;
	}

	public void construire() {

	}

	public PiecePyramide jouer(PyramideMontagne p) {
		// TODO Auto-generated method stub
		return null;
	}
}