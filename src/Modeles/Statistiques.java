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
	int vainqueur;//0 : acteur1 | 1 : acteur2 | |2 : egalite
	int nbVolsJ1, nbVolsJ2, nbBlancsJ1, nbBlancsJ2, nbCoupsMauvaisJ1, nbCoupsMauvaisJ2;
	ArrayList<Coup> coupsJ1, coupsJ2;

	public Statistiques(Acteur acteur1, Acteur acteur2, int joueurDebut, int vainqueur) {
		this.acteur1=acteur1;
		this.acteur2=acteur2;
		this.premierCoup=joueurDebut;
		this.vainqueur=vainqueur;
		this.coupsJ1=acteur1.getHistCoups();
		this.coupsJ2=acteur2.getHistCoups();
		this.nbBlancsJ1=acteur1.getBlancsJoues();
		this.nbBlancsJ2=acteur2.getBlancsJoues();
		this.nbCoupsMauvaisJ1=acteur1.getMauvaisCoupsJoues();
		this.nbCoupsMauvaisJ2=acteur2.getMauvaisCoupsJoues();
		this.nbVolsJ1=acteur1.getNbVols();
		this.nbVolsJ2=acteur2.getNbVols();
	}
}
