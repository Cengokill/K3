package Vue.TexturePack;

import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JTextField;

public class LoadTexture{
	public String CHEMIN;
	
	//IMAGES FOND
	public Image background, backgroundSansLogo, backgroundPhase1, ile1, ile_joueur1, ile_joueur2, chrono;
	//IMAGE PIECE
	public Image pieceVide;
	public Image pieceNoire;
	public Image pieceBleue;
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
	boutonDifficile,boutonDifficile_vert, boutonCommencer, boutonCommencer_presse, boutonPVP_gris, boutonPVM_gris,
	boutonMVM_gris, boutonNomJ1_gris, boutonNomJ2_gris, boutonNomJoueur_gris, boutonDifficulteOrdi1_gris, boutonDifficulteOrdi2_gris,
	boutonDifficulteOrdi_gris, boutonFacile_gris, boutonMoyen_gris, boutonDifficile_gris, menuSuivant, menuRetour,
	boutonPasserTour,boutonPasserTour_gris,boutonCoupPrecedent,boutonCoupPrecedent_gris;
	//IMAGE CHARGEMENT
	public Image boutonLoad;
	public Image boutonLoadPresse;
	
	//IMAGE TUTO
	public Image Tuto1, Tuto2, Tuto3, Tuto4;
	
	//IMAGES BOUTONS
	public Image boutonMelange, boutonMelange_presse, boutonValider, boutonValider_presse, boutonValider_gris;
	
	//IMAGES CURSEURS
	public Image mainFermee, mainDepose;
	//TEXT FIELDS
	public JTextField texte_nomJ1, texte_nomJ2, texte_nomJoueur;

