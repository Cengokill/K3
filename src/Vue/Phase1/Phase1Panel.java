package Vue.Phase1;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Modeles.*;
import Vue.TexturePack.LoadTexture;

public class Phase1Panel extends JPanel{
	
	// PARAMETRES JEU
	public Partie partieEnCours;
	private JFrame window;
	public Dimension tailleFenetre;
	public final Dimension tailleEcran = Toolkit.getDefaultToolkit().getScreenSize();
	public final int screenWidth=tailleEcran.width;
	public final int screenHeight=tailleEcran.height;
	//drag N DROP
	public int OldX = 0;
	public int OldY = 0;
	public int currentX = 0;
	public int currentY = 0;
	private Piece pieceSelectionnee;
	//AFFICHAGE FIXE
	public int TAILLE_CUBES_HAUTEUR;
	public int TAILLE_CUBES_LARGEUR;
	
	public int posX_bouton_melange, posY_bouton_melange, hauteur_bouton, largeur_bouton;
    public int posX_bouton_annuler, posY_bouton_annuler;
	boolean enfonce_melange=false;
	
	public int POSX_BASE_JOUEUR;
	public int POSY_BASE_JOUEUR;
	
	public int POSX_PIOCHE;
	public int POSY_PIOCHE;
	
	public int POSX_BASE_MONTAGNE;
	public int POSY_BASE_MONTAGNE;
	
	//TEXTURES IMPORTEES
	public LoadTexture textures;

	// CONSTRUCTEUR----------------------------------------------
	public Phase1Panel(JFrame w, Partie partieEnCours, LoadTexture t){
		this.textures=t;
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
		affichageBoutonAnnuler(g);
		affichageBoutonMelange(g);
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
		int largeur_pixels=748;
        this.largeur_bouton=(int)(largeur_pixels/4);
        this.hauteur_bouton=(int)(largeur_bouton);
        if(tailleFenetre.width<(screenWidth*0.3) || tailleFenetre.height<screenHeight*0.3){
        	this.largeur_bouton=(int)(largeur_pixels/8);
            this.hauteur_bouton=(int)(largeur_bouton);
        }
        else if(tailleFenetre.width<(screenWidth*0.45) || tailleFenetre.height<(screenHeight*0.45) ) {
        	this.largeur_bouton=(int)(largeur_pixels/5);
            this.hauteur_bouton=(int)(largeur_bouton);
        }
		// largeur 211, hauteur 259. 211/259 = 0.8146718147
		TAILLE_CUBES_LARGEUR = tailleFenetre.height/16;
		TAILLE_CUBES_HAUTEUR = (int)(TAILLE_CUBES_LARGEUR/0.8146718147);//rapport de 211/259
		
		//Position objet
		this.POSX_BASE_JOUEUR = tailleFenetre.height/10;
		this.POSY_BASE_JOUEUR = tailleFenetre.height/100;
		
		this.POSX_PIOCHE = 0;
		this.POSY_PIOCHE = POSY_BASE_JOUEUR+(TAILLE_CUBES_HAUTEUR+1)*6+30;
		
		this.POSX_BASE_MONTAGNE = POSX_BASE_JOUEUR+TAILLE_CUBES_LARGEUR*9;
		this.POSY_BASE_MONTAGNE = POSY_BASE_JOUEUR-(int)(TAILLE_CUBES_HAUTEUR*0.75);
		
		posX_bouton_melange=POSX_BASE_JOUEUR-TAILLE_CUBES_LARGEUR;
		posY_bouton_melange=POSY_PIOCHE+TAILLE_CUBES_HAUTEUR*2;
	    posX_bouton_annuler=posX_bouton_melange+largeur_bouton+TAILLE_CUBES_LARGEUR/2;
	    posY_bouton_annuler=posY_bouton_melange;
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
			return textures.pieceVide;
		}
		else {
			Couleurs colorP = p.getColor();
			if(colorP == Couleurs.BLEU) {
				return textures.pieceBleu;
			}
			else if(colorP == Couleurs.VERT) {
				return textures.pieceVert;
			}else if(colorP == Couleurs.JAUNE) {
				return textures.pieceJaune;
			}else if(colorP == Couleurs.ROUGE) {
				return textures.pieceRouge;
			}else if(colorP == Couleurs.NOIR) {
				return textures.pieceNoire;
			}else if(colorP == Couleurs.BLANC) {
				return textures.pieceBlanche;
			}else if(colorP == Couleurs.NATUREL) {
				return textures.pieceNature;
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
	    g.drawImage(textures.background, 0, 0, tailleFenetre.width, tailleFenetre.height,null);
	}
	
	// AFFICHAGE BOUTONSE-------------------------------------------------------------
	public void affichageBoutonAnnuler(Graphics g) {
		g.drawImage(textures.boutonAnnuler, posX_bouton_annuler, posY_bouton_annuler, largeur_bouton, hauteur_bouton,null);
	}
	
	public void affichageBoutonMelange(Graphics g) {
		if(!enfonce_melange) {
			g.drawImage(textures.boutonMelange, posX_bouton_melange, posY_bouton_melange, largeur_bouton, hauteur_bouton, null);
		}else {
			g.drawImage(textures.boutonMelange_presse, posX_bouton_melange, posY_bouton_melange, largeur_bouton, hauteur_bouton, null);
		}
	}
	
	// AFFICHAGE PIECE SELECTIONNEE-------------------------------------------------------------
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