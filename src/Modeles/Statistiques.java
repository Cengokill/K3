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
	ArrayList<Coup> histCoups;
	String cheminStats;

	public Statistiques(String chemin) {
		this.cheminStats=chemin;
	}
	
	public void initStats(Acteur acteur1, Acteur acteur2, int joueurDebut, int vainqueur, ArrayList<Coup> histCoups) {
		this.acteur1=acteur1;
		this.acteur2=acteur2;
		this.premierCoup=joueurDebut;
		this.vainqueur=vainqueur;
		this.histCoups=histCoups;
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
			bw.write(String.valueOf(this.vainqueur));
			bw.newLine();
			bw.write(String.valueOf(this.premierCoup));
			bw.newLine();
			bw.write(String.valueOf(this.nbBlancsJ1));
			bw.newLine();
			bw.write(String.valueOf(+this.nbVolsJ1));
			bw.newLine();
			bw.write(String.valueOf(this.nbCoupsMauvaisJ1));
			bw.newLine();
			bw.write(String.valueOf(this.nbBlancsJ2));
			bw.newLine();
			bw.write(String.valueOf(this.nbVolsJ2));
			bw.newLine();
			bw.write(String.valueOf(this.nbCoupsMauvaisJ2));
			bw.close();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void combinerStats(int num1, int num2) {
		ArrayList<String> arr=new ArrayList<String>();
		ArrayList<String> tab = new ArrayList<String>();
		String fichier="";
		FileReader reader;
		BufferedReader br;
		String ligne_lue;
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
			nomJ1=tab.get(0);//1. acteur1.getNom()
			nomJ2=tab.get(1);//2. acteur2.getNom()
			//fichiers suivants
			int k=0;
			for(int i=num1+1; i<num2; i++) {
				fichier=this.cheminStats+"00"+i+".txt";
				reader = new FileReader(fichier);
				br = new BufferedReader(reader);
				while ((ligne_lue = br.readLine()) != null) {
					if(k>2) {//on n'ajoute pas les noms des joueurs
						tab.add(ligne_lue);
					}
					k++;
				}
				br.close();
				k=0;
				//0 nomJ1
				//1 nomJ2
				//2 vainqueur
				//3 Qui premier coup
				//4 nbBlancsJ1
				//5 nbVolsJ1
				//6 nbCoupsMauvaisJ1
				//7 nbBlancsJ2
				//8 nbVolsJ2
				//9 nbCoupsMauvaisJ2
				if(tab.get(2).equals("0")) {
					nbVictoiresJ1++;
				}else {//"1"
					nbVictoiresJ2++;
				}
				if(tab.get(3).equals(nomJ1)) {
					nbPremiersCoupsJ1++;
				}else {
					nbPremiersCoupsJ2++;
				}
				nbBlancsJouesJ1+=Integer.parseInt(tab.get(4));
				nbVolsJ1+=Integer.parseInt(tab.get(5));
				nbMauvaisCoupsJ1+=Integer.parseInt(tab.get(6));
				nbBlancsJouesJ2+=Integer.parseInt(tab.get(7));
				nbVolsJ2+=Integer.parseInt(tab.get(8));
				nbMauvaisCoupsJ2+=Integer.parseInt(tab.get(9));
			}
			arr.add(nomJ1);
			arr.add(nomJ2);
			arr.add(String.valueOf(num1));
			arr.add(String.valueOf(num2-1));
			arr.add(String.valueOf(nbVictoiresJ1));
			arr.add(String.valueOf(nbPremiersCoupsJ1));
			arr.add(String.valueOf(nbBlancsJouesJ1));
			arr.add(String.valueOf(nbVolsJ1));
			arr.add(String.valueOf(nbMauvaisCoupsJ1));
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
		File f = new File(this.cheminStats+"Stats_parties_"+arr.get(2)+"-"+arr.get(3)+".txt");
		try {
			f.createNewFile();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		try {
			FileWriter writer = new FileWriter(f, false);// erire en mode remplacement
			BufferedWriter bw = new BufferedWriter(writer);
			int nbEgalites=Integer.parseInt(arr.get(3))-Integer.parseInt(arr.get(2))-Integer.parseInt(arr.get(4))+Integer.parseInt(arr.get(9));
			bw.write("======= Statistiques generales =======");
			bw.newLine();
			bw.newLine();
			bw.write("Nombre d'egalites : "+String.valueOf(nbEgalites));
			bw.newLine();
			bw.write("======= "+arr.get(0)+" =======");
			bw.newLine();
			bw.write("Nombre de victoires : "+arr.get(4));
			bw.newLine();
			bw.write("Nombre de fois ou il a commence : "+arr.get(5));
			bw.newLine();
			bw.write("Pieces Blanches jouees : "+arr.get(6));
			bw.newLine();
			bw.write("Vols effectues : "+arr.get(7));
			bw.newLine();
			bw.write("Mauvais coups joues : "+arr.get(8));
			bw.newLine();
			bw.newLine();
			bw.write("======= "+arr.get(1)+" =======");
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
			bw.close();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}








