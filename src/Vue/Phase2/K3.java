/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package Vue.Phase2;
//import static com.sun.java.accessibility.util.AWTEventMonitor.addMouseListener;
import java.awt.Component;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.JComponent;
import javax.swing.SwingUtilities;
import javax.swing.TransferHandler;
import Modeles.*;
import Controleur.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import Vue.*;

/**
 *
 * @author farid
 */
public class K3 {
  public Position oldX;

  public ArrayList < Position > oldY = new ArrayList();
  public boolean ischange = false;
  public Position MousePyramide = new Position(0, 0);
  public Position MouseMontagne = new Position(0, 0);
  public Jeu j;
  public void K3(Jeu jeu) {
    Plateau start = new Plateau();

    j = jeu;
    start.partie_actuel = j;
    // start.setpartie(j);

    start.addComponentListener(new ComponentAdapter() {
      @Override
      public void componentResized(ComponentEvent e) {
        start.reload = true;

        start.repaint();
      }
    });

    start.jPanel12.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent me) {
        //j = new Jeu();
        start.etat_actuel.selectedpieceetage = -1;
        start.etat_actuel.selectedpiececolonne = -1;
        start.partie_actuel = j;
        start.etat_actuel.etat = EtatVue.ALLREFRESH;
        start.etat_actuel.baseselection.clear();
        MousePyramide = new Position(0, 0);
        MouseMontagne = new Position(0, 0);
        start.gameover1 = false;
        start.gameover2 = false;
        oldY.clear();
        start.repaint();

      }

    });

    start.jPanel1.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent me) {
        System.out.println(j.partieEnCours.joueurCourant);
        start.reload = true;
        start.poschange = me.getLocationOnScreen();
        changemap(start.poschange);

      }

      public void changemap(Point point) {
        int uniteX = start.jPanel1.getWidth() / 9;
        int uniteY = start.jPanel1.getHeight() / 9;

        //TODO CONVERTION POINT TO POINT SCREEN
        //  point.x = 0;
        //point.y = point.y-(8*uniteY);
        int localy = pointmaptopyramide(point, uniteX, uniteY).y;
        int localx = pointmaptopyramide(point, uniteX, uniteY).x;

        Point tmp = new Point(localx, localy);
        tmp = coordmaptopyramide(tmp);
        System.out.println(localx + " " + localy + "=>" + point.x + " " + point.y + " => " + tmp.x + " " + tmp.y + " " + uniteX + " " + uniteY);
        start.etat_actuel.selectedpieceetage = -1;
        start.etat_actuel.selectedpiececolonne = -1;
        if (tmp.x != -1) {

          if (j.partieEnCours.joueurCourant == 0) {

            ArrayList < PiecePyramide > montagne = j.partieEnCours.getBaseMontagne().piecesPosables();
            if (j.partieEnCours.joueur1().getCamp().getPiece(MousePyramide) == null) {
              return;
            }
            final Couleurs j1color = j.partieEnCours.joueur1().getCamp().getPiece(MousePyramide).getColor();
            System.out.println(j1color);
            if (j1color == Couleurs.BLANC) {
              j.partieEnCours.joueurCourant = 1;
              j.partieEnCours.joueur1().getCamp().retirer(MousePyramide);
              System.out.println("On retire le blanc");
              start.partie_actuel = j;
              start.etat_actuel.etat = EtatVue.ALLREFRESH;
              start.repaint();
              return;
            }

            // System.out.println(j1color);
            //  System.out.println(montagne);
            montagne.forEach((n) -> {
              if (n.getPiece().getColor() == j1color) {

                oldX = n.getPos();
                oldY.add(oldX);

              }
            });

            if (!oldY.isEmpty()) {

              final int etage = tmp.x;
              final int rang = tmp.y;
              oldY.forEach((n) -> {
                if (n.etage == etage && n.rang == rang && ischange == false) {
                  PiecePyramide Piece2 = new PiecePyramide(j.partieEnCours.joueur1().getCamp().getPiece(MousePyramide), new Position(etage, rang));
                  ischange = true;
                  if (j.partieEnCours.joueur1().getCamp().retirer(MousePyramide) != null) {
                    if (Piece2.getPiece().getColor() == Couleurs.BLANC) {
                      j.partieEnCours.joueurCourant = 1;
                    } else {
                      j.partieEnCours.getBaseMontagne().empiler(Piece2);
                      j.partieEnCours.joueurCourant = 1;
                    }

                  }
                }

              });
              oldY.clear();
              ischange = false;

            }

            start.partie_actuel = j;
            start.etat_actuel.etat = EtatVue.ALLREFRESH;
            start.etat_actuel.etage = MousePyramide.etage;
            start.etat_actuel.colonne = MousePyramide.rang;
            start.etat_actuel.typejoueur = 0;
            start.repaint();
            MousePyramide = new Position(0, 0);
            MouseMontagne = new Position(0, 0);
            oldY.clear();
            //start.repaint();
            if (j.partieEnCours.estPartieFinie()) {
              start.gameover2 = true;
              start.repaint();
            }
          } else {

            ArrayList < PiecePyramide > montagne = j.partieEnCours.getBaseMontagne().piecesPosables();
            if (j.partieEnCours.joueur2().getCamp().getPiece(MousePyramide) == null) {
              return;
            }
            final Couleurs j1color = j.partieEnCours.joueur2().getCamp().getPiece(MousePyramide).getColor();
            if (j1color == Couleurs.BLANC) {
              j.partieEnCours.joueurCourant = 0;
              j.partieEnCours.joueur2().getCamp().retirer(MousePyramide);
              System.out.println("On retire le blanc");
              start.partie_actuel = j;
              start.etat_actuel.etat = EtatVue.ALLREFRESH;
              start.repaint();
              return;
            }
            // System.out.println(j1color);
            System.out.println(montagne);
            montagne.forEach((n) -> {
              if (n.getPiece().getColor() == j1color) {

                oldX = n.getPos();
                oldY.add(oldX);

              }
            });

            if (!oldY.isEmpty()) {

              final int etage = tmp.x;
              final int rang = tmp.y;
              oldY.forEach((n) -> {
                if (n.etage == etage && n.rang == rang && ischange == false) {
                  PiecePyramide Piece2 = new PiecePyramide(j.partieEnCours.joueur2().getCamp().getPiece(MousePyramide), new Position(etage, rang));
                  ischange = true;
                  if (j.partieEnCours.joueur2().getCamp().retirer(MousePyramide) != null) {
                    if (Piece2.getPiece().getColor() == Couleurs.BLANC) {
                      j.partieEnCours.joueurCourant = 0;
                    } else {
                      j.partieEnCours.getBaseMontagne().empiler(Piece2);
                      j.partieEnCours.joueurCourant = 0;
                    }

                  }
                }

              });
              oldY.clear();
              ischange = false;

            }
            start.partie_actuel = j;
            start.etat_actuel.etat = EtatVue.ALLREFRESH;
            start.etat_actuel.etage = MousePyramide.etage;
            start.etat_actuel.colonne = MousePyramide.rang;
            start.etat_actuel.typejoueur = 1;

            start.repaint();
            MousePyramide = new Position(0, 0);
            MouseMontagne = new Position(0, 0);
            oldY.clear();
            if (j.partieEnCours.estPartieFinie()) {
              start.gameover1 = true;
            }
          }

        }

      }

      public Point pointmaptopyramide(Point point, int uniteX, int uniteY) {

        Point result = new Point();
        int yjpanel = start.jPanel1.getLocationOnScreen().y;
        int xjpanel = start.jPanel1.getLocationOnScreen().x;

        System.out.println(xjpanel + " " + yjpanel);

        if (point.y > yjpanel + 8 * uniteY) {
          if (point.x > xjpanel + 8 * uniteX) {
            result.x = 8;
          } else if (point.x > xjpanel + 7 * uniteX) {
            result.x = 7;
          } else if (point.x > xjpanel + 6 * uniteX) {
            result.x = 6;
          } else if (point.x > xjpanel + 5 * uniteX) {
            result.x = 5;
          } else if (point.x > xjpanel + 4 * uniteX) {
            result.x = 4;
          } else if (point.x > xjpanel + 3 * uniteX) {
            result.x = 3;
          } else if (point.x > xjpanel + 2 * uniteX) {
            result.x = 2;
          } else if (point.x > xjpanel + 1 * uniteX) {
            result.x = 1;
          } else if (point.x > xjpanel + 0 * uniteX) {
            result.x = 0;
          }

          result.y = 8;
        } else if (point.y > (yjpanel + yjpanel / 5 + (7 * uniteY))) {

          if (point.x > xjpanel + 7 * uniteX + (uniteX / 2)) {
            result.x = 7;
          } else if (point.x > xjpanel + 6 * uniteX + (uniteX / 2)) {
            result.x = 6;
          } else if (point.x > xjpanel + 5 * uniteX + (uniteX / 2)) {
            result.x = 5;
          } else if (point.x > xjpanel + 4 * uniteX + (uniteX / 2)) {
            result.x = 4;
          } else if (point.x > xjpanel + 3 * uniteX + (uniteX / 2)) {
            result.x = 3;
          } else if (point.x > xjpanel + 2 * uniteX + (uniteX / 2)) {
            result.x = 2;
          } else if (point.x > xjpanel + 1 * uniteX + (uniteX / 2)) {
            result.x = 1;
          } else if (point.x > (xjpanel + 0 * uniteX) + (uniteX / 2)) {
            result.x = 0;
          }
          result.y = 7;
        } else if (point.y > (yjpanel / 2 + yjpanel + (6 * uniteY))) {

          if (point.x > xjpanel + 7 * uniteX) {
            result.x = 7;
          } else if (point.x > xjpanel + 6 * uniteX) {
            result.x = 6;
          } else if (point.x > xjpanel + 5 * uniteX) {
            result.x = 5;
          } else if (point.x > xjpanel + 4 * uniteX) {
            result.x = 4;
          } else if (point.x > xjpanel + 3 * uniteX) {
            result.x = 3;
          } else if (point.x > xjpanel + 2 * uniteX) {
            result.x = 2;
          } else if (point.x > xjpanel + 1 * uniteX) {
            result.x = 1;
          }

          result.y = 6;
        } else if (point.y > (2 * yjpanel - yjpanel / 4 + (5 * uniteY))) {
          if (point.x > xjpanel + 7 * uniteX - (uniteX) + (uniteX / 2)) {
            result.x = 7;
          } else if (point.x > xjpanel + 6 * uniteX - (uniteX) + (uniteX / 2)) {
            result.x = 6;
          } else if (point.x > xjpanel + 5 * uniteX - (uniteX) + (uniteX / 2)) {
            result.x = 5;
          } else if (point.x > xjpanel + 4 * uniteX - (uniteX) + (uniteX / 2)) {
            result.x = 4;
          } else if (point.x > xjpanel + 3 * uniteX - (uniteX) + (uniteX / 2)) {
            result.x = 3;
          } else if (point.x > xjpanel + 2 * uniteX - (uniteX) + (uniteX / 2)) {
            result.x = 2;
          }

          result.y = 5;
        } else if (point.y > (4 * yjpanel / 2 + 4 * uniteY)) {
          if (point.x > xjpanel + 7 * uniteX - 2 * (uniteX) + (uniteX)) {
            result.x = 7;
          } else if (point.x > xjpanel + 6 * uniteX - 2 * (uniteX) + (uniteX)) {
            result.x = 6;
          } else if (point.x > xjpanel + 5 * uniteX - 2 * (uniteX) + (uniteX)) {
            result.x = 5;
          } else if (point.x > xjpanel + 4 * uniteX - 2 * (uniteX) + (uniteX)) {
            result.x = 4;
          } else if (point.x > xjpanel + 3 * uniteX - 2 * (uniteX) + (uniteX)) {
            result.x = 3;
          }

          result.y = 4;
        } else if (point.y > (7 * yjpanel / 3 + 3 * uniteY)) {
          if (point.x > xjpanel + 7 * uniteX - 2 * (uniteX) + (uniteX) - (uniteX / 2)) {
            result.x = 7;
          } else if (point.x > xjpanel + 6 * uniteX - 2 * (uniteX) + (uniteX) - (uniteX / 2)) {
            result.x = 6;
          } else if (point.x > xjpanel + 5 * uniteX - 2 * (uniteX) + (uniteX) - (uniteX / 2)) {
            result.x = 5;
          } else if (point.x > xjpanel + 4 * uniteX - 2 * (uniteX) + (uniteX) - (uniteX / 2)) {
            result.x = 4;
          }

          result.y = 3;
        } else if (point.y > (8 * yjpanel / 3 + 2 * uniteY)) {
          if (point.x > xjpanel + 7 * uniteX - 2 * (uniteX) + (uniteX) - 2 * (uniteX / 2)) {
            result.x = 7;
          } else if (point.x > xjpanel + 6 * uniteX - 2 * (uniteX) + (uniteX) - 2 * (uniteX / 2)) {
            result.x = 6;
          } else if (point.x > xjpanel + 5 * uniteX - 2 * (uniteX) + (uniteX) - 2 * (uniteX / 2)) {
            result.x = 5;
          }

          result.y = 2;
        } else if (point.y > (9 * yjpanel / 3 + uniteY)) {
          if (point.x > xjpanel + 6 * uniteX - 2 * (uniteX) + (uniteX) - (uniteX / 2)) {
            result.x = 6;
          } else if (point.x > xjpanel + 5 * uniteX - 2 * (uniteX) + (uniteX) - (uniteX / 2)) {
            result.x = 5;
          }
          result.y = 1;
        } else if (point.y > (10 * yjpanel / 3)) {
          if (point.x > xjpanel + 5 * uniteX - 2 * (uniteX) + (uniteX)) {
            result.x = 6;
          }
          result.y = 0;
        }

        //result.y = (point.y)/(start.jPanel1.getLocation().y+uniteY);
        // System.out.println(result);
        return result;

      }

      public Point coordmaptopyramide(Point point) {
        Point result = new Point();
        switch (point.x) {
        case 0:
          switch (point.y) {
          case 8 -> {
            result.x = 0;result.y = 0;
          }
          case 7 -> {
            result.x = 1;result.y = 0;
          }

          default -> {
            result.x = -1;result.y = -1;
          }

          }
          break;

        case 1:
          switch (point.y) {

          case 8:
            result.x = 0;
            result.y = 1;
            break;
          case 7:
            result.x = 1;
            result.y = 1;
            break;
          case 6:
            result.x = 2;
            result.y = 0;
            break;

          }
          break;
        case 2:
          switch (point.y) {

          case 8:
            result.x = 0;
            result.y = 2;
            break;
          case 7:
            result.x = 1;
            result.y = 2;
            break;
          case 6:
            result.x = 2;
            result.y = 1;
            break;
          case 5:
            result.x = 3;
            result.y = 0;
            break;
          case 4:
            result.x = 4;
            result.y = 0;
            break;
          default:
            result.x = -1;
            result.y = -1;
            break;

          }
          break;
        case 3:
          switch (point.y) {
          case 8:
            result.x = 0;
            result.y = 3;
            break;
          case 7:
            result.x = 1;
            result.y = 3;
            break;
          case 6:
            result.x = 2;
            result.y = 2;
            break;
          case 5:
            result.x = 3;
            result.y = 1;
            break;
          case 4:
            result.x = 4;
            result.y = 0;
            break;
          case 3:
            result.x = 5;
            result.y = 0;
            break;
          case 2:
            result.x = 6;
            result.y = 0;
            break;

          default:
            result.x = -1;
            result.y = -1;
            break;

          }
          break;

        case 4:
          switch (point.y) {

          case 8:
            result.x = 0;
            result.y = 4;
            break;
          case 7:
            result.x = 1;
            result.y = 4;
            break;
          case 6:
            result.x = 2;
            result.y = 3;
            break;
          case 5:
            result.x = 3;
            result.y = 2;
            break;
          case 4:
            result.x = 4;
            result.y = 1;
            break;
          case 3:
            result.x = 5;
            result.y = 0;
            break;
          case 2:
            result.x = 6;
            result.y = 1;
            break;
          case 1:
            result.x = 7;
            result.y = 0;
            break;
          case 0:
            result.x = 8;
            result.y = 0;
            break;

          default:
            result.x = -1;
            result.y = -1;
            break;
          }
          break;
        case 5:
          switch (point.y) {

          case 8:
            result.x = 0;
            result.y = 5;
            break;
          case 7:
            result.x = 1;
            result.y = 5;
            break;
          case 6:
            result.x = 2;
            result.y = 4;
            break;
          case 5:
            result.x = 3;
            result.y = 3;
            break;
          case 4:
            result.x = 4;
            result.y = 2;
            break;
          case 3:
            result.x = 5;
            result.y = 1;
            break;
          case 2:
            result.x = 6;
            result.y = 0;
            break;
          case 1:
            result.x = 7;
            result.y = 0;
            break;

          default:
            result.x = -1;
            result.y = -1;
            break;
          }
          break;
        case 6:
          switch (point.y) {

          case 8:
            result.x = 0;
            result.y = 6;
            break;
          case 7:
            result.x = 1;
            result.y = 6;
            break;
          case 6:
            result.x = 2;
            result.y = 5;
            break;
          case 5:
            result.x = 3;
            result.y = 4;
            break;
          case 4:
            result.x = 4;
            result.y = 3;
            break;
          case 3:
            result.x = 5;
            result.y = 2;
            break;
          case 2:
            result.x = 6;
            result.y = 1;
            break;

          case 1:
            result.x = 7;
            result.y = 1;
            break;

          case 0:
            result.x = 8;
            result.y = 0;
            break;

          default:
            result.x = -1;
            result.y = -1;
            break;
          }
          break;
        case 7:
          switch (point.y) {

          case 8:
            result.x = 0;
            result.y = 7;
            break;
          case 7:
            result.x = 1;
            result.y = 7;
            break;
          case 6:
            result.x = 2;
            result.y = 6;
            break;
          case 5:
            result.x = 3;
            result.y = 5;
            break;

          case 4:
            result.x = 4;
            result.y = 4;
            break;

          case 3:
            result.x = 5;
            result.y = 3;
            break;

          case 2:
            result.x = 6;
            result.y = 2;
            break;

          default:
            result.x = -1;
            result.y = -1;
            break;
          }
          break;
        case 8:
          switch (point.y) {

          case 8:
            result.x = 0;
            result.y = 8;
            break;

          default:
            result.x = -1;
            result.y = -1;
            break;
          }
          break;
        default:
          result.x = -1;
          result.y = -1;
          break;
        }
        return result;
      }
    });

    start.jPanel3.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent me) {
        start.reload = true;
        start.poschange = me.getLocationOnScreen();
        changemap(start.poschange);

      }

      public void changemap(Point point) {
        int uniteX = start.jPanel3.getWidth() / 6;
        int uniteY = start.jPanel3.getHeight() / 6;
        int localy = pointmaptopyramide(point, uniteX, uniteY).y;
        int localx = pointmaptopyramide(point, uniteX, uniteY).x;

        Point tmp = new Point(localx, localy);
        tmp = coordmaptopyramide(tmp);
        System.out.println(point.x + " " + point.y + " => " + tmp.x + " " + tmp.y + " " + uniteX + " " + uniteY);

        MousePyramide.etage = tmp.x;
        MousePyramide.rang = tmp.y;

        if (j.partieEnCours.joueur2().getCamp().getPiece(MousePyramide) == null) {
          return;
        }
        if (j.partieEnCours.joueur2().getCamp().getPiece(MousePyramide).getColor() == Couleurs.BLANC && j.partieEnCours.joueurCourant == 1) {
          j.partieEnCours.joueurCourant = 0;
          j.partieEnCours.joueur2().getCamp().retirer(MousePyramide);
          System.out.println("On retire le blanc");
          start.partie_actuel = j;
          start.etat_actuel.etat = EtatVue.ALLREFRESH;
          start.repaint();
          return;
        }
        start.etat_actuel.selectedpieceetage = tmp.x;
        start.etat_actuel.selectedpiececolonne = tmp.y;
        System.out.println("On est la");
        final Couleurs j1color = j.partieEnCours.joueur2().getCamp().getPiece(MousePyramide).getColor();

        ArrayList < PiecePyramide > montagne = j.partieEnCours.getBaseMontagne().piecesPosables();
        montagne.forEach((n) -> {
          if (n.getPiece().getColor() == j1color) {

            oldX = n.getPos();
            start.etat_actuel.baseselection.add(oldX);

          }
        });
        oldX = null;
        if (start.etat_actuel.baseselection.isEmpty() == false) {
          start.etat_actuel.etat = EtatVue.ALLREFRESH;
          start.repaint();
        }

      }

      public Point pointmaptopyramide(Point point, int uniteX, int uniteY) {

        Point result = new Point();
        int yjpanel = start.jPanel3.getLocationOnScreen().y;
        int xjpanel = start.jPanel3.getLocationOnScreen().x;

        result.x = (point.x - start.jPanel3.getLocation().x) / uniteX;

        if (point.y > yjpanel + 5 * uniteY) {
          if (point.x > xjpanel + 5 * uniteX) {
            result.x = 5;
          } else if (point.x > xjpanel + 4 * uniteX) {
            result.x = 4;
          } else if (point.x > xjpanel + 3 * uniteX) {
            result.x = 3;
          } else if (point.x > xjpanel + 2 * uniteX) {
            result.x = 2;
          } else if (point.x > xjpanel + 1 * uniteX) {
            result.x = 1;
          } else if (point.x > xjpanel + 0 * uniteX) {
            result.x = 0;
          }

          result.y = 5;
        } else if (point.y > (yjpanel + yjpanel / 4 + (4 * uniteY))) {

          if (point.x > xjpanel + 4 * uniteX + (uniteX / 2)) {
            result.x = 4;
          } else if (point.x > xjpanel + 3 * uniteX + (uniteX / 2)) {
            result.x = 3;
          } else if (point.x > xjpanel + 2 * uniteX + (uniteX / 2)) {
            result.x = 2;
          } else if (point.x > xjpanel + 1 * uniteX + (uniteX / 2)) {
            result.x = 1;
          } else if (point.x > xjpanel + 0 * uniteX + (uniteX / 2)) {
            result.x = 0;
          }
          result.y = 4;
        } else if (point.y > (yjpanel + yjpanel / 2 + (3 * uniteY))) {

          if (point.x > xjpanel + 4 * uniteX) {
            result.x = 4;
          } else if (point.x > xjpanel + 3 * uniteX) {
            result.x = 3;
          } else if (point.x > xjpanel + 2 * uniteX) {
            result.x = 2;
          } else if (point.x > xjpanel + 1 * uniteX) {
            result.x = 1;
          }

          result.y = 3;
        } else if (point.y > (yjpanel + yjpanel / 2 + yjpanel / 3 + (2 * uniteY))) {
          if (point.x > xjpanel + 4 * uniteX - (uniteX) + (uniteX / 2)) {
            result.x = 4;
          } else if (point.x > xjpanel + 3 * uniteX - (uniteX) + (uniteX / 2)) {
            result.x = 3;
          } else if (point.x > xjpanel + 2 * uniteX - (uniteX) + (uniteX / 2)) {
            result.x = 2;
          }

          result.y = 2;
        } else if (point.y > (2 * yjpanel + yjpanel / 4 + 1 * uniteY)) {
          if (point.x > xjpanel + 4 * uniteX - (uniteX)) {
            result.x = 4;
          } else if (point.x > xjpanel + 2 * uniteX) {
            result.x = 2;
          }
          result.y = 1;
        } else if (point.y > (2 * yjpanel + yjpanel / 2)) {
          if (point.x > xjpanel + 2 * uniteX + (uniteX / 2)) {
            result.x = 2;
          }
          result.y = 0;
        }

        //result.y = (point.y)/(start.jPanel1.getLocation().y+uniteY);
        System.out.println(result);
        return result;

      }

      public Point coordmaptopyramide(Point point) {
        Point result = new Point();
        switch (point.x) {
        case 0:
          switch (point.y) {
          case 5 -> {
            result.x = 0;result.y = 0;
          }
          case 4 -> {
            result.x = 1;result.y = 0;
          }
          default -> {
            result.x = -1;result.y = -1;
          }

          }
          break;

        case 1:
          switch (point.y) {

          case 3:
            result.x = 2;
            result.y = 0;
            break;
          case 4:
            result.x = 1;
            result.y = 1;
            break;
          case 5:
            result.x = 0;
            result.y = 1;
            break;
          default:
            result.x = -1;
            result.y = -1;
            break;

          }
          break;
        case 2:
          switch (point.y) {

          case 0:
            result.x = 5;
            result.y = 0;
            break;

          case 1:
            result.x = 4;
            result.y = 0;
            break;
          case 2:
            result.x = 3;
            result.y = 0;
            break;
          case 3:
            result.x = 2;
            result.y = 1;
            break;
          case 4:
            result.x = 1;
            result.y = 2;
            break;
          case 5:
            result.x = 0;
            result.y = 2;
            break;
          default:
            result.x = -1;
            result.y = -1;
            break;

          }
          break;
        case 3:
          switch (point.y) {

          case 2:
            result.x = 3;
            result.y = 1;
            break;
          case 3:
            result.x = 2;
            result.y = 2;
            break;
          case 4:
            result.x = 1;
            result.y = 3;
            break;
          case 5:
            result.x = 0;
            result.y = 3;
            break;
          default:
            result.x = -1;
            result.y = -1;
            break;

          }
          break;

        case 4:
          switch (point.y) {

          case 1:
            result.x = 4;
            result.y = 1;
            break;
          case 2:
            result.x = 3;
            result.y = 2;
            break;
          case 3:
            result.x = 2;
            result.y = 3;
            break;
          case 4:
            result.x = 1;
            result.y = 4;
            break;
          case 5:
            result.x = 0;
            result.y = 4;
            break;
          default:
            result.x = -1;
            result.y = -1;
            break;
          }
          break;
        case 5:
          switch (point.y) {

          case 5:
            result.x = 0;
            result.y = 5;
            break;
          default:
            result.x = -1;
            result.y = -1;
            break;
          }
          break;
        default:
          result.x = -1;
          result.y = -1;
          break;
        }
        return result;
      }
    });

    start.jPanel2.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent me) {
        start.reload = true;
        start.poschange = me.getLocationOnScreen();
        changemap(start.poschange);

      }

      public void changemap(Point point) {
        int uniteX = start.jPanel2.getWidth() / 6;
        int uniteY = start.jPanel2.getHeight() / 6;
        int localy = pointmaptopyramide(point, uniteX, uniteY).y;
        int localx = pointmaptopyramide(point, uniteX, uniteY).x;

        Point tmp = new Point(localx, localy);
        tmp = coordmaptopyramide(tmp);
        System.out.println(point.x + " " + point.y + " => " + tmp.x + " " + tmp.y + " " + uniteX + " " + uniteY);

        MousePyramide.etage = tmp.x;
        MousePyramide.rang = tmp.y;

        if (j.partieEnCours.joueur1().getCamp().getPiece(MousePyramide) == null) {
          return;
        }

        if (j.partieEnCours.joueur1().getCamp().getPiece(MousePyramide).getColor() == Couleurs.BLANC && j.partieEnCours.joueurCourant == 0) {
          j.partieEnCours.joueurCourant = 1;
          j.partieEnCours.joueur1().getCamp().retirer(MousePyramide);
          System.out.println("On retire le blanc");
          start.partie_actuel = j;
          start.etat_actuel.etat = EtatVue.ALLREFRESH;
          start.repaint();
          return;
        }
        start.etat_actuel.selectedpieceetage = tmp.x;
        start.etat_actuel.selectedpiececolonne = tmp.y;
        System.out.println("On est la");
        final Couleurs j1color = j.partieEnCours.joueur1().getCamp().getPiece(MousePyramide).getColor();

        ArrayList < PiecePyramide > montagne = j.partieEnCours.getBaseMontagne().piecesPosables();
        montagne.forEach((n) -> {
          if (n.getPiece().getColor() == j1color) {

            oldX = n.getPos();
            start.etat_actuel.baseselection.add(oldX);

          }
        });
        oldX = null;
        if (start.etat_actuel.baseselection.isEmpty() == false) {
          start.etat_actuel.etat = EtatVue.ALLREFRESH;
          start.repaint();
          //  start.etat_actuel.baseselection.clear();
        }

      }

      public Point pointmaptopyramide(Point point, int uniteX, int uniteY) {

        Point result = new Point();
        int yjpanel = start.jPanel2.getLocationOnScreen().y;
        int xjpanel = start.jPanel2.getLocationOnScreen().x;

        result.x = (point.x - start.jPanel2.getLocation().x) / uniteX;

        if (point.y > yjpanel + 5 * uniteY) {
          if (point.x > xjpanel + 5 * uniteX) {
            result.x = 5;
          } else if (point.x > xjpanel + 4 * uniteX) {
            result.x = 4;
          } else if (point.x > xjpanel + 3 * uniteX) {
            result.x = 3;
          } else if (point.x > xjpanel + 2 * uniteX) {
            result.x = 2;
          } else if (point.x > xjpanel + 1 * uniteX) {
            result.x = 1;
          } else if (point.x > xjpanel + 0 * uniteX) {
            result.x = 0;
          }

          result.y = 5;
        } else if (point.y > (yjpanel + yjpanel / 4 + (4 * uniteY))) {

          if (point.x > xjpanel + 4 * uniteX + (uniteX / 2)) {
            result.x = 4;
          } else if (point.x > xjpanel + 3 * uniteX + (uniteX / 2)) {
            result.x = 3;
          } else if (point.x > xjpanel + 2 * uniteX + (uniteX / 2)) {
            result.x = 2;
          } else if (point.x > xjpanel + 1 * uniteX + (uniteX / 2)) {
            result.x = 1;
          } else if (point.x > xjpanel + 0 * uniteX + (uniteX / 2)) {
            result.x = 0;
          }
          result.y = 4;
        } else if (point.y > (yjpanel + yjpanel / 2 + (3 * uniteY))) {

          if (point.x > xjpanel + 4 * uniteX) {
            result.x = 4;
          } else if (point.x > xjpanel + 3 * uniteX) {
            result.x = 3;
          } else if (point.x > xjpanel + 2 * uniteX) {
            result.x = 2;
          } else if (point.x > xjpanel + 1 * uniteX) {
            result.x = 1;
          }

          result.y = 3;
        } else if (point.y > (yjpanel + yjpanel / 2 + yjpanel / 3 + (2 * uniteY))) {
          if (point.x > xjpanel + 4 * uniteX - (uniteX) + (uniteX / 2)) {
            result.x = 4;
          } else if (point.x > xjpanel + 3 * uniteX - (uniteX) + (uniteX / 2)) {
            result.x = 3;
          } else if (point.x > xjpanel + 2 * uniteX - (uniteX) + (uniteX / 2)) {
            result.x = 2;
          }

          result.y = 2;
        } else if (point.y > (2 * yjpanel + yjpanel / 4 + 1 * uniteY)) {
          if (point.x > xjpanel + 4 * uniteX - (uniteX)) {
            result.x = 4;
          } else if (point.x > xjpanel + 2 * uniteX) {
            result.x = 2;
          }
          result.y = 1;
        } else if (point.y > (2 * yjpanel + yjpanel / 2)) {
          if (point.x > xjpanel + 2 * uniteX + (uniteX / 2)) {
            result.x = 2;
          }
          result.y = 0;
        }

        //result.y = (point.y)/(start.jPanel1.getLocation().y+uniteY);
        System.out.println(result);
        return result;

      }

      public Point coordmaptopyramide(Point point) {
        Point result = new Point();
        switch (point.x) {
        case 0:
          switch (point.y) {
          case 5 -> {
            result.x = 0;result.y = 0;
          }
          case 4 -> {
            result.x = 1;result.y = 0;
          }
          default -> {
            result.x = -1;result.y = -1;
          }

          }
          break;

        case 1:
          switch (point.y) {

          case 3:
            result.x = 2;
            result.y = 0;
            break;
          case 4:
            result.x = 1;
            result.y = 1;
            break;
          case 5:
            result.x = 0;
            result.y = 1;
            break;
          default:
            result.x = -1;
            result.y = -1;
            break;

          }
          break;
        case 2:
          switch (point.y) {

          case 0:
            result.x = 5;
            result.y = 0;
            break;
          case 1:
            result.x = 4;
            result.y = 0;
            break;
          case 2:
            result.x = 3;
            result.y = 0;
            break;
          case 3:
            result.x = 2;
            result.y = 1;
            break;
          case 4:
            result.x = 1;
            result.y = 2;
            break;
          case 5:
            result.x = 0;
            result.y = 2;
            break;
          default:
            result.x = -1;
            result.y = -1;
            break;

          }
          break;
        case 3:
          switch (point.y) {

          case 2:
            result.x = 3;
            result.y = 1;
            break;
          case 3:
            result.x = 2;
            result.y = 2;
            break;
          case 4:
            result.x = 1;
            result.y = 3;
            break;
          case 5:
            result.x = 0;
            result.y = 3;
            break;
          default:
            result.x = -1;
            result.y = -1;
            break;

          }
          break;

        case 4:
          switch (point.y) {

          case 1:
            result.x = 4;
            result.y = 1;
            break;
          case 2:
            result.x = 3;
            result.y = 2;
            break;
          case 3:
            result.x = 2;
            result.y = 3;
            break;
          case 4:
            result.x = 1;
            result.y = 4;
            break;
          case 5:
            result.x = 0;
            result.y = 4;
            break;
          default:
            result.x = -1;
            result.y = -1;
            break;
          }
          break;
        case 5:
          switch (point.y) {

          case 5:
            result.x = 0;
            result.y = 5;
            break;
          default:
            result.x = -1;
            result.y = -1;
            break;
          }
          break;
        default:
          result.x = -1;
          result.y = -1;
          break;
        }
        return result;
      }
    });

    start.reload = true;
    start.pack();

    start.repaint();
    start.setVisible(true);
  }

}