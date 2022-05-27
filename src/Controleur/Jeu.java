package Controleur;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;

import java.time.LocalTime;

import Modeles.*;
import Reseau.*;
import Vue.*;
import Vue.Phase1.*;
import Vue.TexturePack.LoadTexture;

public class Jeu {
	public Partie partieEnCours;
	public String nomActeur1, nomActeur2;
	public String chemin, cheminStats, cheminImages, cheminSauvegardes, photoProfil;
	private int num_tour, valeur_paire, typeActeurs, difficulte1, difficulte2, vitesseIA;
	public int volumeEffetsSonores, volumeMusique, modeDaltonien;
	private final int NB_LIGNES_OPTIONS = 4;// NB DE LIGNES DU FICHIER Options.txt = 6
	private final int TAILLE_CAMP_JOUEUR=21;
	private SoundPlayer simpleSoundPlayerMusic, simpleSoundPlayerSon;
	public Plateau plateau;
	public JFrame window;
	public Phase1Panel panel;
	private int NB_LIGNES_SAUVEGARDE=43;
	public LoadTexture textures;

	public Jeu(JFrame fenetrePrincipale, InitPartie partieInit, LoadTexture t) {
		this.textures=t;
		this.window=fenetrePrincipale;
		this.chemin=System.getProperty("user.home")+ "/Desktop/Jeu_K3/";
		this.cheminStats=chemin+"Statistiques/";
		this.cheminImages=chemin+"Images/";
		this.cheminSauvegardes=chemin+"Sauvegardes/";
		//creer les dossier du jeu s'il n'existent pas
		new File(this.chemin).mkdirs();
		new File(this.cheminStats).mkdirs();
		new File(this.cheminImages).mkdirs();
		new File(this.cheminSauvegardes).mkdirs();
		lireOptions();
		//initialiser le son
		this.simpleSoundPlayerMusic = new SoundPlayer(0);
		this.simpleSoundPlayerSon = new SoundPlayer(8);
		this.simpleSoundPlayerMusic.setNumSon(43);
		this.simpleSoundPlayerMusic.loopSon();
		//initialiser les parties graphiques
		plateau = new Plateau();
		setParametresPartie(partieInit.modeDeJeu,partieInit.difficulteIA1,partieInit.difficulteIA2,2000,partieInit.nomJoueur1,partieInit.nomJoueur2);
		//lancer une partie
		/*
		if(partieInit.nomFichierCharge!=null) {
			if(!chargerPartie(cheminSauvegardes+partieInit.nomFichierCharge)) {
				System.err.println("Erreur de lecture de la sauvegarde de la partie.");
				System.exit(0);
				setParametresPartie(partieInit.modeDeJeu,partieInit.difficulteIA1,partieInit.difficulteIA2,2000,partieInit.nomJoueur1,partieInit.nomJoueur2);
			}
		}else {
			setParametresPartie(partieInit.modeDeJeu,partieInit.difficulteIA1,partieInit.difficulteIA2,2000,partieInit.nomJoueur1,partieInit.nomJoueur2);
		}
		*/
		lancerPartie();
		//modifVolume();
	}
	
	public static String[] listerSauvegardes(String chemin) {
		File f = new File(chemin);
		String[] sauvegardesListe = f.list();
		return sauvegardesListe;
	}
	
	public void modifVolume() {
		Slider slider = new Slider();
		this.simpleSoundPlayerMusic = new SoundPlayer(this.volumeMusique);
		this.simpleSoundPlayerSon = new SoundPlayer(this.volumeEffetsSonores);
		ecrireOptions(this.photoProfil, this.modeDaltonien, this.volumeEffetsSonores, this.volumeMusique);
	}
	
	public void lancerPhase1() {
		this.panel = new Phase1Panel(this.window, this.partieEnCours, this.textures);
		this.window.setContentPane(panel);
		//this.panel.addMouseListener(new ecouteurClick(panel));
		window.paintAll(window.getGraphics());
	}
	
