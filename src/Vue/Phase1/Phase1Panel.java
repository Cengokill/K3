package Vue.Phase1;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Modeles.*;

public class Phase1Panel extends JPanel{
	
	// PARAMETRE JEU
	private Partie partieEnCours;
	private JFrame window;
	public Dimension tailleFenetre;	
	//drag N DROP
	public int OldX = 0;
	public int OldY = 0;
	public int currentX = 0;
	public int currentY = 0;
	private Piece pieceSelectionnee;
	//AFFICHAGE FIXE
	public int TAILLE_CUBES_HAUTEUR;
	public int TAILLE_CUBES_LARGEUR;
	
	public int POSX_PIECE_CHOISIE;
	public int POSY_PIECE_CHOISIE;
	public int LARGEUR_PIECE_CHOISIE;
	public int HAUTEUR_PIECE_CHOISIE;
	
	public int POSX_BASE_JOUEUR;
	public int POSY_BASE_JOUEUR;
	
	public int POSX_PIOCHE;
	public int POSY_PIOCHE;
	
	public int POSX_BASE_MONTAGNE;
	public int POSY_BASE_MONTAGNE;
	//COMPOSYANT IMPORTER
	public final String CHEMIN = "./src/Ressources/";
	public final String NOMBACKGROUND = "background.png";
	
	// CONSTRUCTEUR----------------------------------------------
	public Phase1Panel(JFrame window, Partie partieEnCours){
		this.partieEnCours=partieEnCours;
		this.window = window;
		changementTaillefenetre();
	}
	
	// CALCUL ***************************************************************
	
	// FONCTION POUR REDIMENTIONNER LES ELEMENTS----------------------------------------------
	public void changementTaillefenetre() {
		tailleFenetre = window.getSize();
		//taille objet
		TAILLE_CUBES_HAUTEUR = tailleFenetre.height/30;
		TAILLE_CUBES_LARGEUR = tailleFenetre.width/50;
		HAUTEUR_PIECE_CHOISIE = tailleFenetre.height/15;
		LARGEUR_PIECE_CHOISIE = tailleFenetre.width/25;
		//Position objet
		this.POSX_PIECE_CHOISIE = 0;
		this.POSY_PIECE_CHOISIE = 0;
		
		this.POSX_BASE_JOUEUR = 0;
		this.POSY_BASE_JOUEUR = HAUTEUR_PIECE_CHOISIE;
		
		this.POSX_PIOCHE = 0;
		this.POSY_PIOCHE = POSY_BASE_JOUEUR+(TAILLE_CUBES_HAUTEUR+1)*(partieEnCours.joueur1().getCamp().getHauteur()+2);
		
		this.POSX_BASE_MONTAGNE = 0;
		this.POSY_BASE_MONTAGNE = POSY_PIOCHE+TAILLE_CUBES_HAUTEUR+1;
	}
	
	// PIECE SELECTIONEE----------------------------------------------
	public Piece getPieceSelectionnee() {
		return pieceSelectionnee;
	}
	public void setPieceSelectionnee(Piece p) {
		pieceSelectionnee = p;
		this.repaint();
	}
	
	// FONCTION QUI PERMET DE RENVOYER L IMAGE DE LA PIECE
	public Color getpetitcolor(Piece p) {
		if(p == null) {
			return Color.GRAY;
		}
		else {
			Couleurs colorP = p.getColor();
			if(colorP == Couleurs.BLEU) {
				return Color.BLUE;
			}
			else if(colorP == Couleurs.VERT) {
				return Color.GREEN;
			}else if(colorP == Couleurs.JAUNE) {
				return Color.YELLOW;
			}else if(colorP == Couleurs.ROUGE) {
				return Color.RED;
			}else if(colorP == Couleurs.NOIR) {
				return Color.BLACK;
			}else if(colorP == Couleurs.BLANC) {
				return Color.WHITE;
			}else if(colorP == Couleurs.NATUREL) {
				return Color.ORANGE;
			}else {
				return Color.MAGENTA;
			}
		}	
	}
	
	// INTERACTION ACTEUR COURANT----------------------------------------------
	public Acteur initAffichageJoueurs() {
		Acteur a;
		if(this.partieEnCours.getJoueurCourant()==0) {
			a=this.partieEnCours.joueur1();
		}else {
			a=this.partieEnCours.joueur2();
		}
		return a;
	}
	
	public void empiler(Position POSYitionPiecePyramide) {
		Acteur a = initAffichageJoueurs();
		PiecePyramide pp=new PiecePyramide(pieceSelectionnee,POSYitionPiecePyramide);
		a.setPiecesPosees(pp);
		pieceSelectionnee = null;
		this.repaint();
	}
	
	
	
	
	// AFFICHAGE***************************************************************
	
