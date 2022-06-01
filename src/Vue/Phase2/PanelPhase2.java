package Vue.Phase2;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Font;

import Controleur.Jeu;
import Modeles.*;
import Vue.PanelGeneral;
import Vue.Menu.Chargement;
import Vue.TexturePack.LoadTexture;

public class PanelPhase2 extends PanelGeneral {

  // PARAMETRES JEU
  public Partie partieEnCours;
  public boolean partieEnCoursSet = false;
  public Jeu jeu;
  private JFrame window;
  public Chargement chargement;
  // drag N DROP
  public int OldX = 0;
  public int OldY = 0;
  public int currentX = 0;
  public int currentY = 0;
  private PiecePyramide pieceSelectionnee;
  // AFFICHAGE FIXE
  public int coupure = 5;
  public int posXPasserTour, posYPasserTour, largeurPasserTour, hauteurPasserTour, posXCoupPrecedent;
  public int largeur_ileJ, hauteur_ileJ, posX_ileJ1, posY_ileJ1, posX_ileJ2, posY_ileJ2;
  public int largeur_ileM, hauteur_ileM, posX_ileM, posY_ileM, posX_campM, posY_campM;
  public int posX_campJ1,posY_campJ1,posX_campJ2,posY_campJ2;
  public int largeur_piece, hauteur_piece, posY_depart;
  public int largeur_vol, hauteur_vol, posX_volJ1, posY_volJ1, posX_volJ2, posY_volJ2;
  public int posX_sauvegarder, posY_sauvegarder, largeur_sauvegarder, hauteur_sauvegarder;
  public int posX_settings, posY_settings, largeur_settings;
  public int largeur_cadre, hauteur_cadre, posX_cadreJ1, posY_cadreJ1, posX_cadreJ2, posY_cadreJ2;
  public int taille_police_nom_joueur, posX_nom_joueur1, posY_nom_joueur1, posX_nom_joueur2, posY_nom_joueur2;
  public int largeur_popup, hauteur_popup, posX_popup, posY_popup, largeur_popup_save, hauteur_popup_save,
      posX_popup_save, posY_popup_save;
  public int largeur_valider, hauteur_valider, largeur_fermer, posX_valider, posY_valider, posX_fermer, posY_fermer;
  public int posX_jtext, posY_jtext, largeur_jtext, hauteur_jtext;
  public int posX_back, posY_back, largeur_back, hauteur_back;
  public int posX_victoire, posY_victoire, largeur_victoire, hauteur_victoire, posX_cadre_victoire, posY_cadre_victoire;
  public int posX_jtext2, posY_jtext2, largeur_jtext2, hauteur_jtext2;
  public int largeur_degrade, hauteur_degade, posX_degrade, posY_degrade;
  public int posX_piece_voleeJ1, posY_piece_voleeJ1, posX_piece_voleeJ2, posY_piece_voleeJ2;
  boolean popup = false;
  boolean popup_save = false;
  public JTextField nomSave, joueurVictorieux;

  // TEXTURES IMPORTEES
  public LoadTexture textures;
  // SON
  private SoundPlayer simpleSoundPlayerSon;

  // CONSTRUCTEUR----------------------------------------------
  public PanelPhase2(JFrame w, Jeu j, LoadTexture t, OptionsJeu o, Chargement c) {
    super(w, t, o);
    this.textures = t;
    this.jeu = j;
    this.simpleSoundPlayerSon = new SoundPlayer(8, textures.CHEMIN);
    this.window = w;
    this.chargement = c;

    addMouseListener(new Phase2Click(this));
    setLayout(null);
    nomSave = new JTextField();
    this.add(nomSave);
  }

  public void setPartieEnCours(Partie p) {
    this.partieEnCours = p;
    this.partieEnCoursSet = true;
  }

  // FONCTION POUR AFFICHER TOUT LES ELEMENTS
  // VISUELS----------------------------------------
  public void paint(Graphics g) {
    if (this.partieEnCoursSet == true) {
      changementTaillefenetre();
      affichageBackGround(g, 1);
      drawIle(g);
      affichePyramideJoueurJ1(g);
      affichePyramideJoueurJ2(g);
      drawPiecesVoleesJ1(g);
      drawPiecesVoleesJ2(g);
      affichePyramideMontagne(g);
      drawBoutons(g);
      afficherNomJoueur(g);
      afficheBoutonBack(g);
      affichePopupSave(g);
      dragNdrop(g);
      drawVictoire(g);
    }
  }

