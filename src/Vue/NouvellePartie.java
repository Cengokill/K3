/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vue;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
/**
 *
 * @author Océanne
 */
public class NouvellePartie extends JPanel {
    
    //PARAMETRE JEU
    private final JFrame window;
    public Dimension tailleFenetre;
    public JTextField nomJ1, nomJ2, nomJ3;
    
    //AFFICHAGE FIXE
    public int posX_label_nomJ1, posX_label_nomJ2, posX_label_nomJ3, posX_Label_diff_IA1, posX_Label_diff_IA2, posX_Label_diff_IA3;
    public int posX_bp_PVP, posX_bp_PVM, posX_bp_MVM, posX_bp_IA1_FACILE, posX_bp_IA1_MOYEN, posX_bp_IA1_DIFFICILE, posX_bp_IA2_FACILE, posX_bp_IA2_MOYEN, posX_bp_IA2_DIFFICILE, posX_bp_IA3_FACILE, posX_bp_IA3_MOYEN, posX_bp_IA3_DIFFICILE, posX_bp_COMMENCER;
    public int posY_label_nomJ1, posY_label_nomJ2, posY_label_nomJ3, posY_Label_diff_IA1, posY_Label_diff_IA2, posY_Label_diff_IA3;
    public int posY_bp_PVP, posY_bp_PVM, posY_bp_MVM, posY_bp_IA1_FACILE, posY_bp_IA1_MOYEN, posY_bp_IA1_DIFFICILE, posY_bp_IA2_FACILE, posY_bp_IA2_MOYEN, posY_bp_IA2_DIFFICILE, posY_bp_IA3_FACILE, posY_bp_IA3_MOYEN, posY_bp_IA3_DIFFICILE, posY_bp_COMMENCER;
    
    public boolean enfonce_pb_PVP = false;
    public boolean enfonce_pb_PVM = false;
    public boolean enfonce_pb_MVM = false;
    public boolean enfonce_bp_IA1_FACILE = false;
    public boolean enfonce_bp_IA1_MOYEN = false;
    public boolean enfonce_bp_IA1_DIFFICILE = false;
    public boolean enfonce_bp_IA2_FACILE = false;
    public boolean enfonce_bp_IA2_MOYEN = false;
    public boolean enfonce_bp_IA2_DIFFICILE = false;
    public boolean enfonce_bp_IA3_FACILE = false;
    public boolean enfonce_bp_IA3_MOYEN = false;
    public boolean enfonce_bp_IA3_DIFFICILE = false;
    public boolean enfonce_pb_COMMENCER = false;
    public int largeur_bouton, hauteur_bouton, largeur_bouton2, largeur_bouton3;
    
    //COMPOSANTS IMPORTES    
    public final String CHEMIN = "C:/Users/Océanne/Documents/NetBeansProjects/K3/src/ressources/";
    public final String NOMBACKGROUND = "background.jpg";
    public Image background = Toolkit.getDefaultToolkit().createImage(CHEMIN+NOMBACKGROUND);
    
    //BOUTONS NON ENFONCES
    public Image Label_J1 = Toolkit.getDefaultToolkit().createImage(CHEMIN+"chargerPartie.png");
    public Image Label_J2 = Toolkit.getDefaultToolkit().createImage(CHEMIN+"chargerPartie.png");
    public Image Label_J3 = Toolkit.getDefaultToolkit().createImage(CHEMIN+"nouvellePartie.png");
    public Image Label_diff_IA1 = Toolkit.getDefaultToolkit().createImage(CHEMIN+"chargerPartie.png");
    public Image Label_diff_IA2 = Toolkit.getDefaultToolkit().createImage(CHEMIN+"chargerPartie.png");
    public Image Label_diff_IA3 = Toolkit.getDefaultToolkit().createImage(CHEMIN+"chargerPartie.png");
    
