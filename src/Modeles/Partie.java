package Modeles;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class Partie {
	private Acteur j1;
	private Acteur j2;
	private ArrayList<Piece> basePieces;
	private PyramideMontagne baseMontagne;// base de la montagne
	private ArrayList<Coup> historique;
	public int joueurCourant;
	Piece pBleu;
	Piece pVert;
	Piece pJaune;
	Piece pRouge;
	Piece pNoir;
	Piece pBlanc;
	Piece pNaturel;
	private final int NB_PIECES_NATURELS = 2;
	private final int NB_PIECES_BLANCS = 2;

	public Partie(Acteur j1, Acteur j2) {
		this.joueurCourant = Aleatoire.genInt(0, 1);// choix du joueur al�atoire
		this.historique = new ArrayList<Coup>();
		this.basePieces = new ArrayList<Piece>();
		this.j1 = j1;
		this.j2 = j2;
		pBleu = new Piece(Couleurs.BLEU);
		pVert = new Piece(Couleurs.VERT);
		pJaune = new Piece(Couleurs.JAUNE);
		pRouge = new Piece(Couleurs.ROUGE);
		pNoir = new Piece(Couleurs.NOIR);
		pBlanc = new Piece(Couleurs.BLANC);
		pNaturel = new Piece(Couleurs.NATUREL);
		initialiserSac();
		// affichesac();
		initBaseMontagne();
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

	public boolean joueurPeutJouer(Acteur j) {
		return coupsJouables(j).isEmpty();
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
		Collections.shuffle(basePieces);// melange les pieces e piocher pour les joueurs
		System.out.println("sac initialise. Taille : " + basePieces.size());
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
		System.out.println("Base de la montagne initialisee avec au moins 3 couleurs differentes. Taille : "
				+ baseMontagne.getHauteur());
	}

	public boolean contiens(ArrayList<PiecePyramide> arr, PiecePyramide pp) {
		for (int i = 0; i < arr.size(); i++) {
			if (arr.get(i).egal(pp)) {
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
		ArrayList<PiecePyramide> piecesDoublons = new ArrayList<PiecePyramide>();
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
					if (((pJoueurCourante.getColor() == p2.getColor())) && !contiens(piecesDoublons, pp)) {
						// si la piece courante du joueur a la meme couleur que la piece courante de la
						// pyramide
						c = new Coup(pJoueurCourante, pos1, pos2);
						piecesDoublons.add(pp);
						coupsPosables.add(c);
					}
				}
			}
		}
		return coupsPosables;
	}

	public ArrayList<Coup> getHist() {
		return this.historique;
	}

	public PyramideMontagne getBaseMontagne() {
		return this.baseMontagne;
	}

	public ArrayList<Piece> getBasePieces() {
		return this.basePieces;
	}

	public int getTailleBasePieces() {
		return this.basePieces.size();
	}

	public boolean volerPiece(Acteur voleur, Acteur victime) {// voleur vole une piece au joueur victime
		ArrayList<PiecePyramide> piecesVolables = victime.getPiecesJouables();
		PiecePyramide pieceVolee = voleur.choixVol(piecesVolables);
		PyramideJoueur campVictime = victime.getCamp();
		boolean b = campVictime.retirer(pieceVolee.getPos());
		if (b) {
			voleur.addPieceVolee(pieceVolee.getPiece());// ajout de la piece volee a la liste des pieces volees du
														// joueur voleur
			return b;
		} else {// si impossible de retirer la piece
			System.err.println("La piece de peut pas etre volee.");
			return b;
		}
	}

	public static void afficherCoups(ArrayList<PiecePyramide> arr) {
		int taille = arr.size();
		for (int i = 0; i < taille; i++) {
			Piece pi = arr.get(i).getPiece();
			Position pos = arr.get(i).getPos();
			System.out.println(pi.toString() + ":" + pos.toString());
		}
	}

	public boolean estPartieFinie(int joueurCourant) {
		if (joueurCourant == 0) {
			return joueurPeutJouer(joueur1());
		} else {
			return joueurPeutJouer(joueur2());
		}
	}

	public Acteur joueur1() {
		return j1;
	}

	public Acteur joueur2() {
		return j2;
	}

	public void affichesac() {
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

	public void jouer(Coup c, int joueurcourant) {
		// retire de la pyramide joueur
		if (joueurcourant == 0) {
			this.j1.getCamp().retirer(c.getPosJ());
		} else {
			this.j2.getCamp().retirer(c.getPosJ());
		}

		// ajoute a sa pyramide
		if (c.getPosBase() != null) {
			this.baseMontagne.empiler(new PiecePyramide(c.getPiece(), c.getPosBase()));
		}
	}

	public void annulercoup(Coup c, int joueurcourant) {
		// retire de la base
		if (c.getPosBase() != null) {// si le joueur ne choisit pas de jouer une piece BLANCHE
			this.baseMontagne.retirer(c.getPosBase());
		}

		// ajoute a sa pyramide
		if (joueurcourant == 0) {
			this.j1.getCamp().empiler(new PiecePyramide(c.getPiece(), c.getPosJ()));
		} else {
			this.j2.getCamp().empiler(new PiecePyramide(c.getPiece(), c.getPosJ()));
		}
	}
}