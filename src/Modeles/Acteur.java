package Modeles;

import java.util.ArrayList;

public interface Acteur {

	private String nom;
	private PyramideJoueur campJ;
	private ArrayList<Piece> piecesVolees;// pieces que le joueur a volees a l'autre joueur
	private ArrayList<Piece> piecesPiochees;// pieces que le joueur a piochees et pas encore placees sur son camp
	
	public String getNom() {// renvoie le nom du joueur
		return this.nom;
	}

	public PyramideJoueur getCamp() {
		return this.campJ;
	}
	
	//piece Volees
	
	public ArrayList<Piece> getPiecesVolees() {
		return this.piecesVolees;
	}
	
	public void addPiecesPiochees(Piece p) {
		this.piecesPiochees.add(p);
	}
	
	public String toStringPiecesVolees() {
		String tableau = new String();
		for (int i = 0; i < piecesVolees.size(); i++) {
			tableau += piecesVolees.get(i).toString();
		}
		return tableau;
	}
	
	//piece Piochees
	
	public ArrayList<Piece> getPiecesPiochees() {
		return this.piecesPiochees;
	}

	public void addPieceVolee(Piece p) {
		this.piecesVolees.add(p);
	}
	
	public int getTaillePiecesPiochees() {
		return piecesPiochees.size();
	}
	
	public Piece piocherPiece(ArrayList<Piece> sac) {//choisis une pièce dans le sac
		Piece pChoisie;
		boolean ColorPresent;
		if(0>=sac.size()) {
			System.err.println("erreur sac vide");
		else if(piecesPiochees.size()<4{ //on n'as pas encore les 4 couleurs différentes
			ColorPresent = false;
			int j = 0;
			while( j<sac.size() && !ColorPresent) {
				pChoisie = sac.get(j);
				ColorPresent = false;
				int i = 0;
				while(i < piecesPiochees.size() && !ColorPresent) {
					if(piecesPiochees.get(i).getColor == pChoisie.getColor()) {
						ColorPresent = true;
					}
					i++;
				}
				j++;
			}
		}else {
			pChoisie = sac.get(0);
		}
	    sac.remove(pChoisie);
	    return pChoisie;
	}

	// phase GAMEPLAY
	public PiecePyramide jouer(PyramideMontagne p); // pyramide au milieu


	public void placerPieces();

}
