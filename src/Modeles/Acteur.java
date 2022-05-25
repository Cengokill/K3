package Modeles;

import java.util.ArrayList;
import java.util.Collections;

public class Acteur {

	// variables STATS
	protected int nb_BlancsJoues;// pieces blanches jouees
	protected int nb_Mauvais_Coups_Joues;// mauvais coups joues (une piece sur 2 pieces de meme couleur)
	protected int nb_Vols;

	// variables GAMEPLAY
	protected String nom;
	protected PyramideJoueur campJ;
	protected ArrayList<Piece> piecesVolees;// pieces que le joueur a volees a l'autre joueur
	protected ArrayList<Piece> piecesPiochees;// pieces que le joueur a piochees et pas encore placees sur son camp
	protected Integer diff = 3;

	// Constructeur
	public Acteur(String nom) {
		// variable STATS
		this.nom = nom;
		this.nb_BlancsJoues = 0;
		this.nb_Mauvais_Coups_Joues = 0;
		this.nb_Vols = 0;

		// variable GAMEPLAY
		campJ = new PyramideJoueur(6, 6);
		this.piecesVolees = new ArrayList<Piece>();
		this.piecesPiochees = new ArrayList<Piece>();
	}

	// METHODES A IMPLEMENTER DANS IA ET JOUEUR HUMAIN
	// -------------------------------------------------------------------
	public int tempsReflexion() {
		return 0;
	}

	public Coup jouer(ArrayList<Coup> arr, Partie encours) // pyramide au milieu
	{
		return null;
	}

	public ArrayList<PiecePyramide> phase1(Partie encours) // revoit une liste de piece a posée dans t'as pyramide
															// (phase 1)
	{
		return null;
	}

	public PiecePyramide choixVol(ArrayList<PiecePyramide> arr) // revoit la piece de la pyramide (en face) a volée
	{
		return null;
	}
	// ---------------------------------------------------------------------------------------------------------------------

	// METHODES STATS --------------------------------------------

	// NOM du joueur -------
	public String getNom() {// renvoie le nom du joueur
		return this.nom;
	}

	// BLANC ------
	public void addBlancJoue() {// ok
		this.nb_BlancsJoues++;
	}

	public void retireBlancJoue() {// ok
		this.nb_BlancsJoues--;
	}

	public int getBlancsJoues() {
		return this.nb_BlancsJoues;
	}
	
	public void setBlancsJoues(int n) {// ok
		this.nb_BlancsJoues=n;
	}

	// MAUVAIS COUP------------
	public void addMauvaisCoup() {// ok
		this.nb_Mauvais_Coups_Joues++;
	}

	public void retireMauvaisCoup() {// ok
		this.nb_Mauvais_Coups_Joues--;
	}

	public int getMauvaisCoupsJoues() {
		return this.nb_Mauvais_Coups_Joues;
	}
	
	public void setMauvaisCoupsJoues(int n) {// ok
		this.nb_Mauvais_Coups_Joues=n;
	}

	// VOL -------------------
	public void addVol() {// ok
		this.nb_Vols++;
	}

	public void retireVol() {
		this.nb_Vols--;
	}

	public int getNbVols() {
		return this.nb_Vols;
	}
	
	public void setVols(int n) {// ok
		this.nb_Vols=n;
	}

	// VARIABLES GAMEPLAY -----------------------------------------------------

	// PYRAMIDE JOUEUR CAMP -------------------
	public void setCamp(PyramideJoueur camp) {
		this.campJ = camp;
	}

	public PyramideJoueur getCamp() {
		return this.campJ;
	}

	// LISTE piecesVolees -------------------
	public void addPieceVolee(Piece p) {// ok
		this.piecesVolees.add(p);
		this.addVol();
	}

	public Piece retirerPieceVolee(Piece p) {// ok
		this.piecesVolees.remove(p);
		this.retireVol();
		return p;
	}

	public ArrayList<Piece> getPiecesVolees() {// piece Volees
		return this.piecesVolees;
	}	

	public String toStringPiecesVolees() {
		String tableau = "";
		for (int i = 0; i < piecesVolees.size(); i++) {
			tableau += piecesVolees.get(i).toString();
			if(i!=piecesVolees.size()-1) {
				tableau+="|";
			}
		}
		return tableau;
	}
	
	public boolean stringToPiecesVolees(String ligne) {
		String[] pieces=ligne.split("|");
		Piece p;
		int taille=pieces.length;
		for(int i=0; i<taille; i++) {
			switch(pieces[i]) {//BLEU, VERT, JAUNE, ROUGE, NOIR, BLANC, NATUREL
			case "BLEU":
				p=new Piece(Couleurs.BLEU);
				break;
			case "VERT":
				p=new Piece(Couleurs.NOIR);
				break;
			case "JAUNE":
				p=new Piece(Couleurs.ROUGE);
				break;
			case "ROUGE":
				p=new Piece(Couleurs.VERT);
				break;
			case "NOIR":
				p=new Piece(Couleurs.JAUNE);
				break;
			case "BLANC":
				p=new Piece(Couleurs.BLANC);
				break;
			case "NATUREL":
				p=new Piece(Couleurs.NATUREL);
				break;
			default:
				return false;
			}
			this.piecesVolees.add(p);
		}
		return true;
	}