	public void setParametresPartie(int t, int d1, int d2, int v, String nom1, String nom2) {
		this.typeActeurs=t;// IA ou joueur
		this.difficulte1=d1;// si 2 AI, difficulte IA 1
		this.difficulte2=d2;// si 2 AI, difficulte IA 2
		this.nomActeur1=nom1;
		this.nomActeur2=nom2;
		this.vitesseIA=v;
	}
	
	public void lancerPartie() {
		//this.simpleSoundPlayerMusic.stopSound();//stopper la mussique d'accueil du jeu
		this.simpleSoundPlayerSon.setNumSon(29);//son de lancement de partie
		this.simpleSoundPlayerSon.jouerSon();
		//this.simpleSoundPlayerMusic.setNumSon(?); MUSIQUE DE LA PARTIE
		//this.simpleSoundPlayerMusic.jouerSon();
		if(this.typeActeurs==0) {//Joueur contre joueur
			lancerPartieJcJ(this.nomActeur1, this.nomActeur2, 0);
		}
		else if(this.typeActeurs==1) {//IA contre joueur
			lancerPartieJcIA(this.nomActeur1, this.nomActeur2, 0);
		}else {//IA contre IA
			Acteur j1 = new IAActeur(this.nomActeur1, this.difficulte1, 0);
			Acteur j2 = new IAActeur(this.nomActeur2, this.difficulte2, 1);
			this.partieEnCours = new Partie(j1, j2, 1000);
			this.partieEnCours.setCheminStats(this.cheminStats);
			this.num_tour=1;
			this.valeur_paire=0;
			//UTILISER this.difficulte
			//PHASE 1
			initPhase1();
			jouerPhase1();//a modifier
			//PHASE 2
			jouerPhase2();//a modifier
		}
	}
	
	public void lancerPartieEnLigne(String nomJ, int difficulte) throws IOException {
		Serveur serveur = new Serveur(nomJ, difficulte);
		int dateAncienne = LocalTime.now().getSecond();
		int dateCourante;
		while(!serveur.getReady()) {
			dateCourante=LocalTime.now().getSecond();
			if(dateAncienne-dateCourante>10) {
				System.err.println("Temp de connection depasse.");
				return;
			}
		}
		//creation de la partie
		Joueur j1 = new Joueur(nomJ);
		Joueur j2 = new Joueur(serveur.getClientName());
		this.partieEnCours = new Partie(j1, j2, 500);
		this.partieEnCours.setCheminStats(cheminStats);
		this.num_tour=1;
		this.valeur_paire=0;
		//PHASE 1
		jouerPhase1();
		//PHASE 2
		jouerPhase2();
	}
	
	public void rejoindrePartieEnLigne(String nomJ) throws IOException {
		Client client = new Client(nomJ);
	}
	
	public void lancerPartieJcIA(String nomJ1, String nomJ2, int numPartie) {
		Acteur j1 = new IAActeur(nomJ2, 1, 0);
		Acteur j2 = new Joueur(nomJ1);
		this.partieEnCours = new Partie(j1, j2, numPartie);
		this.partieEnCours.setCheminStats(cheminStats);
		this.num_tour=1;
		this.valeur_paire=0;
		//PHASE 1
		initPhase1();
		jouerPhase1();
		//PHASE 2
		jouerPhase2();
	}
	
	public void lancerPartieJcJ(String nomJ1, String nomJ2, int numPartie) {
		Joueur j1 = new Joueur(nomJ1);
		Joueur j2 = new Joueur(nomJ2);
		this.partieEnCours = new Partie(j1, j2, numPartie);
		this.partieEnCours.setCheminStats(cheminStats);
		this.num_tour=1;
		this.valeur_paire=0;
		//PHASE 1
		initPhase1();
		jouerPhase1();
		//PHASE 2
		jouerPhase2();
	}
	
