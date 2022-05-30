package Controleur;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;

import java.time.LocalTime;

import Modeles.*;
import Reseau.*;
import Vue.*;
import Vue.Phase1.*;
import Vue.Phase2.Phase2;
import Vue.TexturePack.LoadTexture;

public class Jeu {
	public Partie partieEnCours;
	public String nomActeur1, nomActeur2;
	public String chemin, cheminStats, cheminImages, cheminSauvegardes, photoProfil;
	private int num_tour, valeur_paire, typeActeurs, difficulte1, difficulte2, vitesseIA;
	public int volumeEffetsSonores, volumeMusique, modeDaltonien, modePleinEcran;
	private final int TAILLE_CAMP_JOUEUR=21;
	public JFrame window;
	public Phase1Panel panel;
	private int NB_LIGNES_SAUVEGARDE=43;
	public LoadTexture textures;
	public OptionsJeu options;

	public Jeu(JFrame fenetrePrincipale, InitPartie partieInit, LoadTexture t, OptionsJeu o) {
		this.textures=t;
		this.options=o;
		this.volumeEffetsSonores=options.volumeEffetsSonores;
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
		//initialiser les parties graphiques
		setParametresPartie(partieInit.modeDeJeu,partieInit.difficulteIA1,partieInit.difficulteIA2,500,partieInit.nomJoueur1,partieInit.nomJoueur2);
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
				this.partieEnCours.demarrerTimer(0);
			}else {
				acteurCourant=this.partieEnCours.joueur2();
				this.partieEnCours.demarrerTimer(1);
			}
			while (acteurCourant.getTaillePiecesPiochees()>0 || !acteurCourant.valideCamp) {
				//chaque joueur doit choisir la piece a empiler sur sa pioche
				arr = acteurCourant.phase1(this.partieEnCours);
				timer(16);
				panel.repaint();//afficherTimer(panel.getGraphics());
				if(!arr.isEmpty()){
					for(PiecePyramide p : arr) {
						timer((int)(temps));
						if(acteurCourant.getCamp().empilerPhase1(p)) {//pas de verif pieces porteuse
							acteurCourant.removePiecePiochee(p.getPiece());
							this.panel.repaint();
							if((this.typeActeurs==2 && this.vitesseIA>400) || this.typeActeurs!=2) {
								this.options.gestionSons.playSon(1);//son de lancement de partie
							}
						}
					}
				}
			}
			System.out.println(acteurCourant.getTempsConstruction());
			acteurCourant.stopTempsConstruction();
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
		double tempsJ1 = this.partieEnCours.joueur1().getTempsConstruction();
		double tempsJ2 = this.partieEnCours.joueur1().getTempsConstruction();
		if(tempsJ1<tempsJ2) {
			this.partieEnCours.setJoueurCourant(0);
		}
		else if(tempsJ1>tempsJ2) {
			this.partieEnCours.setJoueurCourant(1);
		}
		else {
			int a = Aleatoire.genInt(0,1);
			if(a==0) {
				this.partieEnCours.setJoueurCourant(0);
			}else {
				this.partieEnCours.setJoueurCourant(1);
			}
		}
		this.partieEnCours.joueur1().resetTempsConstruction();
		this.partieEnCours.joueur2().resetTempsConstruction();
		while(!this.partieEnCours.estPartieFinie()) {//explicite
			panel.repaint();
			if((this.typeActeurs==2 && this.vitesseIA>400) || this.typeActeurs!=2) {
				this.options.gestionSons.playSon(35);
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
		Acteur jCourant, jPrecedent;
		
		if(this.partieEnCours.getJoueurCourant()==0) {
			jCourant=this.partieEnCours.joueur1();
			jPrecedent=this.partieEnCours.joueur2();
		}else {
			jCourant=this.partieEnCours.joueur2();
			jPrecedent=this.partieEnCours.joueur1();
		}
		if(this.valeur_paire%2==0) {//affichage du numero du tour actuel
			afficherTour();
			this.valeur_paire++;
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
			this.partieEnCours.demarrerTimer(0);
		} else {
			jCourant = this.partieEnCours.joueur2();
			jPrecedent = this.partieEnCours.joueur1();
			this.partieEnCours.demarrerTimer(1);
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
				this.options.gestionSons.playSon(4);
			}
			if (coupDemande.getPiece().getColor().equals(Couleurs.NATUREL)) {
				if((this.typeActeurs==2 && this.vitesseIA>400) || this.typeActeurs!=2) {
					this.options.gestionSons.playSon(7);
				}
			}
			timer(temps);
			panel.repaint();
			if (this.partieEnCours.getBaseMontagne().estPorteursMemeCouleur(coupDemande.getPosBase())) {// si vol possible
				if((this.typeActeurs==2 && this.vitesseIA>400) || this.typeActeurs!=2) {
					this.options.gestionSons.playSon(23);
				}
				Coup vol = this.partieEnCours.volerPiece(jPrecedent, jCourant);
				if (vol != null) {//si le joueur vole une piece
					if((this.typeActeurs==2 && this.vitesseIA>400) || this.typeActeurs!=2) {
						this.options.gestionSons.playSon(26);
						timer(500);
					}
					this.partieEnCours.addCoupHist(vol);
				}
				timer(temps);
				panel.repaint();
			}
		} else {// joue une piece BLANCHE
			jCourant.addBlancJoue();
			if((this.typeActeurs==2 && this.vitesseIA>400) || this.typeActeurs!=2) {
				this.options.gestionSons.playSon(10);
			}
			timer(temps);
			panel.repaint();
			System.out.println("Vous avez decide de passer votre tour !");
		}
		if (this.partieEnCours.joueurCourant == 0) {
			this.partieEnCours.joueur1().stopTempsConstruction();
		} else {
			this.partieEnCours.joueur2().stopTempsConstruction();
		}
		partieEnCours.changementJoueurCourant();
	}
	
	public void partieVictoire() {
		this.options.gestionSons.playSon(32);
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