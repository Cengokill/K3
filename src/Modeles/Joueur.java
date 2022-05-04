package Modeles;

import java.awt.Color;
import java.util.ArrayList;

public class Joueur implements Acteur{
	private String nom;
	private PyramideJoueur p;
	private ArrayList<Piece> liste_pieces;
	
	public Joueur(String nom) {
		this.nom=nom;
		this.liste_pieces = new ArrayList<Piece>();
		Piece p1=new Piece(Color.blue);
		Piece p2=new Piece(Color.green);
		Piece p3=new Piece(Color.yellow);
		Piece p4=new Piece(Color.red);
		Piece p5=new Piece(Color.black);
		Piece p6=new Piece(Color.white);
		Piece p7=new Piece(Color.LIGHT_GRAY);
		liste_pieces.add(p1);
		liste_pieces.add(p2);
		liste_pieces.add(p3);
		liste_pieces.add(p4);
		liste_pieces.add(p5);
		liste_pieces.add(p6);
		liste_pieces.add(p7);
		p=new PyramideJoueur(8,5);
	}
	
	public void coupsJouables(Jeu j, Pyramide p) {//renvoie les coups jouables du joueur
		
	}
	
	public String nomJ() {//renvoie le nom du joueur
		return this.nom;
	}
	
	public void volerPiece(Joueur j) {//vole une piece au joueur j
		
	}
	
	public void placerPiece(Piece p) {
		
	}

	@Override
	public void piocher(Piece p) {
		this.liste_pieces.add(p);		
	}

	@Override
	public void construire() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void jouer() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void volerPiece() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void piocher() {
		// TODO Auto-generated method stub
		
	}
}