	public void initPhase1() {
		//initialisation des blancs et des naturels aux joueurs
		this.partieEnCours.distribuerBlancEtNaturels();
		//initialisation des pieces des deux joueurs
		while (this.partieEnCours.joueur1().getTaillePiecesPiochees() < this.TAILLE_CAMP_JOUEUR || this.partieEnCours.joueur2().getTaillePiecesPiochees() < this.TAILLE_CAMP_JOUEUR) {
			piocher();
		}
	}
		
	public void jouerPhase1() {
		long tempsConstructionJoueur1;
		long tempsConstructionJoueur2;
		long debut;
		int temps=0;
		if(this.typeActeurs==2) {
			temps=this.vitesseIA;
		}
		ArrayList<PiecePyramide> arr;
		Acteur acteurCourant;
		lancerPhase1();
		timer(1000);
		for(int i=0; i<2; i++) {
			if(this.partieEnCours.getJoueurCourant()==0) {
				acteurCourant=this.partieEnCours.joueur1();
			}else {
				acteurCourant=this.partieEnCours.joueur2();
			}
			debut=System.currentTimeMillis();
			while (acteurCourant.getTaillePiecesPiochees()>0 || !acteurCourant.valideCamp) {
				//chaque joueur doit choisir la piece a empiler sur sa pioche
				arr = acteurCourant.phase1(this.partieEnCours);
				timer(10);
				if(!arr.isEmpty()){
					for(PiecePyramide p : arr) {
						timer((int)(0.3*temps));
						if(acteurCourant.getCamp().empilerPhase1(p)) {//pas de verif pieces porteuse
							acteurCourant.removePiecePiochee(p.getPiece());
							this.panel.repaint();
							if((this.typeActeurs==2 && this.vitesseIA>400) || this.typeActeurs!=2) {
								this.simpleSoundPlayerSon.setNumSon(1);//son de lancement de partie
								this.simpleSoundPlayerSon.jouerSon();
							}
						}
					}
				}
			}
			acteurCourant.setTempsConstruction(System.currentTimeMillis()-debut);
			timer(1200);
			this.partieEnCours.changementJoueurCourant();
			this.panel.repaint();
		}
	}
	
	public void jouerPhase2() {
		int temps=200;
		if(this.typeActeurs==2) {
			temps=this.vitesseIA;
		}
		System.out.println("Les deux camps des joueurs ont ete creer !");
		System.out.println("================ Deuxieme phase du jeu ================");
		while(!this.partieEnCours.estPartieFinie()) {//explicite
			panel.repaint();
			if((this.typeActeurs==2 && this.vitesseIA>400) || this.typeActeurs!=2) {
				this.simpleSoundPlayerSon.setNumSon(35);
				this.simpleSoundPlayerSon.jouerSon();
			}
			faireJouerActeurs();//fait jouer les acteurs chacun leur tour
			timer(temps);
		}
		partieVictoire();//affichage uniquement
	}
	
	public void afficherBaseMontagne() {
		System.out.println(this.partieEnCours.getBaseMontagne().toString());
	}
	
	public void afficherTour() {	
		System.out.println("================ Tour "+this.num_tour+" ================");
		this.num_tour++;
	}
	
	public void faireJouerActeurs() {
		Coup coupDemande;
		ArrayList<Coup> cJ=new ArrayList<Coup>();
		Acteur jCourant, jPrecedent;
		
		if(this.valeur_paire%2==0) {//affichage du numero du tour actuel
			afficherTour();
			this.valeur_paire++;
		}
		//attribution du joueur courant et precedent
		if (this.partieEnCours.getJoueurCourant() == 0) {
			jCourant=this.partieEnCours.joueur1();
			jPrecedent=this.partieEnCours.joueur2();
		}else {
			jCourant=this.partieEnCours.joueur2();
			jPrecedent=this.partieEnCours.joueur1();
		}
		//affichage dans la console de la partie
		afficherBaseMontagne();
		System.out.println("Votre camp :");
		System.out.println(jCourant.getCamp().toString());
		System.out.println("Vos pieces volees : "+jCourant.toStringPiecesVolees());
		System.out.println("Camp adverse :");
		System.out.println(jPrecedent.getCamp().toString());
		System.out.println("Ses pieces volees : "+jPrecedent.toStringPiecesVolees());
		System.out.println(jCourant.getNom()+", veuillez jouer un coup :");

		//fait jouer un joueur
		jouer();
	}
	
