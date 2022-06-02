package Modeles;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import java.util.Timer;

import Controleur.Jeu;

public class Partie {
	private int numPartie;
	private Acteur j1;
	private Acteur j2;
	private ArrayList<Piece> basePieces;
	private PyramideMontagne baseMontagne;// base de la montagne
	private Statistiques statistiques;// creer a la fin de la partie uniquement
	private String cheminStats;
	private ArrayList<Coup> historiqueCoups;
	public int joueurCourant, joueurDebut;
	Piece pBleu;
	Piece pVert;
	Piece pJaune;
	Piece pRouge;
	Piece pNoir;
	Piece pBlanc;
	Piece pNaturel;
	private final int NB_PIECES_NATURELS = 2;
	private final int NB_PIECES_BLANCS = 2;
	public boolean IAreflechis = false;
	public boolean doitVoler = false;
	public boolean afficheVictoire = true;
	public boolean partieFini = false;

	public Partie(Acteur j1, Acteur j2, int numPartie) {
		this.numPartie = numPartie;
		this.joueurCourant = Aleatoire.genInt(0, 1);// choix du joueur aleatoire
		this.joueurDebut = joueurCourant;
		this.basePieces = new ArrayList<Piece>();
		this.j1 = j1;
		this.j2 = j2;
		this.cheminStats = ".\\Sauvegardes\\Stats\\";// valeur par defaut
		this.statistiques = new Statistiques(this.cheminStats);
		this.historiqueCoups = new ArrayList<Coup>();
		pBleu = new Piece(Couleurs.BLEU);
		pVert = new Piece(Couleurs.VERT);
		pJaune = new Piece(Couleurs.JAUNE);
		pRouge = new Piece(Couleurs.ROUGE);
		pNoir = new Piece(Couleurs.NOIR);
		pBlanc = new Piece(Couleurs.BLANC);
		pNaturel = new Piece(Couleurs.NATUREL);
		initialiserSac();
		initBaseMontagne();
	}

	public void demarrerTimer(int a) {
		if (a == 0) {
			j1.setTempsConstruction();
		} else {
			j2.setTempsConstruction();
		}
	}

	public void setCheminStats(String s) {
		this.cheminStats = s;
	}

	public ArrayList<Coup> getHistCoups() {
		return this.historiqueCoups;
	}

	public void addCoupHist(Coup c) {// gere les vols
		Acteur j;
		if (joueurCourant == 1)
			j = this.j1;
		else
			j = this.j2;

		this.historiqueCoups.add(c);
		if (c.getPosBase() == null) {
			j.addBlancJoue();
		}
	}

	public void retireCoupHist(Coup c) {
		int taille = this.historiqueCoups.size();
		this.historiqueCoups.remove(taille - 1);
	}

	public void changementJoueurCourant() {
		if (this.joueurCourant == 1) {
			this.joueurCourant = 0;
		} else
			this.joueurCourant = 1;
	}

	public int getJoueurCourant() {
		return this.joueurCourant;
	}

	public void setJoueurCourant(int a) {
		this.joueurCourant = a;
	}

	public boolean joueurPeutJouer(Acteur j) {
		return !coupsJouables(j).isEmpty();
		// si le joueur n'a plus de coups jouables mais que la pyramide est pleine alors
		// il n'a pas perdu
		// si le joueur n'a plus de coups jouables et que la pyramide n'est pas pleine
		// alors il a perdu
	}

	public void sauvegarderStatsPartie(int gagnant) {
		this.statistiques.initStats(this.j1, this.j2, this.joueurDebut, gagnant, this.historiqueCoups);
		this.statistiques.ecrireStats(this.numPartie);
	}

	public boolean estPartieFinie() {
		boolean pleine = this.baseMontagne.estPleine();
		if (pleine) {// egalite
			return pleine;
		} else {// un des deux joueurs a perdu
			if (this.joueurCourant == 0) {
				if (!joueurPeutJouer(joueur1())) {// joueur 1 a perdu
					return true;
				}
			} else {
				if (!joueurPeutJouer(joueur2())) {// joueur 2 a perdu
					return true;
				}
			}
		} // si joueur 1 et joueur 2 peuvent jouer et que la montagne n'est pas pleine
		return false;
	}

	public Statistiques getStats() {
		return this.statistiques;
	}

	public void combinerStats(int a, int b) {
		this.statistiques.combinerStats(a, b);
	}

