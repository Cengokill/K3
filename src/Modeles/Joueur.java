package Modeles;

import java.awt.Color;
import java.util.ArrayList;

public class Joueur implements Acteur{
	private String nom;
	private PyramideJoueur p;
	private ArrayList<Piece> liste_pieces;//liste des pièces qui NE SONT PAS sur la pyramide du joueur
	
	public Joueur(String nom) {
		this.nom=nom;
		this.liste_pieces = new ArrayList<Piece>();
		Piece p1=new Piece(Color.blue, null);
		Piece p2=new Piece(Color.green, null);
		Piece p3=new Piece(Color.yellow, null);
		Piece p4=new Piece(Color.red, null);
		Piece p5=new Piece(Color.black, null);
		Piece p6=new Piece(Color.white, null);
		Piece p7=new Piece(Color.LIGHT_GRAY, null);
		liste_pieces.add(p1);
		liste_pieces.add(p2);
		liste_pieces.add(p3);
		liste_pieces.add(p4);
		liste_pieces.add(p5);
		liste_pieces.add(p6);
		liste_pieces.add(p7);
		p=new PyramideJoueur(6,6);
	}
	public void piocher(Piece p) {
		this.liste_pieces.add(p);		
	}
	
	public String nomJ() {//renvoie le nom du joueur
		return this.nom;
	}
	
	public PyramideJoueur pyramideJ() {//renvoie la pyramide du joueur
		return this.p;
	}
	
	public void placerPiece(Piece p) {
		
	}

	public void construire() {
				
	}

	public Coup jouer(PyramideMontagne p) {
		// TODO Auto-generated method stub
		return null;
	}
}