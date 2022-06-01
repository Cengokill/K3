package Controleur;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JFrame;

import Modeles.*;
import Vue.IHM;
import Vue.Menu.Chargement;
import Vue.Menu.Chargement.TypeFenetre;
import Vue.TexturePack.LoadTexture;

public class Jeu {
	public Partie partieEnCours;
	public String nomActeur1, nomActeur2;
	public String chemin, cheminStats, cheminImages, cheminSauvegardes, photoProfil;
	private int num_tour, valeur_paire, typeActeurs, difficulte1, difficulte2, vitesseIA;
	public int volumeEffetsSonores, volumeMusique, modeDaltonien, modePleinEcran;
	private final int TAILLE_CAMP_JOUEUR=21;
	//public JFrame window;
	//public Phase1ihm.phase1Panel ihm.phase1Panel;
	private int NB_LIGNES_SAUVEGARDE=43;
	public OptionsJeu options;
	public Chargement chargement;
	public IHM ihm;
	public boolean changement_joueur=true;

	public Jeu(JFrame fenetrePrincipale, InitPartie partieInit, OptionsJeu o, Chargement c) throws IOException {
		this.options=o;
		this.chargement=c;
		this.volumeEffetsSonores=options.volumeEffetsSonores;
		//this.window=fenetrePrincipale;
		this.chemin=System.getProperty("user.home")+ "/Desktop/Jeu_K3/";
		this.cheminStats=chemin+"Statistiques/";
		this.cheminImages=chemin+"Images/";
		this.cheminSauvegardes=chemin+"Sauvegardes/";
		//creer les dossier du jeu s'il n'existent pas
		new File(this.chemin).mkdirs();
		new File(this.cheminStats).mkdirs();
		new File(this.cheminImages).mkdirs();
		new File(this.cheminSauvegardes).mkdirs();
		//lancer IHM
		LoadTexture texture = new LoadTexture(options.chemin);
		this.ihm = new IHM(fenetrePrincipale, options, this, texture, chargement, partieInit);
		this.ihm.start();
	}
	
	public void setParametresPartie(int t, int d1, int d2, int v, String nom1, String nom2) {
		this.typeActeurs=t;// IA ou joueur
		this.difficulte1=d1;// si 2 AI, difficulte IA 1
		this.difficulte2=d2;// si 2 AI, difficulte IA 2
		this.nomActeur1=nom1;
		this.nomActeur2=nom2;
		this.vitesseIA=v;
	}
	
	public void initNewPhase1(InitPartie p) {
		setParametresPartie(p.modeDeJeu,p.difficulteIA1,p.difficulteIA2,500,p.nomJoueur1,p.nomJoueur2);
		setActeurs();
		initPhase1();
		lancerPhase1();
	}
	
	public void initPhase1() {
		//initialisation des blancs et des naturels aux joueurs
		this.partieEnCours.distribuerBlancEtNaturels();
		//initialisation des pieces des deux joueurs
		while (this.partieEnCours.joueur1().getTaillePiecesPiochees() < this.TAILLE_CAMP_JOUEUR || this.partieEnCours.joueur2().getTaillePiecesPiochees() < this.TAILLE_CAMP_JOUEUR) {
			piocher();
		}
	}
	
