/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Modeles.InitPartie;
import Vue.TexturePack.LoadTexture;

public class NouvellePartie extends JPanel {
    //PARAMETRES JEU
    private final JFrame window;
    public Dimension tailleFenetre;
    public JTextField nomJ1, nomJ2, nomJ3;
    
    //AFFICHAGE FIXE
    public int posX_label_nomJ1, posX_label_nomJ2, posX_label_nomJ3, posX_Label_diff_IA1, posX_Label_diff_IA2, posX_Label_diff_IA3;
    public int posX_PVP, posX_PVM, posX_MVM, posX_IA1_FACILE, posX_IA1_MOYEN, posX_IA1_DIFFICILE, posX_IA2_FACILE, posX_IA2_MOYEN, posX_IA2_DIFFICILE, posX_IA3_FACILE, posX_IA3_MOYEN, posX_IA3_DIFFICILE, posX_COMMENCER;
    public int posY_label_nomJ1, posY_label_nomJ2, posY_label_nomJ3, posY_Label_diff_IA1, posY_Label_diff_IA2, posY_Label_diff_IA3;
    public int posY_PVP, posY_PVM, posY_MVM, posY_IA1_FACILE, posY_IA1_MOYEN, posY_IA1_DIFFICILE, posY_IA2_FACILE, posY_IA2_MOYEN, posY_IA2_DIFFICILE, posY_IA3_FACILE, posY_IA3_MOYEN, posY_IA3_DIFFICILE, posY_COMMENCER;
    
    public boolean enfonce_pb_PVP = false;
    public boolean enfonce_pb_PVM = false;
    public boolean enfonce_pb_MVM = false;
    public boolean enfonce_IA1_FACILE = false;
    public boolean enfonce_IA1_MOYEN = false;
    public boolean enfonce_IA1_DIFFICILE = false;
    public boolean enfonce_IA2_FACILE = false;
    public boolean enfonce_IA2_MOYEN = false;
    public boolean enfonce_IA2_DIFFICILE = false;
    public boolean enfonce_IA3_FACILE = false;
    public boolean enfonce_IA3_MOYEN = false;
    public boolean enfonce_IA3_DIFFICILE = false;
    public boolean enfonce_pb_COMMENCER = false;
    public int largeur_bouton1, hauteur_bouton1, largeur_bouton2, hauteur_bouton2, largeur_bouton3, hauteur_bouton3; 
    public int frameHeight, frameWidth;
    private final Dimension tailleEcran;
    private final int screenWidth;
    private final int screenHeight;
    int espacement_horizontal;
    int espacement_vertical;
    int offset_horizontal, offset_vertical, offset_h2, offset_h3;
    public LoadTexture textures;
    public InitPartie partie;
  
    // METHODE NOUVELLE PARTIE
    public NouvellePartie(JFrame w, LoadTexture t, InitPartie p){
        this.window=w;
        this.textures=t;
        this.partie=p;
        window.setTitle("Nouvelle Partie");
        this.tailleEcran = Toolkit.getDefaultToolkit().getScreenSize();
        this.screenWidth=tailleEcran.width;
        this.screenHeight=tailleEcran.height;
       	this.tailleFenetre=window.getSize();
        this.frameWidth=screenWidth;
        this.frameHeight=screenHeight;
        addMouseListener(new NouvellePartieClics(this));
        window.setBackground(Color.BLACK);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
    }
    public void affichageBackGround(Graphics g) {//3840x2160
    	double rapport = 0.5625;// rapport de 2160/3840
    	int largeur=3840, hauteur=2160;
    	int posX=0, posY=0;
		if(frameHeight/frameWidth>rapport) {
			largeur=frameWidth;
			hauteur=(int)(largeur*rapport);
			posX=0;
			posY=(frameHeight-hauteur)/2;
		}
		else { //if(frameHeight/frameWidth<=rapport) {
			hauteur=frameHeight;
			largeur=(int)(hauteur/rapport);
			posX=(frameWidth-largeur)/2;
			posY=0;
		}
	    g.drawImage(textures.backgroundSansLogo, posX, posY, largeur, hauteur, null);
	    System.out.println(frameWidth+", "+frameHeight);
    }
    