  // CALCUL ***************************************************************

  // FONCTION POUR REDIMENTIONNER LES
  // ELEMENTS----------------------------------------------
  public void changementTaillefenetre() {
    setChangementTaillefenetre();
    // Passer son tour
    double rapportPasserTour = 0.3001776198934281;// 169/563
    largeurPasserTour = Math.min(largeur_background / 8, frameWidth / 8);
    hauteurPasserTour = (int) (largeurPasserTour * rapportPasserTour);
    int espacement = largeurPasserTour / 4;
    posXPasserTour = posX_background + largeur_background / 2 - largeurPasserTour - espacement / 2;
    posYPasserTour = posY_background + (int) (hauteur_background * 0.8);
    // Coup precedent
    posXCoupPrecedent = posXPasserTour + largeurPasserTour + espacement;
    // Sauvegarder
    double rapportSauvegarder = 0.3498964803312629; // 169/483
    hauteur_sauvegarder = (int) (hauteurPasserTour * 0.7);
    largeur_sauvegarder = (int) (hauteur_sauvegarder / rapportSauvegarder);
    posX_sauvegarder = posX_background + (int) (largeur_background * 0.88);
    posY_sauvegarder = posY_background + (int) (hauteur_background * 0.92);
    // Iles joueurs
    double rapportIlesJoueurs = 0.9269340974212034;// 647/698
    largeur_ileJ = Math.min((int) (largeur_background / 3.6), (int) (frameWidth / 3.6));
    hauteur_ileJ = (int) (largeur_ileJ * rapportIlesJoueurs);
    posX_ileJ1 = posX_background + (int)(largeur_background * 0.02);
    posY_ileJ1 = posY_background + (int)(hauteur_background * 0.52);
    posX_campJ1 = posX_ileJ1+(int)(largeur_ileJ*0.23);
    posY_campJ1 = posY_ileJ1 - (int)(hauteur_ileJ * 0.5);
    
    posX_ileJ2 = posX_background + (int)(largeur_background * 0.98) - largeur_ileJ;
    posY_ileJ2 = posY_ileJ1;
    posX_campJ2 = posX_ileJ2+(int)(largeur_ileJ*0.17);
    posY_campJ2 = posY_campJ1;
    posY_depart = posY_ileJ1 - (int)(hauteur_ileJ * 0.04);
    
    // Ile montagne
    double rapportIleMontagne = 0.7764198418404026;// 1080/1391
    largeur_ileM = Math.min((int)(largeur_background / 2.5), (int)(frameWidth / 2.5));
    hauteur_ileM = (int) (largeur_ileM * rapportIleMontagne);
    posX_ileM = posX_background + largeur_background / 2 - largeur_ileM / 2;
    posY_ileM = posY_ileJ1-(int)(hauteur_ileJ*0.25);
    // Camp montagne
    posX_campM = posX_ileM+(int)(largeur_ileM*0.17);
    posY_campM = posY_campJ1 - hauteur_piece*3;
    // Camp joueur
    double rapportPiece = 0.8576709796672828;// 464/541
    largeur_piece = Math.min((int)(largeur_background / 35), (int)(frameWidth / 35));
    hauteur_piece = (int) (largeur_piece * rapportPiece);
    // Cadres joueurs
    double rapportCadre = 0.2200647249190939;// 204/927
    largeur_cadre = Math.min(largeur_background / 6, frameWidth / 6);
    hauteur_cadre = (int) (largeur_cadre * rapportCadre);
    posX_cadreJ1 = posX_background + (int) (largeur_background * 0.08);
    posY_cadreJ1 = posY_background + (int) (hauteur_background * 0.61);
    posX_cadreJ2 = posX_background + (int) (largeur_background * 0.755);
    posY_cadreJ2 = posY_cadreJ1;
    // noms joueurs
    taille_police_nom_joueur = (int) (hauteur_cadre / 2.5);
    posX_nom_joueur1 = posX_cadreJ1 + (int) (largeur_cadre * 0.07);
    posY_nom_joueur1 = posY_cadreJ1 + (int) (hauteur_cadre * 0.64);
    posX_nom_joueur2 = posX_cadreJ2 + (int) (largeur_cadre * 0.07);
    posY_nom_joueur2 = posY_nom_joueur1;
    // Vols
    double rapportVol = 0.8104738154613466;// 975/1203
    largeur_vol = largeur_piece * 6;
    hauteur_vol = (int) (largeur_vol * rapportVol);
    posX_volJ1 = posX_ileJ1 + largeur_ileJ / 2 - largeur_vol / 2 + (int) (largeur_piece * 0.4);
    posY_volJ1 = posY_background + hauteur_background / 24;
    posX_volJ2 = posX_ileJ2 + largeur_ileJ / 2 - largeur_vol / 2 - (int) (largeur_piece * 0.2);
    posY_volJ2 = posY_volJ1;
    // settings
    largeur_settings = largeur_background / 18;
    posX_settings = posX_background + (int) (largeur_background * 0.92);
    posY_settings = posY_background + (int) (hauteur_background * 0.04);
    // popup sauvegarde
    double rapportPopupSave = 1.169139465875371;
    largeur_popup_save = (int) (largeur_background / 4);
    hauteur_popup_save = (int) (largeur_popup_save / rapportPopupSave);
    posX_popup_save = posX_background + largeur_background / 2 - largeur_popup_save / 2;
    posY_popup_save = posY_background + hauteur_background / 2 - hauteur_popup_save / 2;
    // valider et fermer
    double rapportValider = 0.5950704225352113;
    largeur_valider = largeur_popup_save / 3;
    hauteur_valider = (int) (largeur_valider * rapportValider);
    largeur_fermer = hauteur_valider;
    posX_valider = posX_popup_save + (int) (largeur_popup_save * 0.1);
    posY_valider = posY_popup_save + (int) (largeur_popup_save * 0.55);
    posX_fermer = posX_valider + largeur_valider * 2;
    posY_fermer = posY_valider;
    // jtext
    Font text1 = new Font("Dialog", Font.BOLD, (int) (taille_police_nom_joueur * 0.8));
    posX_jtext = posX_valider;
    posY_jtext = posY_popup_save + (int) (hauteur_popup_save * 0.2);
    largeur_jtext = (int) (largeur_popup_save * 0.79);
    hauteur_jtext = hauteur_valider;
    nomSave.setBounds(posX_jtext, posY_jtext, largeur_jtext, hauteur_jtext);
    nomSave.setFont(text1);
    nomSave.setVisible(popup_save);
    // Bouton retour
    double rapportBack = 0.8441247002398082;// 352/417
    largeur_back = Math.min(largeur_background / 15, frameWidth / 15);
    hauteur_back = (int) (largeur_back * rapportBack);
    posX_back = posX_background + (int) (largeur_background * 0.9);
    posY_back = posY_background + (int) (hauteur_background * 0.8);
    // Victoire
    double rapportVictoire = 0.2055030094582975;// 239/1163
    largeur_victoire = Math.min(largeur_background / 3, frameWidth / 3);
    hauteur_victoire = (int) (largeur_victoire * rapportVictoire);
    posX_victoire = posX_background + largeur_background / 2 - largeur_victoire / 2;
    posY_victoire = posY_background + (int) (hauteur_background * 0.05);
    // cadre fond joueur victorieux
    posX_cadre_victoire = posX_victoire + largeur_victoire / 2 - largeur_cadre / 2;
    posY_cadre_victoire = posY_victoire + (int) (hauteur_victoire * 1.5);
    // nom du gagnant
    posX_jtext2 = posX_cadre_victoire + (int) (largeur_cadre * 0.12);
    posY_jtext2 = posY_cadre_victoire + (int) (hauteur_cadre * 0.6);
    // fond degrade noir
    largeur_degrade = largeur_victoire * 2;
    hauteur_degade = hauteur_background;
    posX_degrade = posX_background + largeur_background / 2 - largeur_degrade / 2;
    posY_degrade = posY_background;
    // Pieces volees
    posX_piece_voleeJ1 = posX_volJ1 + (int) (largeur_vol * 0.11);
    posY_piece_voleeJ1 = posY_volJ1 + (int) (hauteur_vol * 0.41);
    posX_piece_voleeJ2 = posX_volJ2 + (int) (largeur_vol * 0.11);
    posY_piece_voleeJ2 = posY_volJ2 + (int) (hauteur_vol * 0.41);
  }