	public void setActeurs() {
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
		}
	}
	
	public void lancerPartieJcIA(String nomJ1, String nomJ2, int numPartie) {
		Acteur j1 = new IAActeur(nomJ2, 1, 0);
		Acteur j2 = new Joueur(nomJ1);
		this.partieEnCours = new Partie(j1, j2, numPartie);
		this.partieEnCours.setCheminStats(cheminStats);
		this.num_tour=1;
		this.valeur_paire=0;
	}
	
	public void lancerPartieJcJ(String nomJ1, String nomJ2, int numPartie) {
		Joueur j1 = new Joueur(nomJ1);
		Joueur j2 = new Joueur(nomJ2);
		this.partieEnCours = new Partie(j1, j2, numPartie);
		this.partieEnCours.setCheminStats(cheminStats);
		this.num_tour=1;
		this.valeur_paire=0;
	}
	
	public void lancerPhase1() {
		/*
		this.ihm.phase1Panel = new Phase1ihm.phase1Panel(this.window, this.partieEnCours, this.textures);
		this.window.setContentPane(ihm.phase1Panel);
		window.paintAll(window.getGraphics());
		*/
		this.chargement.setProchaineFenetre(TypeFenetre.PHASE1);
		this.chargement.lancement=true;
		this.ihm.phase1Panel.setPartieEnCours(this.partieEnCours);
		jouerPhase1();
	}
		
	public void jouerPhase1() {
		int temps=0;
		if(this.typeActeurs==2) {
			temps=this.vitesseIA;
		}
		ArrayList<PiecePyramide> arr;
		Acteur acteurCourant;
		for(int i=0; i<2; i++) {
			if(this.partieEnCours.getJoueurCourant()==0) {
				acteurCourant=this.partieEnCours.joueur1();
				this.partieEnCours.demarrerTimer(0);
			}else {
				acteurCourant=this.partieEnCours.joueur2();
				this.partieEnCours.demarrerTimer(1);
			}
			while ((acteurCourant.getTaillePiecesPiochees()>0 || !acteurCourant.valideCamp) && chargement.getProchaineFenetre()==TypeFenetre.PHASE1) {
				//chaque joueur doit choisir la piece a empiler sur sa pioche
				arr = acteurCourant.phase1(this.partieEnCours);
				timer(16);
				ihm.phase1Panel.repaint();//afficherTimer(ihm.phase1Panel.getGraphics());
				if(!arr.isEmpty()){
					for(PiecePyramide p : arr) {
						if(chargement.getProchaineFenetre()==TypeFenetre.PHASE1) {
							timer(temps);
							if(acteurCourant.getCamp().empilerPhase1(p)) {//pas de verif pieces porteuse
								acteurCourant.removePiecePiochee(p.getPiece());
								this.ihm.phase1Panel.repaint();
								if((this.typeActeurs==2 && this.vitesseIA>400) || this.typeActeurs!=2) {
									this.options.gestionSons.playSon(1);//son de lancement de partie
								}
							}
						}
					}
				}
			}
			acteurCourant.stopTempsConstruction();
			if(chargement.getProchaineFenetre()==TypeFenetre.PHASE1) {
				timer(1200);
				this.partieEnCours.changementJoueurCourant();
				this.ihm.phase1Panel.repaint();
			}
		}
		if(chargement.getProchaineFenetre()==TypeFenetre.PHASE1) {
			this.chargement.setProchaineFenetre(TypeFenetre.PHASE2);
			this.chargement.lancement=true;
		}
	}
	
	public void lancementPhase2() {
		this.chargement.setProchaineFenetre(TypeFenetre.PHASE2);
		this.chargement.lancement=true;
		jouerPhase2();
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
			ihm.phase2Panel.repaint();
			if(changement_joueur&&((this.typeActeurs==2 && this.vitesseIA>400) || this.typeActeurs!=2)) {
				this.options.gestionSons.playSon(35);//debut de tour
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
		if(changement_joueur) {
			afficherBaseMontagne();
			System.out.println("Votre camp :");
			System.out.println(jCourant.getCamp().toString());
			System.out.println("Vos pieces volees : "+jCourant.toStringPiecesVolees());
			System.out.println("Camp adverse :");
			System.out.println(jPrecedent.getCamp().toString());
			System.out.println("Ses pieces volees : "+jPrecedent.toStringPiecesVolees());
			System.out.println(jCourant.getNom()+", veuillez jouer un coup :");
			changement_joueur=false;
		}
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

		if(jCourant.validerCoup) {// || TEMPS DEPASSE
			ArrayList<Coup> cJ = this.partieEnCours.coupsJouables(jCourant);
			Coup coupDemande = jCourant.jouer(cJ, this.partieEnCours);// le joueur courant a choisi un coup a jouer
			this.partieEnCours.addCoupHist(coupDemande);// ajout du coup a l'historique
			if (coupDemande.getPosJ() != null) {// si le joueur courant ne joue pas une piece volee
				jCourant.getCamp().retirer(coupDemande.getPosJ());// retire la piece jouee du camp du joueur courant
			} else {// si le joueur courant decide de jouer une de ses pieces volees
				jCourant.retirerPieceVolee(coupDemande.getPiece());
			}
			timer(temps);
			ihm.phase2Panel.repaint();
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
				ihm.phase2Panel.repaint();
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
					ihm.phase2Panel.repaint();
				}
			} else {// joue une piece BLANCHE
				jCourant.addBlancJoue();
				if((this.typeActeurs==2 && this.vitesseIA>400) || this.typeActeurs!=2) {
					this.options.gestionSons.playSon(10);
				}
				timer(temps);
				ihm.phase2Panel.repaint();
				System.out.println("Vous avez decide de passer votre tour !");
			}
			if (this.partieEnCours.joueurCourant == 0) {
				this.partieEnCours.joueur1().stopTempsConstruction();
			} else {
				this.partieEnCours.joueur2().stopTempsConstruction();
			}
			changement_joueur=true;
			partieEnCours.changementJoueurCourant();
		}
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
		Jeu.timer(10000);
		this.chargement.setProchaineFenetre(TypeFenetre.MENU);
		this.chargement.lancement=true;
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
	
	public static String[] listerSauvegardes(String chemin) {
		File f = new File(chemin);
		String[] sauvegardesListe = f.list();
		return sauvegardesListe;
	}
	
	public void sauvegarderUnePartie() {
		this.partieEnCours.sauvegarderPartie(this.cheminSauvegardes+"partie1.txt");
	}
	
	public boolean chargerPartie(String nomFichier) {
		int i = 0;
		nomFichier = cheminSauvegardes + nomFichier;
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
			System.out.println(tab.get(i).toString()+"||"+i);
			i++;
			lectureBase.add(tab.get(1));
			System.out.println(tab.get(i).toString()+"||"+i);
			i++;
			lectureBase.add(tab.get(2));
			System.out.println(tab.get(i).toString()+"||"+i);
			i++;
			lectureBase.add(tab.get(3));
			System.out.println(tab.get(i).toString()+"||"+i);
			i++;
			lectureBase.add(tab.get(4));
			System.out.println(tab.get(i).toString()+"||"+i);
			i++;
			lectureBase.add(tab.get(5));
			System.out.println(tab.get(i).toString()+"||"+i);
			i++;
			lectureBase.add(tab.get(6));
			System.out.println(tab.get(i).toString()+"||"+i);
			i++;
			
			lectureBase.add(tab.get(7));
			System.out.println(tab.get(i).toString()+"||"+i);
			i++;
			
			lectureBase.add(tab.get(8));
			System.out.println(tab.get(i).toString()+"||"+i);
			i++;
			
			lectureBase.add(tab.get(9));
			System.out.println(tab.get(i).toString()+"||"+i);
			i++;
			i++;
			jCourant=Integer.parseInt((tab.get(11).split(":")[1]));
			System.out.println(tab.get(i).toString()+"||"+i);
			i++;
			
			numPartie=Integer.parseInt((tab.get(12).split(":")[1]));
			System.out.println(tab.get(i).toString()+"||"+i);
			i++;
			i++;
			// JOUEUR 1
			difficulteJ1=Integer.parseInt((tab.get(14).split(":")[1]));
			System.out.println(tab.get(i).toString()+"||"+i);
			i++;
			
			nomJ1=(tab.get(15).split(":")[1]);
			System.out.println(tab.get(i).toString()+"||"+i);
			i++;
			
			lectureBaseJoueur1.add(tab.get(16));
			System.out.println(tab.get(i).toString()+"||"+i);
			i++;
			
			lectureBaseJoueur1.add(tab.get(17));
			System.out.println(tab.get(i).toString()+"||"+i);
			i++;
			
			lectureBaseJoueur1.add(tab.get(18));
			System.out.println(tab.get(i).toString()+"||"+i);
			i++;
			
			lectureBaseJoueur1.add(tab.get(19));
			System.out.println(tab.get(i).toString()+"||"+i);
			i++;
			
			lectureBaseJoueur1.add(tab.get(20));
			System.out.println(tab.get(i).toString()+"||"+i);
			i++;
			
			lectureBaseJoueur1.add(tab.get(21));
			System.out.println(tab.get(i).toString()+"||"+i);
			i++;
			i++;
			if(tab.get(23).split(":").length<2) {
				lecturePiocheJoueur1="";
			}else {
				lecturePiocheJoueur1=tab.get(23).split(":")[1];
				System.out.println(tab.get(23).split(":")[1]+"aaaaaaaaaaaaaaaa");
			}
			System.out.println(tab.get(i).toString()+"||"+i+"rouge");
			i++;
			
			if(tab.get(24).split(":").length<2) {
				piecesVoleesJ1="";
			}else {
				piecesVoleesJ1=tab.get(24).split(":")[1];
			}
			System.out.println(tab.get(i).toString()+"||"+i);
			i++;
			
			nbBlancsJouesJ1=Integer.parseInt(tab.get(25).split(":")[1]);
			System.out.println(tab.get(i).toString()+"||"+i);
			i++;
			
			nbMauvaisCoupsJ1=Integer.parseInt(tab.get(26).split(":")[1]);
			System.out.println(tab.get(i).toString()+"||"+i);
			i++;
			
			nbVolsJ1=Integer.parseInt(tab.get(27).split(":")[1]);
			System.out.println(tab.get(i).toString()+"||"+i);
			i++;
			
			// JOUEUR 2
			difficulteJ2=Integer.parseInt((tab.get(29).split(":")[1]));
			System.out.println(tab.get(i).toString()+"||"+i);
			i++;
			
			nomJ2=(tab.get(30).split(":")[1]);
			System.out.println(tab.get(i).toString()+"||"+i);
			i++;
			
			lectureBaseJoueur2.add(tab.get(31));
			System.out.println(tab.get(i).toString()+"||"+i);
			i++;
			
			lectureBaseJoueur2.add(tab.get(32));
			System.out.println(tab.get(i).toString()+"||"+i);
			i++;
			
			lectureBaseJoueur2.add(tab.get(33));
			System.out.println(tab.get(i).toString()+"||"+i);
			i++;
			
			lectureBaseJoueur2.add(tab.get(34));
			System.out.println(tab.get(i).toString()+"||"+i);
			i++;
			
			lectureBaseJoueur2.add(tab.get(35));
			System.out.println(tab.get(i).toString()+"||"+i);
			i++;
			
			lectureBaseJoueur2.add(tab.get(36));
			System.out.println(tab.get(i).toString()+"||"+i);
			i++;
			
			if(tab.get(38).split(":").length<2) {
				lecturePiocheJoueur2="";
			}else {
				lecturePiocheJoueur2=tab.get(38).split(":")[1];
			}
			System.out.println(tab.get(i).toString()+"||"+i);
			i++;
			
			if(tab.get(39).split(":").length<2) {
				piecesVoleesJ2="";
			}else {
				piecesVoleesJ2=tab.get(39).split(":")[1];
			}
			System.out.println(tab.get(i).toString()+"||"+i);
			i++;
			
			nbBlancsJouesJ2=Integer.parseInt(tab.get(40).split(":")[1]);
			System.out.println(tab.get(i).toString()+"||"+i);
			i++;
			
			nbMauvaisCoupsJ2=Integer.parseInt(tab.get(41).split(":")[1]);
			System.out.println(tab.get(i).toString()+"||"+i);
			i++;
			
			nbVolsJ2=Integer.parseInt(tab.get(42).split(":")[1]);
			System.out.println(tab.get(i).toString()+"||"+i);
			i++;
			
			
			if(difficulteJ1==0 || difficulteJ1==1 || difficulteJ1==2) {
				j1 = new IAActeur(nomJ1, difficulteJ1, 0);
			}else {
				j1 = new Joueur(nomJ1);
			}
			
			i++;
			if(difficulteJ2==0 || difficulteJ2==1 || difficulteJ1==2) {
				j2 = new IAActeur(nomJ2, difficulteJ2, 1);
			}else {
				j2 = new Joueur(nomJ2);
			}
			
			i++;
			
			Partie p=new Partie(j1, j2, numPartie);
			
			i++;
			
			p.joueurCourant = jCourant;
			
			boolean b1 = p.getBaseMontagne().stringToPyramide(lectureBase);
			//System.err.println(p.getBaseMontagne().toString());
			
			i++;
			
			boolean b2 = j1.getCamp().stringToPyramide(lectureBaseJoueur1);
			//System.err.println(j1.getCamp().toString());
			
			i++;
			
			j1.stringToPiecesPiochees(lecturePiocheJoueur1);
			//System.err.println("J1PIO"+j1.piecesPiocheesToString());
			
			i++;
			
			j1.stringToPiecesVolees(piecesVoleesJ1);
			//System.err.println("J1VOL"+j1.toStringPiecesVolees().length());
			
			i++;
			
			j1.setBlancsJoues(nbBlancsJouesJ1);
			//System.err.println("J1BLC"+j1.getBlancsJoues());
			i++;
			
			j1.setMauvaisCoupsJoues(nbMauvaisCoupsJ1);
			//System.err.println("J1BAD"+j1.getMauvaisCoupsJoues());
			
			i++;
			
			j1.setVols(nbVolsJ1);
			//System.err.println("J1VOL"+j1.getNbVols());
			
			i++;
			
			boolean b3 = j2.getCamp().stringToPyramide(lectureBaseJoueur2);
			//System.err.println(j2.getCamp().toString());
			
			i++;
			
			j2.stringToPiecesPiochees(lecturePiocheJoueur2);
			//System.err.println("J2PIO"+j2.piecesPiocheesToString());
			
			i++;
			
			j2.stringToPiecesVolees(piecesVoleesJ2);
			//System.err.println("J2VOL"+j2.toStringPiecesVolees().length());
			
			i++;
			
			j2.setBlancsJoues(nbBlancsJouesJ2);
			////System.err.println("J2BLC"+j2.getBlancsJoues());
			
			i++;
			
			j2.setMauvaisCoupsJoues(nbMauvaisCoupsJ2);
			////System.err.println("J2BAD"+j2.getMauvaisCoupsJoues());
			
			i++;
			
			j2.setVols(nbVolsJ2);
			////System.err.println("J2VOL"+j2.getNbVols());
			
			i++;
			
			if(b1&&b2&&b3) {
				this.partieEnCours=p;
				//System.err.println("J1 : " + j1.getTaillePiecesPiochees());
				//System.err.println("J1m : " + this.partieEnCours.joueur1().getTaillePiecesPiochees());
				//System.err.println("J2 : " + j2.getTaillePiecesPiochees());
				//System.err.println("J2m : " + this.partieEnCours.joueur2().getTaillePiecesPiochees());
				//System.err.println("JC : " + p.joueurCourant);
				//System.err.println("JCm : " + this.partieEnCours.joueurCourant);
				if(j1.getTaillePiecesPiochees()>0 || j2.getTaillePiecesPiochees()>0) {
					//System.err.println("ATTETION");
					if(j1.getTaillePiecesPiochees()>0) {
						//System.err.println("J1");
					}else {
						j1.valideCamp = true;
					}
					if(j2.getTaillePiecesPiochees()>0){
						//System.err.println("J2");
					}
					else {
						j2.valideCamp = true;
					}
					
					this.chargement.setProchaineFenetre(TypeFenetre.PHASE1);
					this.chargement.nouvellePartie=false;
					this.ihm.phase1Panel.setPartieEnCours(p);
					this.chargement.lancement=true;
					return true;
				}else {
					this.chargement.setProchaineFenetre(TypeFenetre.PHASE2);
					this.chargement.lancement=true;
					return true;
				}
			}
			return b1&&b2&&b3;
		}
		catch (Exception e) {
			System.err.println("Erreur : le fichier "+fichier+" n'a pas pu etre lu.");
			e.printStackTrace();
			return false;
		}
}
			
}