    public Image bp_PVP = Toolkit.getDefaultToolkit().createImage(CHEMIN + "chargerPartie.png");
    public Image bp_PVM = Toolkit.getDefaultToolkit().createImage(CHEMIN+"chargerPartie.png");
    public Image bp_MVM = Toolkit.getDefaultToolkit().createImage(CHEMIN+"chargerPartie.png");
    public Image bp_IA1_FACILE = Toolkit.getDefaultToolkit().createImage(CHEMIN+"chargerPartie.png");
    public Image bp_IA1_MOYEN = Toolkit.getDefaultToolkit().createImage(CHEMIN+"chargerPartie.png");
    public Image bp_IA1_DIFFICILE = Toolkit.getDefaultToolkit().createImage(CHEMIN+"chargerPartie.png");
    public Image bp_IA2_FACILE = Toolkit.getDefaultToolkit().createImage(CHEMIN+"chargerPartie.png");
    public Image bp_IA2_MOYEN = Toolkit.getDefaultToolkit().createImage(CHEMIN+"chargerPartie.png");
    public Image bp_IA2_DIFFICILE = Toolkit.getDefaultToolkit().createImage(CHEMIN+"chargerPartie.png");
    public Image bp_IA3_FACILE = Toolkit.getDefaultToolkit().createImage(CHEMIN+"chargerPartie.png");
    public Image bp_IA3_MOYEN = Toolkit.getDefaultToolkit().createImage(CHEMIN+"chargerPartie.png");
    public Image bp_IA3_DIFFICILE = Toolkit.getDefaultToolkit().createImage(CHEMIN+"chargerPartie.png");
    public Image bp_COMMENCER = Toolkit.getDefaultToolkit().createImage(CHEMIN+"chargerPartie.png");

    
        //BOUTONS ENFONCES
    

    public Image bp_PVP_ENFONCE = Toolkit.getDefaultToolkit().createImage(CHEMIN+"nouvellePartie.png");
    
    public Image bp_PVM_ENFONCE = Toolkit.getDefaultToolkit().createImage(CHEMIN+"nouvellePartie.png");
    public Image bp_MVM_ENFONCE = Toolkit.getDefaultToolkit().createImage(CHEMIN+"nouvellePartie.png");
   
    public Image bp_IA1_FACILE_ENFONCE = Toolkit.getDefaultToolkit().createImage(CHEMIN+"nouvellePartie.png");
    public Image bp_IA1_MOYEN_ENFONCE = Toolkit.getDefaultToolkit().createImage(CHEMIN+"nouvellePartie.png");
    public Image bp_IA1_DIFFICILE_ENFONCE = Toolkit.getDefaultToolkit().createImage(CHEMIN+"nouvellePartie.png");
    public Image bp_IA2_FACILE_ENFONCE = Toolkit.getDefaultToolkit().createImage(CHEMIN+"nouvellePartie.png");
    public Image bp_IA2_MOYEN_ENFONCE = Toolkit.getDefaultToolkit().createImage(CHEMIN+"nouvellePartie.png");
    public Image bp_IA2_DIFFICILE_ENFONCE = Toolkit.getDefaultToolkit().createImage(CHEMIN+"nouvellePartie.png");
    public Image bp_IA3_FACILE_ENFONCE = Toolkit.getDefaultToolkit().createImage(CHEMIN+"nouvellePartie.png");
    public Image bp_IA3_MOYEN_ENFONCE = Toolkit.getDefaultToolkit().createImage(CHEMIN+"nouvellePartie.png");
    public Image bp_IA3_DIFFICILE_ENFONCE = Toolkit.getDefaultToolkit().createImage(CHEMIN+"nouvellePartie.png");
    public Image bp_COMMENCER_ENFONCE = Toolkit.getDefaultToolkit().createImage(CHEMIN+"nouvellePartie.png");
    
    
    public int frameHeight, frameWidth;
    private final  Dimension tailleEcran;
    private final  int screenWidth;
    private final  int screenHeight;
    private final  int centreX_Screen;
    private final  int centreY_Screen;
    private  int centreX_Window;
    private  int centreY_Window;
    int espacement_horizontal;
    int espacement_vertical;
    int offset_horizontal, offset_vertical, offset_h2, offset_h3;
    
    
    
