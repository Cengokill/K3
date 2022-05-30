/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vue.Phase2;

import Controleur.Jeu;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

import Modeles.Couleurs;
import Modeles.Piece;
import Modeles.Position;

public class Tools {

  public Jeu partie_actuel;
  ToolsImage galerie;

  public Tools(Jeu partie) {
    this.partie_actuel = partie;
    this.galerie = new ToolsImage();
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
    
    default:
      return this.galerie.imageEMPTY_WHITE;

    }
  }
  
  
  
    public void drawpyramide2(SuivieEtat etat ,Graphics g, int uniteX, int uniteY, JPanel jpanel, int typejoueur) {
         BufferedImage imagetap = this.galerie.imagehand;
        switch (typejoueur) {
    case 1: {
      Position actualpos = new Position(0, 0);
      for (int i = 0; i < this.partie_actuel.partieEnCours.joueur1().getCamp().getHauteur(); i++) { // colone
        for (int j = 0; j < (this.partie_actuel.partieEnCours.joueur1().getCamp().getLargeur() - i); j++) { // ligne
          actualpos.rang = j;
          actualpos.etage = i;
          Piece c = this.partie_actuel.partieEnCours.joueur1().getCamp().getPiece(actualpos);
          BufferedImage image;
          
               
          
          if (c == null) {

            image = colortoimage(null);
          } else {
            image = colortoimage(c.getColor());
          }
          
           g.drawImage(image, (jpanel.getX() + (j * uniteX))-(63*j)+(13*i)+20, jpanel.getY() + ((8-i)* uniteY)+37*i -30, jpanel.getWidth()/6,jpanel.getHeight()/6 , null);
        }
      }
        
    }
    
    case 2: {
      Position actualpos = new Position(0, 0);
      for (int i = 0; i < this.partie_actuel.partieEnCours.joueur2().getCamp().getHauteur(); i++) { // colone
        for (int j = 0; j < (this.partie_actuel.partieEnCours.joueur2().getCamp().getLargeur() - i); j++) { // ligne
          actualpos.rang = j;
          actualpos.etage = i;
          Piece c = this.partie_actuel.partieEnCours.joueur2().getCamp().getPiece(actualpos);
          BufferedImage image;
          
               
          
          if (c == null) {

            image = colortoimage(null);
          } else {
            image = colortoimage(c.getColor());
          }
          
           g.drawImage(image, (jpanel.getX() + (j * uniteX))-(63*j)+(13*i)+20, jpanel.getY() + ((8-i)* uniteY)+37*i -30, jpanel.getWidth()/6,jpanel.getHeight()/6 , null);
           
          
        }
      }
        
    }
        }
    }
  

  public void drawpyramide(SuivieEtat etat ,Graphics g, int uniteX, int uniteY, Point test, int typejoueur) {
      
      BufferedImage imagetap = this.galerie.imagehand;
          
          
          
    switch (typejoueur) {
    case 1: {
      Position actualpos = new Position(0, 0);
      for (int i = 0; i < this.partie_actuel.partieEnCours.joueur1().getCamp().getHauteur(); i++) { // colone
        for (int j = 0; j < (this.partie_actuel.partieEnCours.joueur1().getCamp().getLargeur() - i); j++) { // ligne
          actualpos.rang = j;
          actualpos.etage = i;
          Piece c = this.partie_actuel.partieEnCours.joueur1().getCamp().getPiece(actualpos);
          BufferedImage image;
           
               
          
          if (c == null) {

            image = colortoimage(null);
          } else {
            image = colortoimage(c.getColor());
          }
          switch (i) {
          case 0:
            switch (j) {
            case 0:
              g.drawImage(image, (test.x + (0 * uniteX)), test.y + (8 * uniteY), uniteX, uniteY, null);
              break;
            case 1:
              g.drawImage(image, (test.x + (1 * uniteX)), test.y + (8 * uniteY), uniteX, uniteY, null);
              break;
            case 2:
              g.drawImage(image, (test.x + (2 * uniteX)), test.y + (8 * uniteY), uniteX, uniteY, null);
              break;
            case 3:
              g.drawImage(image, (test.x + (3 * uniteX)), test.y + (8 * uniteY), uniteX, uniteY, null);
              break;
            case 4:
              g.drawImage(image, (test.x + (4 * uniteX)), test.y + (8 * uniteY), uniteX, uniteY, null);
              break;
            case 5:
              g.drawImage(image, (test.x + (5 * uniteX)), test.y + (8 * uniteY), uniteX, uniteY, null);
              break;

            }
            break;

          case 1:
            switch (j) {
            case 0:
              g.drawImage(image, test.x + (0 * uniteX) + (uniteX / 2), test.y + test.y / 4 + (7 * uniteY), uniteX, uniteY, null);
              break;
            case 1:
              g.drawImage(image, (test.x + (1 * uniteX)) + (uniteX / 2), test.y + test.y / 4 + (7 * uniteY), uniteX, uniteY, null);
              break;
            case 2:
              g.drawImage(image, (test.x + (2 * uniteX)) + (uniteX / 2), test.y + test.y / 4 + (7 * uniteY), uniteX, uniteY, null);
              break;
            case 3:
              g.drawImage(image, (test.x + (3 * uniteX)) + (uniteX / 2), test.y + test.y / 4 + (7 * uniteY), uniteX, uniteY, null);
              break;
            case 4:
              g.drawImage(image, (test.x + (4 * uniteX)) + (uniteX / 2), test.y + test.y / 4 + (7 * uniteY), uniteX, uniteY, null);
              break;
            }
            break;

          case 2:
            switch (j) {
            case 0:
              g.drawImage(image, (test.x + (1 * uniteX)), test.y + test.y / 2 + (6 * uniteY), uniteX, uniteY, null);
              break;
            case 1:
              g.drawImage(image, (test.x + (2 * uniteX)), test.y + test.y / 2 + (6 * uniteY), uniteX, uniteY, null);
              break;
            case 2:
              g.drawImage(image, (test.x + (3 * uniteX)), test.y + test.y / 2 + (6 * uniteY), uniteX, uniteY, null);
              break;
            case 3:
              g.drawImage(image, (test.x + (4 * uniteX)), test.y + test.y / 2 + (6 * uniteY), uniteX, uniteY, null);
              break;
            }
            break;
          case 3:
            switch (j) {
            case 0:
              g.drawImage(image, (test.x + (2 * uniteX)) - (uniteX) + (uniteX / 2), test.y + test.y / 2 + test.y / 3 + (5 * uniteY), uniteX, uniteY, null);
              break;
            case 1:
              g.drawImage(image, (test.x + (3 * uniteX)) - (uniteX) + (uniteX / 2), test.y + test.y / 2 + test.y / 3 + (5 * uniteY), uniteX, uniteY, null);
              break;
            case 2:
              g.drawImage(image, (test.x + (4 * uniteX)) - (uniteX) + (uniteX / 2), test.y + test.y / 2 + test.y / 3 + (5 * uniteY), uniteX, uniteY, null);
              break;
            }
            break;
          case 4:

            switch (j) {
            case 0:
              g.drawImage(image, (test.x + (2 * uniteX)), 2 * test.y + test.y / 4 + (4 * uniteY), uniteX, uniteY, null);
              break;
            case 1:
              g.drawImage(image, (test.x + (i * uniteX)) - (uniteX), 2 * test.y + test.y / 4 + (4 * uniteY), uniteX, uniteY, null);
              break;
            }
            break;
          case 5:
            switch (j) {
            case 0:
                if(verifselection2(typejoueur-1,etat,i,j)) { 
                    System.out.print("cc");
                    g.drawImage(image, (test.x + (2 * uniteX)) + (uniteX / 2),  test.y + test.y  + (3 * uniteY), uniteX, uniteY, null);

                }
                else {
                 g.drawImage(image, (test.x + (2 * uniteX)) + (uniteX / 2), 2 * test.y + test.y / 2 + (3 * uniteY), uniteX, uniteY, null);

                }
              break;
            }
            break;

          }

        }

      }
      break;
    }
    case 2: {
      Position actualpos = new Position(0, 0);
      for (int i = 0; i < this.partie_actuel.partieEnCours.joueur2().getCamp().getHauteur(); i++) { // colone
        for (int j = 0; j < (this.partie_actuel.partieEnCours.joueur2().getCamp().getLargeur() - i); j++) { // ligne
          actualpos.rang = j;
          actualpos.etage = i;
          Piece c = this.partie_actuel.partieEnCours.joueur2().getCamp().getPiece(actualpos);
          BufferedImage image;
          if (c == null) {

            image = colortoimage(null);
          } else {
            image = colortoimage(c.getColor());
          }

          switch (i) {
          case 0:
            switch (j) {
            case 0:
                 if(verifselection2(typejoueur-1,etat,i,j)) { 
                                                    g.drawImage(image, (test.x + (0 * uniteX)), test.y/3 + (8 * uniteY), uniteX, uniteY, null);

                 }
                 else {
                                      g.drawImage(image, (test.x + (0 * uniteX)), test.y + (8 * uniteY), uniteX, uniteY, null);

                 }
              break;
            case 1:
                if(verifselection2(typejoueur-1,etat,i,j)) { 
                                                 g.drawImage(image, (test.x + (1 * uniteX)), test.y/3 + (8 * uniteY), uniteX, uniteY, null);

                 }
                 else {
                                   g.drawImage(image, (test.x + (1 * uniteX)), test.y + (8 * uniteY), uniteX, uniteY, null);

                 }
              break;
            case 2:
                if(verifselection2(typejoueur-1,etat,i,j)) { 
                                                 g.drawImage(image, (test.x + (2 * uniteX)), test.y/3 + (8 * uniteY), uniteX, uniteY, null);

                 }
                 else {
                                   g.drawImage(image, (test.x + (2 * uniteX)), test.y + (8 * uniteY), uniteX, uniteY, null);

                 }
              break;
            case 3:
                if(verifselection2(typejoueur-1,etat,i,j)) { 
                                                 g.drawImage(image, (test.x + (3 * uniteX)), test.y/3 + (8 * uniteY), uniteX, uniteY, null);

                 }
                 else {
                                   g.drawImage(image, (test.x + (3 * uniteX)), test.y + (8 * uniteY), uniteX, uniteY, null);

                 }
              break;
            case 4:
                if(verifselection2(typejoueur-1,etat,i,j)) { 
                                                 g.drawImage(image, (test.x + (4 * uniteX)), test.y/3 + (8 * uniteY), uniteX, uniteY, null);

                 }
                 else {
                                   g.drawImage(image, (test.x + (4 * uniteX)), test.y + (8 * uniteY), uniteX, uniteY, null);

                 }
              break;
            case 5:
                if(verifselection2(typejoueur-1,etat,i,j)) { 
                                                 g.drawImage(image, (test.x + (5 * uniteX)), test.y/3 + (8 * uniteY), uniteX, uniteY, null);

                 }
                 else {
                                   g.drawImage(image, (test.x + (5 * uniteX)), test.y + (8 * uniteY), uniteX, uniteY, null);

                 }
              break;
            }
            break;

          case 1:
            switch (j) {
            case 0:
                 if(verifselection2(typejoueur-1,etat,i,j)) { 
                    g.drawImage(image, test.x + (0 * uniteX) + (uniteX / 2), test.y + test.y / 15 + (7 * uniteY), uniteX, uniteY, null);
                 }
                 else {
                                         g.drawImage(image, test.x + (0 * uniteX) + (uniteX / 2), test.y + test.y / 4 + (7 * uniteY), uniteX, uniteY, null);

                 }
              break;
            case 1:
                if(verifselection2(typejoueur-1,etat,i,j)) { 
              g.drawImage(image, (test.x + (1 * uniteX)) + (uniteX / 2), test.y + test.y / 15 + (7 * uniteY), uniteX, uniteY, null);
                }
                else {
                                  g.drawImage(image, (test.x + (1 * uniteX)) + (uniteX / 2), test.y + test.y / 4 + (7 * uniteY), uniteX, uniteY, null);

                }
              break;
            case 2:
                if(verifselection2(typejoueur-1,etat,i,j)) { 
              g.drawImage(image, (test.x + (2 * uniteX)) + (uniteX / 2), test.y + test.y / 15 + (7 * uniteY), uniteX, uniteY, null);
                }
                else {
                                  g.drawImage(image, (test.x + (2 * uniteX)) + (uniteX / 2), test.y + test.y / 4 + (7 * uniteY), uniteX, uniteY, null);

                }
              break;
            case 3:
                if(verifselection2(typejoueur-1,etat,i,j)) { 
              g.drawImage(image, (test.x + (3 * uniteX)) + (uniteX / 2), test.y + test.y / 15 + (7 * uniteY), uniteX, uniteY, null);
                }
                else {
               g.drawImage(image, (test.x + (3 * uniteX)) + (uniteX / 2), test.y + test.y / 4 + (7 * uniteY), uniteX, uniteY, null);

                }
              break;
            case 4:
                if(verifselection2(typejoueur-1,etat,i,j)) { 
              g.drawImage(image, (test.x + (4 * uniteX)) + (uniteX / 2), test.y + test.y /15  + (7 * uniteY), uniteX, uniteY, null);
                }
                else {
              g.drawImage(image, (test.x + (4 * uniteX)) + (uniteX / 2), test.y + test.y / 4 + (7 * uniteY), uniteX, uniteY, null);

                }
              break;
            }
            break;

          case 2:
            switch (j) {
            case 0:
               if(verifselection2(typejoueur-1,etat,i,j)) { 
                   g.drawImage(image, (test.x + (1 * uniteX)), test.y  + (6 * uniteY), uniteX, uniteY, null);
               }
               else {
                   g.drawImage(image, (test.x + (1 * uniteX)), test.y + test.y / 2 + (6 * uniteY), uniteX, uniteY, null);
               }
                
              
              break;
            case 1:
                if(verifselection2(typejoueur-1,etat,i,j)) { 
                    g.drawImage(image, (test.x + (2 * uniteX)), test.y +  (6 * uniteY), uniteX, uniteY, null);

               }
               else {
                   g.drawImage(image, (test.x + (2 * uniteX)), test.y + test.y / 2 + (6 * uniteY), uniteX, uniteY, null);

               }
              break;
            case 2:
                if(verifselection2(typejoueur-1,etat,i,j)) { 
                  g.drawImage(image, (test.x + (3 * uniteX)), test.y  + (6 * uniteY), uniteX, uniteY, null);

               }
               else {
                                 g.drawImage(image, (test.x + (3 * uniteX)), test.y + test.y / 2 + (6 * uniteY), uniteX, uniteY, null);

               }
              break;
            case 3:
                if(verifselection2(typejoueur-1,etat,i,j)) { 
                    g.drawImage(image, (test.x + (4 * uniteX)), test.y  + (6 * uniteY), uniteX, uniteY, null);

               }
               else {
                                 g.drawImage(image, (test.x + (4 * uniteX)), test.y + test.y / 2 + (6 * uniteY), uniteX, uniteY, null);

               }
              break;
            }
            break;
          case 3:
            switch (j) {
            case 0:
                 if(verifselection2(typejoueur-1,etat,i,j)) { 
              g.drawImage(image, (test.x + (2 * uniteX)) - (uniteX) + (uniteX / 2), test.y + test.y / 3 + (5 * uniteY), uniteX, uniteY, null);
                 }
                 else {
              g.drawImage(image, (test.x + (2 * uniteX)) - (uniteX) + (uniteX / 2), test.y + test.y / 2 + test.y / 3 + (5 * uniteY), uniteX, uniteY, null);

                 }
              break;
            case 1:
                if(verifselection2(typejoueur-1,etat,i,j)) { 
                  g.drawImage(image, (test.x + (3 * uniteX)) - (uniteX) + (uniteX / 2), test.y  + test.y / 3 + (5 * uniteY), uniteX, uniteY, null);
                }
                else {
               g.drawImage(image, (test.x + (3 * uniteX)) - (uniteX) + (uniteX / 2), test.y +test.y/2+ test.y / 3 + (5 * uniteY), uniteX, uniteY, null);

                }
              break;
            case 2:
                if(verifselection2(typejoueur-1,etat,i,j)) { 
                    g.drawImage(image, (test.x + (4 * uniteX)) - (uniteX) + (uniteX / 2), test.y  + test.y / 3 + (5 * uniteY), uniteX, uniteY, null);
                }
                else {
                    g.drawImage(image, (test.x + (4 * uniteX)) - (uniteX) + (uniteX / 2), test.y + test.y / 2 + test.y / 3 + (5 * uniteY), uniteX, uniteY, null);

                }
              break;
            }
            break;
          case 4:

            switch (j) {
            case 0:
                 if(verifselection2(typejoueur-1,etat,i,j)) { 
                                                        g.drawImage(image, (test.x + (2 * uniteX)),  test.y + test.y / 3 + (4 * uniteY), uniteX, uniteY, null);

                 }
                 else {
                                   g.drawImage(image, (test.x + (2 * uniteX)), 2 * test.y + test.y / 4 + (4 * uniteY), uniteX, uniteY, null);

                 }
              break;
            case 1:
                if(verifselection2(typejoueur-1,etat,i,j)) { 
                                        g.drawImage(image, (test.x + (i * uniteX)) - (uniteX),  test.y + 2*test.y / 3 + (4 * uniteY), uniteX, uniteY, null);


                }
                else {
                                        g.drawImage(image, (test.x + (i * uniteX)) - (uniteX), 2 * test.y + test.y / 4 + (4 * uniteY), uniteX, uniteY, null);


                }
              break;
            }
            break;
          case 5:
            switch (j) {
                
            case 0:
                if(verifselection2(typejoueur-1,etat,i,j)) { 
                    System.out.print("cc");
                    g.drawImage(image, (test.x + (2 * uniteX)) + (uniteX / 2),  test.y + test.y  + (3 * uniteY), uniteX, uniteY, null);

                }
                else {
                   g.drawImage(image, (test.x + (2 * uniteX)) + (uniteX / 2), 2 * test.y + test.y / 2 + (3 * uniteY), uniteX, uniteY, null);

                }
              break;
            }
            break;

          }
        }

      }
      break;
    }
    
    case 4: {
      Position actualpos = new Position(0, 0);
        for (int j = 0; j < 9; j++) { // ligne
        
          Piece c = this.partie_actuel.partieEnCours.joueur2().getCamp().getPiece(actualpos);
          BufferedImage image;
          if (c == null) {

            image = colortoimage(null);
          } else {
            image = colortoimage(c.getColor());
          }
          
          g.drawImage(image, test.x + (j * uniteX) , test.y , uniteX, uniteY, null);
        }
      
      break;
    }
    default: {
      Position actualpos = new Position(0, 0);
      for (int i = 0; i < this.partie_actuel.partieEnCours.getBaseMontagne().getHauteur(); i++) { // colone
        for (int j = 0; j < (this.partie_actuel.partieEnCours.getBaseMontagne().getLargeur() - i); j++) { // ligne
          actualpos.rang = j;
          actualpos.etage = i;
          Piece c = this.partie_actuel.partieEnCours.getBaseMontagne().getPiece(actualpos);
          BufferedImage image;
          if (c == null) {

            image = colortoimage(null);
          } else {
            image = colortoimage(c.getColor());
          }
          switch (i) {
          case 0:
            switch (j) {
            case 0:
              g.drawImage(image, (test.x + (0 * uniteX)), test.y + (8 * uniteY), uniteX, uniteY, null);
              break;
            case 1:
              g.drawImage(image, (test.x + (1 * uniteX)), test.y + (8 * uniteY), uniteX, uniteY, null);
              break;
            case 2:
              g.drawImage(image, (test.x + (2 * uniteX)), test.y + (8 * uniteY), uniteX, uniteY, null);
              break;
            case 3:
              g.drawImage(image, (test.x + (3 * uniteX)), test.y + (8 * uniteY), uniteX, uniteY, null);
              break;
            case 4:
              g.drawImage(image, (test.x + (4 * uniteX)), test.y + (8 * uniteY), uniteX, uniteY, null);
              break;
            case 5:
              g.drawImage(image, (test.x + (5 * uniteX)), test.y + (8 * uniteY), uniteX, uniteY, null);
              break;
            case 6:
              g.drawImage(image, (test.x + (6 * uniteX)), test.y + (8 * uniteY), uniteX, uniteY, null);
              break;
            case 7:
              g.drawImage(image, (test.x + (7 * uniteX)), test.y + (8 * uniteY), uniteX, uniteY, null);
              break;
            case 8:
              g.drawImage(image, (test.x + (8 * uniteX)), test.y + (8 * uniteY), uniteX, uniteY, null);
              break;
            }
            break;

          case 1:
            switch (j) {
            case 0:
              
              g.drawImage(image, test.x + (0 * uniteX) + (uniteX / 2), test.y + test.y / 5 + (7 * uniteY), uniteX, uniteY, null);
            if(verifselection(etat,i,j) == true)   g.drawImage(imagetap, (test.x + (0 * uniteX) + (uniteX / 2))+uniteX/3, ((test.y + test.y / 5 + (7 * uniteY)))+uniteY/2, uniteX/2, uniteY/2, null);

              break;
            case 1:
              g.drawImage(image, (test.x + (1 * uniteX)) + (uniteX / 2), test.y + test.y / 5 + (7 * uniteY), uniteX, uniteY, null);
            if(verifselection(etat,i,j) == true)  g.drawImage(imagetap, (test.x + (1 * uniteX) + (uniteX / 2))+uniteX/3, ((test.y + test.y / 5 + (7 * uniteY)))+uniteY/2, uniteX/2, uniteY/2, null);

              break;
            case 2:
              g.drawImage(image, (test.x + (2 * uniteX)) + (uniteX / 2), test.y + test.y / 5 + (7 * uniteY), uniteX, uniteY, null);
              if(verifselection(etat,i,j) == true) g.drawImage(imagetap, (test.x + (2 * uniteX) + (uniteX / 2))+uniteX/3, ((test.y + test.y / 5 + (7 * uniteY)))+uniteY/2, uniteX/2, uniteY/2, null);

              break;
            case 3:
              g.drawImage(image, (test.x + (3 * uniteX)) + (uniteX / 2), test.y + test.y / 5 + (7 * uniteY), uniteX, uniteY, null);
             if(verifselection(etat,i,j) == true) g.drawImage(imagetap, (test.x + (3 * uniteX) + (uniteX / 2))+uniteX/3, ((test.y + test.y / 5 + (7 * uniteY)))+uniteY/2, uniteX/2, uniteY/2, null);

              break;
            case 4:
              g.drawImage(image, (test.x + (4 * uniteX)) + (uniteX / 2), test.y + test.y / 5 + (7 * uniteY), uniteX, uniteY, null);
             if(verifselection(etat,i,j) == true)  g.drawImage(imagetap, (test.x + (4 * uniteX) + (uniteX / 2))+uniteX/3, ((test.y + test.y / 5 + (7 * uniteY)))+uniteY/2, uniteX/2, uniteY/2, null);

              break;
            case 5:
              g.drawImage(image, (test.x + (5 * uniteX)) + (uniteX / 2), test.y + test.y / 5 + (7 * uniteY), uniteX, uniteY, null);
             if(verifselection(etat,i,j) == true)  g.drawImage(imagetap, (test.x + (5 * uniteX) + (uniteX / 2))+uniteX/3, ((test.y + test.y / 5 + (7 * uniteY)))+uniteY/2, uniteX/2, uniteY/2, null);

              break;
            case 6:
              g.drawImage(image, (test.x + (6 * uniteX)) + (uniteX / 2), test.y + test.y / 5 + (7 * uniteY), uniteX, uniteY, null);
              if(verifselection(etat,i,j) == true) g.drawImage(imagetap, (test.x + (6 * uniteX) + (uniteX / 2))+uniteX/3, ((test.y + test.y / 5 + (7 * uniteY)))+uniteY/2, uniteX/2, uniteY/2, null);

              break;
            case 7:
              g.drawImage(image, (test.x + (7 * uniteX)) + (uniteX / 2), test.y + test.y / 5 + (7 * uniteY), uniteX, uniteY, null);
             if(verifselection(etat,i,j) == true) g.drawImage(imagetap, (test.x + (7 * uniteX) + (uniteX / 2))+uniteX/3, ((test.y + test.y / 5 + (7 * uniteY)))+uniteY/2, uniteX/2, uniteY/2, null);

              break;
            }
            break;

          case 2:
            switch (j) {
            case 0:
              g.drawImage(image, (test.x + (1 * uniteX)), test.y + test.y / 2 + (6 * uniteY), uniteX, uniteY, null);
             if(verifselection(etat,i,j) == true) g.drawImage(imagetap, (test.x + (1 * uniteX))+uniteX/3, (test.y + test.y / 2 + (6 * uniteY))+uniteY/3, uniteX/2, uniteY/2, null);
              break;
            case 1:
              g.drawImage(image, (test.x + (2 * uniteX)), test.y + test.y / 2 + (6 * uniteY), uniteX, uniteY, null);
              if(verifselection(etat,i,j) == true)                    g.drawImage(imagetap, (test.x + (2 * uniteX))+uniteX/3, (test.y + test.y / 2 + (6 * uniteY))+uniteY/3, uniteX/2, uniteY/2, null);

              break;
            case 2:
              g.drawImage(image, (test.x + (3 * uniteX)), test.y + test.y / 2 + (6 * uniteY), uniteX, uniteY, null);
              if(verifselection(etat,i,j) == true) g.drawImage(imagetap, (test.x + (3 * uniteX))+uniteX/3, (test.y + test.y / 2 + (6 * uniteY))+uniteY/3, uniteX/2, uniteY/2, null);

              break;
            case 3:
              g.drawImage(image, (test.x + (4 * uniteX)), test.y + test.y / 2 + (6 * uniteY), uniteX, uniteY, null);
                 if(verifselection(etat,i,j) == true)                                     g.drawImage(imagetap, (test.x + (4 * uniteX))+uniteX/3, (test.y + test.y / 2 + (6 * uniteY))+uniteY/3, uniteX/2, uniteY/2, null);

              break;
            case 4:
              g.drawImage(image, (test.x + (5 * uniteX)), test.y + test.y / 2 + (6 * uniteY), uniteX, uniteY, null);
                if(verifselection(etat,i,j) == true)                                      g.drawImage(imagetap, (test.x + (5 * uniteX))+uniteX/3, (test.y + test.y / 2 + (6 * uniteY))+uniteY/3, uniteX/2, uniteY/2, null);

              break;
            case 5:
              g.drawImage(image, (test.x + (6 * uniteX)), test.y + test.y / 2 + (6 * uniteY), uniteX, uniteY, null);
                if(verifselection(etat,i,j) == true)                                      g.drawImage(imagetap, (test.x + (6 * uniteX))+uniteX/3, (test.y + test.y / 2 + (6 * uniteY))+uniteY/3, uniteX/2, uniteY/2, null);

              break;
            case 6:
              g.drawImage(image, (test.x + (7 * uniteX)), test.y + test.y / 2 + (6 * uniteY), uniteX, uniteY, null);
                  if(verifselection(etat,i,j) == true)                                    g.drawImage(imagetap, (test.x + (7 * uniteX))+uniteX/3, (test.y + test.y / 2 + (6 * uniteY))+uniteY/3, uniteX/2, uniteY/2, null);

              break;
            }
            break;
          case 3:
            switch (j) {
            case 0:
              g.drawImage(image, (test.x + (2 * uniteX)) - (uniteX) + (uniteX / 2), test.y + test.y - test.y / 4 + (5 * uniteY), uniteX, uniteY, null);
             if(verifselection(etat,i,j) == true) g.drawImage(imagetap, (test.x + (2 * uniteX)) - (uniteX) + (uniteX / 2)+uniteX/3, test.y + test.y - test.y / 4 + (5 * uniteY)+uniteY/3, uniteX/2, uniteY/2, null);

              break;
            case 1:
              g.drawImage(image, (test.x + (3 * uniteX)) - (uniteX) + (uniteX / 2), test.y + test.y - test.y / 4 + (5 * uniteY), uniteX, uniteY, null);
              if(verifselection(etat,i,j) == true) g.drawImage(imagetap, (test.x + (3 * uniteX)) - (uniteX) + (uniteX / 2)+uniteX/3, test.y + test.y - test.y / 4 + (5 * uniteY)+uniteY/3, uniteX/2, uniteY/2, null);

              break;
            case 2:
              g.drawImage(image, (test.x + (4 * uniteX)) - (uniteX) + (uniteX / 2), test.y + test.y - test.y / 4 + (5 * uniteY), uniteX, uniteY, null);
               if(verifselection(etat,i,j) == true)                   g.drawImage(imagetap, (test.x + (4 * uniteX)) - (uniteX) + (uniteX / 2)+uniteX/3, test.y + test.y - test.y / 4 + (5 * uniteY)+uniteY/3, uniteX/2, uniteY/2, null);

              break;
            case 3:
              g.drawImage(image, (test.x + (5 * uniteX)) - (uniteX) + (uniteX / 2), test.y + test.y - test.y / 4 + (5 * uniteY), uniteX, uniteY, null);
                if(verifselection(etat,i,j) == true)                                      g.drawImage(imagetap, (test.x + (5 * uniteX)) - (uniteX) + (uniteX / 2)+uniteX/3, test.y + test.y - test.y / 4 + (5 * uniteY)+uniteY/3, uniteX/2, uniteY/2, null);

              break;
            case 4:
              g.drawImage(image, (test.x + (6 * uniteX)) - (uniteX) + (uniteX / 2), test.y + test.y - test.y / 4 + (5 * uniteY), uniteX, uniteY, null);
                  if(verifselection(etat,i,j) == true)                                    g.drawImage(imagetap, (test.x + (6 * uniteX)) - (uniteX) + (uniteX / 2)+uniteX/3, test.y + test.y - test.y / 4 + (5 * uniteY)+uniteY/3, uniteX/2, uniteY/2, null);

              break;
            case 5:
              g.drawImage(image, (test.x + (7 * uniteX)) - (uniteX) + (uniteX / 2), test.y + test.y - test.y / 4 + (5 * uniteY), uniteX, uniteY, null);
                 if(verifselection(etat,i,j) == true)                                     g.drawImage(imagetap, (test.x + (7 * uniteX)) - (uniteX) + (uniteX / 2)+uniteX/3, test.y + test.y - test.y / 4 + (5 * uniteY)+uniteY/3, uniteX/2, uniteY/2, null);

              break;

            }
            break;
          case 4:

            switch (j) {
            case 0:
              g.drawImage(image, (test.x + (3 * uniteX)) - 2 * (uniteX) + (uniteX), 4 * test.y / 2 + (4 * uniteY), uniteX, uniteY, null);
                       if(verifselection(etat,i,j) == true)           g.drawImage(imagetap, (test.x + (3 * uniteX)) - 2 * (uniteX) + (uniteX)+uniteX/3, 4 * test.y / 2 + (4 * uniteY)+uniteY/3, uniteX/2, uniteY/2, null);

              break;
            case 1:
              g.drawImage(image, (test.x + (4 * uniteX)) - 2 * (uniteX) + (uniteX), 4 * test.y / 2 + (4 * uniteY), uniteX, uniteY, null);
                                if(verifselection(etat,i,j) == true)                      g.drawImage(imagetap, (test.x + (4 * uniteX)) - 2 * (uniteX) + (uniteX)+uniteX/3, 4 * test.y / 2 + (4 * uniteY)+uniteY/3, uniteX/2, uniteY/2, null);

              break;
            case 2:
              g.drawImage(image, (test.x + (5 * uniteX)) - 2 * (uniteX) + (uniteX), 4 * test.y / 2 + (4 * uniteY), uniteX, uniteY, null);
                                      if(verifselection(etat,i,j) == true)                g.drawImage(imagetap, (test.x + (5 * uniteX)) - 2 * (uniteX) + (uniteX)+uniteX/3, 4 * test.y / 2 + (4 * uniteY)+uniteY/3, uniteX/2, uniteY/2, null);

              break;
            case 3:
              g.drawImage(image, (test.x + (6 * uniteX)) - 2 * (uniteX) + (uniteX), 4 * test.y / 2 + (4 * uniteY), uniteX, uniteY, null);
                                           if(verifselection(etat,i,j) == true)           g.drawImage(imagetap, (test.x + (6 * uniteX)) - 2 * (uniteX) + (uniteX)+uniteX/3, 4 * test.y / 2 + (4 * uniteY)+uniteY/3, uniteX/2, uniteY/2, null);

              break;
            case 4:
              g.drawImage(image, (test.x + (7 * uniteX)) - 2 * (uniteX) + (uniteX), 4 * test.y / 2 + (4 * uniteY), uniteX, uniteY, null);
                                                 if(verifselection(etat,i,j) == true)     g.drawImage(imagetap, (test.x + (7 * uniteX)) - 2 * (uniteX) + (uniteX)+uniteX/3, 4 * test.y / 2 + (4 * uniteY)+uniteY/3, uniteX/2, uniteY/2, null);

              break;
            }
            break;
          case 5:
            switch (j) {
            case 0:
              g.drawImage(image, (test.x + (4 * uniteX)) - 2 * (uniteX) + (uniteX) - (uniteX / 2), 7 * test.y / 3 + (3 * uniteY), uniteX, uniteY, null);
             if(verifselection(etat,i,j) == true) g.drawImage(imagetap, (test.x + (4 * uniteX)) - 2 * (uniteX) + (uniteX) - (uniteX / 2)+uniteX/3, 7 * test.y / 3 + (3 * uniteY)+uniteY/3, uniteX/2, uniteY/2, null);

              break;
            case 1:
              g.drawImage(image, (test.x + (5 * uniteX)) - 2 * (uniteX) + (uniteX) - (uniteX / 2), 7 * test.y / 3 + (3 * uniteY), uniteX, uniteY, null);
                  if(verifselection(etat,i,j) == true)                g.drawImage(imagetap, (test.x + (5 * uniteX)) - 2 * (uniteX) + (uniteX) - (uniteX / 2)+uniteX/3, 7 * test.y / 3 + (3 * uniteY)+uniteY/3, uniteX/2, uniteY/2, null);

              break;
            case 2:
              g.drawImage(image, (test.x + (6 * uniteX)) - 2 * (uniteX) + (uniteX) - (uniteX / 2), 7 * test.y / 3 + (3 * uniteY), uniteX, uniteY, null);
                              if(verifselection(etat,i,j) == true)    g.drawImage(imagetap, (test.x + (6 * uniteX)) - 2 * (uniteX) + (uniteX) - (uniteX / 2)+uniteX/3, 7 * test.y / 3 + (3 * uniteY)+uniteY/3, uniteX/2, uniteY/2, null);

              break;
            case 3:
              g.drawImage(image, (test.x + (7 * uniteX)) - 2 * (uniteX) + (uniteX) - (uniteX / 2), 7 * test.y / 3 + (3 * uniteY), uniteX, uniteY, null);
                                if(verifselection(etat,i,j) == true)  g.drawImage(imagetap, (test.x + (7 * uniteX)) - 2 * (uniteX) + (uniteX) - (uniteX / 2)+uniteX/3, 7 * test.y / 3 + (3 * uniteY)+uniteY/3, uniteX/2, uniteY/2, null);

              break;

            }
            break;

          case 6:
            switch (j) {
            case 0:
              g.drawImage(image, (test.x + (5 * uniteX)) - 2 * (uniteX) + (uniteX) - 2 * (uniteX / 2), 8 * test.y / 3 + (2 * uniteY), uniteX, uniteY, null);
             if(verifselection(etat,i,j) == true) g.drawImage(imagetap, (test.x + (5 * uniteX)) - 2 * (uniteX) + (uniteX) - 2 * (uniteX / 2) +uniteX/3, 8 * test.y / 3 + (2 * uniteY)+uniteY/3, uniteX/2, uniteY/2, null);

              break;
            case 1:
              g.drawImage(image, (test.x + (6 * uniteX)) - 2 * (uniteX) + (uniteX) - 2 * (uniteX / 2), 8 * test.y / 3 + (2 * uniteY), uniteX, uniteY, null);
               if(verifselection(etat,i,j) == true)                   g.drawImage(imagetap, (test.x + (6 * uniteX)) - 2 * (uniteX) + (uniteX) - 2 * (uniteX / 2) +uniteX/3, 8 * test.y / 3 + (2 * uniteY)+uniteY/3, uniteX/2, uniteY/2, null);

              break;
            case 2:
              g.drawImage(image, (test.x + (7 * uniteX)) - 2 * (uniteX) + (uniteX) - 2 * (uniteX / 2), 8 * test.y / 3 + (2 * uniteY), uniteX, uniteY, null);
                if(verifselection(etat,i,j) == true)                                      g.drawImage(imagetap, (test.x + (7 * uniteX)) - 2 * (uniteX) + (uniteX) - 2 * (uniteX / 2) +uniteX/3, 8 * test.y / 3 + (2 * uniteY)+uniteY/3, uniteX/2, uniteY/2, null);

              break;

            }
            break;

          case 7:
            switch (j) {
            case 0:
              g.drawImage(image, (test.x + (5 * uniteX)) - 2 * (uniteX) + (uniteX) - (uniteX / 2), 9 * test.y / 3 + (1 * uniteY), uniteX, uniteY, null);
                  if(verifselection(etat,i,j) == true)                g.drawImage(imagetap, (test.x + (5 * uniteX)) - 2 * (uniteX) + (uniteX) - (uniteX / 2)+uniteX/3, 9 * test.y / 3 + (1 * uniteY)+uniteY/3, uniteX/2, uniteY/2, null);

              break;
            case 1:
              g.drawImage(image, (test.x + (6 * uniteX)) - 2 * (uniteX) + (uniteX) - (uniteX / 2), 9 * test.y / 3 + (1 * uniteY), uniteX, uniteY, null);
                         if(verifselection(etat,i,j) == true)                             g.drawImage(imagetap, (test.x + (6 * uniteX)) - 2 * (uniteX) + (uniteX) - (uniteX / 2)+uniteX/3, 9 * test.y / 3 + (1 * uniteY)+uniteY/3, uniteX/2, uniteY/2, null);

              break;

            }
            break;

          case 8:
            switch (j) {
            case 0:
              g.drawImage(image, (test.x + (5 * uniteX)) - 2 * (uniteX) + (uniteX), 10 * test.y / 3 + (0 * uniteY), uniteX, uniteY, null);
            if(verifselection(etat,i,j) == true)  g.drawImage(imagetap, (test.x + (5 * uniteX)) - 2 * (uniteX) + (uniteX)+uniteX/3, 10 * test.y / 3 + (0 * uniteY)+uniteY/3, uniteX/2, uniteY/2, null);

              break;

            }
            break;

          }
        }

      }
      break;
    }
    }
  }
  
  
  public void drawbackground(Graphics g ,javax.swing.JPanel jPanel16 ,javax.swing.JPanel jPanel13,javax.swing.JPanel jPanel12, javax.swing.JPanel jPanel5, javax.swing.JPanel jPanel9, javax.swing.JPanel jPanel6,javax.swing.JPanel jPanel7,javax.swing.JPanel jPanel8 , boolean gameover1,boolean gameover2) {
      
      BufferedImage imageback = this.galerie.imageBackground;
      g.drawImage(imageback, jPanel5.getLocationOnScreen().x, jPanel5.getLocationOnScreen().y, jPanel5.getWidth(), jPanel5.getHeight(), null);
      BufferedImage imageset = this.galerie.imageSettings;
      g.drawImage(imageset, jPanel9.getLocationOnScreen().x, jPanel9.getLocationOnScreen().y, jPanel9.getWidth(), jPanel9.getHeight(), null);
      BufferedImage imagesave = this.galerie.imageSave;
      g.drawImage(imagesave, jPanel8.getLocationOnScreen().x, jPanel8.getLocationOnScreen().y, jPanel8.getWidth(), jPanel8.getHeight(), null);
      BufferedImage imagereplay = this.galerie.imageReplay;
      g.drawImage(imagereplay, jPanel12.getLocationOnScreen().x, jPanel12.getLocationOnScreen().y, jPanel12.getWidth(), jPanel12.getHeight(), null);
      BufferedImage imageplay1 = this.galerie.imagePlayer1;
      BufferedImage imageplay2 = this.galerie.imagePlayer2;
      BufferedImage imageplay1gris = this.galerie.imagePlayer1_off;
      BufferedImage imageplay2gris = this.galerie.imagePlayer2_off;
      BufferedImage imageile = this.galerie.imageile;
                 g.drawImage(imageile, jPanel13.getLocationOnScreen().x-182, jPanel13.getLocationOnScreen().y-15, jPanel13.getWidth(), jPanel13.getHeight(), null);
                 g.drawImage(imageile, jPanel16.getLocationOnScreen().x-182, jPanel16.getLocationOnScreen().y-15, jPanel16.getWidth(), jPanel16.getHeight(), null);

                 
      if (this.partie_actuel.partieEnCours.getJoueurCourant() == 0 && gameover1 == false && gameover1 == false) {

          g.drawImage(imageplay1, jPanel6.getLocationOnScreen().x, jPanel6.getLocationOnScreen().y, jPanel6.getWidth(), jPanel6.getHeight(), null);
          g.drawImage(imageplay2gris, jPanel7.getLocationOnScreen().x, jPanel6.getLocationOnScreen().y, jPanel6.getWidth(), jPanel6.getHeight(), null);
          
      } else if (this.partie_actuel.partieEnCours.getJoueurCourant() == 1 && gameover1 == false && gameover2 == false) {
          g.drawImage(imageplay2, jPanel7.getLocationOnScreen().x, jPanel7.getLocationOnScreen().y, jPanel7.getWidth(), jPanel7.getHeight(), null);
          g.drawImage(imageplay1gris, jPanel6.getLocationOnScreen().x, jPanel7.getLocationOnScreen().y, jPanel7.getWidth(), jPanel7.getHeight(), null);
          
      }
      if (gameover1 == true) {
          BufferedImage gameoverimage = this.galerie.imageWin;
          g.drawImage(gameoverimage, jPanel7.getLocationOnScreen().x, jPanel7.getLocationOnScreen().y, jPanel7.getWidth(), jPanel7.getHeight(), null);
          
      } else if (gameover2 == true) {
          BufferedImage gameoverimage = this.galerie.imageWin;
          g.drawImage(gameoverimage, jPanel6.getLocationOnScreen().x, jPanel6.getLocationOnScreen().y, jPanel6.getWidth(), jPanel6.getHeight(), null);
          
      }
      
  }

  public void drawspecifcalcell(javax.swing.JPanel jpanel,Graphics g, int uniteX, int uniteY, Point test, int typejoueur, int i, int j) {
    switch (typejoueur) {
    case 1: {
      Position actualpos = new Position(0, 0);

      actualpos.rang = j;
      actualpos.etage = i;
      Piece c = this.partie_actuel.partieEnCours.joueur1().getCamp().getPiece(actualpos);
      BufferedImage image;
      if (c == null) {

        image = colortoimage(null);
      } else {
        image = colortoimage(c.getColor());
      }
      switch (i) {
      case 0:
        switch (j) {
        case 0:
          g.clearRect((test.x + (0 * uniteX)), test.y + (5 * uniteY), uniteX, uniteY);
          g.drawImage(image, (test.x + (0 * uniteX)), test.y + (5 * uniteY), uniteX, uniteY, null);
          break;
        case 1:
            g.clearRect((test.x + (1 * uniteX)), test.y + (5 * uniteY), uniteX, uniteY);
          g.drawImage(image, (test.x + (1 * uniteX)), test.y + (5 * uniteY), uniteX, uniteY, null);
          break;
        case 2:
            g.clearRect((test.x + (2 * uniteX)), test.y + (5 * uniteY), uniteX, uniteY);
          g.drawImage(image, (test.x + (2 * uniteX)), test.y + (5 * uniteY), uniteX, uniteY, null);
          break;
        case 3:
            g.clearRect((test.x + (3 * uniteX)), test.y + (5 * uniteY), uniteX, uniteY);
          g.drawImage(image, (test.x + (3 * uniteX)), test.y + (5 * uniteY), uniteX, uniteY, null);
          break;
        case 4:
            g.clearRect((test.x + (4 * uniteX)), test.y + (5 * uniteY), uniteX, uniteY);
          g.drawImage(image, (test.x + (4 * uniteX)), test.y + (5 * uniteY), uniteX, uniteY, null);
          break;
        case 5:
            
           //jpanel.getGraphics().clearRect((test.x + (5 * uniteX)), test.y + (5 * uniteY), uniteX, uniteY);
           
        //  g.drawImage(image, (test.x + (5 * uniteX)), test.y + (5 * uniteY), uniteX, uniteY, null);
          break;

        }
        break;

      case 1:
        switch (j) {
        case 0:
            g.clearRect( test.x + (0 * uniteX) + (uniteX / 2), test.y + test.y / 4 + (4 * uniteY), uniteX, uniteY);

          g.drawImage(image, test.x + (0 * uniteX) + (uniteX / 2), test.y + test.y / 4 + (4 * uniteY), uniteX, uniteY, null);
          break;
        case 1:
                        g.clearRect( test.x + (1 * uniteX) + (uniteX / 2), test.y + test.y / 4 + (4 * uniteY), uniteX, uniteY);

          g.drawImage(image, (test.x + (1 * uniteX)) + (uniteX / 2), test.y + test.y / 4 + (4 * uniteY), uniteX, uniteY, null);
          break;
        case 2:
                        g.clearRect( test.x + (2 * uniteX) + (uniteX / 2), test.y + test.y / 4 + (4 * uniteY), uniteX, uniteY);

          g.drawImage(image, (test.x + (2 * uniteX)) + (uniteX / 2), test.y + test.y / 4 + (4 * uniteY), uniteX, uniteY, null);
          break;
        case 3:
                        g.clearRect( test.x + (3 * uniteX) + (uniteX / 2), test.y + test.y / 4 + (4 * uniteY), uniteX, uniteY);

          g.drawImage(image, (test.x + (3 * uniteX)) + (uniteX / 2), test.y + test.y / 4 + (4 * uniteY), uniteX, uniteY, null);
          break;
        case 4:
                                    g.clearRect( test.x + (4 * uniteX) + (uniteX / 2), test.y + test.y / 4 + (4 * uniteY), uniteX, uniteY);

          g.drawImage(image, (test.x + (4 * uniteX)) + (uniteX / 2), test.y + test.y / 4 + (4 * uniteY), uniteX, uniteY, null);
          break;
        }
        break;

      case 2:
        switch (j) {
        case 0:
                    g.clearRect((test.x + (1 * uniteX)), test.y + test.y / 2 + (3 * uniteY), uniteX, uniteY);

          g.drawImage(image, (test.x + (1 * uniteX)), test.y + test.y / 2 + (3 * uniteY), uniteX, uniteY, null);
          break;
        case 1:
                                g.clearRect((test.x + (2 * uniteX)), test.y + test.y / 2 + (3 * uniteY), uniteX, uniteY);

          g.drawImage(image, (test.x + (2 * uniteX)), test.y + test.y / 2 + (3 * uniteY), uniteX, uniteY, null);
          break;
        case 2:
                                g.clearRect((test.x + (3 * uniteX)), test.y + test.y / 2 + (3 * uniteY), uniteX, uniteY);

          g.drawImage(image, (test.x + (3 * uniteX)), test.y + test.y / 2 + (3 * uniteY), uniteX, uniteY, null);
          break;
        case 3:
                                            g.clearRect((test.x + (4 * uniteX)), test.y + test.y / 2 + (3 * uniteY), uniteX, uniteY);

          g.drawImage(image, (test.x + (4 * uniteX)), test.y + test.y / 2 + (3 * uniteY), uniteX, uniteY, null);
          break;
        }
        break;
      case 3:
        switch (j) {
        case 0:
          g.clearRect((test.x + (2 * uniteX)) - (uniteX) + (uniteX / 2), test.y + test.y / 2 + test.y / 3 + (2 * uniteY), uniteX, uniteY);

          g.drawImage(image, (test.x + (2 * uniteX)) - (uniteX) + (uniteX / 2), test.y + test.y / 2 + test.y / 3 + (2 * uniteY), uniteX, uniteY, null);
          break;
        case 1:
                      g.clearRect((test.x + (3 * uniteX)) - (uniteX) + (uniteX / 2), test.y + test.y / 2 + test.y / 3 + (2 * uniteY), uniteX, uniteY);

          g.drawImage(image, (test.x + (3 * uniteX)) - (uniteX) + (uniteX / 2), test.y + test.y / 2 + test.y / 3 + (2 * uniteY), uniteX, uniteY, null);
          break;
        case 2:
                      g.clearRect((test.x + (4 * uniteX)) - (uniteX) + (uniteX / 2), test.y + test.y / 2 + test.y / 3 + (2 * uniteY), uniteX, uniteY);

          g.drawImage(image, (test.x + (4 * uniteX)) - (uniteX) + (uniteX / 2), test.y + test.y / 2 + test.y / 3 + (2 * uniteY), uniteX, uniteY, null);
          break;
        }
        break;
      case 4:

        switch (j) {
        case 0:
         g.clearRect( (test.x + (2 * uniteX)), 2 * test.y + test.y / 4 + (1 * uniteY), uniteX, uniteY);

          g.drawImage(image, (test.x + (2 * uniteX)), 2 * test.y + test.y / 4 + (1 * uniteY), uniteX, uniteY, null);
          break;
        case 1:
                     g.clearRect( (test.x + (i * uniteX)), 2 * test.y + test.y / 4 + (1 * uniteY), uniteX, uniteY);

          g.drawImage(image, (test.x + (i * uniteX)) - (uniteX), 2 * test.y + test.y / 4 + (j * uniteY), uniteX, uniteY, null);
          break;
        }
        break;
      case 5:
        switch (j) {
        case 0:
            
                    
           g.clearRect((test.x + (2 * uniteX)) + (uniteX / 2), 2 * test.y + test.y / 2 + (0 * uniteY), uniteX, uniteY);
//jpanel.getGraphics()..clearRect( (test.x + (2 * uniteX)) + (uniteX / 2), 2 * test.y + test.y / 2 + (0 * uniteY), uniteX, uniteY);
          g.drawImage(image, (test.x + (2 * uniteX)) + (uniteX / 2), 2 * test.y + test.y / 2 + (0 * uniteY), uniteX, uniteY, null);
          break;
        }
        break;

      }

      break;
    }
    case 2: {
      Position actualpos = new Position(0, 0);

      actualpos.rang = j;
      actualpos.etage = i;
      Piece c = this.partie_actuel.partieEnCours.joueur2().getCamp().getPiece(actualpos);
      BufferedImage image;
      if (c == null) {

        image = colortoimage(null);
      } else {
        image = colortoimage(c.getColor());
      }

      switch (i) {
      case 0:
        switch (j) {
        case 0:
          g.drawImage(image, (test.x + (0 * uniteX)), test.y + (5 * uniteY), uniteX, uniteY, null);
          break;
        case 1:
          g.drawImage(image, (test.x + (1 * uniteX)), test.y + (5 * uniteY), uniteX, uniteY, null);
          break;
        case 2:
          g.drawImage(image, (test.x + (2 * uniteX)), test.y + (5 * uniteY), uniteX, uniteY, null);
          break;
        case 3:
          g.drawImage(image, (test.x + (3 * uniteX)), test.y + (5 * uniteY), uniteX, uniteY, null);
          break;
        case 4:
          g.drawImage(image, (test.x + (4 * uniteX)), test.y + (5 * uniteY), uniteX, uniteY, null);
          break;
        case 5:
          g.drawImage(image, (test.x + (5 * uniteX)), test.y + (5 * uniteY), uniteX, uniteY, null);
          break;
        }
        break;

      case 1:
        switch (j) {
        case 0:
          g.drawImage(image, test.x + (0 * uniteX) + (uniteX / 2), test.y + test.y / 4 + (4 * uniteY), uniteX, uniteY, null);
          break;
        case 1:
          g.drawImage(image, (test.x + (1 * uniteX)) + (uniteX / 2), test.y + test.y / 4 + (4 * uniteY), uniteX, uniteY, null);
          break;
        case 2:
          g.drawImage(image, (test.x + (2 * uniteX)) + (uniteX / 2), test.y + test.y / 4 + (4 * uniteY), uniteX, uniteY, null);
          break;
        case 3:
          g.drawImage(image, (test.x + (3 * uniteX)) + (uniteX / 2), test.y + test.y / 4 + (4 * uniteY), uniteX, uniteY, null);
          break;
        case 4:
          g.drawImage(image, (test.x + (4 * uniteX)) + (uniteX / 2), test.y + test.y / 4 + (4 * uniteY), uniteX, uniteY, null);
          break;
        }
        break;

      case 2:
        switch (j) {
        case 0:
          g.drawImage(image, (test.x + (1 * uniteX)), test.y + test.y / 2 + (3 * uniteY), uniteX, uniteY, null);
          break;
        case 1:
          g.drawImage(image, (test.x + (2 * uniteX)), test.y + test.y / 2 + (3 * uniteY), uniteX, uniteY, null);
          break;
        case 2:
          g.drawImage(image, (test.x + (3 * uniteX)), test.y + test.y / 2 + (3 * uniteY), uniteX, uniteY, null);
          break;
        case 3:
          g.drawImage(image, (test.x + (4 * uniteX)), test.y + test.y / 2 + (3 * uniteY), uniteX, uniteY, null);
          break;
        }
        break;
      case 3:
        switch (j) {
        case 0:
          g.drawImage(image, (test.x + (2 * uniteX)) - (uniteX) + (uniteX / 2), test.y + test.y / 2 + test.y / 3 + (2 * uniteY), uniteX, uniteY, null);
          break;
        case 1:
          g.drawImage(image, (test.x + (3 * uniteX)) - (uniteX) + (uniteX / 2), test.y + test.y / 2 + test.y / 3 + (2 * uniteY), uniteX, uniteY, null);
          break;
        case 2:
          g.drawImage(image, (test.x + (4 * uniteX)) - (uniteX) + (uniteX / 2), test.y + test.y / 2 + test.y / 3 + (2 * uniteY), uniteX, uniteY, null);
          break;
        }
        break;
      case 4:

        switch (j) {
        case 0:
          g.drawImage(image, (test.x + (2 * uniteX)), 2 * test.y + test.y / 4 + (1 * uniteY), uniteX, uniteY, null);
          break;
        case 1:
          g.drawImage(image, (test.x + (i * uniteX)) - (uniteX), 2 * test.y + test.y / 4 + (j * uniteY), uniteX, uniteY, null);
          break;
        }
        break;
      case 5:
        switch (j) {
        case 0:
          g.drawImage(image, (test.x + (2 * uniteX)) + (uniteX / 2), 2 * test.y + test.y / 2 + (0 * uniteY), uniteX, uniteY, null);
          break;
        }
        break;

      }

      break;
    }
    default: {
      Position actualpos = new Position(0, 0);
      actualpos.rang = j;
      actualpos.etage = i;
      Piece c = this.partie_actuel.partieEnCours.getBaseMontagne().getPiece(actualpos);
      BufferedImage image;
      if (c == null) {

        image = colortoimage(null);
      } else {
        image = colortoimage(c.getColor());
      }
      switch (i) {
      case 0:
        switch (j) {
        case 0:
          g.drawImage(image, (test.x + (0 * uniteX)), test.y + (8 * uniteY), uniteX, uniteY, null);
          break;
        case 1:
          g.drawImage(image, (test.x + (1 * uniteX)), test.y + (8 * uniteY), uniteX, uniteY, null);
          break;
        case 2:
          g.drawImage(image, (test.x + (2 * uniteX)), test.y + (8 * uniteY), uniteX, uniteY, null);
          break;
        case 3:
          g.drawImage(image, (test.x + (3 * uniteX)), test.y + (8 * uniteY), uniteX, uniteY, null);
          break;
        case 4:
          g.drawImage(image, (test.x + (4 * uniteX)), test.y + (8 * uniteY), uniteX, uniteY, null);
          break;
        case 5:
          g.drawImage(image, (test.x + (5 * uniteX)), test.y + (8 * uniteY), uniteX, uniteY, null);
          break;
        case 6:
          g.drawImage(image, (test.x + (6 * uniteX)), test.y + (8 * uniteY), uniteX, uniteY, null);
          break;
        case 7:
          g.drawImage(image, (test.x + (7 * uniteX)), test.y + (8 * uniteY), uniteX, uniteY, null);
          break;
        case 8:
          g.drawImage(image, (test.x + (8 * uniteX)), test.y + (8 * uniteY), uniteX, uniteY, null);
          break;
        }
        break;

      case 1:
        switch (j) {
        case 0:
          g.drawImage(image, test.x + (0 * uniteX) + (uniteX / 2), test.y + test.y / 5 + (7 * uniteY), uniteX, uniteY, null);
          break;
        case 1:
          g.drawImage(image, (test.x + (1 * uniteX)) + (uniteX / 2), test.y + test.y / 5 + (7 * uniteY), uniteX, uniteY, null);
          break;
        case 2:
          g.drawImage(image, (test.x + (2 * uniteX)) + (uniteX / 2), test.y + test.y / 5 + (7 * uniteY), uniteX, uniteY, null);
          break;
        case 3:
          g.drawImage(image, (test.x + (3 * uniteX)) + (uniteX / 2), test.y + test.y / 5 + (7 * uniteY), uniteX, uniteY, null);
          break;
        case 4:
          g.drawImage(image, (test.x + (4 * uniteX)) + (uniteX / 2), test.y + test.y / 5 + (7 * uniteY), uniteX, uniteY, null);
          break;
        case 5:
          g.drawImage(image, (test.x + (5 * uniteX)) + (uniteX / 2), test.y + test.y / 5 + (7 * uniteY), uniteX, uniteY, null);
          break;
        case 6:
          g.drawImage(image, (test.x + (6 * uniteX)) + (uniteX / 2), test.y + test.y / 5 + (7 * uniteY), uniteX, uniteY, null);
          break;
        case 7:
          g.drawImage(image, (test.x + (7 * uniteX)) + (uniteX / 2), test.y + test.y / 5 + (7 * uniteY), uniteX, uniteY, null);
          break;
        }
        break;

      case 2:
        switch (j) {
        case 0:
          g.drawImage(image, (test.x + (1 * uniteX)), test.y + test.y / 2 + (6 * uniteY), uniteX, uniteY, null);
          break;
        case 1:
          g.drawImage(image, (test.x + (2 * uniteX)), test.y + test.y / 2 + (6 * uniteY), uniteX, uniteY, null);
          break;
        case 2:
          g.drawImage(image, (test.x + (3 * uniteX)), test.y + test.y / 2 + (6 * uniteY), uniteX, uniteY, null);
          break;
        case 3:
          g.drawImage(image, (test.x + (4 * uniteX)), test.y + test.y / 2 + (6 * uniteY), uniteX, uniteY, null);
          break;
        case 4:
          g.drawImage(image, (test.x + (5 * uniteX)), test.y + test.y / 2 + (6 * uniteY), uniteX, uniteY, null);
          break;
        case 5:
          g.drawImage(image, (test.x + (6 * uniteX)), test.y + test.y / 2 + (6 * uniteY), uniteX, uniteY, null);
          break;
        case 6:
          g.drawImage(image, (test.x + (7 * uniteX)), test.y + test.y / 2 + (6 * uniteY), uniteX, uniteY, null);
          break;
        }
        break;
      case 3:
        switch (j) {
        case 0:
          g.drawImage(image, (test.x + (2 * uniteX)) - (uniteX) + (uniteX / 2), test.y + test.y - test.y / 4 + (5 * uniteY), uniteX, uniteY, null);
          break;
        case 1:
          g.drawImage(image, (test.x + (3 * uniteX)) - (uniteX) + (uniteX / 2), test.y + test.y - test.y / 4 + (5 * uniteY), uniteX, uniteY, null);
          break;
        case 2:
          g.drawImage(image, (test.x + (4 * uniteX)) - (uniteX) + (uniteX / 2), test.y + test.y - test.y / 4 + (5 * uniteY), uniteX, uniteY, null);
          break;
        case 3:
          g.drawImage(image, (test.x + (5 * uniteX)) - (uniteX) + (uniteX / 2), test.y + test.y - test.y / 4 + (5 * uniteY), uniteX, uniteY, null);
          break;
        case 4:
          g.drawImage(image, (test.x + (6 * uniteX)) - (uniteX) + (uniteX / 2), test.y + test.y - test.y / 4 + (5 * uniteY), uniteX, uniteY, null);
          break;
        case 5:
          g.drawImage(image, (test.x + (7 * uniteX)) - (uniteX) + (uniteX / 2), test.y + test.y - test.y / 4 + (5 * uniteY), uniteX, uniteY, null);
          break;

        }
        break;
      case 4:

        switch (j) {
        case 0:
          g.drawImage(image, (test.x + (3 * uniteX)) - 2 * (uniteX) + (uniteX), 4 * test.y / 2 + (4 * uniteY), uniteX, uniteY, null);
          break;
        case 1:
          g.drawImage(image, (test.x + (4 * uniteX)) - 2 * (uniteX) + (uniteX), 4 * test.y / 2 + (4 * uniteY), uniteX, uniteY, null);
          break;
        case 2:
          g.drawImage(image, (test.x + (5 * uniteX)) - 2 * (uniteX) + (uniteX), 4 * test.y / 2 + (4 * uniteY), uniteX, uniteY, null);
          break;
        case 3:
          g.drawImage(image, (test.x + (6 * uniteX)) - 2 * (uniteX) + (uniteX), 4 * test.y / 2 + (4 * uniteY), uniteX, uniteY, null);
          break;
        case 4:
          g.drawImage(image, (test.x + (7 * uniteX)) - 2 * (uniteX) + (uniteX), 4 * test.y / 2 + (4 * uniteY), uniteX, uniteY, null);
          break;
        }
        break;
      case 5:
        switch (j) {
        case 0:
          g.drawImage(image, (test.x + (4 * uniteX)) - 2 * (uniteX) + (uniteX) - (uniteX / 2), 7 * test.y / 3 + (3 * uniteY), uniteX, uniteY, null);
          break;
        case 1:
          g.drawImage(image, (test.x + (5 * uniteX)) - 2 * (uniteX) + (uniteX) - (uniteX / 2), 7 * test.y / 3 + (3 * uniteY), uniteX, uniteY, null);
          break;
        case 2:
          g.drawImage(image, (test.x + (6 * uniteX)) - 2 * (uniteX) + (uniteX) - (uniteX / 2), 7 * test.y / 3 + (3 * uniteY), uniteX, uniteY, null);
          break;
        case 3:
          g.drawImage(image, (test.x + (7 * uniteX)) - 2 * (uniteX) + (uniteX) - (uniteX / 2), 7 * test.y / 3 + (3 * uniteY), uniteX, uniteY, null);
          break;

        }
        break;

      case 6:
        switch (j) {
        case 0:
          g.drawImage(image, (test.x + (5 * uniteX)) - 2 * (uniteX) + (uniteX) - 2 * (uniteX / 2), 8 * test.y / 3 + (2 * uniteY), uniteX, uniteY, null);
          break;
        case 1:
          g.drawImage(image, (test.x + (6 * uniteX)) - 2 * (uniteX) + (uniteX) - 2 * (uniteX / 2), 8 * test.y / 3 + (2 * uniteY), uniteX, uniteY, null);
          break;
        case 2:
          g.drawImage(image, (test.x + (7 * uniteX)) - 2 * (uniteX) + (uniteX) - 2 * (uniteX / 2), 8 * test.y / 3 + (2 * uniteY), uniteX, uniteY, null);
          break;

        }
        break;

      case 7:
        switch (j) {
        case 0:
          g.drawImage(image, (test.x + (5 * uniteX)) - 2 * (uniteX) + (uniteX) - (uniteX / 2), 9 * test.y / 3 + (1 * uniteY), uniteX, uniteY, null);
          break;
        case 1:
          g.drawImage(image, (test.x + (6 * uniteX)) - 2 * (uniteX) + (uniteX) - (uniteX / 2), 9 * test.y / 3 + (1 * uniteY), uniteX, uniteY, null);
          break;

        }
        break;

      case 8:
        switch (j) {
        case 0:
          g.drawImage(image, (test.x + (5 * uniteX)) - 2 * (uniteX) + (uniteX), 10 * test.y / 3 + (0 * uniteY), uniteX, uniteY, null);
          break;

        }
        break;

      }

      break;
    }
    }
  }
  
  
  
  public boolean verifselection(SuivieEtat etat,int i,int j) {
     result = false;
      etat.baseselection.forEach((n) -> {
          System.out.println(n.etage+" =>"+i);
          System.out.println(n.rang+" =>"+j);
          if(n.etage == i && n.rang == j) {
              
             result = true;
          
      }
      });
      
      return result;
  }
  
   public boolean verifselection2(int joueur,SuivieEtat etat,int i,int j) {
     if(joueur != this.partie_actuel.partieEnCours.joueurCourant) {
         return false;
     }
     if (etat.selectedpieceetage != i || etat.selectedpiececolonne != j) {
   } else {
         return true;
      }
      return false;
      
  }
  
  
  
   static boolean result = false;
  
}