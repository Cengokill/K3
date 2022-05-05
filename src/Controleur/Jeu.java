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
		initialiserSac();
		j1=new Joueur("Gaston");
		j2=new Joueur("Mademoiselle Jeanne");
		initCampJoueur(j1);
		initCampJoueur(j2);
		initBaseMontagne();
	}
	
	public void initialiserSac() {//ajoute toutes les pièces au sac
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
		Collections.shuffle(basePieces);//mélange les pièces à piocher pour les joueurs
		System.out.println("sac initialisé. Taille : "+basePieces.size());
	}
	
	public void initCampJoueur(Joueur jou) {//création du camps du joueur j
		for(int i=0; i<6; i++) {//hauteur
			for(int j=0; j<6-i; j++) {//largeur
				Piece element=basePieces.get(0);
				Position pos=new Position(j,i);
				PiecePyramide pp=new PiecePyramide(element, pos);
				jou.getCamp().empiler(pp);
				basePieces.remove(0);
				Piece pAffich=jou.getCamp().getPiece(pos);
				//System.out.println("piece "+pos.x+","+pos.y+" ajoutée au camp de "+jou.getNom()+" : "+pAffich.toString());
			}
		}
		jou.getCamp().afficher();
		System.out.println("Camp de "+jou.getNom()+" initialisé. Taille : "+jou.getCamp().nbPieces);
	}
	
	public void initBaseMontagne() {//création de la base de la montagne constituée de 9 pièces
		baseMontagne=new PyramideMontagne(9,9);//9 étages, 9 pièces au dernier étage
		for(int i=0; i<9; i++) {
			Piece element=basePieces.get(0);
			Position pos=new Position(i,0);
			PiecePyramide pp=new PiecePyramide(element, pos);
			baseMontagne.empiler(pp);
			basePieces.remove(0);
		}
		System.out.println("Base de la montagne initialisée. Taille : "+baseMontagne.getHauteur());
		System.out.println("Le sac a maintenant une taille de "+basePieces.size()+" pièces.");
		baseMontagne.afficher();
	}
	
	public LinkedList<Coup> CoupsJouables(Joueur j) {//renvoie les pieces et la pos jouables du joueur
		PyramideJoueur p = j.getCamp();
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
	
	public PyramideMontagne getBaseMontagne() {
		return this.baseMontagne;
	}
	
	public Position volerPiece(Joueur voleur, Joueur victime) {//vole une piece au joueur j
		return new Position(0,0);
	}
	
	public void afficherCoups(LinkedList<PiecePyramide> arr) {
		int taille=arr.size();
		for(int i=0; i<taille; i++) {
			Piece pi=arr.get(i).getPiece();
			Position pos=arr.get(i).getPos();
			System.out.println(pi.toString()+":"+pos.toString());
		}
	}
	
	public void sauverPartie() {
		
	}
	
	public void chargerPartie() {
		
	}
	
	public void jouer() {
		
	}
	
	public Joueur joueur1() {
		return j1;
	}
	
	public Joueur joueur2() {
		return j2;
	}
}