  public void afficheBoutonBack(Graphics g) {
    g.drawImage(texture.TutoMenu, posX_back, posY_back, largeur_back, hauteur_back, null);
  }

  public void affichePopupSave(Graphics g) {
    if (popup_save) {
      g.drawImage(texture.popup_save, posX_popup_save, posY_popup_save, largeur_popup_save, hauteur_popup_save, null);
      g.drawImage(texture.valider, posX_valider, posY_valider, largeur_valider, hauteur_valider, null);
      g.drawImage(texture.fermer, posX_fermer, posY_fermer, largeur_fermer, largeur_fermer, null);
    }
  }

  public void afficherNomJoueur(Graphics g) {
    String nomJ1, nomJ2;
    int taille_max = 14;
    nomJ1 = this.jeu.partieEnCours.joueur1().getNom();
    nomJ2 = this.jeu.partieEnCours.joueur2().getNom();
    if (nomJ1.length() > taille_max) {
      nomJ1 = nomJ1.substring(0, taille_max);
    }
    if (nomJ2.length() > taille_max) {
      nomJ2 = nomJ2.substring(0, taille_max);
    }
    g.setColor(Color.BLACK);
    g.setFont(new Font("Dialog", Font.BOLD, taille_police_nom_joueur));
    g.drawString(nomJ1, posX_nom_joueur1, posY_nom_joueur1);
    g.drawString(nomJ2, posX_nom_joueur2, posY_nom_joueur2);
  }

