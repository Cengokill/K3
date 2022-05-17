package Modeles;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class ActeurClasse {

	protected String nom;
	protected PyramideJoueur campJ;
	protected ArrayList<Piece> piecesVolees;// pieces que le joueur a volees a l'autre joueur
	protected ArrayList<Piece> piecesPiochees;// pieces que le joueur a piochees et pas encore placees sur son camp
	protected int nb_BlancsJoues;//pieces blanches jouees
	protected int nb_Mauvais_Coups_Joues;//mauvais coups joues (une piece sur 2 pieces de meme couleur)
	protected int nb_Vols;

	public ActeurClasse(String nom) {
		this.nom = nom;
		this.piecesVolees = new ArrayList<Piece>();
		this.piecesPiochees = new ArrayList<Piece>();
		this.nb_BlancsJoues=0;
		this.nb_Mauvais_Coups_Joues=0;
		campJ = new PyramideJoueur(6, 6);
	}
	
	public void setCamp(PyramideJoueur camp) {
		this.campJ=camp;
	}
	
	public void placerPiecesRandom(PyramideMontagne pyrM) {
		//empile toutes les pieces d'un joueur sur son camp aleatoirement
		Position pos;
        ArrayList<Position> arrPos;
        int taillePioche=piecesPiochees.size();
        melangePiecesPiochees();
        while(!estPosable(piecesPiochees.get(taillePioche-1),pyrM)||!estPosable(piecesPiochees.get(taillePioche-2),pyrM)||!estPosable(piecesPiochees.get(taillePioche-3),pyrM)){
        	melangePiecesPiochees();
        }
        Piece p;
        for(int i=0; i<6; i++) {
            for(int j=0; j<6-i; j++) {
            	p=this.piecesPiochees.get(0);
                arrPos=this.campJ.posDisponibles();
                pos=arrPos.get(0);//pos devient la position choisie par le joueur
                PiecePyramide pp = new PiecePyramide(p, pos);
                this.campJ.empiler(pp);
                piecesPiochees.remove(0);
            }
        }
	}
	
	public boolean estPosable(Piece p, PyramideMontagne pyrM) {
		//renvoie true si la premiere piece de la pioche dun joueur peut etre empilee sur le camp de la montagne
		ArrayList<PiecePyramide> pieces_Posables=pyrM.piecesPosables();		
		for(PiecePyramide pCour : pieces_Posables) {
			if(pCour.getPiece().getColor().equals(p.getColor())) {
				return true;
			}
		}
		return false;
	}
	
	public void melangePiecesPiochees() {
		Collections.shuffle(this.piecesPiochees);
	}
	
	
	public int getBlancsJoues() {
		return this.nb_BlancsJoues;
	}
	
	public int getMauvaisCoupsJoues() {
		return this.nb_Mauvais_Coups_Joues;
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
		this.addVol();
	}
	
	public Piece retirerPieceVolee(Piece p) {
		this.piecesVolees.remove(p);
		this.retireVol();
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