	public void initialiserSac() {// ajoute toutes les pieces au sac
		int nb_pieces_par_couleur = 9;
		for (int i = 0; i < nb_pieces_par_couleur; i++) {// uniquement pour la base de la montagne
			basePieces.add(pBleu);
			basePieces.add(pVert);
			basePieces.add(pJaune);
			basePieces.add(pRouge);
			basePieces.add(pNoir);
		}
		Collections.shuffle(basePieces);// melange les pieces a piocher pour les joueurs
		// System.out.println("sac initialise. Taille : " + basePieces.size());
	}

	public void distribuerBlancEtNaturels() {
		Acteur jCourant = j1;
		Piece pNaturel = new Piece(Couleurs.NATUREL);
		Piece pBlanc = new Piece(Couleurs.BLANC);
		for (int j = 0; j <= 1; j++) {
			if (j == 1)
				jCourant = j2;
			for (int i = 0; i < NB_PIECES_NATURELS; i++) {
				jCourant.addPiecePiochee(pNaturel);
			}
			for (int i = 0; i < NB_PIECES_BLANCS; i++) {
				jCourant.addPiecePiochee(pBlanc);
			}
		}
	}

	public void initBaseMontagne() {// creation de la base de la montagne constituee de 9 pieces
		ArrayList<Piece> neufPieces = new ArrayList<Piece>();
		ArrayList<Couleurs> quatreCouleurs = new ArrayList<Couleurs>();
		int i = 0;
		while (i < basePieces.size() && neufPieces.size() < 9) {
			Piece pCourante = basePieces.get(i);
			Couleurs cCourante = basePieces.get(i).getColor();
			if (!quatreCouleurs.contains(cCourante) && quatreCouleurs.size() < 4) {
				quatreCouleurs.add(cCourante);
				neufPieces.add(pCourante);
				basePieces.remove(pCourante);
				i--;
			} else if (quatreCouleurs.size() >= 4) {
				neufPieces.add(pCourante);
				basePieces.remove(pCourante);
				i--;
			}
			i++;
		}
		Collections.shuffle(neufPieces);
		baseMontagne = new PyramideMontagne(9, 9);// 9 etages, 9 pieces au dernier etage
		for (int k = 0; k < 9; k++) {
			Piece element = neufPieces.get(0);
			Position pos = new Position(0, k);
			PiecePyramide pp = new PiecePyramide(element, pos);
			baseMontagne.empiler(pp);
			neufPieces.remove(0);
		}
		// System.out.println("Base de la montagne initialisee avec au moins 4 couleurs
		// differentes. Taille : "
		// + baseMontagne.getHauteur());
	}

	public boolean contiens(ArrayList<Coup> arr, Coup c) {
		for (int i = 0; i < arr.size(); i++) {
			if (arr.get(i).egal(c)) {
				return true;
			}
		}
		return false;
	}

	public ArrayList<Coup> coupsJouables(Acteur j) {// renvoie les pieces et la pos jouables des pieces du joueur
		ArrayList<Coup> coupsPosables = new ArrayList<Coup>();
		Piece pJoueurCourante, p2;
		Position pos1, pos2;
		Coup c;
		ArrayList<PiecePyramide> piecesBase = this.baseMontagne.piecesPosables();
		ArrayList<PiecePyramide> piecesJoueur = j.getPiecesJouables();
		ArrayList<Coup> coupsDoublons = new ArrayList<Coup>();
		for (PiecePyramide pieceJoueur : piecesJoueur) {// pour chaque piece du joueur
			pJoueurCourante = pieceJoueur.getPiece();
			pos1 = pieceJoueur.getPos();
			if (pJoueurCourante.getColor() == Couleurs.BLANC) {// si la piece courante du joueur est BLANC
				c = new Coup(pJoueurCourante, pos1, null);
				coupsPosables.add(c);
			} else {
				for (PiecePyramide pp : piecesBase) {
					p2 = pp.getPiece();// piece courante du camp de la montagne
					pos2 = pp.getPos();// position de la piece
					c = new Coup(pJoueurCourante, pos1, pos2);
					if (((pJoueurCourante.getColor() == p2.getColor())) && !contiens(coupsDoublons, c)) {
						// si la piece courante du joueur a la meme couleur que la piece courante de la
						// pyramide
						c = new Coup(pJoueurCourante, pos1, pos2);
						coupsDoublons.add(c);
						coupsPosables.add(c);
					}
				}
			}
		}
		return coupsPosables;
	}

	public PyramideMontagne getBaseMontagne() {
		return this.baseMontagne;
	}