	public LoadTexture(String c) {
		this.CHEMIN = c;
		//IMAGE FOND -------------------------------------------------------------
		background = Toolkit.getDefaultToolkit().createImage(CHEMIN+"background.png");
		backgroundSansLogo = Toolkit.getDefaultToolkit().createImage(CHEMIN+"background_sans_logo.png");
		backgroundPhase1 = Toolkit.getDefaultToolkit().createImage(CHEMIN+"background_phase1.png");
		ile1 = Toolkit.getDefaultToolkit().createImage(CHEMIN+"ile1.png");
		ile_joueur1 = Toolkit.getDefaultToolkit().createImage(CHEMIN+"ile_joueur1.png");
		ile_joueur2 = Toolkit.getDefaultToolkit().createImage(CHEMIN+"ile_joueur2.png");
		chrono = Toolkit.getDefaultToolkit().createImage(CHEMIN+"chrono_jeu.png");
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
		menuSuivant = Toolkit.getDefaultToolkit().createImage(CHEMIN+"suivant.png");
		menuRetour = Toolkit.getDefaultToolkit().createImage(CHEMIN+"retour.png");
		//IMAGE CHARGEMENT---------------------------------------------------------------
		boutonLoad = Toolkit.getDefaultToolkit().createImage(CHEMIN+"chargerPartie_flou.png");
		boutonLoadPresse = Toolkit.getDefaultToolkit().createImage(CHEMIN+"chargerPartie_presse.png");
		//IMAGE TUTO---------------------------------------------------------------
		Tuto1 = Toolkit.getDefaultToolkit().createImage(CHEMIN+"ConstructionPyramide.png");
		Tuto2 = Toolkit.getDefaultToolkit().createImage(CHEMIN+"PhaseDeJeu.png");
		Tuto3 = Toolkit.getDefaultToolkit().createImage(CHEMIN+"Penalite.png");
		Tuto4 = Toolkit.getDefaultToolkit().createImage(CHEMIN+"finDePartie.png");
		//IMAGES BOUTONS---------------------------------------------------------------
		boutonPVP = Toolkit.getDefaultToolkit().createImage(CHEMIN+"PVP.png");
		boutonPVP_vert = Toolkit.getDefaultToolkit().createImage(CHEMIN+"PVP_vert.png");
		boutonPVP_gris = Toolkit.getDefaultToolkit().createImage(CHEMIN+"PVP_gris.png");
		boutonPVM = Toolkit.getDefaultToolkit().createImage(CHEMIN+"PVM.png");
		boutonPVM_vert = Toolkit.getDefaultToolkit().createImage(CHEMIN+"PVM_vert.png");
		boutonPVM_gris = Toolkit.getDefaultToolkit().createImage(CHEMIN+"PVM_gris.png");
		boutonMVM = Toolkit.getDefaultToolkit().createImage(CHEMIN+"MVM.png");
		boutonMVM_vert = Toolkit.getDefaultToolkit().createImage(CHEMIN+"MVM_vert.png");
		boutonMVM_gris = Toolkit.getDefaultToolkit().createImage(CHEMIN+"MVM_gris.png");
		boutonNomJ1 = Toolkit.getDefaultToolkit().createImage(CHEMIN+"nomJ1.png");
		boutonNomJ1_gris = Toolkit.getDefaultToolkit().createImage(CHEMIN+"nomJ1_gris.png");
		boutonNomJ2 = Toolkit.getDefaultToolkit().createImage(CHEMIN+"nomJ2.png");
		boutonNomJ2_gris = Toolkit.getDefaultToolkit().createImage(CHEMIN+"nomJ2_gris.png");
		boutonNomJoueur = Toolkit.getDefaultToolkit().createImage(CHEMIN+"nomJoueur.png");
		boutonNomJoueur_gris = Toolkit.getDefaultToolkit().createImage(CHEMIN+"nomJoueur_gris.png");
		boutonDifficulteOrdi1 = Toolkit.getDefaultToolkit().createImage(CHEMIN+"difficulte_ordi1.png");
		boutonDifficulteOrdi1_gris = Toolkit.getDefaultToolkit().createImage(CHEMIN+"difficulte_ordi1_gris.png");
		boutonDifficulteOrdi2 = Toolkit.getDefaultToolkit().createImage(CHEMIN+"difficulte_ordi2.png");
		boutonDifficulteOrdi2_gris = Toolkit.getDefaultToolkit().createImage(CHEMIN+"difficulte_ordi2_gris.png");
		boutonDifficulteOrdi = Toolkit.getDefaultToolkit().createImage(CHEMIN+"difficulte_ordi.png");
		boutonDifficulteOrdi_gris = Toolkit.getDefaultToolkit().createImage(CHEMIN+"difficulte_ordi_gris.png");
		boutonFacile = Toolkit.getDefaultToolkit().createImage(CHEMIN+"facile.png");
		boutonFacile_gris = Toolkit.getDefaultToolkit().createImage(CHEMIN+"facile_gris.png");
		boutonFacile_vert = Toolkit.getDefaultToolkit().createImage(CHEMIN+"facile_vert.png");
		boutonMoyen = Toolkit.getDefaultToolkit().createImage(CHEMIN+"moyen.png");
		boutonMoyen_gris = Toolkit.getDefaultToolkit().createImage(CHEMIN+"moyen_gris.png");
		boutonMoyen_vert = Toolkit.getDefaultToolkit().createImage(CHEMIN+"moyen_vert.png");
		boutonDifficile = Toolkit.getDefaultToolkit().createImage(CHEMIN+"difficile.png");
		boutonDifficile_gris = Toolkit.getDefaultToolkit().createImage(CHEMIN+"difficile_gris.png");
		boutonDifficile_vert = Toolkit.getDefaultToolkit().createImage(CHEMIN+"difficile_vert.png");
		boutonCommencer = Toolkit.getDefaultToolkit().createImage(CHEMIN+"lancerPartie.png");
		boutonCommencer_presse = Toolkit.getDefaultToolkit().createImage(CHEMIN+"lancerPartie_presse.png");
		boutonMelange = Toolkit.getDefaultToolkit().createImage(CHEMIN+"bouton_melange.png");
		boutonMelange_presse = Toolkit.getDefaultToolkit().createImage(CHEMIN+"bouton_melange_presse.png");
		boutonValider = Toolkit.getDefaultToolkit().createImage(CHEMIN+"bouton_valider.png");
		boutonValider_presse = Toolkit.getDefaultToolkit().createImage(CHEMIN+"bouton_valider_presse.png");
		boutonValider_gris = Toolkit.getDefaultToolkit().createImage(CHEMIN+"bouton_valider_gris.png");
		boutonPasserTour = Toolkit.getDefaultToolkit().createImage(CHEMIN+"passer_son_tour_flou.png");
		boutonPasserTour_gris = Toolkit.getDefaultToolkit().createImage(CHEMIN+"passer_son_tour_gris.png");
		boutonCoupPrecedent = Toolkit.getDefaultToolkit().createImage(CHEMIN+"coup_precedent_flou.png");
		boutonCoupPrecedent_gris = Toolkit.getDefaultToolkit().createImage(CHEMIN+"coup_precedent_gris.png");
		//CURSEURS
		mainFermee = Toolkit.getDefaultToolkit().createImage(CHEMIN+"hand-closed.png");
		mainDepose = Toolkit.getDefaultToolkit().createImage(CHEMIN+"hand-depose.png");
		//LABELS
		texte_nomJ1= new JTextField(30);
		texte_nomJ2= new JTextField(30);
		texte_nomJoueur= new JTextField(30);
		//PIECES
		pieceVide = Toolkit.getDefaultToolkit().createImage(CHEMIN+"EMPTY2.png");
		pieceNoire = Toolkit.getDefaultToolkit().createImage(CHEMIN+"NOIR.png");
		pieceBleue = Toolkit.getDefaultToolkit().createImage(CHEMIN+"BLEU.png");
		pieceVert = Toolkit.getDefaultToolkit().createImage(CHEMIN+"VERT.png");
		pieceRouge = Toolkit.getDefaultToolkit().createImage(CHEMIN+"ROUGE.png");
		pieceBlanche = Toolkit.getDefaultToolkit().createImage(CHEMIN+"BLANC.png");
		pieceNature = Toolkit.getDefaultToolkit().createImage(CHEMIN+"NATUREL.png");	
		pieceJaune = Toolkit.getDefaultToolkit().createImage(CHEMIN+"JAUNE.png");
	}
}





