	// LISTE piecesPiochees -------------------
	public void addPiecePiochee(Piece p) {
		this.piecesPiochees.add(p);
	}

	public ArrayList<Piece> getPiecesPiochees() {// piece Piochees
		return this.piecesPiochees;
	}
	
	public String piecesPiocheesToString() {
		String tab="";
		for(int i=0; i<this.piecesPiochees.size(); i++) {
			tab+=piecesPiochees.get(i).toString();
			if(i!=piecesPiochees.size()-1) {
				tab+=":";
			}
		}
		return tab;
	}
	
	public boolean stringToPiecesPiochees(String ligne) {
		String[] pieces=ligne.split(":");
		Piece p;
		int taille=pieces.length;
		for(int i=0; i<taille; i++) {
			switch(pieces[i]) {//BLEU, VERT, JAUNE, ROUGE, NOIR, BLANC, NATUREL
			case "VERT":
				p=new Piece(Couleurs.NOIR);
				addPiecePiochee(p);
				break;
			case "JAUNE":
				p=new Piece(Couleurs.ROUGE);
				addPiecePiochee(p);
				break;
			case "ROUGE":
				p=new Piece(Couleurs.VERT);
				addPiecePiochee(p);
				break;
			case "NOIR":
				p=new Piece(Couleurs.JAUNE);
				addPiecePiochee(p);
				break;
			case "BLANC":
				p=new Piece(Couleurs.BLANC);
				addPiecePiochee(p);
				break;
			case "NATUREL":
				p=new Piece(Couleurs.NATUREL);
				addPiecePiochee(p);
				break;
			default:
				return false;
			}
		}
		return true;
	}

	public int getTaillePiecesPiochees() {
		return piecesPiochees.size();
	}

	public void removePiecePiochee(Piece p) {
		int taille = this.piecesPiochees.size();
		for (int i = 0; i < taille; i++) {
			if (piecesPiochees.get(i).getColor().equals(p.getColor())) {
				piecesPiochees.remove(i);
				return;
			}
		}
	}

	// METHODES GAMEPLAY ------------------------------------------------------

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

	public void placerPiecesRandom(PyramideMontagne pyrM) {
		// empile toutes les pieces d'un joueur sur son camp aleatoirement
		Position pos;
		ArrayList<Position> arrPos;
		int taillePioche = piecesPiochees.size();
		melangePiecesPiochees();
		while (!estPosable(piecesPiochees.get(taillePioche - 1), pyrM)
				|| !estPosable(piecesPiochees.get(taillePioche - 2), pyrM)
				|| !estPosable(piecesPiochees.get(taillePioche - 3), pyrM)) {
			melangePiecesPiochees();
		}
		Piece p;
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 6 - i; j++) {
				p = this.piecesPiochees.get(0);
				arrPos = this.campJ.posDisponibles();
				pos = arrPos.get(0);// pos devient la position choisie par le joueur
				PiecePyramide pp = new PiecePyramide(p, pos);
				this.campJ.empiler(pp);
				piecesPiochees.remove(0);
			}
		}
	}

	public boolean estPosable(Piece p, PyramideMontagne pyrM) {
		// renvoie true si la premiere piece de la pioche dun joueur peut etre empilee
		// sur le camp de la montagne
		ArrayList<PiecePyramide> pieces_Posables = pyrM.piecesPosables();
		for (PiecePyramide pCour : pieces_Posables) {
			if (pCour.getPiece().getColor().equals(p.getColor())) {
				return true;
			}
		}
		return false;
	}

	// DIFFICULTE
	public int getDiff() {
		return diff;
	}

	// FONCTIONS UTILES UNIQUEMENT POUR LE JOUEUR
	public void setPiecesPosees(PiecePyramide p) {
	}
	
	public void melangeAleatCamp() {
		ArrayList<Piece> pioche = this.getPiecesPiochees();
		if(pioche.isEmpty()) {
			initCampEtPioche();
			pioche = this.getPiecesPiochees();
		}
		Collections.shuffle(pioche);
		for(int i=0; i<this.getCamp().getHauteur(); i++) {
			for(int j=0; j<this.getCamp().getLargeur()-i; j++) {
				if(this.getCamp().empiler(new PiecePyramide(pioche.get(0), new Position(i, j)))) {
					this.removePiecePiochee(pioche.get(0));
				}
			}
		}
	}
	
	public void initCampEtPioche() {
		for(int i=0; i<this.getCamp().getHauteur(); i++) {
			for(int j=0; j<this.getCamp().getLargeur()-i; j++) {
				Position pos = new Position(i,j);
				Piece pCourante = this.getCamp().getPiece(pos);
				if(pCourante!=null) {
					this.getCamp().retirer(pos);
					this.addPiecePiochee(pCourante);
				}
			}
		}
	}
}



























