package Vue.TexturePack;

import java.awt.Image;
import java.awt.Toolkit;

public class LoadTexture{
	public String CHEMIN;
	
	//IMAGE FOND
	public Image background;
	
	//IMAGE PIECE
	public Image pieceVide;
	public Image pieceNoire;
	public Image pieceBleu;
	public Image pieceVert;
	public Image pieceRouge;
	public Image pieceBlanche;
	public Image pieceNature;	
	public Image pieceJaune;
	
	//IMAGE MENU
	public Image menuBouton1,menuBouton1_presse;
	public Image menuBoutonCharger, menuBoutonCharger_presse;
	public Image menuBouton2, menuBouton2_presse;
	public Image menuBouton3, menuBouton3_presse;
	public Image menuBouton4, menuBouton4_presse;
	
	//IMAGE CHARGEMENT
	public Image boutonLoad;
	public Image boutonLoadPresse;
	
	//IMAGES BOUTONS
	public Image boutonMelange, boutonMelange_presse, boutonValider, boutonValider_presse, boutonValider_gris;
	
	//IMAGES CURSEURS
	public Image mainFermee, mainDepose;

	public LoadTexture(String optionchemin) {
		this.CHEMIN = System.getProperty("user.dir")+optionchemin;
		
		//IMAGE FOND -------------------------------------------------------------
		background = Toolkit.getDefaultToolkit().createImage(CHEMIN+"background.png");
		//IMAGE PIECE -------------------------------------------------------------
		pieceVide = Toolkit.getDefaultToolkit().createImage(CHEMIN+"EMPTY3.png");
		pieceNoire = Toolkit.getDefaultToolkit().createImage(CHEMIN+"NOIR.png");
		pieceBleu = Toolkit.getDefaultToolkit().createImage(CHEMIN+"BLEU.png");
		pieceVert = Toolkit.getDefaultToolkit().createImage(CHEMIN+"VERT.png");
		pieceRouge = Toolkit.getDefaultToolkit().createImage(CHEMIN+"ROUGE.png");
		pieceBlanche = Toolkit.getDefaultToolkit().createImage(CHEMIN+"BLANC.png");
		pieceNature = Toolkit.getDefaultToolkit().createImage(CHEMIN+"NATUREL.png");
		pieceJaune = Toolkit.getDefaultToolkit().createImage(CHEMIN+"JAUNE.png");
		//IMAGE MENU---------------------------------------------------------------
		menuBouton1 = Toolkit.getDefaultToolkit().createImage(CHEMIN+"nouvellePartie_flou.png");
		menuBouton1_presse = Toolkit.getDefaultToolkit().createImage(CHEMIN+"nouvellePartie_presse.png");
		menuBoutonCharger = Toolkit.getDefaultToolkit().createImage(CHEMIN+"chargerPartie_flou.png");
		menuBoutonCharger_presse = Toolkit.getDefaultToolkit().createImage(CHEMIN+"chargerPartie_presse.png");
		menuBouton2 = Toolkit.getDefaultToolkit().createImage(CHEMIN+"options_flou.png");
		menuBouton2_presse = Toolkit.getDefaultToolkit().createImage(CHEMIN+"options_presse.png");
		menuBouton3 = Toolkit.getDefaultToolkit().createImage(CHEMIN+"tutoriel_flou.png");
		menuBouton3_presse = Toolkit.getDefaultToolkit().createImage(CHEMIN+"tutoriel_presse.png");
		menuBouton4 = Toolkit.getDefaultToolkit().createImage(CHEMIN+"quitter_flou.png");
		menuBouton4_presse = Toolkit.getDefaultToolkit().createImage(CHEMIN+"quitter_presse.png");
		//IMAGE CHARGEMENT---------------------------------------------------------------
		boutonLoad = Toolkit.getDefaultToolkit().createImage(CHEMIN+"chargerPartie_flou.png");
		boutonLoadPresse = Toolkit.getDefaultToolkit().createImage(CHEMIN+"chargerPartie_presse.png");
		//IMAGES BOUTONS---------------------------------------------------------------
		boutonMelange = Toolkit.getDefaultToolkit().createImage(CHEMIN+"bouton_melange.png");
		boutonMelange_presse = Toolkit.getDefaultToolkit().createImage(CHEMIN+"bouton_melange_presse.png");
		boutonValider = Toolkit.getDefaultToolkit().createImage(CHEMIN+"bouton_valider.png");
		boutonValider_presse = Toolkit.getDefaultToolkit().createImage(CHEMIN+"bouton_valider_presse.png");
		boutonValider_gris = Toolkit.getDefaultToolkit().createImage(CHEMIN+"bouton_valider_gris.png");
		
		//CURSEURS
		mainFermee = Toolkit.getDefaultToolkit().createImage(CHEMIN+"hand-closed.png");
		mainDepose = Toolkit.getDefaultToolkit().createImage(CHEMIN+"hand-depose.png");
	}
}





























