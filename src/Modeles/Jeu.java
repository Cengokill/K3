package Modeles;

import java.awt.Color;
import java.util.ArrayList;

public class Jeu {
	private Joueur j1;
	private Joueur j2;
	private ArrayList<Piece> basePieces = new ArrayList<Piece>();//pieces disponible à se partager entre les joueurs
	Piece pBleu;
	Piece pVert;
	Piece pJaune;
	Piece pRouge;
	Piece pNoir;
	Piece pBlanc;
	Piece pNaturel;
	
	public Jeu() {
		pBleu=new Piece(Color.blue);
		pVert=new Piece(Color.green);
		pJaune=new Piece(Color.yellow);
		pRouge=new Piece(Color.red);
		pNoir=new Piece(Color.black);
		pBlanc=new Piece(Color.white);
		pNaturel=new Piece(Color.LIGHT_GRAY);
		initialiserJeu();
	}
	
	public void initialiserJeu() {//ajoute toutes les pièces au jeu
		int nb_pieces_par_couleur=9;
		int nb_naturels=6;
		int nb_blancs=4;
		for(int i=0 ; i<nb_pieces_par_couleur ; i++) {
			basePieces.add(pBleu);
			basePieces.add(pVert);
			basePieces.add(pJaune);
			basePieces.add(pRouge);
			basePieces.add(pNoir);
		}
		for(int i=0 ; i<nb_naturels ; i++) {
			basePieces.add(pNaturel);
		}
		for(int i=0 ; i<nb_blancs ; i++) {
			basePieces.add(pBlanc);
		}	
	}
	
	public void sauverPartie() {
		
	}
	
	public void chargerPartie() {
		
	}
	
	public void jouer() {
		
	}
	
	public Joueur Joueur1() {
		return j1;
	}
	
	public Joueur Joueur2() {
		return j2;
	}
}