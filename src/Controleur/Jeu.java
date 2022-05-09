package Controleur;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

import Modeles.*;

public class Jeu {
	private Joueur j1;
	private Joueur j2;
	private ArrayList<Piece> basePieces;// pieces disponibles � se partager entre les joueurs, uniquement � la cr�ation
										// du jeu
	private PyramideMontagne baseMontagne;// base de la montagne
	private LinkedList<Coup> historique;
	public String cheminFichiers, cheminImages, photoProfil;
	public int volumeEffetsSonores, volumeMusique;
	Piece pBleu;
	Piece pVert;
	Piece pJaune;
	Piece pRouge;
	Piece pNoir;
	Piece pBlanc;
	Piece pNaturel;
	String chemin;

	public Jeu() {
		lireOptions();
		basePieces = new ArrayList<Piece>();
		pBleu = new Piece(Couleurs.BLEU);
		pVert = new Piece(Couleurs.VERT);
		pJaune = new Piece(Couleurs.JAUNE);
		pRouge = new Piece(Couleurs.ROUGE);
		pNoir = new Piece(Couleurs.NOIR);
		pBlanc = new Piece(Couleurs.BLANC);
		pNaturel = new Piece(Couleurs.NATUREL);
		initialiserSac();
		j1 = new Joueur("Gaston");
		j2 = new Joueur("Mademoiselle Jeanne");
		initCampJoueur(j1);
		initCampJoueur(j2);
		initBaseMontagne();
		historique = new LinkedList<Coup>();
	}

	public void initialiserSac() {// ajoute toutes les pi�ces au sac
		int nb_pieces_par_couleur = 9;
		int nb_naturels = 6;
		int nb_blancs = 4;
		for (int i = 0; i < nb_pieces_par_couleur; i++) {
			basePieces.add(pBleu);
			basePieces.add(pVert);
			basePieces.add(pJaune);
			basePieces.add(pRouge);
			basePieces.add(pNoir);
		}
		for (int i = 0; i < nb_naturels; i++) {
			basePieces.add(pNaturel);
		}
		for (int i = 0; i < nb_blancs; i++) {
			basePieces.add(pBlanc);
		}
		Collections.shuffle(basePieces);// m�lange les pi�ces � piocher pour les joueurs
		System.out.println("sac initialis�. Taille : " + basePieces.size());
	}

	public void initCampJoueur(Joueur jou) {// cr�ation du camps du joueur j
		for (int i = 0; i < 6; i++) {// hauteur
			for (int j = 0; j < 6 - i; j++) {// largeur
				Piece element = basePieces.get(0);
				Position pos = new Position(j, i);
				PiecePyramide pp = new PiecePyramide(element, pos);
				jou.getCamp().empiler(pp);
				basePieces.remove(0);
				Piece pAffich = jou.getCamp().getPiece(pos);
				// System.out.println("piece "+pos.x+","+pos.y+" ajout�e au camp de
				// "+jou.getNom()+" : "+pAffich.toString());
			}
		}
		System.out.println(jou.getCamp().toString());
		System.out.println("Camp de " + jou.getNom() + " initialis�.");
	}

	public void initBaseMontagne() {// cr�ation de la base de la montagne constitu�e de 9 pi�ces
		baseMontagne = new PyramideMontagne(9, 9);// 9 �tages, 9 pi�ces au dernier �tage
		for (int i = 0; i < 9; i++) {
			Piece element = basePieces.get(0);
			Position pos = new Position(i, 0);
			PiecePyramide pp = new PiecePyramide(element, pos);
			baseMontagne.empiler(pp);
			basePieces.remove(0);
		}
		System.out.println("Base de la montagne initialis�e. Taille : " + baseMontagne.getHauteur());
		System.out.println("Le sac a maintenant une taille de " + basePieces.size() + " pi�ces.");
		System.out.println(baseMontagne.toString());
	}

	public LinkedList<Coup> CoupsJouables(Joueur j) {// renvoie les pieces et la pos jouables du joueur
		PyramideJoueur p = j.getCamp();
		LinkedList<Coup> coupsPosables = new LinkedList<Coup>();
		Piece p1, p2;
		Position pos1, pos2;
		Coup c;
		LinkedList<PiecePyramide> piecesBase = baseMontagne.piecesPosables();
		LinkedList<PiecePyramide> piecesJoueur = p.piecesJouables();
		for (int i = 0; i < piecesJoueur.size(); i++) {// pour chaque piece du joueur
			PiecePyramide pieceJoueur = piecesJoueur.get(i);
			p1 = pieceJoueur.getPiece();
			for (PiecePyramide pp : piecesBase) {
				p2 = pp.getPiece();
				if (p1.getColor() == p2.getColor()) {
					pos1 = pieceJoueur.getPos();
					pos2 = pp.getPos();
					c = new Coup(p1, pos1, pos2);
					coupsPosables.add(c);
				}
			}
		}
		return coupsPosables;
	}

	public LinkedList<Coup> getHist() {
		return this.historique;
	}

	public PyramideMontagne getBaseMontagne() {
		return this.baseMontagne;
	}

	public boolean volerPiece(Joueur voleur, Joueur victime, PiecePyramide pp) {// voleur vole une piece au joueur
																				// victime
		PyramideJoueur campVictime = victime.getCamp();
		boolean b = campVictime.retirer(pp);

		if (b) {
			voleur.addPieceVolee(pp.getPiece());// ajout de la piece volee a la liste des pieces volees du joueur voleur
			return b;
		} else {// si impossible de retirer la piece
			System.err.println("La pi�ce de peut pas �tre vol�e.");
			return b;
		}
	}

	public void afficherCoups(LinkedList<PiecePyramide> arr) {
		int taille = arr.size();
		for (int i = 0; i < taille; i++) {
			Piece pi = arr.get(i).getPiece();
			Position pos = arr.get(i).getPos();
			System.out.println(pi.toString() + ":" + pos.toString());
		}
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
					message = "Erreur : le fichier Options.txt est corrompu. Il a �t� r�initialis�.";
					ecrireOptions();
					break;
				default:
					break;
			}
		}
		return message;
	}

	public void lireOptions() {// au tout premier lancement du jeu, le fichier Options.txt existe d�j�
		int[] renvoi = new int[5];
		// 2 : chemin non trouv�
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
		interpreterReponse(renvoi);// pour l'affichage des message d'erreur � l'�cran
	}

	public int testCheminExistant(String c) {// renvoie 0 si le chemin existe, sinon 2
		String nom_test = "test_fichier_existant";
		try {
			File myFile = new File(c + nom_test);
			if (myFile.createNewFile()) {
				System.out.println("Fichier test cr�er avec succ�s : " + myFile.getName());
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
			String path = myFile.getPath();
			return 0;
		} catch (Exception e) {
			System.err.println("Erreur : le fichier " + nomFichier + " est inexistant.");
			e.printStackTrace();
		}
		return 3;
	}

	public void ecrireOptions() {// r�initialise le fichier Options.txt en �crivant des valeurs par d�faut
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
			FileWriter writer = new FileWriter(f, false);// �crire en mode remplacement
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

	public void jouer() {

	}

	public Joueur joueur1() {
		return j1;
	}

	public Joueur joueur2() {
		return j2;
	}
}