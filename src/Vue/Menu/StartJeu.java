package Vue.Menu;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Modeles.SoundPlayer;

public class StartJeu extends JPanel{

	public JFrame window;
	public Dimension tailleEcran, tailleFenetre;
	public final String CHEMIN = System.getProperty("user.dir")+"./src/Ressources/";
	public Image background = Toolkit.getDefaultToolkit().createImage(CHEMIN+"background.jpg");
	public Image bouton1 = Toolkit.getDefaultToolkit().createImage(CHEMIN+"nouvellePartie_flou.png");
	public Image bouton1_presse = Toolkit.getDefaultToolkit().createImage(CHEMIN+"nouvellePartie_presse_flou.png");
	public Image bouton2 = Toolkit.getDefaultToolkit().createImage(CHEMIN+"options_flou.png");
	public Image bouton2_presse = Toolkit.getDefaultToolkit().createImage(CHEMIN+"options_presse_flou.png");
	public Image bouton3 = Toolkit.getDefaultToolkit().createImage(CHEMIN+"tutoriel_flou.png");
	public Image bouton3_presse = Toolkit.getDefaultToolkit().createImage(CHEMIN+"tutoriel_presse_flou.png");
	public Image bouton4 = Toolkit.getDefaultToolkit().createImage(CHEMIN+"quitter_flou.png");
	public Image bouton4_presse = Toolkit.getDefaultToolkit().createImage(CHEMIN+"quitter_presse_flou.png");
    public int screenHeight, screenWidth, frameHeight, frameWidth, centreX_Screen, centreY_Screen, centreX_Window, centreY_Window;
    public int posX_nouvellePartie, posY_nouvellePartie, hauteur_bouton, largeur_bouton;
    public int posX_options, posY_options;
    public int posX_quitter, posY_quitter;
    public int posX_tuto, posY_tuto;
    public boolean enfonce_nouvellePartie=false;
    public boolean enfonce_options=false;
    public boolean enfonce_tuto=false;
    public boolean enfonce_quitter=false;
    public Chargement chargement;
    private SoundPlayer simpleSoundPlayerMusic, simpleSoundPlayerSon;
	
	public StartJeu(JFrame w, Chargement ch) {
    	this.chargement=ch;
		this.window = w;
		window.setTitle("Partie en cours");
		this.tailleEcran = Toolkit.getDefaultToolkit().getScreenSize();
		this.tailleFenetre=window.getSize();
        this.frameWidth=window.getWidth();
        this.frameHeight=window.getHeight();
        this.screenWidth=tailleEcran.width;
        this.screenHeight=tailleEcran.height;
        this.centreX_Screen=screenWidth/2;
        this.centreY_Screen=screenHeight/2;
        this.centreX_Window=centreX_Screen-frameWidth/2;
        this.centreY_Window=centreY_Screen-frameHeight/2;
		window.setLocation(centreX_Window, centreY_Window);
		window.setSize(screenWidth/2,screenHeight/2);
	    //window.setExtendedState(JFrame.MAXIMIZED_BOTH);
		window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		window.setVisible(true);
		this.simpleSoundPlayerMusic = new SoundPlayer(5);
		this.simpleSoundPlayerSon = new SoundPlayer(8);
		this.simpleSoundPlayerMusic.setNumSon(43);
		this.simpleSoundPlayerMusic.loopSon();
		changementTaillefenetre();
	}

	public void paint(Graphics g) {
		if(tailleFenetre != window.getSize()) {
			//on detecte un changement de fenetre -> on met a jour L IHM
			changementTaillefenetre();
		}
		affichageBackGround(g);
		afficheBoutonNouvellePartie(g);
		afficheBoutonOptions(g);
		afficheBoutonTuto(g);
		afficheBoutonQuitter(g);
	}
	
	public void affichageBackGround(Graphics g) {
	    g.drawImage(background, 0, 0, frameWidth, frameHeight, null);
	}
	
	public void changementTaillefenetre() {
		this.tailleFenetre=window.getSize();
		this.frameWidth=window.getWidth();
        this.frameHeight=window.getHeight();
        this.centreX_Window=centreX_Screen-frameWidth/2;
        this.centreY_Window=centreY_Screen-frameHeight/2;
        int espacement=60;
        
        this.largeur_bouton=(int)(814/1.4);
        this.hauteur_bouton=(int)(largeur_bouton*0.1904176904);//rapport de 155/814
        if(frameWidth<(screenWidth*0.25) || frameHeight<(frameHeight*0.25) ) {
        	this.largeur_bouton=(int)(814/4);
            this.hauteur_bouton=(int)(largeur_bouton*0.1904176904);//rapport de 155/814
            espacement=16;
        }
        else if(frameWidth<(screenWidth*0.45) || frameHeight<(frameHeight*0.45) ) {
        	this.largeur_bouton=(int)(814/2);
            this.hauteur_bouton=(int)(largeur_bouton*0.1904176904);//rapport de 155/814
            espacement=44;
        }
        this.posX_nouvellePartie=frameWidth/2-largeur_bouton/2;
        this.posY_nouvellePartie=frameHeight/10;
        this.posX_options=frameWidth/2-largeur_bouton/2;
        this.posY_options=posY_nouvellePartie+hauteur_bouton+espacement;
        this.posX_tuto=frameWidth/2-largeur_bouton/2;
        this.posY_tuto=posY_options+hauteur_bouton+espacement;
        this.posX_quitter=frameWidth/2-largeur_bouton/2;
        this.posY_quitter=posY_tuto+hauteur_bouton+espacement;
	}
	
	public void setFullScreen(boolean b) {//plein ecran
		window.setUndecorated(b);;
	}
	
	public void jouerSonSurvol() {
		this.simpleSoundPlayerSon.setNumSon(38);
		this.simpleSoundPlayerSon.jouerSon();
	}
	
	public void afficheBoutonNouvellePartie(Graphics g) {
		if(!enfonce_nouvellePartie) {
			g.drawImage(bouton1, posX_nouvellePartie, posY_nouvellePartie, largeur_bouton, hauteur_bouton, null);
		}else {
			g.drawImage(bouton1_presse, posX_nouvellePartie, posY_nouvellePartie, largeur_bouton, hauteur_bouton, null);
		}
	}
	
	public void afficheBoutonOptions(Graphics g) {
		if(!enfonce_options) {
			g.drawImage(bouton2, posX_options, posY_options, largeur_bouton, hauteur_bouton, null);
		}else {
			g.drawImage(bouton2_presse, posX_options, posY_options, largeur_bouton, hauteur_bouton, null);
		}
	}
	
	public void afficheBoutonTuto(Graphics g) {
		if(!enfonce_tuto) {
			g.drawImage(bouton3, posX_tuto, posY_tuto, largeur_bouton, hauteur_bouton, null);
		}else {
			g.drawImage(bouton3_presse, posX_tuto, posY_tuto, largeur_bouton, hauteur_bouton, null);
		}
	}
	
	public void afficheBoutonQuitter(Graphics g) {
		if(!enfonce_quitter) {
			g.drawImage(bouton4, posX_quitter, posY_quitter, largeur_bouton, hauteur_bouton, null);
		}else {
			g.drawImage(bouton4_presse, posX_quitter, posY_quitter, largeur_bouton, hauteur_bouton, null);
		}
	}

}
