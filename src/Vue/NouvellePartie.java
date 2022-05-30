/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vue;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Modeles.InitPartie;
import Modeles.OptionsJeu;
import Modeles.SoundPlayer;
import Vue.TexturePack.LoadTexture;

public class NouvellePartie extends PanelGeneral {
    //PARAMETRES JEU
    private final JFrame window;
    public Dimension tailleFenetre;
    public JTextField nomJ1, nomJ2, nomJ3;
    
    //AFFICHAGE FIXE
    public int posX_label_nomJ1, posX_label_nomJ2, posX_label_nomJ3, posX_Label_diff_IA1, posX_Label_diff_IA2, posX_Label_diff_IA3;
    public int posX_PVP, posX_PVM, posX_MVM, posX_IA1_FACILE, posX_IA1_MOYEN, posX_IA1_DIFFICILE, posX_IA2_FACILE, posX_IA2_MOYEN, posX_IA2_DIFFICILE, posX_IA3_FACILE, posX_IA3_MOYEN, posX_IA3_DIFFICILE, posX_COMMENCER;
    public int posY_label_nomJ1, posY_label_nomJ2, posY_label_nomJ3, posY_Label_diff_IA1, posY_Label_diff_IA2, posY_Label_diff_IA3;
    public int posY_PVP, posY_PVM, posY_MVM, posY_IA1_FACILE, posY_IA1_MOYEN, posY_IA1_DIFFICILE, posY_IA2_FACILE, posY_IA2_MOYEN, posY_IA2_DIFFICILE, posY_IA3_FACILE, posY_IA3_MOYEN, posY_IA3_DIFFICILE, posY_COMMENCER;
    public int posX_back, posY_back, posX_background, posY_background, largeur_background, hauteur_background;
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
    public boolean debut = true;
    public int largeur_bouton1, hauteur_bouton1, largeur_bouton2, hauteur_bouton2, largeur_bouton3, hauteur_bouton3, largeur_back, hauteur_back; 
    public int frameHeight, frameWidth;
    private final Dimension tailleEcran;
    private final int screenWidth;
    private final int screenHeight;
    int espacement_horizontal;
    int espacement_vertical;
    int offset_horizontal, offset_vertical, offset_h2, offset_h3;
    public LoadTexture textures;
    public InitPartie partie;
    private SoundPlayer simpleSoundPlayerSon;
    private OptionsJeu options;
  
    // METHODE NOUVELLE PARTIE
    public NouvellePartie(JFrame w, LoadTexture t, InitPartie p, OptionsJeu o){
    	super(w, t, o);
        this.window=w;
        this.textures=t;
        this.partie=p;
        this.options=o;
        this.simpleSoundPlayerSon = new SoundPlayer(options.volumeEffetsSonores, textures.CHEMIN);
        window.setTitle("Nouvelle Partie");
        this.tailleEcran = Toolkit.getDefaultToolkit().getScreenSize();
        this.screenWidth=tailleEcran.width;
        this.screenHeight=tailleEcran.height;
       	this.tailleFenetre=window.getSize();
        this.frameWidth=tailleFenetre.width;
        this.frameHeight=tailleFenetre.height;
        addMouseListener(new NouvellePartieClics(this));
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
		setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
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
	    g.drawImage(textures.backgroundSansLogo, posX_background, posY_background, largeur_background, hauteur_background, null);
    }
    
