package Modeles;

import java.awt.Color;
import java.util.ArrayList;

public class Joueur {
	private String nom;
	private PyramideJoueur p;
	
	public Joueur(String nom) {
		this.nom=nom;
		ArrayList<Piece> liste_pieces = new ArrayList<Piece>();
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
		Position pos = new Position(0,0);
		for(int i=0; i<7 ; i++) {
			p.empiler(liste_pieces.get(i),pos);
		}
		
	}
	
	public void coupsJouables(Jeu j, pyramide p) {//renvoie les coups jouables du joueur
		
	}
	
	public String nomJ() {//renvoie le nom du joueur
		return this.nom;
	}
	
	public void volerPiece(Joueur j) {//vole une piece au joueur j
		
	}
	
	public void placerPiece(Piece p) {
		
	}
}