    public void changementTaillefenetre() {
		this.tailleFenetre=window.getSize();
		this.frameWidth=window.getWidth();
        this.frameHeight=window.getHeight();
        double rapport1=0.1813304721030043;//rapport de 169/932
        int largeur_pixels1=932;
        double rapport2=0.2203389830508475;//rapport de 169/767
        int largeur_pixels2=767;
        double rapport3=0.4183168316831683;//rapport de 169/404
        int largeur_pixels3=404;
       
        this.largeur_bouton1=(int)(frameWidth/5.5);
        this.hauteur_bouton1=(int)(largeur_bouton1*rapport1);
        
        this.largeur_bouton2=(int)(frameWidth/4);
        this.hauteur_bouton2=(int)(largeur_bouton2*rapport2);
        
        this.largeur_bouton3=(int)(frameWidth/13);
        this.hauteur_bouton3=(int)(largeur_bouton3*rapport3);
        int espacement1 = largeur_bouton1/4;
        
        offset_horizontal = frameWidth/24;
        offset_vertical = (((frameHeight / 10) - hauteur_bouton1)/2);
    
        this.offset_h2 = this.offset_horizontal + (this.largeur_bouton1 - this.largeur_bouton2 )/2;
        this.offset_h3 = this.offset_h2 +  (this.largeur_bouton2 - this.largeur_bouton3)/2;
        
        //Position bouton mode
        this.posX_PVM = frameWidth/2-largeur_bouton1/2;
        this.posY_PVM = offset_vertical;
        this.posX_PVP = posX_PVM-espacement1-largeur_bouton1;
        this.posY_PVP = offset_vertical;
        this.posX_MVM = posX_PVM+espacement1+largeur_bouton1;
        this.posY_MVM = offset_vertical;
                
        //Position bouton label
        //nom des joueurs
        this.posX_label_nomJ1 = posX_PVP;
        this.posY_label_nomJ1 = frameHeight / 10 + offset_vertical;
        this.posX_label_nomJ2 = posX_label_nomJ1;
        this.posY_label_nomJ2 = 5*frameHeight / 10;
        this.posX_label_nomJ3 = posX_PVM;
        this.posY_label_nomJ3 = frameHeight / 10 + offset_vertical;
        
        //IA1 et ses difficultes
        this.posX_Label_diff_IA1 = posX_PVM;
        this.posY_Label_diff_IA1 = 5*frameHeight / 10 ;
        this.posX_IA1_FACILE = posX_PVM+largeur_bouton1/2-largeur_bouton3/2;
        this.posY_IA1_FACILE = 6*frameHeight / 10 - offset_vertical;
        this.posX_IA1_MOYEN = posX_IA1_FACILE;
        this.posY_IA1_MOYEN = 7*frameHeight / 10 - 5*offset_vertical/2;
        this.posX_IA1_DIFFICILE = posX_IA1_MOYEN;
        this.posY_IA1_DIFFICILE = 8*frameHeight / 10 - 4*offset_vertical;
        
        //IA2 et ses difficultes
        this.posX_Label_diff_IA2 = posX_MVM;
        this.posY_Label_diff_IA2 = frameHeight / 10 + offset_vertical;
        this.posX_IA2_FACILE = posX_MVM+largeur_bouton1/2-largeur_bouton3/2;
        this.posY_IA2_FACILE = 2*frameHeight / 10 ;
        this.posX_IA2_MOYEN = posX_IA2_FACILE;
        this.posY_IA2_MOYEN = 3*frameHeight / 10 - 3*offset_vertical/2;
        this.posX_IA2_DIFFICILE = posX_IA2_MOYEN;
        this.posY_IA2_DIFFICILE = 4*frameHeight / 10 - 3*offset_vertical;
        
        //IA3 et se difficultes
        this.posX_Label_diff_IA3 = posX_MVM;
        this.posY_Label_diff_IA3 = 5*frameHeight / 10 ;
        this.posX_IA3_FACILE = posX_MVM+largeur_bouton1/2-largeur_bouton3/2;
        this.posY_IA3_FACILE = 6*frameHeight / 10 - offset_vertical;
        this.posX_IA3_MOYEN = posX_IA3_FACILE;
        this.posY_IA3_MOYEN = 7*frameHeight / 10 - 5*offset_vertical/2;
        this.posX_IA3_DIFFICILE = posX_IA3_MOYEN;
        this.posY_IA3_DIFFICILE = 8*frameHeight / 10 - 4*offset_vertical;
        
        //Position bouton commencer       
        this.posX_COMMENCER = posX_PVM;
        this.posY_COMMENCER = 8 * frameHeight / 10 + 7 *offset_vertical /8 ;
    }
    