  public void drawBoutons(Graphics g) {
    g.drawImage(texture.settings, posX_settings, posY_settings, largeur_settings, largeur_settings, null);

    if (this.jeu.partieEnCours.getJoueurCourant() == 0) {
      if (jeu.partieEnCours.joueur2().doitVol) {
        g.drawImage(texture.cadre_joueur_gris, posX_cadreJ1, posY_cadreJ1, largeur_cadre, hauteur_cadre, null);
        g.drawImage(texture.cadre_joueur, posX_cadreJ2, posY_cadreJ2, largeur_cadre, hauteur_cadre, null);
      } else {
        g.drawImage(texture.cadre_joueur, posX_cadreJ1, posY_cadreJ1, largeur_cadre, hauteur_cadre, null);
        g.drawImage(texture.cadre_joueur_gris, posX_cadreJ2, posY_cadreJ2, largeur_cadre, hauteur_cadre, null);
      }
    } else {
      if (jeu.partieEnCours.joueur1().doitVol) {
        g.drawImage(texture.cadre_joueur, posX_cadreJ1, posY_cadreJ1, largeur_cadre, hauteur_cadre, null);
        g.drawImage(texture.cadre_joueur_gris, posX_cadreJ2, posY_cadreJ2, largeur_cadre, hauteur_cadre, null);
      } else {
        g.drawImage(texture.cadre_joueur_gris, posX_cadreJ1, posY_cadreJ1, largeur_cadre, hauteur_cadre, null);
        g.drawImage(texture.cadre_joueur, posX_cadreJ2, posY_cadreJ2, largeur_cadre, hauteur_cadre, null);
      }
    }

    if (this.jeu.partieEnCours.joueur1().getClass() == Joueur.class) {
      g.drawImage(texture.boutonSauvegarde, posX_sauvegarder, posY_sauvegarder, largeur_sauvegarder,
          hauteur_sauvegarder, null);
    }
    if (!jeu.partieEnCours.estPartieFinie()) {
      g.drawImage(texture.passerTour, posXPasserTour, posYPasserTour, largeurPasserTour, hauteurPasserTour, null);
      g.drawImage(texture.boutonCoupPrecedent, posXCoupPrecedent, posYPasserTour, largeurPasserTour, hauteurPasserTour,
          null);
      g.drawImage(texture.boutonSauvegarde, posX_sauvegarder, posY_sauvegarder, largeur_sauvegarder,
          hauteur_sauvegarder, null);
    }
  }

  public void drawIle(Graphics g) {
    g.drawImage(this.texture.ile_joueur1, posX_ileJ1, posY_ileJ1, largeur_ileJ, hauteur_ileJ, null);
    g.drawImage(this.texture.ile_joueur2, posX_ileJ2, posY_ileJ2, largeur_ileJ, hauteur_ileJ, null);
    g.drawImage(this.texture.ile_montagne, posX_ileM, posY_ileM, largeur_ileM, hauteur_ileM, null);
  }

 

  // INTERACTION ACTEUR COURANT----------------------------------------------
  public Acteur initAffichageJoueurs() {
    Acteur a;
    if (this.partieEnCours.getJoueurCourant() == 0) {
      a = this.partieEnCours.joueur1();
    } else {
      a = this.partieEnCours.joueur2();
    }
    return a;
  }