	public void jouer() {
		int temps=1000;
		if(this.typeActeurs==2) {
			temps=this.vitesseIA;
		}		
		Acteur jCourant, jPrecedent;
		if (this.partieEnCours.joueurCourant == 0) {
			jCourant = this.partieEnCours.joueur1();
			jPrecedent = this.partieEnCours.joueur2();
		} else {
			jCourant = this.partieEnCours.joueur2();
			jPrecedent = this.partieEnCours.joueur1();
		}
		ArrayList<Coup> cJ = this.partieEnCours.coupsJouables(jCourant);
		Coup coupDemande = jCourant.jouer(cJ, this.partieEnCours);// le joueur courant a choisi un coup a jouer
		this.partieEnCours.addCoupHist(coupDemande);// ajout du coup a l'historique
		if (coupDemande.getPosJ() != null) {// si le joueur courant ne joue pas une piece volee
			jCourant.getCamp().retirer(coupDemande.getPosJ());// retire la piece jouee du camp du joueur courant
		} else {// si le joueur courant decide de jouer une de ses pieces volees
			jCourant.retirerPieceVolee(coupDemande.getPiece());
		}
		timer(temps);
		panel.repaint();
		if (coupDemande.getPosBase() != null) {// si le joueur ne choisit pas de jouer une piece BLANCHE
			this.partieEnCours.getBaseMontagne().empiler(new PiecePyramide(coupDemande.getPiece(), coupDemande.getPosBase()));
			if((this.typeActeurs==2 && this.vitesseIA>400) || this.typeActeurs!=2) {
				this.simpleSoundPlayerSon.setNumSon(4);
				this.simpleSoundPlayerSon.jouerSon();
			}
			if (coupDemande.getPiece().getColor().equals(Couleurs.NATUREL)) {
				if((this.typeActeurs==2 && this.vitesseIA>400) || this.typeActeurs!=2) {
					this.simpleSoundPlayerSon.setNumSon(7);
					this.simpleSoundPlayerSon.jouerSon();
				}
			}
			timer(temps);
			panel.repaint();
			if (this.partieEnCours.getBaseMontagne().estPorteursMemeCouleur(coupDemande.getPosBase())) {// si vol possible
				if((this.typeActeurs==2 && this.vitesseIA>400) || this.typeActeurs!=2) {
					this.simpleSoundPlayerSon.setNumSon(23);
					this.simpleSoundPlayerSon.jouerSon();
				}
				Coup vol = this.partieEnCours.volerPiece(jPrecedent, jCourant);
				if (vol != null) {//si le joueur vole une piece
					if((this.typeActeurs==2 && this.vitesseIA>400) || this.typeActeurs!=2) {
						this.simpleSoundPlayerSon.setNumSon(26);
						this.simpleSoundPlayerSon.jouerSon();
					}
					this.partieEnCours.addCoupHist(vol);
				}
				timer(temps);
				panel.repaint();
			}
		} else {// joue une piece BLANCHE
			jCourant.addBlancJoue();
			if((this.typeActeurs==2 && this.vitesseIA>400) || this.typeActeurs!=2) {
				this.simpleSoundPlayerSon.setNumSon(10);
				this.simpleSoundPlayerSon.jouerSon();
			}
			timer(temps);
			panel.repaint();
			System.out.println("Vous avez decide de passer votre tour !");
		}
		partieEnCours.changementJoueurCourant();
	}
	
