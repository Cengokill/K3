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
import Vue.PanelGeneral;
import Vue.Menu.Chargement;
import Vue.TexturePack.LoadTexture;

public class Phase1Panel extends PanelGeneral{
	
	// PARAMETRES JEU
	public Partie partieEnCours;
	public boolean partieEnCoursSet = false;
	private JFrame window;
	public Chargement chargement;
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
	posX_nom_joueur,posY_nom_joueur,taille_nom_joueur,taille_police_nom_joueur,taille_police_timer,posXRetourMenu,
	posYRetourMenu,largeurRetourMenu,hauteurRetourMenu, posX_popup,posY_popup,largeur_popup,hauteur_popup,
	largeur_oui,hauteur_oui,posX_oui,posY_oui,posX_non,posY_non,posX_settings,posY_settings,largeur_settings,
	posX_cadre,posY_cadre,largeur_cadre,hauteur_cadre;
    
	boolean enfonce_melange=false;
	boolean enfonce_valider=false;
	boolean popup=false;
	
	public int POSX_BASE_JOUEUR;
	public int POSY_BASE_JOUEUR;
	
	public int POSX_PIOCHE;
	public int POSY_PIOCHE;
	public int coupure=10;
	
	public int POSX_BASE_MONTAGNE;
	public int POSY_BASE_MONTAGNE;
	
	//TEXTURES IMPORTEES
	public LoadTexture textures;
	//SON
	private SoundPlayer simpleSoundPlayerSon;

	// CONSTRUCTEUR----------------------------------------------
	public Phase1Panel(JFrame w, Partie p, LoadTexture t, OptionsJeu o, Chargement c){
		super(w,t,o);
		this.textures=t;
		this.simpleSoundPlayerSon = new SoundPlayer(8, textures.CHEMIN);
		this.partieEnCours=p;
		this.window = w;
		this.chargement=c;
		addMouseListener(new ecouteurClick(this));
	}
	
	public void setPartieEnCours(Partie p) {
		this.partieEnCours=p;
		this.partieEnCoursSet = true;
	}
	// FONCTION POUR AFFICHER TOUT LES ELEMENTS VISUELS----------------------------------------
	public void paint(Graphics g) {
		if(this.partieEnCoursSet == true) {
			changementTaillefenetre();
			affichageBackGround(g,2);
			if(initAffichageJoueurs().getClass() == Joueur.class) {
				affichageBoutonMelange(g);
				affichageBoutonValider(g);
			}
			afficherTimer(g);
			cadreJoueur(g);
			afficheBaseMontagne(g);
			affichePyramideJoueur1(g);
			affichePioche(g);
			affichageRetourMenu(g);
			affichageSettings(g);
			dragNdrop(g);
			affichePopup(g);
		}
	}
	
	// CALCUL ***************************************************************
	