  public void setCoup(Position POSYitionPiecePyramide, Acteur a) { /////////////////////////////////////////////////////////////////////////////////////////////
    a.setCoupDemande(new Coup(pieceSelectionnee.getPiece(), pieceSelectionnee.getPos(), POSYitionPiecePyramide));
    a.validerCoup = true;
    pieceSelectionnee = null;
    this.repaint();
  }

  public void setPasser(Acteur a) { /////////////////////////////////////////////////////////////////////////////////////////////
    a.setCoupDemande(new Coup(pieceSelectionnee.getPiece(), pieceSelectionnee.getPos(), null));
    a.validerCoup = true;
    pieceSelectionnee = null;
    this.repaint();
  }

  // AFFICHAGE PIECE
  // SELECTIONNEE-------------------------------------------------------------
  public void dragNdrop(Graphics g) {
    if (pieceSelectionnee != null) {
      g.drawImage(getpetitcolor(pieceSelectionnee.getPiece(), 1), currentX, currentY,
          (int) (largeur_piece / 1.5), (int) (hauteur_piece / 1.5), null);
    }
  }

  // AFFICHAGE
  public void drawPiecesVoleesJ1(Graphics g) {
    g.drawImage(texture.imagevol, posX_volJ1, posY_volJ1, largeur_vol, hauteur_vol, null);
    for (int i = 0; i < this.jeu.partieEnCours.joueur1().getPiecesVolees().size(); i++) {
      g.drawImage(colortoimage(this.jeu.partieEnCours.joueur1().getPiecesVolees().get(i).getColor()),
          posX_piece_voleeJ1 + (int) (i * largeur_piece * 0.95), posY_piece_voleeJ2, largeur_piece, hauteur_piece,
          null);

    }
  }
  
  public void drawPiecesVoleesJ2(Graphics g) {
    g.drawImage(texture.imagevol, posX_volJ2, posY_volJ2, largeur_vol, hauteur_vol, null);
    for (int i = 0; i < this.jeu.partieEnCours.joueur2().getPiecesVolees().size(); i++) {
      g.drawImage(colortoimage(this.jeu.partieEnCours.joueur2().getPiecesVolees().get(i).getColor()),
          posX_piece_voleeJ2 + (int) (i * largeur_piece * 0.95), posY_piece_voleeJ2, largeur_piece, hauteur_piece,
          null);

    }
  }
  // J1----------------------------------------------------------------------
  public void affichePyramideJoueurJ1(Graphics g) {
    Acteur a = this.partieEnCours.joueur1();
    Position POSYitionPiecePyramide;
    int decalage = 0;
    for (int etage = 0; etage < a.getCamp().getHauteur(); etage++) {
      for (int rang = 0; rang < a.getCamp().getLargeur() - etage; rang++) {
        POSYitionPiecePyramide = new Position(etage, rang);
        Piece pieceJoueur = a.getCamp().getPiece(POSYitionPiecePyramide);
        g.drawImage(getpetitcolor(pieceJoueur, 1), decalage + rang * (largeur_piece) + posX_campJ1,
        		posY_campJ1 + 5 * (hauteur_piece) - etage * (hauteur_piece),
            largeur_piece, hauteur_piece, null);
      }
      decalage += (largeur_piece) / 2;
    }
  }

  public void drawVictoire(Graphics g) {
    if (!jeu.partieEnCours.IAreflechis) {
      String nom = "";
      if (jeu.partieEnCours.estPartieFinie()) {
        g.drawImage(texture.fond_degrade, posX_degrade, posY_degrade, largeur_degrade, hauteur_degade, null);
        if (jeu.partieEnCours.getBaseMontagne().estPleine()) {// si EGALITE
          g.drawImage(texture.egalite, posX_victoire, posY_victoire, largeur_victoire, hauteur_victoire, null);
        } else {
          if (jeu.partieEnCours.getJoueurCourant() == 0) {
            nom = jeu.partieEnCours.joueur2().getNom();
          } else {
            nom = jeu.partieEnCours.joueur1().getNom();
          }
          g.drawImage(texture.victoire, posX_victoire, posY_victoire, largeur_victoire, hauteur_victoire, null);
          g.drawImage(texture.cadre_joueur, posX_cadre_victoire, posY_cadre_victoire, largeur_cadre, hauteur_cadre,
              null);
          g.drawString(nom, posX_jtext2, posY_jtext2);
        }
      }
    }
  }

