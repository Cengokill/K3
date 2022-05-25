package Vue.Menu;

import java.awt.Color;
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

import Controleur.Jeu;
import Modeles.SoundPlayer;
import Vue.TexturePack.LoadTexture;

public class StartJeu extends JPanel{

	public JFrame window;
	public Dimension tailleEcran, tailleFenetre;
	public LoadTexture texture;
    public int screenHeight, screenWidth, frameHeight, frameWidth;
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
	
	public StartJeu(JFrame w, Chargement ch, LoadTexture texture) {
		this.texture = texture;
		this.simpleSoundPlayerMusic = new SoundPlayer(2);
		this.simpleSoundPlayerSon = new SoundPlayer(8);
		this.simpleSoundPlayerMusic.setNumSon(43);
		this.simpleSoundPlayerMusic.loopSon();
    	this.chargement=ch;
		this.window = w;
		window.setTitle("Partie en cours");
		this.tailleEcran = Toolkit.getDefaultToolkit().getScreenSize();
		this.screenWidth=tailleEcran.width;
        this.screenHeight=tailleEcran.height;
		this.tailleFenetre=window.getSize();
        this.frameWidth=tailleFenetre.width;
        this.frameHeight=tailleFenetre.width;
        window.setLocationRelativeTo(null);
	    window.setExtendedState(JFrame.MAXIMIZED_BOTH);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
	}
	
	public void affichageBackGround(Graphics g) {
	    g.drawImage(texture.background, 0, 0, frameWidth, frameHeight, null);
	}
	
	public void changementTaillefenetre() {
		this.tailleFenetre=window.getSize();
		this.frameWidth=window.getWidth();
        this.frameHeight=window.getHeight();
        int espacement=60;
        double rapport=0.1933045356;//rapport de 179/926
        int largeur_pixels=926;
        this.largeur_bouton=(int)(largeur_pixels/1.7);
        this.hauteur_bouton=(int)(largeur_bouton*rapport);
        if(frameWidth<(screenWidth*0.3) || frameHeight<(frameHeight*0.3) ) {
        	this.largeur_bouton=(int)(largeur_pixels/4);
            this.hauteur_bouton=(int)(largeur_bouton*rapport);
            espacement=16;
        }
        else if(frameWidth<(screenWidth*0.45) || frameHeight<(frameHeight*0.45) ) {
        	this.largeur_bouton=(int)(largeur_pixels/2.5);
            this.hauteur_bouton=(int)(largeur_bouton*rapport);
            espacement=44;
        }
        this.posX_nouvellePartie=frameWidth/2-largeur_bouton/2;
        this.posY_nouvellePartie=frameHeight/5+frameHeight/10;
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
			g.drawImage(texture.menuBouton1, posX_nouvellePartie, posY_nouvellePartie, largeur_bouton, hauteur_bouton, null);
		}else {
			g.drawImage(texture.menuBouton1_presse, posX_nouvellePartie, posY_nouvellePartie, largeur_bouton, hauteur_bouton, null);
		}
	}
	
	public void afficheBoutonOptions(Graphics g) {
		if(!enfonce_options) {
			g.drawImage(texture.menuBouton2, posX_options, posY_options, largeur_bouton, hauteur_bouton, null);
		}else {
			g.drawImage(texture.menuBouton2_presse, posX_options, posY_options, largeur_bouton, hauteur_bouton, null);
		}
	}
	
	public void afficheBoutonTuto(Graphics g) {
		if(!enfonce_tuto) {
			g.drawImage(texture.menuBouton3, posX_tuto, posY_tuto, largeur_bouton, hauteur_bouton, null);
		}else {
			g.drawImage(texture.menuBouton3_presse, posX_tuto, posY_tuto, largeur_bouton, hauteur_bouton, null);
		}
	}
	
	public void afficheBoutonQuitter(Graphics g) {
		if(!enfonce_quitter) {
			g.drawImage(texture.menuBouton4, posX_quitter, posY_quitter, largeur_bouton, hauteur_bouton, null);
		}else {
			g.drawImage(texture.menuBouton4_presse, posX_quitter, posY_quitter, largeur_bouton, hauteur_bouton, null);
		}
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

}
