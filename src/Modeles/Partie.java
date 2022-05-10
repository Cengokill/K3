package Modeles;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

public class Partie {
	private ActeurClasse j1;
	private ActeurClasse j2;
	private ArrayList<Piece> basePieces;// pieces disponibles e se partager entre les joueurs, uniquement e la creation
										// du jeu
	private PyramideMontagne baseMontagne;// base de la montagne
	private LinkedList<Coup> historique;
	Piece pBleu;
	Piece pVert;
	Piece pJaune;
	Piece pRouge;
	Piece pNoir;
	Piece pBlanc;
	Piece pNaturel;

	public Partie(ActeurClasse j1, ActeurClasse j2) {
		this.historique = new LinkedList<Coup>();
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
		initBaseMontagne();
	}

	public void initialiserSac() {// ajoute toutes les pieces au sac
		int nb_pieces_par_couleur = 9;
		int nb_naturels = 6;
		int nb_blancs = 4;
		for (int i = 0; i < nb_pieces_par_couleur; i++) {// uniquement pour la base de la montagne
			basePieces.add(pBleu);
			basePieces.add(pVert);
			basePieces.add(pJaune);
			basePieces.add(pRouge);
			basePieces.add(pNoir);
		}
		for (int i = 0; i < nb_naturels; i++) {// uniquement pour les joueurs
			basePieces.add(pNaturel);
		}
		for (int i = 0; i < nb_blancs; i++) {// uniquement pour les joueurs
			basePieces.add(pBlanc);
		}
		Collections.shuffle(basePieces);// melange les pieces e piocher pour les joueurs
		System.out.println("sac initialise. Taille : " + basePieces.size());
	}

	public void initCampJoueur(Joueur jou) {// creation du camp du joueur j
		/*
		 * for (int i = 0; i < 6; i++) {// hauteur
		 * for (int j = 0; j < 6 - i; j++) {// largeur
		 * Piece element = basePieces.get(0);
		 * Position pos = new Position(j, i);
		 * PiecePyramide pp = new PiecePyramide(element, pos);
		 * jou.getCamp().empiler(pp);
		 * basePieces.remove(0);
		 * //Piece pAffich = jou.getCamp().getPiece(pos);
		 * // System.out.println("piece "+pos.x+","+pos.y+" ajoutee au camp de
		 * // "+jou.getNom()+" : "+pAffich.toString());
		 * }
		 * }
		 */

		System.out.println(jou.getCamp().toString());
		System.out.println("Camp de " + jou.getNom() + " initialise.");
	}

	public void initBaseMontagne() {// creation de la base de la montagne constituee de 9 pieces
		baseMontagne = new PyramideMontagne(9, 9);// 9 etages, 9 pieces au dernier etage
		for (int i = 0; i < 9; i++) {
			Piece element = basePieces.get(0);
			Position pos = new Position(i, 0);
			PiecePyramide pp = new PiecePyramide(element, pos);
			baseMontagne.empiler(pp);
			basePieces.remove(0);
		}
		System.out.println("Base de la montagne initialisee. Taille : " + baseMontagne.getHauteur());
		System.out.println("Le sac a maintenant une taille de " + basePieces.size() + " pieces.");
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

	public ArrayList<Piece> getBasePieces() {
		return this.basePieces;
	}

	public int getTailleBasePieces() {
		return this.basePieces.size();
	}

	public boolean volerPiece(Joueur voleur, Joueur victime, PiecePyramide pp) {// voleur vole une piece au joueur
																				// victime
		PyramideJoueur campVictime = victime.getCamp();
		boolean b = campVictime.retirer(pp);

		if (b) {
			voleur.addPieceVolee(pp.getPiece());// ajout de la piece volee a la liste des pieces volees du joueur voleur
			return b;
		} else {// si impossible de retirer la piece
			System.err.println("La piece de peut pas etre volee.");
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

	public ActeurClasse joueur1() {
		return j1;
	}

	public ActeurClasse joueur2() {
		return j2;
	}
}