    // METHODE NOUVELLE PARTIE
    public NouvellePartie(JFrame w){
        window = w;
        window.setTitle("Nouvelle Partie");
        this.tailleEcran = Toolkit.getDefaultToolkit().getScreenSize();
        this.screenWidth=tailleEcran.width;
        this.screenHeight=tailleEcran.height;
       	this.tailleFenetre=window.getSize();

        this.frameWidth=screenWidth;
        this.frameHeight=screenHeight;
        this.centreX_Screen=screenWidth/2;
        this.centreY_Screen=screenHeight/2;
        this.centreX_Window=centreX_Screen-frameWidth/2;
        this.centreY_Window=centreY_Screen-frameHeight/2;
        window.setLocation(centreX_Window, centreY_Window);
	window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	window.setVisible(true);
        
        
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
        offset_horizontal = frameWidth/24;
        offset_vertical = (((frameHeight / 10) - hauteur_bouton)/2);
        this.largeur_bouton=(int)(6 * frameWidth / 24);
        this.hauteur_bouton=(int)(largeur_bouton*0.16);//rapport de 155/814
        this.largeur_bouton2 = 4 * frameWidth / 18;
        this.offset_h2 = this.offset_horizontal + (this.largeur_bouton - this.largeur_bouton2 )/2;
        this.largeur_bouton3 = 3 * frameWidth / 15;
        this.offset_h3 = this.offset_h2 +  (this.largeur_bouton2 - this.largeur_bouton3)/2;
        
        //Position bouton mode
        this.posX_bp_PVP = (int)offset_horizontal;
        this.posY_bp_PVP = offset_vertical;
        this.posX_bp_PVM = (int)(frameWidth / 3) + (int) offset_horizontal;
        this.posY_bp_PVM = posY_bp_PVP;
        this.posX_bp_MVM = (int)(2 * frameWidth / 3) + (int) offset_horizontal;
        this.posY_bp_MVM = posY_bp_PVP;
                
        //Position bouton label
        //nom des joueurs
        this.posX_label_nomJ1 = offset_h2;
        this.posY_label_nomJ1 = frameHeight / 10 + offset_vertical;
        this.posX_label_nomJ2 = offset_h2;
        this.posY_label_nomJ2 = 5*frameHeight / 10;
        this.posX_label_nomJ3 = offset_h2 + frameWidth/3;
        this.posY_label_nomJ3 = frameHeight / 10 + offset_vertical;
        
        //IA1 et ses difficultes
        this.posX_Label_diff_IA1 = offset_h2 + frameWidth / 3;
        this.posY_Label_diff_IA1 = 5*frameHeight / 10 ;
        this.posX_bp_IA1_FACILE = offset_h3 + frameWidth / 3;
        this.posY_bp_IA1_FACILE = 6*frameHeight / 10 - offset_vertical;
        this.posX_bp_IA1_MOYEN = offset_h3 + frameWidth / 3;
        this.posY_bp_IA1_MOYEN = 7*frameHeight / 10 - 5*offset_vertical/2;
        this.posX_bp_IA1_DIFFICILE = offset_h3 + frameWidth / 3;
        this.posY_bp_IA1_DIFFICILE = 8*frameHeight / 10 - 4*offset_vertical;
        
        //IA2 et ses difficultes
        this.posX_Label_diff_IA2 = offset_h2 + 2*frameWidth/3;
        this.posY_Label_diff_IA2 = frameHeight / 10 + offset_vertical;
        this.posX_bp_IA2_FACILE = offset_h3 + 2*frameWidth / 3;
        this.posY_bp_IA2_FACILE = 2*frameHeight / 10 ;
        this.posX_bp_IA2_MOYEN = offset_h3 + 2*frameWidth / 3;
        this.posY_bp_IA2_MOYEN = 3*frameHeight / 10 - 3*offset_vertical/2;
        this.posX_bp_IA2_DIFFICILE = offset_h3 + 2*frameWidth / 3;
        this.posY_bp_IA2_DIFFICILE = 4*frameHeight / 10 - 3*offset_vertical;
        
        //IA3 et se difficultes
        this.posX_Label_diff_IA3 = offset_h2 + 2*frameWidth/3;
        this.posY_Label_diff_IA3 = 5*frameHeight / 10 ;
        this.posX_bp_IA3_FACILE = offset_h3 + 2*frameWidth / 3;
        this.posY_bp_IA3_FACILE = 6*frameHeight / 10 - offset_vertical;
        this.posX_bp_IA3_MOYEN = offset_h3 + 2*frameWidth / 3;
        this.posY_bp_IA3_MOYEN = 7*frameHeight / 10 - 5*offset_vertical/2;
        this.posX_bp_IA3_DIFFICILE = offset_h3 + 2*frameWidth / 3;
        this.posY_bp_IA3_DIFFICILE = 8*frameHeight / 10 - 4*offset_vertical;
        
        //Position bouton commencer       
        this.posX_bp_COMMENCER = offset_horizontal + frameWidth / 3;
        this.posY_bp_COMMENCER = 8 * frameHeight / 10 + 7 *offset_vertical /8 ;
    }
    
