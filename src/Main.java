import java.io.File;

import Controleur.*;
import Modeles.*;

public class Main {
	public static void main(String args[]) {
		
		int i=0;
		while(i<1) {
			Jeu j=new Jeu();
			i++;
		}
		/*
		while(i<1) {
			Joueur j1 = new Joueur("Gaston");
			Joueur j2 = new Joueur("Mademoiselle Jeanne");
			Partie p=new Partie(j1, j2);
			System.out.println(p.getBaseMontagne().toString());
			Partie.afficherCoups(p.getBaseMontagne().piecesPosables());
			i++;
		}
		*/
	}
}