    public void changementTaillefenetre() {
		this.tailleFenetre=window.getSize();
		this.frameWidth=window.getWidth();
        this.frameHeight=window.getHeight();
        double rapport1=0.1813304721030043;//rapport de 169/932
        double rapport2=0.2203389830508475;//rapport de 169/767
        double rapport3=0.4183168316831683;//rapport de 169/404
        double rapport4=0.8535353535353535;//rapport de 169/198
       
        this.largeur_bouton1=Math.min(largeur_background/6, frameWidth/4);
        this.hauteur_bouton1=(int)(largeur_bouton1*rapport1);
        int espacement1 = (int)(largeur_bouton1/4.5);
        int espacement2 = hauteur_bouton1*2;
        
        this.largeur_bouton2=Math.min(largeur_background/4, frameWidth/4);
        this.hauteur_bouton2=(int)(largeur_bouton2*rapport2);
        
        this.largeur_bouton3=Math.min(largeur_background/13, frameWidth/13);
        this.hauteur_bouton3=(int)(largeur_bouton3*rapport3);
        
        this.largeur_back=Math.min(largeur_background/15, frameWidth/15);
        this.hauteur_back=(int)(largeur_back*rapport4);
        
        offset_horizontal = largeur_background/24;
        offset_vertical = hauteur_background/24;
    
        this.offset_h2 = this.offset_horizontal + (this.largeur_bouton1 - this.largeur_bouton2 )/2;
        this.offset_h3 = this.offset_h2 +  (this.largeur_bouton2 - this.largeur_bouton3)/2;
        
        //Position boutons modes
        this.posX_PVM = frameWidth/2-largeur_bouton1/2;
        this.posY_PVM = posY_background+offset_vertical;
        this.posX_PVP = posX_PVM-espacement1-largeur_bouton1;
        this.posY_PVP = posY_background+offset_vertical;
        this.posX_MVM = posX_PVM+espacement1+largeur_bouton1;
        this.posY_MVM = posY_background+offset_vertical;

        //Position bouton label
        //nom des joueurs
        this.posX_label_nomJ1 = posX_PVP;
        this.posY_label_nomJ1 = posY_PVP+espacement2;
        this.posX_label_nomJ2 = posX_label_nomJ1;
        this.posY_label_nomJ2 = posY_label_nomJ1+espacement2*3;
        this.posX_label_nomJ3 = posX_PVM;
        this.posY_label_nomJ3 = posY_label_nomJ1;
        
        //IA1 et ses difficultes
        this.posX_Label_diff_IA1 = posX_PVM;
        this.posY_Label_diff_IA1 = posY_label_nomJ2;
        this.posX_IA1_FACILE = posX_PVM+largeur_bouton1/2-largeur_bouton3/2;
        this.posY_IA1_FACILE = posY_Label_diff_IA1+espacement1;
        this.posX_IA1_MOYEN = posX_IA1_FACILE;
        this.posY_IA1_MOYEN = posY_IA1_FACILE+espacement1;
        this.posX_IA1_DIFFICILE = posX_IA1_MOYEN;
        this.posY_IA1_DIFFICILE = posY_IA1_MOYEN+espacement1;
        
        //IA2 et ses difficultes
        this.posX_Label_diff_IA2 = posX_MVM;
        this.posY_Label_diff_IA2 = posY_label_nomJ1;
        this.posX_IA2_FACILE = posX_MVM+largeur_bouton1/2-largeur_bouton3/2;
        this.posY_IA2_FACILE = posY_Label_diff_IA2+espacement1 ;
        this.posX_IA2_MOYEN = posX_IA2_FACILE;
        this.posY_IA2_MOYEN = posY_IA2_FACILE+espacement1;
        this.posX_IA2_DIFFICILE = posX_IA2_MOYEN;
        this.posY_IA2_DIFFICILE = posY_IA2_MOYEN+espacement1;
        
        //IA3 et se difficultes
        this.posX_Label_diff_IA3 = posX_MVM;
        this.posY_Label_diff_IA3 = posY_label_nomJ2 ;
        this.posX_IA3_FACILE = posX_MVM+largeur_bouton1/2-largeur_bouton3/2;
        this.posY_IA3_FACILE = posY_IA1_FACILE;
        this.posX_IA3_MOYEN = posX_IA3_FACILE;
        this.posY_IA3_MOYEN = posY_IA1_MOYEN;
        this.posX_IA3_DIFFICILE = posX_IA3_MOYEN;
        this.posY_IA3_DIFFICILE = posY_IA1_DIFFICILE;
        
        //Position bouton commencer       
        this.posX_COMMENCER = posX_PVM;
        this.posY_COMMENCER = posY_IA1_DIFFICILE+espacement1*2 ;
        
        //Position boutons retour et suivant
        this.posX_back = posX_PVP+largeur_bouton1/2+largeur_back/2;
        this.posY_back = posY_COMMENCER-hauteur_bouton1/2;
    }
    
