package Modeles;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
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
	String cheminStats;

	public Statistiques(String chemin) {
		this.cheminStats=chemin;
	}
	
	public void initStats(Acteur acteur1, Acteur acteur2, int joueurDebut, int vainqueur) {
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
	
	public void ecrireStats(int numPartie) {
		File f = new File(this.cheminStats+"00"+numPartie+".txt");
		try {
			f.createNewFile();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		try {
			FileWriter writer = new FileWriter(f, false);// erire en mode remplacement
			BufferedWriter bw = new BufferedWriter(writer);
			bw.write(this.acteur1.getNom());
			bw.newLine();
			bw.write(this.acteur2.getNom());
			bw.newLine();
			bw.write(this.premierCoup);
			bw.newLine();
			bw.write(this.vainqueur);
			bw.newLine();
			bw.write(this.nbBlancsJ1);
			bw.newLine();
			bw.write(+this.nbVolsJ1);
			bw.newLine();
			bw.write(this.nbCoupsMauvaisJ1);
			bw.newLine();
			bw.write(this.nbBlancsJ2);
			bw.newLine();
			bw.write(this.nbVolsJ2);
			bw.newLine();
			bw.write(this.nbCoupsMauvaisJ2);
			bw.close();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void combinerStats(int num1, int num2) {
		ArrayList<String> arr=new ArrayList<String>();
		String fichier="";
		FileReader reader;
		BufferedReader br;
		String ligne_lue;
		ArrayList<String> tab = new ArrayList<String>();
		String nomJ1, nomJ2;
		int nbPremiersCoupsJ1=0;
		int nbPremiersCoupsJ2=0;
		int nbVictoiresJ1=0;
		int nbVictoiresJ2=0;
		int nbBlancsJouesJ1=0;
		int nbBlancsJouesJ2=0;
		int nbVolsJ1=0;
		int nbVolsJ2=0;
		int nbMauvaisCoupsJ1=0;
		int nbMauvaisCoupsJ2=0;
		try {
			//premier fichier
			fichier=this.cheminStats+"00"+num1+".txt";
			reader = new FileReader(fichier);
			br = new BufferedReader(reader);
			while ((ligne_lue = br.readLine()) != null) {
				tab.add(ligne_lue);
			}
			br.close();
			nomJ1=tab.get(0);//1. bw.write(this.acteur1.getNom());
			nomJ2=tab.get(1);//2. bw.write(this.acteur2.getNom());
			arr.add(nomJ1);
			arr.add(nomJ2);
			//fichiers suivants
			int k=0;
			for(int i=num1+1; i<num2+1; i++) {
				fichier=this.cheminStats+"00"+i+".txt";
				reader = new FileReader(fichier);
				br = new BufferedReader(reader);
				while ((ligne_lue = br.readLine()) != null) {
					if(k>2) {
						tab.add(ligne_lue);
					}
					k++;
				}
				br.close();
				k=0;
				//0 premierCoup
				//1 vainqueur
				//2 nbBlancsJ1
				//3 nbVolsJ1
				//4 nbCoupsMauvaisJ1
				//5 nbBlancsJ2
				//6 nbVolsJ2
				//7 nbCoupsMauvaisJ2
				if(tab.get(0).equals(nomJ1)) {
					nbPremiersCoupsJ1++;
				}else {
					nbPremiersCoupsJ2++;
				}
				if(tab.get(1).equals(nomJ1)) {
					nbVictoiresJ1++;
				}else {
					nbVictoiresJ2++;
				}
				nbBlancsJouesJ1+=Integer.parseInt(tab.get(2));
				nbVolsJ1+=Integer.parseInt(tab.get(3));
				nbMauvaisCoupsJ1+=Integer.parseInt(tab.get(4));
				nbBlancsJouesJ2+=Integer.parseInt(tab.get(5));
				nbVolsJ2+=Integer.parseInt(tab.get(6));
				nbMauvaisCoupsJ2+=Integer.parseInt(tab.get(7));
			}
			arr.add(String.valueOf(num1));
			arr.add(String.valueOf(num2));
			arr.add(nomJ1);
			arr.add(String.valueOf(nbVictoiresJ1));
			arr.add(String.valueOf(nbPremiersCoupsJ1));
			arr.add(String.valueOf(nbBlancsJouesJ1));
			arr.add(String.valueOf(nbVolsJ1));
			arr.add(String.valueOf(nbMauvaisCoupsJ1));
			arr.add(nomJ2);
			arr.add(String.valueOf(nbVictoiresJ2));
			arr.add(String.valueOf(nbPremiersCoupsJ2));
			arr.add(String.valueOf(nbBlancsJouesJ2));
			arr.add(String.valueOf(nbVolsJ2));
			arr.add(String.valueOf(nbMauvaisCoupsJ2));
		}
		catch (Exception e) {
			System.err.println("Erreur : le fichier "+fichier+" n'a pas pu etre lu.");
			e.printStackTrace();
		}
		ecrireStatsGenerales(arr);
	}
	
	public void ecrireStatsGenerales(ArrayList<String> arr) {
		File f = new File(this.cheminStats+"Stats_parties_"+arr.get(0)+"-"+arr.get(1)+".txt");
		try {
			f.createNewFile();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		try {
			FileWriter writer = new FileWriter(f, false);// erire en mode remplacement
			BufferedWriter bw = new BufferedWriter(writer);
			bw.write("======= Statistiques generales =======");
			bw.write("======= "+arr.get(2)+" =======");
			bw.newLine();
			bw.write("Nombre de victoires : "+arr.get(3));
			bw.newLine();
			bw.write("Nombre de fois ou il a commence : "+arr.get(4));
			bw.newLine();
			bw.write("Pieces Blanches jouees : "+arr.get(5));
			bw.newLine();
			bw.write("Vols effectues : "+arr.get(6));
			bw.newLine();
			bw.write("Mauvais coups joues : "+arr.get(7));
			bw.newLine();
			bw.write("======= "+arr.get(8)+" =======");
			bw.newLine();
			bw.write("Nombre de victoires : "+arr.get(9));
			bw.newLine();
			bw.write("Nombre de fois ou il a commence : "+arr.get(10));
			bw.newLine();
			bw.write("Pieces Blanches jouees : "+arr.get(11));
			bw.newLine();
			bw.write("Vols effectues : "+arr.get(12));
			bw.newLine();
			bw.write("Mauvais coups joues : "+arr.get(13));
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}








