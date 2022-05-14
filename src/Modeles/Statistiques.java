package Modeles;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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
	Partie p;
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
	
	public void ecrireStats(String chemin, int numPartie) {
		File f = new File(chemin+"00"+numPartie+".txt");
		try {
			f.createNewFile();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		try {
			FileWriter writer = new FileWriter(f, false);// erire en mode remplacement
			BufferedWriter bw = new BufferedWriter(writer);
			bw.write("======= Statistiques generales =======");
			bw.newLine();
			bw.write("Joueur premier coup : "+this.premierCoup);
			bw.newLine();
			bw.write("Joueur victorieux : "+this.vainqueur);
			bw.newLine();
			bw.write("======= Joueur 1 =======");
			bw.newLine();
			bw.write("Nom : "+this.acteur1.getNom());
			bw.newLine();
			bw.write("Blancs joues : "+this.nbBlancsJ1);
			bw.newLine();
			bw.write("Vols effectues : "+this.nbVolsJ1);
			bw.newLine();
			bw.write("Mauvais coups joues : "+this.nbCoupsMauvaisJ1);
			bw.newLine();
			bw.write("======= Joueur 1 =======");
			bw.newLine();
			bw.write("Nom : "+this.acteur2.getNom());
			bw.newLine();
			bw.write("Blancs joues : "+this.nbBlancsJ2);
			bw.newLine();
			bw.write("Vols effectues : "+this.nbVolsJ2);
			bw.newLine();
			bw.write("Mauvais coups joues : "+this.nbCoupsMauvaisJ2);
			bw.close();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