  // AFFICHAGE
  // J2----------------------------------------------------------------------
  public void affichePyramideJoueurJ2(Graphics g) {
    Acteur a = this.partieEnCours.joueur2();
    Position POSYitionPiecePyramide;
    int decalage = 0;
    for (int etage = 0; etage < a.getCamp().getHauteur(); etage++) {
      for (int rang = 0; rang < a.getCamp().getLargeur() - etage; rang++) {
        POSYitionPiecePyramide = new Position(etage, rang);
        Piece pieceJoueur = a.getCamp().getPiece(POSYitionPiecePyramide);
        g.drawImage(getpetitcolor(pieceJoueur, 1), decalage + rang * (largeur_piece) + posX_campJ2,
        		posY_campJ2 + 5 * (hauteur_piece) - etage * (hauteur_piece),
            largeur_piece, hauteur_piece, null);
      }
      decalage += (largeur_piece) / 2;
    }
  }

  // AFFICHAGE
  // MONTAGNE----------------------------------------------------------------------
  public void affichePyramideMontagne(Graphics g) {
    PyramideMontagne m = this.partieEnCours.getBaseMontagne();
    int alpha = 1;
    Position POSYitionPiecePyramide;
    int largeurCube = largeur_piece;
    int hauteurCube = hauteur_piece;
    int decalage = 0;
    for (int etage = 0; etage < m.getHauteur(); etage++) {
      for (int rang = 0; rang < m.getLargeur() - etage; rang++) {
        POSYitionPiecePyramide = new Position(etage, rang);
        Piece piece = m.getPiece(POSYitionPiecePyramide);
        if (piece == null && pieceSelectionnee != null) {
          for (PiecePyramide pp : this.partieEnCours.getBaseMontagne().piecesPosables()) {
            if (pp.getPiece().getColor() == pieceSelectionnee.getPiece().getColor() && pp.getPos().etage == etage
                && pp.getPos().rang == rang) {
              piece = pieceSelectionnee.getPiece();
              alpha = 0;
            }
          }
        }
        g.drawImage(getpetitcolor(piece, alpha), decalage + rang * (largeurCube) + posX_campM,
        		posY_campM + 8 * (hauteurCube) - etage * (hauteurCube), largeurCube, hauteurCube, null);
      }
      decalage += (largeurCube) / 2;
    }
  }

  public Image colortoimage(Couleurs c) {
    switch (c) {
      case VIDE:
        return this.texture.pieceVide;
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
  // PIECE SELECTIONEE----------------------------------------------
  public PiecePyramide getPieceSelectionnee() {
    return pieceSelectionnee;
  }

  public void setPieceSelectionnee(PiecePyramide p) {
    pieceSelectionnee = p;
    this.repaint();
  }

  // FONCTION QUI PERMET DE RENVOYER L IMAGE DE LA PIECE
  public Image getpetitcolor(Piece p, int alpha) {
    // alpha = 1 piece plienne 0 = transparente
    if (p == null) {
      return textures.pieceVide;
    } else {
      Couleurs colorP = p.getColor();
      if (colorP == Couleurs.BLEU) {
        if (alpha == 0) {
          return textures.pieceBleueTransparent;
        } else {
          return textures.pieceBleue;
        }
      } else if (colorP == Couleurs.VERT) {
        if (alpha == 0) {
          return textures.pieceVertTransparent;
        } else {
          return textures.pieceVert;
        }
      } else if (colorP == Couleurs.JAUNE) {
        if (alpha == 0) {
          return textures.pieceJauneTransparent;
        } else {
          return textures.pieceJaune;
        }
      } else if (colorP == Couleurs.ROUGE) {
        if (alpha == 0) {
          return textures.pieceRougeTransparent;
        } else {
          return textures.pieceRouge;
        }
      } else if (colorP == Couleurs.NOIR) {
        if (alpha == 0) {
          return textures.pieceNoireTransparent;
        } else {
          return textures.pieceNoire;
        }
      } else if (colorP == Couleurs.BLANC) {
        return textures.pieceBlanche;
      } else if (colorP == Couleurs.NATUREL) {
        if (alpha == 0) {
          return textures.pieceNatureTransparent;
        } else {
          return textures.pieceNature;
        }
      } else {
        return null;
      }
    }
  }

}