    public void jouerSonClic() {
		this.simpleSoundPlayerSon.setNumSon(17);
		this.simpleSoundPlayerSon.jouerSon();
	}
    
    public void jouerSonLancement() {
		this.simpleSoundPlayerSon.setNumSon(29);
		this.simpleSoundPlayerSon.jouerSon();
	}
    
    public void afficheBoutonBack(Graphics g) {
		g.drawImage(textures.menuRetour, posX_back, posY_back, largeur_back, hauteur_back, null);
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
    
    public void afficheBoutonIA1_FACILE(Graphics g) {// joueur vs ordi   
		if(!enfonce_IA1_FACILE) {
			if(enfonce_pb_PVP || enfonce_pb_MVM || debut) {
	            g.drawImage(textures.boutonFacile_gris, posX_IA1_FACILE, posY_IA1_FACILE, largeur_bouton3, hauteur_bouton3, null);
			}else {
				g.drawImage(textures.boutonFacile, posX_IA1_FACILE, posY_IA1_FACILE, largeur_bouton3, hauteur_bouton3, null);
			}
		}else {
            g.drawImage(textures.boutonFacile_vert, posX_IA1_FACILE, posY_IA1_FACILE, largeur_bouton3, hauteur_bouton3, null);
		}
    }
    
    public void afficheBoutonIA1_MOYEN(Graphics g) {// joueur vs ordi   
		if(!enfonce_IA1_MOYEN) {
			if(enfonce_pb_PVP || enfonce_pb_MVM || debut) {
	            g.drawImage(textures.boutonMoyen_gris, posX_IA1_MOYEN, posY_IA1_MOYEN, largeur_bouton3, hauteur_bouton3, null);
			}else {
				g.drawImage(textures.boutonMoyen, posX_IA1_MOYEN, posY_IA1_MOYEN, largeur_bouton3, hauteur_bouton3, null);
			}
		}else {
            g.drawImage(textures.boutonMoyen_vert, posX_IA1_MOYEN, posY_IA1_MOYEN, largeur_bouton3, hauteur_bouton3, null);
		}
    }
    
    public void afficheBoutonIA1_DIFFICILE(Graphics g) {// joueur vs ordi   
		if(!enfonce_IA1_DIFFICILE) {
			if(enfonce_pb_PVP || enfonce_pb_MVM || debut) {
	            g.drawImage(textures.boutonDifficile_gris, posX_IA1_DIFFICILE, posY_IA1_DIFFICILE, largeur_bouton3, hauteur_bouton3, null);
			}else {
				 g.drawImage(textures.boutonDifficile, posX_IA1_DIFFICILE, posY_IA1_DIFFICILE, largeur_bouton3, hauteur_bouton3, null);
			}
		}else {
            g.drawImage(textures.boutonDifficile_vert, posX_IA1_DIFFICILE, posY_IA1_DIFFICILE, largeur_bouton3, hauteur_bouton3, null);
		}
    }
    
    public void afficheBoutonIA2_FACILE(Graphics g) {// ordi vs ordi
		if(!enfonce_IA2_FACILE) {
			if(enfonce_pb_PVP || enfonce_pb_PVM || debut) {
	            g.drawImage(textures.boutonFacile_gris, posX_IA2_FACILE, posY_IA2_FACILE, largeur_bouton3, hauteur_bouton3, null);
			}else {
				g.drawImage(textures.boutonFacile, posX_IA2_FACILE, posY_IA2_FACILE, largeur_bouton3, hauteur_bouton3, null);
			}
		}else {
            g.drawImage(textures.boutonFacile_vert, posX_IA2_FACILE, posY_IA2_FACILE, largeur_bouton3, hauteur_bouton3, null);
		}
    }
    
    public void afficheBoutonIA2_MOYEN(Graphics g) {// ordi vs ordi
		if(!enfonce_IA2_MOYEN) {
			if(enfonce_pb_PVP || enfonce_pb_PVM || debut) {
	            g.drawImage(textures.boutonMoyen_gris, posX_IA2_MOYEN, posY_IA2_MOYEN, largeur_bouton3, hauteur_bouton3, null);
			}else {
				g.drawImage(textures.boutonMoyen, posX_IA2_MOYEN, posY_IA2_MOYEN, largeur_bouton3, hauteur_bouton3, null);
			}
		}else {
            g.drawImage(textures.boutonMoyen_vert, posX_IA2_MOYEN, posY_IA2_MOYEN, largeur_bouton3, hauteur_bouton3, null);
		}
    }
    
    public void afficheBoutonIA2_DIFFICILE(Graphics g) {// ordi vs ordi
		if(!enfonce_IA2_DIFFICILE) {
			if(enfonce_pb_PVP || enfonce_pb_PVM || debut) {
	            g.drawImage(textures.boutonDifficile_gris, posX_IA2_DIFFICILE, posY_IA2_DIFFICILE, largeur_bouton3, hauteur_bouton3, null);
			}else {
				g.drawImage(textures.boutonDifficile, posX_IA2_DIFFICILE, posY_IA2_DIFFICILE, largeur_bouton3, hauteur_bouton3, null);
			}
		}else {
            g.drawImage(textures.boutonDifficile_vert, posX_IA2_DIFFICILE, posY_IA2_DIFFICILE, largeur_bouton3, hauteur_bouton3, null);
		}
    }
    
    public void afficheBoutonIA3_FACILE(Graphics g) {// ordi vs ordi
		if(!enfonce_IA3_FACILE) {
			if(enfonce_pb_PVP || enfonce_pb_PVM || debut) {
	            g.drawImage(textures.boutonFacile_gris, posX_IA3_FACILE, posY_IA3_FACILE, largeur_bouton3, hauteur_bouton3, null);
			}else {
				g.drawImage(textures.boutonFacile, posX_IA3_FACILE, posY_IA3_FACILE, largeur_bouton3, hauteur_bouton3, null);
			}
		}else {
            g.drawImage(textures.boutonFacile_vert, posX_IA3_FACILE, posY_IA3_FACILE, largeur_bouton3, hauteur_bouton3, null);
		}
    }
    
    public void afficheBoutonIA3_MOYEN(Graphics g) {// ordi vs ordi
		if(!enfonce_IA3_MOYEN) {
			if(enfonce_pb_PVP || enfonce_pb_PVM || debut) {
	            g.drawImage(textures.boutonMoyen_gris, posX_IA3_MOYEN, posY_IA3_MOYEN, largeur_bouton3, hauteur_bouton3, null);
			}else {
				g.drawImage(textures.boutonMoyen, posX_IA3_MOYEN, posY_IA3_MOYEN, largeur_bouton3, hauteur_bouton3, null);
			}
		}else {
            g.drawImage(textures.boutonMoyen_vert, posX_IA3_MOYEN, posY_IA3_MOYEN, largeur_bouton3, hauteur_bouton3, null);
		}
    }
    
    public void afficheBoutonIA3_DIFFICILE(Graphics g) {// ordi vs ordi
		if(!enfonce_IA3_DIFFICILE) {
			if(enfonce_pb_PVP || enfonce_pb_PVM || debut) {
	            g.drawImage(textures.boutonDifficile_gris, posX_IA3_DIFFICILE, posY_IA3_DIFFICILE, largeur_bouton3, hauteur_bouton3, null);
			}else {
				g.drawImage(textures.boutonDifficile, posX_IA3_DIFFICILE, posY_IA3_DIFFICILE, largeur_bouton3, hauteur_bouton3, null);
			}
		}else {
            g.drawImage(textures.boutonDifficile_vert, posX_IA3_DIFFICILE, posY_IA3_DIFFICILE, largeur_bouton3, hauteur_bouton3, null);
		}
    }
    
    public void afficheLabel_NOMJ1(Graphics g) {// PVP
    	if(enfonce_pb_PVM || enfonce_pb_MVM || debut) {
    		g.drawImage(textures.boutonNomJ1_gris, posX_label_nomJ1, posY_label_nomJ1, largeur_bouton1, hauteur_bouton1, null);
    	}else {
    		g.drawImage(textures.boutonNomJ1, posX_label_nomJ1, posY_label_nomJ1, largeur_bouton1, hauteur_bouton1, null);
    	}
    }
    
    public void afficheLabel_NOMJ2(Graphics g) {// PVP
    	if(enfonce_pb_PVM || enfonce_pb_MVM || debut) {
    		g.drawImage(textures.boutonNomJ2_gris, posX_label_nomJ2, posY_label_nomJ2, largeur_bouton1, hauteur_bouton1, null);
    	}else {
    		g.drawImage(textures.boutonNomJ2, posX_label_nomJ2, posY_label_nomJ2, largeur_bouton1, hauteur_bouton1, null);
    	}
    }
    
    public void afficheLabel_NOMJ3(Graphics g) {// PVM
    	if(enfonce_pb_PVP || enfonce_pb_MVM || debut) {
    		g.drawImage(textures.boutonNomJoueur_gris, posX_label_nomJ3, posY_label_nomJ3, largeur_bouton1, hauteur_bouton1, null);
    	}else {
    		g.drawImage(textures.boutonNomJoueur, posX_label_nomJ3, posY_label_nomJ3, largeur_bouton1, hauteur_bouton1, null);
    	}
    }
    
    public void afficheLabel_IA1(Graphics g) {// joueur vs ordi
    	if(enfonce_pb_PVP || enfonce_pb_MVM || debut) {
    		g.drawImage(textures.boutonDifficulteOrdi_gris, posX_Label_diff_IA1, posY_Label_diff_IA1, largeur_bouton1, hauteur_bouton1, null);
    	}else {
    		g.drawImage(textures.boutonDifficulteOrdi, posX_Label_diff_IA1, posY_Label_diff_IA1, largeur_bouton1, hauteur_bouton1, null);
    	}
    }
    
    public void afficheLabel_IA2(Graphics g) {// ordi vs ordi
    	if(enfonce_pb_PVP || enfonce_pb_PVM || debut) {
    		g.drawImage(textures.boutonDifficulteOrdi1_gris, posX_Label_diff_IA2, posY_Label_diff_IA2, largeur_bouton1, hauteur_bouton1, null);
    	}else {
    		g.drawImage(textures.boutonDifficulteOrdi1, posX_Label_diff_IA2, posY_Label_diff_IA2, largeur_bouton1, hauteur_bouton1, null);
    	}
    }
    
    public void afficheLabel_IA3(Graphics g) {// ordi vs ordi
    	if(enfonce_pb_PVP || enfonce_pb_PVM || debut) {
    		g.drawImage(textures.boutonDifficulteOrdi2_gris, posX_Label_diff_IA3, posY_Label_diff_IA3, largeur_bouton1, hauteur_bouton1, null);
    	}else {
    		g.drawImage(textures.boutonDifficulteOrdi2, posX_Label_diff_IA3, posY_Label_diff_IA3, largeur_bouton1, hauteur_bouton1, null);
    	}
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
        affichageBackGround(g,1);
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
        afficheBoutonBack(g);
	}
}