	public void partieVictoire() {
		this.simpleSoundPlayerSon.setNumSon(32);
		this.simpleSoundPlayerSon.jouerSon();
		afficherBaseMontagne();
		System.out.println(this.partieEnCours.joueur1().getCamp().toString());
		System.out.println(this.partieEnCours.joueur2().getCamp().toString());
		System.out.println("Fin de la partie.");
		if (partieEnCours.getJoueurCourant() == 0) {
			System.out.print(this.partieEnCours.joueur2().getNom());
		}else {
			System.out.print(this.partieEnCours.joueur1().getNom());
		}
		System.out.println(" a gagné !");
	}

	public void piocher() {
		Piece p;
		if (partieEnCours.getJoueurCourant() == 0) {
			p = this.partieEnCours.joueur1().piocherPiece(partieEnCours.getBasePieces());
			this.partieEnCours.joueur1().addPiecePiochee(p);
		} else {
			p = this.partieEnCours.joueur2().piocherPiece(partieEnCours.getBasePieces());
			this.partieEnCours.joueur2().addPiecePiochee(p);
		}
		partieEnCours.changementJoueurCourant();
	}

	public void lireOptions() {// au tout premier lancement du jeu, le fichier Options.txt existe deja
		boolean[] renvoi = new boolean[4];
		String nom_fichier = "Options.txt";
		if(!testFichierExistant(this.chemin+nom_fichier)) {
			ecrireInitOptions();
			renvoi[0]=false;
			return;
		}
		// 0 : test fichier options
		// 1 : testPhotoProfil
		// 2 : testDaltonien
		// 3 : testVolume
		// 4 : testSons
		boolean testPhotoProfil, testDaltonien, testVolume;
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
					renvoi[0]=false;
					return;
				} else {
					// Nom du fichier de la photo de profil du joueur
					// Mode daltonien oui/non
					// Volume des effets sonores
					// Volume de la musique
					this.photoProfil = tab.get(0);
					testPhotoProfil = testFichierExistant(chemin+photoProfil+".jpg")||testFichierExistant(chemin+photoProfil+".png");
					this.modeDaltonien=Integer.parseInt(tab.get(1));
					testDaltonien = verifDaltonien(this.modeDaltonien);
					this.volumeEffetsSonores = Integer.parseInt(tab.get(2));
					this.volumeMusique = Integer.parseInt(tab.get(3));
					testVolume = verifVolume(this.volumeEffetsSonores, this.volumeMusique);
					renvoi[1] = testPhotoProfil;
					renvoi[2] = testDaltonien;
					renvoi[3] = testVolume;
				}
			}
			catch (Exception e) {
				System.err.println("Erreur : le fichier Options.txt est corrompu.");
				e.printStackTrace();
			}
	}
	
	public boolean verifDaltonien(int v) {// renvoie true si v est compris entre 0 et 1
		if(v!=0 && v!=1) {
			this.modeDaltonien=0;
			return false;
		}else {
			this.modeDaltonien=v;
			return true;
		}
	}

	public boolean verifVolume(int v1, int v2) {// renvoie true si le volume est compris entre 0 et 10
		boolean b;
		if (v1 > 10 || v1 < 0) {
			this.volumeEffetsSonores = 6;
			b=false;
		}else {
			this.volumeEffetsSonores = v1;
			b=true;
		}
		if (v2 > 10 || v2 < 0) {
			this.volumeMusique = 4;
			b=b&&false;
		}else {
			this.volumeMusique = v2;
			b=b&&true;
		}
		return b;
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
			// Nom du fichier de la photo de profil du joueur
			// Mode daltonien oui/non
			// Volume des effets sonores
			// Volume de la musique
			this.photoProfil="Ma_photo_de_profil";
			this.modeDaltonien=0;
			this.volumeEffetsSonores=6;
			this.volumeMusique=4;
			bw.write(this.photoProfil);
			bw.newLine();
			bw.write(String.valueOf(this.modeDaltonien));
			bw.newLine();
			bw.write(String.valueOf(this.volumeEffetsSonores));
			bw.newLine();
			bw.write(String.valueOf(this.volumeMusique));
			bw.close();
			writer.close();
			System.out.println("Un nouveau fichier Options.txt a ete creer.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void ecrireOptions(String photoProfil, int modeDaltonien, int volumeEffetsSonores, int volumeMusique) {
		try {
			File f = new File(this.chemin + "/Options.txt");
			try {
					f.createNewFile();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			FileWriter writer = new FileWriter(f, false);// ecrire en mode remplacement
			BufferedWriter bw = new BufferedWriter(writer);
			// Nom du fichier de la photo de profil du joueur
			// Mode daltonien oui/non
			// Volume des effets sonores
			// Volume de la musique
			bw.write(photoProfil);
			bw.newLine();
			bw.write(String.valueOf(modeDaltonien));
			bw.newLine();
			bw.write(String.valueOf(volumeEffetsSonores));
			bw.newLine();
			bw.write(String.valueOf(volumeMusique));
			bw.close();
			writer.close();
			System.out.println("Le fichier Options.txt a ete modifie.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	public static void timer(int t) {
		try {
		    Thread.sleep(t);
		} catch (InterruptedException ie) {
		    // ...
		}
	}
	
	public void sauvegarderUnePartie() {
		this.partieEnCours.sauvegarderPartie(this.cheminSauvegardes+"partie1.txt");
	}
	
	public boolean chargerPartie(String nomFichier) {
		ArrayList<String> arr=new ArrayList<String>();
		ArrayList<String> tab = new ArrayList<String>();
		ArrayList<String> lectureBase = new ArrayList<String>();
		ArrayList<String> lectureBaseJoueur1 = new ArrayList<String>();
		String lecturePiocheJoueur1, lecturePiocheJoueur2;
		ArrayList<String> lectureBaseJoueur2 = new ArrayList<String>();
		String piecesVoleesJ1, piecesVoleesJ2;
		String fichier="";
		FileReader reader;
		BufferedReader br;
		String ligne_lue;
		String nomJ1, nomJ2;
		int jCourant=0;
		int numPartie=0;
		int difficulteJ1=0;
		int difficulteJ2=0;
		int nbBlancsJouesJ1=0;
		int nbBlancsJouesJ2=0;
		int nbVolsJ1=0;
		int nbVolsJ2=0;
		int nbMauvaisCoupsJ1=0;
		int nbMauvaisCoupsJ2=0;
		Acteur j1;
		Acteur j2;
		try {
			fichier=nomFichier;
			reader = new FileReader(fichier);
			br = new BufferedReader(reader);
			while ((ligne_lue = br.readLine()) != null) {
				tab.add(ligne_lue);
			}
			br.close();
			if(tab.size()!=this.NB_LIGNES_SAUVEGARDE) {
				System.err.println("Erreur : le fichier de sauvegarde ne contient pas le bon nombre de lignes."+tab.size());
				return false;
			}
			lectureBase.add(tab.get(1));
			lectureBase.add(tab.get(2));
			lectureBase.add(tab.get(3));
			lectureBase.add(tab.get(4));
			lectureBase.add(tab.get(5));
			lectureBase.add(tab.get(6));
			lectureBase.add(tab.get(7));
			lectureBase.add(tab.get(8));
			lectureBase.add(tab.get(9));
			jCourant=Integer.parseInt((tab.get(11).split(":")[1]));
			numPartie=Integer.parseInt((tab.get(12).split(":")[1]));
			// JOUEUR 1
			difficulteJ1=Integer.parseInt((tab.get(14).split(":")[1]));
			nomJ1=(tab.get(15).split(":")[1]);
			lectureBaseJoueur1.add(tab.get(16));
			lectureBaseJoueur1.add(tab.get(17));
			lectureBaseJoueur1.add(tab.get(18));
			lectureBaseJoueur1.add(tab.get(19));
			lectureBaseJoueur1.add(tab.get(20));
			lectureBaseJoueur1.add(tab.get(21));
			if(tab.get(23).split(":").length<2) {
				lecturePiocheJoueur1="";
			}else {
				lecturePiocheJoueur1=tab.get(23).split(":")[1];
			}
			if(tab.get(24).split(":").length<2) {
				piecesVoleesJ1="";
			}else {
				piecesVoleesJ1=tab.get(24).split(":")[1];
			}
			nbBlancsJouesJ1=Integer.parseInt(tab.get(25).split(":")[1]);
			nbMauvaisCoupsJ1=Integer.parseInt(tab.get(26).split(":")[1]);
			nbVolsJ1=Integer.parseInt(tab.get(27).split(":")[1]);
			// JOUEUR 2
			difficulteJ2=Integer.parseInt((tab.get(29).split(":")[1]));
			nomJ2=(tab.get(30).split(":")[1]);
			lectureBaseJoueur2.add(tab.get(31));
			lectureBaseJoueur2.add(tab.get(32));
			lectureBaseJoueur2.add(tab.get(33));
			lectureBaseJoueur2.add(tab.get(34));
			lectureBaseJoueur2.add(tab.get(35));
			lectureBaseJoueur2.add(tab.get(36));
			if(tab.get(38).split(":").length<2) {
				lecturePiocheJoueur2="";
			}else {
				lecturePiocheJoueur2=tab.get(38).split(":")[1];
			}
			if(tab.get(39).split(":").length<2) {
				piecesVoleesJ2="";
			}else {
				piecesVoleesJ2=tab.get(39).split(":")[1];
			}
			nbBlancsJouesJ2=Integer.parseInt(tab.get(40).split(":")[1]);
			nbMauvaisCoupsJ2=Integer.parseInt(tab.get(41).split(":")[1]);
			nbVolsJ2=Integer.parseInt(tab.get(42).split(":")[1]);
			if(difficulteJ1==0 || difficulteJ1==1) {
				j1 = new IAActeur(nomJ1, difficulteJ1, 0);
			}else {
				j1 = new Joueur(nomJ1);
			}
			if(difficulteJ2==0 || difficulteJ2==1) {
				j2 = new IAActeur(nomJ2, difficulteJ2, 1);
			}else {
				j2 = new Joueur(nomJ2);
			}
			Partie p=new Partie(j1, j2, numPartie);
			boolean b1 = p.getBaseMontagne().stringToPyramide(lectureBase);
			boolean b2 = j1.getCamp().stringToPyramide(lectureBaseJoueur1);
			j1.stringToPiecesPiochees(lecturePiocheJoueur1);
			j1.stringToPiecesVolees(piecesVoleesJ1);
			j1.setBlancsJoues(nbBlancsJouesJ1);
			j1.setMauvaisCoupsJoues(nbMauvaisCoupsJ1);
			j1.setVols(nbVolsJ1);
			j2.getCamp().stringToPyramide(lectureBaseJoueur2);
			j2.stringToPiecesPiochees(lecturePiocheJoueur2);
			j2.stringToPiecesVolees(piecesVoleesJ2);
			j2.setBlancsJoues(nbBlancsJouesJ2);
			j2.setMauvaisCoupsJoues(nbMauvaisCoupsJ2);
			j2.setVols(nbVolsJ2);
			if(b1&&b2) {
				this.partieEnCours=p;
				jouerPhase1();
				jouerPhase2();
			}
			return b1&&b2;
		}
		catch (Exception e) {
			System.err.println("Erreur : le fichier "+fichier+" n'a pas pu etre lu.");
			e.printStackTrace();
			return false;
		}
}
			
}