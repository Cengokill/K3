package Test;

import java.util.ArrayList;
import java.util.Iterator;

import Controleur.*;
import Modeles.*;

public class TestIA {
    private final static int TAILLE_CAMP_JOUEUR = 21;

    public static void main(String[] args) {
        int objectif = 1000; // nombre de partie de test
        int nbParties = 0;
        int victoirej1 = 0;
        int victoirej2 = 0;
        double t1 = (double) System.currentTimeMillis();

        while (nbParties != objectif) {
            // INITIALISATION DE LA PARTIE
            Joueur j1 = new Joueur("Stupid 1");
            Joueur j2 = new Joueur("BigBrain");
            Partie ktrois = new Partie(j1, j2);
            int joueurCourant = 0; // Le joueur qui commence est le premier

            // DISTIBUTION DES PIONS
            ktrois.distribuerBlancEtNaturels(); // On donne les blancs
            while (ktrois.joueur1().getTaillePiecesPiochees() < TAILLE_CAMP_JOUEUR
                    || ktrois.joueur2().getTaillePiecesPiochees() < TAILLE_CAMP_JOUEUR) { // On pioche tant qu on a pas
                                                                                          // assez de pions
                Piece p;

                p = ktrois.joueur1().piocherPiece(ktrois.getBasePieces());
                ktrois.joueur1().addPiecePiochee(p);
                p = ktrois.joueur2().piocherPiece(ktrois.getBasePieces());
                ktrois.joueur2().addPiecePiochee(p);

            }

            // CREATION DES PYRAMIDES
            PyramideJoueur pj;
            IApioche iaP = new IApiocheAlea();
            IApioche iaPE = new IApiocheExpert();

            pj = iaPE.CreerPioche(ktrois, 0);
            ktrois.joueur1().setCamp(pj); // Tester avec get
            // System.out.println(ktrois.joueur1().getCamp().toString());

            pj = iaP.CreerPioche(ktrois, 1);
            ktrois.joueur2().setCamp(pj);
            // System.out.println(ktrois.joueur2().getCamp().toString());

            // System.out.println(ktrois.getBaseMontagne().toString());

            // PHASE DE JEU
            // // System.out.println();
            // // System.out.println("Phase de jeu");
            IAjeuAlea iaJ = new IAjeuAlea();
            IAjeuExpert iaE = new IAjeuExpert();
            while (!ktrois.estPartieFinie(joueurCourant)) { // Argument partie en cours
                Coup c;
                if (joueurCourant == 0) {
                    c = iaJ.IACoup(ktrois, joueurCourant);
                } else {
                    // ArrayList<Coup> lc = ktrois.coupsJouables(ktrois.joueur2());
                    // affiche(lc);
                    c = iaE.IACoup(ktrois, joueurCourant);
                    if (c == null) {
                        // System.out.println("IAexpert renvoie un coup vide");
                    }
                }
                // System.out.print("Le joueur numero " + (joueurCourant + 1) + ": joue le
                // coup");
                // System.out.println(c.toString());

                // Joue
                ktrois.jouer(c, joueurCourant);

                // Afiichage
                // System.out.println(ktrois.joueur1().getCamp().toString());
                // System.out.println(ktrois.joueur2().getCamp().toString());
                // System.out.println(ktrois.getBaseMontagne().toString());

                // changer le joueur courant
                if (joueurCourant == 1) {
                    joueurCourant = 0;
                } else {
                    joueurCourant = 1;
                }
            }

            // GAGNANT
            if (joueurCourant == 0) {
                victoirej2++;
            } else {
                victoirej1++;
            }
            nbParties++;
        }
        System.out.println("Nombre de partie jouer :" + objectif);
        System.out.println("Taux de victoire du joueur 1: " + ((double) victoirej1 * 100 / ((double) objectif)) + "%");
        System.out.println("Taux de victoire du joueur 2: " + ((double) victoirej2 * 100 / ((double) objectif)) + "%");
        double t2 = (double) System.currentTimeMillis();
        System.out.println("Temps moyen d'une partie: " + (t2 - t1) / (objectif * 1000) + " s");
    }

    public static void affiche(ArrayList<Coup> l) {
        Iterator<Coup> it = l.iterator();
        System.out.println("Coups possibles");
        while (it.hasNext()) {
            System.out.println(it.next().toString());
        }
        System.out.println();
    }

}