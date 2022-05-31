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
	public boolean partieEnCoursSet = false;
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
	
	public int posX_bouton_melange, posY_bouton_melange, hauteur_bouton, largeur_bouton,posX_bouton_valider,
	posY_bouton_valider, posX_chrono, posY_chrono, hauteur_chrono, largeur_chrono,posX_text_chrono,posY_text_chrono,
	posX_nom_joueur,posY_nom_joueur,taille_nom_joueur,taille_police_nom_joueur,taille_police_timer;
    
	boolean enfonce_melange=false;
	boolean enfonce_valider=false;
	
	public int POSX_BASE_JOUEUR;
	public int POSY_BASE_JOUEUR;
	
	public int POSX_PIOCHE;
	public int POSY_PIOCHE;
	
	public int POSX_BASE_MONTAGNE;
	public int POSY_BASE_MONTAGNE;
	
	//TEXTURES IMPORTEES
	public LoadTexture textures;
	//SON
	private SoundPlayer simpleSoundPlayerSon;

	// CONSTRUCTEUR----------------------------------------------
	public Phase1Panel(JFrame w, Partie p, LoadTexture t){
		this.textures=t;
		this.simpleSoundPlayerSon = new SoundPlayer(8, textures.CHEMIN);
		this.partieEnCours=p;
		this.window = w;
		this.tailleFenetre = window.getSize();
		addMouseListener(new ecouteurClick(this));
		this.window.setSize(tailleFenetre.width,tailleFenetre.height);
		this.window.setExtendedState(JFrame.MAXIMIZED_BOTH);
		//changementTaillefenetre();
	}
	
	public void setPartieEnCours(Partie p) {
		this.partieEnCours=p;
		this.partieEnCoursSet = true;
	}
	// FONCTION POUR AFFICHER TOUT LES ELEMENTS VISUELS----------------------------------------
	public void paint(Graphics g) {
		if(this.partieEnCoursSet == true) {
			if(tailleFenetre != window.getSize()) {
				//on detecte un changement de fenetre -> on met a jour L IHM
				changementTaillefenetre();
			}
			affichageBackGround(g);
			if(initAffichageJoueurs().getClass() == Joueur.class) {
				affichageBoutonMelange(g);
				affichageBoutonValider(g);
			}
			afficherTimer(g);
			
			afficheBaseMontagne(g);
			affichePyramideJoueur1(g);
			affichePioche(g);
			dragNdrop(g);
		}
	}
	
	// CALCUL ***************************************************************
	
	// FONCTION POUR REDIMENTIONNER LES ELEMENTS----------------------------------------------
	public void changementTaillefenetre() {
		tailleFenetre = window.getSize();
		//taille objet
        largeur_bouton=(int)(tailleFenetre.width/12);
        hauteur_bouton=(int)(largeur_bouton);
        
        double rapport=0.8576709797;
        double rapport_chrono=1.395;//400x558
		TAILLE_CUBES_LARGEUR = tailleFenetre.width/28;//541
		TAILLE_CUBES_HAUTEUR = (int)(TAILLE_CUBES_LARGEUR*rapport);//464
		
		//Position objet
		POSX_BASE_JOUEUR = tailleFenetre.height/10;
		POSY_BASE_JOUEUR = tailleFenetre.height/3;
		
		POSX_PIOCHE = 0;
		POSY_PIOCHE = POSY_BASE_JOUEUR+(TAILLE_CUBES_HAUTEUR+1)*6+30;
		
		POSX_BASE_MONTAGNE = POSX_BASE_JOUEUR+TAILLE_CUBES_LARGEUR*9;
		POSY_BASE_MONTAGNE = POSX_BASE_JOUEUR;
		
		posX_chrono = POSX_BASE_JOUEUR+2*TAILLE_CUBES_LARGEUR;
		posY_chrono = POSY_BASE_JOUEUR-(int)(3.8*TAILLE_CUBES_HAUTEUR);
		largeur_chrono = (int)(TAILLE_CUBES_LARGEUR*2);
		hauteur_chrono = (int)(largeur_chrono*rapport_chrono);
		
		taille_police_timer = (int)(hauteur_chrono/8);
		posX_text_chrono = posX_chrono+(int)(taille_police_timer*1.8);
		posY_text_chrono = (int)(posY_chrono+hauteur_chrono*0.94);
		
		taille_nom_joueur = initAffichageJoueurs().getNom().length();
		taille_police_nom_joueur=(int)(hauteur_chrono/6);
		posX_nom_joueur = posX_chrono-(int)(taille_police_nom_joueur*1.2);
		posY_nom_joueur = posY_chrono-TAILLE_CUBES_HAUTEUR;
		
		posX_bouton_melange=POSX_BASE_JOUEUR-TAILLE_CUBES_LARGEUR;
		posY_bouton_melange=POSY_PIOCHE+TAILLE_CUBES_HAUTEUR*2;
	    posX_bouton_valider=posX_bouton_melange+largeur_bouton+TAILLE_CUBES_LARGEUR/2;
	    posY_bouton_valider=posY_bouton_melange;
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
				return textures.pieceBleue;
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
		g.setFont(new Font("Courier New", Font.BOLD, taille_police_nom_joueur));
		g.drawString(nom, posX_nom_joueur, posY_nom_joueur);
	}
	

	public void afficherTimer(Graphics g) {
		double temps;
		int jCourant=this.partieEnCours.getJoueurCourant();
		if(jCourant==0) {
			temps=this.partieEnCours.joueur1().getTempsConstruction();
		}else {
			temps=this.partieEnCours.joueur2().getTempsConstruction();
		}
		String tempsAffiche=Double.toString(Math.round(temps*10.0)/10.0);
		g.drawImage(textures.chrono, posX_chrono, posY_chrono, largeur_chrono, hauteur_chrono, null);
		g.setColor(Color.BLUE);
		g.setFont(new Font("Courier New", Font.BOLD, taille_police_timer));
		g.drawString(tempsAffiche, posX_text_chrono, posY_text_chrono);
	}
	
	// AFFICHAGE FOND D ECRAN -------------------------------------------------------------------
	public void affichageBackGround(Graphics g) {
	    g.drawImage(textures.backgroundPhase1, 0, 0, tailleFenetre.width, tailleFenetre.height,null);
	}
	
	// AFFICHAGE BOUTONS-------------------------------------------------------------
	public void affichageBoutonValider(Graphics g) {
		if(initAffichageJoueurs().getTaillePiecesPiochees()>0) {
			g.drawImage(textures.boutonValider_gris, posX_bouton_valider, posY_bouton_valider, largeur_bouton, hauteur_bouton, null);
		}else if(!enfonce_valider) {
			g.drawImage(textures.boutonValider, posX_bouton_valider, posY_bouton_valider, largeur_bouton, hauteur_bouton, null);
		}else {
			g.drawImage(textures.boutonValider_presse, posX_bouton_valider, posY_bouton_valider, largeur_bouton, hauteur_bouton, null);
		}
	}
	
	public void affichageBoutonMelange(Graphics g) {
		if(!enfonce_melange) {
			g.drawImage(textures.boutonMelange, posX_bouton_melange, posY_bouton_melange, largeur_bouton, hauteur_bouton, null);
		}else {
			g.drawImage(textures.boutonMelange_presse, posX_bouton_melange, posY_bouton_melange, largeur_bouton, hauteur_bouton, null);
		}
	}
	
	public void emettreSonClic() {
		this.simpleSoundPlayerSon.setNumSon(17);
		this.simpleSoundPlayerSon.jouerSon();
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
		int largeurCube=TAILLE_CUBES_LARGEUR;
		int hauteurCube=TAILLE_CUBES_HAUTEUR;
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