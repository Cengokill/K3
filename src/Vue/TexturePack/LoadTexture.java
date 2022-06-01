package Vue.TexturePack;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JTextField;

public class LoadTexture {
	public String CHEMIN;

	// IMAGES FOND
	public Image background, backgroundSansLogo, backgroundPhase1, ile1, ile_joueur1, ile_joueur2, ile_montagne, chrono;
	// IMAGE PIECE
	public Image pieceVide, pieceNoire, pieceBleue, pieceVert, pieceRouge, pieceBlanche, pieceNature, pieceJaune;
	public Image pieceNoireTransparent, pieceBleueTransparent, pieceVertTransparent, pieceRougeTransparent,
			pieceBlancheTransparent, pieceNatureTransparent, pieceJauneTransparent;
	// IMAGE MENU
	public Image menuBouton1, menuBouton1_presse;
	public Image menuBoutonCharger, menuBoutonCharger_presse;
	public Image menuBouton2, menuBouton2_presse;
	public Image menuBouton3, menuBouton3_presse;
	public Image menuBouton4, menuBouton4_presse;
	public Image boutonPVP, boutonPVP_vert, boutonPVM, boutonPVM_vert, boutonMVM, boutonMVM_vert, boutonNomJ1,
			boutonNomJ2, boutonNomJoueur,
			boutonDifficulteOrdi1, boutonDifficulteOrdi2, boutonDifficulteOrdi, boutonFacile, boutonFacile_vert,
			boutonMoyen, boutonMoyen_vert,
			boutonDifficile, boutonDifficile_vert, boutonCommencer, boutonCommencer_presse, boutonPVP_gris,
			boutonPVM_gris,
			boutonMVM_gris, boutonNomJ1_gris, boutonNomJ2_gris, boutonNomJoueur_gris, boutonDifficulteOrdi1_gris,
			boutonDifficulteOrdi2_gris,
			boutonDifficulteOrdi_gris, boutonFacile_gris, boutonMoyen_gris, boutonDifficile_gris, menuSuivant,
			menuRetour,
			boutonPasserTour, boutonPasserTour_gris, boutonCoupPrecedent, boutonCoupPrecedent_gris, modePleinEcran,
			volumeMusique, volumeSons, active, desactive,
			passerTour, passerTour_gris, imagevol, boutonSauvegarde, valider, valider2, oui, non, popup, settings,
			cadre_joueur, cadre_joueur_gris, popup_save, fermer;
	// IMAGE CHARGEMENT
	public Image boutonLoad, boutonLoadPresse;
	// IMAGE TUTO
	public Image Tuto1, Tuto2, Tuto3, Tuto4, TutoPrecedent, TutoSuivant, TutoMenu;
	// IMAGES BOUTONS
	public Image boutonMelange, boutonMelange_presse, boutonValider, boutonValider_presse, boutonValider_gris;
	// IMAGES CURSEURS
	public Image mainFermee, mainDepose;
	// TEXT FIELDS
	public JTextField texte_nomJ1, texte_nomJ2, texte_nomJoueur;

