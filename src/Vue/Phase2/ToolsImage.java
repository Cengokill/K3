/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vue.Phase2;

import Controleur.Jeu;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Said
 */
public class ToolsImage {
    String Path = "C:\\Users\\mathi\\eclipse-workspace\\K3\\bin\\Ressources/";
    File FileBLUE = new File(Path + "BLUE" + ".png");    
    File FileGREEN = new File(Path + "GREEN" + ".png");
    File FileBLACK = new File(Path + "BLACK" + ".png");  
    File FileYELLOW = new File(Path + "YELLOW" + ".png");
    File FileRED = new File(Path + "RED" + ".png");
    File FileWOOD = new File(Path + "NATURAL" + ".png");
    File FileWHITE = new File(Path + "WHITE" + ".png");
    File FileEMPTY_WHITE = new File(Path + "EMPTY_WHITE" + ".png");
    File FileBackground = new File(Path + "28" + ".jpg");
    File FileSettings = new File(Path + "settings" + ".png");
    File FileSave = new File(Path + "save" + ".png");
    File FileReplay = new File(Path + "re" + ".png");
    File FilePlayer1 = new File(Path + "joueur_1" + ".png");
    File FilePlayer2 = new File(Path + "joueur_2" + ".png");
    File FilePlayer1_off = new File(Path + "joueur1_gris" + ".png");
    File FilePlayer2_off = new File(Path + "joueur2_gris" + ".png");
    File FileWin = new File(Path + "win" + ".png");
    File Filehand = new File(Path + "tap" + ".png");
    File Fileile = new File(Path + "ile" + ".png");
    File Fileile2 = new File(Path + "ile2" + ".png");
    File Fileile3 = new File(Path + "ile3" + ".png");
    File Filevol = new File(Path + "espacevol" + ".png");
    
    File FileCoupPrecedent = new File(Path + "coup_precedent_flou" + ".png");
    File FileCoupPrecedent_gris = new File(Path + "coup_precedent_gris" + ".png");
    File FilePassertour = new File(Path + "passer_son_tour_flou" + ".png");
    File FilePassertour_gris = new File(Path + "passer_son_tour_gris" + ".png");
    
    public BufferedImage imageBLUE;
    public BufferedImage imageGREEN;
    public BufferedImage imageBLACK;
    public BufferedImage imageYELLOW;
    public BufferedImage imageRED;
    public BufferedImage imageWOOD;
    public BufferedImage imageWHITE;
    public BufferedImage imageEMPTY_WHITE;
    public BufferedImage imageBackground;
    public BufferedImage imageSettings;
    public BufferedImage imageSave;
    public BufferedImage imageReplay;
    public BufferedImage imagePlayer1;
    public BufferedImage imagePlayer2;
    public BufferedImage imagePlayer1_off;
    public BufferedImage imagePlayer2_off;
    public BufferedImage imageWin;
    public BufferedImage imagehand;
    public BufferedImage imageile;
    public BufferedImage imageile2;
    public BufferedImage imageile3;
    public BufferedImage imagevol;
     public BufferedImage imageeCoupPrecedent;
     public BufferedImage imageCoupPrecedent_gris;
     public BufferedImage imagePassertour ;
     public BufferedImage imagePassertour_gris ;
    
      public ToolsImage() {
        try {
            this.imageBLUE = ImageIO.read(FileBLUE);
            this.imageGREEN = ImageIO.read(FileGREEN);
            this.imageBLACK = ImageIO.read(FileBLACK);
            this.imageYELLOW = ImageIO.read(FileYELLOW);
            this.imageRED = ImageIO.read(FileRED);
            this.imageWOOD = ImageIO.read(FileWOOD);
            this.imageWHITE = ImageIO.read(FileWHITE);
            this.imageEMPTY_WHITE = ImageIO.read(FileEMPTY_WHITE);
            this.imageBackground = ImageIO.read(FileBackground);
            this.imageSettings = ImageIO.read(FileSettings);
            this.imageSave = ImageIO.read(FileSave);
            this.imageReplay = ImageIO.read(FileReplay);
            this.imagePlayer1 = ImageIO.read(FilePlayer1);
            this.imagePlayer2 = ImageIO.read(FilePlayer2);
            this.imagevol = ImageIO.read(Filevol);
            
            this.imagePlayer1_off = ImageIO.read(FilePlayer1_off);
            this.imagePlayer2_off = ImageIO.read(FilePlayer2_off);
            this.imageWin = ImageIO.read(FileWin);
            this.imagehand = ImageIO.read(Filehand);
            this.imageile = ImageIO.read(Fileile);
            this.imageile2 = ImageIO.read(Fileile2);
            this.imageile3 = ImageIO.read(Fileile3);
            
            this.imageeCoupPrecedent = ImageIO.read(FileCoupPrecedent);
            this.imageCoupPrecedent_gris= ImageIO.read(FileCoupPrecedent_gris);
            this.imagePassertour = ImageIO.read(FilePassertour);
            this.imagePassertour_gris = ImageIO.read(FilePassertour_gris) ;
        }
        catch (IOException e) {
            e.printStackTrace();
        }
     }
}


 