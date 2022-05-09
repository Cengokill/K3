package Modeles;

import java.util.ArrayList;
import java.util.Scanner;

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

	public Piece piocherPiece(ArrayList<Piece> sac) {//choisis une pièce dans le sac
		Piece pChoisie, pCourante;
		ArrayList<Couleurs> arrCouleurs = new ArrayList<Couleurs>();
		ArrayList<Integer> arrPositions = new ArrayList<Integer>();
		System.out.println("Veuillez choisir le numéro de la pièce dans le sac :");
		pCourante=sac.get(0);
		arrCouleurs.add(pCourante.getColor());
		arrPositions.add(0);
		int i=1;
		while(!arrCouleurs.isEmpty()) {
			pCourante=sac.get(i);
			if(!arrCouleurs.contains(pCourante.getColor())) {
				System.out.print("["+(arrPositions.size())+"]"+pCourante.toString()+" ,");
				arrCouleurs.add(pCourante.getColor());
				arrPositions.add(i);
			}
			i++;
		}
		Scanner num = new Scanner(System.in);
	    int n = Integer.parseInt(num.nextLine());
	    pChoisie=sac.get(arrPositions.get(n));
	    return pChoisie;
	}

	public PiecePyramide jouer(PyramideMontagne p) {
		// TODO Auto-generated method stub
		return null;
	}

}