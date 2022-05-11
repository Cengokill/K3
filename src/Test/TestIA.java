package Test;

import Controleur.*;
import Modeles.*;

public class TestIA {
    private final static int TAILLE_CAMP_JOUEUR = 21;

    public static void main(String[] args) {
        int objectif = 1; // nombre de partie de test
        int nbParties = 0;
        int victoirej1 = 0;
        int victoirej2 = 0;

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
            IApiocheAlea iaP = new IApiocheAlea();

            pj = iaP.CreerPioche(ktrois.joueur1().getPiecesPiochees());
            ktrois.joueur1().setCamp(pj); // Tester avec get
            System.out.println(ktrois.joueur1().getCamp().toString());

            pj = iaP.CreerPioche(ktrois.joueur2().getPiecesPiochees());
            ktrois.joueur2().setCamp(pj);
            System.out.println(ktrois.joueur2().getCamp().toString());

            System.out.println(ktrois.getBaseMontagne().toString());

            // PHASE DE JEU
            System.out.println();
            System.out.println("Phase de jeu");
            IAjeuAlea iaJ = new IAjeuAlea();
            IAjeuExpert iaE = new IAjeuExpert();
            while (!ktrois.estPartieFinie(joueurCourant)) { // Argument partie en cours
                Coup c;
                if (joueurCourant == 0) {
                    c = iaJ.IACoup(ktrois, joueurCourant);
                } else {
                    c = iaE.IACoup(ktrois, joueurCourant);
                    if (c == null) {
                    }
                }
                System.out.print("Le joueur numero " + (joueurCourant + 1) + ": joue le coup");
                System.out.println(c.toString());

                // Joue
                ktrois.jouer(c, joueurCourant);

                // Afiichage
                System.out.println(ktrois.joueur1().getCamp().toString());
                System.out.println(ktrois.joueur2().getCamp().toString());
                System.out.println(ktrois.getBaseMontagne().toString());

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
            ktrois.affichesac();
        }

        System.out.println("Taux de victoire du joueur 1: " + (victoirej1 * 100 / (objectif)) + "%");
        System.out.println("Taux de victoire du joueur 2: " + (victoirej2 * 100 / (objectif)) + "%");

    }

}