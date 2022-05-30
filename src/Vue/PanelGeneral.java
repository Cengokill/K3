package Vue;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Modeles.OptionsJeu;
import Modeles.SoundPlayer;
import Vue.TexturePack.LoadTexture;

public class PanelGeneral extends JPanel{
	public Dimension tailleEcran, tailleFenetre;
	public int screenHeight, screenWidth, frameHeight, frameWidth;
	public JFrame window;
	public int posX_background, posY_background, largeur_background, hauteur_background, offset_vertical;
	public LoadTexture texture;
	public SoundPlayer simpleSoundPlayerSon;
	public OptionsJeu options;
	public boolean estPleinEcran;
	
	public PanelGeneral(JFrame w, LoadTexture t, OptionsJeu o) {
		this.window=w;
		this.texture=t;
		this.options=o;
		this.simpleSoundPlayerSon = new SoundPlayer(options.volumeEffetsSonores, texture.CHEMIN);
		this.tailleEcran = Toolkit.getDefaultToolkit().getScreenSize();
		this.screenWidth=tailleEcran.width;
        this.screenHeight=tailleEcran.height;
		this.tailleFenetre=window.getSize();
        this.frameWidth=tailleFenetre.width;
        this.frameHeight=tailleFenetre.width;
        window.setLocation(0,0);
        window.setBackground(Color.BLACK);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.addKeyListener(new TouchesClavier(this));
        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	}
	
	public void fullScreenOn() {
		estPleinEcran=true;
    	window.dispose();
    	window.setLocation(0,0);
    	window.setSize(tailleEcran);
    	window.setUndecorated(true);
    	window.setVisible(true);
	}
	
	public void fullScreenOff() {
		estPleinEcran=false;
    	window.dispose();
    	window.setLocation(screenWidth/2-frameWidth/2,screenHeight/2-frameHeight/3);
    	window.setUndecorated(false);
    	window.setVisible(true);
	}
	
	public void jouerSonSurvol() {
		this.simpleSoundPlayerSon.setNumSon(38);
		this.simpleSoundPlayerSon.jouerSon();
	}
	
	public void jouerSonClic() {
		this.simpleSoundPlayerSon.setNumSon(17);
		this.simpleSoundPlayerSon.jouerSon();
	}
	
    public void jouerSonLancement() {
		this.simpleSoundPlayerSon.setNumSon(29);
		this.simpleSoundPlayerSon.jouerSon();
	}
	
    public void affichageBackGround(Graphics g, int i) {//3840x2160
    	double rapport = 0.5625;// rapport de 2160/3840
		if(frameHeight/frameWidth>rapport) {
			largeur_background=frameWidth;
			hauteur_background=(int)(largeur_background*rapport);
			posX_background=0;
			posY_background=(frameHeight-hauteur_background)/2;
		}
		else { //if(frameHeight/frameWidth<=rapport) {
			hauteur_background=frameHeight;
			largeur_background=(int)(hauteur_background/rapport);
			posX_background=(frameWidth-largeur_background)/2;
			posY_background=0;
		}
		if(i==0) {
			g.drawImage(texture.background, posX_background, posY_background, largeur_background, hauteur_background, null);
		}else if(i==1) {
			g.drawImage(texture.backgroundSansLogo, posX_background, posY_background, largeur_background, hauteur_background, null);
		}
	    
    }
    
	public void setChangementTaillefenetre() {
		this.tailleFenetre=window.getSize();
		this.frameWidth=window.getWidth();
        this.frameHeight=window.getHeight();
        offset_vertical = hauteur_background/3;
	}
    
    

}
