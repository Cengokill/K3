package Controleur;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
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
	private final int NB_LIGNES_OPTIONS = 5;// NB DE LIGNES DU FICHIER Options.txt = 6
	private final int TAILLE_CAMP_JOUEUR=21;

	public Jeu() {
		//lireOptions();
		Joueur j1 = new Joueur("Gaston");
		Joueur j2 = new Joueur("Mademoiselle Jeanne");
		partieEnCours = new Partie(j1, j2);
		this.joueurCourant = Aleatoire.genInt(0, 1);// choix du joueur aléatoire
		//initialisation des blancs et des naturels aux joueurs
		partieEnCours.distribuerBlancEtNaturels();
		while (partieEnCours.joueur1().getTaillePiecesPiochees() < TAILLE_CAMP_JOUEUR || partieEnCours.joueur2().getTaillePiecesPiochees() < TAILLE_CAMP_JOUEUR) {
			piocher();
		}
		while (partieEnCours.joueur1().getTaillePiecesPiochees()>0 && partieEnCours.joueur2().getTaillePiecesPiochees()>0) {
			partieEnCours.joueur1().placerPieces();
			partieEnCours.joueur2().placerPieces();
		}
		
		partieEnCours.joueur1().getCamp().retirer((new Position(5,0)));
		partieEnCours.joueur1().getCamp().empiler(new PiecePyramide(new Piece(Couleurs.BLANC),new Position(5,0)));
		
		while(!partieEnCours.estPartieFinie(joueurCourant)) {
			afficherBaseMontagne();
			faireJouerActeurs();
		}
		partieVictoire();
	}
	
	public void afficherBaseMontagne() {
		System.out.println(partieEnCours.getBaseMontagne().toString());
	}
	
	public void faireJouerActeurs() {
		Coup coupDemande;
		ArrayList<Coup> cJ=new ArrayList<Coup>();
		Acteur jCourant;
		if (joueurCourant == 0) {
			jCourant=partieEnCours.joueur1();
		}else {
			jCourant=partieEnCours.joueur2();
		}
		System.out.println(jCourant.getCamp().toString());
		cJ=this.partieEnCours.coupsJouables(jCourant);
		coupDemande=jCourant.jouer(cJ);
		jCourant.getCamp().retirer(coupDemande.getPosJ());
		if(coupDemande.getPosBase()!=null) {//si le joueur ne choisit pas de jouer une piece BLANCHE
			this.partieEnCours.getBaseMontagne().empiler(new PiecePyramide(coupDemande.getPiece(),coupDemande.getPosBase()));
		}else {
			System.out.println("Vous avez decide de passer votre tour !");
		}
		changementJoueurCourant();
	}
	
	public void partieVictoire() {
		System.out.println("Fin de la partie.");
		if (joueurCourant == 0) {
			System.out.print(this.partieEnCours.joueur2().getNom());
		}else {
			System.out.print(this.partieEnCours.joueur1().getNom());
		}
		System.out.println(" a gagné !");
	}

	public void piocher() {
		Piece p;
		if (joueurCourant == 0) {
			p = this.partieEnCours.joueur1().piocherPiece(partieEnCours.getBasePieces());
			this.partieEnCours.joueur1().addPiecePiochee(p);
		} else {
			p = this.partieEnCours.joueur2().piocherPiece(partieEnCours.getBasePieces());
			this.partieEnCours.joueur2().addPiecePiochee(p);
		}
		changementJoueurCourant();
	}

	public void changementJoueurCourant() {
		if (this.joueurCourant == 1) {
			this.joueurCourant = 0;
		} else
			this.joueurCourant = 1;
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
					message = "Erreur : le fichier Options.txt est corrompu. Il a ete reinitialise.";
					ecrireOptions();
					break;
				default:
					break;
			}
		}
		return message;
	}

	public void lireOptions() {// au tout premier lancement du jeu, le fichier Options.txt existe deja
		String nom_fichier = "Options.txt";
		String userHome = System.getProperty("user.home");
		this.chemin = userHome + "/Desktop/Jeu_K3/";
		if(testFichierExistant(this.chemin+nom_fichier)!=0) {
			ecrireOptions();
			return;
		}
		int[] renvoi = new int[5];
		// 2 : chemin non trouve
		// 3 : nom de fichier inexistant
		// 4 : lecture du volume impossible
		// 5 : fichier options.txt corrompu
		renvoi[0] = 5;
		int testChemin1, testChemin2, testFichier, testVolume;
		ArrayList<String> tab = new ArrayList<String>();
			try {
				FileReader reader = new FileReader(chemin + nom_fichier);
				BufferedReader br = new BufferedReader(reader);
				String ligne_lue;
				while ((ligne_lue = br.readLine()) != null) {
					tab.add(ligne_lue);
				}
				if (tab.size() != this.NB_LIGNES_OPTIONS) {
					System.err.println("Erreur : le fichier " + nom_fichier + " a ete modifie. Il contient " + tab.size()+" lignes.");
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
			}
			catch (Exception e) {
				System.err.println("Erreur : le fichier Options.txt est corrompu.");
				e.printStackTrace();
			}
		interpreterReponse(renvoi);// pour l'affichage des message d'erreur a l'ecran
	}

	public int testCheminExistant(String c) {// renvoie 0 si le chemin existe, sinon 2
		String nom_test = "test_fichier_existant";
		try {
			File myFile = new File(c + nom_test);
			if (myFile.createNewFile()) {
				System.out.println("Fichier test creer avec succes : " + myFile.getName());
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
			File myFile = new File(nomFichier);
			if(myFile.isFile()) {
				System.out.println("testFichierExistant = 0");
				return 0;
			}
		} catch (Exception e) {
			System.err.println("Erreur : le fichier " + nomFichier + " est inexistant.");
			e.printStackTrace();
		}
		return 3;
	}

	public void ecrireOptions() {// reinitialise le fichier Options.txt en ecrivant des valeurs par defaut
		new File(this.chemin).mkdirs();
		try {
			File f = new File(this.chemin + "/Options.txt");
			try {
				f.createNewFile();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			FileWriter writer = new FileWriter(f, false);// ecrire en mode remplacement
			BufferedWriter bw = new BufferedWriter(writer);
			bw.write(this.chemin+"Images/");
			bw.newLine();
			bw.write(this.chemin+"Fichiers/");
			bw.newLine();
			bw.write("photoProfil.jpg");
			bw.newLine();
			bw.write("8");
			bw.newLine();
			bw.write("5");
			bw.close();
			writer.close();
			System.out.println("Fichier Options.txt creer avec succes.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int getJoueurCourant() {
		return this.joueurCourant;
	}
}