	public void setBaseMontagne(PyramideMontagne p) {
		this.baseMontagne = p;
	}

	public ArrayList<Piece> getBasePieces() {
		return this.basePieces;
	}

	public int getTailleBasePieces() {
		return this.basePieces.size();
	}

	public static void afficherCoups(ArrayList<PiecePyramide> arr) {
		int taille = arr.size();
		for (int i = 0; i < taille; i++) {
			Piece pi = arr.get(i).getPiece();
			Position pos = arr.get(i).getPos();
			System.out.println(pi.toString() + ":" + pos.toString());
		}
	}

	public Acteur joueur1() {
		return j1;
	}

	public Acteur joueur2() {
		return j2;
	}

	public void afficheSac() {
		Iterator<Piece> it = basePieces.iterator();
		System.out.println("Dans le sac il y a:");
		int noir = 0;
		int jaune = 0;
		int rouge = 0;
		int vert = 0;
		int bleu = 0;
		while (it.hasNext()) {
			Piece p = it.next();
			if (p.getColor().equals(Couleurs.NOIR)) {
				noir++;
			} else if (p.getColor().equals(Couleurs.JAUNE)) {
				jaune++;
			} else if (p.getColor().equals(Couleurs.ROUGE)) {
				rouge++;
			} else if (p.getColor().equals(Couleurs.VERT)) {
				vert++;
			} else if (p.getColor().equals(Couleurs.BLEU)) {
				bleu++;
			}

		}
		System.out.println("Il y a " + noir + " piece noires.");
		System.out.println("Il y a " + jaune + " piece jaune.");
		System.out.println("Il y a " + rouge + " piece rouges.");
		System.out.println("Il y a " + vert + " piece vertes.");
		System.out.println("Il y a " + bleu + " piece bleues.");
		System.out.println();
	}

	// Transformer en 2 fonctions : une qui demande la piece voler et qui renvoie
	// celle a voler et une deuxieme qui vole la piece donner en argument
	public Coup volerPiece(Acteur voleur, Acteur victime) {// voleur vole une piece au joueur victime
		Coup vol = null;
		victime.addMauvaisCoup();
		System.out.println(
				voleur.getNom() + ", voulez-vous voler une piece a " + victime.getNom() + " ? 0 : OUI | 1 : NON");
		voleur.doitVol = true;
		PiecePyramide pieceVolee = null;
		while (voleur.doitVol) {
			Jeu.timer(100);
			if (voleur.validerVol) {
				pieceVolee = voleur.choixVol(victime.getPiecesJouables(), this);
				voleur.doitVol = false;
			}
		}
		voleur.choixVol(victime.getPiecesJouables(), this);

		if (pieceVolee != null) {
			vol = new Coup(pieceVolee.getPiece(), pieceVolee.getPos(), null);
			if (pieceVolee.getPos() == null) {// si le voleur vole une piece volee
				victime.retirerPieceVolee(pieceVolee.getPiece());
			} else {
				victime.getCamp().retirer(pieceVolee.getPos());
			}
			voleur.addPieceVolee(pieceVolee.getPiece());// ajout de la piece volee aux pieces volees du voleur
			System.out.println("Vos pieces volees : " + voleur.toStringPiecesVolees());
		} else {
			System.out.println("Vous avez choisi de ne pas voler de piece a " + victime.getNom() + ".");
		}
		return vol;
	}

	public void annulerCoup(Coup c) {// annule le coup du joueur courant
		Acteur jCourant, jPrecedent;
		if (this.joueurCourant == 0) {
			jCourant = this.j1;
			jPrecedent = this.j2;
		} else {
			jCourant = this.j2;
			jPrecedent = this.j1;
		}
		/*
		 * un coup standard est defini par :
		 * - une piece
		 * - une position du camp joueur
		 * - une position du camp montagne
		 * 
		 * un coup vole :
		 * - une piece
		 * - une position du camp joueur victime
		 * - null
		 * un coup blanc :
		 * - une piece
		 * - une position du camp joueur
		 * - null
		 */
		if (c.getPosBase() != null) {// retire une piece normale
			PiecePyramide pp = this.baseMontagne.retirer(c.getPosBase());// ok
			if (this.baseMontagne.estPorteursMemeCouleur(pp.getPos())) {
				jPrecedent.retireMauvaisCoup();
				jPrecedent.getCamp().empiler(new PiecePyramide(c.getPiece(), c.getPosJ()));
				System.out.println("Mauvais coup annule !");
			}else {
				jPrecedent.getCamp().empiler(new PiecePyramide(c.getPiece(), c.getPosJ()));
			}
		} else {// retire une piece BLANCHE
			PiecePyramide pp = new PiecePyramide(c.getPiece(), c.getPosJ());
			if (c.getPiece().getColor().equals(Couleurs.BLANC)) {
				// annulation d'un coup blanc
				jPrecedent.getCamp().empiler(pp);
				jPrecedent.retireBlancJoue();
				System.out.println("Coup blanc annule !");
			} else {// annulation d'un VOL
				jCourant.getCamp().empiler(pp);// on empile la piece dans le camp de la victime de vol
				jPrecedent.retirerPieceVolee(c.getPiece());
				System.out.println("Vol annule !");
			}
		}
		retireCoupHist(c);
		changementJoueurCourant();
	}

