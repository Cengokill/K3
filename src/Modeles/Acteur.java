package Modeles;

import java.util.ArrayList;
import java.util.Collections;

public class Acteur {
	
	//variable STATS
	protected String nom;
	protected int nb_BlancsJoues;//pieces blanches jouees
	protected int nb_Mauvais_Coups_Joues;//mauvais coups joues (une piece sur 2 pieces de meme couleur)
	protected int nb_Vols;
	
	//variable GAMEPLAY
	protected PyramideJoueur campJ;
	protected ArrayList<Piece> piecesVolees;// pieces que le joueur a volees a l'autre joueur
	protected ArrayList<Piece> piecesPiochees;// pieces que le joueur a piochees et pas encore placees sur son camp
	
	
	//Constructeur
	public Acteur(String nom) {
		//variable STATS
		this.nom = nom;
		this.nb_BlancsJoues=0;
		this.nb_Mauvais_Coups_Joues=0;
		this.nb_Vols = 0;
		
		//variable GAMEPLAY
		campJ = new PyramideJoueur(6, 6);
		this.piecesVolees = new ArrayList<Piece>();
		this.piecesPiochees = new ArrayList<Piece>();
	}
	
	
	// FONCTION A IMPLEMENTER DANS IA && JOUEUR HUMAIN -------------------------------------------------------------------
	public Coup jouer(ArrayList<Coup> arr) // pyramide au milieu
	{
		return null;
	}
	
	public ArrayList<PiecePyramide> Phase1() // revoit une liste de piece a posée dans t'as pyramide (phase 1)
	{
		return null;
	}
	
	public PiecePyramide VoleePiece() // revoit la piece de la pyramide (en face) a volée
	{
		return null;
	}
	//---------------------------------------------------------------------------------------------------------------------

		


	
	//variable STATS --------------------------------------------
	
	//NOM du joueur -------
	public String getNom() {// renvoie le nom du joueur
		return this.nom;
	}
	//BLANC ------
	public void addBlancJoue() {
		this.nb_BlancsJoues++;
	}
	public void retireBlancJoue() {
		this.nb_BlancsJoues--;
	}
	public int getBlancsJoues() {
		return this.nb_BlancsJoues;
	}
	//MAUVAIS COUP------------
	public void addMauvaisCoup() {
		this.nb_Mauvais_Coups_Joues++;
	}
	public void retireMauvaisCoup() {
		this.nb_Mauvais_Coups_Joues--;
	}
	public int getMauvaisCoupsJoues() {
		return this.nb_Mauvais_Coups_Joues;
	}
	//VOL -------------------
	public void addVol() {
		this.nb_Vols++;
	}
	public void retireVol() {
		this.nb_Vols--;
	}
	public int getNbVols() {
		return this.nb_Vols;
	}
	
	
	
	
	//VARIABLE GAMEPLAY -----------------------------------------------------
	
	//PYRAMIDE JOUEUR CAMP -------------------
	public void setCamp(PyramideJoueur camp) {
		this.campJ=camp;
	}
	public PyramideJoueur getCamp() {
		return this.campJ;
	}
	//LISTE piecesVolees -------------------
	public void addPieceVolee(Piece p) {
		this.piecesVolees.add(p);
		this.addVol();
	}
	public Piece retirerPieceVolee(Piece p) {
		this.piecesVolees.remove(p);
		this.retireVol();
		return p;
	}
	public ArrayList<Piece> getPiecesVolees() {// piece Volees
		return this.piecesVolees;
	}
	public String toStringPiecesVolees() {
		String tableau = new String();
		for (int i = 0; i < piecesVolees.size(); i++) {
			tableau += piecesVolees.get(i).toString();
		}
		return tableau;
	}
	//LISTE piecesPiochees -------------------
	public void addPiecePiochee(Piece p) {
		this.piecesPiochees.add(p);
	}
	public ArrayList<Piece> getPiecesPiochees() {// piece Piochees
		return this.piecesPiochees;
	}
	public int getTaillePiecesPiochees() {
		return piecesPiochees.size();
	}
	
	
	
	
	
	//FONCTION GAMEPLAY ------------------------------------------------------
	
	// INTERACTION PYRAMIDE ----------
	public ArrayList<PiecePyramide> getPiecesJouables() {
		ArrayList<PiecePyramide> pJouables = this.campJ.piecesJouables();
		PiecePyramide pieceVoleeCourante;
		for (Piece p : this.piecesVolees) {
			pieceVoleeCourante = new PiecePyramide(p, null);
			pJouables.add(pieceVoleeCourante);
		}
		return pJouables;
	}
	public void afficherCoupsJouables(ArrayList<Coup> arr) {
		int taille = arr.size();
		for (int i = 0; i < taille; i++) {
			System.out.println("[" + i + "] : " + arr.get(i).toString());
		}
	}
	
	// LA PIOCHE ----------
	public void melangePiecesPiochees() {
		Collections.shuffle(this.piecesPiochees);
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
}
