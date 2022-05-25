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
	public Image menuBouton1;
	public Image menuBouton1_presse;
	public Image menuBouton2;
	public Image menuBouton2_presse;
	public Image menuBouton3;
	public Image menuBouton3_presse;
	public Image menuBouton4;
	public Image menuBouton4_presse;
	
	//IMAGE CHARGEMENT
	public Image boutonLoad;
	public Image boutonLoadPresse;

	public LoadTexture(String optionchemin) {
		this.CHEMIN = System.getProperty("user.dir")+optionchemin;
		
		//IMAGE FOND -------------------------------------------------------------
		String NOMBACKGROUND = "background.jpg";
		background = Toolkit.getDefaultToolkit().createImage(CHEMIN+NOMBACKGROUND);
		//IMAGE PIECE -------------------------------------------------------------
		String NOMPIECEVIDE = "EMPTY3.png";
		pieceVide = Toolkit.getDefaultToolkit().createImage(CHEMIN+NOMPIECEVIDE);
		String NOMPIECEBLACK = "BLACK2.png";
		pieceNoire = Toolkit.getDefaultToolkit().createImage(CHEMIN+NOMPIECEBLACK);
		String NOMPIECEBLEU = "BLUE2.png";
		pieceBleu = Toolkit.getDefaultToolkit().createImage(CHEMIN+NOMPIECEBLEU);
		String NOMPIECEVERT = "GREEN2.png";
		pieceVert = Toolkit.getDefaultToolkit().createImage(CHEMIN+NOMPIECEVERT);
		String NOMPIECEROUGE = "RED2.png";
		pieceRouge = Toolkit.getDefaultToolkit().createImage(CHEMIN+NOMPIECEROUGE);
		String NOMPIECEBLANC = "WHITE2.png";
		pieceBlanche = Toolkit.getDefaultToolkit().createImage(CHEMIN+NOMPIECEBLANC);
		String NOMPIECENATURE = "WOOD2.png";
		pieceNature = Toolkit.getDefaultToolkit().createImage(CHEMIN+NOMPIECENATURE);
		String NOMPIECEJAUNE = "YELLOW2.png";
		pieceJaune = Toolkit.getDefaultToolkit().createImage(CHEMIN+NOMPIECEJAUNE);
		//IMAGE MENU---------------------------------------------------------------
		menuBouton1 = Toolkit.getDefaultToolkit().createImage(CHEMIN+"nouvellePartie_flou.png");
		menuBouton1_presse = Toolkit.getDefaultToolkit().createImage(CHEMIN+"nouvellePartie_presse.png");
		menuBouton2 = Toolkit.getDefaultToolkit().createImage(CHEMIN+"options_flou.png");
		menuBouton2_presse = Toolkit.getDefaultToolkit().createImage(CHEMIN+"options_presse.png");
		menuBouton3 = Toolkit.getDefaultToolkit().createImage(CHEMIN+"tutoriel_flou.png");
		menuBouton3_presse = Toolkit.getDefaultToolkit().createImage(CHEMIN+"tutoriel_presse.png");
		menuBouton4 = Toolkit.getDefaultToolkit().createImage(CHEMIN+"quitter_flou.png");
		menuBouton4_presse = Toolkit.getDefaultToolkit().createImage(CHEMIN+"quitter_presse.png");
		//IMAGE CHARGEMENT---------------------------------------------------------------
		String NOMBOUTONLOADENFONCER = "chargerPartie.png";
		boutonLoad = Toolkit.getDefaultToolkit().createImage(CHEMIN+NOMBOUTONLOADENFONCER);
		String NOMBOUTONLOADRELACHER = "chargerPartiePresse.png";
		boutonLoadPresse = Toolkit.getDefaultToolkit().createImage(CHEMIN+NOMBOUTONLOADRELACHER);
	}
}