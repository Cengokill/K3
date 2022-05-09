package Controleur;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import Modeles.*;

public class Jeu {
	public Partie partieEnCours;
	public int joueurCourant;
	public String cheminFichiers, cheminImages, photoProfil;
	private String chemin;
	public int volumeEffetsSonores, volumeMusique;

	public Jeu() {
		//lireOptions();
		Joueur j1 = new Joueur("Gaston");
		Joueur j2 = new Joueur("Mademoiselle Jeanne");
		partieEnCours=new Partie(j1,j2);
		this.joueurCourant=Aleatoire.genInt(0,1);//choix du joueur aléatoire
		while(this.partieEnCours.getTailleBasePieces()>0) {
			piocher();
		}
	}
	
	public void piocher() {
		if(joueurCourant==0) {
			this.partieEnCours.joueur1().piocherPiece(partieEnCours.getBasePieces());
		}else{
			this.partieEnCours.joueur2().piocherPiece(partieEnCours.getBasePieces());
		}
		changementJoueurCourant();
	}
	
	public void changementJoueurCourant() {
		if(this.joueurCourant==1) {
			this.joueurCourant=0;
		}else this.joueurCourant=1;
	}

	public void sauverPartie(String cheminFichier) {
		Fichiers files = new Fichiers(cheminFichier);
		files.ecrireSauvegarde(this);
	}

	public void chargerPartie(String cheminSauvegardes) {
		// Fichiers.lisSauvegarde();
	}

	public String interpreterReponse(int[] r) {
		String message = "";
		for (int i = 0; i < r.length; i++) {
			switch (r[i]) {
				case 2:
					message = "Erreur : le chemin " + cheminImages + " est inexistant.";
					break;
				case 3:
					message = "Erreur : le fichier" + photoProfil + " n'existe pas.";
					break;
				case 4:
					message = "Erreur : le fichier Options.txt n'a pas de valeurs correctes pour le volume.";
					break;
				case 5:
					message = "Erreur : le fichier Options.txt est corrompu. Il a ï¿½tï¿½ rï¿½initialisï¿½.";
					ecrireOptions();
					break;
				default:
					break;
			}
		}
		return message;
	}

	public void lireOptions() {// au tout premier lancement du jeu, le fichier Options.txt existe dï¿½jï¿½
		int[] renvoi = new int[5];
		// 2 : chemin non trouvï¿½
		// 3 : nom de fichier inexistant
		// 4 : lecture du volume impossible
		// 5 : fichier options.txt corrompu
		renvoi[0] = 5;
		int testChemin1, testChemin2, testFichier, testVolume;
		final int NB_LIGNES_OPTIONS = 6;// NB DE LIGNES DU FICHIER Options.txt = 6
		String nom_fichier = "Options.txt";
		ArrayList<String> tab = new ArrayList<String>();
		try {
			FileReader reader = new FileReader(chemin + nom_fichier);
			BufferedReader br = new BufferedReader(reader);
			String ligne_lue;
			while ((ligne_lue = br.readLine()) != null) {
				tab.add(ligne_lue);
			}
			if (tab.size() != NB_LIGNES_OPTIONS) {
				System.err.println("Erreur : le fichier " + nom_fichier + " est corrompu." + tab.size());
			} else {
				// Emplacement des images
				// Emplacement de tous les autres fichiers
				// Autre chose ?
				// Nom du fichier de la photo de profil du joueur
				// Volume de la musique
				// Volume des effets sonores
				this.cheminImages = tab.get(0);
				testChemin1 = testCheminExistant(cheminImages);
				this.cheminFichiers = tab.get(1);
				testChemin2 = testCheminExistant(cheminImages);
				this.photoProfil = tab.get(2);
				testFichier = testFichierExistant(photoProfil);
				this.volumeEffetsSonores = Integer.parseInt(tab.get(3));
				this.volumeMusique = Integer.parseInt(tab.get(4));
				testVolume = testVolume(volumeEffetsSonores, volumeMusique);
				renvoi[1] = testChemin1;
				renvoi[2] = testChemin2;
				renvoi[3] = testFichier;
				renvoi[4] = testVolume;
			}
		} catch (IOException e) {
			System.err.println("Erreur : le fichier Options.txt est corrompu.");
			e.printStackTrace();
		}
		interpreterReponse(renvoi);// pour l'affichage des message d'erreur ï¿½ l'ï¿½cran
	}

	public int testCheminExistant(String c) {// renvoie 0 si le chemin existe, sinon 2
		String nom_test = "test_fichier_existant";
		try {
			File myFile = new File(c + nom_test);
			if (myFile.createNewFile()) {
				System.out.println("Fichier test crï¿½er avec succï¿½s : " + myFile.getName());
				myFile.delete();
				return 0;
			}
		} catch (IOException e) {
			System.err.println("Erreur : le chemin " + c + " est inexistant.");
			e.printStackTrace();
		}
		return 2;
	}

	public int testVolume(int v1, int v2) {// renvoie 0 si le volume est compris entre 0 et 10, sinon renvoie 4
		int r = 0;
		if (v1 >= 10 || v1 < 0) {
			this.volumeEffetsSonores = 6;
			r = 4;
		}
		if (v2 >= 10 || v2 < 0) {
			this.volumeMusique = 4;
			r = 4;
		}
		return r;
	}

	public int testFichierExistant(String nomFichier) {// renvoie 0 si le fichier existe, sinon 3
		try {
			File myFile = new File(this.cheminImages + nomFichier);
			return 0;
		} catch (Exception e) {
			System.err.println("Erreur : le fichier " + nomFichier + " est inexistant.");
			e.printStackTrace();
		}
		return 3;
	}

	public void ecrireOptions() {// rï¿½initialise le fichier Options.txt en ï¿½crivant des valeurs par dï¿½faut
		String userHome = System.getProperty("user.home");
		String desktop = userHome + "/Desktop/Jeu_K3";
		new File(desktop).mkdirs();
		try {
			File f = new File(desktop + "/Options.txt");
			try {
				f.createNewFile();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			FileWriter writer = new FileWriter(f, false);// ï¿½crire en mode remplacement
			BufferedWriter bw = new BufferedWriter(writer);
			bw.write(this.cheminImages);
			bw.newLine();
			bw.write(this.cheminFichiers);
			bw.newLine();
			bw.write(this.photoProfil);
			bw.newLine();
			bw.write(this.volumeEffetsSonores);
			bw.newLine();
			bw.write(this.volumeMusique);
			bw.close();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int getJoueurCourant() {
		return this.joueurCourant;
	}
}