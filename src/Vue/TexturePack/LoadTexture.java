package Vue.TexturePack;

import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JTextField;

public class LoadTexture {
	public String CHEMIN;

	// IMAGES FOND
	public Image background, backgroundSansLogo, backgroundPhase1, ile1, ile_joueur1, ile_joueur2, ile_montagne, chrono;
	// IMAGE PIECE
	public Image pieceVide;
	public Image pieceNoire;
	public Image pieceBleue;
	public Image pieceVert;
	public Image pieceRouge;
	public Image pieceBlanche;
	public Image pieceNature;
	public Image pieceJaune;
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
			passerTour, passerTour_gris, imagevol, boutonSauvegarde, valider, valider2;
	// IMAGE CHARGEMENT
	public Image boutonLoad;
	public Image boutonLoadPresse;

	// IMAGE TUTO
	public Image Tuto1, Tuto2, Tuto3, Tuto4, TutoPrecedent, TutoSuivant, TutoMenu;

	// IMAGES BOUTONS
	public Image boutonMelange, boutonMelange_presse, boutonValider, boutonValider_presse, boutonValider_gris;

	// IMAGES CURSEURS
	public Image mainFermee, mainDepose;
	// TEXT FIELDS
	public JTextField texte_nomJ1, texte_nomJ2, texte_nomJoueur;

