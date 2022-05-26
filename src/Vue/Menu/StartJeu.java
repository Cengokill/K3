package Vue.Menu;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import Modeles.Aleatoire;
import Modeles.SoundPlayer;
import Vue.TexturePack.LoadTexture;

public class StartJeu extends JPanel implements ActionListener{

	public JFrame window;
	public Dimension tailleEcran, tailleFenetre;
	public LoadTexture texture;
    public int screenHeight, screenWidth, frameHeight, frameWidth;
    public int posX_bouton, posY_nouvellePartie, hauteur_bouton, largeur_bouton;
    public int posY_options, posY_quitter, posY_charger, posY_tuto;
    public int posX_Ile, posY_Ile, posMaxX_Ile, posMaxY_Ile, posMinX_Ile, posMinY_Ile;
    public int animX, animY;
    public boolean enfonce_nouvellePartie=false;
    public boolean enfonce_options=false;
    public boolean enfonce_tuto=false;
    public boolean enfonce_quitter=false;
    public boolean enfonce_charger=false;
    public boolean animation1=false;
    public Chargement chargement;
    private SoundPlayer simpleSoundPlayerSon;
    Timer animationTimer;
    Aleatoire rand = new Aleatoire();
	
	public StartJeu(JFrame w, Chargement ch, LoadTexture texture) {
		this.texture = texture;
		this.simpleSoundPlayerSon = new SoundPlayer(8);
    	this.chargement=ch;
		this.window = w;
		window.setTitle("Partie en cours");
		this.tailleEcran = Toolkit.getDefaultToolkit().getScreenSize();
		this.screenWidth=tailleEcran.width;
        this.screenHeight=tailleEcran.height;
		this.tailleFenetre=window.getSize();
        this.frameWidth=tailleFenetre.width;
        this.frameHeight=tailleFenetre.width;
        addMouseListener(new StartJeuClics(this));
	    window.setExtendedState(JFrame.MAXIMIZED_BOTH);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
		this.animationTimer = new Timer(120, this);
		this.animationTimer.start();
		animX=2;
		animY=2;
	}
	
	public void affichageBackGround(Graphics g) {
	    g.drawImage(texture.background, 0, 0, frameWidth, frameHeight, null);
	}
	
	public void changementTaillefenetre() {
		this.tailleFenetre=window.getSize();
		this.frameWidth=window.getWidth();
        this.frameHeight=window.getHeight();
        int espacement=30;
        double rapport=0.1933045356;//rapport de 179/926
        int largeur_pixels=926;
        this.largeur_bouton=(int)(largeur_pixels/1.7);
        this.hauteur_bouton=(int)(largeur_bouton*rapport);
        if(frameWidth<(screenWidth*0.3) || frameHeight<(screenHeight*0.3) ) {
        	this.largeur_bouton=(int)(largeur_pixels/4.5);
            this.hauteur_bouton=(int)(largeur_bouton*rapport);
            espacement=(int)(espacement*0.3);
        }
        else if(frameWidth<(screenWidth*0.45) || frameHeight<(screenHeight*0.45) ) {
        	this.largeur_bouton=(int)(largeur_pixels/3.7);
            this.hauteur_bouton=(int)(largeur_bouton*rapport);
            espacement=(int)(espacement*0.45);
        }
        this.posX_bouton=frameWidth/2-largeur_bouton/2;
        
        this.posY_nouvellePartie=frameHeight/4+frameHeight/12;
        
        this.posY_charger=posY_nouvellePartie+hauteur_bouton+espacement;
        if(!animation1) {
	    	posX_Ile=posX_bouton-largeur_bouton;
			posY_Ile=posY_charger;
			posMaxX_Ile=posX_Ile+50;
			posMinX_Ile=posX_Ile-50;
			posMaxY_Ile=posY_Ile+120;
			posMinY_Ile=posY_Ile-120;
		}else {
		}
        
        this.posY_options=posY_charger+hauteur_bouton+espacement;
        
        this.posY_tuto=posY_options+hauteur_bouton+espacement;
        
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
			g.drawImage(texture.menuBouton1, posX_bouton, posY_nouvellePartie, largeur_bouton, hauteur_bouton, null);
		}else {
			g.drawImage(texture.menuBouton1_presse, posX_bouton, posY_nouvellePartie, largeur_bouton, hauteur_bouton, null);
		}
	}
	
	public void afficheBoutonCharger(Graphics g) {
		if(!enfonce_charger) {
			g.drawImage(texture.menuBoutonCharger, posX_bouton, posY_charger, largeur_bouton, hauteur_bouton, null);
		}else {
			g.drawImage(texture.menuBoutonCharger_presse, posX_bouton, posY_charger, largeur_bouton, hauteur_bouton, null);
		}
	}
	
	public void afficheBoutonOptions(Graphics g) {
		if(!enfonce_options) {
			g.drawImage(texture.menuBouton2, posX_bouton, posY_options, largeur_bouton, hauteur_bouton, null);
		}else {
			g.drawImage(texture.menuBouton2_presse, posX_bouton, posY_options, largeur_bouton, hauteur_bouton, null);
		}
	}
	
	public void afficheBoutonTuto(Graphics g) {
		if(!enfonce_tuto) {
			g.drawImage(texture.menuBouton3, posX_bouton, posY_tuto, largeur_bouton, hauteur_bouton, null);
		}else {
			g.drawImage(texture.menuBouton3_presse, posX_bouton, posY_tuto, largeur_bouton, hauteur_bouton, null);
		}
	}
	
	public void afficheBoutonQuitter(Graphics g) {
		if(!enfonce_quitter) {
			g.drawImage(texture.menuBouton4, posX_bouton, posY_quitter, largeur_bouton, hauteur_bouton, null);
		}else {
			g.drawImage(texture.menuBouton4_presse, posX_bouton, posY_quitter, largeur_bouton, hauteur_bouton, null);
		}
	}
	
	public void afficheIle1(Graphics g) {
		g.drawImage(texture.ile1, posX_Ile, posY_Ile, 660/3, 968/3, null);
	}
	
	public void animerIle1() {
		animation1=true;
		if(posX_Ile>=posMaxX_Ile) {
			posX_Ile+=rand.genInt(-animX, 0);
		}else if(posX_Ile<=posMaxX_Ile) {
			posX_Ile+=rand.genInt(0, animY);
		}else {
			posX_Ile+=rand.genInt(-animX, animY);
		}
		if(posY_Ile>=posMaxY_Ile) {
			posY_Ile+=rand.genInt(-animX, 0);
		}else if(posY_Ile<=posMinY_Ile){
			posY_Ile+=rand.genInt(0, animY);
		}else {
			posY_Ile+=rand.genInt(-animX, animY);
		}
		repaint();
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		if(tailleFenetre != window.getSize()) {
			//on detecte un changement de fenetre -> on met a jour L IHM
			changementTaillefenetre();
		}
		affichageBackGround(g);
		afficheIle1(g);
		afficheBoutonNouvellePartie(g);
		afficheBoutonCharger(g);
		afficheBoutonOptions(g);
		afficheBoutonTuto(g);
		afficheBoutonQuitter(g);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		animerIle1();
	}

}