    public void afficheBoutonPVP(Graphics g) {
		if(!enfonce_pb_PVP) {
	            g.drawImage(textures.boutonPVP, posX_PVP, posY_PVP, largeur_bouton1, hauteur_bouton1, null);
		}else {
	            g.drawImage(textures.boutonPVP_vert, posX_PVP, posY_PVP, largeur_bouton1, hauteur_bouton1, null);
		}
    }
    
    public void afficheBoutonPVM(Graphics g) {   
		if(!enfonce_pb_PVM) {
	            g.drawImage(textures.boutonPVM, posX_PVM, posY_PVM, largeur_bouton1, hauteur_bouton1, null);
		}else {
	            g.drawImage(textures.boutonPVM_vert, posX_PVM, posY_PVM, largeur_bouton1, hauteur_bouton1, null);
		}
    }
    
    public void afficheBoutonMVM(Graphics g) {   
		if(!enfonce_pb_MVM) {
	            g.drawImage(textures.boutonMVM, posX_MVM, posY_MVM, largeur_bouton1, hauteur_bouton1, null);
		}else {
	            g.drawImage(textures.boutonMVM_vert, posX_MVM, posY_MVM, largeur_bouton1, hauteur_bouton1, null);
		}
    }
    
    public void afficheBoutonIA1_FACILE(Graphics g) {   
		if(!enfonce_IA1_FACILE) {
	            g.drawImage(textures.boutonFacile, posX_IA1_FACILE, posY_IA1_FACILE, largeur_bouton3, hauteur_bouton3, null);
		}else {
	            g.drawImage(textures.boutonFacile_vert, posX_IA1_FACILE, posY_IA1_FACILE, largeur_bouton3, hauteur_bouton3, null);
		}
    }
    
    public void afficheBoutonIA1_MOYEN(Graphics g) {   
		if(!enfonce_IA1_MOYEN) {
	            g.drawImage(textures.boutonMoyen, posX_IA1_MOYEN, posY_IA1_MOYEN, largeur_bouton3, hauteur_bouton3, null);
		}else {
	            g.drawImage(textures.boutonMoyen_vert, posX_IA1_MOYEN, posY_IA1_MOYEN, largeur_bouton3, hauteur_bouton3, null);
		}
    }
    
    public void afficheBoutonIA1_DIFFICILE(Graphics g) {   
		if(!enfonce_IA1_DIFFICILE) {
	            g.drawImage(textures.boutonDifficile, posX_IA1_DIFFICILE, posY_IA1_DIFFICILE, largeur_bouton3, hauteur_bouton3, null);
		}else {
	            g.drawImage(textures.boutonDifficile_vert, posX_IA1_DIFFICILE, posY_IA1_DIFFICILE, largeur_bouton3, hauteur_bouton3, null);
		}
    }
    
    public void afficheBoutonIA2_FACILE(Graphics g) {   
		if(!enfonce_IA2_FACILE) {
	            g.drawImage(textures.boutonFacile, posX_IA2_FACILE, posY_IA2_FACILE, largeur_bouton3, hauteur_bouton3, null);
		}else {
	            g.drawImage(textures.boutonFacile_vert, posX_IA2_FACILE, posY_IA2_FACILE, largeur_bouton3, hauteur_bouton3, null);
		}
    }
    
    public void afficheBoutonIA2_MOYEN(Graphics g) {   
		if(!enfonce_IA2_MOYEN) {
	            g.drawImage(textures.boutonMoyen, posX_IA2_MOYEN, posY_IA2_MOYEN, largeur_bouton3, hauteur_bouton3, null);
		}else {
	            g.drawImage(textures.boutonMoyen_vert, posX_IA2_MOYEN, posY_IA2_MOYEN, largeur_bouton3, hauteur_bouton3, null);
		}
    }
    
    public void afficheBoutonIA2_DIFFICILE(Graphics g) {   
		if(!enfonce_IA2_DIFFICILE) {
	            g.drawImage(textures.boutonDifficile, posX_IA2_DIFFICILE, posY_IA2_DIFFICILE, largeur_bouton3, hauteur_bouton3, null);
		}else {
	            g.drawImage(textures.boutonDifficile_vert, posX_IA2_DIFFICILE, posY_IA2_DIFFICILE, largeur_bouton3, hauteur_bouton3, null);
		}
    }
    