	public LoadTexture(String c) throws IOException {
		this.CHEMIN = c;
		// IMAGE FOND -------------------------------------------------------------
		background = ImageIO.read(new File(CHEMIN + "background.png"));
		backgroundSansLogo = ImageIO.read(new File(CHEMIN + "background_sans_logo.png"));
		backgroundPhase1 = ImageIO.read(new File(CHEMIN + "background_phase1.png"));
		ile1 = ImageIO.read(new File(CHEMIN + "ile1.png"));
		ile_joueur1 = ImageIO.read(new File(CHEMIN + "ile_joueur1.png"));
		ile_joueur2 = ImageIO.read(new File(CHEMIN + "ile_joueur2.png"));
		ile_montagne = ImageIO.read(new File(CHEMIN + "ile3.png"));
		chrono = ImageIO.read(new File(CHEMIN + "chrono_jeu.png"));
		cadre_joueur = ImageIO.read(new File(CHEMIN + "cadre_joueur.png"));
		cadre_joueur_gris = ImageIO.read(new File(CHEMIN + "cadre_joueur_gris.png"));
		// IMAGES MENU---------------------------------------------------------------
		menuBouton1 = ImageIO.read(new File(CHEMIN + "nouvellePartie_flou.png"));
		menuBouton1_presse = ImageIO.read(new File(CHEMIN + "nouvellePartie_presse.png"));
		menuBoutonCharger = ImageIO.read(new File(CHEMIN + "chargerPartie_flou.png"));
		menuBoutonCharger_presse = ImageIO.read(new File(CHEMIN + "chargerPartie_presse.png"));
		menuBouton2 = ImageIO.read(new File(CHEMIN + "options_flou.png"));
		menuBouton2_presse = ImageIO.read(new File(CHEMIN + "options_presse.png"));
		menuBouton3 = ImageIO.read(new File(CHEMIN + "tutoriel_flou.png"));
		menuBouton3_presse = ImageIO.read(new File(CHEMIN + "tutoriel_presse.png"));
		menuBouton4 = ImageIO.read(new File(CHEMIN + "quitter_flou.png"));
		menuBouton4_presse = ImageIO.read(new File(CHEMIN + "quitter_presse.png"));
		menuSuivant = ImageIO.read(new File(CHEMIN + "suivant.png"));
		menuRetour = ImageIO.read(new File(CHEMIN + "retour.png"));
		modePleinEcran = ImageIO.read(new File(CHEMIN + "mode_plein_ecran_flou.png"));
		volumeMusique = ImageIO.read(new File(CHEMIN + "volume_musiques_flou.png"));
		volumeSons = ImageIO.read(new File(CHEMIN + "volume_effets_sonores_flou.png"));
		active = ImageIO.read(new File(CHEMIN + "active.png"));
		desactive = ImageIO.read(new File(CHEMIN + "desactive.png"));
		popup = ImageIO.read(new File(CHEMIN + "popup.png"));
		popup_save = ImageIO.read(new File(CHEMIN + "popup_save.png"));
		fermer = ImageIO.read(new File(CHEMIN + "fermer.png"));
		// IMAGE
		// CHARGEMENT---------------------------------------------------------------
		boutonLoad = ImageIO.read(new File(CHEMIN + "chargerPartie_flou.png"));
		boutonLoadPresse = ImageIO.read(new File(CHEMIN + "chargerPartie_presse.png"));
		// IMAGE TUTO---------------------------------------------------------------
		Tuto1 = ImageIO.read(new File(CHEMIN + "ConstructionPyramide.png"));
		Tuto2 = ImageIO.read(new File(CHEMIN + "PhaseDeJeu.png"));
		Tuto3 = ImageIO.read(new File(CHEMIN + "Penalite.png"));
		Tuto4 = ImageIO.read(new File(CHEMIN + "finDePartie.png"));
		TutoPrecedent = ImageIO.read(new File(CHEMIN + "retour.png"));
		TutoSuivant = ImageIO.read(new File(CHEMIN + "suivant.png"));
		TutoMenu = ImageIO.read(new File(CHEMIN + "menu_flou.png"));
		// IMAGES BOUTONS---------------------------------------------------------------
		boutonPVP = ImageIO.read(new File(CHEMIN + "PVP.png"));
		boutonPVP_vert = ImageIO.read(new File(CHEMIN + "PVP_vert.png"));
		boutonPVP_gris = ImageIO.read(new File(CHEMIN + "PVP_gris.png"));
		boutonPVM = ImageIO.read(new File(CHEMIN + "PVM.png"));
		boutonPVM_vert = ImageIO.read(new File(CHEMIN + "PVM_vert.png"));
		boutonPVM_gris = ImageIO.read(new File(CHEMIN + "PVM_gris.png"));
		boutonMVM = ImageIO.read(new File(CHEMIN + "MVM.png"));
		boutonMVM_vert = ImageIO.read(new File(CHEMIN + "MVM_vert.png"));
		boutonMVM_gris = ImageIO.read(new File(CHEMIN + "MVM_gris.png"));
		boutonNomJ1 = ImageIO.read(new File(CHEMIN + "nomJ1.png"));
		boutonNomJ1_gris = ImageIO.read(new File(CHEMIN + "nomJ1_gris.png"));
		boutonNomJ2 = ImageIO.read(new File(CHEMIN + "nomJ2.png"));
		boutonNomJ2_gris = ImageIO.read(new File(CHEMIN + "nomJ2_gris.png"));
		boutonNomJoueur = ImageIO.read(new File(CHEMIN + "nomJoueur.png"));
		boutonNomJoueur_gris = ImageIO.read(new File(CHEMIN + "nomJoueur_gris.png"));
		boutonDifficulteOrdi1 = ImageIO.read(new File(CHEMIN + "difficulte_ordi1.png"));
		boutonDifficulteOrdi1_gris = ImageIO.read(new File(CHEMIN + "difficulte_ordi1_gris.png"));
		boutonDifficulteOrdi2 = ImageIO.read(new File(CHEMIN + "difficulte_ordi2.png"));
		boutonDifficulteOrdi2_gris = ImageIO.read(new File(CHEMIN + "difficulte_ordi2_gris.png"));
		boutonDifficulteOrdi = ImageIO.read(new File(CHEMIN + "difficulte_ordi.png"));
		boutonDifficulteOrdi_gris = ImageIO.read(new File(CHEMIN + "difficulte_ordi_gris.png"));
		boutonFacile = ImageIO.read(new File(CHEMIN + "facile.png"));
		boutonFacile_gris = ImageIO.read(new File(CHEMIN + "facile_gris.png"));
		boutonFacile_vert = ImageIO.read(new File(CHEMIN + "facile_vert.png"));
		boutonMoyen = ImageIO.read(new File(CHEMIN + "moyen.png"));
		boutonMoyen_gris = ImageIO.read(new File(CHEMIN + "moyen_gris.png"));
		boutonMoyen_vert = ImageIO.read(new File(CHEMIN + "moyen_vert.png"));
		boutonDifficile = ImageIO.read(new File(CHEMIN + "difficile.png"));
		boutonDifficile_gris = ImageIO.read(new File(CHEMIN + "difficile_gris.png"));
		boutonDifficile_vert = ImageIO.read(new File(CHEMIN + "difficile_vert.png"));
		boutonCommencer = ImageIO.read(new File(CHEMIN + "lancerPartie.png"));
		boutonCommencer_presse = ImageIO.read(new File(CHEMIN + "lancerPartie_presse.png"));
		boutonMelange = ImageIO.read(new File(CHEMIN + "bouton_melange.png"));
		boutonMelange_presse = ImageIO.read(new File(CHEMIN + "bouton_melange_presse.png"));
		boutonValider = ImageIO.read(new File(CHEMIN + "bouton_valider.png"));
		boutonValider_presse = ImageIO.read(new File(CHEMIN + "bouton_valider_presse.png"));
		boutonValider_gris = ImageIO.read(new File(CHEMIN + "bouton_valider_gris.png"));
		boutonPasserTour = ImageIO.read(new File(CHEMIN + "passer_son_tour_flou.png"));
		boutonPasserTour_gris = ImageIO.read(new File(CHEMIN + "passer_son_tour_gris.png"));
		boutonCoupPrecedent = ImageIO.read(new File(CHEMIN + "coup_precedent_flou.png"));
		boutonCoupPrecedent_gris = ImageIO.read(new File(CHEMIN + "coup_precedent_gris.png"));
		passerTour = ImageIO.read(new File(CHEMIN + "passer_son_tour_flou.png"));
		passerTour_gris = ImageIO.read(new File(CHEMIN + "passer_son_tour_gris.png"));
		imagevol = ImageIO.read(new File(CHEMIN + "espacevol.png"));
		valider = ImageIO.read(new File(CHEMIN + "valider_flou.png"));
		valider2 = ImageIO.read(new File(CHEMIN + "valider2.png"));
		boutonSauvegarde = ImageIO.read(new File(CHEMIN + "sauvegarder_flou.png"));
		oui = ImageIO.read(new File(CHEMIN + "oui.png"));
		non = ImageIO.read(new File(CHEMIN + "non.png"));
		settings = ImageIO.read(new File(CHEMIN + "settings.png"));
		// CURSEURS
		mainFermee = ImageIO.read(new File(CHEMIN + "hand-closed.png"));
		mainDepose = ImageIO.read(new File(CHEMIN + "hand-depose.png"));
		// PIECES
		pieceVide = ImageIO.read(new File(CHEMIN + "VIDE.png"));
		pieceNoire = ImageIO.read(new File(CHEMIN + "NOIR.png"));
		pieceBleue = ImageIO.read(new File(CHEMIN + "BLEU.png"));
		pieceVert = ImageIO.read(new File(CHEMIN + "VERT.png"));
		pieceRouge = ImageIO.read(new File(CHEMIN + "ROUGE.png"));
		pieceBlanche = ImageIO.read(new File(CHEMIN + "BLANC.png"));
		pieceNature = ImageIO.read(new File(CHEMIN + "NATUREL.png"));
		pieceJaune = ImageIO.read(new File(CHEMIN + "JAUNE.png"));

		pieceNoireTransparent = ImageIO.read(new File(CHEMIN + "NOIR_TRANSPARENT.png"));
		pieceBleueTransparent = ImageIO.read(new File(CHEMIN + "BLEU_TRANSPARENT.png"));
		pieceVertTransparent = ImageIO.read(new File(CHEMIN + "VERT_TRANSPARENT.png"));
		pieceRougeTransparent = ImageIO.read(new File(CHEMIN + "ROUGE_TRANSPARENT.png"));
		pieceBlancheTransparent = ImageIO.read(new File(CHEMIN + "BLANC_TRANSPARENT.png"));
		pieceNatureTransparent = ImageIO.read(new File(CHEMIN + "NATUREL_TRANSPARENT.png"));
		pieceJauneTransparent = ImageIO.read(new File(CHEMIN + "JAUNE_TRANSPARENT.png"));
	}
}