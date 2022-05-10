package Modeles;

import java.util.ArrayList;
import java.util.Scanner;

public class Joueur extends ActeurClasse implements Acteur {

	public Joueur(String nom) {
		super(nom);
	}
	
	public Coup jouer(ArrayList<Coup> arr) {
		System.out.println("Joueur, veuillez jouer un coup :");
		super.afficherCoupsJouables(arr);
		Scanner myObj = new Scanner(System.in);  // Create a Scanner object
		String coup = myObj.nextLine();
		return arr.get(Integer.parseInt(coup));
	}

	public void placerPieces() {
        Position pos;
        for(int i=0; i<6; i++) {
            for(int j=0; j<6-i; j++) {
                pos=new Position(i,j);
                if(piecesPiochees.size()==0) {
                    System.err.println("Plus de pièce à placer ! Votre camp de base est déjà construit !");
                    return;
                }
                Piece p=piecesPiochees.get(0);
                PiecePyramide pp = new PiecePyramide(p, pos);
                campJ.empiler(pp);
                piecesPiochees.remove(p);
            }
        }
    }
}