    public void afficheBoutonIA3_FACILE(Graphics g) {   
		if(!enfonce_IA3_FACILE) {
	            g.drawImage(textures.boutonFacile, posX_IA3_FACILE, posY_IA3_FACILE, largeur_bouton3, hauteur_bouton3, null);
		}else {
	            g.drawImage(textures.boutonFacile_vert, posX_IA3_FACILE, posY_IA3_FACILE, largeur_bouton3, hauteur_bouton3, null);
		}
    }
    
    public void afficheBoutonIA3_MOYEN(Graphics g) {   
		if(!enfonce_IA3_MOYEN) {
	            g.drawImage(textures.boutonMoyen, posX_IA3_MOYEN, posY_IA3_MOYEN, largeur_bouton3, hauteur_bouton3, null);
		}else {
	            g.drawImage(textures.boutonMoyen_vert, posX_IA3_MOYEN, posY_IA3_MOYEN, largeur_bouton3, hauteur_bouton3, null);
		}
    }
    
    public void afficheBoutonIA3_DIFFICILE(Graphics g) {   
		if(!enfonce_IA3_DIFFICILE) {
	            g.drawImage(textures.boutonDifficile, posX_IA3_DIFFICILE, posY_IA3_DIFFICILE, largeur_bouton3, hauteur_bouton3, null);
		}else {
	            g.drawImage(textures.boutonDifficile_vert, posX_IA3_DIFFICILE, posY_IA3_DIFFICILE, largeur_bouton3, hauteur_bouton3, null);
		}
    }
    
    public void afficheLabel_NOMJ1(Graphics g) {   
    	g.drawImage(textures.boutonNomJ1, posX_label_nomJ1, posY_label_nomJ1, largeur_bouton1, hauteur_bouton1, null);
    }
    
    public void afficheLabel_NOMJ2(Graphics g) {   
    	g.drawImage(textures.boutonNomJ2, posX_label_nomJ2, posY_label_nomJ2, largeur_bouton1, hauteur_bouton1, null);
    }
    
    public void afficheLabel_NOMJ3(Graphics g) {   
    	g.drawImage(textures.boutonNomJoueur, posX_label_nomJ3, posY_label_nomJ3, largeur_bouton1, hauteur_bouton1, null);
    }
    
    public void afficheLabel_IA1(Graphics g) {   
    	g.drawImage(textures.boutonDifficulteOrdi, posX_Label_diff_IA1, posY_Label_diff_IA1, largeur_bouton1, hauteur_bouton1, null);
    }
    
    public void afficheLabel_IA2(Graphics g) {   
    	g.drawImage(textures.boutonDifficulteOrdi1, posX_Label_diff_IA2, posY_Label_diff_IA2, largeur_bouton1, hauteur_bouton1, null);
    }
    
    public void afficheLabel_IA3(Graphics g) {   
    	g.drawImage(textures.boutonDifficulteOrdi2, posX_Label_diff_IA3, posY_Label_diff_IA3, largeur_bouton1, hauteur_bouton1, null);
    }
    
    public void afficheBoutonCOMMENCER(Graphics g) {   
	if(!enfonce_pb_COMMENCER) {
            g.drawImage(textures.boutonCommencer, posX_COMMENCER, posY_COMMENCER, largeur_bouton1, hauteur_bouton1, null);
	}else {
            g.drawImage(textures.boutonCommencer_presse, posX_COMMENCER, posY_COMMENCER, largeur_bouton1, hauteur_bouton1, null);
	}
    }
    
    @Override
    public void paint(Graphics g) {
        if(tailleFenetre != window.getSize()) {
			//on detecte un changement de fenetre -> on met a jour L IHM
			changementTaillefenetre();
		}
        affichageBackGround(g);
        afficheBoutonPVP(g);
        afficheBoutonPVM(g);
        afficheBoutonMVM(g);
        afficheBoutonIA1_FACILE(g);
        afficheBoutonIA1_MOYEN(g);
        afficheBoutonIA1_DIFFICILE(g);
        afficheBoutonIA2_FACILE(g);
        afficheBoutonIA2_MOYEN(g);
        afficheBoutonIA2_DIFFICILE(g);
        afficheBoutonIA3_FACILE(g);
        afficheBoutonIA3_MOYEN(g);
        afficheBoutonIA3_DIFFICILE(g);
        afficheLabel_NOMJ1(g);
        afficheLabel_NOMJ2(g);
        afficheLabel_NOMJ3(g);
        afficheLabel_IA1(g);
        afficheLabel_IA2(g);
        afficheLabel_IA3(g);
        afficheBoutonCOMMENCER(g);
	}
}
