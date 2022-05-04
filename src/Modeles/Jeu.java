package Modeles;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

public class Jeu {//éàêëèôö
	private Joueur j1;
	private Joueur j2;
	private ArrayList<Piece> basePieces;//pieces disponible à se partager entre les joueurs, uniquement à la création du jeu
	private PyramideMontagne baseMontagne;//base de la montagne
	Piece pBleu;
	Piece pVert;
	Piece pJaune;
	Piece pRouge;
	Piece pNoir;
	Piece pBlanc;
	Piece pNaturel;
	
	public Jeu() {
		basePieces = new ArrayList<Piece>();
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
		Collections.shuffle(basePieces);//mélange du jeu
	}
	
	public void initBaseMontagne() {//création de la base de la montagne constituée de 9 pièces
		int taille=basePieces.size();
		baseMontagne=new PyramideMontagne(1,9);//1 étage, 9 pièces
		for(int i=0; i<9; i++) {
			Piece element=basePieces.get(taille-i-1);
			Position pos=new Position(0,i);
			baseMontagne.empiler(element, pos);
		}
	}
	
	public void coupsJouables(Joueur j) {//renvoie les coups jouables du joueur
		PyramideJoueur p = j.pyramideJ();
		LinkedList<Piece> piecesAcc = p.listePiecesAccessibles();
		LinkedList<Piece> piecesPosables = new LinkedList<Piece>();
		for(int i=0;i<piecesAcc.size(); i++) {
			
		}
		
	}
	
	public Position volerPiece(Joueur voleur, Joueur victime) {//vole une piece au joueur j
		return new Position(0,0);
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