	public void afficherNomJoueur(Graphics g) {
		String nom;
		if(this.partieEnCours.getJoueurCourant()==0) {
			nom = this.partieEnCours.joueur1().getNom();
		}else {
			nom = this.partieEnCours.joueur2().getNom();
		}
		g.setColor(Color.BLACK);
		g.setFont(new Font("Courier New", Font.BOLD, 25));
		g.drawString(nom, 300, POSY_BASE_JOUEUR-POSY_PIECE_CHOISIE+TAILLE_CUBES_HAUTEUR);
	}
	
	// AFFICHAGE FOND D ECRAN -------------------------------------------------------------------
	
	public void affichageBackGround(Graphics g) {
		Image background = Toolkit.getDefaultToolkit().createImage(CHEMIN+NOMBACKGROUND);
	    g.drawImage(background, 0, 0, tailleFenetre.width, tailleFenetre.height,null);
	}
	
	// AFFICHAGE PIECE SELECTIONNEE-------------------------------------------------------------
	//affiche le cube en haut a gauche avec la couleur de la piece selectionnee
	public void affichagePieceSelectionee(Graphics g) {
		g.setColor(getpetitcolor(pieceSelectionnee));
		g.fillRect(POSX_PIECE_CHOISIE, POSY_PIECE_CHOISIE, LARGEUR_PIECE_CHOISIE, HAUTEUR_PIECE_CHOISIE);
	}
	
	public void dragNdrop(Graphics g) {
		if(pieceSelectionnee != null) {
			/*
			g.setColor(this.getBackground()); // petite trainner a supprimer
			g.fillRect(OldX, OldY, 10, 10);
			*/
			g.setColor(getpetitcolor(pieceSelectionnee));
			g.fillRect(currentX, currentY, (int)(TAILLE_CUBES_LARGEUR/1.5), (int)(TAILLE_CUBES_HAUTEUR/1.5));
		}
	}
	// AFFICHAGE PYRAMIDE----------------------------------------------------------------------
	public void affichePyramide(Graphics g) {
		Acteur a = initAffichageJoueurs();
		Position POSYitionPiecePyramide;
		int decalage=0;
		for(int etage = 0; etage < a.getCamp().getHauteur(); etage++) {
			for(int rang = 0; rang < a.getCamp().getLargeur() - etage; rang++) {
				POSYitionPiecePyramide = new Position(etage,rang);
				Piece pieceJoueur = a.getCamp().getPiece(POSYitionPiecePyramide);
				g.setColor(getpetitcolor(pieceJoueur));
				g.fillRect(decalage+rang*(TAILLE_CUBES_LARGEUR+1)+POSX_BASE_JOUEUR, POSY_BASE_JOUEUR+(a.getCamp().getHauteur()-1)*(TAILLE_CUBES_HAUTEUR+1) - etage*(TAILLE_CUBES_HAUTEUR+1), TAILLE_CUBES_LARGEUR, TAILLE_CUBES_HAUTEUR);
			}
			decalage += (TAILLE_CUBES_LARGEUR+1)/2;
		}
	}
	
	public void afficheBaseMontagne(Graphics g) {
		PyramideMontagne m = this.partieEnCours.getBaseMontagne();
		Position POSYitionPiecePyramide;
		afficherNomJoueur(g);
		int decalage=0;
		for(int etage = 0; etage < m.getHauteur(); etage++) {
			for(int rang = 0; rang < m.getLargeur() - etage; rang++) {
				POSYitionPiecePyramide = new Position(etage,rang);
				Piece piece = m.getPiece(POSYitionPiecePyramide);
				g.setColor(getpetitcolor(piece));
				g.fillRect(decalage+rang*(TAILLE_CUBES_LARGEUR+1)+POSX_BASE_MONTAGNE , POSY_BASE_MONTAGNE+m.getHauteur()*(TAILLE_CUBES_HAUTEUR+1) - etage*(TAILLE_CUBES_HAUTEUR+1), TAILLE_CUBES_LARGEUR, TAILLE_CUBES_HAUTEUR);
			}
			decalage += (TAILLE_CUBES_LARGEUR+1)/2;
		}
	}
	
	// AFFICHAGE PIOCHE----------------------------------------------------------------------
	public void affichePioche(Graphics g) {
		Acteur a = initAffichageJoueurs();
		for(int i = 0; i < a.getPiecesPiochees().size(); i++) {
			Piece p = a.getPiecesPiochees().get(i);
			g.setColor(getpetitcolor(p));
			g.fillRect(i*(TAILLE_CUBES_LARGEUR+1)+POSX_PIOCHE, POSY_PIOCHE, TAILLE_CUBES_LARGEUR, TAILLE_CUBES_HAUTEUR);
		}
	}
	
	// FONCTION POUR AFFICHER TOUT LES ELEMENTS VISUELS----------------------------------------
	public void paint(Graphics g) {
		if(tailleFenetre != window.getSize()) {
			//on detecte un changement de fenetre -> on met a jour L IHM
			changementTaillefenetre();
		}
		affichageBackGround(g);
		affichagePieceSelectionee(g);
		afficheBaseMontagne(g);
		affichePyramide(g);
		affichePioche(g);
		dragNdrop(g);
	}
	
}
