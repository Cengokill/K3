package Controleur;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

import Modeles.*;

public class Jeu {
	private Joueur j1;
	private Joueur j2;
	private ArrayList<Piece> basePieces;//pieces disponibles à se partager entre les joueurs, uniquement à la création du jeu
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
		pBleu=new Piece(Couleurs.BLEU);
		pVert=new Piece(Couleurs.VERT);
		pJaune=new Piece(Couleurs.JAUNE);
		pRouge=new Piece(Couleurs.ROUGE);
		pNoir=new Piece(Couleurs.NOIR);
		pBlanc=new Piece(Couleurs.BLANC);
		pNaturel=new Piece(Couleurs.NATUREL);
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
			PiecePyramide pp=new PiecePyramide(element, pos);
			baseMontagne.empiler(pp);
		}
	}
	
	public LinkedList<Coup> CoupsJouables(Joueur j) {//renvoie les pieces et la pos jouables du joueur
		PyramideJoueur p = j.pyramideJ();
		LinkedList<Coup> coupsPosables = new LinkedList<Coup>();
		Piece p1, p2;
		Position pos1, pos2;
		Coup c;
		LinkedList<PiecePyramide> piecesBase=baseMontagne.piecesPosables();
		LinkedList<PiecePyramide> piecesJoueur = p.piecesJouables();
		for(int i=0;i<piecesJoueur.size(); i++) {//pour chaque piece du joueur
			PiecePyramide pieceJoueur=piecesJoueur.get(i);
			p1=pieceJoueur.getPiece();
			for(PiecePyramide pp : piecesBase){
				p2=pp.getPiece();
				if(p1.getColor()==p2.getColor()) {
					pos1=pieceJoueur.getPos();
					pos2=pp.getPos();
					c=new Coup(p1, pos1, pos2);
					coupsPosables.add(c);
				}
			}
		}
		return coupsPosables;
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