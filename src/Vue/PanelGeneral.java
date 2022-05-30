package Vue;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Vue.TexturePack.LoadTexture;

public class PanelGeneral extends JPanel{
	public Dimension tailleEcran, tailleFenetre;
	public int screenHeight, screenWidth, frameHeight, frameWidth;
	public JFrame window;
	public int posX_background, posY_background, largeur_background, hauteur_background, offset_vertical;
	public LoadTexture texture;
	
	public PanelGeneral(JFrame w, LoadTexture t) {
		this.window=w;
		this.texture=t;
		this.tailleEcran = Toolkit.getDefaultToolkit().getScreenSize();
		this.screenWidth=tailleEcran.width;
        this.screenHeight=tailleEcran.height;
		this.tailleFenetre=window.getSize();
        this.frameWidth=tailleFenetre.width;
        this.frameHeight=tailleFenetre.width;
        window.addKeyListener(new TouchesClavier(this));
	}
	
    public void affichageBackGround(Graphics g) {//3840x2160
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
	    g.drawImage(texture.background, posX_background, posY_background, largeur_background, hauteur_background, null);
    }
    
	public void setChangementTaillefenetre() {
		this.tailleFenetre=window.getSize();
		this.frameWidth=window.getWidth();
        this.frameHeight=window.getHeight();
        offset_vertical = hauteur_background/3;
	}
    
    

}
