package Modeles;

import java.util.ArrayList;
import java.util.Scanner;

public class Joueur implements Acteur {
	private String nom;
	private PyramideJoueur campJ;
	private ArrayList<Piece> piecesVolees;// pieces que le joueur a volees a l'autre joueur
	private ArrayList<Piece> piecesPiochees;// pieces que le joueur a piochees et pas encore placees sur son camp

	public Joueur(String nom) {
		this.nom = nom;
		this.piecesVolees = new ArrayList<Piece>();
		this.piecesPiochees = new ArrayList<Piece>();
		campJ = new PyramideJoueur(6, 6);
	}

	public String getNom() {// renvoie le nom du joueur
		return this.nom;
	}

	public PyramideJoueur getCamp() {
		return this.campJ;
	}

	public void placerPieces() {
		//System.out.println(piecesPiochees.size());
		Position pos;
		for(int i=0; i<6; i++) {
			for(int j=0; j<6-i; j++) {
				pos=new Position(j,i);
				if(piecesPiochees.size()==0) {
					System.err.println("Plus de pièce à placer ! Votre camp de base est déjà construit !");
					return;
				}
				Piece p=piecesPiochees.get(0);
				PiecePyramide pp = new PiecePyramide(p, pos);
				campJ.empiler(pp);
				piecesPiochees.remove(p);
			}
		}
	}

	public ArrayList<Piece> getPiecesVolees() {
		return this.piecesVolees;
	}
	
	public ArrayList<Piece> getPiecesPiochees() {
		return this.piecesPiochees;
	}

	public void addPieceVolee(Piece p) {
		this.piecesVolees.add(p);
	}
	
	public void addPiecesPiochees(Piece p) {
		this.piecesPiochees.add(p);
	}
	
	public int getTaillePiecesPiochees() {
		return piecesPiochees.size();
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
		String c=", ";
		ArrayList<Couleurs> arrCouleurs = new ArrayList<Couleurs>();
		ArrayList<Integer> arrPositions = new ArrayList<Integer>();
		System.out.println("Veuillez choisir le numéro de la pièce dans le sac :");
		pCourante=sac.get(0);
		arrCouleurs.add(pCourante.getColor());
		arrPositions.add(0);
		System.out.print("["+0+"]"+pCourante.toString()+c);
		int  i=1;
		while(i<sac.size() && arrCouleurs.size()<7) {
			pCourante=sac.get(i);
			if(!arrCouleurs.contains(pCourante.getColor())) {
				if(arrCouleurs.size()==6) {
					c=" : ";
				}
				System.out.print("["+(arrPositions.size())+"]"+pCourante.toString()+c);
				arrCouleurs.add(pCourante.getColor());
				arrPositions.add(i);
			}
			i++;
		}

		//Scanner num = new Scanner(System.in);
	    //int n = Integer.parseInt(num.nextLine());
		int n=0;
	    //System.out.println("n = "+n);
	    pChoisie=sac.get(arrPositions.get(n));
	    //System.out.println("arrpos.get(n) : "+arrPositions.get(n)+sac.get(arrPositions.get(n)));
	    sac.remove(pChoisie);
	    //System.out.println(sac.size());
	    System.out.println();
	    return pChoisie;
	}
	
	

	public PiecePyramide jouer(PyramideMontagne p) {
		// TODO Auto-generated method stub
		return null;
	}

}