	public LoadTexture(String c) {
		this.CHEMIN = c;
		// IMAGE FOND -------------------------------------------------------------
		background = Toolkit.getDefaultToolkit().createImage(CHEMIN + "background.png");
		backgroundSansLogo = Toolkit.getDefaultToolkit().createImage(CHEMIN + "background_sans_logo.png");
		backgroundPhase1 = Toolkit.getDefaultToolkit().createImage(CHEMIN + "background_phase1.png");
		ile1 = Toolkit.getDefaultToolkit().createImage(CHEMIN + "ile1.png");
		ile_joueur1 = Toolkit.getDefaultToolkit().createImage(CHEMIN + "ile_joueur1.png");
		ile_joueur2 = Toolkit.getDefaultToolkit().createImage(CHEMIN + "ile_joueur2.png");
		ile_montagne = Toolkit.getDefaultToolkit().createImage(CHEMIN + "ile3.png");
		chrono = Toolkit.getDefaultToolkit().createImage(CHEMIN + "chrono_jeu.png");
		// IMAGES MENU---------------------------------------------------------------
		menuBouton1 = Toolkit.getDefaultToolkit().createImage(CHEMIN + "nouvellePartie_flou.png");
		menuBouton1_presse = Toolkit.getDefaultToolkit().createImage(CHEMIN + "nouvellePartie_presse.png");
		menuBoutonCharger = Toolkit.getDefaultToolkit().createImage(CHEMIN + "chargerPartie_flou.png");
		menuBoutonCharger_presse = Toolkit.getDefaultToolkit().createImage(CHEMIN + "chargerPartie_presse.png");
		menuBouton2 = Toolkit.getDefaultToolkit().createImage(CHEMIN + "options_flou.png");
		menuBouton2_presse = Toolkit.getDefaultToolkit().createImage(CHEMIN + "options_presse.png");
		menuBouton3 = Toolkit.getDefaultToolkit().createImage(CHEMIN + "tutoriel_flou.png");
		menuBouton3_presse = Toolkit.getDefaultToolkit().createImage(CHEMIN + "tutoriel_presse.png");
		menuBouton4 = Toolkit.getDefaultToolkit().createImage(CHEMIN + "quitter_flou.png");
		menuBouton4_presse = Toolkit.getDefaultToolkit().createImage(CHEMIN + "quitter_presse.png");
		menuSuivant = Toolkit.getDefaultToolkit().createImage(CHEMIN + "suivant.png");
		menuRetour = Toolkit.getDefaultToolkit().createImage(CHEMIN + "retour.png");
		modePleinEcran = Toolkit.getDefaultToolkit().createImage(CHEMIN + "mode_plein_ecran_flou.png");
		volumeMusique = Toolkit.getDefaultToolkit().createImage(CHEMIN + "volume_musiques_flou.png");
		volumeSons = Toolkit.getDefaultToolkit().createImage(CHEMIN + "volume_effets_sonores_flou.png");
		active = Toolkit.getDefaultToolkit().createImage(CHEMIN + "active.png");
		desactive = Toolkit.getDefaultToolkit().createImage(CHEMIN + "desactive.png");
		// IMAGE
		// CHARGEMENT---------------------------------------------------------------
		boutonLoad = Toolkit.getDefaultToolkit().createImage(CHEMIN + "chargerPartie_flou.png");
		boutonLoadPresse = Toolkit.getDefaultToolkit().createImage(CHEMIN + "chargerPartie_presse.png");
		// IMAGE TUTO---------------------------------------------------------------
		Tuto1 = Toolkit.getDefaultToolkit().createImage(CHEMIN + "ConstructionPyramide.png");
		Tuto2 = Toolkit.getDefaultToolkit().createImage(CHEMIN + "PhaseDeJeu.png");
		Tuto3 = Toolkit.getDefaultToolkit().createImage(CHEMIN + "Penalite.png");
		Tuto4 = Toolkit.getDefaultToolkit().createImage(CHEMIN + "finDePartie.png");
		TutoPrecedent = Toolkit.getDefaultToolkit().createImage(CHEMIN + "retour.png");
		TutoSuivant = Toolkit.getDefaultToolkit().createImage(CHEMIN + "suivant.png");
		TutoMenu = Toolkit.getDefaultToolkit().createImage(CHEMIN + "menu_flou.png");
		// IMAGES BOUTONS---------------------------------------------------------------
		boutonPVP = Toolkit.getDefaultToolkit().createImage(CHEMIN + "PVP.png");
		boutonPVP_vert = Toolkit.getDefaultToolkit().createImage(CHEMIN + "PVP_vert.png");
		boutonPVP_gris = Toolkit.getDefaultToolkit().createImage(CHEMIN + "PVP_gris.png");
		boutonPVM = Toolkit.getDefaultToolkit().createImage(CHEMIN + "PVM.png");
		boutonPVM_vert = Toolkit.getDefaultToolkit().createImage(CHEMIN + "PVM_vert.png");
		boutonPVM_gris = Toolkit.getDefaultToolkit().createImage(CHEMIN + "PVM_gris.png");
		boutonMVM = Toolkit.getDefaultToolkit().createImage(CHEMIN + "MVM.png");
		boutonMVM_vert = Toolkit.getDefaultToolkit().createImage(CHEMIN + "MVM_vert.png");
		boutonMVM_gris = Toolkit.getDefaultToolkit().createImage(CHEMIN + "MVM_gris.png");
		boutonNomJ1 = Toolkit.getDefaultToolkit().createImage(CHEMIN + "nomJ1.png");
		boutonNomJ1_gris = Toolkit.getDefaultToolkit().createImage(CHEMIN + "nomJ1_gris.png");
		boutonNomJ2 = Toolkit.getDefaultToolkit().createImage(CHEMIN + "nomJ2.png");
		boutonNomJ2_gris = Toolkit.getDefaultToolkit().createImage(CHEMIN + "nomJ2_gris.png");
		boutonNomJoueur = Toolkit.getDefaultToolkit().createImage(CHEMIN + "nomJoueur.png");
		boutonNomJoueur_gris = Toolkit.getDefaultToolkit().createImage(CHEMIN + "nomJoueur_gris.png");
		boutonDifficulteOrdi1 = Toolkit.getDefaultToolkit().createImage(CHEMIN + "difficulte_ordi1.png");
		boutonDifficulteOrdi1_gris = Toolkit.getDefaultToolkit().createImage(CHEMIN + "difficulte_ordi1_gris.png");
		boutonDifficulteOrdi2 = Toolkit.getDefaultToolkit().createImage(CHEMIN + "difficulte_ordi2.png");
		boutonDifficulteOrdi2_gris = Toolkit.getDefaultToolkit().createImage(CHEMIN + "difficulte_ordi2_gris.png");
		boutonDifficulteOrdi = Toolkit.getDefaultToolkit().createImage(CHEMIN + "difficulte_ordi.png");
		boutonDifficulteOrdi_gris = Toolkit.getDefaultToolkit().createImage(CHEMIN + "difficulte_ordi_gris.png");
		boutonFacile = Toolkit.getDefaultToolkit().createImage(CHEMIN + "facile.png");
		boutonFacile_gris = Toolkit.getDefaultToolkit().createImage(CHEMIN + "facile_gris.png");
		boutonFacile_vert = Toolkit.getDefaultToolkit().createImage(CHEMIN + "facile_vert.png");
		boutonMoyen = Toolkit.getDefaultToolkit().createImage(CHEMIN + "moyen.png");
		boutonMoyen_gris = Toolkit.getDefaultToolkit().createImage(CHEMIN + "moyen_gris.png");
		boutonMoyen_vert = Toolkit.getDefaultToolkit().createImage(CHEMIN + "moyen_vert.png");
		boutonDifficile = Toolkit.getDefaultToolkit().createImage(CHEMIN + "difficile.png");
		boutonDifficile_gris = Toolkit.getDefaultToolkit().createImage(CHEMIN + "difficile_gris.png");
		boutonDifficile_vert = Toolkit.getDefaultToolkit().createImage(CHEMIN + "difficile_vert.png");
		boutonCommencer = Toolkit.getDefaultToolkit().createImage(CHEMIN + "lancerPartie.png");
		boutonCommencer_presse = Toolkit.getDefaultToolkit().createImage(CHEMIN + "lancerPartie_presse.png");
		boutonMelange = Toolkit.getDefaultToolkit().createImage(CHEMIN + "bouton_melange.png");
		boutonMelange_presse = Toolkit.getDefaultToolkit().createImage(CHEMIN + "bouton_melange_presse.png");
		boutonValider = Toolkit.getDefaultToolkit().createImage(CHEMIN + "bouton_valider.png");
		boutonValider_presse = Toolkit.getDefaultToolkit().createImage(CHEMIN + "bouton_valider_presse.png");
		boutonValider_gris = Toolkit.getDefaultToolkit().createImage(CHEMIN + "bouton_valider_gris.png");
		boutonPasserTour = Toolkit.getDefaultToolkit().createImage(CHEMIN + "passer_son_tour_flou.png");
		boutonPasserTour_gris = Toolkit.getDefaultToolkit().createImage(CHEMIN + "passer_son_tour_gris.png");
		boutonCoupPrecedent = Toolkit.getDefaultToolkit().createImage(CHEMIN + "coup_precedent_flou.png");
		boutonCoupPrecedent_gris = Toolkit.getDefaultToolkit().createImage(CHEMIN + "coup_precedent_gris.png");
		passerTour = Toolkit.getDefaultToolkit().createImage(CHEMIN + "passer_son_tour_flou.png");
		passerTour_gris = Toolkit.getDefaultToolkit().createImage(CHEMIN + "passer_son_tour_gris.png");
		imagevol = Toolkit.getDefaultToolkit().createImage(CHEMIN + "espacevol.png");
		valider = Toolkit.getDefaultToolkit().createImage(CHEMIN + "valider_flou.png");
		valider2 = Toolkit.getDefaultToolkit().createImage(CHEMIN + "valider2.png");
		boutonSauvegarde = Toolkit.getDefaultToolkit().createImage(CHEMIN + "sauvegarder_flou.png");
		// CURSEURS
		mainFermee = Toolkit.getDefaultToolkit().createImage(CHEMIN + "hand-closed.png");
		mainDepose = Toolkit.getDefaultToolkit().createImage(CHEMIN + "hand-depose.png");
		// LABELS
		texte_nomJ1 = new JTextField(30);
		texte_nomJ2 = new JTextField(30);
		texte_nomJoueur = new JTextField(30);
		// PIECES
		pieceVide = Toolkit.getDefaultToolkit().createImage(CHEMIN + "EMPTY2.png");
		pieceNoire = Toolkit.getDefaultToolkit().createImage(CHEMIN + "NOIR.png");
		pieceBleue = Toolkit.getDefaultToolkit().createImage(CHEMIN + "BLEU.png");
		pieceVert = Toolkit.getDefaultToolkit().createImage(CHEMIN + "VERT.png");
		pieceRouge = Toolkit.getDefaultToolkit().createImage(CHEMIN + "ROUGE.png");
		pieceBlanche = Toolkit.getDefaultToolkit().createImage(CHEMIN + "BLANC.png");
		pieceNature = Toolkit.getDefaultToolkit().createImage(CHEMIN + "NATUREL.png");
		pieceJaune = Toolkit.getDefaultToolkit().createImage(CHEMIN + "JAUNE.png");

		// Si jar
		// try {
		// // IMAGE FOND -------------------------------------------------------------
		// System.out.println("Image fond");
		// background = ImageIO.read(getClass().getResourceAsStream(CHEMIN +
		// "background.png"));
		// backgroundSansLogo = ImageIO.read(getClass().getResourceAsStream(CHEMIN +
		// "background_sans_logo.png"));
		// backgroundPhase1 = ImageIO.read(getClass().getResourceAsStream(CHEMIN +
		// "background_phase1.png"));
		// ile1 = ImageIO.read(getClass().getResourceAsStream(CHEMIN + "ile1.png"));
		// ile_joueur1 = ImageIO.read(getClass().getResourceAsStream(CHEMIN +
		// "ile_joueur1.png"));
		// ile_joueur2 = ImageIO.read(getClass().getResourceAsStream(CHEMIN +
		// "ile_joueur2.png"));
		// ile_montagne = ImageIO.read(getClass().getResourceAsStream(CHEMIN +
		// "ile3.png"));
		// chrono = ImageIO.read(getClass().getResourceAsStream(CHEMIN +
		// "chrono_jeu.png"));
		// // IMAGES MENU---------------------------------------------------------------
		// System.out.println("Image menu");
		// menuBouton1 = ImageIO.read(getClass().getResourceAsStream(CHEMIN +
		// "nouvellePartie_flou.png"));
		// menuBouton1_presse = ImageIO.read(getClass().getResourceAsStream(CHEMIN +
		// "nouvellePartie_presse.png"));
		// menuBoutonCharger = ImageIO.read(getClass().getResourceAsStream(CHEMIN +
		// "chargerPartie_flou.png"));
		// menuBoutonCharger_presse = ImageIO
		// .read(getClass().getResourceAsStream(CHEMIN + "chargerPartie_presse.png"));
		// menuBouton2 = ImageIO.read(getClass().getResourceAsStream(CHEMIN +
		// "options_flou.png"));
		// menuBouton2_presse = ImageIO.read(getClass().getResourceAsStream(CHEMIN +
		// "options_presse.png"));
		// menuBouton3 = ImageIO.read(getClass().getResourceAsStream(CHEMIN +
		// "tutoriel_flou.png"));
		// menuBouton3_presse = ImageIO.read(getClass().getResourceAsStream(CHEMIN +
		// "tutoriel_presse.png"));
		// menuBouton4 = ImageIO.read(getClass().getResourceAsStream(CHEMIN +
		// "quitter_flou.png"));
		// menuBouton4_presse = ImageIO.read(getClass().getResourceAsStream(CHEMIN +
		// "quitter_presse.png"));
		// menuSuivant = ImageIO.read(getClass().getResourceAsStream(CHEMIN +
		// "suivant.png"));
		// menuRetour = ImageIO.read(getClass().getResourceAsStream(CHEMIN +
		// "retour.png"));
		// modePleinEcran = ImageIO.read(getClass().getResourceAsStream(CHEMIN +
		// "mode_plein_ecran_flou.png"));
		// volumeMusique = ImageIO.read(getClass().getResourceAsStream(CHEMIN +
		// "volume_musiques_flou.png"));
		// volumeSons = ImageIO.read(getClass().getResourceAsStream(CHEMIN +
		// "volume_effets_sonores_flou.png"));
		// active = ImageIO.read(getClass().getResourceAsStream(CHEMIN + "active.png"));
		// desactive = ImageIO.read(getClass().getResourceAsStream(CHEMIN +
		// "desactive.png"));
		// // IMAGE
		// // CHARGEMENT---------------------------------------------------------------
		// System.out.println("Image chargement");
		// boutonLoad = ImageIO.read(getClass().getResourceAsStream(CHEMIN +
		// "chargerPartie_flou.png"));
		// boutonLoadPresse = ImageIO.read(getClass().getResourceAsStream(CHEMIN +
		// "chargerPartie_presse.png"));
		// // IMAGE TUTO---------------------------------------------------------------
		// System.out.println("Image tutoriel");
		// Tuto1 = ImageIO.read(getClass().getResourceAsStream(CHEMIN +
		// "ConstructionPyramide.png"));
		// Tuto2 = ImageIO.read(getClass().getResourceAsStream(CHEMIN +
		// "PhaseDeJeu.png"));
		// Tuto3 = ImageIO.read(getClass().getResourceAsStream(CHEMIN +
		// "Penalite.png"));
		// Tuto4 = ImageIO.read(getClass().getResourceAsStream(CHEMIN +
		// "finDePartie.png"));
		// TutoPrecedent = ImageIO.read(getClass().getResourceAsStream(CHEMIN +
		// "retour.png"));
		// TutoSuivant = ImageIO.read(getClass().getResourceAsStream(CHEMIN +
		// "suivant.png"));
		// TutoMenu = ImageIO.read(getClass().getResourceAsStream(CHEMIN +
		// "menu_flou.png"));
		// // IMAGES
		// BOUTONS---------------------------------------------------------------
		// System.out.println("Image boutons");
		// boutonPVP = ImageIO.read(getClass().getResourceAsStream(CHEMIN + "PVP.png"));
		// boutonPVP_vert = ImageIO.read(getClass().getResourceAsStream(CHEMIN +
		// "PVP_vert.png"));
		// boutonPVP_gris = ImageIO.read(getClass().getResourceAsStream(CHEMIN +
		// "PVP_gris.png"));
		// boutonPVM = ImageIO.read(getClass().getResourceAsStream(CHEMIN + "PVM.png"));
		// boutonPVM_vert = ImageIO.read(getClass().getResourceAsStream(CHEMIN +
		// "PVM_vert.png"));
		// boutonPVM_gris = ImageIO.read(getClass().getResourceAsStream(CHEMIN +
		// "PVM_gris.png"));
		// boutonMVM = ImageIO.read(getClass().getResourceAsStream(CHEMIN + "MVM.png"));
		// boutonMVM_vert = ImageIO.read(getClass().getResourceAsStream(CHEMIN +
		// "MVM_vert.png"));
		// boutonMVM_gris = ImageIO.read(getClass().getResourceAsStream(CHEMIN +
		// "MVM_gris.png"));
		// boutonNomJ1 = ImageIO.read(getClass().getResourceAsStream(CHEMIN +
		// "nomJ1.png"));
		// boutonNomJ1_gris = ImageIO.read(getClass().getResourceAsStream(CHEMIN +
		// "nomJ1_gris.png"));
		// boutonNomJ2 = ImageIO.read(getClass().getResourceAsStream(CHEMIN +
		// "nomJ2.png"));
		// boutonNomJ2_gris = ImageIO.read(getClass().getResourceAsStream(CHEMIN +
		// "nomJ2_gris.png"));
		// boutonNomJoueur = ImageIO.read(getClass().getResourceAsStream(CHEMIN +
		// "nomJoueur.png"));
		// boutonNomJoueur_gris = ImageIO.read(getClass().getResourceAsStream(CHEMIN +
		// "nomJoueur_gris.png"));
		// boutonDifficulteOrdi1 = ImageIO.read(getClass().getResourceAsStream(CHEMIN +
		// "difficulte_ordi1.png"));
		// boutonDifficulteOrdi1_gris = ImageIO
		// .read(getClass().getResourceAsStream(CHEMIN + "difficulte_ordi1_gris.png"));
		// boutonDifficulteOrdi2 = ImageIO.read(getClass().getResourceAsStream(CHEMIN +
		// "difficulte_ordi2.png"));
		// boutonDifficulteOrdi2_gris = ImageIO
		// .read(getClass().getResourceAsStream(CHEMIN + "difficulte_ordi2_gris.png"));
		// boutonDifficulteOrdi = ImageIO.read(getClass().getResourceAsStream(CHEMIN +
		// "difficulte_ordi.png"));
		// boutonDifficulteOrdi_gris = ImageIO
		// .read(getClass().getResourceAsStream(CHEMIN + "difficulte_ordi_gris.png"));
		// boutonFacile = ImageIO.read(getClass().getResourceAsStream(CHEMIN +
		// "facile.png"));
		// boutonFacile_gris = ImageIO.read(getClass().getResourceAsStream(CHEMIN +
		// "facile_gris.png"));
		// boutonFacile_vert = ImageIO.read(getClass().getResourceAsStream(CHEMIN +
		// "facile_vert.png"));
		// boutonMoyen = ImageIO.read(getClass().getResourceAsStream(CHEMIN +
		// "moyen.png"));
		// boutonMoyen_gris = ImageIO.read(getClass().getResourceAsStream(CHEMIN +
		// "moyen_gris.png"));
		// boutonMoyen_vert = ImageIO.read(getClass().getResourceAsStream(CHEMIN +
		// "moyen_vert.png"));
		// boutonDifficile = ImageIO.read(getClass().getResourceAsStream(CHEMIN +
		// "difficile.png"));
		// boutonDifficile_gris = ImageIO.read(getClass().getResourceAsStream(CHEMIN +
		// "difficile_gris.png"));
		// boutonDifficile_vert = ImageIO.read(getClass().getResourceAsStream(CHEMIN +
		// "difficile_vert.png"));
		// boutonCommencer = ImageIO.read(getClass().getResourceAsStream(CHEMIN +
		// "lancerPartie.png"));
		// boutonCommencer_presse = ImageIO.read(getClass().getResourceAsStream(CHEMIN +
		// "lancerPartie_presse.png"));
		// boutonMelange = ImageIO.read(getClass().getResourceAsStream(CHEMIN +
		// "bouton_melange.png"));
		// boutonMelange_presse = ImageIO.read(getClass().getResourceAsStream(CHEMIN +
		// "bouton_melange_presse.png"));
		// boutonValider = ImageIO.read(getClass().getResourceAsStream(CHEMIN +
		// "bouton_valider.png"));
		// boutonValider_presse = ImageIO.read(getClass().getResourceAsStream(CHEMIN +
		// "bouton_valider_presse.png"));
		// boutonValider_gris = ImageIO.read(getClass().getResourceAsStream(CHEMIN +
		// "bouton_valider_gris.png"));
		// boutonPasserTour = ImageIO.read(getClass().getResourceAsStream(CHEMIN +
		// "passer_son_tour_flou.png"));
		// boutonPasserTour_gris = ImageIO.read(getClass().getResourceAsStream(CHEMIN +
		// "passer_son_tour_gris.png"));
		// boutonCoupPrecedent = ImageIO.read(getClass().getResourceAsStream(CHEMIN +
		// "coup_precedent_flou.png"));
		// boutonCoupPrecedent_gris = ImageIO.read(getClass().getResourceAsStream(CHEMIN
		// + "coup_precedent_gris.png"));
		// passerTour = ImageIO.read(getClass().getResourceAsStream(CHEMIN +
		// "passer_son_tour_flou.png"));
		// passerTour_gris = ImageIO.read(getClass().getResourceAsStream(CHEMIN +
		// "passer_son_tour_gris.png"));
		// imagevol = ImageIO.read(getClass().getResourceAsStream(CHEMIN +
		// "espacevol.png"));
		// valider = ImageIO.read(getClass().getResourceAsStream(CHEMIN +
		// "valider_flou.png"));
		// valider2 = ImageIO.read(getClass().getResourceAsStream(CHEMIN +
		// "valider2.png"));
		// // CURSEURS
		// System.out.println("chargement curseurs");
		// mainFermee = ImageIO.read(getClass().getResourceAsStream(CHEMIN +
		// "hand-closed.png"));
		// mainDepose = ImageIO.read(getClass().getResourceAsStream(CHEMIN +
		// "hand-depose.png"));
		// // LABELS
		// System.out.println("chargement labels");
		// texte_nomJ1 = new JTextField(30);
		// texte_nomJ2 = new JTextField(30);
		// texte_nomJoueur = new JTextField(30);
		// // PIECES
		// System.out.println("chargement pieces");
		// pieceVide = ImageIO.read(getClass().getResourceAsStream(CHEMIN +
		// "EMPTY2.png"));
		// pieceNoire = ImageIO.read(getClass().getResourceAsStream(CHEMIN +
		// "NOIR.png"));
		// pieceBleue = ImageIO.read(getClass().getResourceAsStream(CHEMIN +
		// "BLEU.png"));
		// pieceVert = ImageIO.read(getClass().getResourceAsStream(CHEMIN +
		// "VERT.png"));
		// pieceRouge = ImageIO.read(getClass().getResourceAsStream(CHEMIN +
		// "ROUGE.png"));
		// pieceBlanche = ImageIO.read(getClass().getResourceAsStream(CHEMIN +
		// "BLANC.png"));
		// pieceNature = ImageIO.read(getClass().getResourceAsStream(CHEMIN +
		// "NATUREL.png"));
		// pieceJaune = ImageIO.read(getClass().getResourceAsStream(CHEMIN +
		// "JAUNE.png"));
		// } catch (Exception e) {
		// e.printStackTrace();
		// }

	}
}
