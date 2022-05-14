package Modeles;

import java.util.ArrayList;

public class Statistiques {
	/***
	 *  qui a gagne
	 *  nb de vols de j1/j2
	 *  nb de coups sur 2 pieces de la meme couleur j1/j2
	 *  
	 */
	Acteur acteur1;
	Acteur acteur2;
	int premierCoup;
	int vainqueur;
	int nbVolsJ1, nbVolsJ2;
	int nbCoupsMauvaisJ1, nbCoupsMauvaisJ2;
	ArrayList<Coup> coupsJ1, coupsJ2;

	public Statistiques(Acteur acteur1, Acteur acteur2, int joueurDebut) {
		this.acteur1=acteur1;
		this.acteur2=acteur2;
		this.premierCoup=joueurDebut;
	}

}
