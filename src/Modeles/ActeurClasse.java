package Modeles;

import java.util.ArrayList;
import java.util.Collections;

public class ActeurClasse {

	protected String nom;
	protected PyramideJoueur campJ;
	protected ArrayList<Piece> piecesVolees;// pieces que le joueur a volees a l'autre joueur
	protected ArrayList<Piece> piecesPiochees;// pieces que le joueur a piochees et pas encore placees sur son camp
	protected ArrayList<Coup> historiqueCoups;
	protected int nb_BlancsJoues;//pieces blanches jouees
	protected int nb_Mauvais_Coups_Joues;//mauvais coups joues (une piece sur 2 pieces de meme couleur)
	protected int nb_Vols;

	public ActeurClasse(String nom) {
		this.nom = nom;
		this.piecesVolees = new ArrayList<Piece>();
		this.piecesPiochees = new ArrayList<Piece>();
		this.historiqueCoups=new ArrayList<Coup>();
		this.nb_BlancsJoues=0;
		this.nb_Mauvais_Coups_Joues=0;
		campJ = new PyramideJoueur(6, 6);
	}
	
	public void addCoupHist(Coup c) {
		this.historiqueCoups.add(c);
	}
	
	public void melangePiecesPiochees() {
		Collections.shuffle(this.piecesPiochees);
	}
	
	public ArrayList<Coup> getHistCoups() {
		return this.historiqueCoups;
	}
	
	public int getBlancsJoues() {
		return this.nb_BlancsJoues;
	}
	
	public int getMauvaisCoupsJoues() {
		return this.nb_Mauvais_Coups_Joues;
	}
	
	public void retireCoupHist(Coup c) {
		int taille= this.historiqueCoups.size();
		if(c.getPosBase()==null) {
			retireBlancJoue();
		}
		this.historiqueCoups.remove(taille-1);
	}
	
	public void addBlancJoue() {
		this.nb_BlancsJoues++;
	}
	
	public void retireBlancJoue() {
		this.nb_BlancsJoues--;
	}
	
	public void addMauvaisCoup() {
		this.nb_Mauvais_Coups_Joues++;
	}
	
	public void retireMauvaisCoup() {
		this.nb_Mauvais_Coups_Joues--;
	}
	
	public int getNbVols() {
		return this.nb_Vols;
	}
	
	public void addVol() {
		this.nb_Vols++;
	}
	
	public void retireVol() {
		this.nb_Vols--;
	}

	public String getNom() {// renvoie le nom du joueur
		return this.nom;
	}

	public PyramideJoueur getCamp() {
		return this.campJ;
	}

	public ArrayList<PiecePyramide> getPiecesJouables() {
		ArrayList<PiecePyramide> pJouables = this.campJ.piecesJouables();
		PiecePyramide pieceVoleeCourante;
		for (Piece p : this.piecesVolees) {
			pieceVoleeCourante = new PiecePyramide(p, null);
			pJouables.add(pieceVoleeCourante);
		}
		return pJouables;
	}
	
	public Piece piocherPiece(ArrayList<Piece> sac) {// choisis une piece dans le sac
		Piece pChoisie = null;
		if (sac.isEmpty()) {
			System.err.println("erreur sac vide");
		} else {
			pChoisie = sac.get(0);
		}
		sac.remove(pChoisie);
		return pChoisie;
	}

	public ArrayList<Piece> getPiecesVolees() {// piece Volees
		return this.piecesVolees;
	}

	public void addPiecePiochee(Piece p) {
		this.piecesPiochees.add(p);
	}

	public String toStringPiecesVolees() {
		String tableau = new String();
		for (int i = 0; i < piecesVolees.size(); i++) {
			tableau += piecesVolees.get(i).toString();
		}
		return tableau;
	}

	public void addPieceVolee(Piece p) {
		this.piecesVolees.add(p);
	}
	
	public Piece retirerPieceVolee(Piece p) {
		this.piecesVolees.remove(p);
		return p;
	}
	
	public ArrayList<Piece> getPiecesPiochees() {// piece Piochees
		return this.piecesPiochees;
	}

	public int getTaillePiecesPiochees() {
		return piecesPiochees.size();
	}

	public void afficherCoupsJouables(ArrayList<Coup> arr) {
		int taille = arr.size();
		for (int i = 0; i < taille; i++) {
			System.out.println("[" + i + "] : " + arr.get(i).toString());
		}
	}
}
