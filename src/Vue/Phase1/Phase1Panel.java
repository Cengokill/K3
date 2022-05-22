package Vue.Phase1;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
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
	public final String CHEMIN = System.getProperty("user.dir")+"/src/Ressources/";
	
	public final String NOMBACKGROUND = "background.jpg";
	public Image background = Toolkit.getDefaultToolkit().createImage(CHEMIN+NOMBACKGROUND);
	
	public final String NOMPIECEVIDE = "EMPTY3.png";
	public Image pieceVide = Toolkit.getDefaultToolkit().createImage(CHEMIN+NOMPIECEVIDE);
	
	public final String NOMPIECEBLACK = "BLACK2.png";
	public Image pieceNoire = Toolkit.getDefaultToolkit().createImage(CHEMIN+NOMPIECEBLACK);
	
	public final String NOMPIECEBLEU = "BLUE2.png";
	public Image pieceBleu = Toolkit.getDefaultToolkit().createImage(CHEMIN+NOMPIECEBLEU);
	
	public final String NOMPIECEVERT = "GREEN2.png";
	public Image pieceVert = Toolkit.getDefaultToolkit().createImage(CHEMIN+NOMPIECEVERT);
	
	public final String NOMPIECEROUGE = "RED2.png";
	public Image pieceRouge = Toolkit.getDefaultToolkit().createImage(CHEMIN+NOMPIECEROUGE);
	
	public final String NOMPIECEBLANC = "WHITE2.png";
	public Image pieceBlanche = Toolkit.getDefaultToolkit().createImage(CHEMIN+NOMPIECEBLANC);
	
	public final String NOMPIECENATURE = "WOOD2.png";
	public Image pieceNature = Toolkit.getDefaultToolkit().createImage(CHEMIN+NOMPIECENATURE);
	
	public final String NOMPIECEJAUNE = "YELLOW2.png";
	public Image pieceJaune = Toolkit.getDefaultToolkit().createImage(CHEMIN+NOMPIECEJAUNE);
	
	// CONSTRUCTEUR----------------------------------------------
	public Phase1Panel(JFrame w, Partie partieEnCours){
		this.partieEnCours=partieEnCours;
		this.window = w;
		this.tailleFenetre = window.getSize();
		this.window.setSize(tailleFenetre.width,tailleFenetre.height);
		this.window.setExtendedState(JFrame.MAXIMIZED_BOTH);
		changementTaillefenetre();
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
		affichePyramideJoueur1(g);
		affichePioche(g);
		dragNdrop(g);
	}
	
	// CALCUL ***************************************************************
	
	// FONCTION POUR REDIMENTIONNER LES ELEMENTS----------------------------------------------
	public void changementTaillefenetre() {
		tailleFenetre = window.getSize();
		//taille objet
		// largeur 211, hauteur 259. 211/259 = 0.8146718147
		TAILLE_CUBES_LARGEUR = tailleFenetre.height/16;
		TAILLE_CUBES_HAUTEUR = (int)(TAILLE_CUBES_LARGEUR/0.8146718147);//rapport de 211/259
		
		LARGEUR_PIECE_CHOISIE = tailleFenetre.width/18;
		HAUTEUR_PIECE_CHOISIE = (int)(LARGEUR_PIECE_CHOISIE/0.8146718147);
		
		//Position objet
		this.POSX_PIECE_CHOISIE = 0;
		this.POSY_PIECE_CHOISIE = 0;
		
		this.POSX_BASE_JOUEUR = tailleFenetre.height/10;
		this.POSY_BASE_JOUEUR = HAUTEUR_PIECE_CHOISIE*2;
		
		this.POSX_PIOCHE = 0;
		this.POSY_PIOCHE = POSY_BASE_JOUEUR+(TAILLE_CUBES_HAUTEUR+1)*6+30;
		
		this.POSX_BASE_MONTAGNE = POSX_BASE_JOUEUR+TAILLE_CUBES_LARGEUR*9;
		this.POSY_BASE_MONTAGNE = POSY_BASE_JOUEUR-(int)(TAILLE_CUBES_HAUTEUR*0.75);
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
	public Image getpetitcolor(Piece p) {
		if(p == null) {
			return pieceVide;
		}
		else {
			Couleurs colorP = p.getColor();
			if(colorP == Couleurs.BLEU) {
				return pieceBleu;
			}
			else if(colorP == Couleurs.VERT) {
				return pieceVert;
			}else if(colorP == Couleurs.JAUNE) {
				return pieceJaune;
			}else if(colorP == Couleurs.ROUGE) {
				return pieceRouge;
			}else if(colorP == Couleurs.NOIR) {
				return pieceNoire;
			}else if(colorP == Couleurs.BLANC) {
				return pieceBlanche;
			}else if(colorP == Couleurs.NATUREL) {
				return pieceNature;
			}else {
				return null;
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
		int jCourant=this.partieEnCours.getJoueurCourant();
		if(jCourant==0) {
			nom=this.partieEnCours.joueur1().getNom();
		}else {
			nom=this.partieEnCours.joueur2().getNom();
		}
		g.setColor(Color.BLACK);
		g.setFont(new Font("Courier New", Font.BOLD, 28));
		g.drawString(nom, POSX_BASE_JOUEUR+2*TAILLE_CUBES_LARGEUR, POSY_BASE_JOUEUR-TAILLE_CUBES_HAUTEUR/2);
	}
	
	// AFFICHAGE FOND D ECRAN -------------------------------------------------------------------
	
	public void affichageBackGround(Graphics g) {
	    g.drawImage(background, 0, 0, tailleFenetre.width, tailleFenetre.height,null);
	}
	
	// AFFICHAGE PIECE SELECTIONNEE-------------------------------------------------------------
	//affiche le cube en haut a gauche avec la couleur de la piece selectionnee
	public void affichagePieceSelectionee(Graphics g) {
		g.drawImage(getpetitcolor(pieceSelectionnee), POSX_PIECE_CHOISIE, POSY_PIECE_CHOISIE, LARGEUR_PIECE_CHOISIE, HAUTEUR_PIECE_CHOISIE,null);
		//g.setColor();
		//g.fillRect(POSX_PIECE_CHOISIE, POSY_PIECE_CHOISIE, LARGEUR_PIECE_CHOISIE, HAUTEUR_PIECE_CHOISIE);
	}
	
	public void dragNdrop(Graphics g) {
		if(pieceSelectionnee != null) {
			g.drawImage(getpetitcolor(pieceSelectionnee), currentX, currentY, (int)(TAILLE_CUBES_LARGEUR/1.5), (int)(TAILLE_CUBES_HAUTEUR/1.5),null);
		}
	}
	// AFFICHAGE PYRAMIDE----------------------------------------------------------------------
	public void affichePyramideJoueur1(Graphics g) {
		Acteur a = initAffichageJoueurs();
		Position POSYitionPiecePyramide;
		int decalage=0;
		for(int etage = 0; etage < a.getCamp().getHauteur(); etage++) {
			for(int rang = 0; rang < a.getCamp().getLargeur() - etage; rang++) {
				POSYitionPiecePyramide = new Position(etage,rang);
				Piece pieceJoueur = a.getCamp().getPiece(POSYitionPiecePyramide);
				g.drawImage(getpetitcolor(pieceJoueur), decalage+rang*(TAILLE_CUBES_LARGEUR+1)+POSX_BASE_JOUEUR, POSY_BASE_JOUEUR+5*(TAILLE_CUBES_HAUTEUR+1) - etage*(TAILLE_CUBES_HAUTEUR+1), TAILLE_CUBES_LARGEUR, TAILLE_CUBES_HAUTEUR,null);
			}
			decalage += (TAILLE_CUBES_LARGEUR+1)/2;
		}
	}
	
	public void afficheBaseMontagne(Graphics g) {
		PyramideMontagne m = this.partieEnCours.getBaseMontagne();
		Position POSYitionPiecePyramide;
		afficherNomJoueur(g);
		int largeurCube=(int)(TAILLE_CUBES_LARGEUR*0.75);
		int hauteurCube=(int)(TAILLE_CUBES_HAUTEUR*0.75);
		int decalage=0;
		for(int etage = 0; etage < m.getHauteur(); etage++) {
			for(int rang = 0; rang < m.getLargeur() - etage; rang++) {
				POSYitionPiecePyramide = new Position(etage,rang);
				Piece piece = m.getPiece(POSYitionPiecePyramide);
				g.drawImage(getpetitcolor(piece), decalage+rang*(largeurCube+1)+POSX_BASE_MONTAGNE, POSY_BASE_MONTAGNE+8*(hauteurCube+1) - etage*(hauteurCube+1), largeurCube, hauteurCube,null);
			}
			decalage += (largeurCube+1)/2;
		}
	}
	
	// AFFICHAGE PIOCHE----------------------------------------------------------------------
	public void affichePioche(Graphics g) {
		Acteur a = initAffichageJoueurs();
		for(int i = 0; i < a.getPiecesPiochees().size(); i++) {
			Piece p = a.getPiecesPiochees().get(i);
			g.drawImage(getpetitcolor(p), i*(TAILLE_CUBES_LARGEUR+1)+POSX_PIOCHE, POSY_PIOCHE, TAILLE_CUBES_LARGEUR, TAILLE_CUBES_HAUTEUR,null);
		}
	}
	
}