    public void afficheBoutonPVP(Graphics g) {   
	if(!enfonce_pb_PVP) {
            g.drawImage(bp_PVP, posX_bp_PVP, posY_bp_PVP, largeur_bouton, hauteur_bouton, null);
	}else {
            g.drawImage(bp_PVP_ENFONCE, posX_bp_PVP, posY_bp_PVP, largeur_bouton, hauteur_bouton, null);
	}
    }
    
    public void afficheBoutonPVM(Graphics g) {   
	if(!enfonce_pb_PVM) {
            g.drawImage(bp_PVM, posX_bp_PVM, posY_bp_PVM, largeur_bouton, hauteur_bouton, null);
	}else {
            g.drawImage(bp_PVM_ENFONCE, posX_bp_PVM, posY_bp_PVM, largeur_bouton, hauteur_bouton, null);
	}
    }
    
    public void afficheBoutonMVM(Graphics g) {   
	if(!enfonce_pb_MVM) {
            g.drawImage(bp_MVM, posX_bp_MVM, posY_bp_MVM, largeur_bouton, hauteur_bouton, null);
	}else {
            g.drawImage(bp_MVM_ENFONCE, posX_bp_MVM, posY_bp_MVM, largeur_bouton, hauteur_bouton, null);
	}
    }
    
    public void afficheBoutonIA1_FACILE(Graphics g) {   
	if(!enfonce_bp_IA1_FACILE) {
            g.drawImage(bp_IA1_FACILE, posX_bp_IA1_FACILE, posY_bp_IA1_FACILE, largeur_bouton3, hauteur_bouton, null);
	}else {
            g.drawImage(bp_IA1_FACILE_ENFONCE, posX_bp_IA1_FACILE, posY_bp_IA1_FACILE, largeur_bouton3, hauteur_bouton, null);
	}
    }
    
    public void afficheBoutonIA1_MOYEN(Graphics g) {   
	if(!enfonce_bp_IA1_MOYEN) {
            g.drawImage(bp_IA1_MOYEN, posX_bp_IA1_MOYEN, posY_bp_IA1_MOYEN, largeur_bouton3, hauteur_bouton, null);
	}else {
            g.drawImage(bp_IA1_MOYEN_ENFONCE, posX_bp_IA1_MOYEN, posY_bp_IA1_MOYEN, largeur_bouton3, hauteur_bouton, null);
	}
    }
    
    public void afficheBoutonIA1_DIFFICILE(Graphics g) {   
	if(!enfonce_bp_IA1_DIFFICILE) {
            g.drawImage(bp_IA1_DIFFICILE, posX_bp_IA1_DIFFICILE, posY_bp_IA1_DIFFICILE, largeur_bouton3, hauteur_bouton, null);
	}else {
            g.drawImage(bp_IA1_DIFFICILE_ENFONCE, posX_bp_IA1_DIFFICILE, posY_bp_IA1_DIFFICILE, largeur_bouton3, hauteur_bouton, null);
	}
    }
    
    public void afficheBoutonIA2_FACILE(Graphics g) {   
	if(!enfonce_bp_IA2_FACILE) {
            g.drawImage(bp_IA2_FACILE, posX_bp_IA2_FACILE, posY_bp_IA2_FACILE, largeur_bouton3, hauteur_bouton, null);
	}else {
            g.drawImage(bp_IA2_FACILE_ENFONCE, posX_bp_IA2_FACILE, posY_bp_IA2_FACILE, largeur_bouton3, hauteur_bouton, null);
	}
    }
    
    public void afficheBoutonIA2_MOYEN(Graphics g) {   
	if(!enfonce_bp_IA2_MOYEN) {
            g.drawImage(bp_IA2_MOYEN, posX_bp_IA2_MOYEN, posY_bp_IA2_MOYEN, largeur_bouton3, hauteur_bouton, null);
	}else {
            g.drawImage(bp_IA2_MOYEN_ENFONCE, posX_bp_IA2_MOYEN, posY_bp_IA2_MOYEN, largeur_bouton3, hauteur_bouton, null);
	}
    }
    