	// FONCTION POUR REDIMENTIONNER LES ELEMENTS----------------------------------------------
	public void changementTaillefenetre() {
		setChangementTaillefenetre();
		//taille objet
        largeur_bouton=Math.min(largeur_background/12, frameWidth/12);
        hauteur_bouton=largeur_bouton;//pas de rapport puisque 1x1
        
        double rapport=0.8576709797;
        double rapport_chrono=1.395;//400x558
        double rapportBackMenu = 1.185567010309278;
        double rapportPopup = 0.5103280680437424;
        double rapportOui = 0.4183168316831683;// 169/404
        double rapportCadre = 0.2200647249190939;// 204/927
		TAILLE_CUBES_LARGEUR = Math.min(largeur_background/28, frameWidth/28);
		TAILLE_CUBES_HAUTEUR = (int)(TAILLE_CUBES_LARGEUR*rapport);//464
		
		//base joueur
		POSX_BASE_JOUEUR = posX_background+(int)(largeur_background*0.11);
		POSY_BASE_JOUEUR = posY_background+(int)(hauteur_background*0.37);
		//pioche
		POSX_PIOCHE = posX_background+(int)(largeur_background*0.17);
		POSY_PIOCHE = posY_background+(int)(hauteur_background*0.76);
		//base montagne
		POSX_BASE_MONTAGNE = posX_background+(int)(largeur_background*0.61);
		POSY_BASE_MONTAGNE = POSY_BASE_JOUEUR-(int)(TAILLE_CUBES_HAUTEUR*3.4);
		//chrono
		posX_chrono = POSX_BASE_JOUEUR+2*TAILLE_CUBES_LARGEUR;
		posY_chrono = POSY_BASE_JOUEUR-(int)(3.8*TAILLE_CUBES_HAUTEUR);
		largeur_chrono = (int)(TAILLE_CUBES_LARGEUR*2);
		hauteur_chrono = (int)(largeur_chrono*rapport_chrono);
		
		taille_police_timer = (int)(hauteur_chrono/8);
		posX_text_chrono = posX_chrono+(int)(taille_police_timer*1.8);
		posY_text_chrono = (int)(posY_chrono+hauteur_chrono*0.94);
		//cadre joueur
		largeur_cadre = Math.min(largeur_background/6, frameWidth/6);
		hauteur_cadre = (int)(largeur_cadre*rapportCadre);
		posX_cadre = posX_background+(int)(largeur_background*0.135);
		posY_cadre = posY_background+(int)(hauteur_background*0.08);
		//nom joueur
		taille_nom_joueur = initAffichageJoueurs().getNom().length();
		taille_police_nom_joueur=(int)(hauteur_chrono/6);
		posX_nom_joueur = posX_cadre+(int)(largeur_cadre*0.07);
		posY_nom_joueur = posY_cadre+(int)(hauteur_cadre*0.6);
		//bouton melange
		posX_bouton_melange=posX_background+(int)(largeur_background*0.4);
		posY_bouton_melange=posY_chrono;
	    posX_bouton_valider=posX_bouton_melange;
	    posY_bouton_valider=posY_bouton_melange+(int)(hauteur_bouton*1.1);
	    //bouton retour
	    posXRetourMenu=posX_background+(int)(largeur_background*0.74);
		posYRetourMenu=posY_background+(int)(hauteur_background*0.8);
		largeurRetourMenu = Math.min(largeur_background/15, frameWidth/15);
		hauteurRetourMenu = (int)(largeurRetourMenu/rapportBackMenu);
		//popup
		largeur_popup = (int)(largeur_background/4);
		hauteur_popup = (int)(largeur_popup*rapportPopup);
		posX_popup = posX_background+largeur_background/2-largeur_popup/2;
		posY_popup = posY_background+hauteur_background/2-hauteur_popup/2;
		//oui et non
		largeur_oui = largeur_popup/3;
		hauteur_oui = (int)(largeur_oui*rapportOui);
		posX_oui = posX_popup+(int)(largeur_popup*0.1);
		posY_oui = posY_popup+(int)(hauteur_popup*0.55);
		posX_non = posX_popup+(int)(largeur_popup*0.9)-largeur_oui;
		posY_non = posY_oui;
		//settings
		largeur_settings=largeur_background/18;
		posX_settings=posX_background+(int)(largeur_background*0.92);
		posY_settings=posY_background+(int)(hauteur_background*0.04);
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
		int taille_max = 14;
		int jCourant=this.partieEnCours.getJoueurCourant();
		if(jCourant==0) {
			nom=this.partieEnCours.joueur1().getNom();
		}else {
			nom=this.partieEnCours.joueur2().getNom();
		}
		if(nom.length()>taille_max) {
			nom = nom.substring(0,taille_max);
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
	// AFFICHAGE BOUTONS-------------------------------------------------------------
	public void affichePopup(Graphics g) {
		if(popup) {
			g.drawImage(textures.popup, posX_popup, posY_popup, largeur_popup, hauteur_popup, null);
			g.drawImage(textures.oui, posX_oui, posY_oui, largeur_oui, hauteur_oui, null);
			g.drawImage(textures.non, posX_non, posY_non, largeur_oui, hauteur_oui, null);
		}
	}
	
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
	
	public void affichageRetourMenu(Graphics g) {
		g.drawImage(texture.TutoMenu, posXRetourMenu, posYRetourMenu, largeurRetourMenu, hauteurRetourMenu, null);	
	}
	
	public void affichageSettings(Graphics g) {
		g.drawImage(texture.settings, posX_settings, posY_settings, largeur_settings, largeur_settings, null);	
	}
	
	public void cadreJoueur(Graphics g) {
		g.drawImage(textures.cadre_joueur, posX_cadre, posY_cadre, largeur_cadre, hauteur_cadre, null);
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
		int nb_pieces=a.getPiecesPiochees().size();
		int posX=POSX_PIOCHE;
		int posY=POSY_PIOCHE;
		for(int i = 0; i < nb_pieces; i++) {
			Piece p = a.getPiecesPiochees().get(i);
			if(i<coupure) {
				g.drawImage(getpetitcolor(p), posX, posY, TAILLE_CUBES_LARGEUR, TAILLE_CUBES_HAUTEUR,null);
				posX+=TAILLE_CUBES_LARGEUR;
			}else {
				if(i==coupure) {
					posX=POSX_PIOCHE;
					posY+=TAILLE_CUBES_HAUTEUR+2;
				}
				g.drawImage(getpetitcolor(p), posX, posY, TAILLE_CUBES_LARGEUR, TAILLE_CUBES_HAUTEUR,null);
				posX+=TAILLE_CUBES_LARGEUR;
			}
		}
	}
	
}




























