/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Vue.Phase2;

import Controleur.Jeu;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import Modeles.Couleurs;
import Modeles.Piece;
import Modeles.Position;
import Vue.TexturePack.LoadTexture;


public class PanelPhase2 extends javax.swing.JPanel {

	public LoadTexture texture;
	public Dimension tailleFenetre;
	public int frameWidth, frameHeight;
	public int largeur_background, hauteur_background, posX_background, posY_background;
	public int posXPasserTour, posYPasserTour, largeurPasserTour, hauteurPasserTour, posXCoupPrecedent;
	public int largeur_ileJ, hauteur_ileJ, posX_ileJ1, posY_ileJ1, posX_ileJ2, posY_ileJ2;
	public int largeur_ileM, hauteur_ileM, posX_ileM, posY_ileM;
	public int largeur_piece, hauteur_piece, posY_depart;
	public int largeur_vol, hauteur_vol, posX_volJ1, poxY_volJ1, posX_volJ2, poxY_volJ2;
    /**
     * Creates new form PanelPhase2
     */
    public PanelPhase2(LoadTexture texture) {
        this.texture = texture;
    	initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setAutoscrolls(true);
        setDoubleBuffered(false);
        setFocusable(false);
        setOpaque(false);
        setPreferredSize(new java.awt.Dimension(779, 699));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 679, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 374, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    @Override
   public void paint(Graphics g) {
       pospion.clear();
       setChangementTaillefenetre();
       drawbackground(g);
       drawIle(g);
       drawbaPyramideJ1(g);
       drawbaPyramideJ2(g);
       drawbaPyramideMilieu(g);
   }
    
    public void setChangementTaillefenetre() {
		this.tailleFenetre=this.getSize();
		this.frameWidth=this.getWidth();
        this.frameHeight=this.getHeight();
 	   double rapport = (double) 0.5625;// rapport de 2160/3840
 		if((double)((double)frameHeight/(double)frameWidth)>rapport) {
 			largeur_background=frameWidth;
 			hauteur_background=(int)(largeur_background*rapport);
 			posX_background=0;
 			posY_background=(frameHeight-hauteur_background)/2;
 		}
 		else {//si largeur fenetre tres grande
 			hauteur_background=frameHeight;
 			largeur_background=(int)(hauteur_background/rapport);
 			posX_background=(frameWidth-largeur_background)/2;
 			posY_background=0;
 		}
 		//Passer son tour
        double rapportPasserTour=0.3001776198934281;// 169/563
		largeurPasserTour=Math.min(largeur_background/6, frameWidth/6);
		hauteurPasserTour=(int)(largeurPasserTour*rapportPasserTour);
		int espacement = largeurPasserTour/4; 
		posXPasserTour=posX_background+largeur_background/2-largeurPasserTour-espacement/2;
		posYPasserTour=posY_background+(int)(hauteur_background*0.8);
		//Coup precedent
		posXCoupPrecedent=posXPasserTour+largeurPasserTour+espacement;
		//Iles joueurs
		double rapportIlesJoueurs=0.9269340974212034;// 647/698
		largeur_ileJ=Math.min((int)(largeur_background/3.6), (int)(frameWidth/3.6));
		hauteur_ileJ=(int)(largeur_ileJ*rapportIlesJoueurs);
		posX_ileJ1=posX_background+(int)(largeur_background*0.02);
		posY_ileJ1=posY_background+(int)(hauteur_background*0.52);
		posX_ileJ2=posX_background+(int)(largeur_background*0.98)-largeur_ileJ;;
		posY_ileJ2=posY_ileJ1;
		posY_depart=posY_ileJ1-(int)(hauteur_ileJ*0.04);
		//Ile montagne
		double rapportIleMontagne=0.7764198418404026;// 1080/1391
		largeur_ileM=Math.min((int)(largeur_background/2.5), (int)(frameWidth/2.5));
		hauteur_ileM=(int)(largeur_ileM*rapportIleMontagne);
		posX_ileM=posX_background+largeur_background/2-largeur_ileM/2;
		posY_ileM=posY_background+hauteur_background-(int)(hauteur_background/1.7);
		//Camp joueur 1
		double rapportPiece=0.8576709796672828;// 464/541
		largeur_piece=Math.min((int)(largeur_background/35), (int)(frameWidth/35));
		hauteur_piece=(int)(largeur_piece*rapportPiece);
		//Vols
		double rapportVol=0.8104738154613466;// 975/1203
		largeur_vol=largeur_piece*6;
		hauteur_vol=(int)(largeur_vol*rapportVol);
		posX_volJ1=posX_ileJ1+largeur_ileJ/2-largeur_vol/2+(int)(largeur_piece*0.4);
		poxY_volJ1=posY_background+hauteur_background/24;
		
		posX_volJ2=posX_ileJ2+largeur_ileJ/2-largeur_vol/2-(int)(largeur_piece*0.2);
		poxY_volJ2=poxY_volJ1;
		
	}
   
   public void drawbackground(Graphics g) {
      g.drawImage(texture.backgroundSansLogo, posX_background, posY_background, largeur_background, hauteur_background, null);
   }
   
   public void drawBoutons(Graphics g) {
      g.drawImage(texture.passerTour, posXPasserTour, posYPasserTour, largeurPasserTour, hauteurPasserTour, null);
      g.drawImage(texture.boutonCoupPrecedent, posXCoupPrecedent, posYPasserTour, largeurPasserTour, hauteurPasserTour, null);
	}
   
   public void drawIle(Graphics g) {        
      g.drawImage(this.texture.ile_joueur1, posX_ileJ1, posY_ileJ1, largeur_ileJ, hauteur_ileJ, null);
      g.drawImage(this.texture.ile_joueur2, posX_ileJ2, posY_ileJ2, largeur_ileJ, hauteur_ileJ, null);
      g.drawImage(this.texture.ile_montagne, posX_ileM, posY_ileM, largeur_ileM, hauteur_ileM, null);
   }
 
   public void drawbaPyramideJ1(Graphics g) {
	   g.drawImage(texture.imagevol,posX_volJ1,poxY_volJ1,largeur_vol,hauteur_vol, null);
	     Position actualpos = new Position(0, 0);
	     int posX_depart = posX_ileJ1+(int)(largeur_ileJ*0.54)-((largeur_piece*6)/2);
	     int posX = posX_depart;
	     int posY = posY_depart;
	     int decalage=0;
	      for (int i = 0; i < this.partie_actuel.partieEnCours.joueur1().getCamp().getHauteur(); i++) { // etage
	        for (int j = 0; j < (this.partie_actuel.partieEnCours.joueur1().getCamp().getLargeur() - i); j++) { // rang
	          actualpos.rang = j;
	          actualpos.etage = i;
	          Piece c = this.partie_actuel.partieEnCours.joueur1().getCamp().getPiece(actualpos);
	          Image image;
	          if (c == null) {
	            image = colortoimage(Couleurs.VIDE);
	          } else {
	            image = colortoimage(c.getColor());
	          }
	           g.drawImage(image,posX,posY,largeur_piece,hauteur_piece, null);
	           posX+=largeur_piece;
	        }
	        decalage+=largeur_piece/2;
	        posY-= hauteur_piece*0.9;
	        posX = posX_depart+decalage;//+decalage;
	      }
   }
   
   public void drawbaPyramideJ2(Graphics g) {
	   g.drawImage(texture.imagevol,posX_volJ2,poxY_volJ2,largeur_vol,hauteur_vol, null);
	     Position actualpos = new Position(0, 0);
	     int posX_depart = posX_ileJ2+(int)(largeur_ileJ*0.49)-((largeur_piece*6)/2);
	     int posX = posX_depart;
	     int posY = posY_depart;
	     int decalage=0;
	      for (int i = 0; i < this.partie_actuel.partieEnCours.joueur2().getCamp().getHauteur(); i++) { // etage
	        for (int j = 0; j < (this.partie_actuel.partieEnCours.joueur2().getCamp().getLargeur() - i); j++) { // rang
	          actualpos.rang = j;
	          actualpos.etage = i;
	          Piece c = this.partie_actuel.partieEnCours.joueur2().getCamp().getPiece(actualpos);
	          Image image;
	          if (c == null) {
	            image = colortoimage(Couleurs.VIDE);
	          } else {
	            image = colortoimage(c.getColor());
	          }
	           g.drawImage(image,posX,posY,largeur_piece,hauteur_piece, null);
	           posX+=largeur_piece;
	        }
	        decalage+=largeur_piece/2;
	        posY-= hauteur_piece*0.9;
	        posX = posX_depart+decalage;//+decalage;
	      }
   }
   
   public void drawbaPyramideMilieu(Graphics g) {
     Position actualpos = new Position(0, 0);
     int posX_depart = posX_ileM+largeur_ileM/2-((largeur_piece*9)/2);
     int posX = posX_depart;
     int posY = posY_depart;
     int decalage=0;
      for (int i = 0; i < this.partie_actuel.partieEnCours.getBaseMontagne().getHauteur(); i++) { // etage
        for (int j = 0; j < (this.partie_actuel.partieEnCours.getBaseMontagne().getLargeur() - i); j++) { // rang
          actualpos.rang = j;
          actualpos.etage = i;
          Piece c = this.partie_actuel.partieEnCours.getBaseMontagne().getPiece(actualpos);
          Image image;
          if (c == null) {
            image = colortoimage(Couleurs.VIDE);
          } else {
            image = colortoimage(c.getColor());
          }
           g.drawImage(image,posX,posY,largeur_piece,hauteur_piece, null);
           posX+=largeur_piece;
        }
        decalage+=largeur_piece/2;
        posY-= hauteur_piece*0.9;
        posX = posX_depart+decalage;//+decalage;
      }
   }
   
    public Image colortoimage(Couleurs c) {
	    switch (c) {
	    case BLEU:
	      return this.texture.pieceBleue;
	    case VERT:
	      return this.texture.pieceVert;  
	    case NOIR:
	      return this.texture.pieceNoire; 
	    case JAUNE:
	      return this.texture.pieceJaune;
	    case ROUGE:
	      return this.texture.pieceRouge;
	    case BLANC:
	      return this.texture.pieceBlanche;
	    case NATUREL:
	      return this.texture.pieceNature;
    }
    return null;
  }
   
   
   public Jeu partie_actuel;
   public ArrayList<PosPixel> pospion = new ArrayList<>();

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