    public void afficheBoutonIA2_DIFFICILE(Graphics g) {   
	if(!enfonce_bp_IA2_DIFFICILE) {
            g.drawImage(bp_IA2_DIFFICILE, posX_bp_IA2_DIFFICILE, posY_bp_IA2_DIFFICILE, largeur_bouton3, hauteur_bouton, null);
	}else {
            g.drawImage(bp_IA2_DIFFICILE_ENFONCE, posX_bp_IA2_DIFFICILE, posY_bp_IA2_DIFFICILE, largeur_bouton3, hauteur_bouton, null);
	}
    }
    
    public void afficheBoutonIA3_FACILE(Graphics g) {   
	if(!enfonce_bp_IA3_FACILE) {
            g.drawImage(bp_IA3_FACILE, posX_bp_IA3_FACILE, posY_bp_IA3_FACILE, largeur_bouton3, hauteur_bouton, null);
	}else {
            g.drawImage(bp_IA3_FACILE_ENFONCE, posX_bp_IA3_FACILE, posY_bp_IA3_FACILE, largeur_bouton3, hauteur_bouton, null);
	}
    }
    
    public void afficheBoutonIA3_MOYEN(Graphics g) {   
	if(!enfonce_bp_IA3_MOYEN) {
            g.drawImage(bp_IA3_MOYEN, posX_bp_IA3_MOYEN, posY_bp_IA3_MOYEN, largeur_bouton3, hauteur_bouton, null);
	}else {
            g.drawImage(bp_IA3_MOYEN_ENFONCE, posX_bp_IA3_MOYEN, posY_bp_IA3_MOYEN, largeur_bouton3, hauteur_bouton, null);
	}
    }
    
    public void afficheBoutonIA3_DIFFICILE(Graphics g) {   
	if(!enfonce_bp_IA3_DIFFICILE) {
            g.drawImage(bp_IA3_DIFFICILE, posX_bp_IA3_DIFFICILE, posY_bp_IA3_DIFFICILE, largeur_bouton3, hauteur_bouton, null);
	}else {
            g.drawImage(bp_IA3_DIFFICILE_ENFONCE, posX_bp_IA3_DIFFICILE, posY_bp_IA3_DIFFICILE, largeur_bouton3, hauteur_bouton, null);
	}
    }
    
    public void afficheLabel_NOMJ1(Graphics g) {   
	g.drawImage(Label_J1, posX_label_nomJ1, posY_label_nomJ1, largeur_bouton2, hauteur_bouton, null);
    }
    
    public void afficheLabel_NOMJ2(Graphics g) {   
	g.drawImage(Label_J2, posX_label_nomJ2, posY_label_nomJ2, largeur_bouton2, hauteur_bouton, null);
    }
    
    public void afficheLabel_NOMJ3(Graphics g) {   
	g.drawImage(Label_J3, posX_label_nomJ3, posY_label_nomJ3, largeur_bouton2, hauteur_bouton, null);
    }
    
    public void afficheLabel_IA1(Graphics g) {   
	g.drawImage(Label_diff_IA1, posX_Label_diff_IA1, posY_Label_diff_IA1, largeur_bouton2, hauteur_bouton, null);
    }
    
    public void afficheLabel_IA2(Graphics g) {   
	g.drawImage(Label_diff_IA2, posX_Label_diff_IA2, posY_Label_diff_IA2, largeur_bouton2, hauteur_bouton, null);
    }
    
    public void afficheLabel_IA3(Graphics g) {   
	g.drawImage(Label_diff_IA3, posX_Label_diff_IA3, posY_Label_diff_IA3, largeur_bouton2, hauteur_bouton, null);
    }
    
    public void afficheBoutonCOMMENCER(Graphics g) {   
	if(!enfonce_pb_COMMENCER) {
            g.drawImage(bp_COMMENCER, posX_bp_COMMENCER, posY_bp_COMMENCER, largeur_bouton, hauteur_bouton, null);
	}else {
            g.drawImage(bp_COMMENCER_ENFONCE, posX_bp_COMMENCER, posY_bp_COMMENCER, largeur_bouton, hauteur_bouton, null);
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
