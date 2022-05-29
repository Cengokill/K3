package Modeles;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class OptionsJeu {
	
	public String chemin;
	public int NB_LIGNES_OPTIONS=4;
	public String nom_fichier = "Options.txt";
	public int modeDaltonien;
	public int modePleinEcran;
	public int volumeEffetsSonores=6, volumeMusique=5;
	
	public OptionsJeu(String chemin) {
		this.chemin=chemin;
		lireOptions();
	}
	
	public void lireOptions() {// au tout premier lancement du jeu, le fichier Options.txt existe deja
		if(!testFichierExistant(this.chemin+nom_fichier)) {
			ecrireInitOptions();
		}
		ArrayList<String> tab = new ArrayList<String>();
			try {
				FileReader reader = new FileReader(this.chemin + nom_fichier);
				BufferedReader br = new BufferedReader(reader);
				String ligne_lue;
				while ((ligne_lue = br.readLine()) != null) {
					tab.add(ligne_lue);
				}
				br.close();
				if (tab.size() != this.NB_LIGNES_OPTIONS) {
					System.err.println("Erreur : le fichier " + nom_fichier + " a ete modifie. Il contient " + tab.size()+" lignes.");
					ecrireInitOptions();
					return;
				} else {
					// Mode daltonien 0/1
					// Volume des effets sonores
					// Volume de la musique
					// Mode plein ecran 0/1
					verifDaltonien(Integer.parseInt(tab.get(0)));
					verifVolume(Integer.parseInt(tab.get(1)), Integer.parseInt(tab.get(2)));
					verifEntier(Integer.parseInt(tab.get(3)));
				}
			}
			catch (Exception e) {
				System.err.println("Erreur : le fichier Options.txt est corrompu.");
				e.printStackTrace();
			}
	}
	public void verifEntier(int e){
		if(e!=0 && e!=1) {
			this.modePleinEcran=1;
		}else {
			this.modePleinEcran=e;
		}
	}
	
	public void verifDaltonien(int v) {// renvoie true si v est compris entre 0 et 1
		if(v!=0 && v!=1) {
			this.modeDaltonien=0;
		}else {
			this.modeDaltonien=v;
		}
	}

	public void verifVolume(int v1, int v2) {// renvoie true si le volume est compris entre 0 et 10
		if (v1 > 10 || v1 < 0) {
			this.volumeEffetsSonores = 6;
		}else {
			this.volumeEffetsSonores = v1;
		}
		if (v2 > 10 || v2 < 0) {
			this.volumeMusique = 4;
		}else {
			this.volumeMusique = v2;
		}
	}

	public boolean testFichierExistant(String nomFichier) {// renvoie true si le fichier existe
		try {
			File myFile = new File(nomFichier);
			if(myFile.isFile()) {
				return true;
			}
		} catch (Exception e) {
			System.err.println("Erreur : le fichier " + nomFichier + " est inexistant.");
			e.printStackTrace();
		}
		return false;
	}

	public void ecrireInitOptions() {// reinitialise le fichier Options.txt en ecrivant des valeurs par defaut
		try {
			File f = new File(this.chemin + "/Options.txt");
			try {
				f.createNewFile();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			FileWriter writer = new FileWriter(f, false);// ecrire en mode remplacement
			BufferedWriter bw = new BufferedWriter(writer);
			this.modeDaltonien=0;
			this.volumeEffetsSonores=6;
			this.volumeMusique=5;
			this.modePleinEcran=1;
			bw.write(String.valueOf(this.modeDaltonien));
			bw.newLine();
			bw.write(String.valueOf(this.volumeEffetsSonores));
			bw.newLine();
			bw.write(String.valueOf(this.volumeMusique));
			bw.newLine();
			bw.write(String.valueOf(this.modePleinEcran));
			bw.close();
			writer.close();
			System.out.println("Un nouveau fichier Options.txt a ete creer.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void ecrireOptions(String photoProfil, int modeDaltonien, int volumeEffetsSonores, int volumeMusique, int pleinEcran) {
		try {
			File f = new File(this.chemin + "/Options.txt");
			try {
					f.createNewFile();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			FileWriter writer = new FileWriter(f, false);// ecrire en mode remplacement
			BufferedWriter bw = new BufferedWriter(writer);
			bw.write(String.valueOf(modeDaltonien));
			bw.newLine();
			bw.write(String.valueOf(volumeEffetsSonores));
			bw.newLine();
			bw.write(String.valueOf(volumeMusique));
			bw.newLine();
			bw.write(String.valueOf(pleinEcran));
			bw.close();
			writer.close();
			System.out.println("Le fichier Options.txt a ete modifie.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}