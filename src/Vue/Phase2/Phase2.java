/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Vue.Phase2;

import Controleur.Jeu;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import Modeles.Couleurs;
import Modeles.Piece;
import Modeles.Position;

/**
 *
 * @author farid
 */
public class Phase2 extends javax.swing.JFrame {

    /**
     * Creates new form Etape2
     */
    public Phase2() {
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 709, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 359, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Phase2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Phase2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Phase2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Phase2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Phase2().setVisible(true);
            }
        });
    }
    
    
     
    @Override
   public void paint(Graphics g) {
       pospion.clear();
       drawbackground(g);
       drawIle(g);
       drawbaPyramideJ1(g);
       drawbaPyramideJ2(g);
       drawbaPyramideMilieu(g);
   }
   
   public void drawbackground(Graphics g) {
     BufferedImage imageback = this.galerie.imageBackground;
      g.drawImage(imageback, this.getLocationOnScreen().x, this.getLocationOnScreen().y, this.getWidth(), this.getHeight(), null);
      
       BufferedImage imagepassetour = this.galerie.imagePassertour;
      g.drawImage(imagepassetour, this.getWidth()/3, this.getHeight() -this.getHeight()/7, this.getWidth()/9, this.getHeight()/13, null);
      
       BufferedImage imagecouprecedent = this.galerie.imageeCoupPrecedent;
      g.drawImage(imagecouprecedent, this.getWidth()/2, this.getHeight() -this.getHeight()/7, this.getWidth()/9, this.getHeight()/13, null);
   }
   
   public void drawIle(Graphics g) {
     BufferedImage imageback = this.galerie.imageile;
          BufferedImage imageback2 = this.galerie.imageile2;
          BufferedImage imageback3 = this.galerie.imageile3;

     int posx =  this.getLocationOnScreen().x -this.getWidth()/5 ;
     int posy = this.getLocationOnScreen().y +this.getWidth()/6 ;
             
      g.drawImage(imageback,posx, posy, (2*this.getWidth())/3, (2*this.getHeight())/3, null);
      posx = this.getWidth()/2;
      g.drawImage(imageback2,posx, posy, (2*this.getWidth())/3, (2*this.getHeight())/3, null);
       posy = this.getLocationOnScreen().y +this.getWidth()/4 ;
      posx = this.getWidth()/2 - (2*this.getWidth())/9;
      g.drawImage(imageback3,posx, posy, (2*this.getWidth())/5, (2*this.getHeight())/5, null);
   }
   
  
   
   public void drawbaPyramideJ1(Graphics g) {
       
        BufferedImage imagevol = this.galerie.imagevol;
        g.drawImage(imagevol,this.getWidth()/19,this.getHeight()/20,this.getWidth()/7,this.getHeight()/5, null);
      
     Position actualpos = new Position(0, 0);
      for (int i = 0; i < this.partie_actuel.partieEnCours.joueur1().getCamp().getHauteur(); i++) { // colone
        for (int j = 0; j < (this.partie_actuel.partieEnCours.joueur1().getCamp().getLargeur() - i); j++) { // ligne
          actualpos.rang = j;
          actualpos.etage = i;
          
          int BoutX = this.getWidth()/18;
          int BoutY = this.getHeight()/18;
          
          
            int FinalWidth = this.getWidth()/11;
          int FinalHeight = this.getHeight()/12;
          int decalage = ((i-j)*BoutX/4) -((j)*FinalWidth/5)  ;
          int PosX =this.getLocation().x + j*BoutX + decalage +BoutX/2  ;
          int PosY = this.getLocation().y + (6-i)*BoutY +this.getHeight()/9 +i*BoutY/3 +i*BoutY/10 +BoutY/2  ;
      
          Piece c = this.partie_actuel.partieEnCours.joueur1().getCamp().getPiece(actualpos);
          BufferedImage image;
     
          if (c == null) {

            image = colortoimage(null);
          } else {
            image = colortoimage(c.getColor());
          }
           pospion.add(new PosPixel(PosX,PosY));
           g.drawImage(image,PosX,PosY,FinalWidth,FinalHeight, null);
           
          
        }
      }

   }
   
   public void drawbaPyramideJ2(Graphics g) {
       
        BufferedImage imagevol = this.galerie.imagevol;
        g.drawImage(imagevol,3*this.getWidth()/4,this.getHeight()/20,this.getWidth()/7,this.getHeight()/5, null);
      
     Position actualpos = new Position(0, 0);
      for (int i = 0; i < this.partie_actuel.partieEnCours.joueur2().getCamp().getHauteur(); i++) { // colone
        for (int j = 0; j < (this.partie_actuel.partieEnCours.joueur2().getCamp().getLargeur() - i); j++) { // ligne
          actualpos.rang = j;
          actualpos.etage = i;
          
          int BoutX = this.getWidth()/18;
          int BoutY = this.getHeight()/18;
          
          
            int FinalWidth = this.getWidth()/11;
          int FinalHeight = this.getHeight()/12;
          int decalage = ((i-j)*BoutX/4) -((j)*FinalWidth/5)  ;
          int PosX =this.getX() + j*BoutX + decalage +13*BoutX  ;
          int PosY = this.getY() + (6-i)*BoutY +this.getHeight()/9 +i*BoutY/3 +i*BoutY/10 +BoutY/2  ;
      
          Piece c = this.partie_actuel.partieEnCours.joueur2().getCamp().getPiece(actualpos);
          BufferedImage image;
     
          if (c == null) {

            image = colortoimage(null);
          } else {
            image = colortoimage(c.getColor());
          }
          
           g.drawImage(image,PosX,PosY,FinalWidth,FinalHeight, null);
           
          
        }
      }

   }
   
   
   public void drawbaPyramideMilieu(Graphics g) {
       
      
     Position actualpos = new Position(0, 0);
      for (int i = 0; i < this.partie_actuel.partieEnCours.getBaseMontagne().getHauteur(); i++) { // colone
        for (int j = 0; j < (this.partie_actuel.partieEnCours.getBaseMontagne().getLargeur() - i); j++) { // ligne
          actualpos.rang = j;
          actualpos.etage = i;
          
          int BoutX = this.getWidth()/18;
          int BoutY = this.getHeight()/18;
          
          
            int FinalWidth = this.getWidth()/11;
          int FinalHeight = this.getHeight()/12;
          int decalage = ((i-j)*BoutX/4) -((j)*FinalWidth/5)+6*BoutX - 4*BoutX/11  ;
          int PosX =this.getX() + j*BoutX + decalage +BoutX/2  ;
          int PosY = this.getY() + (6-i)*BoutY +this.getHeight()/9 +i*BoutY/3 +i*BoutY/10 +BoutY + BoutY/8  ;
      
          Piece c = this.partie_actuel.partieEnCours.getBaseMontagne().getPiece(actualpos);
          BufferedImage image;
     
          if (c == null) {

            image = colortoimage(null);
          } else {
            image = colortoimage(c.getColor());
          }
          
           g.drawImage(image,PosX,PosY,FinalWidth,FinalHeight, null);
           
          
        }
      }

   }
   
   
    public BufferedImage colortoimage(Couleurs c) {
    switch (c) {
    case BLEU:
      return this.galerie.imageBLUE;
    case VERT:
      return this.galerie.imageGREEN;  
    case NOIR:
      return this.galerie.imageBLACK; 
    case JAUNE:
      return this.galerie.imageYELLOW;
    case ROUGE:
      return this.galerie.imageRED;
    case BLANC:
      return this.galerie.imageWHITE;
    case NATUREL:
      return this.galerie.imageWOOD;
    
    }
    return null;
  }
   
   
   public Jeu partie_actuel;
   ToolsImage galerie = new ToolsImage();
   public ArrayList<PosPixel> pospion = new ArrayList<>();

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