	public void sauvegarderPartie(String CheminEtnomFichier) {
		System.out.println("ESSAI SAUVEGARDE");
		File f = new File(CheminEtnomFichier);
		try {
			f.createNewFile();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		try {
			System.out.println("try");
			FileWriter writer = new FileWriter(f, false);// erire en mode remplacement
			BufferedWriter bw = new BufferedWriter(writer);
			bw.write("base montagne:");
			bw.newLine();
			bw.write(this.getBaseMontagne().toStringSauvegarde());
			bw.newLine();
			bw.write("joueur courant:" + this.getJoueurCourant());
			bw.newLine();
			bw.write("numero partie:" + this.numPartie);
			bw.newLine();
			bw.write(" ======================= JOUEUR 1 =======================");
			bw.newLine();
			bw.write("type joueur 1:" + this.joueur1().getDiff());
			bw.newLine();
			bw.write("nom joueur 1:" + this.joueur1().getNom());
			bw.newLine();
			bw.write(this.joueur1().getCamp().toStringSauvegarde());
			bw.newLine();
			bw.write("pioche:" + this.joueur1().piecesPiocheesToString());
			bw.newLine();
			bw.write("pieces volees:" + this.joueur1().toStringPiecesVolees());
			bw.newLine();
			bw.write("blancs joues:" + this.joueur1().getBlancsJoues());
			bw.newLine();
			bw.write("mauvais coups joues:" + this.joueur1().getMauvaisCoupsJoues());
			bw.newLine();
			bw.write("vols effectues:" + this.joueur1().getNbVols());
			bw.newLine();
			bw.write(" ======================= JOUEUR 2 =======================");
			bw.newLine();
			bw.write("type joueur 2:" + this.joueur2().getDiff());
			bw.newLine();
			bw.write("nom joueur 2:" + this.joueur2().getNom());
			bw.newLine();
			bw.write(this.joueur2().getCamp().toStringSauvegarde());
			bw.newLine();
			bw.write("pioche:" + this.joueur2().piecesPiocheesToString());
			bw.newLine();
			bw.write("pieces volees:" + this.joueur2().toStringPiecesVolees());
			bw.newLine();
			bw.write("blancs joues:" + this.joueur2().getBlancsJoues());
			bw.newLine();
			bw.write("mauvais coups joues:" + this.joueur2().getMauvaisCoupsJoues());
			bw.newLine();
			bw.write("vols effectues:" + this.joueur2().getNbVols());
			bw.newLine();
			bw.close();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public boolean IAjoueCoup(Coup c, int joueurcourant) {
		// System.out.println("on joue un coup");
		Acteur jCourant;
		if (joueurcourant == 0) {
			jCourant = this.j1;
		} else {
			jCourant = this.j2;
		}

		if (c.getPosJ() != null) {// si le joueur courant ne joue pas une piece volee
			jCourant.getCamp().retirer(c.getPosJ());// retire la piece jouee du camp du joueur courant
		} else {// si le joueur courant decide de jouer une de ses pieces volees
			jCourant.retirerPieceVolee(c.getPiece());
		}

		if (c.getPosBase() != null) {// si le joueur ne choisit pas de jouer une piece BLANCHE
			this.getBaseMontagne().empiler(new PiecePyramide(c.getPiece(), c.getPosBase()));
		} else {// joue une piece BLANCHE
			return false;
		}
		return this.getBaseMontagne().estPorteursMemeCouleur(c.getPosBase())
				&& jCourant.getPiecesJouables().size() != 0;
	}

	public void IAannulCoup(Coup c, int joueurcourant) {
		// System.out.println("on annule un coup");
		Acteur jCourant;
		if (joueurcourant == 0) {
			jCourant = this.j1;
		} else {
			jCourant = this.j2;
		}
		if (c.getPosJ() != null) {// si le joueur courant ne joue pas une piece volee
			jCourant.getCamp().empiler(new PiecePyramide(c.getPiece(), c.getPosJ()));// retire la piece jouee du camp
																						// du joueur courant
		} else {// si le joueur courant decide de jouer une de ses pieces volees
			jCourant.addPieceVolee(c.getPiece());
		}

		if (c.getPosBase() != null) {// si le joueur ne choisit pas de jouer une piece BLANCHE
			this.getBaseMontagne().retirer(c.getPosBase());
		}
	}

	public void IAvol(PiecePyramide pp, int joueurcourant) { // le joueur courant se fait voler une piece
		Acteur jCourant, jPrecedent;
		if (joueurcourant == 0) {
			jCourant = this.j1;
			jPrecedent = this.j2;
		} else {
			jCourant = this.j2;
			jPrecedent = this.j1;
		}

		if (pp.getPos() == null) {// si le voleur vole une piece volee
			jCourant.piecesVolees.remove(pp.getPiece());
		} else {
			if (jCourant.getCamp().retirer(pp.getPos()) == null) {
				System.out.println("on vole");
				System.out.println("Le joueur " + jPrecedent.getNom() + " veut voler la piece " + pp.toString());
				System.out.println("Configuration actuelle");
				System.out.println("Pyramide de " + this.joueur1().getNom());
				System.out.println(this.joueur1().getCamp().toString());
				System.out.println("Liste des pieces volees: " + this.joueur1().toStringPiecesVolees());
				System.out.println("Pyramide de " + this.joueur2().getNom());
				System.out.println(this.joueur2().getCamp().toString());
				System.out.println("Liste des pieces volees: " + this.joueur2().toStringPiecesVolees());
				System.out.println("Pyramide de jeu");
				System.out.println(this.getBaseMontagne().toString());
			}
		} // ajout de la piece volee aux pieces volees du voleur
		jPrecedent.piecesVolees.add(pp.getPiece());
	}

	public void IAannulvol(PiecePyramide pp, int joueurcourant) {
		Acteur jCourant, jPrecedent;
		if (joueurcourant == 0) {
			jCourant = this.j1;
			jPrecedent = this.j2;
		} else {
			jCourant = this.j2;
			jPrecedent = this.j1;
		}

		if (pp.getPos() == null) {
			jCourant.piecesVolees.add(pp.getPiece());
		} else {
			if (!jCourant.getCamp().empiler(pp)) {
				System.out.println("on annule un vol");
				System.out.println("Le joueur " + jPrecedent.getNom() + " annule le vol de la piece " + pp.toString());
				System.out.println("Configuration actuelle");
				System.out.println("Pyramide de " + this.joueur1().getNom());
				System.out.println(this.joueur1().getCamp().toString());
				System.out.println("Liste des pieces volees: " + this.joueur1().toStringPiecesVolees());
				System.out.println("Pyramide de " + this.joueur2().getNom());
				System.out.println(this.joueur2().getCamp().toString());
				System.out.println("Liste des pieces volees: " + this.joueur2().toStringPiecesVolees());
				System.out.println("Pyramide de jeu");
				System.out.println(this.getBaseMontagne().toString());
			}
		}
		jPrecedent.piecesVolees.remove(pp.getPiece());
	}

	public String optiIA() {
		String res = "";
		res += "\n";
		res += joueur1().getCamp().toString();
		res += joueur1().getPiecesVolees().toString();
		res += baseMontagne.toString();
		res += joueur2().getCamp().toString();
		res += joueur2().getPiecesVolees().toString();
		return res;
	}

	public Partie clonePartie() {
		Partie p = new Partie(new Acteur("clone"), new Acteur("clone"), this.numPartie);
		p.setjoueur1(this.j1.cloneActeur());
		p.setjoueur2(this.j2.cloneActeur());
		p.setBaseMontagne(this.getBaseMontagne().clonepyraB());
		p.basePieces = new ArrayList<>();
		p.joueurDebut = this.joueurDebut;
		p.joueurCourant = this.joueurCourant;
		for (Piece pp : this.basePieces) {
			p.basePieces.add(new Piece(pp.getColor()));
		}
		return p;
	}

	private void setjoueur2(Acteur cloneActeur) {
		this.j2 = cloneActeur;
	}

	private void setjoueur1(Acteur cloneActeur) {
		this.j1 = cloneActeur;
	}
}
