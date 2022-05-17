package Test;

import java.util.ArrayList;
import java.util.Iterator;

import Controleur.*;
import Modeles.*;

public class TestJcIA {

    public static void main(String[] args) {
        int objectif = 1; // nombre de parties de test
        int nbParties = 0;
        int victoirej1 = 0;
        int victoirej2 = 0;
        int matchnull = 0;

        // Chronometre du temps de toutes les parties
        double t1 = (double) System.currentTimeMillis();

        Partie ktrois = null;
        while (nbParties != objectif) { // On créer des parties jusqu a avoir le bon nombre de partie jouer

            // INITIALISATION DE LA PARTIE
            Acteur j1 = new Joueur("Stupid 1");
            Acteur j2 = new IAActeur("BigBrain", 2, 1);

            ktrois = new Partie(j1, j2, nbParties);// numero de partie
            // Modifier le premier joueur qui commence
            ktrois.joueurCourant = 0;
            ktrois.joueurDebut = 0;

            // DISTIBUTION DES PIONS
            ktrois.distribuerBlancEtNaturels(); // On donne les blancs
            while (ktrois.joueur1().getTaillePiecesPiochees() < TAILLE_CAMP_JOUEUR
                    || ktrois.joueur2().getTaillePiecesPiochees() < TAILLE_CAMP_JOUEUR) { // On pioche tant qu on n a
                                                                                          // pas
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

            pj = iaPE.CreerPioche(ktrois, 1);
            ktrois.joueur2().setCamp(pj);
            // System.out.println(ktrois.joueur2().getCamp().toString());

            // System.out.println(ktrois.getBaseMontagne().toString());

            // PHASE DE JEU
            // System.out.println();
            // System.out.println("Phase de jeu");
            IAjeuAlea iaA = new IAjeuAlea();
            IAjeuExpert iaJ = new IAjeuExpert(3);
            IAjeuExpert iaE = new IAjeuExpert(5);
            while (!ktrois.estPartieFinie()) { // Argument partie en cours
                Coup c;
                if (ktrois.getJoueurCourant() == 0) {
                    c = iaA.IACoup(ktrois, ktrois.getJoueurCourant());
                } else {
                    // ArrayList<Coup> lc = ktrois.coupsJouables(ktrois.joueur2());
                    // affiche(lc);
                    c = iaA.IACoup(ktrois, ktrois.getJoueurCourant());
                    if (c == null) {
                        // System.out.println("IAexpert renvoie un coup vide");
                    }
                }
                // System.out.print("Le joueur numero " + (ktrois.getJoueurCourant() + 1) + ":
                // joue le coup");
                // System.out.println(c.toString());

                // Joue
                ktrois.jouer(c, ktrois.getJoueurCourant());

                // Afiichage
                // System.out.println(ktrois.joueur1().getCamp().toString());
                // System.out.println(ktrois.joueur2().getCamp().toString());
                // System.out.println(ktrois.getBaseMontagne().toString());

                // changer le joueur courant
                ktrois.changementJoueurCourant();
            }

            // GAGNANT
            if (ktrois.getJoueurCourant() == 0) {
                victoirej2++;
            } else {
                victoirej1++;
            }
            nbParties++;
        }
        ktrois.combinerStats(0, objectif);
        System.out.println("Nombre de parties jouees : " + objectif);
        System.out.println("Taux de victoire du joueur 1 : " + ((double) victoirej1 * 100 / ((double) objectif)) + "%");
        System.out.println("Taux de victoire du joueur 2 : " + ((double) victoirej2 * 100 / ((double) objectif)) + "%");
        double t2 = (double) System.currentTimeMillis();
        System.out.println("Temps moyen d'une partie : " + (t2 - t1) / (objectif * 1000) + " s");
        System.out.println("Temps total de toutes les parties : " + ((t2 - t1) / 1000) + " s");
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