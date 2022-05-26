package Vue.TexturePack;

import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JTextField;

public class LoadTexture{
	public String CHEMIN;
	
	//IMAGE FOND
	public Image background, backgroundSansLogo;
	
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
	public Image boutonPVP,boutonPVP_vert,boutonPVM,boutonPVM_vert,boutonMVM,boutonMVM_vert,boutonNomJ1,boutonNomJ2,boutonNomJoueur,
	boutonDifficulteOrdi1,boutonDifficulteOrdi2,boutonDifficulteOrdi,boutonFacile,boutonFacile_vert,boutonMoyen,boutonMoyen_vert,
	boutonDifficile,boutonDifficile_vert, boutonCommencer, boutonCommencer_presse;
	//IMAGE CHARGEMENT
	public Image boutonLoad;
	public Image boutonLoadPresse;
	
	//IMAGES BOUTONS
	public Image boutonMelange, boutonMelange_presse, boutonValider, boutonValider_presse, boutonValider_gris;
	
	//IMAGES CURSEURS
	public Image mainFermee, mainDepose;
	//TEXT FIELDS
	public JTextField texte_nomJ1, texte_nomJ2, texte_nomJoueur;

	public LoadTexture(String optionchemin) {
		this.CHEMIN = System.getProperty("user.dir")+optionchemin;
		//IMAGE FOND -------------------------------------------------------------
		background = Toolkit.getDefaultToolkit().createImage(CHEMIN+"background.png");
		backgroundSansLogo = Toolkit.getDefaultToolkit().createImage(CHEMIN+"background_sans_logo.png");
		//IMAGES MENU---------------------------------------------------------------
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
		boutonPVP = Toolkit.getDefaultToolkit().createImage(CHEMIN+"PVP.png");
		boutonPVP_vert = Toolkit.getDefaultToolkit().createImage(CHEMIN+"PVP_vert.png");
		boutonPVM = Toolkit.getDefaultToolkit().createImage(CHEMIN+"PVM.png");
		boutonPVM_vert = Toolkit.getDefaultToolkit().createImage(CHEMIN+"PVM_vert.png");
		boutonMVM = Toolkit.getDefaultToolkit().createImage(CHEMIN+"MVM.png");
		boutonMVM_vert = Toolkit.getDefaultToolkit().createImage(CHEMIN+"MVM_vert.png");
		boutonNomJ1 = Toolkit.getDefaultToolkit().createImage(CHEMIN+"nomJ1.png");
		boutonNomJ2 = Toolkit.getDefaultToolkit().createImage(CHEMIN+"nomJ2.png");
		boutonNomJoueur = Toolkit.getDefaultToolkit().createImage(CHEMIN+"nomJoueur.png");
		boutonDifficulteOrdi1 = Toolkit.getDefaultToolkit().createImage(CHEMIN+"difficulte_ordi1.png");
		boutonDifficulteOrdi2 = Toolkit.getDefaultToolkit().createImage(CHEMIN+"difficulte_ordi2.png");
		boutonDifficulteOrdi = Toolkit.getDefaultToolkit().createImage(CHEMIN+"difficulte_ordi.png");
		boutonFacile = Toolkit.getDefaultToolkit().createImage(CHEMIN+"facile.png");
		boutonFacile_vert = Toolkit.getDefaultToolkit().createImage(CHEMIN+"facile_vert.png");
		boutonMoyen = Toolkit.getDefaultToolkit().createImage(CHEMIN+"moyen.png");
		boutonMoyen_vert = Toolkit.getDefaultToolkit().createImage(CHEMIN+"moyen_vert.png");
		boutonDifficile = Toolkit.getDefaultToolkit().createImage(CHEMIN+"difficile.png");
		boutonDifficile_vert = Toolkit.getDefaultToolkit().createImage(CHEMIN+"difficile_vert.png");
		boutonCommencer = Toolkit.getDefaultToolkit().createImage(CHEMIN+"lancerPartie_flou.png");
		boutonCommencer_presse = Toolkit.getDefaultToolkit().createImage(CHEMIN+"lancerPartie_presse.png");
		//CURSEURS
		mainFermee = Toolkit.getDefaultToolkit().createImage(CHEMIN+"hand-closed.png");
		mainDepose = Toolkit.getDefaultToolkit().createImage(CHEMIN+"hand-depose.png");
		//LABELS
		texte_nomJ1= new JTextField(30);
		texte_nomJ2= new JTextField(30);
		texte_nomJoueur= new JTextField(30);
	}
}





























